package com.ydk.account.validator;

import com.ydk.account.persistence.entity.Account;
import com.ydk.service.mail.EmailUtility;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

/**
 * <code>Validator</code> for <code>Account</code> forms.
 *
 * @author Zhang Yu
 */
public class AccountValidator {

	public static void validate(Account account, Errors errors) {
		if (!StringUtils.hasLength(account.getAccountName())) {
			errors.rejectValue("account.accountName", "required", "required");
		}
		if (!StringUtils.hasLength(account.getPassword())) {
			errors.rejectValue("account.password", "required", "required");
		}
		if (!StringUtils.hasLength(account.getEmail())) {
			errors.rejectValue("account.email", "required", "required");
		}
		if (!EmailUtility.isValid(account.getEmail())) {
			errors.rejectValue("account.email", "invalid", "invalid");
		}
		if (!StringUtils.hasLength(account.getFirstName())) {
			errors.rejectValue("account.firstName", "required", "required");
		}
		if (!StringUtils.hasLength(account.getLastName())) {
			errors.rejectValue("account.lastName", "required", "required");
		}
		if (!StringUtils.hasLength(account.getAddress())) {
			errors.rejectValue("account.address", "required", "required");
		}
		if (!StringUtils.hasLength(account.getCity())) {
			errors.rejectValue("account.city", "required", "required");
		}

		String telephone = account.getTelephone();
		if (!StringUtils.hasLength(telephone)) {
			errors.rejectValue("account.telephone", "required", "required");
		}
		else {
			for (int i = 0; i < telephone.length(); ++i) {
				if ((Character.isDigit(telephone.charAt(i))) == false) {
					errors.rejectValue("account.telephone", "nonNumeric", "non-numeric");
					break;
				}
			}
		}
	}

}
