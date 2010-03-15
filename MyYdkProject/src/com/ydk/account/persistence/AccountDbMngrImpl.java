
package com.ydk.account.persistence;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import com.ydk.account.persistence.entity.*;
import com.ydk.account.persistence.interfaces.AccountDbMngr;
import com.ydk.account.persistence.operator.*;
import com.ydk.book.persistence.entity.Author;
import com.ydk.book.persistence.entity.Book;
import com.ydk.book.persistence.operator.AuthorEntity;
import com.ydk.book.persistence.operator.AuthorQuery;
import com.ydk.book.persistence.operator.BookEntity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * JPA implementation of the Ydk Db interface using EntityManager.
 * <p>
 * The mappings are defined in "orm.xml" located in the META-INF directory.
 *
 * @author Zhang Yu
 */
@Repository
@Transactional
public class AccountDbMngrImpl implements AccountDbMngr{

	/** Logger that is available to subclasses
	private final Log logger = LogFactory.getLog(getClass().getName());
	 */
	
	@PersistenceContext
	private EntityManager em;
	
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}
	
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Account> findAccounts(String accountName)
			throws DataAccessException {
		// TODO Auto-generated method stub
		AccountQuery query = new AccountQuery(em);
		query.searchAccountByField("accountName", accountName);
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Account> findAccounts(String accountName, Integer maxNumPerPage)
		throws DataAccessException {
		// TODO Auto-generated method stub
		AccountQuery query = new AccountQuery(em);
		query.setMaxResults(maxNumPerPage);
		query.searchAccountByField("accountName", accountName);
		return query.getResultList();
	}

	@Transactional(readOnly = true)
	public Account getAccount(String accountName) throws DataAccessException {
		// TODO Auto-generated method stub
		AccountQuery query = new AccountQuery(em, accountName);
		List<Account> entries = query.getResultList();
		if (entries.size() == 0)
		{
			return null;
		}
		else
		{
			//There should be only one entry matching.
			for (Account account : entries)
			{
				if (accountName.equals(account.getAccountName()))
				{
					return account;
				}
			}
			// Not Found
			return null;
		}
	}
	

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Account> getAccounts() throws DataAccessException {
		// TODO Auto-generated method stub
		AccountQuery query = new AccountQuery(em);
		
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Account> getAccounts(Integer maxNumPerPage)
	throws DataAccessException {
		AccountQuery query = new AccountQuery(em);
		query.setMaxResults(maxNumPerPage);
		return query.getResultList();
	}

	
	@Transactional(readOnly = true)
	public Account getAccount(Long accountId) throws DataAccessException {
		AccountEntity entity = new AccountEntity(em);
		return entity.loadAccount(accountId);
	}

	/**
	 * @see AccountDbMngr#saveAccount
	 */
	public void saveAccount(Account account) throws DataAccessException {
		//store account or update account
		if (account.isNew())
		{
			//store account
			//0. The user of DbMngr should ensure there is no 
			//	account name confliction, else DataAccessException is thrown
			//1.  store account;
			
			AccountEntity entity = new AccountEntity(em);
			entity.setInstance(account);
			entity.persist();
		
		}
		else
		{
			//0. The user of DbMngr should ensure the account is valid,
			//else DataAccessException is thrown
			//update account
			AccountEntity entity = new AccountEntity(em, account.getId());
			entity.setInstance(account);
			entity.update();
		}
		
	}
	
	public void deleteAccount(String accountName) throws DataAccessException {
		// TODO Auto-generated method stub
		Account account = getAccount(accountName);
		deleteAccount(account.getId());
		
	}

	public void deleteAccount(Long accountId) throws DataAccessException {
		// TODO Auto-generated method stub
		
		AccountEntity entity = new AccountEntity(em, accountId);
		
		//First We need to delete the image
		if(entity.getInstance().getImage().getFileId() != null)
		{
			deleteUploadedFile(entity.getInstance().getImage().getFileId());
		}
		entity.remove();
	}


	public Collection<Account> getBooks() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}


	public void saveBook(Book book) throws DataAccessException {
		// TODO Auto-generated method stub
		//Finish checking, do the business
		BookEntity be = new BookEntity(em);
		be.setInstance(book);
		be.persist();
	}
	
	@Transactional(readOnly = true)
	public FileDescriptor getAccountImg(Long imgId) throws DataAccessException {
		FileDescriptorEntity entity = new FileDescriptorEntity(em);
		return entity.loadFile(imgId);
	}


	public UploadedFile getUploadedFile(Long fileId) throws DataAccessException {
		// TODO Auto-generated method stub
		UploadedFileEntity entity = new UploadedFileEntity(em);
		return entity.loadFile(fileId);
	}
	
	public void deleteUploadedFile(Long fileId) throws DataAccessException {
		// TODO Auto-generated method stub
		UploadedFileEntity entity = new UploadedFileEntity(em, fileId);
		entity.remove();
	}


	public void storeUploadedFile(UploadedFile uploadedFile)
			throws DataAccessException {
		// TODO Auto-generated method stub
		UploadedFileEntity entity = new UploadedFileEntity(em);
		entity.setInstance(uploadedFile);
		entity.persist();
	}
	
	public void updateUploadedFile(UploadedFile uploadedFile)
		throws DataAccessException {
		// TODO Auto-generated method stub
		UploadedFileEntity entity = new UploadedFileEntity(em);
		entity.setInstance(uploadedFile);
		entity.update();
	}
	
	/**
	 * @see AccountDbMngr#saveAccount
	 */
	public void saveAccount(Account account, UploadedFile uploadedFile) throws DataAccessException {
		//store account or update account
		if (account.isNew())
		{
			//First we need to store uploadedFile;
			if (uploadedFile != null)
			{
				storeUploadedFile(uploadedFile);
				account.getImage().setFileId(uploadedFile.getId());
			}
			//store account
			//0. The user of DbMngr should ensure there is no 
			//	account name confliction, else DataAccessException is thrown
			//1.  store account;
			
			AccountEntity entity = new AccountEntity(em);
			entity.setInstance(account);
			entity.persist();
		
		}
		else
		{
			//First we need to delete the old image and then store new image;
			if (uploadedFile != null)
			{
				if (account.getImage().getFileId() != null)
				{
					uploadedFile.setId(account.getImage().getFileId());
					updateUploadedFile(uploadedFile);
				}
			}
			//0. The user of DbMngr should ensure the account is valid,
			//else DataAccessException is thrown
			//update account
			AccountEntity entity = new AccountEntity(em, account.getId());
			entity.setInstance(account);
			entity.update();
		}
		
	}
	
	
	/**
	 * Retrieve all <code>PartnerType</code>s from the data store.
	 * @return Collection<PartnerType>
	 * @throws DataAccessException
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<PartnerType> getAllPartnerTypes() throws DataAccessException
	{
		PartnerTypeQuery query = new PartnerTypeQuery(em);
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<PartnerType> getLimitedPartnerTypes(Integer maxNumPerPage) throws DataAccessException
	{
		PartnerTypeQuery query = new PartnerTypeQuery(em);
		query.setMaxResults(maxNumPerPage);
		return query.getResultList();
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<PartnerType> findAllPartnerTypes(String type) throws DataAccessException
	{
		PartnerTypeQuery query = new PartnerTypeQuery(em);
		query.searchPartnerTypeByField("type", type);
		return query.getResultList();
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<PartnerType> findLimitedPartnerTypes(String type, Integer maxNumPerPage) throws DataAccessException
	{
		PartnerTypeQuery query = new PartnerTypeQuery(em);
		query.searchPartnerTypeByField("type", type);
		query.setMaxResults(maxNumPerPage);
		return query.getResultList();
	}
	/**
	 * CRUD
	 */
	@Transactional(readOnly = true)
	public PartnerType getPartnerType(Object id) throws DataAccessException
	{
		PartnerTypeEntity entity = new PartnerTypeEntity(em);
		return entity.loadEntity(id);
	}
	
	public void storePartnerType(PartnerType partnerType) throws DataAccessException
	{
		PartnerTypeEntity entity = new PartnerTypeEntity(em);
		entity.setInstance(partnerType);
		entity.persist();
	}
	public void updatePartnerType(PartnerType partnerType) throws DataAccessException
	{
		PartnerTypeEntity entity = new PartnerTypeEntity(em);
		entity.setInstance(partnerType);
		entity.update();
	}
	public void deletePartnerType(Object id) throws DataAccessException
	{
		PartnerTypeEntity entity = new PartnerTypeEntity(em);
		if (entity.loadEntity(id) != null)
		{
			entity.remove();
		}
	}
	
	
	/**
	 * Retrieve all <code>Partner</code>s from the data store.
	 * @return Collection<Partner>
	 * @throws DataAccessException
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Partner> getAllPartners() throws DataAccessException
	{
		PartnerQuery query = new PartnerQuery(em);
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Partner> getLimitedPartners(Integer maxNumPerPage) throws DataAccessException
	{
		PartnerQuery query = new PartnerQuery(em);
		query.setMaxResults(maxNumPerPage);
		return query.getResultList();
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Partner> findAllPartners(Object searchKey) throws DataAccessException
	{
		return null;
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Partner> findLimitedPartners(Object searchKey, Integer maxNumPerPage) throws DataAccessException
	{
		return null;
	}
	/**
	 * CRUD
	 */
	@Transactional(readOnly = true)
	public Partner getPartner(Object id) throws DataAccessException
	{
		PartnerEntity entity = new PartnerEntity(em);
		return entity.loadEntity(id);
	}
	
	public void storePartner(Partner instance) throws DataAccessException
	{
		PartnerEntity entity = new PartnerEntity(em);
		entity.setInstance(instance);
		entity.persist();
	}
	public void updatePartner(Partner instance) throws DataAccessException
	{
		PartnerEntity entity = new PartnerEntity(em);
		entity.setInstance(instance);
		entity.update();
	}
	public void deletePartner(Object id) throws DataAccessException
	{
		PartnerEntity entity = new PartnerEntity(em);
		if (entity.loadEntity(id) != null)
		{
			entity.remove();
		}
	}
	
	
	/**
	 * Retrieve all <code>Reader</code>s from the data store.
	 * @return Collection<Reader>
	 * @throws DataAccessException
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Reader> getAllReaders() throws DataAccessException
	{
		ReaderQuery query = new ReaderQuery(em);
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Reader> getLimitedReaders(Integer maxNumPerPage) throws DataAccessException
	{
		ReaderQuery query = new ReaderQuery(em);
		query.setMaxResults(maxNumPerPage);
		return query.getResultList();
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Reader> findAllReaders(Object searchKey) throws DataAccessException
	{
		return null;
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Reader> findLimitedReaders(Object searchKey, Integer maxNumPerPage) throws DataAccessException
	{
		return null;
	}
	/**
	 * CRUD
	 */
	@Transactional(readOnly = true)
	public Reader getReader(Object id) throws DataAccessException
	{
		ReaderEntity entity = new ReaderEntity(em);
		return entity.loadEntity(id);
	}
	
	public void storeReader(Reader instance) throws DataAccessException
	{
		ReaderEntity entity = new ReaderEntity(em);
		entity.setInstance(instance);
		entity.persist();
	}
	public void updateReader(Reader instance) throws DataAccessException
	{
		ReaderEntity entity = new ReaderEntity(em);
		entity.setInstance(instance);
		entity.update();
	}
	public void deleteReader(Object id) throws DataAccessException
	{
		ReaderEntity entity = new ReaderEntity(em);
		if (entity.loadEntity(id) != null)
		{
			entity.remove();
		}
	}
	
	
	/**
	 * Retrieve all <code>Key</code>s from the data store.
	 * @return Collection<Key>
	 * @throws DataAccessException
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Key> getAllKeys() throws DataAccessException
	{
		KeyQuery query = new KeyQuery(em);
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Key> getLimitedKeys(Integer maxNumPerPage) throws DataAccessException
	{
		KeyQuery query = new KeyQuery(em);
		query.setMaxResults(maxNumPerPage);
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Key> findAllKeys(Object searchKey) throws DataAccessException
	{
		return null;
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Key> findLimitedKeys(Object searchKey, Integer maxNumPerPage) throws DataAccessException
	{
		return null;
	}
	/**
	 * CRUD
	 */
	@Transactional(readOnly = true)
	public Key getKey(Object id) throws DataAccessException
	{
		KeyEntity entity = new KeyEntity(em);
		return entity.loadEntity(id);
	}
	
	public void storeKey(Key instance) throws DataAccessException
	{
		KeyEntity entity = new KeyEntity(em);
		entity.setInstance(instance);
		entity.persist();
	}
	public void updateKey(Key instance) throws DataAccessException
	{
		KeyEntity entity = new KeyEntity(em);
		entity.setInstance(instance);
		entity.update();
	}
	public void deleteKey(Object id) throws DataAccessException
	{
		KeyEntity entity = new KeyEntity(em);
		if (entity.loadEntity(id) != null)
		{
			entity.remove();
		}
	}
	
	
	/**
	 * Retrieve all <code>YdkRole</code>s from the data store.
	 * @return Collection<YdkRole>
	 * @throws DataAccessException
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<YdkRole> getAllYdkRoles() throws DataAccessException
	{
		YdkRoleQuery query = new YdkRoleQuery(em);
		//need to eager initialization
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<YdkRole> getLimitedYdkRoles(Integer maxNumPerPage) throws DataAccessException
	{
		YdkRoleQuery query = new YdkRoleQuery(em);
		query.setMaxResults(maxNumPerPage);
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<YdkRole> findAllYdkRoles(Object searchKey) throws DataAccessException
	{
		return null;
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<YdkRole> findLimitedYdkRoles(Object searchKey, Integer maxNumPerPage) throws DataAccessException
	{
		return null;
	}
	
	/**
	 * CRUD
	 */
	@Transactional(readOnly = true)
	public YdkRole getYdkRole(Object id) throws DataAccessException
	{
		YdkRoleEntity entity = new YdkRoleEntity(em);
		return entity.loadEntity(id);
	}
	
	public void storeYdkRole(YdkRole instance) throws DataAccessException
	{
		YdkRoleEntity entity = new YdkRoleEntity(em);
		entity.setInstance(instance);
		entity.persist();
	}
	public void updateYdkRole(YdkRole instance) throws DataAccessException
	{
		YdkRoleEntity entity = new YdkRoleEntity(em);
		entity.setInstance(instance);
		entity.update();
	}
	public void deleteYdkRole(Object id) throws DataAccessException
	{
		YdkRoleEntity entity = new YdkRoleEntity(em);
		if (entity.loadEntity(id) != null)
		{
			entity.remove();
		}
	}
	
	/**
	 * Retrieve all <code>YdkAccount</code>s from the data store.
	 * @return Collection<YdkAccount>
	 * @throws DataAccessException
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<YdkAccount> getAllYdkAccounts() throws DataAccessException
	{
		YdkAccountQuery query = new YdkAccountQuery(em);
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<YdkAccount> getLimitedYdkAccounts(Integer maxNumPerPage) throws DataAccessException
	{
		YdkAccountQuery query = new YdkAccountQuery(em);
		query.setMaxResults(maxNumPerPage);
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<YdkAccount> findAllYdkAccounts(Object searchKey) throws DataAccessException
	{
		return null;
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<YdkAccount> findLimitedYdkAccounts(Object searchKey, Integer maxNumPerPage) throws DataAccessException
	{
		return null;
	}
	
	/**
	 * CRUD
	 */
	@Transactional(readOnly = true)
	public YdkAccount getYdkAccount(Object id) throws DataAccessException
	{
		YdkAccountEntity entity = new YdkAccountEntity(em);
		return entity.loadEntity(id);
	}
	
	public YdkAccount getYdkAccountByName(String username)
		throws DataAccessException 
	{
		// TODO Auto-generated method stub
		YdkAccountQuery query = new YdkAccountQuery(em);
		return query.searchYdkAccountByUsername(username);
	}
	
	public void storeYdkAccount(YdkAccount instance) throws DataAccessException
	{
		YdkAccountEntity entity = new YdkAccountEntity(em);
		entity.setInstance(instance);
		entity.persist();
	}
	public void updateYdkAccount(YdkAccount instance) throws DataAccessException
	{
		YdkAccountEntity entity = new YdkAccountEntity(em);
		entity.setInstance(instance);
		entity.update();
	}
	public void deleteYdkAccount(Object id) throws DataAccessException
	{
		YdkAccountEntity entity = new YdkAccountEntity(em);
		if (entity.loadEntity(id) != null)
		{
			entity.remove();
		}
	}
	
	
	/**
	 * Retrieve all <code>Editor</code>s from the data store.
	 * @return Collection<Editor>
	 * @throws DataAccessException
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Editor> getAllEditors() throws DataAccessException
	{
		EditorQuery query = new EditorQuery(em);
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Editor> getLimitedEditors(Integer maxNumPerPage) throws DataAccessException
	{
		EditorQuery query = new EditorQuery(em);
		query.setMaxResults(maxNumPerPage);
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Editor> findAllEditors(Object searchKey) throws DataAccessException
	{
		return null;
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Editor> findLimitedEditors(Object searchKey, Integer maxNumPerPage) throws DataAccessException
	{
		return null;
	}
	/**
	 * CRUD
	 */
	@Transactional(readOnly = true)
	public Editor getEditor(Object id) throws DataAccessException
	{
		EditorEntity entity = new EditorEntity(em);
		return entity.loadEntity(id);
	}
	
	public void storeEditor(Editor instance) throws DataAccessException
	{
		EditorEntity entity = new EditorEntity(em);
		entity.setInstance(instance);
		entity.persist();
	}
	public void updateEditor(Editor instance) throws DataAccessException
	{
		EditorEntity entity = new EditorEntity(em);
		entity.setInstance(instance);
		entity.update();
	}
	public void deleteEditor(Object id) throws DataAccessException
	{
		EditorEntity entity = new EditorEntity(em);
		if (entity.loadEntity(id) != null)
		{
			entity.remove();
		}
	}
	
	/**
	 * Retrieve all <code>Author</code>s from the data store.
	 * @return Collection<Author>
	 * @throws DataAccessException
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Author> getAllAuthors() throws DataAccessException
	{
		AuthorQuery query = new AuthorQuery(em);
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Author> getLimitedAuthors(Integer maxNumPerPage) throws DataAccessException
	{
		AuthorQuery query = new AuthorQuery(em);
		query.setMaxResults(maxNumPerPage);
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Author> findAllAuthors(Object searchKey) throws DataAccessException
	{
		return null;
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Author> findLimitedAuthors(Object searchKey, Integer maxNumPerPage) throws DataAccessException
	{
		return null;
	}
	
	/**
	 * CRUD
	 */
	@Transactional(readOnly = true)
	public Author getAuthor(Object id) throws DataAccessException
	{
		AuthorEntity entity = new AuthorEntity(em);
		return entity.loadEntity(id);
	}
	
	public void storeAuthor(Author instance) throws DataAccessException
	{
		AuthorEntity entity = new AuthorEntity(em);
		entity.setInstance(instance);
		entity.persist();
	}
	public void updateAuthor(Author instance) throws DataAccessException
	{
		AuthorEntity entity = new AuthorEntity(em);
		entity.setInstance(instance);
		entity.update();
	}
	public void deleteAuthor(Object id) throws DataAccessException
	{
		AuthorEntity entity = new AuthorEntity(em);
		if (entity.loadEntity(id) != null)
		{
			entity.remove();
		}
	}
	
	/**
	 * Retrieve all <code>Transaction</code>s from the data store.
	 * @return Collection<Transaction>
	 * @throws DataAccessException
	 */

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Transaction> getAllTransactions() throws DataAccessException
	{
		TransactionQuery query = new TransactionQuery(em);
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Transaction> getLimitedTransactions(Integer maxNumPerPage) throws DataAccessException
	{
		TransactionQuery query = new TransactionQuery(em);
		query.setMaxResults(maxNumPerPage);
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Transaction> findAllTransactions(Object searchKey) throws DataAccessException
	{
		return null;
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Transaction> findLimitedTransactions(Object searchKey, Integer maxNumPerPage) throws DataAccessException
	{
		return null;
	}

	/**
	 * CRUD
	 */
	@Transactional(readOnly = true)
	public Transaction getTransaction(Object id) throws DataAccessException
	{
		TransactionEntity entity = new TransactionEntity(em);
		return entity.loadEntity(id);
	}
	
	public void storeTransaction(Transaction instance) throws DataAccessException
	{
		TransactionEntity entity = new TransactionEntity(em);
		entity.setInstance(instance);
		entity.persist();
	}
	public void updateTransaction(Transaction instance) throws DataAccessException
	{
		TransactionEntity entity = new TransactionEntity(em);
		entity.setInstance(instance);
		entity.update();
	}
	public void deleteTransaction(Object id) throws DataAccessException
	{
		TransactionEntity entity = new TransactionEntity(em);
		if (entity.loadEntity(id) != null)
		{
			entity.remove();
		}
	}
	
	/**
	 * Retrieve all <code>TransactionType</code>s from the data store.
	 * @return Collection<TransactionType>
	 * @throws DataAccessException
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<TransactionType> getAllTransactionTypes() throws DataAccessException
	{
		TransactionTypeQuery query = new TransactionTypeQuery(em);
		return query.getResultList();
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<TransactionType> getLimitedTransactionTypes(Integer maxNumPerPage) throws DataAccessException
	{
		TransactionTypeQuery query = new TransactionTypeQuery(em);
		query.setMaxResults(maxNumPerPage);
		return query.getResultList();
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<TransactionType> findAllTransactionTypes(Object searchKey) throws DataAccessException
	{
		return null;
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<TransactionType> findLimitedTransactionTypes(Object searchKey, Integer maxNumPerPage) throws DataAccessException
	{
		return null;
	}
	
	/**
	 * CRUD
	 */
	@Transactional(readOnly = true)
	public TransactionType getTransactionType(Object id) throws DataAccessException
	{
		TransactionTypeEntity entity = new TransactionTypeEntity(em);
		return entity.loadEntity(id);
	}
	
	public void storeTransactionType(TransactionType instance) throws DataAccessException
	{
		TransactionTypeEntity entity = new TransactionTypeEntity(em);
		entity.setInstance(instance);
		entity.persist();
	}
	public void updateTransactionType(TransactionType instance) throws DataAccessException
	{
		TransactionTypeEntity entity = new TransactionTypeEntity(em);
		entity.setInstance(instance);
		entity.update();
	}
	public void deleteTransactionType(Object id) throws DataAccessException
	{
		TransactionTypeEntity entity = new TransactionTypeEntity(em);
		if (entity.loadEntity(id) != null)
		{
			entity.remove();
		}
	}
	
	
	/**
	 * Retrieve all <code>Address</code>s from the data store.
	 * @return Collection<Address>
	 * @throws DataAccessException
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Address> getAllAddresses() throws DataAccessException
	{
		AddressQuery query = new AddressQuery(em);
		return query.getResultList();
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Address> getLimitedAddresses(Integer maxNumPerPage) throws DataAccessException
	{
		AddressQuery query = new AddressQuery(em);
		query.setMaxResults(maxNumPerPage);
		return query.getResultList();
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Address> findAllAddresses(Object searchKey) throws DataAccessException
	{
		return null;
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Address> findLimitedAddresses(Object searchKey, Integer maxNumPerPage) throws DataAccessException
	{
		return null;
	}
	
	/**
	 * CRUD
	 */
	@Transactional(readOnly = true)
	public Address getAddress(Object id) throws DataAccessException
	{
		AddressEntity entity = new AddressEntity(em);
		return entity.loadEntity(id);
	}
	
	public void storeAddress(Address instance) throws DataAccessException
	{
		AddressEntity entity = new AddressEntity(em);
		entity.setInstance(instance);
		entity.persist();
	}
	public void updateAddress(Address instance) throws DataAccessException
	{
		AddressEntity entity = new AddressEntity(em);
		entity.setInstance(instance);
		entity.update();
	}
	public void deleteAddress(Object id) throws DataAccessException
	{
		AddressEntity entity = new AddressEntity(em);
		if (entity.loadEntity(id) != null)
		{
			entity.remove();
		}
	}
	
	
	/**
	 * Retrieve all <code>Evaluation</code>s from the data store.
	 * @return Collection<Evaluation>
	 * @throws DataAccessException
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Evaluation> getAllEvaluationes() throws DataAccessException
	{
		EvaluationQuery query = new EvaluationQuery(em);
		return query.getResultList();
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Evaluation> getLimitedEvaluationes(Integer maxNumPerPage) throws DataAccessException
	{
		EvaluationQuery query = new EvaluationQuery(em);
		query.setMaxResults(maxNumPerPage);
		return query.getResultList();
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Evaluation> findAllEvaluationes(Object searchKey) throws DataAccessException
	{
		return null;
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Evaluation> findLimitedEvaluationes(Object searchKey, Integer maxNumPerPage) throws DataAccessException
	{
		return null;
	}
	
	/**
	 * CRUD
	 */
	@Transactional(readOnly = true)
	public Evaluation getEvaluation(Object id) throws DataAccessException
	{
		EvaluationEntity entity = new EvaluationEntity(em);
		return entity.loadEntity(id);
	}
	
	public void storeEvaluation(Evaluation instance) throws DataAccessException
	{
		EvaluationEntity entity = new EvaluationEntity(em);
		entity.setInstance(instance);
		entity.persist();
	}
	public void updateEvaluation(Evaluation instance) throws DataAccessException
	{
		EvaluationEntity entity = new EvaluationEntity(em);
		entity.setInstance(instance);
		entity.update();
	}
	public void deleteEvaluation(Object id) throws DataAccessException
	{
		EvaluationEntity entity = new EvaluationEntity(em);
		if (entity.loadEntity(id) != null)
		{
			entity.remove();
		}
	}
	
	
	/**
	 * Retrieve all <code>ReaderComment</code>s from the data store.
	 * @return Collection<ReaderComment>
	 * @throws DataAccessException
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<ReaderComment> getAllReaderCommentes() throws DataAccessException
	{
		ReaderCommentQuery query = new ReaderCommentQuery(em);
		return query.getResultList();
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<ReaderComment> getLimitedReaderCommentes(Integer maxNumPerPage) throws DataAccessException
	{
		ReaderCommentQuery query = new ReaderCommentQuery(em);
		query.setMaxResults(maxNumPerPage);
		return query.getResultList();
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<ReaderComment> findAllReaderCommentes(Object searchKey) throws DataAccessException
	{
		return null;
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<ReaderComment> findLimitedReaderCommentes(Object searchKey, Integer maxNumPerPage) throws DataAccessException
	{
		return null;
	}
	
	/**
	 * CRUD
	 */
	@Transactional(readOnly = true)
	public ReaderComment getReaderComment(Object id) throws DataAccessException
	{
		ReaderCommentEntity entity = new ReaderCommentEntity(em);
		return entity.loadEntity(id);
	}
	
	public void storeReaderComment(ReaderComment instance) throws DataAccessException
	{
		ReaderCommentEntity entity = new ReaderCommentEntity(em);
		entity.setInstance(instance);
		entity.persist();
	}
	public void updateReaderComment(ReaderComment instance) throws DataAccessException
	{
		ReaderCommentEntity entity = new ReaderCommentEntity(em);
		entity.setInstance(instance);
		entity.update();
	}
	public void deleteReaderComment(Object id) throws DataAccessException
	{
		ReaderCommentEntity entity = new ReaderCommentEntity(em);
		if (entity.loadEntity(id) != null)
		{
			entity.remove();
		}
	}
	
	
	
	/**
	 * Retrieve all <code>BookMark</code>s from the data store.
	 * @return Collection<BookMark>
	 * @throws DataAccessException
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<BookMark> getAllBookMarkes() throws DataAccessException
	{
		BookMarkQuery query = new BookMarkQuery(em);
		return query.getResultList();
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<BookMark> getLimitedBookMarkes(Integer maxNumPerPage) throws DataAccessException
	{
		BookMarkQuery query = new BookMarkQuery(em);
		query.setMaxResults(maxNumPerPage);
		return query.getResultList();
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<BookMark> findAllBookMarkes(Object searchKey) throws DataAccessException
	{
		return null;
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<BookMark> findAllBookMarkes(Reader reader,
			Book book) throws DataAccessException {
		// Search
		BookMarkQuery be = new BookMarkQuery(getEntityManager());
		be.setUserRestrictions(be.CASE_SENSITIVE_RESTRICTION_SEARCH);
		be.addRestrictionParameter("reader", reader);
		be.addRestrictionParameter("book", book);
		return be.getResultList();
	}
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<BookMark> findLimitedBookMarkes(Object searchKey, Integer maxNumPerPage) throws DataAccessException
	{
		return null;
	}
	
	/**
	 * CRUD
	 */
	@Transactional(readOnly = true)
	public BookMark getBookMark(Object id) throws DataAccessException
	{
		BookMarkEntity entity = new BookMarkEntity(em);
		return entity.loadEntity(id);
	}
	
	public void storeBookMark(BookMark instance) throws DataAccessException
	{
		BookMarkEntity entity = new BookMarkEntity(em);
		entity.setInstance(instance);
		entity.persist();
	}
	public void updateBookMark(BookMark instance) throws DataAccessException
	{
		BookMarkEntity entity = new BookMarkEntity(em);
		entity.setInstance(instance);
		entity.update();
	}
	public void deleteBookMark(Object id) throws DataAccessException
	{
		BookMarkEntity entity = new BookMarkEntity(em);
		if (entity.loadEntity(id) != null)
		{
			entity.remove();
		}
	}


	public Partner getPartnerByName(String name) throws DataAccessException {

		PartnerQuery query = new PartnerQuery(em);
		return query.searchPartnerByName(name);
	}


	public PartnerType getPartnerTypeByName(String name)
			throws DataAccessException {
		PartnerTypeQuery query = new PartnerTypeQuery(em);
		return query.searchPartnerTypeByName(name);
	}


	public Reader getReaderByName(String name) throws DataAccessException {
		ReaderQuery query = new ReaderQuery(em);
		return query.searchReaderByName(name);
	}


	public TransactionType getTransactionTypeByName(String name)
			throws DataAccessException {
		TransactionTypeQuery query = new TransactionTypeQuery(em);
		return query.searchTransactionTypeByType(name);
	}




	
	
	
}
