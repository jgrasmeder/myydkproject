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
import org.springframework.beans.support.PagedListHolder;
import org.springframework.transaction.annotation.Transactional;

import com.ydk.account.persistence.entity.Partner;
import com.ydk.account.persistence.entity.PartnerType;
import com.ydk.account.persistence.entity.Reader;
import com.ydk.account.persistence.entity.Transaction;
import com.ydk.account.persistence.entity.TransactionType;
import com.ydk.account.persistence.entity.UploadedFile;
import com.ydk.account.persistence.entity.YdkAccount;
import com.ydk.account.persistence.interfaces.AccountDbMngr;
import com.ydk.account.persistence.operator.TransactionQuery;
import com.ydk.account.validator.UploadedFileValidator;
import com.ydk.book.persistence.entity.Book;
import com.ydk.book.persistence.entity.BookNews;
import com.ydk.book.persistence.entity.BookReview;
import com.ydk.book.persistence.entity.Event;
import com.ydk.book.persistence.entity.Interview;
import com.ydk.book.persistence.interfaces.BookDbMngr;
import com.ydk.book.webservice.OperationStatus;
import com.ydk.book.webservice.WebBookCateListResult;
import com.ydk.book.webservice.WebBookListResult;
import com.ydk.book.webservice.WebBookMarkListResult;
import com.ydk.book.webservice.WebBookNewsListResult;
import com.ydk.book.webservice.WebBookReviewListResult;
import com.ydk.book.webservice.WebEventListResult;
import com.ydk.book.webservice.WebInterviewListResult;
import com.ydk.book.webservice.WebLibraryListResult;
import com.ydk.book.webservice.WebServiceBook;
import com.ydk.book.webservice.WebServiceBookNews;
import com.ydk.book.webservice.WebServiceBookReview;
import com.ydk.book.webservice.WebServiceCategory;
import com.ydk.book.webservice.WebServiceEvent;
import com.ydk.book.webservice.WebServiceInterview;
import com.ydk.book.webservice.WebServiceLibrary;
import com.ydk.book.webservice.WebServiceListParams;
import com.ydk.web.FileManager;
import com.ydk.web.KeyManager;
/**
 * @author y140zhan
 *
 */
@WebService(endpointInterface = "com.ydk.account.webservice.YdkTransactionMngrService")
@Transactional
public final class YdkTransactionMngrServiceImpl implements YdkTransactionMngrService {
	
	private final Logger logger = Logger.getLogger(YdkSubjectMngrServiceImpl.class.getName());
	protected final AccountDbMngr accountDbMngr;
	protected final BookDbMngr bookDbMngr;
	
	@Autowired
	protected  KeyManager keyManager;
	
	
	public void setKeyManager(KeyManager keyManager)
	{
		this.keyManager = keyManager;
	}
	
	@Autowired
	public YdkTransactionMngrServiceImpl(AccountDbMngr accountDbMngr, 
			BookDbMngr bookDbMngr) {
		this.accountDbMngr = accountDbMngr;
		this.bookDbMngr = bookDbMngr;
	}
	
	



	public OperationStatus deleteTransaction(Long id) {
		OperationStatus os = new OperationStatus();
		if (id == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input Id");
			return os;
		}
		
		accountDbMngr.deleteTransaction(id);
		return os;
	}
	
	public WebServiceTransaction getTransactionById(Long id) {
		WebServiceTransaction result = new WebServiceTransaction();
		if (id == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Null input Id");
			return result;
		}
		
		Transaction instance= accountDbMngr.getTransaction(id);
		if (instance == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Not found");
			return result;
		}
		
		
		
		
		result.setTransaction(instance.setToPlainObject(instance));
		return result;
	}



	public OperationStatus storeTransaction(WebServiceTransaction instance) {
		OperationStatus os = new OperationStatus();
		if (instance == null || instance.getTransaction() == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input Id");
			return os;
		}
		accountDbMngr.storeTransaction(instance.getTransaction());
		return os;
	}

	public OperationStatus updateTransaction(WebServiceTransaction instance) {
		OperationStatus os = new OperationStatus();
		if (instance == null || instance.getTransaction() == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input Id");
			return os;
		}
		accountDbMngr.updateTransaction(instance.getTransaction());
		return os;
	}

	public WebTransactionListResult getAllTransactions(WebServiceListParams listParams) {
		List<Transaction> list = (List<Transaction>) accountDbMngr.getAllTransactions();
		WebTransactionListResult result= new WebTransactionListResult();
		
		result = (WebTransactionListResult) WebListResultHelper.getSpecifiedPageList(
				list, listParams, result);
		
		list = new ArrayList<Transaction>(0);
		//Eager get @ManytoOne, Null oneToMany;
		for(Transaction instance : result.getList())
		{
			list.add(instance.setToPlainObject(instance));
		}
		result.setList(list);
		
		return result;
		
	}
	
	
	
//	public WebServiceTransaction getTransactionByName(String name) {
//		// TODO Auto-generated method stub
//		WebServiceTransaction result = new WebServiceTransaction();
//		if (name == null)
//		{
//			result.getOs().setIsSuccessful(false);
//			result.getOs().setFailureReason("Null input Id");
//			return result;
//		}
//		Transaction instance = accountDbMngr.getTransactionByName(name);
//		if (instance == null)
//		{
//			result.getOs().setIsSuccessful(false);
//			result.getOs().setFailureReason("YdkAccount not found by username: " + name);
//			return result;
//		}
//		
//		//Eager get @ManytoOne, Null oneToMany;
//		instance.setToPlainObject(instance);
//		
//		//instance.getCurrentKeyPair();
//		
//		result.setTransaction(instance);
//		return result;
//	}

	public OperationStatus deleteTransactionType(Long id) {
		OperationStatus os = new OperationStatus();
		if (id == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input Id");
			return os;
		}
		
		accountDbMngr.deleteTransactionType(id);
		return os;
	}


	public WebTransactionTypeListResult getAllTransactionTypes(
			WebServiceListParams listParams) {
		
		List<TransactionType> list = (List<TransactionType>) accountDbMngr.getAllTransactionTypes();
		WebTransactionTypeListResult result= new WebTransactionTypeListResult();
		
		result = (WebTransactionTypeListResult) WebListResultHelper.getSpecifiedPageList(
				list, listParams, result);
		
		list = new ArrayList<TransactionType>(0);
		//Eager get @ManytoOne, Null oneToMany;
		for(TransactionType instance : result.getList())
		{
			list.add(instance.setToPlainObject(instance));
		}
		result.setList(list);
		
		return result;
	}


	public WebServiceTransactionType getTransactionTypeById(Long id) {
		WebServiceTransactionType result = new WebServiceTransactionType();
		if (id == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Null input Id");
			return result;
		}
		
		TransactionType instance= accountDbMngr.getTransactionType(id);
		if (instance == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Not found");
			return result;
		}
		
		//Eager get @ManytoOne, 

		//Null oneToMany;
		
		result.setTransactionType(instance.setToPlainObject(instance));
		return result;
	}


	public WebServiceTransactionType getTransactionTypeByName(String name) {
		// TODO Auto-generated method stub
		WebServiceTransactionType result = new WebServiceTransactionType();
		if (name == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Null input Id");
			return result;
		}
		TransactionType instance = accountDbMngr.getTransactionTypeByName(name);
		if (instance == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("YdkAccount not found by username: " + name);
			return result;
		}
		
		//Eager get @ManytoOne, 

		//Null oneToMany;
		
		result.setTransactionType(instance.setToPlainObject(instance));
		return result;
	}


	public OperationStatus storeTransactionType(WebServiceTransactionType instance) {
		OperationStatus os = new OperationStatus();
		if (instance == null || instance.getTransactionType() == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input");
			return os;
		}
		accountDbMngr.storeTransactionType(instance.getTransactionType());
		return os;
	}


	public OperationStatus updateTransactionType(WebServiceTransactionType instance) {
		OperationStatus os = new OperationStatus();
		if (instance == null || instance.getTransactionType() == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input");
			return os;
		}
		accountDbMngr.updateTransactionType(instance.getTransactionType());
		return os;
	}







	



}
