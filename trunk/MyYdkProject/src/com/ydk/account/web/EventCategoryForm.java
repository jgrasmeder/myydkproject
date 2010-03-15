/**
 * 
 */
package com.ydk.account.web;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


import com.ydk.book.persistence.entity.EventCategory;


/**
 * @author y140zhan
 *
 */
public class EventCategoryForm {
	

	private EventCategory instance;
//	private Long accountId;
//	private String name;
//	private String password;
//	private String repeatedPw;
//	private String email;
//	private Boolean isActived;
//	private FileUploadBean fileUploadBean;
//	private Long roleId;
	private Long[] keys ;
	
	/**
	 * 
	 */
	public EventCategoryForm() {
		super();
		instance = new EventCategory();
		// TODO Auto-generated constructor stub
	}
	
	public EventCategoryForm(EventCategory instance) {
		super();
		this.instance = instance;

	}
	
    

	/**
	 * @param instance
	 * @param keys
	 */
	public EventCategoryForm(EventCategory instance, Long[] keys) {
		super();
		this.instance = instance;
		this.keys = keys;
	}
	

	/**
	 * @return the instance
	 */
	public EventCategory getInstance() {
		return instance;
	}

	/**
	 * @param instance the instance to set
	 */
	public void setInstance(EventCategory instance) {
		this.instance = instance;
	}


	/**
	 * @return the keys
	 */
	public Long[] getKeys() {
		return keys;
	}

	/**
	 * @param keys the keys to set
	 */
	public void setKeys(Long[] keys) {
		this.keys = keys;
	}
	
	public void prepareForModify(EventCategory instance)
	{
		instance.setName(this.instance.getName());
		
	}


	
	
	
	
	

}
