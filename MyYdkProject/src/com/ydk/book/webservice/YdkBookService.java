/**
 * 
 */
package com.ydk.book.webservice;

/**
 * @author y140zhan
 *
 */
import java.util.Collection;
import java.util.List;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;

import org.springframework.dao.DataAccessException;

import com.ydk.book.webservice.WebServiceBook;
import com.ydk.book.persistence.entity.Book;
import com.ydk.account.persistence.entity.*;
import com.ydk.book.webservice.WebServiceBook;


@WebService
public interface YdkBookService {
    
    List<WebServiceBook> getAllBooks();
    
    /**
	 * Retrieve all books maxNumPerPage of <code>Book</code>s from the data store.
	 * @return Collection<Book>
	 * @throws 
	 */
    List<WebServiceBook> getBooks(@WebParam(name = "maxNumPerPage")Integer maxNumPerPage);
	
	/**
	 * Retrieve all <code>Book</code>s from the data store similar with bookName,
	 * returning all books whose name <i>contains</i> with the given name.
	 * @param bookName
	 * @return Collection<Book>
	 * @throws DataAccessException
	 */
    List<WebServiceBook> findBooks(@WebParam(name = "bookName")String bookName);
	
	/**
	 * Retrieve <code>Book</code>s from the data store by last name,
	 * returning all books whose name <i>contains</i> with the given name.
	 * @param bookName
	 * @return Collection<Book>
	 * @throws DataAccessException
	 */
    List<WebServiceBook> findLimitedBooks(@WebParam(name = "bookName")String bookName,
    		@WebParam(name = "maxNumPerPage")Integer maxNumPerPage);

	/**
	 * Retrieve an <code>Book</code> from the data store by bookTitle.
	 * @param bookTitle the unique name to search for
	 * @return
	 * @throws org.springframework.dao.DataRetrievalFailureException if not
	 *         found
	 */
    WebServiceBook getBookByTitle(@WebParam(name = "bookTitle")String bookTitle) ;
	

	/**
	 * Retrieve an <code>Book</code> from the data store by bookId.
	 * @param bookId the unique id to search for
	 * @return
	 * @throws org.springframework.dao.DataRetrievalFailureException if not
	 *         found
	 */
    WebServiceBook getBook(@WebParam(name = "bookId")Long bookId);

	/**
	 * Save an <code>Book</code> to the data store, either inserting or
	 * updating it.
	 *
	 * @param book the <code>Book</code> to save
	 * 
	 */
	void saveBook(@WebParam(name = "book")WebServiceBook book);
	

	/**
	 * Delete an <code>Book</code> from the data store by bookName.
	 *
	 * @param book the <code>Book</code> to save
	 * 
	 */
	void deleteBookByTitle(@WebParam(name = "bookTitle")String bookTitle);
	
	/**
	 * Delete an <code>Book</code> from the data store by book Id.
	 * @param bookId
	 * @throws DataAccessException
	 */
	void deleteBook(@WebParam(name = "bookId")Long bookId);
	
	/**
	 * Retrieve an <code>UploadedFile</code> from the data store by fileId.
	 * @param fileId the unique id to search for
	 * @return
	 * @throws org.springframework.dao.DataRetrievalFailureException if not
	 *         found
	 */
	UploadedFile getUploadedFile(@WebParam(name = "fileId")Long fileId);

	/**
	 * Save an <code>UploadedFile</code> to the data store, either inserting or
	 * updating it.
	 *
	 * @param uploadedFile the <code>UploadedFile</code> to save
	 * 
	 */
	void storeUploadedFile(@WebParam(name = "uploadedFile")WebServiceBook uploadedFile);
}
