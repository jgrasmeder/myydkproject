/**
 * 
 */
package com.ydk.account.web;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.fileupload.FileItem;
import org.hibernate.lob.BlobImpl;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ydk.account.core.FileUploadBean;
import com.ydk.account.persistence.entity.FileDescriptor;
import com.ydk.account.persistence.entity.YdkAccount;


/**
 * @author y140zhan
 *
 */
public class YdkAccountForm {
	
	private Long accountId;
	private String name;
	private String password;
	private String passwordNew;
	private String repeatedPw;
	private String email;
	private Boolean isActived;
	private FileUploadBean fileUploadBean;
	private Long roleId;
	private Long[] keys ;
	
	/**
	 * 
	 */
	public YdkAccountForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	/**
	 * @param name
	 * @param password
	 * @param repeatedPw
	 * @param email
	 * @param isActived
	 * @param fileUploadBean
	 * @param roleId
	 */
	public YdkAccountForm(Long accountId, String name, String password, String repeatedPw,
			String email, Boolean isActived, FileUploadBean fileUploadBean,
			Long roleId) {
		super();
		this.accountId = accountId;
		this.name = name;
		this.password = password;
		this.repeatedPw = repeatedPw;
		this.email = email;
		this.isActived = isActived;
		this.fileUploadBean = fileUploadBean;
		this.roleId = roleId;
	}

	
	/**
	 * @return the accountId
	 */
	public Long getAccountId() {
		return accountId;
	}
	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	/**
	 * @return the passwordNew
	 */
	public String getPasswordNew() {
		return passwordNew;
	}


	/**
	 * @param passwordNew the passwordNew to set
	 */
	public void setPasswordNew(String passwordNew) {
		this.passwordNew = passwordNew;
	}


	/**
	 * @return the repeatedPw
	 */
	public String getRepeatedPw() {
		return repeatedPw;
	}
	/**
	 * @param repeatedPw the repeatedPw to set
	 */
	public void setRepeatedPw(String repeatedPw) {
		this.repeatedPw = repeatedPw;
	}



	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the isActived
	 */
	public Boolean getIsActived() {
		return isActived;
	}
	/**
	 * @param isActived the isActived to set
	 */
	public void setIsActived(Boolean isActived) {
		this.isActived = isActived;
	}
	/**
	 * @return the fileUploadBean
	 */
	public FileUploadBean getFileUploadBean() {
		return fileUploadBean;
	}
	/**
	 * @param fileUploadBean the fileUploadBean to set
	 */
	public void setFileUploadBean(FileUploadBean fileUploadBean) {
		this.fileUploadBean = fileUploadBean;
	}
	/**
	 * @return the roleId
	 */
	public Long getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
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
	
	


	
	
	
	
	

}
