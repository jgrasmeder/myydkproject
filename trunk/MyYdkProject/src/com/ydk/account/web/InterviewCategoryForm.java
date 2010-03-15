/**
 * 
 */
package com.ydk.account.web;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


import com.ydk.book.persistence.entity.EventCategory;
import com.ydk.book.persistence.entity.InterviewCategory;


/**
 * @author y140zhan
 *
 */
public class InterviewCategoryForm {
	

	private InterviewCategory instance;
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
	public InterviewCategoryForm() {
		super();
		instance = new InterviewCategory();
		// TODO Auto-generated constructor stub
	}
	
	public InterviewCategoryForm(InterviewCategory instance) {
		super();
		this.instance = instance;

	}
	
    

	/**
	 * @param instance
	 * @param keys
	 */
	public InterviewCategoryForm(InterviewCategory instance, Long[] keys) {
		super();
		this.instance = instance;
		this.keys = keys;
	}
	

	/**
	 * @return the instance
	 */
	public InterviewCategory getInstance() {
		return instance;
	}

	/**
	 * @param instance the instance to set
	 */
	public void setInstance(InterviewCategory instance) {
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
	
	public void prepareForModify(InterviewCategory instance)
	{
		instance.setName(this.instance.getName());
		
	}


	
	
	
	
	

}
