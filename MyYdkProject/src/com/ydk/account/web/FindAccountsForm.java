package com.ydk.account.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ydk.account.core.WebServiceAccountHelper;
import com.ydk.account.persistence.entity.Account;
import com.ydk.account.persistence.interfaces.AccountDbMngr;
import com.ydk.account.webservice.YdkAccountService;

/**
 * JavaBean Form controller that is used to search for <code>Owner</code>s by
 * last name.
 *
 * @author Juergen Hoeller
 * @author Ken Krebs
 */
@Controller
@RequestMapping("/findAccounts.do")
public class FindAccountsForm {

	@Autowired
	@Qualifier("ydkAccountServiceClient")
    private YdkAccountService ydkAccountServiceClient;
	
	public void setYdkAccountServiceClient(YdkAccountService ydkAccountServiceClient) {
        this.ydkAccountServiceClient = ydkAccountServiceClient;
    }

	
	@RequestMapping(method = RequestMethod.GET)
	public  ModelAndView setupForm() {
		ModelAndView modleAndview = new ModelAndView("findAccounts");
		modleAndview.addObject("account", new Account());
		return modleAndview;
	}

	@RequestMapping(method = RequestMethod.POST)
	public  String processSubmit(Account account, BindingResult result, ModelMap model) {
		// find owners by last name
		Collection<Account> results =
			WebServiceAccountHelper.stripAccountList(
					ydkAccountServiceClient.findLimitedAccounts(account.getAccountName(), 20)
					);
		
		if (results.size() < 1) {
			// no owners found
			result.rejectValue("accountName", "notFound", "not found");
			return "findAccounts";
		}
		if (results.size() > 1) {
			// multiple owners found
			model.addAttribute("selections", results);
			return "accounts";
		}
		else {
			// 1 owner found
			account = results.iterator().next();
			return "redirect:account.do?accountId=" + account.getId();
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, params="action=all")
	public  String processSubmitAll(Account account, BindingResult result, ModelMap model) {
		// find owners by last name
		Collection<Account> results = 
			WebServiceAccountHelper.stripAccountList(
					ydkAccountServiceClient.getAllAccounts()
					);
		
		if (results.size() < 1) {
			// no owners found
			result.rejectValue("accountName", "notFound", "not found");
			return "findAccounts";
		}
		if (results.size() > 1) {
			// multiple owners found
			model.addAttribute("selections", results);
			return "accounts";
		}
		else {
			// 1 owner found
			account = results.iterator().next();
			return "redirect:account.do?accountId=" + account.getId();
		}
	}

}
