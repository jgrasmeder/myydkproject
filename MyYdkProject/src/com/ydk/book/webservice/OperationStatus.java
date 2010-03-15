/**
 * 
 */
package com.ydk.book.webservice;

/**
 * @author y140zhan
 *
 */

import java.util.List;
import com.ydk.book.persistence.entity.Book;


public final class OperationStatus implements java.io.Serializable {

	Boolean isSuccessful;
	String failureReason;
	
	
	public OperationStatus() {
		super();
		isSuccessful = true;
		failureReason = null;
		// TODO Auto-generated constructor stub
	}
	
	public OperationStatus(Boolean isSuccessful, String failureReason) {
		super();
		this.isSuccessful = isSuccessful;
		this.failureReason = failureReason;
	}
	
	/**
	 * @return the isSuccessful
	 */
	public Boolean getIsSuccessful() {
		return isSuccessful;
	}
	/**
	 * @param isSuccessful the isSuccessful to set
	 */
	public void setIsSuccessful(Boolean isSuccessful) {
		this.isSuccessful = isSuccessful;
	}
	/**
	 * @return the failureReason
	 */
	public String getFailureReason() {
		return failureReason;
	}
	/**
	 * @param failureReason the failureReason to set
	 */
	public void setFailureReason(String failureReason) {
		this.failureReason = failureReason;
	}
	
	
}
