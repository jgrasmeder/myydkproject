
package com.ydk.book.persistence.interfaces;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import com.ydk.account.persistence.entity.*;
import com.ydk.book.persistence.entity.Author;
import com.ydk.book.persistence.entity.Book;
import com.ydk.book.persistence.entity.Publisher;

import org.springframework.dao.DataAccessException;

/**
 * The high-level ydk business db interface.
 * <p>
 * This is basically a data access object. ydk should add more dedicated
 * business facades.
 *
 * @publisher Zhang Yu
 */
public interface PublisherDbMngr {


	
	EntityManager getEntityManager();
	/**
	 * Retrieve all <code>Publisher</code>s from the data store.
	 * @return Collection<Publisher>
	 * @throws DataAccessException
	 */
	Collection<Publisher> getPublishers() throws DataAccessException;
	Collection<Publisher> getPublishers(Integer maxNumPerPage) throws DataAccessException;
	Collection<Publisher> findPublishers(String name) throws DataAccessException;
	Collection<Publisher> findPublishers(String name, Integer maxNumPerPage) throws DataAccessException;
	Publisher getPublisherByName(String name) throws DataAccessException;
	Publisher getPublisher(Long id) throws DataAccessException;
	void savePublisher(Publisher publisher) throws DataAccessException;
	void deletePublisherByName(String publisherName) throws DataAccessException;
	void deletePublisher(Long id) throws DataAccessException;

	
	
	
	
	
	
	
	


}
