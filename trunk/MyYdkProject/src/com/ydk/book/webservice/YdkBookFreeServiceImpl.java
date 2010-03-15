/**
 * 
 */
package com.ydk.book.webservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ydk.account.persistence.entity.BookMark;
import com.ydk.account.persistence.entity.Evaluation;
import com.ydk.account.persistence.entity.Reader;
import com.ydk.account.persistence.entity.ReaderComment;
import com.ydk.account.persistence.entity.ResourceType;
import com.ydk.account.persistence.interfaces.AccountDbMngr;
import com.ydk.account.webservice.WebListResultHelper;
import com.ydk.book.persistence.entity.Book;
import com.ydk.book.persistence.entity.BookNews;
import com.ydk.book.persistence.entity.Interview;
import com.ydk.book.persistence.interfaces.BookDbMngr;
import com.ydk.search.SimpleSearchManager;

/**
 * @author y140zhan
 *
 */
@WebService(endpointInterface = "com.ydk.book.webservice.YdkBookFreeService")
@Transactional
public final class YdkBookFreeServiceImpl implements YdkBookFreeService {
	
	protected final BookDbMngr bookDbMngr;
	protected final AccountDbMngr accountDbMngr;
	protected final SimpleSearchManager searchManager;
	
	@Autowired
	public YdkBookFreeServiceImpl(
				BookDbMngr bookDbMngr, 
				AccountDbMngr accountDbMngr, 
				SimpleSearchManager searchManager) {
		this.bookDbMngr = bookDbMngr;
		this.accountDbMngr = accountDbMngr;
		this.searchManager = searchManager;
		try {
			searchManager.init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	

	//Get listBooks
	public WebBookListResult listBooks(WebServiceListParams webBookListParams) {
		List<Book> list = (List<Book>) bookDbMngr.getBooks();
		WebBookListResult result= new WebBookListResult();
		
		result = (WebBookListResult) WebListResultHelper.getSpecifiedPageList((List)list, webBookListParams, result);
		
		list = new ArrayList<Book>(0);
		//Eager get @ManytoOne, Null oneToMany;
		for(Book instance : result.getList())
		{
			list.add(instance.setToPlainObjectQuick(instance));
			
		}
		result.setBooks(list);
		
		return result;
		
	}

	//Evaluate Book
	public OperationStatus evaluateBook(Long readerId, Long bookId, Double score) {
		if (readerId == null || bookId == null || score == null)
		{
			return new OperationStatus(false, "null parameters");
		}
		Book book =  bookDbMngr.getBook(bookId);
		if (book == null)
			return new OperationStatus(false, "book not found");
		Reader reader = accountDbMngr.getReader(readerId);
		if (reader == null)
		{
			return new OperationStatus(false, "reader not found");
		}
		if (book.getScore() == null)
		{
			book.setScore(score);
			book.setScoreReaderNum(1);
		}
		else
		{
			book.setScore((book.getScore() + score)/(book.getScoreReaderNum() + 1));
			book.setScoreReaderNum(book.getScoreReaderNum() + 1);
		}
		bookDbMngr.saveBook(book);
		Evaluation evaluation = new Evaluation(reader, bookId, ResourceType.RESOURCETYPE_BOOK,
				score, new Date());
		
		accountDbMngr.storeEvaluation(evaluation);
		return new OperationStatus();
		
	}

	//CommentBook
	public OperationStatus commentBook(Long readerId, Long bookId, String comments) {
		if (readerId == null || bookId == null || comments == null)
		{
			return new OperationStatus(false, "null parameters");
		}
		Book book =  bookDbMngr.getBook(bookId);
		if (book == null)
			return new OperationStatus(false, "book not found");
		Reader reader = accountDbMngr.getReader(readerId);
		if (reader == null)
		{
			return new OperationStatus(false, "reader not found");
		}
		
		//Do the logic
		ReaderComment rc = new ReaderComment(reader, bookId, ResourceType.RESOURCETYPE_BOOK,
				comments, new Date());
		
		accountDbMngr.storeReaderComment(rc);
		
		return new OperationStatus();
	}

	public OperationStatus addBookMark(Long readerId, Long bookId, Integer chapter,
			Integer section, Integer page, String name) {
		// TODO Auto-generated method stub
		if (readerId == null || bookId == null || name == null)
		{
			return new OperationStatus(false, "null parameters");
		}
		Book book =  bookDbMngr.getBook(bookId);
		if (book == null)
			return new OperationStatus(false, "book not found");
		Reader reader = accountDbMngr.getReader(readerId);
		if (reader == null)
		{
			return new OperationStatus(false, "reader not found");
		}
		
		BookMark bm = new BookMark(reader, book, chapter, section,
				page, name, new Date());
		
		accountDbMngr.storeBookMark(bm);
		
		
		return new OperationStatus();
		
	}

	public OperationStatus removeBookMark(Long readerId, Long bookId,
			Long bookMarkId) {
		// TODO Auto-generated method stub
		if (readerId == null || bookId == null || bookMarkId == null)
		{
			return new OperationStatus(false, "null parameters");
		}
		Book book =  bookDbMngr.getBook(bookId);
		if (book == null)
			return new OperationStatus(false, "book not found");
		Reader reader = accountDbMngr.getReader(readerId);
		if (reader == null)
		{
			return new OperationStatus(false, "reader not found");
		}
		
		accountDbMngr.deleteBookMark(bookMarkId);
		return new OperationStatus();
	}

	public WebBookMarkListResult listMyBookMark(Long readerId, Long bookId,
			WebServiceListParams listParams) {
		// TODO Auto-generated method stub
		WebBookMarkListResult result = new WebBookMarkListResult();
		if (readerId == null || bookId == null || listParams == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("null parameters");
			return result;
		}
		
		Reader reader = accountDbMngr.getReader(readerId);
		if (reader == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("reader not found");
			return result;
		}
		Book book =  bookDbMngr.getBook(bookId);
		if (book == null)
		{
			//Search all my bookMarks
			List<BookMark> bookMarks = reader.getBookMarks();
			result = (WebBookMarkListResult) WebListResultHelper.getSpecifiedPageList(bookMarks, listParams, result);
			
		}
		else
		{
			//Search all my bookMarks on this book;
			List<BookMark> bookMarks = (List<BookMark>) accountDbMngr.findAllBookMarkes(reader, book);
			result = (WebBookMarkListResult) WebListResultHelper.getSpecifiedPageList(bookMarks, listParams, result);
		}
		
		//Eager get @ManytoOne, Null oneToMany;
		for(BookMark instance : result.getList())
		{
			instance.setToPlainObjectQuick(instance);
		}
		
		return result;
	}
	
	
	public OperationStatus buyEbook(Long readerId, Long bookId) {
		// TODO Auto-generated method stub
		return null;
	}

	public OperationStatus buyPaperBook(Long readerId, Long bookId) {
		// TODO Auto-generated method stub
		return null;
	}

	public WebServiceFile getImage(Long imageId) {
		return null;
	}





	public WebBookListResult searchBooks(String keywords,
				WebServiceListParams webBookListParams) {
		// TODO Auto-generated method stub
		List<String> bookIds = this.searchManager.searchBookExact(keywords);
		WebBookListResult result = new WebBookListResult();
		result.setBooks(new ArrayList<Book>(0));
		Book book =null;
		
		for(String stringId : bookIds)
		{
			book = this.bookDbMngr.getBook(new Long(stringId));
			if (book != null)
			{
				//Found it;
				book = book.setToPlainObjectQuick(book);
				result.getBooks().add(book);
			}
		}
		//Done, do the page spliting;
		result = (WebBookListResult) WebListResultHelper.getSpecifiedPageList(
				(List)result.getBooks(), webBookListParams, result);
		
		
		return result;
	}

}
