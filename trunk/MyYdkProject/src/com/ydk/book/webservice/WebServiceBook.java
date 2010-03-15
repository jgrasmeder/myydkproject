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

import javax.activation.DataHandler;

import org.apache.cxf.aegis.type.java5.IgnoreProperty;

import com.ydk.book.persistence.entity.Book;
import com.ydk.account.persistence.entity.*;

public final class WebServiceBook {
    private Book book;
    private Key key;
    private UploadedFile bookPackage;
    private List<UploadedFile> bookPackages = null;
    private UploadedFile uploadedImageSmall;
    private UploadedFile uploadedImageLarge;
    private UploadedFile uploadedImage3DSmall;
    private UploadedFile uploadedImage3DLarge;
    OperationStatus os ;
    
    public WebServiceBook() {
    	book = null;
    	os= new OperationStatus();
    }
    
    public WebServiceBook(Book book) {
    	this.book = book;
    	os= new OperationStatus();
    }
    

    /**
	 * @param book
	 * @param bookPackage
	 * @param os
	 */
	public WebServiceBook(Book book, UploadedFile bookPackage,
			OperationStatus os) {
		super();
		this.book = book;
		this.bookPackage = bookPackage;
		this.os = os;
	}



	public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
    

    
    
    
	/**
	 * @return the uploadedImageSmall
	 */
	public UploadedFile getUploadedImageSmall() {
		return uploadedImageSmall;
	}

	/**
	 * @param uploadedImageSmall the uploadedImageSmall to set
	 */
	public void setUploadedImageSmall(UploadedFile uploadedImageSmall) {
		this.uploadedImageSmall = uploadedImageSmall;
	}

	/**
	 * @return the uploadedImageLarge
	 */
	public UploadedFile getUploadedImageLarge() {
		return uploadedImageLarge;
	}

	/**
	 * @param uploadedImageLarge the uploadedImageLarge to set
	 */
	public void setUploadedImageLarge(UploadedFile uploadedImageLarge) {
		this.uploadedImageLarge = uploadedImageLarge;
	}

	/**
	 * @return the uploadedImage3DSmall
	 */
	public UploadedFile getUploadedImage3DSmall() {
		return uploadedImage3DSmall;
	}

	/**
	 * @param uploadedImage3DSmall the uploadedImage3DSmall to set
	 */
	public void setUploadedImage3DSmall(UploadedFile uploadedImage3DSmall) {
		this.uploadedImage3DSmall = uploadedImage3DSmall;
	}

	/**
	 * @return the uploadedImage3DLarge
	 */
	public UploadedFile getUploadedImage3DLarge() {
		return uploadedImage3DLarge;
	}

	/**
	 * @param uploadedImage3DLarge the uploadedImage3DLarge to set
	 */
	public void setUploadedImage3DLarge(UploadedFile uploadedImage3DLarge) {
		this.uploadedImage3DLarge = uploadedImage3DLarge;
	}

	/**
	 * @return the bookPackage
	 */
	public UploadedFile getBookPackage() {
		return bookPackage;
	}


	/**
	 * @param bookPackage the bookPackage to set
	 */
	public void setBookPackage(UploadedFile bookPackage) {
		this.bookPackage = bookPackage;
	}
	
	


	/**
	 * @return the bookPackages
	 */
	public List<UploadedFile> getBookPackages() {
		return bookPackages;
	}

	/**
	 * @param bookPackages the bookPackages to set
	 */
	public void setBookPackages(List<UploadedFile> bookPackages) {
		this.bookPackages = bookPackages;
	}
	
	

	/**
	 * @return the key
	 */
	public Key getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(Key key) {
		this.key = key;
	}

	/**
	 * @return the os
	 */
	public OperationStatus getOs() {
		return os;
	}
	public void setOs(OperationStatus os) {
		this.os = os;
	}
	
}
