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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ydk.account.core.WebServiceAccountHelper;
import com.ydk.account.persistence.entity.Account;
import com.ydk.account.persistence.entity.FileDescriptor;
import com.ydk.account.persistence.entity.UploadedFile;
import com.ydk.account.persistence.interfaces.AccountDbMngr;
/**
 * @author y140zhan
 *
 */
@WebService(endpointInterface = "com.ydk.account.webservice.YdkAccountService")
@Transactional
public final class YdkAccountServiceImpl implements YdkAccountService {
	
	protected final AccountDbMngr accountDbMngr;
	
	@Autowired
	public YdkAccountServiceImpl(AccountDbMngr accountDbMngr) {
		this.accountDbMngr = accountDbMngr;
	}

	
    public List<WebServiceAccount> getAllAccounts() {
    	List<Account> accounts = (List<Account>) accountDbMngr.getAccounts();
    	
        return WebServiceAccountHelper.wrapAccountList(accounts);
    }

	public void deleteAccountByName(String accountName) {
		// TODO Maybe we could user Thread pool here
		accountDbMngr.deleteAccount(accountName);
		
	}

	public void deleteAccount(Long accountId) {
		// TODO Auto-generated method stub
		accountDbMngr.deleteAccount(accountId);
	}

	public List<WebServiceAccount> findAccounts(String accountName) {
		// TODO Auto-generated method stub
		List<Account> accounts = (List<Account>) accountDbMngr.findAccounts(accountName);
		
		return WebServiceAccountHelper.wrapAccountList(accounts);
	}

	public List<WebServiceAccount> findLimitedAccounts(String accountName,
			Integer maxNumPerPage) {
		List<Account> accounts = (List<Account>) accountDbMngr.findAccounts(accountName, maxNumPerPage);
		
		return WebServiceAccountHelper.wrapAccountList(accounts);
	}

	public WebServiceAccount getAccountByName(String accountName) {
		
		return WebServiceAccountHelper.wrapAccount(accountDbMngr.getAccount(accountName));
	}

	public WebServiceAccount getAccount(Long accountId) {
		// TODO Auto-generated method stub
		return WebServiceAccountHelper.wrapAccount(accountDbMngr.getAccount(accountId));
	}

	public List<WebServiceAccount> getAccounts(Integer maxNumPerPage) {
		// TODO Auto-generated method stub
		List<Account> accounts = 
			(List<Account>) accountDbMngr.getAccounts(maxNumPerPage);
		
		return WebServiceAccountHelper.wrapAccountList(accounts);
	}

	public WebServiceAccount getUploadedFile(Long fileId) {
		return WebServiceAccountHelper.wrapUploadedFile(accountDbMngr.getUploadedFile(fileId));
	}

	public void saveAccount(WebServiceAccount account) {
		accountDbMngr.saveAccount(account.getAccount(), account.getUploadedFile());
		
	}

	public void storeUploadedFile(WebServiceAccount uploadedFile) {
		accountDbMngr.storeUploadedFile(uploadedFile.getUploadedFile());
		
	}
}
