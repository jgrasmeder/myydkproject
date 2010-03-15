/**
 * 
 */
package com.ydk.account.webservice;

/**
 * @author y140zhan
 *
 */

import com.ydk.account.persistence.entity.Partner;
import com.ydk.account.persistence.entity.PartnerType;
import com.ydk.account.persistence.entity.TransactionType;
import com.ydk.book.webservice.OperationStatus;


public final class WebServiceTransactionType implements java.io.Serializable {
    private TransactionType transactionType;
    private OperationStatus os;
    

	public WebServiceTransactionType() {
		// TODO Auto-generated constructor stub
		this.os = new OperationStatus();
	}
    
	/**
	 * @param event
	 * @param os
	 */
	public WebServiceTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
		this.os = new OperationStatus();
	}

	/**
	 * @return the transactionType
	 */
	public TransactionType getTransactionType() {
		return transactionType;
	}

	/**
	 * @param transactionType the transactionType to set
	 */
	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
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
