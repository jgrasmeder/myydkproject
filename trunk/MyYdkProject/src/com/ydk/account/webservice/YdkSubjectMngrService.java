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
import com.ydk.book.persistence.entity.BookNews;
import com.ydk.book.persistence.entity.BookReview;
import com.ydk.book.persistence.entity.Event;
import com.ydk.book.persistence.entity.Interview;
import com.ydk.book.webservice.OperationStatus;
import com.ydk.book.webservice.WebBookNewsCateListResult;
import com.ydk.book.webservice.WebBookNewsListResult;
import com.ydk.book.webservice.WebBookReviewCateListResult;
import com.ydk.book.webservice.WebBookReviewListResult;
import com.ydk.book.webservice.WebBookTagListResult;
import com.ydk.book.webservice.WebEventCateListResult;
import com.ydk.book.webservice.WebEventListResult;
import com.ydk.book.webservice.WebInterviewCateListResult;
import com.ydk.book.webservice.WebInterviewListResult;
import com.ydk.book.webservice.WebLibraryListResult;
import com.ydk.book.webservice.WebServiceBookNews;
import com.ydk.book.webservice.WebServiceBookNewsCategory;
import com.ydk.book.webservice.WebServiceBookReview;
import com.ydk.book.webservice.WebServiceBookReviewCategory;
import com.ydk.book.webservice.WebServiceBookTag;
import com.ydk.book.webservice.WebServiceEvent;
import com.ydk.book.webservice.WebServiceEventCategory;
import com.ydk.book.webservice.WebServiceInterview;
import com.ydk.book.webservice.WebServiceInterviewCategory;
import com.ydk.book.webservice.WebServiceLibrary;
import com.ydk.book.webservice.WebServiceListParams;


@WebService
public interface YdkSubjectMngrService {
    
	//BookNews Managements
    WebBookNewsListResult listBookNewses(@WebParam(name = "listParams")
    		WebServiceListParams listParams);
   
//    BookNews getBookNewsByName(@WebParam(name = "name")String name) ;
    WebServiceBookNews getBookNewsById(@WebParam(name = "id")Long id);
    WebServiceBookNews getBookNewsByTitle(@WebParam(name = "name")String name);
//    OperationStatus saveBookNews(@WebParam(name = "bookNews")WebServiceBookNews bookNews);
    OperationStatus storeBookNews(@WebParam(name = "bookNews")WebServiceBookNews instance);
    OperationStatus updateBookNews(@WebParam(name = "bookNews")WebServiceBookNews instance);
    OperationStatus deleteBookNews(@WebParam(name = "id")Long id);

	//Interview Managements
    WebInterviewListResult listInterviews(@WebParam(name = "listParams")
    		WebServiceListParams listParams);
//    Interview getInterviewByName(@WebParam(name = "name")String name) ;
    WebServiceInterview getInterviewById(@WebParam(name = "id")Long id);
    WebServiceInterview getInterviewByTitle(@WebParam(name = "name")String name);
//    OperationStatus saveInterview(@WebParam(name = "interview")WebServiceInterview interview);
    OperationStatus storeInterview(@WebParam(name = "interview")WebServiceInterview instance);
    OperationStatus updateInterview(@WebParam(name = "interview")WebServiceInterview instance);
    OperationStatus deleteInterview(@WebParam(name = "id")Long id);

	//BookReview Managements
    WebBookReviewListResult listBookReviews(@WebParam(name = "listParams")
    		WebServiceListParams listParams);
//    BookReview getBookReviewByName(@WebParam(name = "name")String name) ;
    WebServiceBookReview getBookReviewById(@WebParam(name = "id")Long id);
    WebServiceBookReview getBookReviewByTitle(@WebParam(name = "name")String name);
//	void saveBookReview(@WebParam(name = "bookReview")BookReview bookReview);
    OperationStatus storeBookReview(@WebParam(name = "bookReview")WebServiceBookReview instance);
    OperationStatus updateBookReview(@WebParam(name = "bookReview")WebServiceBookReview instance);
    OperationStatus deleteBookReview(@WebParam(name = "id")Long id);
	
	//Event Managements
    WebEventListResult listEvents(@WebParam(name = "listParams")
    		WebServiceListParams listParams);
//    Event getEventByName(@WebParam(name = "name")String name) ;
    WebServiceEvent getEventById(@WebParam(name = "id")Long id);
    WebServiceEvent getEventByTitle(@WebParam(name = "name")String name);
//	void saveEvent(@WebParam(name = "event")Event event);
    OperationStatus storeEvent(@WebParam(name = "event")WebServiceEvent instance);
    OperationStatus updateEvent(@WebParam(name = "event")WebServiceEvent instance);
    OperationStatus deleteEvent(@WebParam(name = "id")Long id);
    
    
    WebBookNewsCateListResult getBookNewsCategories(@WebParam(name = "listParams")WebServiceListParams listParams);
    WebServiceBookNewsCategory getBookNewsCategoryByName(@WebParam(name = "name")String name);
    WebServiceBookNewsCategory getBookNewsCategoryById(@WebParam(name = "id")Long id);
    OperationStatus storeBookNewsCategory(@WebParam(name = "library")WebServiceBookNewsCategory instance);
    OperationStatus updateBookNewsCategory(@WebParam(name = "library")WebServiceBookNewsCategory instance);
    OperationStatus deleteBookNewsCategory(@WebParam(name = "id")Long id);
    
    WebBookReviewCateListResult getBookReviewCategories(@WebParam(name = "listParams")WebServiceListParams listParams);
    WebServiceBookReviewCategory getBookReviewCategoryByName(@WebParam(name = "name")String name) ;
    WebServiceBookReviewCategory getBookReviewCategoryById(@WebParam(name = "id")Long id);
    OperationStatus storeBookReviewCategory(@WebParam(name = "library")WebServiceBookReviewCategory instance);
    OperationStatus updateBookReviewCategory(@WebParam(name = "library")WebServiceBookReviewCategory instance);
    OperationStatus deleteBookReviewCategory(@WebParam(name = "id")Long id);
    
    WebEventCateListResult getEventCategories(@WebParam(name = "listParams")WebServiceListParams listParams);
    WebServiceEventCategory getEventCategoryByName(@WebParam(name = "name")String name) ;
    WebServiceEventCategory getEventCategoryById(@WebParam(name = "id")Long id);
    OperationStatus storeEventCategory(@WebParam(name = "library")WebServiceEventCategory instance);
    OperationStatus updateEventCategory(@WebParam(name = "library")WebServiceEventCategory instance);
    OperationStatus deleteEventCategory(@WebParam(name = "id")Long id);
    
    WebInterviewCateListResult getInterviewCategories(@WebParam(name = "listParams")WebServiceListParams listParams);
    WebServiceInterviewCategory getInterviewCategoryByName(@WebParam(name = "name")String name) ;
    WebServiceInterviewCategory getInterviewCategoryById(@WebParam(name = "id")Long id);
    OperationStatus storeInterviewCategory(@WebParam(name = "library")WebServiceInterviewCategory instance);
    OperationStatus updateInterviewCategory(@WebParam(name = "library")WebServiceInterviewCategory instance);
    OperationStatus deleteInterviewCategory(@WebParam(name = "id")Long id);
    
    
  //BookTag Managements
    WebBookTagListResult listBookTags(@WebParam(name = "listParams")
    		WebServiceListParams listParams);
    WebServiceBookTag getBookTagById(@WebParam(name = "id")Long id);
    WebServiceBookTag getBookTagByName(@WebParam(name = "name")String name);

    OperationStatus storeBookTag(@WebParam(name = "event")WebServiceBookTag instance);
    OperationStatus updateBookTag(@WebParam(name = "event")WebServiceBookTag instance);
    OperationStatus deleteBookTag(@WebParam(name = "id")Long id);

    
    


}
