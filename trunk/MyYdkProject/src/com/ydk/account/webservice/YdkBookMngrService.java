/**
 * 
 */
package com.ydk.account.webservice;

/**
 * @author y140zhan
 *
 */
import java.util.List;
import javax.jws.WebParam;
import javax.jws.WebService;


import org.springframework.dao.DataAccessException;

import com.ydk.account.persistence.entity.Category;
import com.ydk.account.persistence.entity.Library;
import com.ydk.account.persistence.entity.Partner;
import com.ydk.account.persistence.entity.Reader;
import com.ydk.account.persistence.entity.YdkAccount;
import com.ydk.account.persistence.entity.YdkRole;
import com.ydk.book.persistence.entity.Book;
import com.ydk.book.webservice.OperationStatus;
import com.ydk.book.webservice.WebBookCateListResult;
import com.ydk.book.webservice.WebBookListResult;
import com.ydk.book.webservice.WebLibraryListResult;
import com.ydk.book.webservice.WebServiceBook;
import com.ydk.book.webservice.WebServiceCategory;
import com.ydk.book.webservice.WebServiceLibrary;
import com.ydk.book.webservice.WebServiceListParams;


@WebService
public interface YdkBookMngrService {
    
	//Book Managements
    WebBookListResult listBooks(@WebParam(name = "listParams")
    		WebServiceListParams webBookListParams);
    
    WebServiceBook getBookById(@WebParam(name = "id")Long id);
//    OperationStatus saveBook(@WebParam(name = "book")WebServiceBook webServiceBook);
    WebServiceBook getBookByYdkId(@WebParam(name = "ydkId")String ydkId);
    OperationStatus storeBook(@WebParam(name = "book")WebServiceBook instance);
    OperationStatus updateBook(@WebParam(name = "book")WebServiceBook instance);
    OperationStatus deleteBook(@WebParam(name = "id")Long id);
    OperationStatus renewBookKeyPair(@WebParam(name = "id")Long id);
	
	//Library Managements
    WebLibraryListResult getLibraries(@WebParam(name = "listParams")WebServiceListParams listParams);
    WebServiceLibrary getLibraryByName(@WebParam(name = "name")String name);
    WebServiceLibrary getLibraryById(@WebParam(name = "id")Long id);
//    OperationStatus saveLibrary(@WebParam(name = "library")Library library);
    OperationStatus storeLibrary(@WebParam(name = "library")WebServiceLibrary instance);
    OperationStatus updateLibrary(@WebParam(name = "library")WebServiceLibrary instance);
    OperationStatus deleteLibrary(@WebParam(name = "id")Long id);
	
	//Category Managements

//    Category getCategoryByName(@WebParam(name = "name")String name) ;
    WebBookCateListResult getCategories(@WebParam(name = "listParams")WebServiceListParams listParams);
    WebServiceCategory getCategoryById(@WebParam(name = "id")Long id);
    WebServiceCategory getCategoryByName(@WebParam(name = "name")String name);
//    OperationStatus saveCategory(@WebParam(name = "category")Category category);
    OperationStatus storeCategory(@WebParam(name = "category")WebServiceCategory instance);
    OperationStatus updateCategory(@WebParam(name = "category")WebServiceCategory instance);
    OperationStatus deleteCategory(@WebParam(name = "id")Long id);
	
    OperationStatus addCategoryToLibrary(@WebParam(name = "cateId")Long cateId, 
			@WebParam(name = "libId")Long libId);
    OperationStatus remCategoryFromLibrary(@WebParam(name = "cateId")Long cateId, 
			@WebParam(name = "libId")Long libId);
    OperationStatus addBookToCategory(@WebParam(name = "bookId")Long bookId, 
			@WebParam(name = "cateId")Long cateId);
    OperationStatus remBookFromCategory(@WebParam(name = "bookId")Long bookId, 
			@WebParam(name = "cateId")Long cateId);
	

}
