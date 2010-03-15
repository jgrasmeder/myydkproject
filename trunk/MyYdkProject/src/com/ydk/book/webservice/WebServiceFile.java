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
import com.ydk.account.persistence.entity.UploadedFile;


public final class WebServiceFile implements java.io.Serializable {
    private UploadedFile file;
    private OperationStatus os;
    

	public WebServiceFile() {
		// TODO Auto-generated constructor stub
		file = null;
		os = new OperationStatus();
	}
    
	/**
	 * @param file
	 * @param os
	 */
	public WebServiceFile(UploadedFile file) {
		this.file = file;
		this.os = new OperationStatus();
	}

	/**
	 * @return the file
	 */
	public UploadedFile getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(UploadedFile file) {
		this.file = file;
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
