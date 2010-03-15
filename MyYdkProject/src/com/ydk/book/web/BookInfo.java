package com.ydk.book.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;

import com.ydk.account.core.AccountHelper;
import com.ydk.account.persistence.entity.Account;
import com.ydk.account.webservice.YdkAccountService;
import com.ydk.book.core.BookHelper;
import com.ydk.book.persistence.entity.Book;
import com.ydk.book.persistence.interfaces.BookDbMngr;
import com.ydk.book.webservice.YdkBookService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * JavaBean Form controller that is used to search for <code>Owner</code>s by
 * last name.
 *
 * @author Juergen Hoeller
 * @author Ken Krebs
 */
@Controller
@RequestMapping("/book.do")
public class BookInfo {

	@Autowired
	@Qualifier("ydkBookServiceClient")
	private YdkBookService ydkBookServiceClient;

	public void setYdkBookServiceClient(YdkBookService ydkBookServiceClient) {
        this.ydkBookServiceClient = ydkBookServiceClient;
    }

	@RequestMapping(method = RequestMethod.GET)
	public  String setupForm(ServletWebRequest request, ModelMap model) 
	throws DataAccessException, IOException, SQLException {
		
		BookHelper ah = new BookHelper();
		String pBookId = request.getParameter("bookId");
		if (pBookId != null)
		{
			Long bookId = new Long(pBookId);
			ah.setBook(this.ydkBookServiceClient.getBook(bookId).getBook());
		}
		else
		{
			String pBookTitle = request.getParameter("bookTitle");
			ah.setBook(this.ydkBookServiceClient.getBookByTitle(pBookTitle).getBook());
		}
		
		model.addAttribute("bookHelper", ah);
		return "book";
	}

	@RequestMapping(method = RequestMethod.POST)
	public  String processSubmit(Book book, BindingResult result, ModelMap model) {
		// find owners by last name
		return "Error";
	}

}
