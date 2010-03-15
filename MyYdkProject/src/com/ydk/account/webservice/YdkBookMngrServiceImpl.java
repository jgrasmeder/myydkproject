/**
 * 
 */
package com.ydk.account.webservice;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.jws.WebService;
import javax.mail.util.ByteArrayDataSource;

import org.apache.log4j.Logger;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.store.LockObtainFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ydk.account.core.WebServiceAccountHelper;
import com.ydk.account.persistence.entity.Account;
import com.ydk.account.persistence.entity.Category;
import com.ydk.account.persistence.entity.FileDescriptor;
import com.ydk.account.persistence.entity.Library;
import com.ydk.account.persistence.entity.Partner;
import com.ydk.account.persistence.entity.Transaction;
import com.ydk.account.persistence.entity.UploadedFile;
import com.ydk.account.persistence.interfaces.AccountDbMngr;
import com.ydk.account.persistence.operator.TransactionQuery;
import com.ydk.account.validator.UploadedFileValidator;
import com.ydk.book.persistence.entity.Book;
import com.ydk.book.persistence.entity.Event;
import com.ydk.book.persistence.interfaces.BookDbMngr;
import com.ydk.book.webservice.OperationStatus;
import com.ydk.book.webservice.WebBookCateListResult;
import com.ydk.book.webservice.WebBookListResult;
import com.ydk.book.webservice.WebBookMarkListResult;
import com.ydk.book.webservice.WebEventListResult;
import com.ydk.book.webservice.WebLibraryListResult;
import com.ydk.book.webservice.WebServiceBook;
import com.ydk.book.webservice.WebServiceCategory;
import com.ydk.book.webservice.WebServiceLibrary;
import com.ydk.book.webservice.WebServiceListParams;
import com.ydk.epub.BookEPubClient;
import com.ydk.epub.EpubBook;
import com.ydk.search.SimpleSearchManager;
import com.ydk.web.FileManager;
/**
 * @author y140zhan
 *
 */
@WebService(endpointInterface = "com.ydk.account.webservice.YdkBookMngrService")
@Transactional
public final class YdkBookMngrServiceImpl implements YdkBookMngrService {
	
	private final Logger logger = Logger.getLogger(YdkSubjectMngrServiceImpl.class.getName());
	protected final AccountDbMngr accountDbMngr;
	protected final BookDbMngr bookDbMngr;
	
	@Autowired
	public YdkBookMngrServiceImpl(AccountDbMngr accountDbMngr, 
			BookDbMngr bookDbMngr) {
		this.accountDbMngr = accountDbMngr;
		this.bookDbMngr = bookDbMngr;
	}
	
	
	public OperationStatus addBookToCategory(Long bookId, Long cateId) {
		OperationStatus os = new OperationStatus();
		if (bookId == null || cateId == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input Ids");
			return os;
		}
		
		Book book = bookDbMngr.getBook(bookId);
		if (book == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Book not found id = " + bookId);
			return os;
		}
		
		Category cate = bookDbMngr.getCategory(cateId);
		if (cate == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Book category not found id = " + cateId);
			return os;
		}
		
		book.setCategory(cate);
		bookDbMngr.saveBook(book);
		
		return os;
	}
	
	public OperationStatus addCategoryToLibrary(Long cateId, Long libId) {
		OperationStatus os = new OperationStatus();
		if (libId == null || cateId == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input Ids");
			return os;
		}
		
		Category cate = bookDbMngr.getCategory(cateId);
		if (cate == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Book category not found id = " + cateId);
			return os;
		}
		
		Library lib = bookDbMngr.getLibrary(libId);
		if (lib == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Library not found id = " + libId);
			return os;
		}
		
		cate.setLibrary(lib);
		bookDbMngr.updateCategory(cate);
		
		return os;
	}

	public OperationStatus deleteBook(Long id) {
		OperationStatus os = new OperationStatus();
		if (id == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input Id");
			return os;
		}
		
		bookDbMngr.deleteBook(id);
		return os;
	}
	

	public OperationStatus deleteCategory(Long id) {
		OperationStatus os = new OperationStatus();
		if (id == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input Id");
			return os;
		}
		
		bookDbMngr.deleteCategory(id);
		return os;
	}

	public OperationStatus deleteLibrary(Long id) {
		OperationStatus os = new OperationStatus();
		if (id == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input Id");
			return os;
		}
		
		bookDbMngr.deleteLibrary(id);
		return os;
	}




	public WebServiceBook getBookById(Long id) {
		WebServiceBook wb = new WebServiceBook();
		if (id == null)
		{
			wb.getOs().setIsSuccessful(false);
			wb.getOs().setFailureReason("Null input Id");
			return wb;
		}
		Book instance = bookDbMngr.getBook(id);
		if (instance == null)
		{
			wb.getOs().setIsSuccessful(false);
			wb.getOs().setFailureReason("Book not found by id: " + id);
			return wb;
		}
		
		//Eager get @ManytoOne, Null oneToMany;
		
		
		wb.setBook(instance.setToPlainObject(instance));
		return wb;
	}
	
	public WebServiceCategory getCategoryById(Long id) {
		WebServiceCategory wc = new WebServiceCategory();
		if (id == null)
		{
			wc.getOs().setIsSuccessful(false);
			wc.getOs().setFailureReason("Null input Id");
			return wc;
		}
		Category instance = bookDbMngr.getCategory(id);
		if (instance == null)
		{
			wc.getOs().setIsSuccessful(false);
			wc.getOs().setFailureReason("Category not found by id: " + id);
			return wc;
		}
		
		//Eager get @ManytoOne, Null oneToMany;
		
		wc.setCategory(instance.setToPlainObject(instance));
		return wc;
	}
	



	public WebServiceLibrary getLibraryById(Long id) {
		WebServiceLibrary wl = new WebServiceLibrary();
		if (id == null)
		{
			wl.getOs().setIsSuccessful(false);
			wl.getOs().setFailureReason("Null input Id");
			return wl;
		}
		Library instance = bookDbMngr.getLibrary(id);
		if (instance == null)
		{
			wl.getOs().setIsSuccessful(false);
			wl.getOs().setFailureReason("Library not found by id: " + id);
			return wl;
		}
		
		//Eager get @ManytoOne, Null oneToMany;
		
		wl.setLibrary(instance.setToPlainObject(instance));
		return wl;
	}
	
	
	public OperationStatus remBookFromCategory(Long bookId, Long cateId) {
		OperationStatus os = new OperationStatus();
		if (bookId == null || cateId == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input Ids");
			return os;
		}
		
		Book book = bookDbMngr.getBook(bookId);
		if (book == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Book not found id = " + bookId);
			return os;
		}
		book.setCategory(null);
		
//		Category cate = bookDbMngr.getCategory(cateId);
//		if (cate == null)
//		{
//			os.setIsSuccessful(false);
//			os.setFailureReason("Book category not found id = " + cateId);
//			return os;
//		}
		
		bookDbMngr.saveBook(book);
		
		return os;
	}

	public OperationStatus remCategoryFromLibrary(Long cateId, Long libId) {
		OperationStatus os = new OperationStatus();
		if (libId == null || cateId == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input Ids");
			return os;
		}
		
		Category cate = bookDbMngr.getCategory(cateId);
		if (cate == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Book category not found id = " + cateId);
			return os;
		}
		
//		Library lib = bookDbMngr.getLibrary(libId);
//		if (lib == null)
//		{
//			os.setIsSuccessful(false);
//			os.setFailureReason("Library not found id = " + libId);
//			return os;
//		}
		
		cate.setLibrary(null);
		bookDbMngr.updateCategory(cate);
		
		return os;
	}

	public OperationStatus renewBookKeyPair(Long id) {
		// TODO Auto-generated method stub
		return new OperationStatus();
	}

//	public OperationStatus saveBook(Book book) {
//		OperationStatus os = new OperationStatus();
//		if (book == null)
//		{
//			os.setIsSuccessful(false);
//			os.setFailureReason("Null input");
//			return os;
//		}
//		
//		//TODO some logic check here;
//		bookDbMngr.saveBook(book);
//		
//		return null;
//	}
//
//	public OperationStatus saveCategory(Category category) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public OperationStatus saveLibrary(Library library) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	public OperationStatus storeBook(WebServiceBook instance) {
		OperationStatus os = new OperationStatus();
		if (instance == null /*|| instance.getBook() == null*/)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input");
			return os;
		}
		
		//TODO some logic Event
		//Here we need to store the file/images to specific path
		//and add the fileDescriptor to instance.
		String fileName = FileManager.generateFileName(FileManager.FILE_KEY_BOOK);
		if (fileName == null)
		{
			logger.error("Could not generate File name");
			os.setIsSuccessful(false);
			os.setFailureReason("Could not generate File name");
			return os;
		}
		
		String checkResult = null;
		
		//Check the book packages upload;
		if (instance.getBookPackages() != null && instance.getBookPackages().size() > 0)
		{
			for(int i = 0; i <instance.getBookPackages().size(); i ++)
			{
				UploadedFile contentImage = instance.getBookPackages().get(i);
				if (contentImage != null)
				{
					checkResult = UploadedFileValidator.validate(contentImage);
					if (checkResult != null)
					{
						logger.error(checkResult);
						os.setIsSuccessful(false);
						os.setFailureReason(checkResult);
						return os;
					}
					
					//Store the files;
					FileManager.saveAsFile(fileName + "bc" + (i+1), contentImage.getFileContent());
					//Then we need to parse the file, user our java-epub interface
//					BookEPubClient bookReader = new BookEPubClient();
					Book book = null;
					try {
						book = FileManager.parseEpubBook(fileName + "bc" + (i+1));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					book.setSize(new Double(contentImage.getFileSize()));
					book.setBookContent(fileName + "bc" + (i+1));
					
					//Store the book to DB
					this.bookDbMngr.saveBook(book);
					
					//OK, Now we need to index the book
					SimpleSearchManager sm = new SimpleSearchManager();
					try {
						sm.init();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						sm.addBook(book, FileManager.getFileByName(book.getBookContent()));
					} catch (CorruptIndexException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (LockObtainFailedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		}
		
		if (instance.getBook() != null)
		{
			//process the Images files
			//Store the files;

			//Image Small
			UploadedFile image = instance.getUploadedImageSmall();
			checkResult = UploadedFileValidator.validate(image);
			if (checkResult == null)
			{
				FileManager.saveAsFile(fileName + "is", 
						image.getFileContent());
				//Then we need to parse the file, user our java-epub interface
				instance.getBook().setImageSmall(fileName + "is");
			}
			
			//Image Large
			image = instance.getUploadedImageLarge();
			checkResult = UploadedFileValidator.validate(image);
			if (checkResult == null)
			{
				FileManager.saveAsFile(fileName + "il", 
						image.getFileContent());
				//Then we need to parse the file, user our java-epub interface
				instance.getBook().setImageLarge(fileName + "il");
			}
			
			//Image 3d Small
			image = instance.getUploadedImage3DSmall();
			checkResult = UploadedFileValidator.validate(image);
			if (checkResult == null)
			{
				FileManager.saveAsFile(fileName + "3ds", 
						image.getFileContent());
				//Then we need to parse the file, user our java-epub interface
				instance.getBook().setImage3DSmall(fileName + "3ds");
			}
			
			//Image 3d Large
			image = instance.getUploadedImage3DLarge();
			checkResult = UploadedFileValidator.validate(image);
			if (checkResult == null)
			{
				FileManager.saveAsFile(fileName + "3dl", 
						image.getFileContent());
				//Then we need to parse the file, user our java-epub interface
				instance.getBook().setImage3DLarge(fileName + "3dl");
			}
			
			//Book content
			image = instance.getBookPackage();
			checkResult = UploadedFileValidator.validate(image);
			if (checkResult == null)
			{
				FileManager.saveAsFile(fileName + "bc", 
						image.getFileContent());
				//Then we need to parse the file, user our java-epub interface
				instance.getBook().setBookContent(fileName + "bc");
			}
			
			//TODO some logic book
//			bookDbMngr.saveBook(instance.getBook());
			instance.getKey().setExpired(false);
			instance.getKey().setValidFrom(new Date());
			bookDbMngr.saveBook(instance.getBook());
			accountDbMngr.storeKey(instance.getKey());
			
			
			//OK, Now we need to index the book
			SimpleSearchManager sm = new SimpleSearchManager();
			try {
				sm.init();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				sm.addBook(instance.getBook(), 
						FileManager.getFileByName(instance.getBook().getBookContent()));
			} catch (CorruptIndexException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LockObtainFailedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		return os;
	}

	public OperationStatus storeCategory(WebServiceCategory instance) {
		OperationStatus os = new OperationStatus();
		if (instance == null || instance.getCategory() == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input");
			return os;
		}
		//TODO some logic book
		bookDbMngr.storeCategory(instance.getCategory());
		return os;
	}

	public OperationStatus storeLibrary(WebServiceLibrary instance) {
		OperationStatus os = new OperationStatus();
		if (instance == null || instance.getLibrary() == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input");
			return os;
		}
		//TODO some logic book
		bookDbMngr.storeLibrary(instance.getLibrary());
		return os;
	}

	public OperationStatus updateBook(WebServiceBook instance) {
		OperationStatus os = new OperationStatus();
		if (instance == null || instance.getBook() == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input");
			return os;
		}
		//TODO some logic book
		bookDbMngr.saveBook(instance.getBook());
		return os;
	}

	public OperationStatus updateCategory(WebServiceCategory instance) {
		OperationStatus os = new OperationStatus();
		if (instance == null || instance.getCategory() == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input");
			return os;
		}
		//TODO some logic book
		bookDbMngr.updateCategory(instance.getCategory());
		return os;
	}

	public OperationStatus updateLibrary(WebServiceLibrary instance) {
		OperationStatus os = new OperationStatus();
		if (instance == null || instance.getLibrary() == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input");
			return os;
		}
		//TODO some logic book
		bookDbMngr.updateLibrary(instance.getLibrary());
		return os;
	}




	public WebBookCateListResult getCategories(WebServiceListParams listParams) {
		
		List<Category> libs = (List<Category>) bookDbMngr.getAllCategories();
		WebBookCateListResult result= new WebBookCateListResult();
		
		result = (WebBookCateListResult) WebListResultHelper.getSpecifiedPageList(libs, listParams, result);
	
		//Solve the lasy initialization
		List<Category> list = (List<Category>) result.getList();
		result.setList(new ArrayList<Category>(0));
		//Eager get @ManytoOne, Null oneToMany;
		for(Category instance : list)
		{
			result.getList().add(instance.setToPlainObject(instance));
		}
		
		return result;
	}

	public WebLibraryListResult getLibraries(WebServiceListParams listParams) {
		List<Library> libs = (List<Library>) bookDbMngr.getAllLibraries();
		WebLibraryListResult result= new WebLibraryListResult();
		
		result= (WebLibraryListResult) WebListResultHelper.getSpecifiedPageList(libs, listParams, result);
		//Eager get @ManytoOne, Null oneToMany;
		//Solve the lasy initialization
		List<Library> list = (List<Library>) result.getList();
		result.setList(new ArrayList<Library>(0));
		//Eager get @ManytoOne, Null oneToMany;
		for(Library instance : list)
		{
			result.getList().add(instance.setToPlainObject(instance));
		}
		
		return result;
	}

	public WebBookListResult listBooks(WebServiceListParams listParams) {
		List<Book> list = (List<Book>) bookDbMngr.getBooks();
		WebBookListResult result= new WebBookListResult();
		
		
		result = (WebBookListResult) WebListResultHelper.getSpecifiedPageList(list, listParams, result);
		
		//Eager get @ManytoOne, Null oneToMany;
		//Solve the lasy initialization
		List<Book> resultlist = (List<Book>) result.getList();
		result.setList(new ArrayList<Book>(0));
		//Eager get @ManytoOne, Null oneToMany;
		for(Book instance : resultlist)
		{
			result.getList().add(instance.setToPlainObject(instance));
		}
		
		return result;
	}
	
	
	


	
	public WebServiceLibrary getLibraryByName(String name) {
		// TODO Auto-generated method stub
		WebServiceLibrary result = new WebServiceLibrary();
		if (name == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Null input Id");
			return result;
		}
		Library instance = bookDbMngr.getLibraryByName(name);
		if (instance == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Library not found by name: " + name);
			return result;
		}
		
		//Eager get @ManytoOne, Null oneToMany;
		
		//instance.getCurrentKeyPair();
		
		result.setLibrary(instance.setToPlainObject(instance));
		return result;
	}


	public WebServiceCategory getCategoryByName(String name) {
		// TODO Auto-generated method stub
		WebServiceCategory result = new WebServiceCategory();
		if (name == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Null input Id");
			return result;
		}
		Category instance = bookDbMngr.getCategoryByName(name);
		if (instance == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Category not found by name: " + name);
			return result;
		}
		
		//Eager get @ManytoOne, Null oneToMany;
		//instance.getCurrentKeyPair();
		result.setCategory(instance.setToPlainObject(instance));
		return result;
	}


	public WebServiceBook getBookByYdkId(String ydkId) {
		// TODO Auto-generated method stub
		WebServiceBook result = new WebServiceBook();
		if (ydkId == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Null input Id");
			return result;
		}
		Book instance = bookDbMngr.getBookByYdkId(ydkId);
		if (instance == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Book not found by ydkId: " + ydkId);
			return result;
		}
		
		//Eager get @ManytoOne, Null oneToMany;
		
		//instance.getCurrentKeyPair();
		
		result.setBook(instance.setToPlainObject(instance));
		return result;
	}




}