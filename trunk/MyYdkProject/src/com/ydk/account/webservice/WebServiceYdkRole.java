/**
 * 
 */
package com.ydk.account.webservice;

/**
 * @author y140zhan
 *
 */

import com.ydk.account.persistence.entity.YdkRole;
import com.ydk.book.webservice.OperationStatus;


public final class WebServiceYdkRole implements java.io.Serializable {
    private YdkRole ydkRole;
    private OperationStatus os;
    

	public WebServiceYdkRole() {
		// TODO Auto-generated constructor stub
		this.os = new OperationStatus();
	}
    
	/**
	 * @param event
	 * @param os
	 */
	public WebServiceYdkRole(YdkRole ydkRole) {
		this.ydkRole = ydkRole;
		this.os = new OperationStatus();
	}

	

	

	/**
	 * @return the ydkRole
	 */
	public YdkRole getYdkRole() {
		return ydkRole;
	}

	/**
	 * @param ydkRole the ydkRole to set
	 */
	public void setYdkRole(YdkRole ydkRole) {
		this.ydkRole = ydkRole;
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
