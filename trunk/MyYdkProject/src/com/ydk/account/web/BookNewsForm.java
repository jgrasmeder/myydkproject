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
import com.ydk.book.persistence.entity.BookNews;
import com.ydk.book.persistence.entity.BookNewsCategory;


/**
 * @author y140zhan
 *
 */
public class BookNewsForm {
	

	private BookNews instance;

	private Long[] keys ;
	
	/**
	 * 
	 */
	public BookNewsForm() {
		super();
		instance = new BookNews();
		instance.setBookNewsCategory(new BookNewsCategory());
		// TODO Auto-generated constructor stub
	}
	
	public BookNewsForm(BookNews instance) {
		super();
		this.instance = instance;

	}
	
    

	/**
	 * @param instance
	 * @param keys
	 */
	public BookNewsForm(BookNews instance, Long[] keys) {
		super();
		this.instance = instance;
		this.keys = keys;
	}
	

	/**
	 * @return the instance
	 */
	public BookNews getInstance() {
		return instance;
	}

	/**
	 * @param instance the instance to set
	 */
	public void setInstance(BookNews instance) {
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
	
	public void prepareForModify(BookNews instance)
	{
		instance.setTitle(this.instance.getTitle());
		
	}


	
	
	
	
	

}
