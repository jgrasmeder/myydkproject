package com.ydk.account.web;

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

import com.ydk.account.core.AccountHelper;
import com.ydk.account.core.WebServiceAccountHelper;
import com.ydk.account.persistence.entity.Account;
import com.ydk.account.persistence.interfaces.AccountDbMngr;
import com.ydk.account.validator.AccountValidator;
import com.ydk.account.webservice.YdkAccountService;

/**
 * JavaBean Form controller that is used to edit an existing <code>Owner</code>.
 *
 * @author Juergen Hoeller
 * @author Ken Krebs
 */
@Controller
@RequestMapping("/editAccount.do")
@SessionAttributes("accountHelper")
public class EditAccountForm {

	@Autowired
	@Qualifier("ydkAccountServiceClient")
    private YdkAccountService ydkAccountServiceClient;
	
	public void setYdkAccountServiceClient(YdkAccountService ydkAccountServiceClient) {
        this.ydkAccountServiceClient = ydkAccountServiceClient;
    }

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(@RequestParam("accountId") Long accountId, ModelMap model) {
		Account account = this.ydkAccountServiceClient.getAccount(accountId).getAccount();
		AccountHelper accountHelper = new AccountHelper();
		accountHelper.setAccount(account);
		model.addAttribute("accountHelper", accountHelper);
		return "accountForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute Account account, BindingResult result, SessionStatus status) {
		AccountValidator.validate(account, result);
		if (result.hasErrors()) {
			return "accountForm";
		}
		else {

			this.ydkAccountServiceClient.saveAccount(WebServiceAccountHelper.wrapAccount(account));
			status.setComplete();
			return "redirect:account.do?accountId=" + account.getId();

		}
	}

}
