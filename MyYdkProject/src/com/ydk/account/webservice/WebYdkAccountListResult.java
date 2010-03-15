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
import com.ydk.account.persistence.entity.Transaction;
import com.ydk.account.persistence.entity.YdkAccount;
import com.ydk.book.webservice.OperationStatus;


public final class WebYdkAccountListResult implements java.io.Serializable {

	List<YdkAccount> ydkAccounts;
	Integer totalItems;
	Integer totalPages;
	Integer currentPage;
	OperationStatus os;


	public WebYdkAccountListResult() {
		ydkAccounts = null;
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
	public WebYdkAccountListResult(List<YdkAccount> ydkAccounts, Integer totalItems,
			Integer totalPages, Integer currentPage) {
		super();
		this.ydkAccounts = ydkAccounts;
		this.totalItems = totalItems;
		this.totalPages = totalPages;
		this.currentPage = currentPage;
		this.os = new OperationStatus();
	}


    
    /**
	 * @return the ydkAccounts
	 */
	public List<YdkAccount> getYdkAccounts() {
		return ydkAccounts;
	}
	public void setYdkAccounts(List<YdkAccount> ydkAccounts) {
		this.ydkAccounts = ydkAccounts;
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
