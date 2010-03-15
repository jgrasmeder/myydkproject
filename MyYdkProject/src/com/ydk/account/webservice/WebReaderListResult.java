/**
 * 
 */
package com.ydk.account.webservice;

/**
 * @author y140zhan
 *
 */

import java.util.List;

import com.ydk.account.persistence.entity.BookMark;
import com.ydk.account.persistence.entity.Partner;
import com.ydk.account.persistence.entity.Reader;
import com.ydk.account.persistence.entity.Transaction;
import com.ydk.book.webservice.OperationStatus;


public final class WebReaderListResult implements WebListResultInterface, java.io.Serializable {

	List<Reader> readers;
	Integer totalItems;
	Integer totalPages;
	Integer currentPage;
	OperationStatus os;


	public WebReaderListResult() {
		readers = null;
    	totalItems = 0;
    	totalPages = 0;
    	currentPage = 0;
    	os = new OperationStatus();
    }
	

    /**
	 * @param transactions
	 * @param totalItems
	 * @param totalPages
	 * @param currentPage
	 * @param os
	 */
	public WebReaderListResult(List<Reader> readers, Integer totalItems,
			Integer totalPages, Integer currentPage) {
		super();
		this.readers = readers;
		this.totalItems = totalItems;
		this.totalPages = totalPages;
		this.currentPage = currentPage;
		this.os = new OperationStatus();
	}


    
    /**
	 * @return the readers
	 */
	public List<Reader> getList() {
		return readers;
	}
	public void setList(List readers) {
		this.readers = readers;
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
