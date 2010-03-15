/**
 * 
 */
package com.ydk.book.webservice;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.jws.WebService;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ydk.book.core.WebServiceBookHelper;
import com.ydk.book.persistence.entity.Book;
import com.ydk.account.persistence.entity.*;
import com.ydk.book.persistence.interfaces.BookDbMngr;
import com.ydk.book.webservice.YdkBookService;
/**
 * @author y140zhan
 *
 */

@WebService(endpointInterface = "com.ydk.book.webservice.YdkBookService")
@Transactional
public final class YdkBookServiceImpl implements YdkBookService {

	public void deleteBook(Long bookId) {
		// TODO Auto-generated method stub
		
	}

	public void deleteBookByTitle(String bookTitle) {
		// TODO Auto-generated method stub
		
	}

	public List<WebServiceBook> findBooks(String bookName) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<WebServiceBook> findLimitedBooks(String bookName,
			Integer maxNumPerPage) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<WebServiceBook> getAllBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	public WebServiceBook getBook(Long bookId) {
		// TODO Auto-generated method stub
		return null;
	}

	public WebServiceBook getBookByTitle(String bookTitle) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<WebServiceBook> getBooks(Integer maxNumPerPage) {
		// TODO Auto-generated method stub
		return null;
	}

	public UploadedFile getUploadedFile(Long fileId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveBook(WebServiceBook book) {
		// TODO Auto-generated method stub
		
	}

	public void storeUploadedFile(WebServiceBook uploadedFile) {
		// TODO Auto-generated method stub
		
	}
	
//	protected final BookDbMngr bookDbMngr;
//	
//	@Autowired
//	public YdkBookServiceImpl(BookDbMngr bookDbMngr) {
//		this.bookDbMngr = bookDbMngr;
//	}
//
//	
//    public List<WebServiceBook> getAllBooks() {
//    	List<Book> books = (List<Book>) bookDbMngr.getBooks();
//    	
//        return WebServiceBookHelper.wrapBookList(books);
//    }
//
//	public void deleteBookByTitle(String bookTitle) {
//		// TODO Maybe we could user Thread pool here
//		bookDbMngr.deleteBook(bookTitle);
//		
//	}
//
//	public void deleteBook(Long bookId) {
//		// TODO Auto-generated method stub
//		bookDbMngr.deleteBook(bookId);
//	}
//
//	public List<WebServiceBook> findBooks(String bookTitle) {
//		// TODO Auto-generated method stub
//		List<Book> books = (List<Book>) bookDbMngr.findBooks(bookTitle);
//		
//		return WebServiceBookHelper.wrapBookList(books);
//	}
//
//	public List<WebServiceBook> findLimitedBooks(String bookTitle,
//			Integer maxNumPerPage) {
//		List<Book> books = (List<Book>) bookDbMngr.findBooks(bookTitle, maxNumPerPage);
//		
//		return WebServiceBookHelper.wrapBookList(books);
//	}
//
//	public WebServiceBook getBookByTitle(String bookTitle) {
//		
//		return WebServiceBookHelper.wrapBook(bookDbMngr.getBook(bookTitle));
//	}
//
//	public WebServiceBook getBook(Long bookId) {
//		// TODO Auto-generated method stub
//		return WebServiceBookHelper.wrapBook(bookDbMngr.getBook(bookId));
//	}
//
//	public List<WebServiceBook> getBooks(Integer maxNumPerPage) {
//		// TODO Auto-generated method stub
//		List<Book> books = 
//			(List<Book>) bookDbMngr.getBooks(maxNumPerPage);
//		
//		return WebServiceBookHelper.wrapBookList(books);
//	}
//	public UploadedFile getUploadedFile(Long fileId) {
//		return bookDbMngr.getUploadedFile(fileId);
//	}
//
////	public WebServiceBook getUploadedFileSmall(Long fileId) {
////		return WebServiceBookHelper.wrapUploadedFileSmall(bookDbMngr.getUploadedFile(fileId));
////	}
////	public WebServiceBook getUploadedFileLarge(Long fileId) {
////		return WebServiceBookHelper.wrapUploadedFileLarge(bookDbMngr.getUploadedFile(fileId));
////	}
////	public WebServiceBook getUploadedFile3DSmall(Long fileId) {
////		return WebServiceBookHelper.wrapUploadedFile3DSmall(bookDbMngr.getUploadedFile(fileId));
////	}
////	public WebServiceBook getUploadedFile3DLarge(Long fileId) {
////		return WebServiceBookHelper.wrapUploadedFile3DLarge(bookDbMngr.getUploadedFile(fileId));
////	}
////	public WebServiceBook getUploadedFileContent(Long fileId) {
////		return WebServiceBookHelper.wrapUploadedFileContent(bookDbMngr.getUploadedFile(fileId));
////	}
//
//	public void saveBook(WebServiceBook book) {
//		bookDbMngr.saveBook(book.getBook(), book.getUploadedFileSmall(),
//				book.getUploadedFileLarge(),book.getUploadedFile3DSmall(),
//				book.getUploadedFile3DLarge(),book.getUploadedFileContent());
//		
//	}
//
//	public void storeUploadedFile(WebServiceBook book) {
//		bookDbMngr.storeUploadedFile(book.getUploadedFileSmall());
//		bookDbMngr.storeUploadedFile(book.getUploadedFileLarge());
//		bookDbMngr.storeUploadedFile(book.getUploadedFile3DSmall());
//		bookDbMngr.storeUploadedFile(book.getUploadedFile3DLarge());
//		bookDbMngr.storeUploadedFile(book.getUploadedFileContent());		
//	}
}
