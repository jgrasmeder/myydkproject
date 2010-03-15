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
import com.ydk.book.persistence.entity.BookReview;
import com.ydk.book.persistence.entity.BookReviewCategory;


/**
 * @author y140zhan
 *
 */
public class BookReviewForm {
	

	private BookReview instance;

	private Long[] keys ;
	
	/**
	 * 
	 */
	public BookReviewForm() {
		super();
		instance = new BookReview();
		instance.setBookReviewCategory(new BookReviewCategory());
		// TODO Auto-generated constructor stub
	}
	
	public BookReviewForm(BookReview instance) {
		super();
		this.instance = instance;

	}
	
    

	/**
	 * @param instance
	 * @param keys
	 */
	public BookReviewForm(BookReview instance, Long[] keys) {
		super();
		this.instance = instance;
		this.keys = keys;
	}
	

	/**
	 * @return the instance
	 */
	public BookReview getInstance() {
		return instance;
	}

	/**
	 * @param instance the instance to set
	 */
	public void setInstance(BookReview instance) {
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
	
	public void prepareForModify(BookReview instance)
	{
		instance.setTitle(this.instance.getTitle());
//		instance.setContent(this.instance.getContent());
		instance.setIntroduction(this.instance.getIntroduction());
		instance.setEditor(this.instance.getEditor());
		instance.setDate(this.instance.getDate());
		instance.setOrder(this.instance.getOrder());
		instance.setRecommanded(this.instance.getRecommanded());
		instance.setIsNew(this.instance.getIsNew());
		
	}


	
	
	
	
	

}
