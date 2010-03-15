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
public interface YdkBookFreeService {
    
	//Book Free Service
    WebBookListResult listBooks(@WebParam(name = "listParams")
    		WebServiceListParams webBookListParams);
	
    OperationStatus evaluateBook(@WebParam(name = "readerId")Long readerId,
    		@WebParam(name = "bookId")Long bookId,
    		@WebParam(name = "score") Double score);
    
    OperationStatus commentBook(@WebParam(name = "readerId")Long readerId,
    		@WebParam(name = "bookId")Long bookId,
    		@WebParam(name = "comments") String comments);
    
    OperationStatus addBookMark(@WebParam(name = "readerId")Long readerId,
    		@WebParam(name = "bookId")Long bookId,
    		@WebParam(name = "chapter") Integer chapter,
    		@WebParam(name = "section") Integer section,
    		@WebParam(name = "page") Integer page,
    		@WebParam(name = "name") String name);
    
    OperationStatus removeBookMark(@WebParam(name = "readerId")Long readerId,
    		@WebParam(name = "bookId")Long bookId,
    		@WebParam(name = "bookMarkId") Long bookMarkId);
    
    WebBookMarkListResult listMyBookMark(@WebParam(name = "readerId")Long readerId,
    		@WebParam(name = "bookId")Long bookId,
    		@WebParam(name = "listParams")
    		WebServiceListParams listParams);
    
    OperationStatus buyEbook(@WebParam(name = "readerId")Long readerId,
    		@WebParam(name = "bookId")Long bookId);
    
    OperationStatus buyPaperBook(@WebParam(name = "readerId")Long readerId,
    		@WebParam(name = "bookId")Long bookId);
    
    WebServiceFile getImage(@WebParam(name = "imageId")Long imageId);
    
    WebBookListResult searchBooks(@WebParam(name = "keywords")String keywords, @WebParam(name = "listParams")
    		WebServiceListParams webBookListParams);
    
    
    

    
    
}
