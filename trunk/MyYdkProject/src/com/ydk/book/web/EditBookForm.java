package com.ydk.book.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ydk.book.core.BookHelper;
import com.ydk.book.core.WebServiceBookHelper;
import com.ydk.book.persistence.entity.Book;
import com.ydk.book.persistence.interfaces.BookDbMngr;
import com.ydk.book.validator.BookValidator;
import com.ydk.book.webservice.YdkBookService;

/**
 * JavaBean Form controller that is used to edit an existing <code>Owner</code>.
 *
 * @author Juergen Hoeller
 * @author Ken Krebs
 */
@Controller
@RequestMapping("/editBook.do")
@SessionAttributes("bookHelper")
public class EditBookForm {

	@Autowired
	@Qualifier("ydkBookServiceClient")
    private YdkBookService ydkBookServiceClient;
	
	public void setYdkBookServiceClient(YdkBookService ydkBookServiceClient) {
        this.ydkBookServiceClient = ydkBookServiceClient;
    }

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(@RequestParam("bookId") Long bookId, ModelMap model) {
		Book book = this.ydkBookServiceClient.getBook(bookId).getBook();
		BookHelper bookHelper = new BookHelper();
		bookHelper.setBook(book);
		model.addAttribute("bookHelper", bookHelper);
		return "bookForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute Book book, BindingResult result, SessionStatus status) {
		BookValidator.validate(book, result);
		if (result.hasErrors()) {
			return "bookForm";
		}
		else {

//			this.ydkBookServiceClient.saveBook(WebServiceBookHelper.wrapBook(book));
			status.setComplete();
			return "redirect:book.do?bookId=" + book.getId();

		}
	}

}
