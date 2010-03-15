/**
 * 
 */
package com.ydk.account.webservice;

/**
 * @author y140zhan
 *
 */

import com.ydk.account.persistence.entity.Partner;
import com.ydk.book.webservice.OperationStatus;


public final class WebServicePartner implements java.io.Serializable {
    private Partner partner;
    private OperationStatus os;
    

	public WebServicePartner() {
		// TODO Auto-generated constructor stub
		this.os = new OperationStatus();
	}
    
	/**
	 * @param event
	 * @param os
	 */
	public WebServicePartner(Partner partner) {
		this.partner = partner;
		this.os = new OperationStatus();
	}

	/**
	 * @return the partner
	 */
	public Partner getPartner() {
		return partner;
	}

	/**
	 * @param partner the partner to set
	 */
	public void setPartner(Partner partner) {
		this.partner = partner;
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
