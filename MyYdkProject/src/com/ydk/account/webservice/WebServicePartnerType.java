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
import com.ydk.book.webservice.OperationStatus;


public final class WebServicePartnerType implements java.io.Serializable {
    private PartnerType partnerType;
    private OperationStatus os;
    

	public WebServicePartnerType() {
		// TODO Auto-generated constructor stub
		this.os = new OperationStatus();
	}
    
	/**
	 * @param event
	 * @param os
	 */
	public WebServicePartnerType(PartnerType partnerType) {
		this.partnerType = partnerType;
		this.os = new OperationStatus();
	}

	/**
	 * @return the partnerType
	 */
	public PartnerType getPartnerType() {
		return partnerType;
	}

	/**
	 * @param partnerType the partnerType to set
	 */
	public void setPartnerType(PartnerType partnerType) {
		this.partnerType = partnerType;
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
