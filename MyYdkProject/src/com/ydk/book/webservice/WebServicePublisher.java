/**
 * 
 */
package com.ydk.book.webservice;

/**
 * @author y140zhan
 *
 */

import java.util.ArrayList;
import java.util.List;

import com.ydk.account.persistence.entity.UploadedFile;
import com.ydk.book.persistence.entity.Author;
import com.ydk.book.persistence.entity.Book;
import com.ydk.book.persistence.entity.Publisher;


public final class WebServicePublisher implements java.io.Serializable {
    private Publisher publisher;
    private OperationStatus os;
    
    
	

	public WebServicePublisher() {
		// TODO Auto-generated constructor stub
		publisher = null;
		os = new OperationStatus();
	}
    
	/**
	 * @param publisher
	 * @param os
	 */
	public WebServicePublisher(Publisher publisher) {
		this.publisher = publisher;
		this.os = new OperationStatus();
	}

	

	/**
	 * @return the publisher
	 */
	public Publisher getPublisher() {
		return publisher;
	}

	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	/**
	 * @return the os
	 */
	public OperationStatus getOs() {
		return os;
	}

	/**
	 * @param os the os to set
	 */
	public void setOs(OperationStatus os) {
		this.os = os;
	}
	
	
	public static WebServicePublisher wrapPublisher(Publisher publisher){
	return new WebServicePublisher(publisher);
}

	public static List<WebServicePublisher> wrapPublisherList(List<Publisher> publishers){
	List<WebServicePublisher> webPublishers = new ArrayList<WebServicePublisher>();
	for (Publisher publisher: publishers)
	{
		webPublishers.add(new WebServicePublisher(publisher));
	}
	return webPublishers;
}

	public static Publisher stripPublisher(WebServicePublisher publisher){
		return publisher.getPublisher();
	}
	public static List<Publisher> stripPublisherList(List<WebServicePublisher> publishers){
    	List<Publisher> webPublishers = new ArrayList<Publisher>();
    	for (WebServicePublisher publisher: publishers)
    	{
    		webPublishers.add(publisher.getPublisher());
    	}
		return webPublishers;
	}
    
}
