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
import com.ydk.account.persistence.entity.YdkAccount;


/**
 * @author y140zhan
 *
 */
public class PartnerForm {
	

	private Partner instance;
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
	public PartnerForm() {
		super();
		instance = new Partner();
		instance.setPartnerType(new PartnerType());
		// TODO Auto-generated constructor stub
	}
	
	public PartnerForm(Partner instance) {
		super();
		this.instance = instance;

	}
	
    

	/**
	 * @param instance
	 * @param keys
	 */
	public PartnerForm(Partner instance, Long[] keys) {
		super();
		this.instance = instance;
		this.keys = keys;
	}
	

	/**
	 * @return the instance
	 */
	public Partner getInstance() {
		return instance;
	}

	/**
	 * @param instance the instance to set
	 */
	public void setInstance(Partner instance) {
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
	
	public void prepareForModify(Partner instance)
	{
		instance.setName(this.instance.getName());
		instance.setContactPerson(this.instance.getContactPerson());
		instance.setEmail(this.instance.getEmail());
		instance.setFax(this.instance.getFax());
		instance.setFixPhone(this.instance.getFixPhone());
		instance.setMobilePhone(this.instance.getMobilePhone());
		instance.setIsActived(this.instance.getIsActived());
		instance.setLogo(this.instance.getLogo());
		instance.setRegion(this.instance.getRegion());
		instance.setZipCode(this.instance.getZipCode());
		instance.setPostAddress(this.instance.getPostAddress());
		instance.setPartnerType(this.instance.getPartnerType());
	}


	
	
	
	
	

}
