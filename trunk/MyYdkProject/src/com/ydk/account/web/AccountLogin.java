/**
 * 
 */
package com.ydk.account.web;


/**
 * @author y140zhan
 *
 */
public class AccountLogin {
	private String accountName;
	private String password;
	private String verificationCode;
	private String verificationValue;
	
	
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getVerificationCode() {
		return this.verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	
	public String getVerificationValue() {
		return this.verificationValue;
	}

	public void setVerificationValue(String verificationValue) {
		this.verificationValue = verificationValue;
	}
}
