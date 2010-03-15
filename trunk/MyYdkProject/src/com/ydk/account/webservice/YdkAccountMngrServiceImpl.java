/**
 * 
 */
package com.ydk.account.webservice;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.jws.WebService;
import javax.mail.util.ByteArrayDataSource;

import org.apache.cxf.aegis.type.java5.IgnoreProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ydk.account.core.WebServiceAccountHelper;
import com.ydk.account.persistence.entity.Account;
import com.ydk.account.persistence.entity.FileDescriptor;
import com.ydk.account.persistence.entity.UploadedFile;
import com.ydk.account.persistence.entity.YdkAccount;
import com.ydk.account.persistence.entity.YdkRole;
import com.ydk.account.persistence.interfaces.AccountDbMngr;
import com.ydk.book.persistence.entity.Book;
import com.ydk.book.webservice.OperationStatus;
import com.ydk.book.webservice.WebBookListResult;
import com.ydk.book.webservice.WebServiceBook;
import com.ydk.book.webservice.WebServiceListParams;
/**
 * @author y140zhan
 *
 */
@WebService(endpointInterface = "com.ydk.account.webservice.YdkAccountMngrService")
@Transactional
public final class YdkAccountMngrServiceImpl implements YdkAccountMngrService {
	
	protected final AccountDbMngr accountDbMngr;
	
	@Autowired
	public YdkAccountMngrServiceImpl(AccountDbMngr accountDbMngr) {
		this.accountDbMngr = accountDbMngr;
	}



	public WebYdkAccountListResult findYdkAccounts(String name, WebServiceListParams listRarams) {
		// TODO Auto-generated method stub
		List<YdkAccount> list = (List<YdkAccount>) accountDbMngr.findAllYdkAccounts(name);
		for (YdkAccount a : list)
		{
			a.getYdkRole().getMask();
			a.getYdkRole().setYdkAccounts(null);
		}
		return getSpecifiedPageList(list , listRarams, new WebYdkAccountListResult());
	}
	

	public WebYdkAccountListResult getYdkAccounts(WebServiceListParams listRarams) {
		// TODO Auto-generated method stub
		List<YdkAccount> accountList = (List<YdkAccount>) accountDbMngr.getAllYdkAccounts();
		for (YdkAccount a : accountList)
		{
			a.getYdkRole().getMask();
			a.getYdkRole().setYdkAccounts(null);
		}
		
		return getSpecifiedPageList(accountList , listRarams, new WebYdkAccountListResult());
	}
	
	public WebYdkRoleListResult getYdkRoles(WebServiceListParams listRarams) {
		// TODO Auto-generated method stub
		
		List<YdkRole> list = (List<YdkRole>)(accountDbMngr.getAllYdkRoles());
		//Lazy.get;
		for(YdkRole e: list)
		{
			e.setYdkAccounts(null);;
		}
		return getSpecifiedPageList(list , listRarams, new WebYdkRoleListResult());
	}
	
	public WebYdkRoleListResult getAllYdkRoles() {
		// TODO Auto-generated method stub
		WebYdkRoleListResult result = new WebYdkRoleListResult();
		result.setYdkRoles((List) new ArrayList(accountDbMngr.getAllYdkRoles()));
		
		//Lazy.get;
		for(YdkRole e: result.getYdkRoles())
		{
			e.setYdkAccounts(null);
		}
		result.setCurrentPage(1);
		result.setTotalItems(result.getYdkRoles().size());
		result.setTotalPages(1);
		return result;
	}
	
	public WebYdkRoleListResult getAllYdkRolesEager() {
		// TODO Auto-generated method stub
		WebYdkRoleListResult result = new WebYdkRoleListResult();
		result.setYdkRoles((List) new ArrayList(accountDbMngr.getAllYdkRoles()));
		
		//Lazy.get;
		for(YdkRole e: result.getYdkRoles())
		{
			e.getYdkAccounts().size();
		}
		result.setCurrentPage(1);
		result.setTotalItems(result.getYdkRoles().size());
		result.setTotalPages(1);
		return result;
	}
	
	
	public WebYdkAccountListResult getAllYdkAccounts() {
		// TODO Auto-generated method stub
		WebYdkAccountListResult result = new WebYdkAccountListResult();
		
		result.setYdkAccounts((List) accountDbMngr.getAllYdkAccounts());
		result.setCurrentPage(1);
		result.setTotalItems(result.getYdkAccounts().size());
		result.setTotalPages(1);
		for (YdkAccount a : result.getYdkAccounts())
		{
			a.getYdkRole().getMask();
			a.getYdkRole().setYdkAccounts(null);
		}
		return result;
	}
	
	
	
	public WebServiceYdkAccount getYdkAccountById(Long id) {
		// TODO Auto-generated method stub
		WebServiceYdkAccount result = new WebServiceYdkAccount();
		if (id == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Null input Id");
			return result;
		}
		YdkAccount instance = accountDbMngr.getYdkAccount(id);
		if (instance == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("YdkAccount not found by id: " + id);
			return result;
		}
		
		result.setYdkAccount(instance);
		//Eager get
		instance.getYdkRole().getMask();
		instance.getYdkRole().setYdkAccounts(null);
		return result;
	}
	


	public WebServiceYdkAccount getYdkAccountByName(String name) {
		// TODO Auto-generated method stub
		WebServiceYdkAccount result = new WebServiceYdkAccount();
		if (name == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("Null input Id");
			return result;
		}
		YdkAccount instance = accountDbMngr.getYdkAccountByName(name);
		if (instance == null)
		{
			result.getOs().setIsSuccessful(false);
			result.getOs().setFailureReason("YdkAccount not found by username: " + name);
			return result;
		}
		
		//Eager get
		instance.getYdkRole().getMask();
		instance.getYdkRole().setYdkAccounts(null);
		
		result.setYdkAccount(instance);
		return result;
	}







	public OperationStatus storeYdkAccount(WebServiceYdkAccount instance) {
		OperationStatus os = new OperationStatus();
		if (instance == null || instance.getYdkAccount() == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input");
			return os;
		}
		//TODO some logic book
		accountDbMngr.storeYdkAccount(instance.getYdkAccount());
		return os;
	}

	public OperationStatus updateYdkAccount(WebServiceYdkAccount instance) {
		OperationStatus os = new OperationStatus();
		if (instance == null || instance.getYdkAccount() == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input");
			return os;
		}
		//TODO some logic book
		accountDbMngr.updateYdkAccount(instance.getYdkAccount());
		return os;
	}
	
	public OperationStatus assignYdkRole(Long ydkRoleId, Long id) {
		// TODO Auto-generated method stub
		OperationStatus os = new OperationStatus();
		if (id == null || ydkRoleId == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input");
			return os;
		}
		YdkAccount ydkAccount = accountDbMngr.getYdkAccount(id);
		if (ydkAccount == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Could not find YdkAccount by id : " + id);
			return os;
		}
		
		YdkRole ydkRole = accountDbMngr.getYdkRole(id);
		if (ydkRole == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Could not find YdkRole by id : " + ydkRoleId);
			return os;
		}

		ydkAccount.setYdkRole(ydkRole);
		accountDbMngr.updateYdkAccount(ydkAccount);
		return os;
	}

	public OperationStatus deactiveYdkAccount(Long id) {
		// TODO Auto-generated method stub
		OperationStatus os = new OperationStatus();
		YdkAccount ydkAccount = accountDbMngr.getYdkAccount(id);
		
		if (ydkAccount == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Could not find YdkAccount by id : " + id);
			return os;
		}
		
		ydkAccount.setIsActived(false);
		accountDbMngr.updateYdkAccount(ydkAccount);
		return os;

	}

	public OperationStatus deleteYdkAccount(Long id) {
		// TODO Auto-generated method stub
		OperationStatus os = new OperationStatus();
		accountDbMngr.deleteYdkAccount(id);
		return os;
	}
	


	public OperationStatus storeYdkRole(WebServiceYdkRole instance) {
		OperationStatus os = new OperationStatus();
		if (instance == null || instance.getYdkRole() == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input");
			return os;
		}
		//TODO some logic book
		accountDbMngr.storeYdkRole(instance.getYdkRole());
		return os;
	}


	public OperationStatus updateYdkRole(WebServiceYdkRole instance) {
		OperationStatus os = new OperationStatus();
		if (instance == null || instance.getYdkRole() == null)
		{
			os.setIsSuccessful(false);
			os.setFailureReason("Null input");
			return os;
		}
		//TODO some logic book
		accountDbMngr.updateYdkRole(instance.getYdkRole());
		return os;
	}
	
	
	public OperationStatus deleteYdkRole(Long id) {
		OperationStatus os = new OperationStatus();
		accountDbMngr.deleteYdkRole(id);
		return os;
	}

	
	
	
	public OperationStatus renewYdkAccountKeyPair(Long id) {
		
		OperationStatus os = new OperationStatus();
		
		// TODO Auto-generated method stub
		
		return os;
		
	}
	
	
	public OperationStatus systemKeyPairBackup() {
		OperationStatus os = new OperationStatus();
		
		// TODO Auto-generated method stub
		
		return os;
		
	}
	
	

	public OperationStatus systemKeyPairRenew() {
		OperationStatus os = new OperationStatus();
		
		// TODO Auto-generated method stub
		
		return os;
		
	}

	public OperationStatus backupDb() {
		OperationStatus os = new OperationStatus();
		
		// TODO Auto-generated method stub
		
		return os;
		
	}
	
//	@IgnoreProperty
	public OperationStatus backupResources() {
		OperationStatus os = new OperationStatus();
		
		// TODO Auto-generated method stub
		
		return os;
		
	}
	
	
	private WebYdkAccountListResult getSpecifiedPageList(List list, WebServiceListParams listParams,
			WebYdkAccountListResult result)
	{
		int maxPageSize = list.size() < listParams.getPageSize()
		? list.size() : listParams.getPageSize();

		int totalPage = (list.size()%listParams.getPageSize() == 0
			? list.size()/maxPageSize
			: list.size()/maxPageSize + 1);
		int currentPage = totalPage < listParams.getPageOffset() 
		? totalPage : listParams.getPageOffset();
		result.setTotalItems(list.size());
		result.setTotalPages(totalPage);
		result.setCurrentPage(currentPage);
		result.setYdkAccounts(
				list.subList(
				listParams.getPageSize()* currentPage,
				listParams.getPageSize()* (1 + currentPage)
				)
				);
		return result;
	}
	
	
	private WebYdkRoleListResult getSpecifiedPageList(List list, WebServiceListParams listParams,
			WebYdkRoleListResult result)
	{
		int maxPageSize = list.size() < listParams.getPageSize()
		? list.size() : listParams.getPageSize();

		int totalPage = (list.size()%listParams.getPageSize() == 0
			? list.size()/maxPageSize
			: list.size()/maxPageSize + 1);
		int currentPage = totalPage < listParams.getPageOffset() 
		? totalPage : listParams.getPageOffset();
		result.setTotalItems(list.size());
		result.setTotalPages(totalPage);
		result.setCurrentPage(currentPage);
		result.setYdkRoles(
				list.subList(
				listParams.getPageSize()* currentPage,
				listParams.getPageSize()* (1 + currentPage)
				)
				);
		return result;
	}




}
