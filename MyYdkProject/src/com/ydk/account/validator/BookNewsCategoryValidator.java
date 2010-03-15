package com.ydk.account.validator;


import com.ydk.account.web.BookNewsCategoryForm;

import com.ydk.service.mail.EmailUtility;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

/**
 * <code>Validator</code> for <code>YdkAccount</code> forms.
 *
 * @author Zhang Yu
 */
public class BookNewsCategoryValidator {

	public static void validate(BookNewsCategoryForm dataForm, Errors errors) {
		if (!StringUtils.hasLength(dataForm.getInstance().getName())) {
			errors.rejectValue("instance.title", "required", "不能为空");
		}
		
	}

}
