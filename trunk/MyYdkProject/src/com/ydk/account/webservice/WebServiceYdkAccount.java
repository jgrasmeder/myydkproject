/**
 * 
 */
package com.ydk.account.webservice;

/**
 * @author y140zhan
 *
 */

import com.ydk.account.persistence.entity.YdkAccount;
import com.ydk.book.webservice.OperationStatus;


public final class WebServiceYdkAccount implements java.io.Serializable {
    private YdkAccount ydkAccount;
    private OperationStatus os;
    

	public WebServiceYdkAccount() {
		// TODO Auto-generated constructor stub
		this.os = new OperationStatus();
	}
    
	/**
	 * @param event
	 * @param os
	 */
	public WebServiceYdkAccount(YdkAccount ydkAccount) {
		this.ydkAccount = ydkAccount;
		this.os = new OperationStatus();
	}

	

	/**
	 * @return the ydkAccount
	 */
	public YdkAccount getYdkAccount() {
		return ydkAccount;
	}

	/**
	 * @param ydkAccount the ydkAccount to set
	 */
	public void setYdkAccount(YdkAccount ydkAccount) {
		this.ydkAccount = ydkAccount;
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
