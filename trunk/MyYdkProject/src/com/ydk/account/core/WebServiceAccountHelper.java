/**
 * 
 */
package com.ydk.account.core;

import java.util.ArrayList;
import java.util.List;

import com.ydk.account.persistence.entity.Account;
import com.ydk.account.persistence.entity.UploadedFile;
import com.ydk.account.webservice.WebServiceAccount;

/**
 * @author y140zhan
 *
 */
public class WebServiceAccountHelper {

	//Wrap account to WebServiceAccount
	public static WebServiceAccount wrapAccount(Account account){
		return new WebServiceAccount(account, null);
	}
	//Wrap account to WebServiceAccount
	public static WebServiceAccount wrapUploadedFile(UploadedFile file){
		return new WebServiceAccount(null, file);
	}
	
	public static WebServiceAccount wrapAccount(Account account, UploadedFile file){
		return new WebServiceAccount(account, file);
	}
	
	public static List<WebServiceAccount> wrapAccountList(List<Account> accounts){
    	List<WebServiceAccount> webAccounts = new ArrayList<WebServiceAccount>();
    	for (Account account: accounts)
    	{
    		webAccounts.add(new WebServiceAccount(account,null));
    	}
		return webAccounts;
	}
	
	//Strip WebServiceAccount to account
	public static Account stripAccount(WebServiceAccount account){
		return account.getAccount();
	}
	
	public static UploadedFile stripUploadedFile(WebServiceAccount account){
		return account.getUploadedFile();
	}
	
	public static List<Account> stripAccountList(List<WebServiceAccount> accounts){
    	List<Account> webAccounts = new ArrayList<Account>();
    	for (WebServiceAccount account: accounts)
    	{
    		webAccounts.add(account.getAccount());
    	}
		return webAccounts;
	}
	
	
}
