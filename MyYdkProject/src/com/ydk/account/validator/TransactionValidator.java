package com.ydk.account.validator;


import com.ydk.account.web.PartnerForm;
import com.ydk.account.web.PartnerTypeForm;
import com.ydk.account.web.TransactionForm;
import com.ydk.account.web.YdkAccountForm;
import com.ydk.service.mail.EmailUtility;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

/**
 * <code>Validator</code> for <code>YdkAccount</code> forms.
 *
 * @author Zhang Yu
 */
public class TransactionValidator {

	public static void validate(TransactionForm dataForm, Errors errors) {
		if ((dataForm.getInstance().getPartner() != null)) {
			errors.rejectValue("instance.partner.id", "required", "不能为空");
		}
		if (dataForm.getInstance().getReader() != null) {
			errors.rejectValue("instance.reader.id", "required", "不能为空");
		}
		if (dataForm.getInstance().getBook() != null) {
			errors.rejectValue("instance.book.id", "invalid", "无效");
		}
		
	}

}
