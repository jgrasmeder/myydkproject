
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
import com.ydk.book.persistence.entity.Translator;
import com.ydk.book.persistence.interfaces.AuthorDbMngr;
import com.ydk.book.persistence.interfaces.TranslatorDbMngr;
import com.ydk.book.persistence.operator.AuthorEntity;
import com.ydk.book.persistence.operator.AuthorQuery;
import com.ydk.book.persistence.operator.BookEntity;
import com.ydk.book.persistence.operator.TranslatorEntity;
import com.ydk.book.persistence.operator.TranslatorQuery;

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
public class TranslatorDbMngrImpl implements TranslatorDbMngr{

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
	public Collection<Translator> findTranslators(String name)
			throws DataAccessException {
		// TODO Auto-generated method stub
		TranslatorQuery query = new TranslatorQuery(em);
		query.searchTranslatorByField("name", name);
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Translator> findTranslators(String name, Integer maxNumPerPage)
		throws DataAccessException {
		// TODO Auto-generated method stub
		TranslatorQuery query = new TranslatorQuery(em);
		query.setMaxResults(maxNumPerPage);
		query.searchTranslatorByField("name", name);
		return query.getResultList();
	}

	@Transactional(readOnly = true)
	public Translator getTranslatorByName(String name) throws DataAccessException {
		// TODO Auto-generated method stub
		TranslatorQuery query = new TranslatorQuery(em);
		List<Translator> entries = query.searchTranslatorByField("name", name);
		if (entries.size() == 0)
		{
			return null;
		}
		else
		{
			//There should be only one entry matching.
			for (Translator translator : entries)
			{
				if (name.equals(translator.getName()))
				{
					return translator;
				}
			}
			// Not Found
			return null;
		}
	}
	

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Translator> getTranslators() throws DataAccessException {
		// TODO Auto-generated method stub
		TranslatorQuery query = new TranslatorQuery(em);
		
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Collection<Translator> getTranslators(Integer maxNumPerPage)
	throws DataAccessException {
		TranslatorQuery query = new TranslatorQuery(em);
		query.setMaxResults(maxNumPerPage);
		return query.getResultList();
	}

	
	@Transactional(readOnly = true)
	public Translator getTranslator(Long id) throws DataAccessException {
		TranslatorEntity entity = new TranslatorEntity(em);
		return entity.loadEntity(id);
	}

	/**
	 * @see TranslatorDbMngr#saveTranslator
	 */
	public void saveTranslator(Translator translator) throws DataAccessException {
		//store translator or update translator
		if (translator.getId() == null)
		{
			//store translator
			//0. The user of DbMngr should ensure there is no 
			//	translator name confliction, else DataAccessException is thrown
			//1.  store translator;
			
			TranslatorEntity entity = new TranslatorEntity(em);
			entity.setInstance(translator);
			entity.persist();
		
		}
		else
		{
			//0. The user of DbMngr should ensure the translator is valid,
			//else DataAccessException is thrown
			//update translator
			TranslatorEntity entity = new TranslatorEntity(em, translator.getId());
			entity.setInstance(translator);
			entity.update();
		}
		
	}
	
	public void deleteTranslatorByName(String name) throws DataAccessException {
		// TODO Auto-generated method stub
		Translator translator = getTranslatorByName(name);
		deleteTranslator(translator.getId());
		
	}

	public void deleteTranslator(Long id) throws DataAccessException {
		// TODO Auto-generated method stub
		
		TranslatorEntity entity = new TranslatorEntity(em, id);
			
		entity.remove();
	}

	
}
