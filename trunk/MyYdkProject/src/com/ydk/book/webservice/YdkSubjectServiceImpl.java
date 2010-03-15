/**
 * 
 */
package com.ydk.book.webservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ydk.account.persistence.entity.BookMark;
import com.ydk.account.persistence.entity.Evaluation;
import com.ydk.account.persistence.entity.Library;
import com.ydk.account.persistence.entity.Reader;
import com.ydk.account.persistence.entity.ReaderComment;
import com.ydk.account.persistence.entity.ResourceType;
import com.ydk.account.persistence.interfaces.AccountDbMngr;
import com.ydk.account.webservice.WebListResultHelper;
import com.ydk.book.persistence.entity.Book;
import com.ydk.book.persistence.entity.BookNews;
import com.ydk.book.persistence.entity.BookNewsCategory;
import com.ydk.book.persistence.entity.BookReview;
import com.ydk.book.persistence.entity.BookReviewCategory;
import com.ydk.book.persistence.entity.Event;
import com.ydk.book.persistence.entity.EventCategory;
import com.ydk.book.persistence.entity.Interview;
import com.ydk.book.persistence.entity.InterviewCategory;
import com.ydk.book.persistence.interfaces.BookDbMngr;

/**
 * @author y140zhan
 *
 */
@WebService(endpointInterface = "com.ydk.book.webservice.YdkSubjectService")
@Transactional
public final class YdkSubjectServiceImpl implements YdkSubjectService {
	
	protected final BookDbMngr bookDbMngr;
	protected final AccountDbMngr accountDbMngr;
	
	@Autowired
	public YdkSubjectServiceImpl(BookDbMngr bookDbMngr, AccountDbMngr accountDbMngr) {
		this.bookDbMngr = bookDbMngr;
		this.accountDbMngr = accountDbMngr;
	}
	

	public WebServiceBookNews getBookNews(Long bookNewsId) {
		WebServiceBookNews result = new WebServiceBookNews();
		if (bookNewsId == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("null bookNewsId");
			return result;
		}
		result.setBookNews(bookDbMngr.getBookNews(bookNewsId));
		if (result.getBookNews() == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Error, BookNews not found");
		}
		result.setBookNews(result.getBookNews().setToPlainObject(result.getBookNews()));
		return result;
	}

	public WebServiceBookReview getBookReview(Long bookReviewId) {
		
		WebServiceBookReview result = new WebServiceBookReview();
		if (bookReviewId == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("null bookReviewId");
			return result;
		}
		result.setBookReview(bookDbMngr.getBookReview(bookReviewId));
		if (result.getBookReview() == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Error, BookReview not found");
		}
		
		result.setBookReview(
				result.getBookReview().setToPlainObject(result.getBookReview()));
		

		return result;
	}

	public WebServiceEvent getEvent(Long eventId) {
		WebServiceEvent result = new WebServiceEvent();
		if (eventId == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("null eventId");
			return result;
		}
		result.setEvent(bookDbMngr.getEvent(eventId));
		if (result.getEvent() == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Error, Event not found");
		}
		
		result.setEvent(result.getEvent().setToPlainObject(result.getEvent()));
		
		return result;
	}

	public WebServiceInterview getInterview(Long interviewId) {
		WebServiceInterview result = new WebServiceInterview();
		if (interviewId == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("null interviewId");
			return result;
		}
		result.setInterview(bookDbMngr.getInterview(interviewId));
		if (result.getInterview() == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Error, Interview not found");
		}
		result.setInterview(
				result.getInterview().setToPlainObject(result.getInterview()));
		
		
		return result;
	}

	public WebBookNewsCateListResult listAllBookNewsCategories() {
		WebBookNewsCateListResult result =new WebBookNewsCateListResult();
		
		result.setList((List<BookNewsCategory>) bookDbMngr.getAllBookNewsCategories());
		
		result.setCurrentPage(0);
		result.setTotalItems(result.getList().size());
		result.setTotalPages(1);
		
		//Eager get @ManytoOne, Null oneToMany;
		for(BookNewsCategory instance : result.getList())
		{
			instance.setToPlainObjectQuick(instance);
		}
		
		return result;
	}

	public WebBookReviewCateListResult listAllBookReviewCategories() {
		WebBookReviewCateListResult result =new WebBookReviewCateListResult();
		
		result.setList((List<BookReviewCategory>) bookDbMngr.getAllBookReviewCategories());
		result.setCurrentPage(0);
		result.setTotalItems(result.getList().size());
		result.setTotalPages(1);
		
		//Eager get @ManytoOne, Null oneToMany;
		for(BookReviewCategory instance : result.getList())
		{
			instance.setToPlainObjectQuick(instance);
		}
		
		return result;
	}

	public WebEventCateListResult listAllEventCategories() {
		WebEventCateListResult result =new WebEventCateListResult();
		
		result.setList((List<EventCategory>) bookDbMngr.getAllEventCategories());
		result.setCurrentPage(0);
		result.setTotalItems(result.getList().size());
		result.setTotalPages(1);
		
		//Eager get @ManytoOne, Null oneToMany;
		for(EventCategory instance : result.getList())
		{
			instance.setToPlainObjectQuick(instance);
		}
		
		return result;
	}

	public WebInterviewCateListResult listAllInterviewCategories() {
		WebInterviewCateListResult result =new WebInterviewCateListResult();
		
		result.setList((List<InterviewCategory>) bookDbMngr.getAllInterviewCategories());
		result.setCurrentPage(0);
		result.setTotalItems(result.getList().size());
		result.setTotalPages(1);
		
		//Eager get @ManytoOne, Null oneToMany;
		for(InterviewCategory instance : result.getList())
		{
			instance.setToPlainObjectQuick(instance);
		}
		
		return result;
	}

	
	public WebBookNewsCateListResult listBookNewsCategories(
			WebServiceListParams listParams) {
		// TODO Auto-generated method stub
		List<BookNewsCategory> bookNewsCateList = 
			(List<BookNewsCategory>)bookDbMngr.getAllBookNewsCategories();
		WebBookNewsCateListResult result = new WebBookNewsCateListResult();
		
		result= (WebBookNewsCateListResult) WebListResultHelper.getSpecifiedPageList(bookNewsCateList, listParams, result);
		//Eager get @ManytoOne, Null oneToMany;
		for(BookNewsCategory instance : result.getList())
		{
			instance.setToPlainObjectQuick(instance);
		}
		
		return result;
	}

	public WebBookNewsListResult listBookNews(Long cateId,
			WebServiceListParams listParams) {
		
		List<BookNews> bookNewsList = (List<BookNews>)bookDbMngr.getBookNewsCategory(cateId).getBookNewss();
		WebBookNewsListResult result = new WebBookNewsListResult();
		
		result= (WebBookNewsListResult) WebListResultHelper.getSpecifiedPageList(bookNewsList, listParams, result);
		bookNewsList = new ArrayList<BookNews>(0);
		
		//Eager get @ManytoOne, Null oneToMany;
		for(BookNews instance : result.getList())
		{
			bookNewsList.add(instance.setToPlainObjectQuick(instance));
		}
		result.setList(bookNewsList);
		
		return result;
	}

	public WebBookReviewCateListResult listBookReviewCategories(
			WebServiceListParams listParams) {
		
		List<BookReviewCategory> bookReviewCateList = 
			(List<BookReviewCategory>)bookDbMngr.getAllBookReviewCategories();
		WebBookReviewCateListResult result = new WebBookReviewCateListResult();
		
		result = (WebBookReviewCateListResult) WebListResultHelper.getSpecifiedPageList(bookReviewCateList, listParams, result);
		
		//Eager get @ManytoOne, Null oneToMany;
		for(BookReviewCategory instance : result.getList())
		{
			instance.setToPlainObjectQuick(instance);
		}
		
		return result;
	}

	public WebBookReviewListResult listBookReviews(Long cateId,
			WebServiceListParams listParams) {
		
		List<BookReview> bookReviewList = 
			(List<BookReview>)bookDbMngr.getBookReviewCategory(cateId).getBookReviews();
		WebBookReviewListResult result = new WebBookReviewListResult();
		
		result = (WebBookReviewListResult) WebListResultHelper.getSpecifiedPageList(bookReviewList, listParams, result);
		//Eager get @ManytoOne, Null oneToMany;
		
		bookReviewList = new ArrayList<BookReview>();
		for(BookReview instance : result.getList())
		{
			bookReviewList.add(instance.setToPlainObjectQuick(instance));
		}
		
		result.setList(bookReviewList);
		
		return result;
	}

	public WebEventCateListResult listEventCategories(
			WebServiceListParams listParams) {
		
		List<EventCategory> eventCateList = 
			(List<EventCategory>)bookDbMngr.getAllEventCategories();
		WebEventCateListResult result = new WebEventCateListResult();
		
		result = (WebEventCateListResult) WebListResultHelper.getSpecifiedPageList(eventCateList, listParams, result);
		
		
		//Eager get @ManytoOne, Null oneToMany;
		for(EventCategory instance : result.getList())
		{
			instance.setToPlainObjectQuick(instance);
		}
		
		return result;
	}

	public WebEventListResult listEvents(Long cateId,
			WebServiceListParams listParams) {
		
		List<Event> eventList = 
			(List<Event>)bookDbMngr.getEventCategory(cateId).getEvents();
		WebEventListResult result = new WebEventListResult();
		
		result= (WebEventListResult) WebListResultHelper.getSpecifiedPageList(eventList, listParams, result);
		
		eventList = new ArrayList<Event>(0);
		
		//Eager get @ManytoOne, Null oneToMany;
		for(Event instance : result.getList())
		{
			eventList.add(instance.setToPlainObjectQuick(instance));
		}
		result.setList(eventList);		
		
		return result;
	}

	public WebInterviewCateListResult listInterviewCategories(
			WebServiceListParams listParams) {
		
		List<InterviewCategory> interviewCateList = 
			(List<InterviewCategory>)bookDbMngr.getAllInterviewCategories();
		WebInterviewCateListResult result = new WebInterviewCateListResult();
		
		result= (WebInterviewCateListResult) WebListResultHelper.getSpecifiedPageList(interviewCateList, listParams, result);
		
		//Eager get @ManytoOne, Null oneToMany;
		for(InterviewCategory instance : result.getList())
		{
			instance.setToPlainObjectQuick(instance);
		}
		
		return result;
	}

	public WebInterviewListResult listInterviews(Long cateId,
			WebServiceListParams listParams) {
		
		List<Interview> interviewList = 
			(List<Interview>)bookDbMngr.getInterviewCategory(cateId).getInterviews();
		WebInterviewListResult result = new WebInterviewListResult();
		
		
		
		result= (WebInterviewListResult) WebListResultHelper.getSpecifiedPageList(interviewList, listParams, result);
		
		interviewList = new ArrayList<Interview>();
		//Eager get @ManytoOne, Null oneToMany;
		for(Interview instance : result.getList())
		{
			interviewList.add(instance.setToPlainObjectQuick(instance));
		}
		
		result.setList(interviewList);
		
		return result;
	}
	
	
	

	

}
