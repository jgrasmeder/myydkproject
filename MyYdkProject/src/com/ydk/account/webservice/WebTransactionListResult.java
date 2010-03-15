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
import com.ydk.account.persistence.entity.Transaction;
import com.ydk.book.webservice.OperationStatus;


public final class WebTransactionListResult implements WebListResultInterface,java.io.Serializable {

	List<Transaction> transactions;
	Integer totalItems;
	Integer totalPages;
	Integer currentPage;
	OperationStatus os;


	public WebTransactionListResult() {
		transactions = null;
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
	public WebTransactionListResult(List<Transaction> transactions, Integer totalItems,
			Integer totalPages, Integer currentPage) {
		super();
		this.transactions = transactions;
		this.totalItems = totalItems;
		this.totalPages = totalPages;
		this.currentPage = currentPage;
		this.os = new OperationStatus();
	}



	public List<Transaction> getList() {
        return transactions;
    }
    public void setList(List transactions) {
        this.transactions = transactions;
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
