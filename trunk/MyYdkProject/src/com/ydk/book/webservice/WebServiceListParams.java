package com.ydk.book.webservice;

import java.io.Serializable;

public class WebServiceListParams implements Serializable {
	
	public static final Integer MAX_PAGESIZE = 99999;
	Integer pageSize;
	Integer pageOffset;
	String attrbute1;
	String attrbute2;
	String attrbute3;

	public WebServiceListParams()
	{
		
	}
	
	public WebServiceListParams(Integer pageSize, Integer pageOffset,
			String attrbute1, String attrbute2, String attrbute3) {
		super();
		this.pageSize = pageSize;
		this.pageOffset = pageOffset;
		this.attrbute1 = attrbute1;
		this.attrbute2 = attrbute2;
		this.attrbute3 = attrbute3;
	}
	/**
	 * @return the pageOffset
	 */
	public Integer getPageOffset() {
		return pageOffset;
	}
	/**
	 * @param pageOffset the pageOffset to set
	 */
	public void setPageOffset(Integer pageOffset) {
		this.pageOffset = pageOffset;
	}
	/**
	 * @return the attrbute1
	 */
	public String getAttrbute1() {
		return attrbute1;
	}
	/**
	 * @param attrbute1 the attrbute1 to set
	 */
	public void setAttrbute1(String attrbute1) {
		this.attrbute1 = attrbute1;
	}
	/**
	 * @return the attrbute2
	 */
	public String getAttrbute2() {
		return attrbute2;
	}
	/**
	 * @param attrbute2 the attrbute2 to set
	 */
	public void setAttrbute2(String attrbute2) {
		this.attrbute2 = attrbute2;
	}
	/**
	 * @return the attrbute3
	 */
	public String getAttrbute3() {
		return attrbute3;
	}
	/**
	 * @param attrbute3 the attrbute3 to set
	 */
	public void setAttrbute3(String attrbute3) {
		this.attrbute3 = attrbute3;
	}
	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	

}
