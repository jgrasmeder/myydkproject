package com.ydk.account.webservice;

import java.util.List;

import org.springframework.beans.support.PagedListHolder;

import com.ydk.book.webservice.WebServiceListParams;

public class WebListResultHelper {
	
	public static WebListResultInterface getSpecifiedPageList(List list, WebServiceListParams listParams,
			WebListResultInterface result)
	{
		PagedListHolder pl = new PagedListHolder(list);
		pl.setPageSize(listParams.getPageSize());
		
		result.setTotalItems(list.size());
		result.setTotalPages(pl.getPageCount());
		int currentPage = listParams.getPageOffset();
		if (currentPage >= pl.getPageCount())
		{
			currentPage = pl.getPageCount() - 1;
		}
		
		result.setCurrentPage(currentPage);
		pl.setPage(currentPage);
		result.setList(pl.getPageList());
		return result;
	}

}
