/**
 * 
 */
package com.ydk.book.webservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ydk.account.persistence.entity.BookMark;
import com.ydk.account.persistence.entity.Category;
import com.ydk.account.persistence.entity.Evaluation;
import com.ydk.account.persistence.entity.Library;
import com.ydk.account.persistence.entity.Partner;
import com.ydk.account.persistence.entity.Reader;
import com.ydk.account.persistence.entity.ReaderComment;
import com.ydk.account.persistence.entity.ResourceType;
import com.ydk.account.persistence.interfaces.AccountDbMngr;
import com.ydk.account.webservice.WebListResultHelper;
import com.ydk.book.persistence.entity.Book;
import com.ydk.book.persistence.entity.BookNews;
import com.ydk.book.persistence.entity.BookNewsCategory;
import com.ydk.book.persistence.entity.BookReview;
import com.ydk.book.persistence.entity.BookReviewCategory;
import com.ydk.book.persistence.entity.Event;
import com.ydk.book.persistence.entity.EventCategory;
import com.ydk.book.persistence.entity.Interview;
import com.ydk.book.persistence.entity.InterviewCategory;
import com.ydk.book.persistence.interfaces.BookDbMngr;
import com.ydk.web.FileManager;

/**
 * @author y140zhan
 *
 */
@WebService(endpointInterface = "com.ydk.book.webservice.YdkFrontEndWebService")
@Transactional
public final class YdkFrontEndWebServiceImpl implements YdkFrontEndWebService {
	
	private final Logger logger = Logger.getLogger(YdkFrontEndWebServiceImpl.class.getName());
	protected final BookDbMngr bookDbMngr;
	protected final AccountDbMngr accountDbMngr;
	
	@Autowired
	public YdkFrontEndWebServiceImpl(BookDbMngr bookDbMngr, AccountDbMngr accountDbMngr) {
		this.bookDbMngr = bookDbMngr;
		this.accountDbMngr = accountDbMngr;
	}
	
	
	public WebLibraryListResult getLibraries(WebServiceListParams listParams) {
		// TODO Auto-generated method stub
		List<Library> bookLibraryList = 
			(List<Library>)bookDbMngr.getAllLibraries();
		WebLibraryListResult result = new WebLibraryListResult();
		
		result= (WebLibraryListResult) WebListResultHelper.getSpecifiedPageList(bookLibraryList, listParams, result);
		//Eager get @ManytoOne, Null oneToMany;
		for(Library instance : result.getList())
		{
			instance.setToPlainObjectQuick(instance);
		}
		
		return result;
		
	}
	
	
	public WebBookCateListResult getBookCategroryList(Long libId,
			WebServiceListParams listParams) {
		// TODO Auto-generated method stub
		List<Category> bookCateList =(List<Category>) bookDbMngr.getLibrary(libId).getCategories();

		WebBookCateListResult result = new WebBookCateListResult();
		
		result =(WebBookCateListResult) WebListResultHelper.getSpecifiedPageList(bookCateList, listParams, result);
		
		
		//Eager get @ManytoOne, Null oneToMany;
		for(Category instance : result.getList())
		{
			instance.setToPlainObjectQuick(instance);
		}
		
		return result;
	
	}




	public WebBookListResult getBookListInCategory(Long cateId,
			WebServiceListParams listParams) {
		List<Book> list = 
			(List<Book>)bookDbMngr.getCategory(cateId).getBooks();
		WebBookListResult result = new WebBookListResult();
		
		result= (WebBookListResult) WebListResultHelper.getSpecifiedPageList(list, listParams, result);
		
		list = new ArrayList<Book>(0);
		//Eager get @ManytoOne, Null oneToMany;
		for(Book instance : result.getList())
		{
			list.add(instance.setToPlainObjectQuick(instance));
			
		}
		result.setBooks(list);
		
		return result;
	}


	public WebServiceFile getFileByName(String fileName) {
		// TODO Auto-generated method stub
		WebServiceFile result = new WebServiceFile();
		if (fileName != null && !fileName.isEmpty())
		{
			//Check the fileName is valid;
			String checkResult = FileManager.checkFileName(fileName);
			if (checkResult != null)
			{
				logger.error("fileName error : " + checkResult);
				result.getOs().setIsSuccessful(false);
				result.getOs().setFailureReason(checkResult);
				return result;
			}
			result.setFile(FileManager.loadUploadedFile(fileName));
			return result;
		}
		logger.error("Empty fileName");
		result.getOs().setIsSuccessful(false);
		result.getOs().setFailureReason("Empty fileName");
		return result;
	}


	
	
	

}
