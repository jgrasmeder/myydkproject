package com.ydk.account.validator;


import com.ydk.account.web.LibraryForm;
import com.ydk.service.mail.EmailUtility;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

/**
 * <code>Validator</code> for <code>YdkAccount</code> forms.
 *
 * @author Zhang Yu
 */
public class LibraryValidator {

	public static void validate(LibraryForm dataForm, Errors errors) {
		if (!StringUtils.hasLength(dataForm.getInstance().getName())) {
			errors.rejectValue("instance.name", "required", "不能为空");
		}
		
		
	}

}
