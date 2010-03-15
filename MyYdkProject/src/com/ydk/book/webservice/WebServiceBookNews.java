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
import com.ydk.book.persistence.entity.BookNews;


public final class WebServiceBookNews implements java.io.Serializable {
    private BookNews bookNews;
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
	

	public WebServiceBookNews() {
		// TODO Auto-generated constructor stub
		bookNews = null;
		os = new OperationStatus();
	}
    
	/**
	 * @param file
	 * @param os
	 */
	public WebServiceBookNews(BookNews bookNews) {
		this.bookNews = bookNews;
		this.os = new OperationStatus();
	}

	

	/**
	 * @return the bookNews
	 */
	public BookNews getBookNews() {
		return bookNews;
	}

	/**
	 * @param bookNews the bookNews to set
	 */
	public void setBookNews(BookNews bookNews) {
		this.bookNews = bookNews;
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
