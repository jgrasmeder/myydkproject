package com.ydk.account.validator;


import com.ydk.account.persistence.entity.UploadedFile;
import com.ydk.account.web.YdkAccountForm;
import com.ydk.service.mail.EmailUtility;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

/**
 * <code>Validator</code> for <code>YdkAccount</code> forms.
 *
 * @author Zhang Yu
 */
public class UploadedFileValidator {

	public static String validate(UploadedFile file) {
		if (file == null || !StringUtils.hasLength(file.getFileName())) {
			return "UploadedFile is null";
		}
		if (!StringUtils.hasLength(file.getFileType())) {
			return "UploadedFile's content type is null";
		}
		
		return null;
		
	}

}
