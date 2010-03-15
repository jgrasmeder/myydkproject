
package com.ydk.book.persistence;

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
import com.ydk.book.persistence.interfaces.AuthorDbMngr;
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
public class AuthorDbMngrImpl implements AuthorDbMngr{

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
	public Collection<Author> findAuthors(String name)
			throws DataAccessException {
		// TODO Auto-generated method stub
		AuthorQuery query = new AuthorQuery(em);
		query.searchAuthorByField("name", name);
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Author> findAuthors(String name, Integer maxNumPerPage)
		throws DataAccessException {
		// TODO Auto-generated method stub
		AuthorQuery query = new AuthorQuery(em);
		query.setMaxResults(maxNumPerPage);
		query.searchAuthorByField("name", name);
		return query.getResultList();
	}

	@Transactional(readOnly = true)
	public Author getAuthorByName(String name) throws DataAccessException {
		// TODO Auto-generated method stub
		AuthorQuery query = new AuthorQuery(em);
		
		List<Author> entries = query.searchAuthorByField(name, name);
		if (entries.size() == 0)
		{
			return null;
		}
		else
		{
			//There should be only one entry matching.
			for (Author author : entries)
			{
				if (name.equals(author.getName()))
				{
					return author;
				}
			}
			// Not Found
			return null;
		}
	}
	

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Author> getAuthors() throws DataAccessException {
		// TODO Auto-generated method stub
		AuthorQuery query = new AuthorQuery(em);
		
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Author> getAuthors(Integer maxNumPerPage)
	throws DataAccessException {
		AuthorQuery query = new AuthorQuery(em);
		query.setMaxResults(maxNumPerPage);
		return query.getResultList();
	}

	
	@Transactional(readOnly = true)
	public Author getAuthor(Long id) throws DataAccessException {
		AuthorEntity entity = new AuthorEntity(em);
		return entity.loadEntity(id);
	}

	/**
	 * @see AuthorDbMngr#saveAuthor
	 */
	public void saveAuthor(Author author) throws DataAccessException {
		//store author or update author
		if (author.getId() == null)
		{
			//store author
			//0. The user of DbMngr should ensure there is no 
			//	author name confliction, else DataAccessException is thrown
			//1.  store author;
			
			AuthorEntity entity = new AuthorEntity(em);
			entity.setInstance(author);
			entity.persist();
		
		}
		else
		{
			//0. The user of DbMngr should ensure the author is valid,
			//else DataAccessException is thrown
			//update author
			AuthorEntity entity = new AuthorEntity(em, author.getId());
			entity.setInstance(author);
			entity.update();
		}
		
	}
	
	public void deleteAuthorByName(String name) throws DataAccessException {
		// TODO Auto-generated method stub
		Author author = getAuthorByName(name);
		deleteAuthor(author.getId());
		
	}

	public void deleteAuthor(Long id) throws DataAccessException {
		// TODO Auto-generated method stub
		
		AuthorEntity entity = new AuthorEntity(em, id);
			
		entity.remove();
	}

	
}
