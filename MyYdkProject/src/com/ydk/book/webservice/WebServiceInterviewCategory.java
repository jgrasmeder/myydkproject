/**
 * 
 */
package com.ydk.book.webservice;

/**
 * @author y140zhan
 *
 */

import com.ydk.book.persistence.entity.EventCategory;
import com.ydk.book.persistence.entity.InterviewCategory;


public final class WebServiceInterviewCategory implements java.io.Serializable {
    private InterviewCategory interviewCategory;
    private OperationStatus os;
    

	public WebServiceInterviewCategory() {
		// TODO Auto-generated constructor stub
		interviewCategory = null;
		os = new OperationStatus();
	}
    
	/**
	 * @param file
	 * @param os
	 */
	public WebServiceInterviewCategory(InterviewCategory interviewCategory) {
		this.interviewCategory = interviewCategory;
		this.os = new OperationStatus();
	}

	

	/**
	 * @return the interviewCategory
	 */
	public InterviewCategory getInterviewCategory() {
		return interviewCategory;
	}

	/**
	 * @param interviewCategory the interviewCategory to set
	 */
	public void setInterviewCategory(InterviewCategory interviewCategory) {
		this.interviewCategory = interviewCategory;
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
