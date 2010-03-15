/**
 * 
 */
package com.ydk.account.webservice;

/**
 * @author y140zhan
 *
 */

import com.ydk.account.persistence.entity.Partner;
import com.ydk.account.persistence.entity.Transaction;
import com.ydk.book.webservice.OperationStatus;


public final class WebServiceTransaction implements java.io.Serializable {
    private Transaction transaction;
    private OperationStatus os;
    

	public WebServiceTransaction() {
		// TODO Auto-generated constructor stub
		this.os = new OperationStatus();
	}
    
	/**
	 * @param event
	 * @param os
	 */
	public WebServiceTransaction(Transaction transaction) {
		this.transaction = transaction;
		this.os = new OperationStatus();
	}

	/**
	 * @return the transaction
	 */
	public Transaction getTransaction() {
		return transaction;
	}

	/**
	 * @param transaction the transaction to set
	 */
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
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
