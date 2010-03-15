package com.ydk.account.validator;



import com.ydk.account.web.EventForm;
import com.ydk.account.web.LibraryForm;
import com.ydk.service.mail.EmailUtility;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

/**
 * <code>Validator</code> for <code>YdkAccount</code> forms.
 *
 * @author Zhang Yu
 */
public class EventValidator {

	public static void validate(EventForm dataForm, Errors errors) {
		if (!StringUtils.hasLength(dataForm.getInstance().getTitle())) {
			errors.rejectValue("instance.title", "required", "不能为空");
		}
		
	}

}
