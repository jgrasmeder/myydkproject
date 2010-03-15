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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ydk.account.persistence.entity.Category;
import com.ydk.account.persistence.entity.UploadedFile;
import com.ydk.account.persistence.interfaces.AccountDbMngr;
import com.ydk.account.persistence.operator.TransactionQuery;
import com.ydk.account.validator.UploadedFileValidator;
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
import com.ydk.book.persistence.interfaces.BookDbMngr;
import com.ydk.book.webservice.OperationStatus;
import com.ydk.book.webservice.WebBookCateListResult;
import com.ydk.book.webservice.WebBookListResult;
import com.ydk.book.webservice.WebBookMarkListResult;
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
import com.ydk.book.webservice.WebServiceBook;
import com.ydk.book.webservice.WebServiceBookNews;
import com.ydk.book.webservice.WebServiceBookNewsCategory;
import com.ydk.book.webservice.WebServiceBookReview;
import com.ydk.book.webservice.WebServiceBookReviewCategory;
import com.ydk.book.webservice.WebServiceBookTag;
import com.ydk.book.webservice.WebServiceCategory;
import com.ydk.book.webservice.WebServiceEvent;
import com.ydk.book.webservice.WebServiceEventCategory;
import com.ydk.book.webservice.WebServiceInterview;
import com.ydk.book.webservice.WebServiceInterviewCategory;
import com.ydk.book.webservice.WebServiceLibrary;
import com.ydk.book.webservice.WebServiceListParams;
import com.ydk.web.FileManager;
/**
 * @author y140zhan
 *
 */
@WebService(endpointInterface = "com.ydk.account.webservice.YdkSubjectMngrService")
@Transactional
public final class YdkSubjectMngrServiceImpl implements YdkSubjectMngrService {
	
	private final Logger logger = Logger.getLogger(YdkSubjectMngrServiceImpl.class.getName());
	protected final AccountDbMngr accountDbMngr;
	protected final BookDbMngr bookDbMngr;
	
	@Autowired
	public YdkSubjectMngrServiceImpl(AccountDbMngr accountDbMngr, 
			BookDbMngr bookDbMngr) {
		this.accountDbMngr = accountDbMngr;
		this.bookDbMngr = bookDbMngr;
	}

	public OperationStatus deleteBookNews(Long id) {
		OperationStatus os = new OperationStatus();
		if (id == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input Id");
			return os;
		}
		
		bookDbMngr.deleteBookNews(id);
		return os;
	}

	public OperationStatus deleteBookReview(Long id) {
		OperationStatus os = new OperationStatus();
		if (id == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input Id");
			return os;
		}
		
		bookDbMngr.deleteBookReview(id);
		return os;
	}

	public OperationStatus deleteEvent(Long id) {
		OperationStatus os = new OperationStatus();
		if (id == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input Id");
			return os;
		}
		
		bookDbMngr.deleteEvent(id);
		return os;
	}

	public OperationStatus deleteInterview(Long id) {
		OperationStatus os = new OperationStatus();
		if (id == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input Id");
			return os;
		}
		
		bookDbMngr.deleteInterview(id);
		return os;
	}

	
	public WebServiceBookNews getBookNewsById(Long id) {
		WebServiceBookNews wbn = new WebServiceBookNews();
		if (id == null)
		{
			wbn.getOs().setIsSuccessful(false);
			wbn.getOs().setFailureReason("Null input Id");
			return wbn;
		}
		BookNews instance = bookDbMngr.getBookNews(id);
		if (instance == null)
		{
			wbn.getOs().setIsSuccessful(false);
			wbn.getOs().setFailureReason("BookNews not found by id: " + id);
			return wbn;
		}
		
		//Eager get @ManytoOne, Null oneToMany;
		
		wbn.setBookNews(instance.setToPlainObject(instance));
		return wbn;
	}

	public WebServiceBookReview getBookReviewById(Long id) {
		WebServiceBookReview wbr = new WebServiceBookReview();
		if (id == null)
		{
			wbr.getOs().setIsSuccessful(false);
			wbr.getOs().setFailureReason("Null input Id");
			return wbr;
		}
		BookReview instance = bookDbMngr.getBookReview(id);
		if (instance == null)
		{
			wbr.getOs().setIsSuccessful(false);
			wbr.getOs().setFailureReason("BookNews not found by id: " + id);
			return wbr;
		}
		
		//Eager get @ManytoOne, Null oneToMany;
		
		wbr.setBookReview(instance.setToPlainObject(instance));
		return wbr;
	}

	public WebServiceEvent getEventById(Long id) {
		WebServiceEvent result = new WebServiceEvent();
		if (id == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Null input Id");
			return result;
		}
		Event instance = bookDbMngr.getEvent(id);
		if (instance == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("BookNews not found by id: " + id);
			return result;
		}
		
		//Eager get @ManytoOne, Null oneToMany;
		
		result.setEvent(instance.setToPlainObject(instance));
		return result;
	}

	public WebServiceInterview getInterviewById(Long id) {
		WebServiceInterview result = new WebServiceInterview();
		if (id == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Null input Id");
			return result;
		}
		Interview instance = bookDbMngr.getInterview(id);
		if (instance == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("BookNews not found by id: " + id);
			return result;
		}
		
		//Eager get @ManytoOne, Null oneToMany;
		
		result.setInterview(instance.setToPlainObject(instance));
		return result;
	}
	
	
	public OperationStatus storeBookNews(WebServiceBookNews instance) {
		OperationStatus os = new OperationStatus();
		if (instance == null || instance.getBookNews() == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input");
			return os;
		}
		
		
		//TODO some logic getBookNews
		//Here we need to store the file/images to specific path
		//and add the fileDescriptor to instance.
		String fileName = FileManager.generateFileName(FileManager.FILE_KEY_BOOKNEWS);
		if (fileName == null)
		{
			logger.error("Could not generate File name");
			os.setIsSuccessful(false);
			os.setFailureReason("Could not generate File name");
			return os;
		}
		
		
		//Check file Content
		String checkResult = UploadedFileValidator.validate(instance.getContentFile());
		if (checkResult != null)
		{
			logger.error(checkResult);
			os.setIsSuccessful(false);
			os.setFailureReason(checkResult);
			return os;
		}
		
		instance.getBookNews().setContent(fileName + "cf");
		//Store the files;
		FileManager.saveAsFile(fileName + "cf", instance.getContentFile().getFileContent());
		
		//Check title image
		if (instance.getTitleImage() != null)
		{
			checkResult = UploadedFileValidator.validate(instance.getTitleImage());
			if (checkResult != null)
			{
				logger.error(checkResult);
				os.setIsSuccessful(false);
				os.setFailureReason(checkResult);
				return os;
			}
			instance.getBookNews().setTitleImage(fileName + "ti");
			//Store the files;
			FileManager.saveAsFile(fileName + "ti", instance.getTitleImage().getFileContent());
		}
		//Check the content images
		if (instance.getContentImages() != null && instance.getContentImages().size() > 0)
		{
			for(int i = 0; i <instance.getContentImages().size(); i ++)
			{
				UploadedFile contentImage = instance.getContentImages().get(i);
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
					instance.getBookNews().getImages().add(fileName + "i" + (i+1));
					//Store the files;
					FileManager.saveAsFile(fileName + "i" + (i+1), contentImage.getFileContent());
				}
			}
		}
		
		//OK, now we could save the BookNews to dataStore;
		
		
		
		//TODO some logic book
		bookDbMngr.storeBookNews(instance.getBookNews());
		return os;
	}

	public OperationStatus storeBookReview(WebServiceBookReview instance) {
		OperationStatus os = new OperationStatus();
		if (instance == null || instance.getBookReview() == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input");
			return os;
		}
		
		//TODO some logic BookReview
		//Here we need to store the file/images to specific path
		//and add the fileDescriptor to instance.
		String fileName = FileManager.generateFileName(FileManager.FILE_KEY_BOOKREVIEW);
		if (fileName == null)
		{
			logger.error("Could not generate File name");
			os.setIsSuccessful(false);
			os.setFailureReason("Could not generate File name");
			return os;
		}
		
		
		//Check file Content
		String checkResult = UploadedFileValidator.validate(instance.getContentFile());
		if (checkResult != null)
		{
			logger.error(checkResult);
			os.setIsSuccessful(false);
			os.setFailureReason(checkResult);
			return os;
		}
		
		instance.getBookReview().setContent(fileName + "cf");
		//Store the files;
		FileManager.saveAsFile(fileName + "cf", instance.getContentFile().getFileContent());
		
		//Check title image
		if (instance.getTitleImage() != null)
		{
			checkResult = UploadedFileValidator.validate(instance.getTitleImage());
			if (checkResult != null)
			{
				logger.error(checkResult);
				os.setIsSuccessful(false);
				os.setFailureReason(checkResult);
				return os;
			}
			instance.getBookReview().setTitleImage(fileName + "ti");
			//Store the files;
			FileManager.saveAsFile(fileName + "ti", instance.getTitleImage().getFileContent());
		}
		//Check the content images
		if (instance.getContentImages() != null && instance.getContentImages().size() > 0)
		{
			for(int i = 0; i <instance.getContentImages().size(); i ++)
			{
				UploadedFile contentImage = instance.getContentImages().get(i);
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
					instance.getBookReview().getImages().add(fileName + "i" + (i+1));
					//Store the files;
					FileManager.saveAsFile(fileName + "i" + (i+1), contentImage.getFileContent());
				}
			}
		}
		
		//OK, now we could save the BookReview to dataStore;
		
		
		
		bookDbMngr.storeBookReview(instance.getBookReview());
		return os;
	}

	public OperationStatus storeEvent(WebServiceEvent instance) {
		OperationStatus os = new OperationStatus();
		if (instance == null || instance.getEvent() == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input");
			return os;
		}
		
		
		//TODO some logic Event
		//Here we need to store the file/images to specific path
		//and add the fileDescriptor to instance.
		String fileName = FileManager.generateFileName(FileManager.FILE_KEY_EVENT);
		if (fileName == null)
		{
			logger.error("Could not generate File name");
			os.setIsSuccessful(false);
			os.setFailureReason("Could not generate File name");
			return os;
		}
		
		
		//Check file Content
		String checkResult = UploadedFileValidator.validate(instance.getContentFile());
		if (checkResult != null)
		{
			logger.error(checkResult);
			os.setIsSuccessful(false);
			os.setFailureReason(checkResult);
			return os;
		}
		
		instance.getEvent().setContent(fileName + "cf");
		//Store the files;
		FileManager.saveAsFile(fileName + "cf", instance.getContentFile().getFileContent());
		
		//Check title image
		if (instance.getTitleImage() != null)
		{
			checkResult = UploadedFileValidator.validate(instance.getTitleImage());
			if (checkResult != null)
			{
				logger.error(checkResult);
				os.setIsSuccessful(false);
				os.setFailureReason(checkResult);
				return os;
			}
			instance.getEvent().setTitleImage(fileName + "ti");
			//Store the files;
			FileManager.saveAsFile(fileName + "ti", instance.getTitleImage().getFileContent());
		}
		//Check the content images
		if (instance.getContentImages() != null && instance.getContentImages().size() > 0)
		{
			for(int i = 0; i <instance.getContentImages().size(); i ++)
			{
				UploadedFile contentImage = instance.getContentImages().get(i);
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
					instance.getEvent().getImages().add(fileName + "i" + (i+1));
					//Store the files;
					FileManager.saveAsFile(fileName + "i" + (i+1), contentImage.getFileContent());
				}
			}
		}
		
		//OK, now we could save the BookReview to dataStore;
		
		bookDbMngr.storeEvent(instance.getEvent());
		return os;
	}

	public OperationStatus storeInterview(WebServiceInterview instance) {
		OperationStatus os = new OperationStatus();
		if (instance == null || instance.getInterview() == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input");
			return os;
		}
		
		//TODO some logic interview
		//Here we need to store the file/images to specific path
		//and add the fileDescriptor to instance.
		String fileName = FileManager.generateFileName(FileManager.FILE_KEY_INTERVIEW);
		if (fileName == null)
		{
			logger.error("Could not generate File name");
			os.setIsSuccessful(false);
			os.setFailureReason("Could not generate File name");
			return os;
		}
		
		
		//Check file Content
		String checkResult = UploadedFileValidator.validate(instance.getContentFile());
		if (checkResult != null)
		{
			logger.error(checkResult);
			os.setIsSuccessful(false);
			os.setFailureReason(checkResult);
			return os;
		}
		
		instance.getInterview().setContent(fileName + "cf");
		//Store the files;
		FileManager.saveAsFile(fileName + "cf", instance.getContentFile().getFileContent());
		
		//Check title image
		if (instance.getTitleImage() != null)
		{
			checkResult = UploadedFileValidator.validate(instance.getTitleImage());
			if (checkResult != null)
			{
				logger.error(checkResult);
				os.setIsSuccessful(false);
				os.setFailureReason(checkResult);
				return os;
			}
			instance.getInterview().setTitleImage(fileName + "ti");
			//Store the files;
			FileManager.saveAsFile(fileName + "ti", instance.getTitleImage().getFileContent());
		}
		//Check the content images
		if (instance.getContentImages() != null && instance.getContentImages().size() > 0)
		{
			for(int i = 0; i <instance.getContentImages().size(); i ++)
			{
				UploadedFile contentImage = instance.getContentImages().get(i);
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
					instance.getInterview().getImages().add(fileName + "i" + (i+1));
					//Store the files;
					FileManager.saveAsFile(fileName + "i" + (i+1), contentImage.getFileContent());
				}
			}
		}
		
		//OK, now we could save the Interview to dataStore;
		
		bookDbMngr.storeInterview(instance.getInterview());
		return os;
	}

	public OperationStatus updateBookNews(WebServiceBookNews instance) {
		OperationStatus os = new OperationStatus();
		if (instance == null || instance.getBookNews() == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input");
			return os;
		}
		//TODO some logic book
		bookDbMngr.updateBookNews(instance.getBookNews());
		return os;	
	}

	public OperationStatus updateBookReview(WebServiceBookReview instance) {
		OperationStatus os = new OperationStatus();
		if (instance == null || instance.getBookReview() == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input");
			return os;
		}
		//TODO some logic book
		bookDbMngr.updateBookReview(instance.getBookReview());
		return os;
	}

	public OperationStatus updateEvent(WebServiceEvent instance) {
		OperationStatus os = new OperationStatus();
		if (instance == null || instance.getEvent() == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input");
			return os;
		}
		//TODO some logic book
		bookDbMngr.updateEvent(instance.getEvent());
		return os;
	}

	public OperationStatus updateInterview(WebServiceInterview instance) {
		OperationStatus os = new OperationStatus();
		if (instance == null || instance.getInterview() == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input");
			return os;
		}
		//TODO some logic book
		bookDbMngr.updateInterview(instance.getInterview());
		return os;
	}


	
	public WebBookNewsListResult listBookNewses(
			WebServiceListParams listParams) {
		List<BookNews> list = (List<BookNews>) bookDbMngr.getAllBookNewses();
		WebBookNewsListResult result= new WebBookNewsListResult();
		
		result= (WebBookNewsListResult) WebListResultHelper.getSpecifiedPageList(list, listParams, result);
		//Eager get @ManytoOne, Null oneToMany;
		list = new ArrayList<BookNews>(0);
		
		//Eager get @ManytoOne, Null oneToMany;
		for(BookNews instance : result.getList())
		{
			list.add(instance.setToPlainObjectQuick(instance));
		}
		result.setList(list);
		
		return result;
	}

	public WebBookReviewListResult listBookReviews(
			WebServiceListParams listParams) {
		List<BookReview> list = (List<BookReview>) bookDbMngr.getAllBookReviews();
		WebBookReviewListResult result= new WebBookReviewListResult();
		
		result = (WebBookReviewListResult) WebListResultHelper.getSpecifiedPageList(list, listParams, result);
		
		list = new ArrayList<BookReview>();
		for(BookReview instance : result.getList())
		{
			list.add(instance.setToPlainObjectQuick(instance));
		}
		
		result.setList(list);
		
		return result;
	}

	public WebEventListResult listEvents(WebServiceListParams listParams) {
		List<Event> list = (List<Event>) bookDbMngr.getAllEvents();
		WebEventListResult result= new WebEventListResult();
		
		result = (WebEventListResult) WebListResultHelper.getSpecifiedPageList(list, listParams, result);
		
		list = new ArrayList<Event>(0);
		//Eager get @ManytoOne, Null oneToMany;
		for(Event instance : result.getList())
		{
			list.add(instance.setToPlainObjectQuick(instance));
		}
		
		result.setList(list);
		
		return result;
	}

	public WebInterviewListResult listInterviews(
			WebServiceListParams listParams) {
		List<Interview> interviews = (List<Interview>) bookDbMngr.getAllInterviews();
		WebInterviewListResult result= new WebInterviewListResult();
		
		result = (WebInterviewListResult) WebListResultHelper.getSpecifiedPageList(interviews, listParams, result);
		
		
		interviews = new ArrayList<Interview>(0);
		//Eager get @ManytoOne, Null oneToMany;
		for(Interview instance : result.getList())
		{
			interviews.add(instance.setToPlainObjectQuick(instance));
		}
		result.setList(interviews);
		
		return result;
	}

	
	
	public OperationStatus deleteBookNewsCategory(Long id) {
		OperationStatus os = new OperationStatus();
		if (id == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input Id");
			return os;
		}
		
		bookDbMngr.deleteBookNewsCategory(id);
		return os;
	}

	public OperationStatus deleteBookReviewCategory(Long id) {
		OperationStatus os = new OperationStatus();
		if (id == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input Id");
			return os;
		}
		
		bookDbMngr.deleteBookReviewCategory(id);
		return os;
	}

	public OperationStatus deleteEventCategory(Long id) {
		OperationStatus os = new OperationStatus();
		if (id == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input Id");
			return os;
		}
		
		bookDbMngr.deleteEventCategory(id);
		return os;
	}

	public OperationStatus deleteInterviewCategory(Long id) {
		OperationStatus os = new OperationStatus();
		if (id == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input Id");
			return os;
		}
		
		bookDbMngr.deleteInterviewCategory(id);
		return os;
	}

	public WebServiceBookNewsCategory getBookNewsCategoryById(Long id) {
		WebServiceBookNewsCategory result = new WebServiceBookNewsCategory();
		if (id == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Null input Id");
			return result;
		}
		BookNewsCategory instance = bookDbMngr.getBookNewsCategory(id);
		if (instance == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("BookNewsCategory not found by id: " + id);
			return result;
		}
		
		//Eager get @ManytoOne, Null oneToMany;
		instance.setToPlainObject(instance);
		
		result.setBookNewsCategory(instance);
		return result;
	}

	public WebServiceBookNewsCategory getBookNewsCategoryByName(String name) {
		WebServiceBookNewsCategory result = new WebServiceBookNewsCategory();
		if (name == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Null input Id");
			return result;
		}
		BookNewsCategory instance = bookDbMngr.getBookNewsCategoryByName(name);
		if (instance == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("BookNewsCategory not found by name: " + name);
			return result;
		}
		
		//Eager get @ManytoOne, Null oneToMany;
		instance.setToPlainObject(instance);
		//instance.getCurrentKeyPair();
		result.setBookNewsCategory(instance);
		return result;
	}

	public WebBookNewsCateListResult getBookNewsCategories(
			WebServiceListParams listParams) {
		
		List<BookNewsCategory> libs = (List<BookNewsCategory>) bookDbMngr.getAllBookNewsCategories();
		WebBookNewsCateListResult result= new WebBookNewsCateListResult();
		
		result = (WebBookNewsCateListResult) WebListResultHelper.getSpecifiedPageList(libs, listParams, result);
		
		//Eager get @ManytoOne, Null oneToMany;
		for(BookNewsCategory instance : result.getList())
		{
			instance.setToPlainObject(instance);
		}
		
		return result;
	}

	public WebServiceBookReviewCategory getBookReviewCategoryById(Long id) {
		WebServiceBookReviewCategory result = new WebServiceBookReviewCategory();
		if (id == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Null input Id");
			return result;
		}
		BookReviewCategory instance = bookDbMngr.getBookReviewCategory(id);
		if (instance == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("BookReviewCategory not found by id: " + id);
			return result;
		}
		
		//Eager get @ManytoOne, Null oneToMany;
		instance.setToPlainObject(instance);
		
		result.setBookReviewCategory(instance);
		return result;
	}

	public WebServiceBookReviewCategory getBookReviewCategoryByName(String name) {
		WebServiceBookReviewCategory result = new WebServiceBookReviewCategory();
		if (name == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Null input Id");
			return result;
		}
		BookReviewCategory instance = bookDbMngr.getBookReviewCategoryByName(name);
		if (instance == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("BookNewsCategory not found by name: " + name);
			return result;
		}
		
		//Eager get @ManytoOne, Null oneToMany;
		instance.setToPlainObject(instance);
		//instance.getCurrentKeyPair();
		result.setBookReviewCategory(instance);
		return result;
	}

	public WebBookReviewCateListResult getBookReviewCategories(
			WebServiceListParams listParams) {
		
		List<BookReviewCategory> libs = (List<BookReviewCategory>) bookDbMngr.getAllBookReviewCategories();
		WebBookReviewCateListResult result= new WebBookReviewCateListResult();
		
		result = (WebBookReviewCateListResult) WebListResultHelper.getSpecifiedPageList(libs, listParams, result);
		
		//Eager get @ManytoOne, Null oneToMany;
		for(BookReviewCategory instance : result.getList())
		{
			instance.setToPlainObject(instance);
		}
		
		return result;
	}

	public WebServiceEventCategory getEventCategoryById(Long id) {
		WebServiceEventCategory result = new WebServiceEventCategory();
		if (id == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Null input Id");
			return result;
		}
		EventCategory instance = bookDbMngr.getEventCategory(id);
		if (instance == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("EventCategory not found by id: " + id);
			return result;
		}
		
		//Eager get @ManytoOne, Null oneToMany;
		instance.setToPlainObject(instance);
		
		result.setEventCategory(instance);
		return result;
	}

	public WebServiceEventCategory getEventCategoryByName(String name) {
		WebServiceEventCategory result = new WebServiceEventCategory();
		if (name == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Null input Id");
			return result;
		}
		EventCategory instance = bookDbMngr.getEventCategoryByName(name);
		if (instance == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("BookNewsCategory not found by name: " + name);
			return result;
		}
		
		//Eager get @ManytoOne, Null oneToMany;
		instance.setToPlainObject(instance);
		//instance.getCurrentKeyPair();
		result.setEventCategory(instance);
		return result;
	}

	public WebEventCateListResult getEventCategories(WebServiceListParams listParams) {
		List<EventCategory> libs = (List<EventCategory>) bookDbMngr.getAllEventCategories();
		WebEventCateListResult result= new WebEventCateListResult();
		
		result = (WebEventCateListResult) WebListResultHelper.getSpecifiedPageList(libs, listParams, result);
		
		//Eager get @ManytoOne, Null oneToMany;
		for(EventCategory instance : result.getList())
		{
			instance.setToPlainObject(instance);
		}
		
		return result;
	}

	public WebServiceInterviewCategory getInterviewCategoryById(Long id) {
		WebServiceInterviewCategory result = new WebServiceInterviewCategory();
		if (id == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Null input Id");
			return result;
		}
		InterviewCategory instance = bookDbMngr.getInterviewCategory(id);
		if (instance == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("InterviewCategory not found by id: " + id);
			return result;
		}
		
		//Eager get @ManytoOne, Null oneToMany;
		instance.setToPlainObject(instance);
		
		result.setInterviewCategory(instance);
		return result;
	}

	public WebServiceInterviewCategory getInterviewCategoryByName(String name) {
		WebServiceInterviewCategory result = new WebServiceInterviewCategory();
		if (name == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Null input Id");
			return result;
		}
		InterviewCategory instance = bookDbMngr.getInterviewCategoryByName(name);
		if (instance == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("BookNewsCategory not found by name: " + name);
			return result;
		}
		
		//Eager get @ManytoOne, Null oneToMany;
		instance.setToPlainObject(instance);
		//instance.getCurrentKeyPair();
		result.setInterviewCategory(instance);
		return result;
	}

	public WebInterviewCateListResult getInterviewCategories(
			WebServiceListParams listParams) {
		List<InterviewCategory> libs = (List<InterviewCategory>) bookDbMngr.getAllInterviewCategories();
		WebInterviewCateListResult result= new WebInterviewCateListResult();
		
		result = (WebInterviewCateListResult) WebListResultHelper.getSpecifiedPageList(libs, listParams, result);
		
		//Eager get @ManytoOne, Null oneToMany;
		for(InterviewCategory instance : result.getList())
		{
			instance.setToPlainObject(instance);
		}
		
		return result;
	}

	public OperationStatus storeBookNewsCategory(
			WebServiceBookNewsCategory instance) {
		OperationStatus os = new OperationStatus();
		if (instance == null || instance.getBookNewsCategory() == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input");
			return os;
		}
		//TODO some logic book
		bookDbMngr.storeBookNewsCategory(instance.getBookNewsCategory());
		return os;
	}

	public OperationStatus storeBookReviewCategory(
			WebServiceBookReviewCategory instance) {
		OperationStatus os = new OperationStatus();
		if (instance == null || instance.getBookReviewCategory() == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input");
			return os;
		}
		//TODO some logic book
		bookDbMngr.storeBookReviewCategory(instance.getBookReviewCategory());
		return os;
	}

	public OperationStatus storeEventCategory(WebServiceEventCategory instance) {
		OperationStatus os = new OperationStatus();
		if (instance == null || instance.getEventCategory() == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input");
			return os;
		}
		//TODO some logic book
		bookDbMngr.storeEventCategory(instance.getEventCategory());
		return os;
	}

	public OperationStatus storeInterviewCategory(
			WebServiceInterviewCategory instance) {
		OperationStatus os = new OperationStatus();
		if (instance == null || instance.getInterviewCategory() == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input");
			return os;
		}
		//TODO some logic book
		bookDbMngr.storeInterviewCategory(instance.getInterviewCategory());
		return os;
	}

	public OperationStatus updateBookNewsCategory(
			WebServiceBookNewsCategory instance) {
		OperationStatus os = new OperationStatus();
		if (instance == null || instance.getBookNewsCategory() == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input");
			return os;
		}
		//TODO some logic book
		bookDbMngr.updateBookNewsCategory(instance.getBookNewsCategory());
		return os;
	}

	public OperationStatus updateBookReviewCategory(
			WebServiceBookReviewCategory instance) {
		
		OperationStatus os = new OperationStatus();
		if (instance == null || instance.getBookReviewCategory() == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input");
			return os;
		}
		//TODO some logic book
		bookDbMngr.updateBookReviewCategory(instance.getBookReviewCategory());
		return os;
	}

	public OperationStatus updateEventCategory(WebServiceEventCategory instance) {
		OperationStatus os = new OperationStatus();
		if (instance == null || instance.getEventCategory() == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input");
			return os;
		}
		//TODO some logic book
		bookDbMngr.updateEventCategory(instance.getEventCategory());
		return os;
	}

	public OperationStatus updateInterviewCategory(
			WebServiceInterviewCategory instance) {
		OperationStatus os = new OperationStatus();
		if (instance == null || instance.getInterviewCategory() == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input");
			return os;
		}
		//TODO some logic book
		bookDbMngr.updateInterviewCategory(instance.getInterviewCategory());
		return os;
	}

	public WebServiceBookNews getBookNewsByTitle(String title) {
		WebServiceBookNews result = new WebServiceBookNews();
		if (title == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Null input Id");
			return result;
		}
		BookNews instance = bookDbMngr.getBookNewsByTitle(title);
		if (instance == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("BookNews not found by title: " + title);
			return result;
		}
		
		//Eager get @ManytoOne, Null oneToMany;
		//instance.getCurrentKeyPair();
		result.setBookNews(instance.setToPlainObject(instance));
		return result;
	}

	public WebServiceBookReview getBookReviewByTitle(String title) {
		WebServiceBookReview result = new WebServiceBookReview();
		if (title == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Null input Id");
			return result;
		}
		BookReview instance = bookDbMngr.getBookReviewByTitle(title);
		if (instance == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("BookReview not found by title: " + title);
			return result;
		}
		
		//Eager get @ManytoOne, Null oneToMany;
		
		//instance.getCurrentKeyPair();
		result.setBookReview(instance.setToPlainObject(instance));
		return result;
	}

	public WebServiceEvent getEventByTitle(String title) {
		WebServiceEvent result = new WebServiceEvent();
		if (title == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Null input Id");
			return result;
		}
		Event instance = bookDbMngr.getEventByTitle(title);
		if (instance == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Event not found by title: " + title);
			return result;
		}
		
		//Eager get @ManytoOne, Null oneToMany;
		//instance.getCurrentKeyPair();
		result.setEvent(instance.setToPlainObject(instance));
		return result;
	}

	public WebServiceInterview getInterviewByTitle(String title) {
		WebServiceInterview result = new WebServiceInterview();
		if (title == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Null input Id");
			return result;
		}
		Interview instance = bookDbMngr.getInterviewByTitle(title);
		if (instance == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Event not found by title: " + title);
			return result;
		}
		
		//Eager get @ManytoOne, Null oneToMany;
		
		//instance.getCurrentKeyPair();
		result.setInterview(instance.setToPlainObject(instance));
		return result;
	}

	
	public OperationStatus deleteBookTag(Long id) {
		OperationStatus os = new OperationStatus();
		if (id == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input Id");
			return os;
		}
		
		bookDbMngr.deleteBookTag(id);
		return os;
	}

	public WebServiceBookTag getBookTagById(Long id) {
		WebServiceBookTag result = new WebServiceBookTag();
		if (id == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Null input Id");
			return result;
		}
		BookTag instance = bookDbMngr.getBookTag(id);
		if (instance == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("BookTag not found by id: " + id);
			return result;
		}
		
		//Eager get @ManytoOne, Null oneToMany;
		
		
		result.setBookTag(instance.setToPlainObject(instance));
		return result;
	}

	public WebServiceBookTag getBookTagByName(String name) {
		WebServiceBookTag result = new WebServiceBookTag();
		if (name == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Null input Id");
			return result;
		}
		BookTag instance = bookDbMngr.getBookTagByName(name);
		if (instance == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("BookTag not found by name: " + name);
			return result;
		}
		
		//Eager get @ManytoOne, Null oneToMany;
		
		//instance.getCurrentKeyPair();
		result.setBookTag(instance.setToPlainObject(instance));
		return result;
	}

	public OperationStatus storeBookTag(WebServiceBookTag instance) {
		OperationStatus os = new OperationStatus();
		if (instance == null || instance.getBookTag() == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input");
			return os;
		}
		
		//OK, now we could save the BookTag to dataStore;
		bookDbMngr.storeBookTag(instance.getBookTag());
		return os;
	}

	public OperationStatus updateBookTag(WebServiceBookTag instance) {
		OperationStatus os = new OperationStatus();
		if (instance == null || instance.getBookTag() == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input");
			return os;
		}
		//TODO some logic book
		bookDbMngr.updateBookTag(instance.getBookTag());
		return os;
	}
	
	public WebBookTagListResult listBookTags(WebServiceListParams listParams) {
		List<BookTag> list = (List<BookTag>) bookDbMngr.getAllBookTags();
		WebBookTagListResult result= new WebBookTagListResult();
		
		result = (WebBookTagListResult) WebListResultHelper.getSpecifiedPageList(list, listParams, result);
		
		list = new ArrayList<BookTag>(0);
		//Eager get @ManytoOne, Null oneToMany;
		for(BookTag instance : result.getList())
		{
			list.add(instance.setToPlainObjectQuick(instance));
		}
		
		result.setList(list);
		
		return result;
	}
	
	

	



}
