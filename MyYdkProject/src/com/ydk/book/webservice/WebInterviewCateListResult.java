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
import com.ydk.book.persistence.entity.Interview;
import com.ydk.book.persistence.entity.InterviewCategory;


public final class WebInterviewCateListResult implements WebListResultInterface,java.io.Serializable {

	List<InterviewCategory> interviewCategories;
	Integer totalItems;
	Integer totalPages;
	Integer currentPage;
	OperationStatus os;

	
	public WebInterviewCateListResult() {
		interviewCategories = null;
    	totalItems = 0;
    	totalPages = 0;
    	currentPage = 0;
    	os = new OperationStatus();
    }
	
	public WebInterviewCateListResult(List<InterviewCategory> interviewCategories, Integer totalItems,
			Integer totalPages, Integer currentPage) {
		super();
		this.interviewCategories = interviewCategories;
		this.totalItems = totalItems;
		this.totalPages = totalPages;
		this.currentPage = currentPage;
		this.os = new OperationStatus();
	}



	public List<InterviewCategory> getList() {
        return interviewCategories;
    }
    public void setList(List interviewCategories) {
        this.interviewCategories = interviewCategories;
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
