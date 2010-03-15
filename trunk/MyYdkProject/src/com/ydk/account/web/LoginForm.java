package com.ydk.account.web;


import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ydk.account.persistence.entity.*;
import com.ydk.account.utility.YdkRandomString;
import com.ydk.account.validator.AccountConstraintsValidator;
import com.ydk.account.validator.AccountValidator;
import com.ydk.account.webservice.WebServiceYdkAccount;
import com.ydk.account.webservice.YdkAccountMngrService;
import com.ydk.account.webservice.YdkAccountService;
import com.ydk.book.webservice.OperationStatus;
import com.ydk.service.mail.YdkEmailInterface;
import com.ydk.service.mail.YdkMail;
import com.ydk.web.WebControllerBase;

/**
 * Process the login request
 *
 * @author Juergen Hoeller
 * @author Ken Krebs
 */
@Controller
@SessionAttributes({"userLogIn", "myForwardAction"})
public class LoginForm extends WebControllerBase {

	@Autowired
	@Qualifier("ydkAccountMngrServiceClient")
    private YdkAccountMngrService ydkAccountMngrServiceClient;
	
	
	public void setYdkAccountMngrServiceClient(YdkAccountMngrService ydkAccountMngrServiceClient) {
        this.ydkAccountMngrServiceClient = ydkAccountMngrServiceClient;
    }
	
	private YdkEmailInterface mailService;
	
	@Autowired
	public void setYdkEmailService(YdkEmailInterface mailService) {
        this.mailService = mailService;
    }

	public LoginForm() {
		this.thisView = "login";
		this.fromView = null;
		this.errorView = "login";
		this.successView = "mainframe";
		this.successViewRedirect = "mngrSystemHome.do";
		this.homeView = "login.do";
	}

	@RequestMapping(value="/login.do", method = RequestMethod.GET)
	public String setupForm(ModelMap model, ServletWebRequest request) {
		AccountLogin account = new AccountLogin();
//		model.addAttribute(account);
		String tmp = (String) request.getRequest().getAttribute("myForwardAction");
		String forwardAction = request.getParameter("forwardAction");
		if (forwardAction != null)
		{
			model.addAttribute("myForwardAction", forwardAction);
		}
		account.setAccountName("请输入用户名");
		model.addAttribute("user", account);
		String operationMsg = request.getParameter("operationMsg");
		if (operationMsg != null)
		{
			model.addAttribute("operationMsg", operationMsg);
		}
		
		return thisView;
	}

	@RequestMapping(value="/login.do", method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("user") AccountLogin account, 
			BindingResult result, SessionStatus status, Model model, ServletWebRequest request) {
		//First we check the given parameters
		//Then we perform store;
		//Check
		//1 check verification
//		String key = (String) request.getRequest().getAttribute("verification");
		String randomChar = (String) request.getRequest().getSession().getAttribute("randomChar");
		
//		String value = (String) request.getRequest().getParameter("verificationValue");
		String tmp = (String) request.getRequest().getAttribute("myForwardAction");
		if (tmp != null)
		{
			request.getRequest().removeAttribute("myForwardAction");
		}
		String forwardAction = request.getParameter("forwardAction");
		if (forwardAction != null)
		{
			model.addAttribute("myForwardAction", forwardAction);
		}
		if (account.getVerificationValue() == null
				|| !account.getVerificationValue().equals(randomChar))
		{
			result.rejectValue("verificationValue", "validatoin.login.wrongCode",
					"校验码错误");
			return errorView;
		}

		if (!StringUtils.hasLength(account.getAccountName())) {
			result.rejectValue("accountName", "required", "不能为空");
		}
		if (!StringUtils.hasLength(account.getPassword())) {
			result.rejectValue("password", "required", "不能为空");
		}
		if (result.hasErrors()) {
			return errorView;
		}
		else {
			//Finish checking, do the business
			WebServiceYdkAccount webAccount = ydkAccountMngrServiceClient.getYdkAccountByName(
					account.getAccountName());
			if (!webAccount.getOs().getIsSuccessful())
			{
				result.rejectValue("accountName", "not exist", "not exist");
				return errorView;
			}
			
			YdkAccount resultUser = webAccount.getYdkAccount();
			if (resultUser == null)
			{
				result.rejectValue("accountName", "not exist", "not exist");
			}
			else
			{
				//Get, check the password;
				if (!account.getPassword().equals(resultUser.getPassword()))
				{
					result.rejectValue("password", "wrong", "wrong");
				}
			}
			
			if (result.hasErrors()) {
				return errorView;
			}
			
			//Ok, use could log in, Save the user attribute in session
			model.addAttribute("userLogIn", resultUser);
			
			if (forwardAction != null) {
				
				return "redirect:" + forwardAction;
			}
			
			//We should not set session status to complete until user logout
//			status.setComplete();
			
			return "redirect:" + successViewRedirect;
		}
	}
	
	
	@RequestMapping(value="/forgetPassword.do", method = RequestMethod.POST)
	public  ModelAndView processForgetPw(@ModelAttribute("user") AccountLogin account, 
			BindingResult result, SessionStatus status, Model model, ServletWebRequest request) {
		
		//log login info
		// find users by user name
		//1 check verification
//		String key = (String) request.getRequest().getAttribute("verification");
		String randomChar = (String) request.getRequest().getSession().getAttribute("randomChar");
		if (account.getVerificationValue() == null
				|| !account.getVerificationValue().equals(randomChar))
		{
			result.rejectValue("verificationValue", "validatoin.login.wrongCode",
					"wrong");
			return new ModelAndView(errorView);
		}
		
		if (!StringUtils.hasLength(account.getAccountName())) {
			result.rejectValue("accountName", "required", "不能为空");
		}
		
		if (result.hasErrors()) {
			return new ModelAndView(errorView);
		}
		else 
		{
			//Finish checking, do the business
			WebServiceYdkAccount instance = ydkAccountMngrServiceClient.getYdkAccountByName(
					account.getAccountName());
			if (!instance.getOs().getIsSuccessful())
			{
				result.rejectValue("accountName", "not exist", "不存在");
				return new ModelAndView(errorView);
			}
			
			YdkAccount resultUser = instance.getYdkAccount();
			if (resultUser == null)
			{
				result.rejectValue("accountName", "not exist", "不存在");
			}
			else
			{
				String newPw = YdkRandomString.getRandomString(8);
				instance.getYdkAccount().setPassword(newPw);
				OperationStatus os = ydkAccountMngrServiceClient.updateYdkAccount(instance);
				if (os.getIsSuccessful())
				{
					//Now we need to send password to the user's email addr
					YdkMail ydkMail = mailService.getYdkMail(resultUser.getEmail(), "Your Ydk account's password ", true);
					
					ydkMail.setContent("Dear " + resultUser.getName() + ":<br/>" 
							+ "Your password of ydk account: " + resultUser.getName() 
							+ " has been reset to: <b>" + newPw + "</b>.<br/>");
	
					try {
						mailService.send(ydkMail);
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			//We should not set session status to complete until user logout
//			status.setComplete();	
			return new ModelAndView(errorView,
					"operationMsg", "密码已经被重置，很快会发送到你的邮箱中，请检查");
		}

	}
	
	@RequestMapping(value="/logout.do", method = RequestMethod.GET, params="logOut=true")
	public  String processLogOut(@ModelAttribute("userLogIn")YdkAccount userLogIn,
			BindingResult result, SessionStatus status, ServletWebRequest request) {
		
		//log login info
		// find users by user name
		
		status.setComplete();
		return "redirect:" + homeView;
		
	}

}
