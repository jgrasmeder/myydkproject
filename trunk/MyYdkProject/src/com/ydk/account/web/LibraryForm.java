/**
 * 
 */
package com.ydk.account.web;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


import com.ydk.account.core.FileUploadBean;
import com.ydk.account.persistence.entity.FileDescriptor;
import com.ydk.account.persistence.entity.Library;
import com.ydk.account.persistence.entity.Partner;
import com.ydk.account.persistence.entity.PartnerType;
import com.ydk.account.persistence.entity.YdkAccount;


/**
 * @author y140zhan
 *
 */
public class LibraryForm {
	

	private Library instance;
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
	public LibraryForm() {
		super();
		instance = new Library();
		// TODO Auto-generated constructor stub
	}
	
	public LibraryForm(Library instance) {
		super();
		this.instance = instance;

	}
	
    

	/**
	 * @param instance
	 * @param keys
	 */
	public LibraryForm(Library instance, Long[] keys) {
		super();
		this.instance = instance;
		this.keys = keys;
	}
	

	/**
	 * @return the instance
	 */
	public Library getInstance() {
		return instance;
	}

	/**
	 * @param instance the instance to set
	 */
	public void setInstance(Library instance) {
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
	
	public void prepareForModify(Library instance)
	{
		instance.setName(this.instance.getName());
		
	}


	
	
	
	
	

}
