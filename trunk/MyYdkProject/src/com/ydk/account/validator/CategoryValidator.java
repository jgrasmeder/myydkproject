package com.ydk.account.validator;


import com.ydk.account.web.CategoryForm;
import com.ydk.service.mail.EmailUtility;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

/**
 * <code>Validator</code> for <code>YdkAccount</code> forms.
 *
 * @author Zhang Yu
 */
public class CategoryValidator {

	public static void validate(CategoryForm dataForm, Errors errors) {
		if (!StringUtils.hasLength(dataForm.getInstance().getName())) {
			errors.rejectValue("instance.name", "required", "不能为空");
		}
//		if (dataForm.getInstance().getLibrary() == null
//				|| dataForm.getInstance().getLibrary().getId() == null) {
//			errors.rejectValue("instance.library.id", "required", "不能为空");
//		}
		
		
	}

}
