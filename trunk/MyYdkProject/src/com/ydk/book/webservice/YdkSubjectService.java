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
public interface YdkSubjectService {
    
	//Ydk Subject Service
	
	//BookNews Service
	WebBookNewsCateListResult listAllBookNewsCategories();
	WebBookNewsCateListResult listBookNewsCategories(@WebParam(name = "listParams")
    		WebServiceListParams listParams);
	
    WebBookNewsListResult listBookNews(@WebParam(name = "cateId")Long cateId,
    		@WebParam(name = "listParams")WebServiceListParams listParams);
    
    WebServiceBookNews getBookNews(@WebParam(name = "bookNewsId")Long bookNewsId);
    
    
    //BookReview Service
	WebBookReviewCateListResult listAllBookReviewCategories();
	WebBookReviewCateListResult listBookReviewCategories(@WebParam(name = "listParams")
    		WebServiceListParams listParams);
	
    WebBookReviewListResult listBookReviews(@WebParam(name = "cateId")Long cateId,
    		@WebParam(name = "listParams")WebServiceListParams listParams);
    
    WebServiceBookReview getBookReview(@WebParam(name = "bookNewsId")Long bookReviewId);
    
    
    //Interview Service
	WebInterviewCateListResult listAllInterviewCategories();
	WebInterviewCateListResult listInterviewCategories(@WebParam(name = "listParams")
    		WebServiceListParams listParams);
	
    WebInterviewListResult listInterviews(@WebParam(name = "cateId")Long cateId,
    		@WebParam(name = "listParams")WebServiceListParams listParams);
    
    WebServiceInterview getInterview(@WebParam(name = "bookNewsId")Long bookInterviewId);
    
    
    //Event Service
	WebEventCateListResult listAllEventCategories();
	WebEventCateListResult listEventCategories(@WebParam(name = "listParams")
    		WebServiceListParams listParams);
	
    WebEventListResult listEvents(@WebParam(name = "cateId")Long cateId,
    		@WebParam(name = "listParams")WebServiceListParams listParams);
    
    WebServiceEvent getEvent(@WebParam(name = "bookNewsId")Long bookEventId);
    
    
    
    
    
    
}
