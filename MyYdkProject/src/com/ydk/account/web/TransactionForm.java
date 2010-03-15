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
import com.ydk.account.persistence.entity.Partner;
import com.ydk.account.persistence.entity.PartnerType;
import com.ydk.account.persistence.entity.Transaction;
import com.ydk.account.persistence.entity.TransactionType;
import com.ydk.account.persistence.entity.YdkAccount;


/**
 * @author y140zhan
 *
 */
public class TransactionForm {
	

	private Transaction instance;
	private String ydkBookId;
	private String readerName;
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
	public TransactionForm() {
		super();
		instance = new Transaction();
		instance.setTransactionType(new TransactionType());
		// TODO Auto-generated constructor stub
	}
	
	public TransactionForm(Transaction instance) {
		super();
		this.instance = instance;

	}
	
    

	/**
	 * @param instance
	 * @param keys
	 */
	public TransactionForm(Transaction instance, Long[] keys) {
		super();
		this.instance = instance;
		this.keys = keys;
	}
	

	/**
	 * @return the instance
	 */
	public Transaction getInstance() {
		return instance;
	}
	public void setInstance(Transaction instance) {
		this.instance = instance;
	}


	/**
	 * @return the keys
	 */
	public Long[] getKeys() {
		return keys;
	}
	public void setKeys(Long[] keys) {
		this.keys = keys;
	}
	
	
	/**
	 * @return the ydkBookId
	 */
	public String getYdkBookId() {
		return ydkBookId;
	}
	public void setYdkBookId(String ydkBookId) {
		this.ydkBookId = ydkBookId;
	}

	/**
	 * @return the readerName
	 */
	public String getReaderName() {
		return readerName;
	}
	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}
	
	
	public void prepareForModify(Transaction instance)
	{
		//We can not modify the Transactions
		
	}


	
	
	
	
	

}
