
package com.ydk.book.persistence.interfaces;

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
public interface AuthorDbMngr {


	
	EntityManager getEntityManager();
	/**
	 * Retrieve all <code>Author</code>s from the data store.
	 * @return Collection<Author>
	 * @throws DataAccessException
	 */
	Collection<Author> getAuthors() throws DataAccessException;
	Collection<Author> getAuthors(Integer maxNumPerPage) throws DataAccessException;
	Collection<Author> findAuthors(String name) throws DataAccessException;
	Collection<Author> findAuthors(String name, Integer maxNumPerPage) throws DataAccessException;
	Author getAuthorByName(String name) throws DataAccessException;
	Author getAuthor(Long id) throws DataAccessException;
	void saveAuthor(Author author) throws DataAccessException;
	void deleteAuthorByName(String authorName) throws DataAccessException;
	void deleteAuthor(Long id) throws DataAccessException;

	
	
	
	
	
	
	
	


}
