/**
 * 
 */
package com.ydk.book.webservice;

/**
 * @author y140zhan
 *
 */

import java.util.ArrayList;
import java.util.List;

import com.ydk.account.persistence.entity.UploadedFile;
import com.ydk.book.persistence.entity.Interview;


public final class WebServiceInterview implements java.io.Serializable {
    private Interview interview;
    private OperationStatus os;
    
    private UploadedFile contentFile;
    private UploadedFile titleImage;
    private List<UploadedFile> contentImages = new ArrayList<UploadedFile>(0);

    /**
	 * @return the contentFile
	 */
	public UploadedFile getContentFile() {
		return contentFile;
	}

	/**
	 * @param contentFile the contentFile to set
	 */
	public void setContentFile(UploadedFile contentFile) {
		this.contentFile = contentFile;
	}

	/**
	 * @return the titleImage
	 */
	public UploadedFile getTitleImage() {
		return titleImage;
	}

	/**
	 * @param titleImage the titleImage to set
	 */
	public void setTitleImage(UploadedFile titleImage) {
		this.titleImage = titleImage;
	}

	/**
	 * @return the contentImages
	 */
	public List<UploadedFile> getContentImages() {
		return contentImages;
	}

	/**
	 * @param contentImages the contentImages to set
	 */
	public void setContentImages(List<UploadedFile> contentImages) {
		this.contentImages = contentImages;
	}
	
	
    
    
	public WebServiceInterview() {
		// TODO Auto-generated constructor stub
		interview = null;
		this.os = new OperationStatus();
	}
    
	/**
	 * @param event
	 * @param os
	 */
	public WebServiceInterview(Interview interview) {
		this.interview = interview;
		this.os = new OperationStatus();
	}
	
	


	

	/**
	 * @return the interview
	 */
	public Interview getInterview() {
		return interview;
	}

	/**
	 * @param interview the interview to set
	 */
	public void setInterview(Interview interview) {
		this.interview = interview;
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
