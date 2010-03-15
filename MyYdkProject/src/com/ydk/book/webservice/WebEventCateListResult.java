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
import com.ydk.book.persistence.entity.EventCategory;
import com.ydk.book.persistence.entity.Interview;
import com.ydk.book.persistence.entity.InterviewCategory;


public final class WebEventCateListResult implements WebListResultInterface,java.io.Serializable {

	List<EventCategory> eventCategories;
	Integer totalItems;
	Integer totalPages;
	Integer currentPage;
	OperationStatus os;

	
	public WebEventCateListResult() {
		eventCategories = null;
    	totalItems = 0;
    	totalPages = 0;
    	currentPage = 0;
    	os = new OperationStatus();
    }
	
	public WebEventCateListResult(List<EventCategory> eventCategories, Integer totalItems,
			Integer totalPages, Integer currentPage) {
		super();
		this.eventCategories = eventCategories;
		this.totalItems = totalItems;
		this.totalPages = totalPages;
		this.currentPage = currentPage;
		this.os = new OperationStatus();
	}



	public List<EventCategory> getList() {
        return eventCategories;
    }
    public void setList(List eventCategories) {
        this.eventCategories = eventCategories;
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
