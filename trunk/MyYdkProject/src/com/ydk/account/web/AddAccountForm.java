package com.ydk.account.web;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;



import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ydk.account.core.AccountHelper;
import com.ydk.account.core.WebServiceAccountHelper;
import com.ydk.account.persistence.entity.*;
import com.ydk.account.persistence.interfaces.AccountDbMngr;
import com.ydk.account.validator.AccountConstraintsValidator;
import com.ydk.account.validator.AccountValidator;
import com.ydk.account.webservice.WebServiceAccount;
import com.ydk.account.webservice.YdkAccountService;
import com.ydk.service.mail.YdkEmailInterface;

/**
 * JavaBean form controller that is used to add a new <code>Owner</code> to
 * the system.
 *
 * @author Juergen Hoeller
 * @author Ken Krebs
 */
@Controller
@RequestMapping("/addAccount.do")
@SessionAttributes({"account", "accountHelper"})
public class AddAccountForm {

	@Autowired
	@Qualifier("ydkAccountServiceClient")
    private YdkAccountService ydkAccountServiceClient;
	
	private YdkEmailInterface mailService;
	
	public void setYdkAccountServiceClient(YdkAccountService ydkAccountServiceClient) {
        this.ydkAccountServiceClient = ydkAccountServiceClient;
    }
	
	@Autowired
	public void setYdkEmailService(YdkEmailInterface mailService) {
        this.mailService = mailService;
    }
	

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(ModelMap model) {
		Account account = new Account();
		AccountHelper accountHelper = new AccountHelper();
		accountHelper.setAccount(account);
		model.addAttribute("accountHelper", accountHelper);
		return "accountForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("accountHelper") AccountHelper accountHelper, 
			BindingResult result, SessionStatus status, ServletWebRequest request) 
		throws DataAccessException, IOException {
		//First we check the given parameters
		//Then we perform store;
		
		if (! (request.getRequest() instanceof MultipartHttpServletRequest)) 
		{
			
			return null;
		}
		MultipartHttpServletRequest multipartRequest =
		(MultipartHttpServletRequest) request.getRequest();
		MultipartFile file = multipartRequest.getFile("uploadedFile");
		accountHelper.getFileUploadBean().setFile(file);
		String name = file.getName();
		String type = file.getContentType();
		Long size = (Long) file.getSize();
		accountHelper.getFileUploadBean().setFile(file);
		name = file.getOriginalFilename();
		
		
		
		//Check
		AccountValidator.validate(accountHelper.getAccount(), result);
		if (result.hasErrors()) {
			return "accountForm";
		}
		AccountConstraintsValidator.validate(accountHelper.getAccount(),
				result, ydkAccountServiceClient);
		
		if (result.hasErrors()) {
			return "accountForm";
		}
		else {
			//Finish checking, do the business
			
			//First we need to check if there is a image. 
			UploadedFile uploadedFile = null;
			if (accountHelper.getFileUploadBean().getFile() != null)
			{
				//Store the upload image;
				uploadedFile = new UploadedFile(
						accountHelper.getFileUploadBean().getFile().getOriginalFilename(),
						accountHelper.getFileUploadBean().getFile().getContentType(), 
						accountHelper.getFileUploadBean().getFile().getSize(),
						accountHelper.getFileUploadBean().getFile().getName(), 
						(accountHelper.getFileUploadBean().getFile().getBytes()));
				
			}
			
			
			ydkAccountServiceClient.saveAccount(
					WebServiceAccountHelper.wrapAccount(accountHelper.getPreparedAccount(),
							uploadedFile));
			
			
			try {
				mailService.send(mailService.getYdkMail(accountHelper.getAccount().getEmail(), "Hello SrpingEmail", true));
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			status.setComplete();
			return "redirect:account.do?accountName=" + accountHelper.getAccount().getAccountName();
		}
	}

}
