package com.ydk.account.validator;


import com.ydk.account.web.PartnerTypeForm;
import com.ydk.account.web.YdkAccountForm;
import com.ydk.service.mail.EmailUtility;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

/**
 * <code>Validator</code> for <code>YdkAccount</code> forms.
 *
 * @author Zhang Yu
 */
public class PartnerTypeValidator {

	public static void validate(PartnerTypeForm dataForm, Errors errors) {
		if (!StringUtils.hasLength(dataForm.getInstance().getType())) {
			errors.rejectValue("instance.type", "required", "required");
		}
//		if (!StringUtils.hasLength(account.getPassword())) {
//			errors.rejectValue("password", "required", "required");
//		}
//		if (!StringUtils.hasLength(account.getRepeatedPw())) {
//			errors.rejectValue("repeatedPw", "required", "required");
//		}
//		if (!account.getRepeatedPw().equals(account.getPassword())) {
//			errors.rejectValue("repeatedPw", "not equal", "不一致");
//		}
//		if (!StringUtils.hasLength(account.getEmail())) {
//			errors.rejectValue("email", "required", "required");
//		}
//		if (!EmailUtility.isValid(account.getEmail())) {
//			errors.rejectValue("email", "invalid", "invalid");
//		}
//		if (account.getRoleId() == null) {
//			errors.rejectValue("roleId", "required", "required");
//		}
		
	}

}
