package com.ydk.account.validator;


import com.ydk.account.web.PartnerForm;
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
public class PartnerValidator {

	public static void validate(PartnerForm dataForm, Errors errors) {
		if (!StringUtils.hasLength(dataForm.getInstance().getName())) {
			errors.rejectValue("instance.name", "required", "不能为空");
		}
		if (!StringUtils.hasLength(dataForm.getInstance().getEmail())) {
			errors.rejectValue("instance.email", "required", "不能为空");
		}
		if (!EmailUtility.isValid(dataForm.getInstance().getEmail())) {
			errors.rejectValue("instance.email", "invalid", "无效");
		}
		if (!StringUtils.hasLength(dataForm.getInstance().getContactPerson())) {
			errors.rejectValue("instance.contactPerson", "required", "不能为空");
		}
		if (!StringUtils.hasLength(dataForm.getInstance().getFixPhone())) {
			errors.rejectValue("instance.fixPhone", "required", "不能为空");
		}
		if (!StringUtils.hasLength(dataForm.getInstance().getMobilePhone())) {
			errors.rejectValue("instance.mobilePhone", "required", "不能为空");
		}
		
	}

}
