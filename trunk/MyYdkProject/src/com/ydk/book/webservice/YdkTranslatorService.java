/**
 * 
 */
package com.ydk.book.webservice;

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

import com.ydk.book.webservice.WebServiceBook;
import com.ydk.book.persistence.entity.Book;
import com.ydk.account.persistence.entity.*;
import com.ydk.book.webservice.WebServiceBook;


@WebService
public interface YdkTranslatorService {
    
    List<WebServiceTranslator> getAllTranslators();
    List<String> getNameList();
    
    /**
	 * Retrieve all translators maxNumPerPage of <code>Translator</code>s from the data store.
	 * @return Collection<Translator>
	 * @throws 
	 */
    List<WebServiceTranslator> getTranslators(@WebParam(name = "maxNumPerPage")Integer maxNumPerPage);
	
	/**
	 * Retrieve all <code>Translator</code>s from the data store similar with translatorName,
	 * returning all translators whose name <i>contains</i> with the given name.
	 * @param translatorName
	 * @return Collection<Translator>
	 * @throws DataAccessException
	 */
    List<WebServiceTranslator> findTranslators(@WebParam(name = "name")String translatorName);
	
	/**
	 * Retrieve <code>Translator</code>s from the data store by last name,
	 * returning all translators whose name <i>contains</i> with the given name.
	 * @param translatorName
	 * @return Collection<Translator>
	 * @throws DataAccessException
	 */
    List<WebServiceTranslator> findLimitedTranslators(@WebParam(name = "name")String translatorName,
    		@WebParam(name = "maxNumPerPage")Integer maxNumPerPage);

	/**
	 * Retrieve an <code>Translator</code> from the data store by translatorTitle.
	 * @param translatorTitle the unique name to search for
	 * @return
	 * @throws org.springframework.dao.DataRetrievalFailureException if not
	 *         found
	 */
    WebServiceTranslator getTranslatorByName(@WebParam(name = "name")String translatorTitle) ;
	

	/**
	 * Retrieve an <code>Translator</code> from the data store by translatorId.
	 * @param translatorId the unique id to search for
	 * @return
	 * @throws org.springframework.dao.DataRetrievalFailureException if not
	 *         found
	 */
    WebServiceTranslator getTranslator(@WebParam(name = "id")Long translatorId);

	/**
	 * Save an <code>Translator</code> to the data store, either inserting or
	 * updating it.
	 *
	 * @param translator the <code>Translator</code> to save
	 * 
	 */
	void saveTranslator(@WebParam(name = "translator")WebServiceTranslator translator);
	
	void addTranslator(@WebParam(name = "name")String name, @WebParam(name = "nation")String nation, 
			@WebParam(name = "sex")String sex, @WebParam(name = "introduction")String introduction);

	/**
	 * Delete an <code>Translator</code> from the data store by translatorName.
	 *
	 * @param translator the <code>Translator</code> to save
	 * 
	 */
	void deleteTranslatorByName(@WebParam(name = "name")String translatorTitle);
	
	/**
	 * Delete an <code>Translator</code> from the data store by translator Id.
	 * @param translatorId
	 * @throws DataAccessException
	 */
	void deleteTranslator(@WebParam(name = "id")Long translatorId);
	

}
