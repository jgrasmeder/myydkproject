
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
import com.ydk.book.persistence.entity.Publisher;
import com.ydk.book.persistence.interfaces.AuthorDbMngr;
import com.ydk.book.persistence.interfaces.PublisherDbMngr;
import com.ydk.book.persistence.operator.AuthorEntity;
import com.ydk.book.persistence.operator.AuthorQuery;
import com.ydk.book.persistence.operator.BookEntity;
import com.ydk.book.persistence.operator.PublisherEntity;
import com.ydk.book.persistence.operator.PublisherQuery;

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
public class PublisherDbMngrImpl implements PublisherDbMngr{

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
	public Collection<Publisher> findPublishers(String name)
			throws DataAccessException {
		// TODO Auto-generated method stub
		PublisherQuery query = new PublisherQuery(em);
		
		return query.searchPublisherByField("name", name);
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Publisher> findPublishers(String name, Integer maxNumPerPage)
		throws DataAccessException {
		// TODO Auto-generated method stub
		PublisherQuery query = new PublisherQuery(em);
		query.setMaxResults(maxNumPerPage);
		
		return query.searchPublisherByField("name", name);
	}

	@Transactional(readOnly = true)
	public Publisher getPublisherByName(String name) throws DataAccessException {
		// TODO Auto-generated method stub
		PublisherQuery query = new PublisherQuery(em);
		
		List<Publisher> entries = query.searchPublisherByField("name", name);
		if (entries.size() == 0)
		{
			return null;
		}
		else
		{
			//There should be only one entry matching.
			for (Publisher publisher : entries)
			{
				if (name.equals(publisher.getName()))
				{
					return publisher;
				}
			}
			// Not Found
			return null;
		}
	}
	

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Publisher> getPublishers() throws DataAccessException {
		// TODO Auto-generated method stub
		PublisherQuery query = new PublisherQuery(em);
		
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Publisher> getPublishers(Integer maxNumPerPage)
	throws DataAccessException {
		PublisherQuery query = new PublisherQuery(em);
		query.setMaxResults(maxNumPerPage);
		return query.getResultList();
	}

	
	@Transactional(readOnly = true)
	public Publisher getPublisher(Long id) throws DataAccessException {
		PublisherEntity entity = new PublisherEntity(em);
		return entity.loadEntity(id);
	}

	/**
	 * @see PublisherDbMngr#savePublisher
	 */
	public void savePublisher(Publisher publisher) throws DataAccessException {
		//store publisher or update publisher
		if (publisher.getId() == null)
		{
			//store publisher
			//0. The user of DbMngr should ensure there is no 
			//	publisher name confliction, else DataAccessException is thrown
			//1.  store publisher;
			
			PublisherEntity entity = new PublisherEntity(em);
			entity.setInstance(publisher);
			entity.persist();
		
		}
		else
		{
			//0. The user of DbMngr should ensure the publisher is valid,
			//else DataAccessException is thrown
			//update publisher
			PublisherEntity entity = new PublisherEntity(em, publisher.getId());
			entity.setInstance(publisher);
			entity.update();
		}
		
	}
	
	public void deletePublisherByName(String name) throws DataAccessException {
		// TODO Auto-generated method stub
		Publisher publisher = getPublisherByName(name);
		deletePublisher(publisher.getId());
		
	}

	public void deletePublisher(Long id) throws DataAccessException {
		// TODO Auto-generated method stub
		
		PublisherEntity entity = new PublisherEntity(em, id);
			
		entity.remove();
	}

	
}
