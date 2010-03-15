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
import com.ydk.account.persistence.entity.YdkRole;
import com.ydk.book.webservice.OperationStatus;


public final class WebYdkRoleListResult implements java.io.Serializable {

	List<YdkRole> ydkRoles;
	Integer totalItems;
	Integer totalPages;
	Integer currentPage;
	OperationStatus os;


	public WebYdkRoleListResult() {
		ydkRoles = null;
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
	public WebYdkRoleListResult(List<YdkRole> ydkRoles, Integer totalItems,
			Integer totalPages, Integer currentPage) {
		super();
		this.ydkRoles = ydkRoles;
		this.totalItems = totalItems;
		this.totalPages = totalPages;
		this.currentPage = currentPage;
		this.os = new OperationStatus();
	}


    
    /**
	 * @return the ydkRoles
	 */
	public List<YdkRole> getYdkRoles() {
		return ydkRoles;
	}
	public void setYdkRoles(List<YdkRole> ydkRoles) {
		this.ydkRoles = ydkRoles;
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
