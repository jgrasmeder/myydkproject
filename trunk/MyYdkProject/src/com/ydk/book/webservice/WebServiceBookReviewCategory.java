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
import com.ydk.book.persistence.entity.BookReviewCategory;


public final class WebServiceBookReviewCategory implements java.io.Serializable {
    private BookReviewCategory bookReviewCategory;
    private OperationStatus os;
    

	public WebServiceBookReviewCategory() {
		// TODO Auto-generated constructor stub
		bookReviewCategory = null;
		os = new OperationStatus();
	}
    
	/**
	 * @param file
	 * @param os
	 */
	public WebServiceBookReviewCategory(BookReviewCategory bookReviewCategory) {
		this.bookReviewCategory = bookReviewCategory;
		this.os = new OperationStatus();
	}

	

	/**
	 * @return the bookReviewCategory
	 */
	public BookReviewCategory getBookReviewCategory() {
		return bookReviewCategory;
	}

	/**
	 * @param bookReviewCategory the bookReviewCategory to set
	 */
	public void setBookReviewCategory(BookReviewCategory bookReviewCategory) {
		this.bookReviewCategory = bookReviewCategory;
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
