package com.ydk.book.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;



import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.ServletWebRequest;

import com.ydk.book.persistence.entity.*;
import com.ydk.book.persistence.interfaces.BookDbMngr;
import com.ydk.book.validator.BookConstraintsValidator;
import com.ydk.book.validator.BookValidator;
import com.ydk.book.webservice.YdkBookService;
import com.ydk.web.WebControllerBase;

/**
 * JavaBean form controller that is used to add a new <code>Owner</code> to
 * the system.
 *
 * @author Juergen Hoeller
 * @author Ken Krebs
 */
@Controller
@RequestMapping("/deleteBook.do")
public class DeleteBookForm extends WebControllerBase {

	@Autowired
	@Qualifier("ydkBookServiceClient")
    private YdkBookService ydkBookServiceClient;
	
	public void setYdkBookServiceClient(YdkBookService ydkBookServiceClient) {
        this.ydkBookServiceClient = ydkBookServiceClient;
    }

	public DeleteBookForm() {
		this.thisView = "books";
		this.fromView = "books";
		this.errorView = "books";
		this.successView = "books";
		this.successViewRedirect = "findBooks.do";
		this.homeView = "welcome.do";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(ModelMap model, ServletWebRequest request) {
		Book book = new Book();
		model.addAttribute(book);
		String bookId = request.getParameter("bookId");
		//Check the validation
		for (int i = 0; i < bookId.length(); ++i) {
			if ((Character.isDigit(bookId.charAt(i))) == false) {
				model.addAttribute("message", "Invalid BookId");
				break;
			}
		}
		setFromView(request.getParameter("fromView"));
		
		ydkBookServiceClient.deleteBook(new Long(bookId));
		model.addAttribute("message", "Delete book successfully");
		return "redirect:" + getFromView() + ".do";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("bookId") Integer bookId, BindingResult result, SessionStatus status) {
		//First we check the given parameters
		//Then we perform store;
		
		//Check
//		BookValidator.validate(book, result);
//		if (result.hasErrors()) {
//			return "bookForm";
//		}
//		BookConstraintsValidator.validate(book, result, bookDbMngr);
//		if (result.hasErrors()) {
//			return "bookForm";
//		}
//		else {
//			//Finish checking, do the business
//			
//			bookDbMngr.saveBook(book);
//			status.setComplete();
//			return "redirect:book.do?bookId=" + book.getId();
//		}
		return null;
	}

}
