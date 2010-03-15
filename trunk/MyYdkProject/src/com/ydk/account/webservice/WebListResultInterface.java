/**
 * 
 */
package com.ydk.account.webservice;

import java.util.List;

import com.ydk.account.persistence.entity.Partner;

/**
 * @author y140zhan
 *
 */
public interface WebListResultInterface {
	
	public List getList();
	public void setList(List list);
	
	public Integer getTotalItems();
    public void setTotalItems(Integer totalItems);
    
    public Integer getTotalPages();
    public void setTotalPages(Integer totalPages);
    
    public Integer getCurrentPage();
    public void setCurrentPage(Integer currentPage);

}
