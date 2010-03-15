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
public interface YdkAuthorService {
    
    List<WebServiceAuthor> getAllAuthors();
    
    List<String> getNameList();
    
    /**
	 * Retrieve all authors maxNumPerPage of <code>Author</code>s from the data store.
	 * @return Collection<Author>
	 * @throws 
	 */
    List<WebServiceAuthor> getAuthors(@WebParam(name = "maxNumPerPage")Integer maxNumPerPage);
	
	/**
	 * Retrieve all <code>Author</code>s from the data store similar with authorName,
	 * returning all authors whose name <i>contains</i> with the given name.
	 * @param authorName
	 * @return Collection<Author>
	 * @throws DataAccessException
	 */
    List<WebServiceAuthor> findAuthors(@WebParam(name = "name")String authorName);
	
	/**
	 * Retrieve <code>Author</code>s from the data store by last name,
	 * returning all authors whose name <i>contains</i> with the given name.
	 * @param authorName
	 * @return Collection<Author>
	 * @throws DataAccessException
	 */
    List<WebServiceAuthor> findLimitedAuthors(@WebParam(name = "name")String authorName,
    		@WebParam(name = "maxNumPerPage")Integer maxNumPerPage);

	/**
	 * Retrieve an <code>Author</code> from the data store by authorTitle.
	 * @param authorTitle the unique name to search for
	 * @return
	 * @throws org.springframework.dao.DataRetrievalFailureException if not
	 *         found
	 */
    WebServiceAuthor getAuthorByName(@WebParam(name = "name")String authorTitle) ;
	

	/**
	 * Retrieve an <code>Author</code> from the data store by authorId.
	 * @param authorId the unique id to search for
	 * @return
	 * @throws org.springframework.dao.DataRetrievalFailureException if not
	 *         found
	 */
    WebServiceAuthor getAuthor(@WebParam(name = "id")Long authorId);

	/**
	 * Save an <code>Author</code> to the data store, either inserting or
	 * updating it.
	 *
	 * @param author the <code>Author</code> to save
	 * 
	 */
	void saveAuthor(@WebParam(name = "author")WebServiceAuthor author);
	
	void addAuthor(@WebParam(name = "name")String name, @WebParam(name = "nation")String nation, 
			@WebParam(name = "sex")String sex, @WebParam(name = "introduction")String introduction);

	/**
	 * Delete an <code>Author</code> from the data store by authorName.
	 *
	 * @param author the <code>Author</code> to save
	 * 
	 */
	void deleteAuthorByName(@WebParam(name = "name")String authorTitle);
	
	/**
	 * Delete an <code>Author</code> from the data store by author Id.
	 * @param authorId
	 * @throws DataAccessException
	 */
	void deleteAuthor(@WebParam(name = "id")Long authorId);
	

}
