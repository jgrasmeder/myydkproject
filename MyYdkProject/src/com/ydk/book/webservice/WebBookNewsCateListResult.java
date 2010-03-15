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
import com.ydk.book.persistence.entity.BookNews;
import com.ydk.book.persistence.entity.BookNewsCategory;
import com.ydk.book.persistence.entity.Interview;


public final class WebBookNewsCateListResult implements WebListResultInterface,java.io.Serializable {

	List<BookNewsCategory> bookNewsCategories;
	Integer totalItems;
	Integer totalPages;
	Integer currentPage;
	OperationStatus os;


	public WebBookNewsCateListResult() {
		bookNewsCategories = null;
    	totalItems = 0;
    	totalPages = 0;
    	currentPage = 0;
    	os = new OperationStatus();
    }
	

    /**
	 * @param bookNewsCategories
	 * @param totalItems
	 * @param totalPages
	 * @param currentPage
	 * @param os
	 */
	public WebBookNewsCateListResult(List<BookNewsCategory> bookNewsCategories, Integer totalItems,
			Integer totalPages, Integer currentPage) {
		super();
		this.bookNewsCategories = bookNewsCategories;
		this.totalItems = totalItems;
		this.totalPages = totalPages;
		this.currentPage = currentPage;
		this.os = new OperationStatus();
	}



	public List<BookNewsCategory> getList() {
        return bookNewsCategories;
    }
    public void setList(List bookNewsCategories) {
        this.bookNewsCategories = bookNewsCategories;
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
}
