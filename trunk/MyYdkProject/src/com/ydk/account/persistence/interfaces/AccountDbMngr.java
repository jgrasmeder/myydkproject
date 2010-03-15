
package com.ydk.account.persistence.interfaces;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import com.ydk.account.persistence.entity.*;
import com.ydk.book.persistence.entity.Author;
import com.ydk.book.persistence.entity.Book;

import org.springframework.dao.DataAccessException;

/**
 * The high-level ydk business db interface.
 * <p>
 * This is basically a data access object. ydk should add more dedicated
 * business facades.
 *
 * @author Zhang Yu
 */
public interface AccountDbMngr {


	
	EntityManager getEntityManager();
	/**
	 * Retrieve all <code>Account</code>s from the data store.
	 * @return Collection<Account>
	 * @throws DataAccessException
	 */
	Collection<Account> getAccounts() throws DataAccessException;
	Collection<Account> getAccounts(Integer maxNumPerPage) throws DataAccessException;
	Collection<Account> findAccounts(String accountName) throws DataAccessException;
	Collection<Account> findAccounts(String accountName, Integer maxNumPerPage) throws DataAccessException;
	Account getAccount(String accountName) throws DataAccessException;
	Account getAccount(Long accountId) throws DataAccessException;
	void saveAccount(Account account) throws DataAccessException;
	void deleteAccount(String accountName) throws DataAccessException;
	void deleteAccount(Long accountId) throws DataAccessException;
	Collection<Account> getBooks() throws DataAccessException;
	void saveBook(Book book) throws DataAccessException;
	FileDescriptor getAccountImg(Long imgId) throws DataAccessException ;
	UploadedFile getUploadedFile(Long fileId) throws DataAccessException;
	void storeUploadedFile(UploadedFile uploadedFile) throws DataAccessException;
	void saveAccount(Account account, UploadedFile uploadedFile) throws DataAccessException;
	
	
	
	/**
	 * Retrieve all <code>PartnerType</code>s from the data store.
	 * @return Collection<PartnerType>
	 * @throws DataAccessException
	 */
	Collection<PartnerType> getAllPartnerTypes() throws DataAccessException;
	Collection<PartnerType> getLimitedPartnerTypes(Integer maxNumPerPage) throws DataAccessException;

	Collection<PartnerType> findAllPartnerTypes(String type) throws DataAccessException;
	Collection<PartnerType> findLimitedPartnerTypes(String type, Integer maxNumPerPage) throws DataAccessException;
	/**
	 * CRUD
	 */
	PartnerType getPartnerType(Object id) throws DataAccessException;
	PartnerType getPartnerTypeByName(String name) throws DataAccessException;
	void storePartnerType(PartnerType instance) throws DataAccessException;
	void updatePartnerType(PartnerType instance) throws DataAccessException;
	void deletePartnerType(Object id) throws DataAccessException;
	
	
	/**
	 * Retrieve all <code>Partner</code>s from the data store.
	 * @return Collection<Partner>
	 * @throws DataAccessException
	 */
	Collection<Partner> getAllPartners() throws DataAccessException;
	Collection<Partner> getLimitedPartners(Integer maxNumPerPage) throws DataAccessException;

	Collection<Partner> findAllPartners(Object searchKey) throws DataAccessException;
	Collection<Partner> findLimitedPartners(Object searchKey, Integer maxNumPerPage) throws DataAccessException;
	/**
	 * CRUD
	 */
	Partner getPartner(Object id) throws DataAccessException;
	Partner getPartnerByName(String name) throws DataAccessException;
	void storePartner(Partner instance) throws DataAccessException;
	void updatePartner(Partner instance) throws DataAccessException;
	void deletePartner(Object id) throws DataAccessException;
	
	
	/**
	 * Retrieve all <code>Reader</code>s from the data store.
	 * @return Collection<Reader>
	 * @throws DataAccessException
	 */
	Collection<Reader> getAllReaders() throws DataAccessException;
	Collection<Reader> getLimitedReaders(Integer maxNumPerPage) throws DataAccessException;

	Collection<Reader> findAllReaders(Object searchKey) throws DataAccessException;
	Collection<Reader> findLimitedReaders(Object searchKey, Integer maxNumPerPage) throws DataAccessException;
	/**
	 * CRUD
	 */
	Reader getReader(Object id) throws DataAccessException;
	Reader getReaderByName(String name) throws DataAccessException;
	void storeReader(Reader instance) throws DataAccessException;
	void updateReader(Reader instance) throws DataAccessException;
	void deleteReader(Object id) throws DataAccessException;
	
	
	/**
	 * Retrieve all <code>Key</code>s from the data store.
	 * @return Collection<Key>
	 * @throws DataAccessException
	 */
	Collection<Key> getAllKeys() throws DataAccessException;
	Collection<Key> getLimitedKeys(Integer maxNumPerPage) throws DataAccessException;

	Collection<Key> findAllKeys(Object searchKey) throws DataAccessException;
	Collection<Key> findLimitedKeys(Object searchKey, Integer maxNumPerPage) throws DataAccessException;
	/**
	 * CRUD
	 */
	Key getKey(Object id) throws DataAccessException;
	void storeKey(Key instance) throws DataAccessException;
	void updateKey(Key instance) throws DataAccessException;
	void deleteKey(Object id) throws DataAccessException;
	
	
	/**
	 * Retrieve all <code>YdkRole</code>s from the data store.
	 * @return Collection<YdkRole>
	 * @throws DataAccessException
	 */
	Collection<YdkRole> getAllYdkRoles() throws DataAccessException;
	Collection<YdkRole> getLimitedYdkRoles(Integer maxNumPerPage) throws DataAccessException;

	Collection<YdkRole> findAllYdkRoles(Object searchKey) throws DataAccessException;
	Collection<YdkRole> findLimitedYdkRoles(Object searchKey, Integer maxNumPerPage) throws DataAccessException;
	/**
	 * CRUD
	 */
	YdkRole getYdkRole(Object id) throws DataAccessException;
	void storeYdkRole(YdkRole instance) throws DataAccessException;
	void updateYdkRole(YdkRole instance) throws DataAccessException;
	void deleteYdkRole(Object id) throws DataAccessException;
	
	/**
	 * Retrieve all <code>YdkAccount</code>s from the data store.
	 * @return Collection<YdkAccount>
	 * @throws DataAccessException
	 */
	Collection<YdkAccount> getAllYdkAccounts() throws DataAccessException;
	Collection<YdkAccount> getLimitedYdkAccounts(Integer maxNumPerPage) throws DataAccessException;

	Collection<YdkAccount> findAllYdkAccounts(Object searchKey) throws DataAccessException;
	Collection<YdkAccount> findLimitedYdkAccounts(Object searchKey, Integer maxNumPerPage) throws DataAccessException;
	/**
	 * CRUD
	 */
	YdkAccount getYdkAccount(Object id) throws DataAccessException;
	YdkAccount getYdkAccountByName(String username) throws DataAccessException;
	void storeYdkAccount(YdkAccount instance) throws DataAccessException;
	void updateYdkAccount(YdkAccount instance) throws DataAccessException;
	void deleteYdkAccount(Object id) throws DataAccessException;
	
	
	/**
	 * Retrieve all <code>Editor</code>s from the data store.
	 * @return Collection<Editor>
	 * @throws DataAccessException
	 */
	Collection<Editor> getAllEditors() throws DataAccessException;
	Collection<Editor> getLimitedEditors(Integer maxNumPerPage) throws DataAccessException;

	Collection<Editor> findAllEditors(Object searchKey) throws DataAccessException;
	Collection<Editor> findLimitedEditors(Object searchKey, Integer maxNumPerPage) throws DataAccessException;
	/**
	 * CRUD
	 */
	Editor getEditor(Object id) throws DataAccessException;
	void storeEditor(Editor instance) throws DataAccessException;
	void updateEditor(Editor instance) throws DataAccessException;
	void deleteEditor(Object id) throws DataAccessException;
	
	/**
	 * Retrieve all <code>Author</code>s from the data store.
	 * @return Collection<Author>
	 * @throws DataAccessException
	 */
	Collection<Author> getAllAuthors() throws DataAccessException;
	Collection<Author> getLimitedAuthors(Integer maxNumPerPage) throws DataAccessException;

	Collection<Author> findAllAuthors(Object searchKey) throws DataAccessException;
	Collection<Author> findLimitedAuthors(Object searchKey, Integer maxNumPerPage) throws DataAccessException;
	/**
	 * CRUD
	 */
	Author getAuthor(Object id) throws DataAccessException;
	void storeAuthor(Author instance) throws DataAccessException;
	void updateAuthor(Author instance) throws DataAccessException;
	void deleteAuthor(Object id) throws DataAccessException;
	
	/**
	 * Retrieve all <code>Transaction</code>s from the data store.
	 * @return Collection<Transaction>
	 * @throws DataAccessException
	 */
	Collection<Transaction> getAllTransactions() throws DataAccessException;
	Collection<Transaction> getLimitedTransactions(Integer maxNumPerPage) throws DataAccessException;

	Collection<Transaction> findAllTransactions(Object searchKey) throws DataAccessException;
	Collection<Transaction> findLimitedTransactions(Object searchKey, Integer maxNumPerPage) throws DataAccessException;
	/**
	 * CRUD
	 */
	Transaction getTransaction(Object id) throws DataAccessException;
	void storeTransaction(Transaction instance) throws DataAccessException;
	void updateTransaction(Transaction instance) throws DataAccessException;
	void deleteTransaction(Object id) throws DataAccessException;
	
	/**
	 * Retrieve all <code>TransactionType</code>s from the data store.
	 * @return Collection<TransactionType>
	 * @throws DataAccessException
	 */
	Collection<TransactionType> getAllTransactionTypes() throws DataAccessException;
	Collection<TransactionType> getLimitedTransactionTypes(Integer maxNumPerPage) throws DataAccessException;

	Collection<TransactionType> findAllTransactionTypes(Object searchKey) throws DataAccessException;
	Collection<TransactionType> findLimitedTransactionTypes(Object searchKey, Integer maxNumPerPage) throws DataAccessException;
	/**
	 * CRUD
	 */
	TransactionType getTransactionType(Object id) throws DataAccessException;
	TransactionType getTransactionTypeByName(String name) throws DataAccessException;
	void storeTransactionType(TransactionType instance) throws DataAccessException;
	void updateTransactionType(TransactionType instance) throws DataAccessException;
	void deleteTransactionType(Object id) throws DataAccessException;
	
	
	/**
	 * Retrieve all <code>Address</code>s from the data store.
	 * @return Collection<Address>
	 * @throws DataAccessException
	 */
	Collection<Address> getAllAddresses() throws DataAccessException;
	Collection<Address> getLimitedAddresses(Integer maxNumPerPage) throws DataAccessException;

	Collection<Address> findAllAddresses(Object searchKey) throws DataAccessException;
	Collection<Address> findLimitedAddresses(Object searchKey, Integer maxNumPerPage) throws DataAccessException;
	/**
	 * CRUD
	 */
	Address getAddress(Object id) throws DataAccessException;
	void storeAddress(Address instance) throws DataAccessException;
	void updateAddress(Address instance) throws DataAccessException;
	void deleteAddress(Object id) throws DataAccessException;
	
	
	
	/**
	 * Retrieve all <code>Evaluation</code>s from the data store.
	 * @return Collection<Evaluation>
	 * @throws DataAccessException
	 */
	Collection<Evaluation> getAllEvaluationes() throws DataAccessException;
	Collection<Evaluation> getLimitedEvaluationes(Integer maxNumPerPage) throws DataAccessException;

	Collection<Evaluation> findAllEvaluationes(Object searchKey) throws DataAccessException;
	Collection<Evaluation> findLimitedEvaluationes(Object searchKey, Integer maxNumPerPage) throws DataAccessException;
	/**
	 * CRUD
	 */
	Evaluation getEvaluation(Object id) throws DataAccessException;
	void storeEvaluation(Evaluation instance) throws DataAccessException;
	void updateEvaluation(Evaluation instance) throws DataAccessException;
	void deleteEvaluation(Object id) throws DataAccessException;
	
	
	/**
	 * Retrieve all <code>ReaderComment</code>s from the data store.
	 * @return Collection<ReaderComment>
	 * @throws DataAccessException
	 */
	Collection<ReaderComment> getAllReaderCommentes() throws DataAccessException;
	Collection<ReaderComment> getLimitedReaderCommentes(Integer maxNumPerPage) throws DataAccessException;

	Collection<ReaderComment> findAllReaderCommentes(Object searchKey) throws DataAccessException;
	Collection<ReaderComment> findLimitedReaderCommentes(Object searchKey, Integer maxNumPerPage) throws DataAccessException;
	/**
	 * CRUD
	 */
	ReaderComment getReaderComment(Object id) throws DataAccessException;
	void storeReaderComment(ReaderComment instance) throws DataAccessException;
	void updateReaderComment(ReaderComment instance) throws DataAccessException;
	void deleteReaderComment(Object id) throws DataAccessException;
	
	
	/**
	 * Retrieve all <code>BookMark</code>s from the data store.
	 * @return Collection<BookMark>
	 * @throws DataAccessException
	 */
	Collection<BookMark> getAllBookMarkes() throws DataAccessException;
	Collection<BookMark> getLimitedBookMarkes(Integer maxNumPerPage) throws DataAccessException;

	Collection<BookMark> findAllBookMarkes(Object searchKey) throws DataAccessException;
	Collection<BookMark> findAllBookMarkes(Reader reader,Book book) throws DataAccessException;
	Collection<BookMark> findLimitedBookMarkes(Object searchKey, Integer maxNumPerPage) throws DataAccessException;
	/**
	 * CRUD
	 */
	BookMark getBookMark(Object id) throws DataAccessException;
	void storeBookMark(BookMark instance) throws DataAccessException;
	void updateBookMark(BookMark instance) throws DataAccessException;
	void deleteBookMark(Object id) throws DataAccessException;
	
	
	
	
	
	
	
	
	
	
	


}
