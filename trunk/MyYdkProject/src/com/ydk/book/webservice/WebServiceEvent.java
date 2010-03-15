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
import com.ydk.book.persistence.entity.Event;


public final class WebServiceEvent implements java.io.Serializable {
    private Event event;
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
	

	public WebServiceEvent() {
		// TODO Auto-generated constructor stub
		event = null;
		os = new OperationStatus();
	}
    
	/**
	 * @param event
	 * @param os
	 */
	public WebServiceEvent(Event event) {
		this.event = event;
		this.os = new OperationStatus();
	}

	

	/**
	 * @return the event
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * @param event the event to set
	 */
	public void setEvent(Event event) {
		this.event = event;
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
