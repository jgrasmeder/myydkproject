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
import com.ydk.book.persistence.entity.BookReview;


public final class WebServiceBookReview implements java.io.Serializable {
    private BookReview bookReview;
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
	

	public WebServiceBookReview() {
		// TODO Auto-generated constructor stub
		bookReview = null;
		os = new OperationStatus();
	}
    
	/**
	 * @param bookReview
	 * @param os
	 */
	public WebServiceBookReview(BookReview bookReview) {
		this.bookReview = bookReview;
		this.os = new OperationStatus();
	}

	/**
	 * @return the bookReview
	 */
	public BookReview getBookReview() {
		return bookReview;
	}

	/**
	 * @param bookReview the bookReview to set
	 */
	public void setBookReview(BookReview bookReview) {
		this.bookReview = bookReview;
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
