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
@WebService(endpointInterface = "com.ydk.account.webservice.YdkPartnerMngrService")
@Transactional
public final class YdkPartnerMngrServiceImpl implements YdkPartnerMngrService {
	
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
	public YdkPartnerMngrServiceImpl(AccountDbMngr accountDbMngr, 
			BookDbMngr bookDbMngr) {
		this.accountDbMngr = accountDbMngr;
		this.bookDbMngr = bookDbMngr;
	}
	
	

	public OperationStatus deactivePartner(Long id) {
		OperationStatus os = new OperationStatus();
		if (id == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input Id");
			return os;
		}
		
		Partner instance= accountDbMngr.getPartner(id);
		if (instance == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Not found");
			return os;
		}
		
		instance.setIsActived(false);
		accountDbMngr.storePartner(instance);
		return os;
	}

	public OperationStatus deletePartner(Long id) {
		OperationStatus os = new OperationStatus();
		if (id == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input Id");
			return os;
		}
		
		accountDbMngr.deletePartner(id);
		return os;
	}
	
	public WebServicePartner getPartnerById(Long id) {
		WebServicePartner result = new WebServicePartner();
		if (id == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Null input Id");
			return result;
		}
		
		Partner instance= accountDbMngr.getPartner(id);
		if (instance == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Not found");
			return result;
		}
		
		//Eager get @ManytoOne, 
		instance.getPartnerType().getType();
		//Null oneToMany;
		instance.getPartnerType().setPartners(null);
		instance.setReaders(null);
		instance.setTransactions(null);
		
		
		result.setPartner(instance);
		return result;
	}

	public OperationStatus renewPartnerKeyPair(Long id) {
		OperationStatus os = new OperationStatus();
		if (id == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input Id");
			return os;
		}
		return os;
	}

	public OperationStatus storePartner(WebServicePartner instance) {
		OperationStatus os = new OperationStatus();
		if (instance == null || instance.getPartner() == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input Id");
			return os;
		}
		accountDbMngr.storePartner(instance.getPartner());
		return os;
	}

	public OperationStatus updatePartner(WebServicePartner instance) {
		OperationStatus os = new OperationStatus();
		if (instance == null || instance.getPartner() == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input Id");
			return os;
		}
		accountDbMngr.updatePartner(instance.getPartner());
		return os;
	}

	public WebPartnerListResult getAllPartners(WebServiceListParams listParams) {
		List<Partner> list = (List<Partner>) accountDbMngr.getAllPartners();
		WebPartnerListResult result= new WebPartnerListResult();
		
		result = (WebPartnerListResult) WebListResultHelper.getSpecifiedPageList(
				list, listParams, result);
		
		list = new ArrayList<Partner>(0);
		//Eager get @ManytoOne, Null oneToMany;
		for(Partner instance : result.getList())
		{
			list.add(instance.setToPlainObjectQuick(instance));
		}
		result.setList(list);
		
		return result;
		
	}
	
	
	
	public WebServicePartner getPartnerByName(String name) {
		// TODO Auto-generated method stub
		WebServicePartner result = new WebServicePartner();
		if (name == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Null input Id");
			return result;
		}
		Partner instance = accountDbMngr.getPartnerByName(name);
		if (instance == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("YdkAccount not found by username: " + name);
			return result;
		}
		
		//Eager get @ManytoOne, Null oneToMany;
		
		
		//instance.getCurrentKeyPair();
		
		result.setPartner(instance.setToPlainObject(instance));
		return result;
	}

	public OperationStatus deletePartnerType(Long id) {
		OperationStatus os = new OperationStatus();
		if (id == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input Id");
			return os;
		}
		
		accountDbMngr.deletePartnerType(id);
		return os;
	}


	public WebPartnerTypeListResult getAllPartnerTypes(
			WebServiceListParams listParams) {
		
		List<PartnerType> list = (List<PartnerType>) accountDbMngr.getAllPartnerTypes();
		WebPartnerTypeListResult result= new WebPartnerTypeListResult();
		
		result = (WebPartnerTypeListResult) WebListResultHelper.getSpecifiedPageList(
				list, listParams, result);
		
		
		//Eager get @ManytoOne, Null oneToMany;
		for(PartnerType instance : result.getList())
		{
			instance.setPartners(null);
		}
		
		return result;
	}


	public WebServicePartnerType getPartnerTypeById(Long id) {
		WebServicePartnerType result = new WebServicePartnerType();
		if (id == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Null input Id");
			return result;
		}
		
		PartnerType instance= accountDbMngr.getPartnerType(id);
		if (instance == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Not found");
			return result;
		}
		
		//Eager get @ManytoOne, 

		//Null oneToMany;
		instance.setPartners(null);
		
		result.setPartnerType(instance);
		return result;
	}


	public WebServicePartnerType getPartnerTypeByName(String name) {
		// TODO Auto-generated method stub
		WebServicePartnerType result = new WebServicePartnerType();
		if (name == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Null input Id");
			return result;
		}
		PartnerType instance = accountDbMngr.getPartnerTypeByName(name);
		if (instance == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("YdkAccount not found by username: " + name);
			return result;
		}
		
		//Eager get @ManytoOne, 

		//Null oneToMany;
		instance.setPartners(null);
		
		result.setPartnerType(instance);
		return result;
	}


	public OperationStatus storePartnerType(WebServicePartnerType instance) {
		OperationStatus os = new OperationStatus();
		if (instance == null || instance.getPartnerType() == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input");
			return os;
		}
		accountDbMngr.storePartnerType(instance.getPartnerType());
		return os;
	}


	public OperationStatus updatePartnerType(WebServicePartnerType instance) {
		OperationStatus os = new OperationStatus();
		if (instance == null || instance.getPartnerType() == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input");
			return os;
		}
		accountDbMngr.updatePartnerType(instance.getPartnerType());
		return os;
	}



	public OperationStatus deactiveReader(Long id) {
		OperationStatus os = new OperationStatus();
		if (id == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input Id");
			return os;
		}
		
		Reader instance= accountDbMngr.getReader(id);
		if (instance == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Not found");
			return os;
		}
		
		instance.setIsActived(false);
		accountDbMngr.storeReader(instance);
		return os;
	}



	public OperationStatus deleteReader(Long id) {
		OperationStatus os = new OperationStatus();
		if (id == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input Id");
			return os;
		}
		
		accountDbMngr.deleteReader(id);
		return os;
	}



	public WebServiceReader getReaderById(Long id) {
		WebServiceReader result = new WebServiceReader();
		if (id == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Null input Id");
			return result;
		}
		
		Reader instance= accountDbMngr.getReader(id);
		if (instance == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Not found");
			return result;
		}
		
		//Eager get @ManytoOne, 
		result.setReader(instance.setToPlainObject(instance));
		return result;
	}



	public WebServiceReader getReaderByName(String name) {
		// TODO Auto-generated method stub
		WebServiceReader result = new WebServiceReader();
		if (name == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Null input Id");
			return result;
		}
		Reader instance = accountDbMngr.getReaderByName(name);
		if (instance == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Reader not found by name: " + name);
			return result;
		}
		
		
		result.setReader(instance.setToPlainObject(instance));
		return result;
	}



	public WebReaderListResult getReaders(WebServiceListParams listParams) {
		List<Reader> list = (List<Reader>) accountDbMngr.getAllReaders();
		WebReaderListResult result= new WebReaderListResult();
		
		result = (WebReaderListResult) WebListResultHelper.getSpecifiedPageList(
				list, listParams, result);
		

		//Eager get @ManytoOne, Null oneToMany;
		//Solve the lasy initialization
		List<Reader> resultlist = (List<Reader>) result.getList();
		result.setList(new ArrayList<Book>(0));
		//Eager get @ManytoOne, Null oneToMany;
		for(Reader instance : resultlist)
		{
			result.getList().add(instance.setToPlainObject(instance));
		}
		
		return result;
	}



	public OperationStatus renewReaderKeyPair(Long id) {
		OperationStatus os = new OperationStatus();
		if (id == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input Id");
			return os;
		}
		return os;
	}



	public OperationStatus storeReader(WebServiceReader instance) {
		OperationStatus os = new OperationStatus();
		if (instance == null || instance.getReader() == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input");
			return os;
		}
		
		
		//TODO some logic Reader
		//Here we need to store the file/images to specific path
		//and add the fileDescriptor to instance.
		String fileName = FileManager.generateFileName(FileManager.FILE_KEY_READER);
		if (fileName == null)
		{
			logger.error("Could not generate File name");
			os.setIsSuccessful(false);
			os.setFailureReason("Could not generate File name");
			return os;
		}
		
		
		//Check file Content
		String checkResult = null;
		
		//Check title image
		if (instance.getPortraitImage() != null)
		{
			checkResult = UploadedFileValidator.validate(instance.getPortraitImage());
			if (checkResult != null)
			{
				logger.error(checkResult);
				os.setIsSuccessful(false);
				os.setFailureReason(checkResult);
				return os;
			}
			instance.getReader().setPortrait(fileName + "pi");
			//Store the files;
			FileManager.saveAsFile(fileName + "pi", instance.getPortraitImage().getFileContent());
		}
		
		//Add additional infor to Reader;
		instance.getReader().setRegisterDate(new Date());
		//Generate Key Pair.
		instance.getReader().setCurrentKeyPair(keyManager.generateKeyPair(new Date()));
		instance.getReader().setLastKeyPair(null);
		
		//OK
		accountDbMngr.storeReader(instance.getReader());
		return os;
		
	}



	public OperationStatus updateReader(WebServiceReader instance) {
		OperationStatus os = new OperationStatus();
		if (instance == null || instance.getReader() == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input Id");
			return os;
		}
		accountDbMngr.updateReader(instance.getReader());
		return os;
	}




	



}
