
package com.ydk.book.persistence;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ydk.account.persistence.entity.Account;
import com.ydk.account.persistence.entity.Address;
import com.ydk.account.persistence.entity.Category;
import com.ydk.account.persistence.entity.FileDescriptor;
import com.ydk.account.persistence.entity.Library;
import com.ydk.account.persistence.entity.Transaction;
import com.ydk.account.persistence.operator.AccountEntity;
import com.ydk.account.persistence.operator.AddressEntity;
import com.ydk.account.persistence.operator.AddressQuery;
import com.ydk.account.persistence.operator.CategoryEntity;
import com.ydk.account.persistence.operator.CategoryQuery;
import com.ydk.account.persistence.operator.FileDescriptorEntity;
import com.ydk.account.persistence.operator.LibraryEntity;
import com.ydk.account.persistence.operator.LibraryQuery;
import com.ydk.account.persistence.operator.ReaderQuery;
import com.ydk.account.persistence.operator.TransactionQuery;
import com.ydk.book.persistence.entity.Author;
import com.ydk.book.persistence.entity.Book;
import com.ydk.book.persistence.entity.BookNews;
import com.ydk.book.persistence.entity.BookNewsCategory;
import com.ydk.book.persistence.entity.BookReview;
import com.ydk.book.persistence.entity.BookReviewCategory;
import com.ydk.book.persistence.entity.BookTag;
import com.ydk.book.persistence.entity.Event;
import com.ydk.book.persistence.entity.EventCategory;
import com.ydk.book.persistence.entity.Interview;
import com.ydk.book.persistence.entity.InterviewCategory;
import com.ydk.book.persistence.entity.Publisher;
import com.ydk.book.persistence.entity.Translator;
import com.ydk.account.persistence.entity.*;
import com.ydk.book.persistence.interfaces.BookDbMngr;
import com.ydk.book.persistence.operator.AuthorEntity;
import com.ydk.book.persistence.operator.AuthorQuery;
import com.ydk.book.persistence.operator.BookEntity;
import com.ydk.book.persistence.operator.BookNewsCategoryEntity;
import com.ydk.book.persistence.operator.BookNewsCategoryQuery;
import com.ydk.book.persistence.operator.BookNewsEntity;
import com.ydk.book.persistence.operator.BookNewsQuery;
import com.ydk.book.persistence.operator.BookQuery;
import com.ydk.book.persistence.operator.BookReviewCategoryEntity;
import com.ydk.book.persistence.operator.BookReviewCategoryQuery;
import com.ydk.book.persistence.operator.BookReviewEntity;
import com.ydk.book.persistence.operator.BookReviewQuery;
import com.ydk.book.persistence.operator.BookTagEntity;
import com.ydk.book.persistence.operator.BookTagQuery;
import com.ydk.book.persistence.operator.EventCategoryEntity;
import com.ydk.book.persistence.operator.EventCategoryQuery;
import com.ydk.book.persistence.operator.EventEntity;
import com.ydk.book.persistence.operator.EventQuery;
import com.ydk.book.persistence.operator.InterviewCategoryEntity;
import com.ydk.book.persistence.operator.InterviewCategoryQuery;
import com.ydk.book.persistence.operator.InterviewEntity;
import com.ydk.book.persistence.operator.InterviewQuery;
import com.ydk.book.persistence.operator.PublisherEntity;
import com.ydk.book.persistence.operator.PublisherQuery;
import com.ydk.book.persistence.operator.TranslatorEntity;
import com.ydk.book.persistence.operator.TranslatorQuery;
import com.ydk.book.persistence.operator.UploadedFileEntity;
import com.ydk.book.persistence.entity.Book;
import com.ydk.book.persistence.interfaces.BookDbMngr;
import com.ydk.book.persistence.operator.BookEntity;
import com.ydk.book.persistence.operator.BookQuery;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * JPA implementation of the Ydk Db interface using EntityManager.
 * <p>
 * The mappings are defined in "orm.xml" located in the META-INF directory.
 *
 * @author Zhang Yu
 */
@Repository
@Transactional
public class BookDbMngrImpl implements BookDbMngr{

	/** Logger that is available to subclasses
	private final Log logger = LogFactory.getLog(getClass().getName());
	 */
	
	@PersistenceContext
	private EntityManager em;
	

	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Book> findBooks(String bookName)
			throws DataAccessException {
		// TODO Auto-generated method stub
		BookQuery query = new BookQuery(em);
		query.searchBookByField("bookName", bookName);
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Book> findBooks(String bookName, Integer maxNumPerPage)
		throws DataAccessException {
		// TODO Auto-generated method stub
		BookQuery query = new BookQuery(em);
		query.setMaxResults(maxNumPerPage);
		query.searchBookByField("bookName", bookName);
		return query.getResultList();
	}

	@Transactional(readOnly = true)
	public Book getBook(String bookName) throws DataAccessException {
		// TODO Auto-generated method stub
		BookQuery query = new BookQuery(em, bookName);
		List<Book> entries = query.getResultList();
		if (entries.size() == 0)
		{
			return null;
		}
		else
		{
			//There should be only one entry matching.
			return entries.get(0);
		}
	}
	
	@Transactional(readOnly = true)
	public Book getBook(Long bookId) throws DataAccessException {
		BookEntity entity = new BookEntity(em);
		return entity.loadBook(bookId);
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Book> getBooks() throws DataAccessException {
		// TODO Auto-generated method stub
		BookQuery query = new BookQuery(em);
		
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Book> getBooks(Integer maxNumPerPage)
	throws DataAccessException {
		BookQuery query = new BookQuery(em);
		query.setMaxResults(maxNumPerPage);
		return query.getResultList();
	}

	/**
	 * Check the author before we save the book
	 * @param book
	 */
	private void checkAndSaveBookAuthor(Book book)
	{
		//1. We need to check if the Author, Translator, Publisher exists first.
		//1.1 If do not exist, we need to store them manually. Can we use Cascade here?
			//Author
		AuthorQuery query = new AuthorQuery(em);
		AuthorEntity entity = new AuthorEntity(em);
		
		if (book.getAuthor() != null && book.getAuthor().getId() == null
				&& book.getAuthor().getName() != null 
				&& !book.getAuthor().getName().isEmpty())
		{
			Author instance = query.searchEntityByName(book.getAuthor().getName());
			if (instance != null)
			{
				book.setAuthor(instance);
			}
			else
			{
				
				entity.setInstance(book.getAuthor());
				entity.persist();
			}
		}
		
		if (book.getAuthor1() != null && book.getAuthor1().getId() == null 
				&& book.getAuthor1().getName() != null
				&& !book.getAuthor1().getName().isEmpty())
		{
			Author instance = query.searchEntityByName(book.getAuthor1().getName());
			if (instance != null)
			{
				book.setAuthor1(instance);
			}
			else
			{
				
				entity.setInstance(book.getAuthor1());
				entity.persist();
			}
		}
		
		if (book.getAuthor2() != null && book.getAuthor2().getId() == null 
				&& book.getAuthor2().getName() != null
				&& !book.getAuthor2().getName().isEmpty())
		{
			Author instance = query.searchEntityByName(book.getAuthor2().getName());
			if (instance != null)
			{
				book.setAuthor2(instance);
			}
			else
			{
				
				entity.setInstance(book.getAuthor2());
				entity.persist();
			}
		}
		
		if (book.getAuthor3() != null && book.getAuthor3().getId() == null 
				&& book.getAuthor3().getName() != null
				&& !book.getAuthor3().getName().isEmpty())
		{
			Author instance = query.searchEntityByName(book.getAuthor3().getName());
			if (instance != null)
			{
				book.setAuthor3(instance);
			}
			else
			{
				
				entity.setInstance(book.getAuthor3());
				entity.persist();
			}
		}
		
		//OK, done!
		
	}
	
	
	/**
	 * Check the Translator before we save the book
	 * @param book
	 */
	private void checkAndSaveBookTranslator(Book book)
	{
		//1. We need to check if the Translator, Translator, Publisher exists first.
		//1.1 If do not exist, we need to store them manually. Can we use Cascade here?
			//Translator
		TranslatorQuery query = new TranslatorQuery(em);
		TranslatorEntity entity = new TranslatorEntity(em);
		
		if (book.getTranslator() != null && book.getTranslator().getId() == null 
				&& book.getTranslator().getName() != null
				&& !book.getTranslator().getName().isEmpty())
		{
			Translator instance = query.searchEntityByName(book.getTranslator().getName());
			if (instance != null)
			{
				book.setTranslator(instance);
			}
			else
			{
				
				entity.setInstance(book.getTranslator());
				entity.persist();
			}
		}
		
		if (book.getTranslator1() != null && book.getTranslator1().getId() == null
				&& book.getTranslator1().getName() != null
				&& !book.getTranslator1().getName().isEmpty())
		{
			Translator instance = query.searchEntityByName(book.getTranslator1().getName());
			if (instance != null)
			{
				book.setTranslator1(instance);
			}
			else
			{
				
				entity.setInstance(book.getTranslator1());
				entity.persist();
			}
		}
		
		if (book.getTranslator2() != null && book.getTranslator2().getId() == null 
				&& book.getTranslator2().getName() != null
				&& !book.getTranslator2().getName().isEmpty())
		{
			Translator instance = query.searchEntityByName(book.getTranslator2().getName());
			if (instance != null)
			{
				book.setTranslator2(instance);
			}
			else
			{
				
				entity.setInstance(book.getTranslator2());
				entity.persist();
			}
		}
		
		if (book.getTranslator3() != null && book.getTranslator3().getId() == null
				 && book.getTranslator3().getName() != null
				 && !book.getTranslator3().getName().isEmpty())
		{
			Translator instance = query.searchEntityByName(book.getTranslator3().getName());
			if (instance != null)
			{
				book.setTranslator3(instance);
			}
			else
			{
				
				entity.setInstance(book.getTranslator3());
				entity.persist();
			}
		}
		
		//OK, done!
		
	}
	
	
	/**
	 * Check the Publisher before we save the book
	 * @param book
	 */
	private void checkAndSaveBookPublisher(Book book)
	{
		//1. We need to check if the Publisher, Publisher, Publisher exists first.
		//1.1 If do not exist, we need to store them manually. Can we use Cascade here?
			//Publisher
		PublisherQuery query = new PublisherQuery(em);
		PublisherEntity entity = new PublisherEntity(em);
		
		if (book.getPublisher() != null && book.getPublisher().getId() == null 
				&& book.getPublisher().getName() != null
				&& !book.getPublisher().getName().isEmpty())
		{
			Publisher instance = query.searchEntityByName(book.getPublisher().getName());
			if (instance != null)
			{
				book.setPublisher(instance);
			}
			else
			{
				
				entity.setInstance(book.getPublisher());
				entity.persist();
			}
		}
		
		
		//OK, done!
		
	}
	
	
	/**
	 * Check the Category before we save the book
	 * @param book
	 */
	private void checkAndSaveBookCategory(Book book)
	{
		//1. We need to check if the Publisher, Publisher, Publisher exists first.
		//1.1 If do not exist, we need to store them manually. Can we use Cascade here?
			//Publisher
		CategoryQuery query = new CategoryQuery(em);
		CategoryEntity entity = new CategoryEntity(em);
		
		if (book.getCategory() != null && book.getCategory().getId() == null 
				&& book.getCategory().getName() != null
				&& !book.getCategory().getName().isEmpty())
		{
			Category instance = query.searchCategoryByName(book.getCategory().getName());
			if (instance != null)
			{
				book.setCategory(instance);
			}
			else
			{
				
				entity.setInstance(book.getCategory());
				entity.persist();
			}
		}
		
		
		//OK, done!
		
	}

	/**
	 * @see BookDbMngr#saveBook
	 */
	public void saveBook(Book book) throws DataAccessException {
		//store book or update book
		if (book.isNew())
		{
			//store book
			//0. The user of DbMngr should ensure there is no 
			//	book name confliction, else DataAccessException is thrown
			//1.  store book;
			//2. We need to check if the Author, Translator, Publisher exists first.
			
			checkAndSaveBookAuthor(book);
			checkAndSaveBookTranslator(book);
			checkAndSaveBookPublisher(book);
			checkAndSaveBookCategory(book);
			//Category
			
			BookEntity entity = new BookEntity(em);
			entity.setInstance(book);
			entity.persist();
			
		
		}
		else
		{
			//0. The user of DbMngr should ensure the book is valid,
			//else DataAccessException is thrown
			//update book
			
			//Do we need to check and save the Author?
			//Yes, the book should newver be deleted for transactions and statistics
			checkAndSaveBookAuthor(book);
			checkAndSaveBookTranslator(book);
			checkAndSaveBookPublisher(book);
			checkAndSaveBookCategory(book);
			
			BookEntity entity = new BookEntity(em, book.getId());
			entity.setInstance(book);
			entity.update();
		}
		
		//TODO before we return do we need to update the Search Engine!
		
		
	}
	
	public void deleteBook(String bookName) throws DataAccessException {
		// TODO Auto-generated method stub
		Book book = getBook(bookName);
		BookEntity entity = new BookEntity(em);
		entity.setInstance(book);
		entity.remove();
		
	}

	public void deleteBook(Long bookId) throws DataAccessException {
		// TODO Auto-generated method stub
		BookEntity entity = new BookEntity(em, bookId);
		entity.remove();
	}


	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}

	public UploadedFile getUploadedFile(Long fileId) throws DataAccessException {
		// TODO Auto-generated method stub
		UploadedFileEntity entity = new UploadedFileEntity(em);
		return entity.loadFile(fileId);
	}
	public void storeUploadedFile(UploadedFile uploadedFile)
	throws DataAccessException {
        // TODO Auto-generated method stub
        UploadedFileEntity entity = new UploadedFileEntity(em);
        entity.setInstance(uploadedFile);
        entity.persist();
    }
	public void updateUploadedFile(UploadedFile uploadedFile)
	throws DataAccessException {
	// TODO Auto-generated method stub
	UploadedFileEntity entity = new UploadedFileEntity(em);
	entity.setInstance(uploadedFile);
	entity.update();
}
	public void saveBook(Book book, UploadedFile uploadedFileSmall,
			UploadedFile uploadedFileLarge,UploadedFile uploadedFile3DSmall,
			UploadedFile uploadedFile3DLarge,UploadedFile uploadedFileContent) throws DataAccessException {
		//store book or update book
		
		//store book
		//0. The user of DbMngr should ensure there is no 
		//	book name conflict, else DataAccessException is thrown
		//1.  store book;
		
		BookEntity entity = new BookEntity(em);
		entity.setInstance(book);
		entity.persist();
		
	}
	
	public FileDescriptor getBookFile(Long fileId) throws DataAccessException {
		FileDescriptorEntity entity = new FileDescriptorEntity(em);
		return entity.loadFile(fileId);
	}
	
	
	public List<Transaction> listTransactionByBook(Date startDate,
			Date endDate, Long bookId) {
		if (bookId != null)
		{
			TransactionQuery tq = new TransactionQuery(getEntityManager());
			Book book = new Book();
			book.setId(bookId);
			return tq.searchByFieldWithDate("book", book, startDate, endDate);
		}
		return null;
	}

	public List<Transaction> listTransactionByPartner(Date startDate,
			Date endDate, Long partnerId) {
		// TODO Auto-generated method stub
		if (partnerId != null)
		{
			TransactionQuery tq = new TransactionQuery(getEntityManager());
			Partner partner = new Partner();
			partner.setId(partnerId);
			return tq.searchByFieldWithDate("partner", partner, startDate, endDate);
		}
		return null;
	}

	public List<Transaction> listTransactionByReader(Date startDate,
			Date endDate, Long readId) {
		if (readId != null)
		{
			TransactionQuery tq = new TransactionQuery(getEntityManager());
			Reader reader = new Reader();
			reader.setId(readId);
			return tq.searchByFieldWithDate("partner", reader, startDate, endDate);
		}
		return null;
	}
	
	
	
	/**
	 * Retrieve all <code>Interview</code>s from the data store.
	 * @return Collection<Interview>
	 * @throws DataAccessException
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Interview> getAllInterviews() throws DataAccessException
	{
		InterviewQuery query = new InterviewQuery(em);
		return query.getResultList();
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Interview> getLimitedInterviews(Integer maxNumPerPage) throws DataAccessException
	{
		InterviewQuery query = new InterviewQuery(em);
		query.setMaxResults(maxNumPerPage);
		return query.getResultList();
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Interview> findAllInterviews(Object searchKey) throws DataAccessException
	{
		return null;
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Interview> findLimitedInterviews(Object searchKey, Integer maxNumPerPage) throws DataAccessException
	{
		return null;
	}
	
	/**
	 * CRUD
	 */
	@Transactional(readOnly = true)
	public Interview getInterview(Object id) throws DataAccessException
	{
		InterviewEntity entity = new InterviewEntity(em);
		return entity.loadEntity(id);
	}
	
	public void storeInterview(Interview instance) throws DataAccessException
	{
		InterviewEntity entity = new InterviewEntity(em);
		entity.setInstance(instance);
		entity.persist();
	}
	public void updateInterview(Interview instance) throws DataAccessException
	{
		InterviewEntity entity = new InterviewEntity(em);
		entity.setInstance(instance);
		entity.update();
	}
	public void deleteInterview(Object id) throws DataAccessException
	{
		InterviewEntity entity = new InterviewEntity(em);
		if (entity.loadEntity(id) != null)
		{
			entity.remove();
		}
	}
	
	/**
	 * Retrieve all <code>BookReview</code>s from the data store.
	 * @return Collection<BookReview>
	 * @throws DataAccessException
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<BookReview> getAllBookReviews() throws DataAccessException
	{
		BookReviewQuery query = new BookReviewQuery(em);
		return query.getResultList();
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<BookReview> getLimitedBookReviews(Integer maxNumPerPage) throws DataAccessException
	{
		BookReviewQuery query = new BookReviewQuery(em);
		query.setMaxResults(maxNumPerPage);
		return query.getResultList();
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<BookReview> findAllBookReviews(Object searchKey) throws DataAccessException
	{
		return null;
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<BookReview> findLimitedBookReviews(Object searchKey, Integer maxNumPerPage) throws DataAccessException
	{
		return null;
	}
	
	/**
	 * CRUD
	 */
	@Transactional(readOnly = true)
	public BookReview getBookReview(Object id) throws DataAccessException
	{
		BookReviewEntity entity = new BookReviewEntity(em);
		return entity.loadEntity(id);
	}
	
	public void storeBookReview(BookReview instance) throws DataAccessException
	{
		BookReviewEntity entity = new BookReviewEntity(em);
		entity.setInstance(instance);
		entity.persist();
	}
	public void updateBookReview(BookReview instance) throws DataAccessException
	{
		BookReviewEntity entity = new BookReviewEntity(em);
		entity.setInstance(instance);
		entity.update();
	}
	public void deleteBookReview(Object id) throws DataAccessException
	{
		BookReviewEntity entity = new BookReviewEntity(em);
		if (entity.loadEntity(id) != null)
		{
			entity.remove();
		}
	}
	
	/**
	 * Retrieve all <code>Event</code>s from the data store.
	 * @return Collection<Event>
	 * @throws DataAccessException
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Event> getAllEvents() throws DataAccessException
	{
		EventQuery query = new EventQuery(em);
		return query.getResultList();
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Event> getLimitedEvents(Integer maxNumPerPage) throws DataAccessException
	{
		EventQuery query = new EventQuery(em);
		query.setMaxResults(maxNumPerPage);
		return query.getResultList();
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Event> findAllEvents(Object searchKey) throws DataAccessException
	{
		return null;
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Event> findLimitedEvents(Object searchKey, Integer maxNumPerPage) throws DataAccessException
	{
		return null;
	}
	
	/**
	 * CRUD
	 */
	@Transactional(readOnly = true)
	public Event getEvent(Object id) throws DataAccessException
	{
		EventEntity entity = new EventEntity(em);
		return entity.loadEntity(id);
	}
	
	public void storeEvent(Event instance) throws DataAccessException
	{
		EventEntity entity = new EventEntity(em);
		entity.setInstance(instance);
		entity.persist();
	}
	public void updateEvent(Event instance) throws DataAccessException
	{
		EventEntity entity = new EventEntity(em);
		entity.setInstance(instance);
		entity.update();
	}
	public void deleteEvent(Object id) throws DataAccessException
	{
		EventEntity entity = new EventEntity(em);
		if (entity.loadEntity(id) != null)
		{
			entity.remove();
		}
	}
	
	
	/**
	 * Retrieve all <code>BookNews</code>s from the data store.
	 * @return Collection<BookNews>
	 * @throws DataAccessException
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<BookNews> getAllBookNewses() throws DataAccessException
	{
		BookNewsQuery query = new BookNewsQuery(em);
		return query.getResultList();
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<BookNews> getLimitedBookNewses(Integer maxNumPerPage) throws DataAccessException
	{
		BookNewsQuery query = new BookNewsQuery(em);
		query.setMaxResults(maxNumPerPage);
		return query.getResultList();
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<BookNews> findAllBookNewses(Object searchKey) throws DataAccessException
	{
		return null;
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<BookNews> findLimitedBookNewses(Object searchKey, Integer maxNumPerPage) throws DataAccessException
	{
		return null;
	}
	
	/**
	 * CRUD
	 */
	@Transactional(readOnly = true)
	public BookNews getBookNews(Object id) throws DataAccessException
	{
		BookNewsEntity entity = new BookNewsEntity(em);
		return entity.loadEntity(id);
	}
	
	public void storeBookNews(BookNews instance) throws DataAccessException
	{
		BookNewsEntity entity = new BookNewsEntity(em);
		entity.setInstance(instance);
		entity.persist();
	}
	public void updateBookNews(BookNews instance) throws DataAccessException
	{
		BookNewsEntity entity = new BookNewsEntity(em);
		entity.setInstance(instance);
		entity.update();
	}
	public void deleteBookNews(Object id) throws DataAccessException
	{
		BookNewsEntity entity = new BookNewsEntity(em);
		if (entity.loadEntity(id) != null)
		{
			entity.remove();
		}
	}
	
	
	/**
	 * Retrieve all <code>Library</code>s from the data store.
	 * @return Collection<Library>
	 * @throws DataAccessException
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Library> getAllLibraries() throws DataAccessException
	{
		LibraryQuery query = new LibraryQuery(em);
		return query.getResultList();
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Library> getLimitedLibraries(Integer maxNumPerPage) throws DataAccessException
	{
		LibraryQuery query = new LibraryQuery(em);
		query.setMaxResults(maxNumPerPage);
		return query.getResultList();
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Library> findAllLibraries(Object searchKey) throws DataAccessException
	{
		return null;
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Library> findLimitedLibraries(Object searchKey, Integer maxNumPerPage) throws DataAccessException
	{
		return null;
	}
	
	/**
	 * CRUD
	 */
	@Transactional(readOnly = true)
	public Library getLibrary(Object id) throws DataAccessException
	{
		LibraryEntity entity = new LibraryEntity(em);
		return entity.loadEntity(id);
	}
	
	public void storeLibrary(Library instance) throws DataAccessException
	{
		LibraryEntity entity = new LibraryEntity(em);
		entity.setInstance(instance);
		entity.persist();
	}
	public void updateLibrary(Library instance) throws DataAccessException
	{
		LibraryEntity entity = new LibraryEntity(em);
		entity.setInstance(instance);
		entity.update();
	}
	public void deleteLibrary(Object id) throws DataAccessException
	{
		LibraryEntity entity = new LibraryEntity(em);
		if (entity.loadEntity(id) != null)
		{
			entity.remove();
		}
	}
	
	/**
	 * Retrieve all <code>InterviewCategory</code>s from the data store.
	 * @return Collection<InterviewCategory>
	 * @throws DataAccessException
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<InterviewCategory> getAllInterviewCategories() throws DataAccessException
	{
		InterviewCategoryQuery query = new InterviewCategoryQuery(em);
		return query.getResultList();
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<InterviewCategory> getLimitedInterviewCategories(Integer maxNumPerPage) throws DataAccessException
	{
		InterviewCategoryQuery query = new InterviewCategoryQuery(em);
		query.setMaxResults(maxNumPerPage);
		return query.getResultList();
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<InterviewCategory> findAllInterviewCategories(Object searchKey) throws DataAccessException
	{
		return null;
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<InterviewCategory> findLimitedInterviewCategories(Object searchKey, Integer maxNumPerPage) throws DataAccessException
	{
		return null;
	}
	
	/**
	 * CRUD
	 */
	@Transactional(readOnly = true)
	public InterviewCategory getInterviewCategory(Object id) throws DataAccessException
	{
		InterviewCategoryEntity entity = new InterviewCategoryEntity(em);
		return entity.loadEntity(id);
	}
	
	public void storeInterviewCategory(InterviewCategory instance) throws DataAccessException
	{
		InterviewCategoryEntity entity = new InterviewCategoryEntity(em);
		entity.setInstance(instance);
		entity.persist();
	}
	public void updateInterviewCategory(InterviewCategory instance) throws DataAccessException
	{
		InterviewCategoryEntity entity = new InterviewCategoryEntity(em);
		entity.setInstance(instance);
		entity.update();
	}
	public void deleteInterviewCategory(Object id) throws DataAccessException
	{
		InterviewCategoryEntity entity = new InterviewCategoryEntity(em);
		if (entity.loadEntity(id) != null)
		{
			entity.remove();
		}
	}
	
	
	/**
	 * Retrieve all <code>EventCategory</code>s from the data store.
	 * @return Collection<EventCategory>
	 * @throws DataAccessException
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<EventCategory> getAllEventCategories() throws DataAccessException
	{
		EventCategoryQuery query = new EventCategoryQuery(em);
		return query.getResultList();
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<EventCategory> getLimitedEventCategories(Integer maxNumPerPage) throws DataAccessException
	{
		EventCategoryQuery query = new EventCategoryQuery(em);
		query.setMaxResults(maxNumPerPage);
		return query.getResultList();
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<EventCategory> findAllEventCategories(Object searchKey) throws DataAccessException
	{
		return null;
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<EventCategory> findLimitedEventCategories(Object searchKey, Integer maxNumPerPage) throws DataAccessException
	{
		return null;
	}
	
	/**
	 * CRUD
	 */
	@Transactional(readOnly = true)
	public EventCategory getEventCategory(Object id) throws DataAccessException
	{
		EventCategoryEntity entity = new EventCategoryEntity(em);
		return entity.loadEntity(id);
	}
	
	public void storeEventCategory(EventCategory instance) throws DataAccessException
	{
		EventCategoryEntity entity = new EventCategoryEntity(em);
		entity.setInstance(instance);
		entity.persist();
	}
	public void updateEventCategory(EventCategory instance) throws DataAccessException
	{
		EventCategoryEntity entity = new EventCategoryEntity(em);
		entity.setInstance(instance);
		entity.update();
	}
	public void deleteEventCategory(Object id) throws DataAccessException
	{
		EventCategoryEntity entity = new EventCategoryEntity(em);
		if (entity.loadEntity(id) != null)
		{
			entity.remove();
		}
	}
	
	
	/**
	 * Retrieve all <code>BookNewsCategory</code>s from the data store.
	 * @return Collection<BookNewsCategory>
	 * @throws DataAccessException
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<BookNewsCategory> getAllBookNewsCategories() throws DataAccessException
	{
		BookNewsCategoryQuery query = new BookNewsCategoryQuery(em);
		return query.getResultList();
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<BookNewsCategory> getLimitedBookNewsCategories(Integer maxNumPerPage) throws DataAccessException
	{
		BookNewsCategoryQuery query = new BookNewsCategoryQuery(em);
		query.setMaxResults(maxNumPerPage);
		return query.getResultList();
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<BookNewsCategory> findAllBookNewsCategories(Object searchKey) throws DataAccessException
	{
		return null;
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<BookNewsCategory> findLimitedBookNewsCategories(Object searchKey, Integer maxNumPerPage) throws DataAccessException
	{
		return null;
	}
	
	/**
	 * CRUD
	 */
	@Transactional(readOnly = true)
	public BookNewsCategory getBookNewsCategory(Object id) throws DataAccessException
	{
		BookNewsCategoryEntity entity = new BookNewsCategoryEntity(em);
		return entity.loadEntity(id);
	}
	
	public void storeBookNewsCategory(BookNewsCategory instance) throws DataAccessException
	{
		BookNewsCategoryEntity entity = new BookNewsCategoryEntity(em);
		entity.setInstance(instance);
		entity.persist();
	}
	public void updateBookNewsCategory(BookNewsCategory instance) throws DataAccessException
	{
		BookNewsCategoryEntity entity = new BookNewsCategoryEntity(em);
		entity.setInstance(instance);
		entity.update();
	}
	public void deleteBookNewsCategory(Object id) throws DataAccessException
	{
		BookNewsCategoryEntity entity = new BookNewsCategoryEntity(em);
		if (entity.loadEntity(id) != null)
		{
			entity.remove();
		}
	}
	
	
	
	/**
	 * Retrieve all <code>BookReviewCategory</code>s from the data store.
	 * @return Collection<BookReviewCategory>
	 * @throws DataAccessException
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<BookReviewCategory> getAllBookReviewCategories() throws DataAccessException
	{
		BookReviewCategoryQuery query = new BookReviewCategoryQuery(em);
		return query.getResultList();
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<BookReviewCategory> getLimitedBookReviewCategories(Integer maxNumPerPage) throws DataAccessException
	{
		BookReviewCategoryQuery query = new BookReviewCategoryQuery(em);
		query.setMaxResults(maxNumPerPage);
		return query.getResultList();
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<BookReviewCategory> findAllBookReviewCategories(Object searchKey) throws DataAccessException
	{
		return null;
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<BookReviewCategory> findLimitedBookReviewCategories(Object searchKey, Integer maxNumPerPage) throws DataAccessException
	{
		return null;
	}
	
	/**
	 * CRUD
	 */
	@Transactional(readOnly = true)
	public BookReviewCategory getBookReviewCategory(Object id) throws DataAccessException
	{
		BookReviewCategoryEntity entity = new BookReviewCategoryEntity(em);
		return entity.loadEntity(id);
	}
	
	public void storeBookReviewCategory(BookReviewCategory instance) throws DataAccessException
	{
		BookReviewCategoryEntity entity = new BookReviewCategoryEntity(em);
		entity.setInstance(instance);
		entity.persist();
	}
	public void updateBookReviewCategory(BookReviewCategory instance) throws DataAccessException
	{
		BookReviewCategoryEntity entity = new BookReviewCategoryEntity(em);
		entity.setInstance(instance);
		entity.update();
	}
	public void deleteBookReviewCategory(Object id) throws DataAccessException
	{
		BookReviewCategoryEntity entity = new BookReviewCategoryEntity(em);
		if (entity.loadEntity(id) != null)
		{
			entity.remove();
		}
	}
	
	/**
	 * Retrieve all <code>Category</code>s from the data store.
	 * @return Collection<Category>
	 * @throws DataAccessException
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Category> getAllCategories() throws DataAccessException
	{
		CategoryQuery query = new CategoryQuery(em);
		return query.getResultList();
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Category> getLimitedCategories(Integer maxNumPerPage) throws DataAccessException
	{
		CategoryQuery query = new CategoryQuery(em);
		query.setMaxResults(maxNumPerPage);
		return query.getResultList();
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Category> findAllCategories(Object searchKey) throws DataAccessException
	{
		return null;
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Category> findLimitedCategories(Object searchKey, Integer maxNumPerPage) throws DataAccessException
	{
		return null;
	}
	
	/**
	 * CRUD
	 */
	@Transactional(readOnly = true)
	public Category getCategory(Object id) throws DataAccessException
	{
		CategoryEntity entity = new CategoryEntity(em);
		return entity.loadEntity(id);
	}
	
	public void storeCategory(Category instance) throws DataAccessException
	{
		CategoryEntity entity = new CategoryEntity(em);
		entity.setInstance(instance);
		entity.persist();
	}
	public void updateCategory(Category instance) throws DataAccessException
	{
		CategoryEntity entity = new CategoryEntity(em);
		entity.setInstance(instance);
		entity.update();
	}
	public void deleteCategory(Object id) throws DataAccessException
	{
		CategoryEntity entity = new CategoryEntity(em);
		if (entity.loadEntity(id) != null)
		{
			entity.remove();
		}
	}

	public Category getCategoryByName(String name) throws DataAccessException {
		CategoryQuery query = new CategoryQuery(em);
		return query.searchCategoryByName(name);
	}

	public Library getLibraryByName(String name) throws DataAccessException {
		LibraryQuery query = new LibraryQuery(em);
		return query.searchLibraryByName(name);
	}

	public BookNewsCategory getBookNewsCategoryByName(String name)
			throws DataAccessException {
		BookNewsCategoryQuery query = new BookNewsCategoryQuery(em);
		return query.searchBookNewsCategoryByName(name);
	}

	public BookReviewCategory getBookReviewCategoryByName(String name)
			throws DataAccessException {
		BookReviewCategoryQuery query = new BookReviewCategoryQuery(em);
		return query.searchBookReviewCategoryByName(name);
	}

	public EventCategory getEventCategoryByName(String name)
			throws DataAccessException {
		EventCategoryQuery query = new EventCategoryQuery(em);
		return query.searchEventCategoryByName(name);
	}

	public InterviewCategory getInterviewCategoryByName(String name)
			throws DataAccessException {
		InterviewCategoryQuery query = new InterviewCategoryQuery(em);
		return query.searchInterviewCategoryByName(name);
	}

	public BookNews getBookNewsByTitle(String tilte) throws DataAccessException {
		BookNewsQuery query = new BookNewsQuery(em);
		return query.searchBookNewsByTitle(tilte);
	}

	public BookReview getBookReviewByTitle(String title)
			throws DataAccessException {
		BookReviewQuery query = new BookReviewQuery(em);
		return query.searchBookReviewByTitle(title);
	}

	public Event getEventByTitle(String title) throws DataAccessException {
		// TODO Auto-generated method stub
		EventQuery query = new EventQuery(em);
		return query.searchEventByTitle(title);
	}

	public Interview getInterviewByTitle(String title) throws DataAccessException {
		InterviewQuery query = new InterviewQuery(em);
		return query.searchInterviewByTitle(title);
	}
	
	

	public void deleteBookTag(Object id) throws DataAccessException {
		BookTagEntity entity = new BookTagEntity(em);
		if (entity.loadEntity(id) != null)
		{
			entity.remove();
		}
		
	}

	public Collection<BookTag> findAllBookTags(Object searchKey)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<BookTag> findLimitedBookTags(Object searchKey,
			Integer maxNumPerPage) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<BookTag> getAllBookTags() throws DataAccessException {
		BookTagQuery query = new BookTagQuery(em);
		return query.getResultList();
	}

	@Transactional(readOnly = true)
	public BookTag getBookTag(Object id) throws DataAccessException {
		// TODO Auto-generated method stub
		BookTagEntity entity = new BookTagEntity(em);
		return entity.loadEntity(id);
	}

	public BookTag getBookTagByName(String name) throws DataAccessException {
		// TODO Auto-generated method stub
		BookTagQuery query = new BookTagQuery(em);
		return query.searchBookTagByName(name);
	}

	public Collection<BookTag> getLimitedBookTags(Integer maxNumPerPage)
			throws DataAccessException {
		BookTagQuery query = new BookTagQuery(em);
		query.setMaxResults(maxNumPerPage);
		return query.getResultList();
	}

	public void storeBookTag(BookTag instance) throws DataAccessException {
		BookTagEntity entity = new BookTagEntity(em);
		entity.setInstance(instance);
		entity.persist();
		
	}

	public void updateBookTag(BookTag instance) throws DataAccessException {
		BookTagEntity entity = new BookTagEntity(em);
		entity.setInstance(instance);
		entity.update();
		
	}

	public Book getBookByYdkId(String ydkId) throws DataAccessException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		BookQuery query = new BookQuery(em);
		return query.searchBookByYdkId(ydkId);
	}
	
	
	
	
	
	
}
