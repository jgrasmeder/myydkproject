package com.ydk.account.validator;



import com.ydk.account.web.BookForm;


import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

/**
 * <code>Validator</code> for <code>YdkAccount</code> forms.
 *
 * @author Zhang Yu
 */
public class BookValidator {

	public static void validate(BookForm dataForm, Errors errors) {
		if (!StringUtils.hasLength(dataForm.getInstance().getTitle())) {
			errors.rejectValue("instance.title", "required", "不能为空");
		}
		
	}

}
