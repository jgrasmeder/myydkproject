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
public interface YdkPublisherService {
    
    List<WebServicePublisher> getAllPublishers();
    
    List<String> getNameList();
    /**
	 * Retrieve all publishers maxNumPerPage of <code>Publisher</code>s from the data store.
	 * @return Collection<Publisher>
	 * @throws 
	 */
    List<WebServicePublisher> getPublishers(@WebParam(name = "maxNumPerPage")Integer maxNumPerPage);
	
	/**
	 * Retrieve all <code>Publisher</code>s from the data store similar with publisherName,
	 * returning all publishers whose name <i>contains</i> with the given name.
	 * @param publisherName
	 * @return Collection<Publisher>
	 * @throws DataAccessException
	 */
    List<WebServicePublisher> findPublishers(@WebParam(name = "name")String publisherName);
	
	/**
	 * Retrieve <code>Publisher</code>s from the data store by last name,
	 * returning all publishers whose name <i>contains</i> with the given name.
	 * @param publisherName
	 * @return Collection<Publisher>
	 * @throws DataAccessException
	 */
    List<WebServicePublisher> findLimitedPublishers(@WebParam(name = "name")String publisherName,
    		@WebParam(name = "maxNumPerPage")Integer maxNumPerPage);

	/**
	 * Retrieve an <code>Publisher</code> from the data store by publisherTitle.
	 * @param publisherTitle the unique name to search for
	 * @return
	 * @throws org.springframework.dao.DataRetrievalFailureException if not
	 *         found
	 */
    WebServicePublisher getPublisherByName(@WebParam(name = "name")String publisherTitle) ;
	

	/**
	 * Retrieve an <code>Publisher</code> from the data store by publisherId.
	 * @param publisherId the unique id to search for
	 * @return
	 * @throws org.springframework.dao.DataRetrievalFailureException if not
	 *         found
	 */
    WebServicePublisher getPublisher(@WebParam(name = "id")Long publisherId);

	/**
	 * Save an <code>Publisher</code> to the data store, either inserting or
	 * updating it.
	 *
	 * @param publisher the <code>Publisher</code> to save
	 * 
	 */
	void savePublisher(@WebParam(name = "publisher")WebServicePublisher publisher);
	
	void addPublisher(@WebParam(name = "name")String name, @WebParam(name = "address")String address, 
			@WebParam(name = "postcode")String postcode, @WebParam(name = "telephone")String telephone,
			@WebParam(name = "fax")String fax, @WebParam(name = "email")String email, 
			@WebParam(name = "contactPerson")String contactPerson, @WebParam(name = "introduction")String introduction);

	/**
	 * Delete an <code>Publisher</code> from the data store by publisherName.
	 *
	 * @param publisher the <code>Publisher</code> to save
	 * 
	 */
	void deletePublisherByName(@WebParam(name = "name")String publisherTitle);
	
	/**
	 * Delete an <code>Publisher</code> from the data store by publisher Id.
	 * @param publisherId
	 * @throws DataAccessException
	 */
	void deletePublisher(@WebParam(name = "id")Long publisherId);
	

}
