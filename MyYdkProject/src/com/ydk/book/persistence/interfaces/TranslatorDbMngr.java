
package com.ydk.book.persistence.interfaces;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import com.ydk.account.persistence.entity.*;
import com.ydk.book.persistence.entity.Author;
import com.ydk.book.persistence.entity.Book;
import com.ydk.book.persistence.entity.Translator;

import org.springframework.dao.DataAccessException;

/**
 * The high-level ydk business db interface.
 * <p>
 * This is basically a data access object. ydk should add more dedicated
 * business facades.
 *
 * @author Zhang Yu
 */
public interface TranslatorDbMngr {


	
	EntityManager getEntityManager();
	/**
	 * Retrieve all <code>Translator</code>s from the data store.
	 * @return Collection<Translator>
	 * @throws DataAccessException
	 */
	Collection<Translator> getTranslators() throws DataAccessException;
	Collection<Translator> getTranslators(Integer maxNumPerPage) throws DataAccessException;
	Collection<Translator> findTranslators(String name) throws DataAccessException;
	Collection<Translator> findTranslators(String name, Integer maxNumPerPage) throws DataAccessException;
	Translator getTranslatorByName(String name) throws DataAccessException;
	Translator getTranslator(Long id) throws DataAccessException;
	void saveTranslator(Translator translator) throws DataAccessException;
	void deleteTranslatorByName(String translatorName) throws DataAccessException;
	void deleteTranslator(Long id) throws DataAccessException;

	
	
	
	
	
	
	
	


}
