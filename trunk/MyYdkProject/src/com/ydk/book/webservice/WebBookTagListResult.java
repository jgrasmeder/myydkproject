/**
 * 
 */
package com.ydk.book.webservice;

/**
 * @author y140zhan
 *
 */

import java.util.List;

import com.ydk.account.persistence.entity.BookMark;
import com.ydk.account.webservice.WebListResultInterface;
import com.ydk.book.persistence.entity.BookTag;
import com.ydk.book.persistence.entity.Event;
import com.ydk.book.persistence.entity.Interview;


public final class WebBookTagListResult implements WebListResultInterface,java.io.Serializable {

	List<BookTag> bookTags;
	Integer totalItems;
	Integer totalPages;
	Integer currentPage;
	OperationStatus os;


	public WebBookTagListResult() {
		bookTags = null;
    	totalItems = 0;
    	totalPages = 0;
    	currentPage = 0;
    	os = new OperationStatus();
    }
	

    /**
	 * @param interviewCategories
	 * @param totalItems
	 * @param totalPages
	 * @param currentPage
	 * @param os
	 */
	public WebBookTagListResult(List<BookTag> bookTags, Integer totalItems,
			Integer totalPages, Integer currentPage) {
		super();
		this.bookTags = bookTags;
		this.totalItems = totalItems;
		this.totalPages = totalPages;
		this.currentPage = currentPage;
		this.os = new OperationStatus();
	}



	public List<BookTag> getList() {
        return bookTags;
    }
    public void setList(List bookTags) {
        this.bookTags = bookTags;
    }
    
    public Integer getTotalItems() {
        return totalItems;
    }
    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }
    
    public Integer getTotalPages() {
        return totalPages;
    }
    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
    
    public Integer getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
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


	/**
	 * @return the bookTags
	 */
	public List<BookTag> getBookTags() {
		return bookTags;
	}


	/**
	 * @param bookTags the bookTags to set
	 */
	public void setBookTags(List<BookTag> bookTags) {
		this.bookTags = bookTags;
	}
	
}
