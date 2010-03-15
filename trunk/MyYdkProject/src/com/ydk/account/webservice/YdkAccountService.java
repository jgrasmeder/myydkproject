/**
 * 
 */
package com.ydk.account.webservice;

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

import com.ydk.account.persistence.entity.Account;
import com.ydk.account.persistence.entity.FileDescriptor;
import com.ydk.account.persistence.entity.UploadedFile;

@WebService
public interface YdkAccountService {
    
    List<WebServiceAccount> getAllAccounts();
    
    /**
	 * Retrieve all accounts maxNumPerPage of <code>Account</code>s from the data store.
	 * @return Collection<Account>
	 * @throws 
	 */
    List<WebServiceAccount> getAccounts(@WebParam(name = "maxNumPerPage")Integer maxNumPerPage);
	
	/**
	 * Retrieve all <code>Account</code>s from the data store simillay with accountName,
	 * returning all accounts whose name <i>contains</i> with the given name.
	 * @param accountName
	 * @return Collection<Account>
	 * @throws DataAccessException
	 */
    List<WebServiceAccount> findAccounts(@WebParam(name = "accountName")String accountName);
	
	/**
	 * Retrieve <code>Account</code>s from the data store by last name,
	 * returning all accounts whose name <i>contains</i> with the given name.
	 * @param accountName
	 * @return Collection<Account>
	 * @throws DataAccessException
	 */
    List<WebServiceAccount> findLimitedAccounts(@WebParam(name = "accountName")String accountName,
    		@WebParam(name = "maxNumPerPage")Integer maxNumPerPage);

	/**
	 * Retrieve an <code>Account</code> from the data store by accountName.
	 * @param accountName the unique name to search for
	 * @return
	 * @throws org.springframework.dao.DataRetrievalFailureException if not
	 *         found
	 */
    WebServiceAccount getAccountByName(@WebParam(name = "accountName")String accountName) ;
	

	/**
	 * Retrieve an <code>Account</code> from the data store by accountId.
	 * @param accountId the unique id to search for
	 * @return
	 * @throws org.springframework.dao.DataRetrievalFailureException if not
	 *         found
	 */
    WebServiceAccount getAccount(@WebParam(name = "accountId")Long accountId);

	/**
	 * Save an <code>Account</code> to the data store, either inserting or
	 * updating it.
	 *
	 * @param account the <code>Account</code> to save
	 * 
	 */
	void saveAccount(@WebParam(name = "account")WebServiceAccount account);
	

	/**
	 * Delete an <code>Account</code> from the data store by accountName.
	 *
	 * @param account the <code>Account</code> to save
	 * 
	 */
	void deleteAccountByName(@WebParam(name = "accountName")String accountName);
	
	/**
	 * Delete an <code>Account</code> from the data store by account Id.
	 * @param accountId
	 * @throws DataAccessException
	 */
	void deleteAccount(@WebParam(name = "accountId")Long accountId);
	
	/**
	 * Retrieve an <code>UploadedFile</code> from the data store by fileId.
	 * @param fileId the unique id to search for
	 * @return
	 * @throws org.springframework.dao.DataRetrievalFailureException if not
	 *         found
	 */
	WebServiceAccount getUploadedFile(@WebParam(name = "fileId")Long fileId);

	/**
	 * Save an <code>UploadedFile</code> to the data store, either inserting or
	 * updating it.
	 *
	 * @param uploadedFile the <code>UploadedFile</code> to save
	 * 
	 */
	void storeUploadedFile(@WebParam(name = "uploadedFile")WebServiceAccount uploadedFile);
}
