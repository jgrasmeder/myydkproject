package com.ydk.account.validator;


import com.ydk.account.web.PartnerTypeForm;
import com.ydk.account.web.TransactionTypeForm;
import com.ydk.account.web.YdkAccountForm;
import com.ydk.service.mail.EmailUtility;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

/**
 * <code>Validator</code> for <code>YdkAccount</code> forms.
 *
 * @author Zhang Yu
 */
public class TransactionTypeValidator {

	public static void validate(TransactionTypeForm dataForm, Errors errors) {
		if (!StringUtils.hasLength(dataForm.getInstance().getType())) {
			errors.rejectValue("instance.type", "required", "required");
		}
//		
		
	}

}
