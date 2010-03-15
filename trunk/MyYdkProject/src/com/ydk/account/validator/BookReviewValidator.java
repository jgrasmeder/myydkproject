package com.ydk.account.validator;


import com.ydk.account.web.BookNewsForm;
import com.ydk.account.web.BookReviewForm;
import com.ydk.account.web.LibraryForm;
import com.ydk.service.mail.EmailUtility;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

/**
 * <code>Validator</code> for <code>YdkAccount</code> forms.
 *
 * @author Zhang Yu
 */
public class BookReviewValidator {

	public static void validate(BookReviewForm dataForm, Errors errors) {
		if (!StringUtils.hasLength(dataForm.getInstance().getTitle())) {
			errors.rejectValue("instance.title", "required", "不能为空");
		}
		
	}

}
