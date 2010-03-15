
package com.ydk.book.persistence.interfaces;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.ydk.account.persistence.entity.Account;
import com.ydk.account.persistence.entity.Address;
import com.ydk.account.persistence.entity.BookMark;
import com.ydk.account.persistence.entity.Reader;
import com.ydk.account.persistence.entity.Transaction;
import com.ydk.book.persistence.entity.*;
import com.ydk.account.persistence.entity.*;

import org.springframework.dao.DataAccessException;

/**
 * The high-level ydk business db interface.
 * <p>
 * This is basically a data access object. ydk should add more dedicated
 * business facades.
 *
 * @author Zhang Yu
 */
public interface BookDbMngr {


	/**
	 * Retrieve all <code>Book</code>s from the data store.
	 * @return Collection<Book>
	 * @throws DataAccessException
	 */
	Collection<Book> getBooks() throws DataAccessException;
	
	/**
	 * Retrieve the specified maxNumPerPage of <code>Book</code>s from the data store.
	 * @return Collection<Book>
	 * @throws DataAccessException
	 */
	Collection<Book> getBooks(Integer maxNumPerPage) throws DataAccessException;
	
	/**
	 * Retrieve all <code>Book</code>s from the data store simillay with BookName,
	 * returning all Books whose name <i>contains</i> with the given name.
	 * @param BookName
	 * @return Collection<Book>
	 * @throws DataAccessException
	 */
	Collection<Book> findBooks(String BookName) throws DataAccessException;
	
	/**
	 * Retrieve <code>Book</code>s from the data store by last name,
	 * returning all Books whose name <i>contains</i> with the given name.
	 * @param BookName
	 * @return Collection<Book>
	 * @throws DataAccessException
	 */
	Collection<Book> findBooks(String BookName, Integer maxNumPerPage) throws DataAccessException;

	/**
	 * Retrieve an <code>Book</code> from the data store by BookName.
	 * @param BookName the unique name to search for
	 * @return
	 * @throws org.springframework.dao.DataRetrievalFailureException if not
	 *         found
	 */
	Book getBook(String BookName) throws DataAccessException;
	
	Book getBookByYdkId(String ydkId) throws DataAccessException;
	

	/**
	 * Retrieve an <code>Book</code> from the data store by BookId.
	 * @param BookId the unique id to search for
	 * @return
	 * @throws org.springframework.dao.DataRetrievalFailureException if not
	 *         found
	 */
	Book getBook(Long BookId) throws DataAccessException;

	/**
	 * Save an <code>Book</code> to the data store, either inserting or
	 * updating it.
	 *
	 * @param Book the <code>Book</code> to save
	 * 
	 */
	void saveBook(Book Book) throws DataAccessException;
	

	/**
	 * Delete an <code>Book</code> from the data store by BookName.
	 *
	 * @param Book the <code>Book</code> to save
	 * 
	 */
	void deleteBook(String BookName) throws DataAccessException;
	
	/**
	 * Delete an <code>Book</code> from the data store by Book Id.
	 * @param BookId
	 * @throws DataAccessException
	 */
	void deleteBook(Long BookId) throws DataAccessException;
	
	EntityManager getEntityManager();
	
	UploadedFile getUploadedFile(Long fileId) throws DataAccessException;
//	UploadedFile getUploadedFileSmall(Long fileId) throws DataAccessException;
//	UploadedFile getUploadedFileLarge(Long fileId) throws DataAccessException;
//	UploadedFile getUploadedFile3DSmall(Long fileId) throws DataAccessException;
//	UploadedFile getUploadedFile3DLarge(Long fileId) throws DataAccessException;
//	UploadedFile getUploadedFileContent(Long fileId) throws DataAccessException;
//	
	void storeUploadedFile(UploadedFile uploadedFile) throws DataAccessException;
//	void storeUploadedFileSmall(UploadedFile uploadedFile) throws DataAccessException;
//	void storeUploadedFileLarge(UploadedFile uploadedFile) throws DataAccessException;
//	void storeUploadedFile3DSmall(UploadedFile uploadedFile) throws DataAccessException;
//	void storeUploadedFile3DLarge(UploadedFile uploadedFile) throws DataAccessException;
//	void storeUploadedFileContent(UploadedFile uploadedFile) throws DataAccessException;

	
	
	void saveBook(Book book, UploadedFile uploadedFileSmall,
			UploadedFile uploadedFileLarge,UploadedFile uploadedFile3DSmall,
			UploadedFile uploadedFile3DLarge,UploadedFile uploadedFileContent) throws DataAccessException;
//	void saveBookSmall(Book book, UploadedFile uploadedFile) throws DataAccessException;
//	void saveBookLarge(Book book, UploadedFile uploadedFile) throws DataAccessException;
//	void saveBook3DSmall(Book book, UploadedFile uploadedFile) throws DataAccessException;
//	void saveBook3DLarge(Book book, UploadedFile uploadedFile) throws DataAccessException;
//	void saveBookContent(Book book, UploadedFile uploadedFile) throws DataAccessException;
	
	FileDescriptor getBookFile(Long fileId) throws DataAccessException ;
	
	List<Transaction> listTransactionByBook(Date startDate,Date endDate, Long bookId);
	List<Transaction> listTransactionByPartner(Date startDate, Date endDate, Long partnerId);
	List<Transaction>listTransactionByReader(Date startDate, Date endDate, Long readId);
	
	
	/**
	 * Retrieve all <code>Event</code>s from the data store.
	 * @return Collection<Event>
	 * @throws DataAccessException
	 */
	Collection<Event> getAllEvents() throws DataAccessException;
	Collection<Event> getLimitedEvents(Integer maxNumPerPage) throws DataAccessException;
	Collection<Event> findAllEvents(Object searchKey) throws DataAccessException;
	Collection<Event> findLimitedEvents(Object searchKey, Integer maxNumPerPage) throws DataAccessException;
	
	/**
	 * CRUD
	 */
	Event getEvent(Object id) throws DataAccessException;
	void storeEvent(Event instance) throws DataAccessException;
	void updateEvent(Event instance) throws DataAccessException;
	void deleteEvent(Object id) throws DataAccessException;
	
	/**
	 * Retrieve all <code>BookNews</code>s from the data store.
	 * @return Collection<BookNews>
	 * @throws DataAccessException
	 */
	Collection<BookNews> getAllBookNewses() throws DataAccessException;
	Collection<BookNews> getLimitedBookNewses(Integer maxNumPerPage) throws DataAccessException;

	Collection<BookNews> findAllBookNewses(Object searchKey) throws DataAccessException;
	Collection<BookNews> findLimitedBookNewses(Object searchKey, Integer maxNumPerPage) throws DataAccessException;
	/**
	 * CRUD
	 */
	BookNews getBookNews(Object id) throws DataAccessException;
	void storeBookNews(BookNews instance) throws DataAccessException;
	void updateBookNews(BookNews instance) throws DataAccessException;
	void deleteBookNews(Object id) throws DataAccessException;
	
	
	
	/**
	 * Retrieve all <code>BookReview</code>s from the data store.
	 * @return Collection<BookReview>
	 * @throws DataAccessException
	 */
	Collection<BookReview> getAllBookReviews() throws DataAccessException;
	Collection<BookReview> getLimitedBookReviews(Integer maxNumPerPage) throws DataAccessException;

	Collection<BookReview> findAllBookReviews(Object searchKey) throws DataAccessException;
	Collection<BookReview> findLimitedBookReviews(Object searchKey, Integer maxNumPerPage) throws DataAccessException;
	/**
	 * CRUD
	 */
	BookReview getBookReview(Object id) throws DataAccessException;
	void storeBookReview(BookReview instance) throws DataAccessException;
	void updateBookReview(BookReview instance) throws DataAccessException;
	void deleteBookReview(Object id) throws DataAccessException;
	
	/**
	 * Retrieve all <code>Interview</code>s from the data store.
	 * @return Collection<Interview>
	 * @throws DataAccessException
	 */
	Collection<Interview> getAllInterviews() throws DataAccessException;
	Collection<Interview> getLimitedInterviews(Integer maxNumPerPage) throws DataAccessException;

	Collection<Interview> findAllInterviews(Object searchKey) throws DataAccessException;
	Collection<Interview> findLimitedInterviews(Object searchKey, Integer maxNumPerPage) throws DataAccessException;
	/**
	 * CRUD
	 */
	Interview getInterview(Object id) throws DataAccessException;
	void storeInterview(Interview instance) throws DataAccessException;
	void updateInterview(Interview instance) throws DataAccessException;
	void deleteInterview(Object id) throws DataAccessException;
	
	
	
	/**
	 * Retrieve all <code>InterviewCategory</code>s from the data store.
	 * @return Collection<InterviewCategory>
	 * @throws DataAccessException
	 */
	Collection<InterviewCategory> getAllInterviewCategories() throws DataAccessException;
	Collection<InterviewCategory> getLimitedInterviewCategories(Integer maxNumPerPage) throws DataAccessException;

	Collection<InterviewCategory> findAllInterviewCategories(Object searchKey) throws DataAccessException;
	Collection<InterviewCategory> findLimitedInterviewCategories(Object searchKey, Integer maxNumPerPage) throws DataAccessException;
	/**
	 * CRUD
	 */
	InterviewCategory getInterviewCategory(Object id) throws DataAccessException;
	void storeInterviewCategory(InterviewCategory instance) throws DataAccessException;
	void updateInterviewCategory(InterviewCategory instance) throws DataAccessException;
	void deleteInterviewCategory(Object id) throws DataAccessException;
	
	
	/**
	 * Retrieve all <code>EventCategory</code>s from the data store.
	 * @return Collection<EventCategory>
	 * @throws DataAccessException
	 */
	Collection<EventCategory> getAllEventCategories() throws DataAccessException;
	Collection<EventCategory> getLimitedEventCategories(Integer maxNumPerPage) throws DataAccessException;

	Collection<EventCategory> findAllEventCategories(Object searchKey) throws DataAccessException;
	Collection<EventCategory> findLimitedEventCategories(Object searchKey, Integer maxNumPerPage) throws DataAccessException;
	/**
	 * CRUD
	 */
	EventCategory getEventCategory(Object id) throws DataAccessException;
	void storeEventCategory(EventCategory instance) throws DataAccessException;
	void updateEventCategory(EventCategory instance) throws DataAccessException;
	void deleteEventCategory(Object id) throws DataAccessException;
	
	
	/**
	 * Retrieve all <code>BookNewsCategory</code>s from the data store.
	 * @return Collection<BookNewsCategory>
	 * @throws DataAccessException
	 */
	Collection<BookNewsCategory> getAllBookNewsCategories() throws DataAccessException;
	Collection<BookNewsCategory> getLimitedBookNewsCategories(Integer maxNumPerPage) throws DataAccessException;

	Collection<BookNewsCategory> findAllBookNewsCategories(Object searchKey) throws DataAccessException;
	Collection<BookNewsCategory> findLimitedBookNewsCategories(Object searchKey, Integer maxNumPerPage) throws DataAccessException;
	/**
	 * CRUD
	 */
	BookNewsCategory getBookNewsCategory(Object id) throws DataAccessException;
	void storeBookNewsCategory(BookNewsCategory instance) throws DataAccessException;
	void updateBookNewsCategory(BookNewsCategory instance) throws DataAccessException;
	void deleteBookNewsCategory(Object id) throws DataAccessException;
	
	
	/**
	 * Retrieve all <code>BookReviewCategory</code>s from the data store.
	 * @return Collection<BookReviewCategory>
	 * @throws DataAccessException
	 */
	Collection<BookReviewCategory> getAllBookReviewCategories() throws DataAccessException;
	Collection<BookReviewCategory> getLimitedBookReviewCategories(Integer maxNumPerPage) throws DataAccessException;

	Collection<BookReviewCategory> findAllBookReviewCategories(Object searchKey) throws DataAccessException;
	Collection<BookReviewCategory> findLimitedBookReviewCategories(Object searchKey, Integer maxNumPerPage) throws DataAccessException;
	/**
	 * CRUD
	 */
	BookReviewCategory getBookReviewCategory(Object id) throws DataAccessException;
	void storeBookReviewCategory(BookReviewCategory instance) throws DataAccessException;
	void updateBookReviewCategory(BookReviewCategory instance) throws DataAccessException;
	void deleteBookReviewCategory(Object id) throws DataAccessException;
	
	
	/**
	 * Retrieve all <code>Library</code>s from the data store.
	 * @return Collection<Library>
	 * @throws DataAccessException
	 */
	Collection<Library> getAllLibraries() throws DataAccessException;
	Collection<Library> getLimitedLibraries(Integer maxNumPerPage) throws DataAccessException;

	Collection<Library> findAllLibraries(Object searchKey) throws DataAccessException;
	Collection<Library> findLimitedLibraries(Object searchKey, Integer maxNumPerPage) throws DataAccessException;
	/**
	 * CRUD
	 */
	Library getLibrary(Object id) throws DataAccessException;
	Library getLibraryByName(String name) throws DataAccessException;
	void storeLibrary(Library instance) throws DataAccessException;
	void updateLibrary(Library instance) throws DataAccessException;
	void deleteLibrary(Object id) throws DataAccessException;
	
	/**
	 * Retrieve all <code>Category</code>s from the data store.
	 * @return Collection<Category>
	 * @throws DataAccessException
	 */
	Collection<Category> getAllCategories() throws DataAccessException;
	Collection<Category> getLimitedCategories(Integer maxNumPerPage) throws DataAccessException;

	Collection<Category> findAllCategories(Object searchKey) throws DataAccessException;
	Collection<Category> findLimitedCategories(Object searchKey, Integer maxNumPerPage) throws DataAccessException;
	/**
	 * CRUD
	 */
	Category getCategory(Object id) throws DataAccessException;
	Category getCategoryByName(String name) throws DataAccessException;
	void storeCategory(Category instance) throws DataAccessException;
	void updateCategory(Category instance) throws DataAccessException;
	void deleteCategory(Object id) throws DataAccessException;
	
	BookNewsCategory getBookNewsCategoryByName(String name) throws DataAccessException;
	BookReviewCategory getBookReviewCategoryByName(String name) throws DataAccessException;
	EventCategory getEventCategoryByName(String name) throws DataAccessException;
	InterviewCategory getInterviewCategoryByName(String name) throws DataAccessException;
	
	
	BookNews getBookNewsByTitle(String name) throws DataAccessException;
	BookReview getBookReviewByTitle(String name) throws DataAccessException;
	Event getEventByTitle(String name) throws DataAccessException;
	Interview getInterviewByTitle(String name) throws DataAccessException;
	
	
	/**
	 * Retrieve all <code>BookTag</code>s from the data store.
	 * @return Collection<BookTag>
	 * @throws DataAccessException
	 */
	Collection<BookTag> getAllBookTags() throws DataAccessException;
	Collection<BookTag> getLimitedBookTags(Integer maxNumPerPage) throws DataAccessException;

	Collection<BookTag> findAllBookTags(Object searchKey) throws DataAccessException;
	Collection<BookTag> findLimitedBookTags(Object searchKey, Integer maxNumPerPage) throws DataAccessException;
	/**
	 * CRUD
	 */
	BookTag getBookTag(Object id) throws DataAccessException;
	void storeBookTag(BookTag instance) throws DataAccessException;
	void updateBookTag(BookTag instance) throws DataAccessException;
	void deleteBookTag(Object id) throws DataAccessException;
	BookTag getBookTagByName(String name) throws DataAccessException;
	
	
	


}
