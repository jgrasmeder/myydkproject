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
import com.ydk.book.persistence.entity.BookTag;
import com.ydk.book.persistence.entity.Event;


public final class WebServiceBookTag implements java.io.Serializable {
    private BookTag bookTag;
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
	

	public WebServiceBookTag() {
		// TODO Auto-generated constructor stub
		bookTag = null;
		os = new OperationStatus();
	}
    
	/**
	 * @param bookTag
	 * @param os
	 */
	public WebServiceBookTag(BookTag bookTag) {
		this.bookTag = bookTag;
		this.os = new OperationStatus();
	}

	

	/**
	 * @return the bookTag
	 */
	public BookTag getBookTag() {
		return bookTag;
	}

	/**
	 * @param bookTag the bookTag to set
	 */
	public void setBookTag(BookTag bookTag) {
		this.bookTag = bookTag;
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
