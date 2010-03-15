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
import com.ydk.book.persistence.entity.BookNews;
import com.ydk.book.persistence.entity.BookReview;
import com.ydk.book.persistence.entity.Event;
import com.ydk.book.persistence.entity.Interview;
import com.ydk.account.persistence.entity.*;
import com.ydk.book.webservice.WebServiceBook;


@WebService
public interface YdkFrontEndWebService {
    
	//Ydk Front End Service
	WebLibraryListResult getLibraries(@WebParam(name = "listParams")WebServiceListParams listParams);
	
	WebBookCateListResult getBookCategroryList(@WebParam(name = "libId")Long libId,
    		@WebParam(name = "listParams")WebServiceListParams listParams);
	WebBookListResult getBookListInCategory(@WebParam(name = "cateId")Long cateId,
    		@WebParam(name = "listParams")WebServiceListParams listParams);
	
	WebServiceFile getFileByName(@WebParam(name = "fileName")String fileName);
	
    
    
}
