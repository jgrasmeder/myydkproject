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

import com.ydk.account.persistence.entity.Library;
import com.ydk.account.persistence.entity.UploadedFile;


public final class WebServiceLibrary implements java.io.Serializable {
    private Library library;
    private OperationStatus os;
    

	public WebServiceLibrary() {
		// TODO Auto-generated constructor stub
		library = null;
		os = new OperationStatus();
	}
    
	/**
	 * @param file
	 * @param os
	 */
	public WebServiceLibrary(Library library) {
		this.library = library;
		this.os = new OperationStatus();
	}


	/**
	 * @return the library
	 */
	public Library getLibrary() {
		return library;
	}
	public void setLibrary(Library library) {
		this.library = library;
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
