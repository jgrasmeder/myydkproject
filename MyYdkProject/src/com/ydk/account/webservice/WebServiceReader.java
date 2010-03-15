/**
 * 
 */
package com.ydk.account.webservice;

/**
 * @author y140zhan
 *
 */


import com.ydk.account.persistence.entity.Reader;
import com.ydk.account.persistence.entity.UploadedFile;
import com.ydk.book.webservice.OperationStatus;


public final class WebServiceReader implements java.io.Serializable {
    private Reader reader;
    private OperationStatus os;
    private UploadedFile portraitImage;
    

	public WebServiceReader() {
		// TODO Auto-generated constructor stub
		this.os = new OperationStatus();
	}
    
	/**
	 * @param event
	 * @param os
	 */
	public WebServiceReader(Reader reader) {
		this.reader = reader;
		this.os = new OperationStatus();
	}

	/**
	 * @return the reader
	 */
	public Reader getReader() {
		return reader;
	}

	/**
	 * @param reader the reader to set
	 */
	public void setReader(Reader reader) {
		this.reader = reader;
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

	/**
	 * @return the portraitImage
	 */
	public UploadedFile getPortraitImage() {
		return portraitImage;
	}
	public void setPortraitImage(UploadedFile portraitImage) {
		this.portraitImage = portraitImage;
	}
	
	
	
	


    
}
