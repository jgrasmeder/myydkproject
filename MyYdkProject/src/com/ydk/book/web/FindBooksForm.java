package com.ydk.book.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ydk.book.core.WebServiceBookHelper;
import com.ydk.book.persistence.entity.Book;
import com.ydk.book.persistence.interfaces.BookDbMngr;
import com.ydk.book.webservice.YdkBookService;

/**
 * JavaBean Form controller that is used to search for <code>Owner</code>s by
 * last name.
 *
 * @author Juergen Hoeller
 * @author Ken Krebs
 */
@Controller
@RequestMapping("/findBooks.do")
public class FindBooksForm {

	@Autowired
	@Qualifier("ydkBookServiceClient")
    private YdkBookService ydkBookServiceClient;
	
	public void setYdkBookServiceClient(YdkBookService ydkBookServiceClient) {
        this.ydkBookServiceClient = ydkBookServiceClient;
    }

	
	@RequestMapping(method = RequestMethod.GET)
	public  ModelAndView setupForm() {
		ModelAndView modleAndview = new ModelAndView("findBooks");
		modleAndview.addObject("book", new Book());
		return modleAndview;
	}

	@RequestMapping(method = RequestMethod.POST)
	public  String processSubmit(Book book, BindingResult result, ModelMap model) {
		// find owners by last name
		Collection<Book> results =
			WebServiceBookHelper.stripBookList(
					ydkBookServiceClient.findLimitedBooks(book.getTitle(), 20)
					);
		
		if (results.size() < 1) {
			// no owners found
			result.rejectValue("bookName", "notFound", "not found");
			return "findBooks";
		}
		if (results.size() > 1) {
			// multiple owners found
			model.addAttribute("selections", results);
			return "books";
		}
		else {
			// 1 owner found
			book = results.iterator().next();
			return "redirect:book.do?bookId=" + book.getId();
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, params="action=all")
	public  String processSubmitAll(Book book, BindingResult result, ModelMap model) {
		// find owners by last name
		Collection<Book> results = 
			WebServiceBookHelper.stripBookList(
					ydkBookServiceClient.getAllBooks()
					);
		
		if (results.size() < 1) {
			// no owners found
			result.rejectValue("bookName", "notFound", "not found");
			return "findBooks";
		}
		if (results.size() > 1) {
			// multiple owners found
			model.addAttribute("selections", results);
			return "books";
		}
		else {
			// 1 owner found
			book = results.iterator().next();
			return "redirect:book.do?bookId=" + book.getId();
		}
	}

}
