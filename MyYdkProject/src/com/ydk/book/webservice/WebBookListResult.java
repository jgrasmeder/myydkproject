/**
 * 
 */
package com.ydk.book.webservice;

/**
 * @author y140zhan
 *
 */

import java.util.List;

import com.ydk.account.webservice.WebListResultInterface;
import com.ydk.book.persistence.entity.Book;


public final class WebBookListResult implements WebListResultInterface,java.io.Serializable {

	List<Book> books;
	Integer totalItems;
	Integer totalPages;
	Integer currentPage;
	OperationStatus os;
	

	public WebBookListResult() {
    	books = null;
    	totalItems = 0;
    	totalPages = 0;
    	currentPage = 0;
    	os = new OperationStatus();
    }
	
    public WebBookListResult(List<Book> books, Integer totalItems,
			Integer totalPages, Integer currentPage) {
		super();
		this.books = books;
		this.totalItems = totalItems;
		this.totalPages = totalPages;
		this.currentPage = currentPage;
		os = new OperationStatus();
	}
    
    public List<Book> getList() {
        return books;
    }
    public void setList(List books) {
        this.books = books;
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
	 * @return the books
	 */
	public List<Book> getBooks() {
		return books;
	}

	/**
	 * @param books the books to set
	 */
	public void setBooks(List<Book> books) {
		this.books = books;
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
