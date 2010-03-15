package com.ydk.book.validator;

import com.ydk.book.persistence.entity.Book;
import com.ydk.book.persistence.entity.Book;
import com.ydk.service.mail.EmailUtility;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

/**
 * <code>Validator</code> for <code>Book</code> forms.
 *
 * @author Zhang Yu
 */
public class BookValidator {

	public static void validate(Book book, Errors errors) {
		if (!StringUtils.hasLength(book.getTitle())) {
			errors.rejectValue("book.bookTitle", "required", "required");
		}
		if (book.getAuthor() != null) {
			errors.rejectValue("book.author", "required", "required");
		}
		

//		String telephone = book.getTelephone();
//		if (!StringUtils.hasLength(telephone)) {
//			errors.rejectValue("book.telephone", "required", "required");
//		}
//		else {
//			for (int i = 0; i < telephone.length(); ++i) {
//				if ((Character.isDigit(telephone.charAt(i))) == false) {
//					errors.rejectValue("book.telephone", "nonNumeric", "non-numeric");
//					break;
//				}
//			}
//		}
	}

}
