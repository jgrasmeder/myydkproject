package com.ydk.account.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;

import com.ydk.account.core.AccountHelper;
import com.ydk.account.persistence.entity.Account;
import com.ydk.account.persistence.interfaces.AccountDbMngr;
import com.ydk.account.webservice.YdkAccountService;

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
@RequestMapping("/account.do")
public class AccountInfo {

	@Autowired
	@Qualifier("ydkAccountServiceClient")
    private YdkAccountService ydkAccountServiceClient;
	
	public void setYdkAccountServiceClient(YdkAccountService ydkAccountServiceClient) {
        this.ydkAccountServiceClient = ydkAccountServiceClient;
    }

	@RequestMapping(method = RequestMethod.GET)
	public  String setupForm(ServletWebRequest request, ModelMap model) 
	throws DataAccessException, IOException, SQLException {
		
//		return new ModelMap(this.accountDbMngr.getAccount(accountId))
		AccountHelper ah = new AccountHelper();
		String pAccountId = request.getParameter("accountId");
		if (pAccountId != null)
		{
			Long accountId = new Long(pAccountId);
			ah.setAccount(this.ydkAccountServiceClient.getAccount(accountId).getAccount());
		}
		else
		{
			String pAccountName = request.getParameter("accountName");
			ah.setAccount(this.ydkAccountServiceClient.getAccountByName(pAccountName).getAccount());
		}
		
		model.addAttribute("accountHelper", ah);
		return "account";
	}

	@RequestMapping(method = RequestMethod.POST)
	public  String processSubmit(Account account, BindingResult result, ModelMap model) {
		// find owners by last name
		return "Error";
	}

}
