
package com.ydk.test.db;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.annotation.ExpectedException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.jpa.AbstractJpaTests;

import com.ydk.account.persistence.entity.*;
import com.ydk.account.persistence.interfaces.AccountDbMngr;
import com.ydk.account.persistence.operator.TransactionQuery;
import com.ydk.account.webservice.WebServiceAccount;
import com.ydk.account.webservice.YdkAccountService;
import com.ydk.book.persistence.entity.Author;
import com.ydk.book.persistence.entity.Book;
import com.ydk.book.persistence.entity.BookTag;
import com.ydk.book.persistence.entity.Event;
import com.ydk.book.persistence.entity.EventCategory;
import com.ydk.book.persistence.entity.Interview;
import com.ydk.book.persistence.entity.InterviewCategory;
import com.ydk.book.persistence.interfaces.BookDbMngr;
import com.ydk.book.persistence.operator.AuthorEntity;
import com.ydk.book.persistence.operator.BookEntity;
import com.ydk.book.persistence.operator.EventEntity;
import com.ydk.book.persistence.operator.InterviewEntity;
import com.ydk.book.webservice.YdkBookService;

/**
 * <p>
 * This class extends {@link AbstractJpaTests}, one of the valuable test
 * superclasses provided in the <code>org.springframework.test</code> package.
 * This represents best practice for integration tests with Spring for JPA based
 * tests which require <em>shadow class loading</em>. For all other types of
 * integration testing, the <em>Spring TestContext Framework</em> is
 * preferred.
 * </p>
 * <p>
 * AbstractJpaTests and its superclasses provide the following services:
 * <ul>
 * <li>Injects test dependencies, meaning that we don't need to perform
 * application context lookups. See the setClinic() method. Injection uses
 * autowiring by type.</li>
 * <li>Executes each test method in its own transaction, which is automatically
 * rolled back by default. This means that even if tests insert or otherwise
 * change database state, there is no need for a teardown or cleanup script.</li>
 * <li>Provides useful inherited protected fields, such as a
 * {@link SimpleJdbcTemplate} that can be used to verify database state after
 * test operations, or verify the results of queries performed by application
 * code. Alternatively, you can use protected convenience methods such as
 * {@link #countRowsInTable(String)}, {@link #deleteFromTables(String[])},
 * etc. An ApplicationContext is also inherited, and can be used for explicit
 * lookup if necessary.</li>
 * </ul>
 * <p>
 * {@link AbstractJpaTests} and related classes are shipped in
 * <code>spring-test.jar</code>.
 * </p>
 *
 * @author Rod Johnson
 * @author Sam Brannen
 * @see AbstractJpaTests
 */
public abstract class AbstractJpaYdkTests extends AbstractJpaTests {

//	@Autowired
	protected AccountDbMngr accountDbMngr;
	protected BookDbMngr bookDbMngr;
//	@Autowired
	@Qualifier("ydkBookServiceClient")
    protected YdkBookService ydkBookServiceClient;
	
//	@Autowired
	@Qualifier("ydkAccountServiceClient")
    protected YdkAccountService ydkAccountServiceClient;
	
	public void setYdkAccountServiceClient(@Qualifier("ydkAccountServiceClient")YdkAccountService ydkAccountServiceClient) {
        this.ydkAccountServiceClient = ydkAccountServiceClient;
    }
	
	public void setYdkBookServiceClient(@Qualifier("ydkBookServiceClient")YdkBookService ydkBookServiceClient) {
        this.ydkBookServiceClient = ydkBookServiceClient;
    }


	/**
	 * This method is provided to set the Clinic instance being tested by the
	 * Dependency Injection injection behaviour of the superclass from the
	 * <code>org.springframework.test</code> package.
	 *
	 * @param clinic clinic to test
	 */
	public void setAccountDbMngr(AccountDbMngr accountDbMngr) {
		this.accountDbMngr = accountDbMngr;
	}
	
	public void setBookDbMngr(BookDbMngr bookDbMngr) {
		this.bookDbMngr = bookDbMngr;
	}

	@ExpectedException(IllegalArgumentException.class)
	public void testBogusJpql() {
		this.sharedEntityManager.createQuery("SELECT RUBBISH FROM RUBBISH HEAP").executeUpdate();
	}

	public void testApplicationManaged() {
		EntityManager appManaged = this.entityManagerFactory.createEntityManager();
		appManaged.joinTransaction();
	}
	
	@Rollback(false)
	public void testBookTag() {
		List<Author> list = (List<Author>)accountDbMngr.getAllAuthors();
		List<Book> books = list.get(0).getMeFirstBooks();
		books.get(0).getTitle();
		Book book = new Book();
		book.setTitle("book1");
		bookDbMngr.saveBook(book);
		assertNotNull(book.getId());
		
		BookTag bookTag = new BookTag();
		bookTag.setName("booktag1");
		bookDbMngr.storeBookTag(bookTag);
		
		
		book.getBookTags().add(bookTag);
		
		bookDbMngr.saveBook(book);
		
		
		
	}

	@Rollback(false)
	public void testBookTag2() {
		BookTag result = bookDbMngr.getBookTagByName("booktag1");
		int size = result.getBooks().size();
		List books = result.getBooks();
		((Book)books.get(0)).getTitle();
		assertEquals("book1", ((Book)books.get(0)).getTitle());
	}
	@Rollback(false)
	public void testPartner() {
		//CRUD PartnerType;
		PartnerType instance = new PartnerType("1");
		accountDbMngr.storePartnerType(instance);
		instance = accountDbMngr.getPartnerType(instance.getId());
		assertNotNull(instance);
		assertEquals("1", instance.getType());
		
		instance = new PartnerType("2");
		accountDbMngr.storePartnerType(instance);
		instance = new PartnerType("3");
		accountDbMngr.storePartnerType(instance);
		instance = new PartnerType("4");
		accountDbMngr.storePartnerType(instance);
		accountDbMngr.deletePartnerType("1");
		instance = accountDbMngr.getPartnerType("1");
		assertNull(instance);
		assertEquals(3, accountDbMngr.getAllPartnerTypes().size());
		
		
		instance = accountDbMngr.getPartnerType("2");
		Key key = new Key("public_1", "private_1", new Date(), false,"1");
		Partner partner = new Partner(instance, "_partner_1", "_partner_1@ydk.com");
		partner.setCurrentKeyPair(key);
		key = new Key("public_2", "private_2", new Date(), false, "2");
		partner.setLastKeyPair(key);
		accountDbMngr.storePartner(partner);
		
		assertNotNull(partner.getId());
		
		partner = new Partner(instance, "_partner_2", "_partner_2@ydk.com");
		accountDbMngr.storePartner(partner);
		assertNotNull(partner.getId());
		
		Partner result = accountDbMngr.getPartner(partner.getId());
		assertNotNull(partner.getId());
		
		instance = null;
		PartnerType newResult = accountDbMngr.getPartnerType("2");
		//This will fail, because the transaction has not been submitted.
//		assertEquals(2, newResult.getPartners().size());
	}

	public void testPartner2() {
		
		
		Partner result = accountDbMngr.getPartner((long)1);
		assertNotNull(result);
		assertNotNull(result.getCurrentKeyPair());
		assertNotNull(result.getLastKeyPair());
		
		result = accountDbMngr.getPartner((long)2);
		assertNotNull(result);
		
		PartnerType instance = accountDbMngr.getPartnerType("2");
		assertEquals(2, instance.getPartners().size());
	}
	
	@Rollback(false)
	public void testReader() {
		//CRUD Address;
		Address instance = new Address("China", "Beijing", null, null, "1", null, "132",null);
		accountDbMngr.storeAddress(instance);
		instance = accountDbMngr.getAddress(instance.getId());
		assertNotNull(instance);
		assertNotNull(instance.getId());
		Long addressId = instance.getId();
		assertEquals("China", instance.getCountry());
		assertEquals("Beijing", instance.getState());
		
		instance = new Address("China", "Shanghai", null, null, "1", null, "132",null);
		accountDbMngr.storeAddress(instance);
		instance = new Address("China", "Guangzhou", null, null, "1", null, "132",null);
		accountDbMngr.storeAddress(instance);
		instance = new Address("China", "Xiamen", null, null, "1", null, "132",null);
		accountDbMngr.storeAddress(instance);
		accountDbMngr.deleteAddress(instance.getId());
		instance = accountDbMngr.getAddress(instance.getId());
		assertNull(instance);
		assertEquals(3, accountDbMngr.getAllAddresses().size());
		
		
		instance = accountDbMngr.getAddress(addressId);
		Key key = new Key("public_1", "private_1", new Date(), false, "1");
		Partner partner = accountDbMngr.getPartner((long)1);
		Reader reader = new Reader(partner, "_reader_1", "_reader_1@ydk.com");
//		reader.setAddress("instance);
		reader.setCurrentKeyPair(key);
		key = new Key("public_2", "private_2", new Date(), false, "1");
		reader.setLastKeyPair(key);
		Book book = new Book();
		book.setTitle("book1");
		accountDbMngr.saveBook(book);
		assertNotNull(book.getId());
		BookShelf bookShelf = new BookShelf(reader, book);
		reader.getBookShelves().add(bookShelf);
		accountDbMngr.storeReader(reader);
		
		assertNotNull(reader.getId());
		
		reader = new Reader(partner, "_reader_2", "_reader_2@ydk.com");
//		reader.setAddress(instance);
		accountDbMngr.storeReader(reader);
		assertNotNull(partner.getId());
		
		Reader result = accountDbMngr.getReader(reader.getId());
		assertNotNull(result);
		
		instance = null;
		
		//This will fail, because the transaction has not been submitted.
		assertEquals(2, accountDbMngr.getPartner(partner.getId()).getReaders().size());
		
//		Address addr = accountDbMngr.getAddress(addressId);
//		int i = addr.getReaders().size();
//		//This will fail, because the transaction has not been submitted.
//		assertEquals(2, addr.getReaders().size());
	}
	
	@Rollback(false)
	public void testReader2() {
		
		Partner result = accountDbMngr.getPartner((long)1);
		
		assertEquals(2, result.getReaders().size());
		
		assertNotNull(result.getReaders().get(0).getCurrentKeyPair());
		assertNull(result.getReaders().get(1).getCurrentKeyPair());
		assertNotNull(result.getReaders().get(0).getLastKeyPair());
		assertNull(result.getReaders().get(1).getLastKeyPair());
		
		BookShelf bookShelf = result.getReaders().get(0).getBookShelves().get(0);
		
		Address address = accountDbMngr.getAddress((long)1);
		assertEquals(2, address.getReaders().size());
		
		
	}
	
	
	@Rollback(false)
	public void testTransaction() {
		//CRUD Address;
		TransactionType instance = new TransactionType("1");
		accountDbMngr.storeTransactionType(instance);
		instance = accountDbMngr.getTransactionType(instance.getId());
		assertNotNull(instance);
		assertNotNull(instance.getId());
		Long instanceId = instance.getId();
		assertEquals("1", instance.getType());
		
		instance = new TransactionType("2");
		accountDbMngr.storeTransactionType(instance);
		instance = new TransactionType("3");
		accountDbMngr.storeTransactionType(instance);
		instance = new TransactionType("4");
		accountDbMngr.storeTransactionType(instance);
		accountDbMngr.deleteTransactionType(instance.getId());
		instance = accountDbMngr.getTransactionType(instance.getId());
		assertNull(instance);
		assertEquals(3, accountDbMngr.getAllTransactionTypes().size());
		
		instance = accountDbMngr.getTransactionType(instanceId);
		
		Book book = new Book();
		book.setTitle("book2");
		accountDbMngr.saveBook(book);
		assertNotNull(book.getId());
		
		Reader reader = accountDbMngr.getReader((long)1);
		
		Transaction tx = new Transaction(instance, book, reader, reader.getPartner(),new Date());
		accountDbMngr.storeTransaction(tx);
		
		assertNotNull(tx.getId());
		
		tx = new Transaction(instance, book, reader, reader.getPartner(), new Date());
		accountDbMngr.storeTransaction(tx);
		assertNotNull(tx.getId());
		
		
		Transaction result = accountDbMngr.getTransaction(tx.getId());
		assertNotNull(result);
		
		instance = null;
		//This will fail, because the transaction has not been submitted.
//		assertEquals(2, accountDbMngr.getTransactionType(instanceId).getTransactions().size());
		//This will fail, because the transaction has not been submitted.
		assertEquals(2, accountDbMngr.getReader(reader.getId()).getTransactions().size());
		//This will fail, because the transaction has not been submitted.
//		assertEquals(2, accountDbMngr.getBooks().getTransactions().size());
		
	}
	
	@Rollback(false)
	public void testTransaction2() {
		
		
		Reader reader = accountDbMngr.getReader((long)1);
		
		assertEquals(2, accountDbMngr.getReader(reader.getId()).getTransactions().size());
		Long id = reader.getTransactions().get(0).getId();
		Long bookid = reader.getTransactions().get(0).getBook().getId();
//		assertEquals(1, reader.getTransactions().get(0).getBook().getBookShelves().size());
		accountDbMngr.deleteTransaction(id);
		TransactionType txType = accountDbMngr.getTransactionType((long)1);
		
		assertEquals(1, accountDbMngr.getTransactionType(txType.getId()).getTransactions().size());
		
		Transaction tx1 = accountDbMngr.getTransaction((long)2);
		assertNotNull(tx1.getBook().getId());

		
		Book book = tx1.getBook();
		Long bookId = book.getId();
		
		List<BookShelf> bookShelves = book.getBookShelves();
		int size1 = bookShelves.size();
		assertEquals(0, size1);
		
		assertEquals(1, reader.getBookShelves().size());
		BookShelf bs = reader.getBookShelves().get(0);
		assertEquals("book1",bs.getBook().getTitle());
		
		assertNotNull(tx1.getReader().getId());
		assertNotNull(tx1.getTransactionType().getId());
		
//		accountDbMngr.deleteReader(reader.getId());
//		
//		txType = accountDbMngr.getTransactionType((long)1);
//		
//		int size =  txType.getTransactions().size();
//		assertEquals(0,size);
//		txType = txType;
		
	}
	
	

	@Rollback(false)
	public void testBookShelf() {
		Transaction tx1 = accountDbMngr.getTransaction((long)2);
		assertNotNull(tx1.getBook().getId());
		Book book = tx1.getBook();
		Long bookId = book.getId();
		List<BookShelf> bookShelves = book.getBookShelves();
		int size1 = bookShelves.size();
		assertEquals(0, size1);
		assertNotNull(tx1.getReader().getId());
		assertNotNull(tx1.getTransactionType().getId());
	}

	
	@Rollback(false)
	public void testBookAuthor() {
		
		Book book = new Book();
		book.setTitle("BookAuthorTest");
		Author author = new Author("AuthorFirst");
		book.setAuthor(author);
		Author author1 = new Author("AuthorFirst1");
		book.setAuthor1(author1);
		accountDbMngr.saveBook(book);
		
		assertNotNull(author.getId());
		assertEquals("AuthorFirst", book.getAuthor().getName());
	}

	@Rollback(false)
	public void testBookAuthor1() {
		AuthorEntity entity = new AuthorEntity(accountDbMngr.getEntityManager());
		
		Author author = entity.loadEntity((long)1);
		assertEquals("AuthorFirst", author.getName());
		assertEquals(1, author.getMeFirstBooks().size());
		assertEquals("BookAuthorTest", author.getMeFirstBooks().get(0).getTitle());
		
		AuthorEntity entity1 = new AuthorEntity(accountDbMngr.getEntityManager());
		Author author1 = entity1.loadEntity((long)2);
		assertEquals("AuthorFirst1", author1.getName());
		assertEquals(0, author1.getMeFirstBooks().size());
		assertEquals(1, author1.getMeSecondBooks().size());
		assertEquals("BookAuthorTest", author1.getMeSecondBooks().get(0).getTitle());
		
		
		BookEntity be = new BookEntity(accountDbMngr.getEntityManager());
		Book book = be.loadBook((long)3);
		assertEquals("AuthorFirst", book.getAuthor().getName());
		assertEquals("AuthorFirst1", book.getAuthor1().getName());
		
//		Owner o1 = this.clinic.loadOwner(1);
//		assertTrue(o1.getLastName().startsWith("Franklin"));
//		Owner o10 = this.clinic.loadOwner(10);
//		assertEquals("Carlos", o10.getFirstName());
//
//		// Check lazy loading, by ending the transaction
//		endTransaction();
//
//		// Now Owners are "disconnected" from the data store.
//		// We might need to touch this collection if we switched to lazy loading
//		// in mapping files, but this test would pick this up.
//		o1.getPets();
	}
	@Rollback(false)
	public void testInterviewSubject() {
		
		InterviewCategory ic = new InterviewCategory("interviewCategory", "interviewCategory");
		Interview interview = new Interview("interview1", "1", new Date());
		
		interview.setInterviewCategory(ic);
		
		Book book = new Book();
		book.setTitle("testInterview");
//		accountDbMngr.saveBook(book);
//		Long id = book.getId();
		
		interview.setBook(book);
//		FileDescriptor titleImg = new FileDescriptor("titleImg", "jpeg", 
//				(long)2, "test", (long)1);
//		interview.setTitleImage(titleImg);
		
//		FileDescriptor contentImg = new FileDescriptor("contentImg", "jpeg", 
//				(long)2, "test", (long)2);
//		interview.getImages().add(contentImg);
		
		
		
		InterviewEntity ie = new InterviewEntity(accountDbMngr.getEntityManager());
		ie.setInstance(interview);
		ie.persist();
		
		EventCategory ec = 
			new EventCategory("eventCategory", "eventCategory");
		Event event = new Event("event1", "bj", new Date());
		event.setEventCategory(ec);
		
		event.setBook(book);
//		titleImg = new FileDescriptor("titleImg1", "jpeg", 
//				(long)2, "test", (long)1);
//		event.setTitleImage(titleImg);
//		contentImg = new FileDescriptor("contentImg2", "jpeg", 
//				(long)2, "test", (long)2);
//		event.getImages().add(contentImg);
		
		EventEntity ee = new EventEntity(accountDbMngr.getEntityManager());
		ee.setInstance(event);
		ee.persist();
		
		
//		BookEntity be = new BookEntity(accountDbMngr.getEntityManager());
//		be.loadBook(id);
//		assertEquals(1,be.getInstance().getInterviews().size());
//		assertEquals("interview1",be.getInstance().getInterviews().get(0).getTitle());
		
		
		
		
		
//		owner.setLastName("Schultz");
//		this.clinic.storeOwner(owner);
//		// assertTrue(!owner.isNew()); -- NOT TRUE FOR TOPLINK (before commit)
//		owners = this.clinic.findOwners("Schultz");
//		assertEquals(found + 1, owners.size());
	}
	
	@Rollback(false)
	public void testDeleteInterview() throws Exception {
		
		assertEquals(1,bookDbMngr.getAllInterviewCategories().size());
		List<InterviewCategory> categories = (List<InterviewCategory>) bookDbMngr.getAllInterviewCategories();
		assertEquals(1, categories.get(0).getInterviews().size());
		
		BookEntity be = new BookEntity(accountDbMngr.getEntityManager());
		be.loadBook((long)4);
		assertEquals(1,be.getInstance().getInterviews().size());
		assertEquals("interview1",be.getInstance().getInterviews().get(0).getTitle());
		
		InterviewEntity ie = new InterviewEntity(accountDbMngr.getEntityManager());
		ie.loadEntity(be.getInstance().getInterviews().get(0).getId());
		ie.remove();
//		Owner o1 = this.clinic.loadOwner(1);
//		String old = o1.getLastName();
//		o1.setLastName(old + "X");
//		this.clinic.storeOwner(o1);
//		o1 = this.clinic.loadOwner(1);
//		assertEquals(old + "X", o1.getLastName());
	}

	public void testTransactionQuery() {
		TransactionQuery tq = new TransactionQuery(accountDbMngr.getEntityManager());
		Date startDate = new Date();
		startDate.setYear(109);

		Date endDate = new Date();
		endDate.setYear(111);
		Partner partner = new Partner();
		partner.setId((long)1);
		List<Transaction> ts = tq.searchByFieldWithDate("partner", partner, startDate, endDate);
		assertEquals(1, ts.size());
		ts = tq.searchByFieldWithDate("partner", partner, null, endDate);
		assertEquals(1, ts.size());
		
		ts = tq.searchByFieldWithDate("partner", partner, startDate, null);
		assertEquals(1, ts.size());
		
		ts = tq.searchByFieldWithDate("partner", partner, null, null);
		assertEquals(1, ts.size());
		Reader reader = new Reader();
		reader.setId((long)1);
		ts = tq.searchByFieldWithDate("reader", reader, null, null);
		assertEquals(1, ts.size());
		
		Book book = new Book();
		book.setId((long)2);
		ts = tq.searchByFieldWithDate("book", book, null, null);
		assertEquals(1, ts.size());
		
		
		
//		tq.searchByFieldWithDate(name, value, startDate, endDate)
//		Collection<PetType> types = this.clinic.getPetTypes();
//		Pet p7 = this.clinic.loadPet(7);
//		assertTrue(p7.getName().startsWith("Samantha"));
//		assertEquals(EntityUtils.getById(types, PetType.class, 1).getId(), p7.getType().getId());
//		assertEquals("Jean", p7.getOwner().getFirstName());
//		Pet p6 = this.clinic.loadPet(6);
//		assertEquals("George", p6.getName());
//		assertEquals(EntityUtils.getById(types, PetType.class, 4).getId(), p6.getType().getId());
//		assertEquals("Peter", p6.getOwner().getFirstName());
	}

	public void testInsertPet() {
//		Owner o6 = this.clinic.loadOwner(6);
//		int found = o6.getPets().size();
//		Pet pet = new Pet();
//		pet.setName("bowser");
//		Collection<PetType> types = this.clinic.getPetTypes();
//		pet.setType(EntityUtils.getById(types, PetType.class, 2));
//		pet.setBirthDate(new Date());
//		o6.addPet(pet);
//		assertEquals(found + 1, o6.getPets().size());
//		this.clinic.storeOwner(o6);
//		// assertTrue(!pet.isNew()); -- NOT TRUE FOR TOPLINK (before commit)
//		o6 = this.clinic.loadOwner(6);
//		assertEquals(found + 1, o6.getPets().size());
	}

	public void testUpdatePet() throws Exception {
//		Pet p7 = this.clinic.loadPet(7);
//		String old = p7.getName();
//		p7.setName(old + "X");
//		this.clinic.storePet(p7);
//		p7 = this.clinic.loadPet(7);
//		assertEquals(old + "X", p7.getName());
	}

	public void testInsertVisit() {
//		Pet p7 = this.clinic.loadPet(7);
//		int found = p7.getVisits().size();
//		Visit visit = new Visit();
//		p7.addVisit(visit);
//		visit.setDescription("test");
//		this.clinic.storePet(p7);
//		// assertTrue(!visit.isNew()); -- NOT TRUE FOR TOPLINK (before commit)
//		p7 = this.clinic.loadPet(7);
//		assertEquals(found + 1, p7.getVisits().size());
	}

}
