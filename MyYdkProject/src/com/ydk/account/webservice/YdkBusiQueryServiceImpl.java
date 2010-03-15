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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ydk.account.core.WebServiceAccountHelper;
import com.ydk.account.persistence.entity.Account;
import com.ydk.account.persistence.entity.BookMark;
import com.ydk.account.persistence.entity.FileDescriptor;
import com.ydk.account.persistence.entity.Transaction;
import com.ydk.account.persistence.entity.UploadedFile;
import com.ydk.account.persistence.interfaces.AccountDbMngr;
import com.ydk.account.persistence.operator.TransactionQuery;
import com.ydk.book.persistence.entity.Book;
import com.ydk.book.persistence.interfaces.BookDbMngr;
import com.ydk.book.webservice.WebBookListResult;
import com.ydk.book.webservice.WebBookMarkListResult;
import com.ydk.book.webservice.WebServiceListParams;
/**
 * @author y140zhan
 *
 */
@WebService(endpointInterface = "com.ydk.account.webservice.YdkBusiQueryService")
@Transactional
public final class YdkBusiQueryServiceImpl implements YdkBusiQueryService {
	
	protected final AccountDbMngr accountDbMngr;
	protected final BookDbMngr bookDbMngr;
	
	@Autowired
	public YdkBusiQueryServiceImpl(AccountDbMngr accountDbMngr, 
			BookDbMngr bookDbMngr) {
		this.accountDbMngr = accountDbMngr;
		this.bookDbMngr = bookDbMngr;
	}
	

	public WebTransactionListResult listTransactionByBook(Date startDate,
			Date endDate, Long bookId, WebServiceListParams listParams) {
		WebTransactionListResult result= new WebTransactionListResult();
		if (bookId == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("null bookId");
			return result;
		}
		
		List<Transaction> transactions = (List<Transaction>) 
			bookDbMngr.listTransactionByBook(startDate, endDate, bookId);

		
		result = (WebTransactionListResult) WebListResultHelper.getSpecifiedPageList(transactions, listParams, result);
		
		transactions = new ArrayList<Transaction>(0);
		//Eager get @ManytoOne, Null oneToMany;
		for(Transaction instance : result.getList())
		{
			transactions.add(instance.setToPlainObject(instance));
		}
		
		result.setList(transactions);
		
		return result;
		
	}

	public WebTransactionListResult listTransactionByPartner(Date startDate,
			Date endDate, Long partnerId, WebServiceListParams listParams) {
		WebTransactionListResult result= new WebTransactionListResult();
		if (partnerId == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("null partnerId");
			return result;
		}
		
		List<Transaction> transactions = (List<Transaction>) 
		bookDbMngr.listTransactionByPartner(startDate, endDate, partnerId);
	
		result = (WebTransactionListResult) WebListResultHelper.getSpecifiedPageList(transactions, listParams, result);
		
		transactions = new ArrayList<Transaction>(0);
		
		//Eager get @ManytoOne, Null oneToMany;
		for(Transaction instance : result.getList())
		{
			transactions.add(instance.setToPlainObjectQuick(instance));
		}
		
		result.setList(transactions);
		
		return result;
		
	}

	public WebTransactionListResult listTransactionByReader(Date startDate,
			Date endDate, Long readId, WebServiceListParams listParams) {
		
		WebTransactionListResult result= new WebTransactionListResult();
		if (readId == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("null readId");
			return result;
		}
		
		List<Transaction> transactions = (List<Transaction>) 
		bookDbMngr.listTransactionByReader(startDate, endDate, readId);
	
		result = (WebTransactionListResult) WebListResultHelper.getSpecifiedPageList(transactions, listParams, result);
		
		transactions = new ArrayList<Transaction>(0);
		
		//Eager get @ManytoOne, Null oneToMany;
		for(Transaction instance : result.getList())
		{
			transactions.add(instance.setToPlainObjectQuick(instance));
		}
		
		result.setList(transactions);
		
		return result;
	}
	
	

}
