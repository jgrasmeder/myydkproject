/**
 * 
 */
package com.ydk.book.webservice;

/**
 * @author y140zhan
 *
 */

import com.ydk.book.persistence.entity.BookNews;
import com.ydk.book.persistence.entity.BookNewsCategory;


public final class WebServiceBookNewsCategory implements java.io.Serializable {
    private BookNewsCategory bookNewsCategory;
    private OperationStatus os;
    

	public WebServiceBookNewsCategory() {
		// TODO Auto-generated constructor stub
		bookNewsCategory = null;
		os = new OperationStatus();
	}
    
	/**
	 * @param file
	 * @param os
	 */
	public WebServiceBookNewsCategory(BookNewsCategory bookNewsCategory) {
		this.bookNewsCategory = bookNewsCategory;
		this.os = new OperationStatus();
	}

	

	/**
	 * @return the bookNewsCategory
	 */
	public BookNewsCategory getBookNewsCategory() {
		return bookNewsCategory;
	}

	/**
	 * @param bookNewsCategory the bookNewsCategory to set
	 */
	public void setBookNewsCategory(BookNewsCategory bookNewsCategory) {
		this.bookNewsCategory = bookNewsCategory;
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
