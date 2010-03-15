package com.ydk.account.validator;

import com.ydk.account.persistence.entity.Account;
import com.ydk.account.persistence.interfaces.AccountDbMngr;
import com.ydk.account.webservice.YdkAccountService;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

/**
 * <code>Validator</code> for <code>Account</code> forms.
 *
 * @author Zhang Yu
 */
public class AccountConstraintsValidator {

	/**
	 * This validate is used to check all the business constraints
	 * for <code>Account</code>
	 * Right now we have the following constraints for Account
	 * 1.Account.accountName - unique constraints
	 * 2.
	 * 3.
	 * 4.
	 * @param account
	 * @param errors
	 * @param dbMngr
	 */
	public static void validate(Account account, Errors errors,
			AccountDbMngr dbMngr) {
		
		//Check the unique Account.accountName
		if (account.isNew() && 
				dbMngr.getAccount(account.getAccountName()) != null)
		{
			//On create new account;
			errors.rejectValue("account.accountName", "Already exists", "Already exists");
		}
		//TODO Add other constraints;
	}
	
	public static void validate(Account account, Errors errors,
			YdkAccountService dbMngr) {
		
		//Check the unique Account.accountName
		if (account.isNew() && 
				dbMngr.getAccountByName(account.getAccountName()).getAccount() != null)
		{
			//On create new account;
			errors.rejectValue("account.accountName", "Already exists", "Already exists");
		}
		//TODO Add other constraints;
	}

}
