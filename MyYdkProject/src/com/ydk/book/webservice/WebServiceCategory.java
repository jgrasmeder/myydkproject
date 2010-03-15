/**
 * 
 */
package com.ydk.book.webservice;

/**
 * @author y140zhan
 *
 */

import java.util.List;
import org.apache.cxf.aegis.type.java5.IgnoreProperty;

import com.ydk.account.persistence.entity.Category;
import com.ydk.account.persistence.entity.Library;
import com.ydk.account.persistence.entity.UploadedFile;


public final class WebServiceCategory implements java.io.Serializable {
    private Category category;
    private OperationStatus os;
    

	public WebServiceCategory() {
		// TODO Auto-generated constructor stub
		category = null;
		os = new OperationStatus();
	}
    
	/**
	 * @param file
	 * @param os
	 */
	public WebServiceCategory(Category category) {
		this.category = category;
		this.os = new OperationStatus();
	}



	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
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
