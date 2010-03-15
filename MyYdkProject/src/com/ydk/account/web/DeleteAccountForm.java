package com.ydk.account.web;

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

import com.ydk.account.persistence.entity.*;
import com.ydk.account.persistence.interfaces.AccountDbMngr;
import com.ydk.account.validator.AccountConstraintsValidator;
import com.ydk.account.validator.AccountValidator;
import com.ydk.account.webservice.YdkAccountService;
import com.ydk.web.WebControllerBase;

/**
 * JavaBean form controller that is used to add a new <code>Owner</code> to
 * the system.
 *
 * @author Juergen Hoeller
 * @author Ken Krebs
 */
@Controller
@RequestMapping("/deleteAccount.do")
public class DeleteAccountForm extends WebControllerBase {

	@Autowired
	@Qualifier("ydkAccountServiceClient")
    private YdkAccountService ydkAccountServiceClient;
	
	public void setYdkAccountServiceClient(YdkAccountService ydkAccountServiceClient) {
        this.ydkAccountServiceClient = ydkAccountServiceClient;
    }

	public DeleteAccountForm() {
		this.thisView = "accounts";
		this.fromView = "accounts";
		this.errorView = "accounts";
		this.successView = "accounts";
		this.successViewRedirect = "findAccounts.do";
		this.homeView = "welcome.do";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(ModelMap model, ServletWebRequest request) {
		Account account = new Account();
		model.addAttribute(account);
		String accountId = request.getParameter("accountId");
		//Check the validation
		for (int i = 0; i < accountId.length(); ++i) {
			if ((Character.isDigit(accountId.charAt(i))) == false) {
				model.addAttribute("message", "Invalid AccountId");
				break;
			}
		}
		setFromView(request.getParameter("fromView"));
		
		ydkAccountServiceClient.deleteAccount(new Long(accountId));
		model.addAttribute("message", "Delete account successfully");
		return "redirect:" + getFromView() + ".do";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("accountId") Integer accountId, BindingResult result, SessionStatus status) {
		//First we check the given parameters
		//Then we perform store;
		
		//Check
//		AccountValidator.validate(account, result);
//		if (result.hasErrors()) {
//			return "accountForm";
//		}
//		AccountConstraintsValidator.validate(account, result, accountDbMngr);
//		if (result.hasErrors()) {
//			return "accountForm";
//		}
//		else {
//			//Finish checking, do the business
//			
//			accountDbMngr.saveAccount(account);
//			status.setComplete();
//			return "redirect:account.do?accountId=" + account.getId();
//		}
		return null;
	}

}
