/**
 * 
 */
package com.ydk.book.webservice;

/**
 * @author y140zhan
 *
 */

import com.ydk.book.persistence.entity.EventCategory;


public final class WebServiceEventCategory implements java.io.Serializable {
    private EventCategory eventCategory;
    private OperationStatus os;
    

	public WebServiceEventCategory() {
		// TODO Auto-generated constructor stub
		eventCategory = null;
		os = new OperationStatus();
	}
    
	/**
	 * @param file
	 * @param os
	 */
	public WebServiceEventCategory(EventCategory eventCategory) {
		this.eventCategory = eventCategory;
		this.os = new OperationStatus();
	}

	

	/**
	 * @return the eventCategory
	 */
	public EventCategory getEventCategory() {
		return eventCategory;
	}

	/**
	 * @param eventCategory the eventCategory to set
	 */
	public void setEventCategory(EventCategory eventCategory) {
		this.eventCategory = eventCategory;
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
	
	


    
}
