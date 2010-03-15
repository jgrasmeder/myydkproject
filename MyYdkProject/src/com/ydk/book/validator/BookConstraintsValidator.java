package com.ydk.book.validator;

import com.ydk.book.persistence.entity.Book;
import com.ydk.book.persistence.interfaces.BookDbMngr;
import com.ydk.book.webservice.YdkBookService;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

/**
 * <code>Validator</code> for <code>book</code> forms.
 *
 * @author Zhang Yu
 */
public class BookConstraintsValidator {

	/**
	 * This validate is used to check all the business constraints
	 * for <code>book</code>
	 * Right now we have the following constraints for book
	 * 1.book.bookName - unique constraints
	 * 2.
	 * 3.
	 * 4.
	 * @param book
	 * @param errors
	 * @param dbMngr
	 */
	public static void validate(Book book, Errors errors,
			BookDbMngr dbMngr) {
		
		//Check the unique book.bookTitle
		if (book.isNew() && 
				dbMngr.getBook(book.getTitle()) != null)
		{
			//On create new book;
			errors.rejectValue("book.bookTitle", "Already exists", "Already exists");
		}
		//TODO Add other constraints;
	}
	
	public static void validate(Book book, Errors errors,
			YdkBookService dbMngr) {
		
		//Check the unique book.bookName
		if (book.isNew() && 
				dbMngr.getBookByTitle(book.getTitle()).getBook() != null)
		{
			//On create new book;
			errors.rejectValue("book.bookTitle", "Already exists", "Already exists");
		}
		//TODO Add other constraints;
	}

}
