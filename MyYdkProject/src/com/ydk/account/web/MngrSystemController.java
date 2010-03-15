package com.ydk.account.web;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;



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
import com.ydk.account.validator.AccountConstraintsValidator;
import com.ydk.account.validator.AccountValidator;
import com.ydk.account.validator.YdkAccountValidator;
import com.ydk.account.webservice.WebServiceYdkAccount;
import com.ydk.account.webservice.WebYdkAccountListResult;
import com.ydk.account.webservice.WebYdkRoleListResult;
import com.ydk.account.webservice.YdkAccountMngrService;
import com.ydk.account.webservice.YdkAccountService;
import com.ydk.account.webservice.YdkBookMngrService;
import com.ydk.account.webservice.YdkBusiQueryService;
import com.ydk.account.webservice.YdkPartnerMngrService;
import com.ydk.account.webservice.YdkSubjectMngrService;
import com.ydk.book.webservice.OperationStatus;
import com.ydk.book.webservice.YdkBookFreeService;
import com.ydk.book.webservice.YdkFrontEndWebService;
import com.ydk.book.webservice.YdkSubjectService;
import com.ydk.web.WebControllerBase;

/**
 * Process the login request
 *
 * @author Juergen Hoeller
 * @author Ken Krebs
 */
@Controller
@SessionAttributes({"ydkRoleList"})
public class MngrSystemController extends WebControllerBase {

	@Autowired
	@Qualifier("ydkAccountMngrServiceClient")
    private YdkAccountMngrService ydkAccountMngrServiceClient;
	
	public void setYdkAccountMngrServiceClient(YdkAccountMngrService ydkAccountMngrServiceClient) {
        this.ydkAccountMngrServiceClient = ydkAccountMngrServiceClient;
    }
//	
//	@Qualifier("ydkBookFreeServiceClient")
//    protected YdkBookFreeService ydkBookFreeService;
//	
//	public void setYdkBookFreeService(@Qualifier("ydkBookFreeServiceClient")YdkBookFreeService service) {
//        this.ydkBookFreeService = service;
//    }
//	
//	@Qualifier("ydkSubjectServiceClient")
//    protected YdkSubjectService ydkSubjectService;
//	
//	public void setYdkSubjectService(@Qualifier("ydkSubjectServiceClient")YdkSubjectService service) {
//        this.ydkSubjectService = service;
//    }
//	
//	@Qualifier("ydkFrontEndServiceClient")
//    protected YdkFrontEndWebService ydkFrontEndWebService;
//	
//	public void setYdkFrontEndWebService(@Qualifier("ydkFrontEndServiceClient")YdkFrontEndWebService service) {
//        this.ydkFrontEndWebService = service;
//    }
//	
//	
//	@Qualifier("ydkSubjectMngrServiceClient")
//    protected YdkSubjectMngrService ydkSubjectMngrService;
//	
//	public void setYdkSubjectMngrService(@Qualifier("ydkSubjectMngrServiceClient")YdkSubjectMngrService service) {
//        this.ydkSubjectMngrService = service;
//    }
//	
//	@Qualifier("ydkPartnerMngrServiceClient")
//    protected YdkPartnerMngrService ydkPartnerMngrService;
//	
//	public void setYdkPartnerMngrService(@Qualifier("ydkPartnerMngrServiceClient")YdkPartnerMngrService service) {
//        this.ydkPartnerMngrService = service;
//    }
//	
//	@Qualifier("ydkBookMngrServiceClient")
//    protected YdkBookMngrService ydkBookMngrService;
//	
//	public void setYdkBookMngrService(@Qualifier("ydkBookMngrServiceClient")YdkBookMngrService service) {
//        this.ydkBookMngrService = service;
//    }
//	
//	@Qualifier("ydkBusiQueryServiceClient")
//    protected YdkBusiQueryService ydkBusiQueryService;
//	
//	public void setYdkBusiQueryService(@Qualifier("ydkBusiQueryServiceClient")YdkBusiQueryService service) {
//        this.ydkBusiQueryService = service;
//    }

	public MngrSystemController() {
		this.thisView = "mainframe";
		this.fromView = null;
		this.errorView = "mainframe";
		this.successView = "mainframe";
		this.successViewRedirect = successView + ".do";
		this.homeView = "first_page";
	}

	
	@RequestMapping("/mngrSystemHome.do")
	public String setupForm(ModelMap model, ServletWebRequest request) {
		return thisView;
	}
	@RequestMapping("/mngrHomePage.do")
	public String setupHomePage(ModelMap model, ServletWebRequest request) {
		return homeView;
	}
	
	@RequestMapping(value="/createYdkAccount.do", method=RequestMethod.GET)
	public ModelAndView createYdkAccountGet(ServletWebRequest request) {

		WebYdkRoleListResult ydkRoleList = ydkAccountMngrServiceClient.getAllYdkRoles();
		
		if (ydkRoleList == null || !ydkRoleList.getOs().getIsSuccessful())
		{
			return new ModelAndView("errorPage", "errorMsg","Could not get YdkRoles");
		}
		if (ydkRoleList.getYdkRoles().size() == 0)
		{
			return new ModelAndView("errorPage", "errorMsg","Could not get YdkRoles");
		}
		
		YdkAccountForm ydkAccountForm = new YdkAccountForm();
//		ydkAccount.setYdkRole(new YdkRole());
		Map model = new HashMap();
		
		model.put("dataForm", ydkAccountForm);
		model.put("ydkRoleList", ydkRoleList.getYdkRoles());
		
		return new ModelAndView("system_account_create", model);
	}
	
	@RequestMapping(value="/createYdkAccount.do", method=RequestMethod.POST)
	public ModelAndView createYdkAccountPost(
			@ModelAttribute("dataForm")YdkAccountForm ydkAccountForm,
			BindingResult result,
			@ModelAttribute("ydkRoleList")List<YdkRole> ydkRoleList, 
			ModelMap model, ServletWebRequest request) {
		
		
		YdkAccountValidator.validate(ydkAccountForm, result);

		if (result.hasErrors()) {
			return new ModelAndView("system_account_create");
		}
		
		//Now we need to check the Unique constrains of YdkAccount.
		WebServiceYdkAccount webAccount = ydkAccountMngrServiceClient.getYdkAccountByName(
				ydkAccountForm.getName());
		if (webAccount.getYdkAccount() != null)
		{
			result.rejectValue("account.name", "duplicate", "duplicate");
			return new ModelAndView("system_account_create");
		}
		
		//Input the infor
		YdkAccount ydkAccount = new YdkAccount();
		ydkAccount.setName(ydkAccountForm.getName());
		ydkAccount.setPassword(ydkAccountForm.getPassword());
		ydkAccount.setEmail(ydkAccountForm.getEmail());
		ydkAccount.setIsActived(ydkAccountForm.getIsActived());
		
		for (YdkRole role : ydkRoleList)
		{
			if (role.getId().equals(ydkAccountForm.getRoleId()))
			{
				ydkAccount.setYdkRole(role);
			}
		}
//		ydkAccount.setIsActived(false);
		
		//OK, now it's time to store the ydkAccount;
		webAccount.setYdkAccount(ydkAccount);
		webAccount.setOs(null);
		OperationStatus os = ydkAccountMngrServiceClient.storeYdkAccount(webAccount);
		
		//Clear the sessioin;
		request.getRequest().getSession().removeAttribute("ydkRoleList");
		
		if (!os.getIsSuccessful())
		{
			return new ModelAndView("system_account_create",
					"operationMsg", "创建管理帐户失败");
		}
		else
		{
			return new ModelAndView("redirect:listYdkAccount.do", 
					"operationMsg", "创建成功");
		}
		
	}
	
	@RequestMapping(value="/changePassword.do", method=RequestMethod.GET)
	public ModelAndView changePasswordGet(ServletWebRequest request) {

		
//		WebYdkRoleListResult ydkRoleList = ydkAccountMngrServiceClient.getAllYdkRoles();
//		
//		if (ydkRoleList == null || !ydkRoleList.getOs().getIsSuccessful())
//		{
//			return new ModelAndView("errorPage", "errorMsg","Could not get YdkRoles");
//		}
//		if (ydkRoleList.getYdkRoles().size() == 0)
//		{
//			return new ModelAndView("errorPage", "errorMsg","Could not get YdkRoles");
//		}
		
		YdkAccountForm ydkAccountForm = new YdkAccountForm();
//		ydkAccountForm.setAccountId(myAccount.getId());
//		ydkAccountForm.setName(myAccount.getName());
//		ydkAccountForm.setEmail(myAccount.getEmail());
//		ydkAccountForm.setIsActived(myAccount.getIsActived());
//		ydkAccountForm.setRoleId(myAccount.getYdkRole().getId());

		Map model = new HashMap();
		
		model.put("dataForm", ydkAccountForm);
//		model.put("ydkRoleList", ydkRoleList.getYdkRoles());
		
		return new ModelAndView("change_admin_password", model);
	}
	
	
	
	@RequestMapping(value="/changePassword.do", method=RequestMethod.POST)
	public ModelAndView changePasswordPost(
			@ModelAttribute("dataForm")YdkAccountForm ydkAccountForm,
			BindingResult result,
			ModelMap model, ServletWebRequest request) {
		
		YdkAccount myAccount = 
			(YdkAccount)request.getRequest().getSession().getAttribute("userLogIn");
		//Check the new password
		if (!StringUtils.hasLength(ydkAccountForm.getPassword())) {
			result.rejectValue("password", "pwRequired", "请输入旧密码");
		}
		if (!StringUtils.hasLength(ydkAccountForm.getPasswordNew())) {
			result.rejectValue("passwordNew", "pwnRequired", "请输入新密码");
		}
		if (!ydkAccountForm.getPassword().equals(myAccount.getPassword())) {
			result.rejectValue("password", "not equal", "密码错误");
		}
		if (!StringUtils.hasLength(ydkAccountForm.getRepeatedPw())) {
			result.rejectValue("repeatedPw", "required", "请确认新密码");
		}
		if (!ydkAccountForm.getRepeatedPw().equals(ydkAccountForm.getPasswordNew())) {
			result.rejectValue("repeatedPw", "not equal", "新密码不一致");
		}

		if (result.hasErrors()) {
			return new ModelAndView("change_admin_password");
		}
		myAccount.setPassword(ydkAccountForm.getPasswordNew());
		WebServiceYdkAccount instance = new WebServiceYdkAccount(myAccount);
		OperationStatus os = ydkAccountMngrServiceClient.updateYdkAccount(instance);
		
		if (!os.getIsSuccessful())
		{
			return new ModelAndView("change_admin_password",
					"operationMsg", "密码修改失败");
		}
		else
		{
			request.getRequest().getSession().setAttribute("userLogIn", myAccount);
			return new ModelAndView("redirect:mngrHomePage.do", 
					"operationMsg", "密码修改成功");
		}
		
	}
	
	
	@RequestMapping(value="/listYdkAccount.do", method=RequestMethod.GET)
	public ModelAndView listYdkAccountGet(ServletWebRequest request) {


		String page = request.getParameter("page");
		String pageNumber = request.getParameter("pageNumber");
		PagedListHolder pageList = null;
		if (page != null || pageNumber != null)
		{
			pageList = (PagedListHolder) request.getRequest().getSession().getAttribute("pageDataList");
		}
		else
		{
			WebYdkAccountListResult ydkAccountList = ydkAccountMngrServiceClient.getAllYdkAccounts();
			
			if (ydkAccountList == null || !ydkAccountList.getOs().getIsSuccessful())
			{
				return new ModelAndView("errorPage", "errorMsg","Could not get YdkRoles");
			}
		
		
			pageList = new PagedListHolder(ydkAccountList.getYdkAccounts());
			pageList.setPageSize(8);
		}
		
		if ("last".equals(page)){
			pageList.setPage(pageList.getLastLinkedPage());
		}
		else if(pageNumber != null){
			Integer pageN = new Integer(pageNumber);
			if (pageN.intValue() >= 0)
			{
				pageList.setPage(pageN.intValue());
			}
		}
		
		//Get the modify identifier;
		String accountId = request.getParameter("accountId");
		Map model = new HashMap();
		YdkAccountForm accountForm = new YdkAccountForm();
		if (accountId != null && !accountId.isEmpty())
		{
			Long id = new Long(accountId);
			//user try to modify a record;
			WebServiceYdkAccount webAccount = ydkAccountMngrServiceClient.getYdkAccountById(id);
			if (webAccount.getOs().getIsSuccessful())
			{
				accountForm.setAccountId(id);
				accountForm.setName(webAccount.getYdkAccount().getName());
				accountForm.setEmail(webAccount.getYdkAccount().getEmail());
				accountForm.setIsActived(webAccount.getYdkAccount().getIsActived());
				accountForm.setRoleId(webAccount.getYdkAccount().getYdkRole().getId());
			}
			
			WebYdkRoleListResult ydkRoleList = ydkAccountMngrServiceClient.getAllYdkRoles();
			
			if (ydkRoleList == null || !ydkRoleList.getOs().getIsSuccessful())
			{
				return new ModelAndView("errorPage", "errorMsg","Could not get YdkRoles");
			}
			if (ydkRoleList.getYdkRoles().size() == 0)
			{
				return new ModelAndView("errorPage", "errorMsg","Could not get YdkRoles");
			}
			model.put("ydkRoleList", ydkRoleList.getYdkRoles());
			request.getRequest().getSession().setAttribute("dataFormSession", webAccount.getYdkAccount());
		}
		model.put("dataForm", accountForm);
		
		
		request.getRequest().getSession().setAttribute("pageDataList", pageList);
		
		return new ModelAndView("system_account_list", model);
	}
	
	
	@RequestMapping(value="/listYdkAccount.do", method=RequestMethod.POST)
	public ModelAndView listYdkAccountDelete(@ModelAttribute("dataForm")YdkAccountForm ydkAccountForm, 
			ServletWebRequest request) {


		for (Long id : ydkAccountForm.getKeys())
		{
			ydkAccountMngrServiceClient.deleteYdkAccount(id);
		}
		
		return new ModelAndView("redirect:listYdkAccount.do");
	}
	
	@RequestMapping(value="/modifyYdkAccount.do", method=RequestMethod.POST)
	public ModelAndView modifyYdkAccountPost(@ModelAttribute("dataForm")YdkAccountForm ydkAccountForm, 
			ServletWebRequest request) {

		YdkAccount ydkAccountSession = 
			(YdkAccount)request.getRequest().getSession().getAttribute("dataFormSession");
		//Check the validation of ydkAccountForm and then store ydkAccountForm;
		if (ydkAccountForm.getAccountId() != null)
		{
//			YdkAccount ydkAccount = new YdkAccount();
			ydkAccountSession.setId(ydkAccountForm.getAccountId());
			ydkAccountSession.setEmail(ydkAccountForm.getEmail());
			ydkAccountSession.setName(ydkAccountForm.getName());
			ydkAccountSession.setIsActived(ydkAccountForm.getIsActived());
			ydkAccountSession.setYdkRole(new YdkRole(ydkAccountForm.getRoleId()));
			WebServiceYdkAccount instance = new WebServiceYdkAccount(ydkAccountSession);
			ydkAccountMngrServiceClient.updateYdkAccount(instance);
			
			YdkAccount myAccount = 
				(YdkAccount)request.getRequest().getSession().getAttribute("userLogIn");
			if (myAccount.getId().equals(ydkAccountForm.getAccountId()))
			{
				
				request.getRequest().getSession().setAttribute("userLogIn", instance.getYdkAccount());
			}
		}
		
		return new ModelAndView("redirect:listYdkAccount.do");
	}
	
	

	

}
