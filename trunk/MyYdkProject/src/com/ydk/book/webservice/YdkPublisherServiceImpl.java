/**
 * 
 */
package com.ydk.book.webservice;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ydk.book.core.WebServiceBookHelper;
import com.ydk.book.persistence.entity.Author;
import com.ydk.book.persistence.entity.Book;
import com.ydk.book.persistence.entity.Publisher;
import com.ydk.account.persistence.entity.*;
import com.ydk.book.persistence.interfaces.AuthorDbMngr;
import com.ydk.book.persistence.interfaces.BookDbMngr;
import com.ydk.book.persistence.interfaces.PublisherDbMngr;
import com.ydk.book.webservice.YdkBookService;
/**
 * @author y140zhan
 *
 */

@WebService(endpointInterface = "com.ydk.book.webservice.YdkPublisherService")
@Transactional
public final class YdkPublisherServiceImpl implements YdkPublisherService {

	protected final PublisherDbMngr publisherDbMngr;
	
	@Autowired
	public YdkPublisherServiceImpl(PublisherDbMngr publisherDbMngr) {
		this.publisherDbMngr = publisherDbMngr;
	}

	public List<String> getNameList() {
	    	List<Publisher> publishers = (List<Publisher>) publisherDbMngr.getPublishers();
	    	List<String> nameList = new ArrayList<String>();
	    	
	    	for (Publisher publisher: publishers)
	    	{
	    		nameList.add(publisher.getName());
	    	}   	
	    	
	        return nameList;
	    }
    public List<WebServicePublisher> getAllPublishers() {
    	List<Publisher> publishers = (List<Publisher>) publisherDbMngr.getPublishers();
    	
        return WebServicePublisher.wrapPublisherList(publishers);
    }

	public void deletePublisherByName(String publisherName) {
		// TODO Maybe we could user Thread pool here
		publisherDbMngr.deletePublisherByName(publisherName);
		
	}

	public void deletePublisher(Long id) {
		// TODO Auto-generated method stub
		publisherDbMngr.deletePublisher(id);
	}

	public List<WebServicePublisher> findPublishers(String publisherName) {
		// TODO Auto-generated method stub
		List<Publisher> publishers = (List<Publisher>) publisherDbMngr.findPublishers(publisherName);
		
		return WebServicePublisher.wrapPublisherList(publishers);
	}

	public List<WebServicePublisher> findLimitedPublishers(String publisherName,
			Integer maxNumPerPage) {
		List<Publisher> publishers = (List<Publisher>) publisherDbMngr.findPublishers(publisherName, maxNumPerPage);
		
		return WebServicePublisher.wrapPublisherList(publishers);
	}

	public WebServicePublisher getPublisherByName(String publisherName) {
		
		return WebServicePublisher.wrapPublisher(publisherDbMngr.getPublisherByName(publisherName));
	}

	public WebServicePublisher getPublisher(Long id) {
		// TODO Auto-generated method stub
		return WebServicePublisher.wrapPublisher(publisherDbMngr.getPublisher(id));
	}

	public List<WebServicePublisher> getPublishers(Integer maxNumPerPage) {
		// TODO Auto-generated method stub
		List<Publisher> publishers = 
			(List<Publisher>) publisherDbMngr.getPublishers(maxNumPerPage);
		
		return WebServicePublisher.wrapPublisherList(publishers);
	}
	

	public void savePublisher(WebServicePublisher publisher) {
		publisherDbMngr.savePublisher(publisher.getPublisher());
		
	}
	public void addPublisher(String name, String address, 
			String postcode, String telephone,
			String fax, String email, 
			String contactPerson, String introduction){
		Publisher publisher = new Publisher();
		publisher.setName(name);
//		publisher.setAddress(address);
//		publisher.setPostcode(postcode);
//		publisher.setTelephone(telephone);
//		publisher.setFax(fax);
//		publisher.setEmail(email);
//		publisher.setContactPerson(contactPerson);		
		publisher.setIntroduction(introduction);
		publisherDbMngr.savePublisher(publisher);		
	}

}
