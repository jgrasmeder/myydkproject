package com.ydk.account.web;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ydk.account.core.FileUploadBean;
import com.ydk.account.persistence.entity.*;
import com.ydk.account.validator.AccountConstraintsValidator;
import com.ydk.account.validator.AccountValidator;
import com.ydk.account.validator.EventValidator;
import com.ydk.account.validator.PartnerTypeValidator;
import com.ydk.account.validator.PartnerValidator;
import com.ydk.account.validator.ReaderValidator;
import com.ydk.account.validator.TransactionTypeValidator;
import com.ydk.account.validator.TransactionValidator;
import com.ydk.account.validator.YdkAccountValidator;
import com.ydk.account.webservice.WebPartnerListResult;
import com.ydk.account.webservice.WebPartnerTypeListResult;
import com.ydk.account.webservice.WebReaderListResult;
import com.ydk.account.webservice.WebServicePartner;
import com.ydk.account.webservice.WebServicePartnerType;
import com.ydk.account.webservice.WebServiceReader;
import com.ydk.account.webservice.WebServiceTransaction;
import com.ydk.account.webservice.WebServiceTransactionType;
import com.ydk.account.webservice.WebServiceYdkAccount;
import com.ydk.account.webservice.WebTransactionListResult;
import com.ydk.account.webservice.WebTransactionTypeListResult;
import com.ydk.account.webservice.WebYdkAccountListResult;
import com.ydk.account.webservice.WebYdkRoleListResult;
import com.ydk.account.webservice.YdkAccountMngrService;
import com.ydk.account.webservice.YdkAccountService;
import com.ydk.account.webservice.YdkBookMngrService;
import com.ydk.account.webservice.YdkBusiQueryService;
import com.ydk.account.webservice.YdkPartnerMngrService;
import com.ydk.account.webservice.YdkSubjectMngrService;
import com.ydk.account.webservice.YdkTransactionMngrService;
import com.ydk.book.persistence.entity.Event;
import com.ydk.book.persistence.entity.EventCategory;
import com.ydk.book.webservice.OperationStatus;
import com.ydk.book.webservice.WebEventCateListResult;
import com.ydk.book.webservice.WebEventListResult;
import com.ydk.book.webservice.WebServiceBook;
import com.ydk.book.webservice.WebServiceEvent;
import com.ydk.book.webservice.WebServiceFile;
import com.ydk.book.webservice.WebServiceListParams;
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
@SessionAttributes({"typeList"})
public class MngrBusinessController extends WebControllerBase {

//	@Autowired
//	@Qualifier("ydkAccountMngrServiceClient")
//    private YdkAccountMngrService ydkAccountMngrServiceClient;
//	
//	public void setYdkAccountMngrServiceClient(YdkAccountMngrService ydkAccountMngrServiceClient) {
//        this.ydkAccountMngrServiceClient = ydkAccountMngrServiceClient;
//    }
//	
//	@Autowired
//	@Qualifier("ydkBookFreeServiceClient")
//    protected YdkBookFreeService ydkBookFreeService;
//	
//	public void setYdkBookFreeService(@Qualifier("ydkBookFreeServiceClient")YdkBookFreeService service) {
//        this.ydkBookFreeService = service;
//    }
//	
//	@Autowired
//	@Qualifier("ydkSubjectServiceClient")
//    protected YdkSubjectService ydkSubjectService;
//	
//	public void setYdkSubjectService(@Qualifier("ydkSubjectServiceClient")YdkSubjectService service) {
//        this.ydkSubjectService = service;
//    }
//	
	@Autowired
	@Qualifier("ydkFrontEndServiceClient")
    protected YdkFrontEndWebService ydkFrontEndWebService;
	
	public void setYdkFrontEndWebService(@Qualifier("ydkFrontEndServiceClient")YdkFrontEndWebService service) {
        this.ydkFrontEndWebService = service;
    }
//	
//	@Autowired
//	@Qualifier("ydkSubjectMngrServiceClient")
//    protected YdkSubjectMngrService ydkSubjectMngrService;
//	
//	public void setYdkSubjectMngrService(@Qualifier("ydkSubjectMngrServiceClient")YdkSubjectMngrService service) {
//        this.ydkSubjectMngrService = service;
//    }
	
	@Autowired
	@Qualifier("ydkPartnerMngrServiceClient")
    protected YdkPartnerMngrService ydkPartnerMngrService;
	
	public void setYdkPartnerMngrService(@Qualifier("ydkPartnerMngrServiceClient")YdkPartnerMngrService service) {
        this.ydkPartnerMngrService = service;
    }
	
	@Autowired
	@Qualifier("ydkBookMngrServiceClient")
    protected YdkBookMngrService ydkBookMngrService;
	
	public void setYdkBookMngrService(@Qualifier("ydkBookMngrServiceClient")YdkBookMngrService service) {
        this.ydkBookMngrService = service;
    }
//	
//	@Autowired
//	@Qualifier("ydkBusiQueryServiceClient")
//    protected YdkBusiQueryService ydkBusiQueryService;
//	
//	public void setYdkBusiQueryService(@Qualifier("ydkBusiQueryServiceClient")YdkBusiQueryService service) {
//        this.ydkBusiQueryService = service;
//    }
	@Autowired
	@Qualifier("ydkTransactionMngrServiceClient")
    protected YdkTransactionMngrService ydkTransactionMngrService;
	
	public void setYdkTransactionMngrService(@Qualifier("ydkTransactionMngrServiceClient")YdkTransactionMngrService service) {
        this.ydkTransactionMngrService = service;
    }

	public MngrBusinessController() {
		this.thisView = "mainframe";
		this.fromView = null;
		this.errorView = "mainframe";
		this.successView = "mainframe";
		this.successViewRedirect = successView + ".do";
		this.homeView = "login.do";
	}

	
	
	@RequestMapping(value="/createPartnerType.do", method=RequestMethod.GET)
	public ModelAndView createPartnerTypeGet(ServletWebRequest request) {
		
		String view ="partner_type_create";

//		WebYdkRoleListResult ydkRoleList = ydkAccountMngrServiceClient.getAllYdkRoles();
		
//		if (ydkRoleList == null || !ydkRoleList.getOs().getIsSuccessful())
//		{
//			return new ModelAndView("errorPage", "errorMsg","Could not get YdkRoles");
//		}
//		if (ydkRoleList.getYdkRoles().size() == 0)
//		{
//			return new ModelAndView("errorPage", "errorMsg","Could not get YdkRoles");
//		}
		
		
//		PartnerType instance = new PartnerType();
		PartnerTypeForm dataForm = new PartnerTypeForm();
//		dataForm.setInstance(instance);
		
//		ydkAccount.setYdkRole(new YdkRole());
		Map model = new HashMap();
		
		model.put("dataForm", dataForm);
//		model.put("ydkRoleList", ydkRoleList.getYdkRoles());
		
		return new ModelAndView(view, model);
	}
	
	
	@RequestMapping(value="/createPartnerType.do", method=RequestMethod.POST)
	public ModelAndView createPartnerTypePost(
			@ModelAttribute("dataForm")PartnerTypeForm dataForm,
			BindingResult result,
//			@ModelAttribute("ydkRoleList")List<YdkRole> ydkRoleList, 
			ModelMap model, ServletWebRequest request) {
		
		String view ="partner_type_create";
		
		PartnerTypeValidator.validate(dataForm, result);

		if (result.hasErrors()) {
			return new ModelAndView(view, "operationMsg", "创建失败");
		}
		
		//Now we need to check the Unique constrains of YdkAccount.
		WebServicePartnerType webAccount = ydkPartnerMngrService.getPartnerTypeByName(
				dataForm.getInstance().getType());
		if (webAccount.getPartnerType() != null)
		{
			result.rejectValue("instance.type", "duplicate", "duplicate");
			return new ModelAndView(view, "operationMsg", "创建失败");
		}
		
		//Input the infor
		PartnerType instance = dataForm.getInstance();
		
		
		
//		for (YdkRole role : ydkRoleList)
//		{
//			if (role.getId().equals(ydkAccountForm.getRoleId()))
//			{
//				ydkAccount.setYdkRole(role);
//			}
//		}
		
		//OK, now it's time to store the ydkAccount;
		webAccount.setPartnerType(instance);
		webAccount.setOs(null);
		OperationStatus os = ydkPartnerMngrService.storePartnerType(webAccount);
		
		//Clear the sessioin;
//		request.getRequest().getSession().removeAttribute("ydkRoleList");
		
		if (!os.getIsSuccessful())
		{
			return new ModelAndView(view,
					"operationMsg", "创建失败");
		}
		else
		{
			return new ModelAndView("redirect:listPartnerType.do", 
					"operationMsg", "创建成功");
		}
		
	}
	
	
	@RequestMapping(value="/listPartnerType.do", method=RequestMethod.GET)
	public ModelAndView listPartnerTypeGet(ServletWebRequest request) {

		String view ="partner_type_list";

		String page = request.getParameter("page");
		String pageNumber = request.getParameter("pageNumber");
		PagedListHolder pageList = null;
		if (page != null || pageNumber != null)
		{
			pageList = (PagedListHolder) request.getRequest().getSession().getAttribute("pageDataList");
		}
		else
		{
			WebServiceListParams params = new WebServiceListParams();
			params.setPageSize(WebServiceListParams.MAX_PAGESIZE);
			params.setPageOffset(0);
			WebPartnerTypeListResult resultList = ydkPartnerMngrService.getAllPartnerTypes(params);
			
			if (resultList == null || !resultList.getOs().getIsSuccessful())
			{
				return new ModelAndView("errorPage", "errorMsg","Could not get Partner Types");
			}
		
		
			pageList = new PagedListHolder(resultList.getList());
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
		String itemId = request.getParameter("itemId");
		Map model = new HashMap();
		PartnerTypeForm dataForm = new PartnerTypeForm();
		if (itemId != null && !itemId.isEmpty())
		{
			Long id = new Long(itemId);
			//user try to modify a record;
			WebServicePartnerType webAccount = ydkPartnerMngrService.getPartnerTypeById(id);
			if (webAccount.getOs().getIsSuccessful())
			{
				dataForm.setInstance(webAccount.getPartnerType());
			}
			
//			WebYdkRoleListResult ydkRoleList = ydkAccountMngrServiceClient.getAllYdkRoles();
//			
//			if (ydkRoleList == null || !ydkRoleList.getOs().getIsSuccessful())
//			{
//				return new ModelAndView("errorPage", "errorMsg","Could not get YdkRoles");
//			}
//			if (ydkRoleList.getYdkRoles().size() == 0)
//			{
//				return new ModelAndView("errorPage", "errorMsg","Could not get YdkRoles");
//			}
//			model.put("ydkRoleList", ydkRoleList.getYdkRoles());
		}
		model.put("dataForm", dataForm);
		
		String operationMsg = request.getParameter("operationMsg");
		if (operationMsg != null && !operationMsg.isEmpty())
		{
			model.put("operationMsg", operationMsg);
		}
		
		
		request.getRequest().getSession().setAttribute("pageDataList", pageList);
		
		return new ModelAndView(view, model);
	}
	
	
	@RequestMapping(value="/listPartnerType.do", method=RequestMethod.POST)
	public ModelAndView listPartnerTypeDelete(@ModelAttribute("dataForm")PartnerTypeForm dataForm, 
			ServletWebRequest request) {


		for (Long id : dataForm.getKeys())
		{
			ydkPartnerMngrService.deletePartnerType(id);
		}
		
		return new ModelAndView("redirect:listPartnerType.do", "operationMsg", "删除成功");
	}
	
	@RequestMapping(value="/modifyPartnerType.do", method=RequestMethod.POST)
	public ModelAndView modifyPartnerTypePost(@ModelAttribute("dataForm")PartnerTypeForm dataForm, 
			BindingResult result, ServletWebRequest request) {


		//Check the validation of ydkAccountForm and then store ydkAccountForm;
		if (dataForm.getInstance() == null 
				|| dataForm.getInstance().getId() == null 
				|| dataForm.getInstance().getType() == null
				|| dataForm.getInstance().getType().isEmpty())
		{
			result.rejectValue("instance.type", "required", "不能为空");
			return new ModelAndView("redirect:listPartnerType.do", "operationMsg", "修改失败");
		}

		WebServicePartnerType instance = new WebServicePartnerType(dataForm.getInstance());
		ydkPartnerMngrService.updatePartnerType(instance);
		
		
		return new ModelAndView("redirect:listPartnerType.do", "operationMsg", "修改成功");
	}
	
	
	
	
	/**
	 * 
	 * 
	 * 
	 * For Partner Controller
	 * 
	 * 
	 */
	
	@RequestMapping(value="/createPartner.do", method=RequestMethod.GET)
	public ModelAndView createPartnerGet(ServletWebRequest request) {
		
		String view ="partner_create";
		
		WebServiceListParams params = new WebServiceListParams();
		params.setPageSize(WebServiceListParams.MAX_PAGESIZE);
		params.setPageOffset(0);
		WebPartnerTypeListResult resultList = ydkPartnerMngrService.getAllPartnerTypes(params);
		
		
		if (resultList == null || !resultList.getOs().getIsSuccessful())
		{
			return new ModelAndView("errorPage", "errorMsg","无法获得合作伙伴类型");
		}
		if (resultList.getList().size() == 0)
		{
			return new ModelAndView("errorPage", "errorMsg","无法获得合作伙伴类型");
		}
		
		
		PartnerForm dataForm = new PartnerForm();

		
		Map model = new HashMap();
		
		model.put("dataForm", dataForm);
		model.put("typeList", resultList.getList());
		
		return new ModelAndView(view, model);
	}
	
	
	@RequestMapping(value="/createPartner.do", method=RequestMethod.POST)
	public ModelAndView createPartnerPost(
			@ModelAttribute("dataForm")PartnerForm dataForm,
			BindingResult result,
			@ModelAttribute("typeList")List<PartnerType> typeList, 
			ModelMap model, ServletWebRequest request) {
		
		String view ="partner_create";
		
		PartnerValidator.validate(dataForm, result);

		if (result.hasErrors()) {
			return new ModelAndView(view);
		}
		
		//Now we need to check the Unique constrains of YdkAccount.
		WebServicePartner webInstance = ydkPartnerMngrService.getPartnerByName(
				dataForm.getInstance().getName());
		if (webInstance.getPartner() != null)
		{
			result.rejectValue("instance.name", "duplicate", "已经存在");
			return new ModelAndView(view);
		}
		
		//Input the infor
		Partner instance = dataForm.getInstance();
		
		
		
		for (PartnerType type : typeList)
		{
			if (type.getId().equals(dataForm.getInstance().getPartnerType().getId()))
			{
				dataForm.getInstance().setPartnerType(type);
			}
		}
		
		//OK, now it's time to store the Partner;
		webInstance.setPartner(instance);
		webInstance.setOs(null);
		OperationStatus os = ydkPartnerMngrService.storePartner(webInstance);
		
		//Clear the sessioin;
		request.getRequest().getSession().removeAttribute("typeList");
		
		if (!os.getIsSuccessful())
		{
			return new ModelAndView("redirect:listPartner.do",
					"operationMsg", "创建失败");
		}
		else
		{
			return new ModelAndView("redirect:listPartner.do", 
					"operationMsg", "创建成功");
		}
		
	}
	
	
	@RequestMapping(value="/listPartner.do", method=RequestMethod.GET)
	public ModelAndView listPartnerGet(ServletWebRequest request) {

		String view ="partner_list";

		String page = request.getParameter("page");
		String pageNumber = request.getParameter("pageNumber");
		PagedListHolder pageList = null;
		if (page != null || pageNumber != null)
		{
			pageList = (PagedListHolder) request.getRequest().getSession().getAttribute("pageDataList");
		}
		else
		{
			WebServiceListParams params = new WebServiceListParams();
			params.setPageSize(WebServiceListParams.MAX_PAGESIZE);
			params.setPageOffset(0);
			WebPartnerListResult resultList = ydkPartnerMngrService.getAllPartners(params);
			
			if (resultList == null || !resultList.getOs().getIsSuccessful())
			{
				return new ModelAndView("errorPage", "errorMsg","Could not get Partners");
			}
		
			pageList = new PagedListHolder(resultList.getList());
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
		String itemId = request.getParameter("itemId");
		Map model = new HashMap();
		PartnerForm dataForm = new PartnerForm();
		if (itemId != null && !itemId.isEmpty())
		{
			Long id = new Long(itemId);
			//user try to modify a record;
			WebServicePartner webInstance = ydkPartnerMngrService.getPartnerById(id);
			if (webInstance.getOs().getIsSuccessful())
			{
				dataForm.setInstance(webInstance.getPartner());
			}
			
			WebServiceListParams params = new WebServiceListParams();
			params.setPageSize(WebServiceListParams.MAX_PAGESIZE);
			params.setPageOffset(0);
			WebPartnerTypeListResult resultList = ydkPartnerMngrService.getAllPartnerTypes(params);
			
			if (resultList == null || !resultList.getOs().getIsSuccessful())
			{
				return new ModelAndView("errorPage", "errorMsg","无法获得合作伙伴类型");
			}
			if (resultList.getList().size() == 0)
			{
				return new ModelAndView("errorPage", "errorMsg","无法获得合作伙伴类型");
			}
			model.put("typeList", resultList.getList());
			request.getRequest().getSession().setAttribute("dataFormSession", dataForm);
		}
		model.put("dataForm", dataForm);
		
		String operationMsg = request.getParameter("operationMsg");
		if (operationMsg != null && !operationMsg.isEmpty())
		{
			model.put("operationMsg", operationMsg);
		}
		
		
		request.getRequest().getSession().setAttribute("pageDataList", pageList);
		
		return new ModelAndView(view, model);
	}
	
	
	@RequestMapping(value="/listPartner.do", method=RequestMethod.POST)
	public ModelAndView listPartnerDelete(@ModelAttribute("dataForm")PartnerForm dataForm, 
			ServletWebRequest request) {


		for (Long id : dataForm.getKeys())
		{
			ydkPartnerMngrService.deletePartner(id);
		}
		
		return new ModelAndView("redirect:listPartner.do", "operationMsg", "删除成功");
	}
	
	@RequestMapping(value="/modifyPartner.do", method=RequestMethod.POST)
	public ModelAndView modifyPartnerPost(@ModelAttribute("dataForm")PartnerForm dataForm, 
			BindingResult result, ServletWebRequest request) {


		PartnerValidator.validate(dataForm, result);
		
		PartnerForm dataFormSession = (PartnerForm)request.getRequest().getSession().getAttribute("dataFormSession");

		
		if (result.hasErrors() || dataFormSession == null) {
			return new ModelAndView("redirect:listPartner.do", "operationMsg", "修改失败，请检查修改的值");
		}
		dataForm.prepareForModify(dataFormSession.getInstance());

		WebServicePartner instance = new WebServicePartner(dataFormSession.getInstance());
		ydkPartnerMngrService.updatePartner(instance);
		
		
		return new ModelAndView("redirect:listPartner.do");
	}
	
	
	
	/**
	 * 
	 * 
	 * 
	 * For Event Controller
	 * 
	 * 
	 */
	
	@RequestMapping(value="/viewReader.do", method=RequestMethod.GET)
	public ModelAndView viewReaderGet(ServletWebRequest request) {
		
		String view ="reader_view";
		
		String sId = request.getParameter("itemId");
		if (sId == null || sId.isEmpty())
		{
			return new ModelAndView(view, "operationMsg", "无效的Id");
		}
		Long id = new Long((sId));
		ReaderForm dataForm = null;
		//user try to modify a record;
		WebServiceReader webInstance = ydkPartnerMngrService.getReaderById(id);
		if (webInstance.getOs().getIsSuccessful())
		{
			dataForm = new ReaderForm((webInstance.getReader()));
		}
		
		Map model = new HashMap();
		model.put("dataForm", dataForm);
		//Get Reader Cotent 
		WebServiceFile result = 
			ydkFrontEndWebService.getFileByName(dataForm.getInstance().getPortrait());

		if (result != null && result.getOs().getIsSuccessful())
		{
			try {
				model.put("portrait", new String(result.getFile().getFileContent(),"utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		
		return new ModelAndView(view, model);
	}
	
	
	
	@RequestMapping(value="/createReader.do", method=RequestMethod.GET)
	public ModelAndView createReaderGet(ServletWebRequest request) {
		
		String view ="reader_create";
		
		WebServiceListParams params = new WebServiceListParams();
		params.setPageSize(WebServiceListParams.MAX_PAGESIZE);
		params.setPageOffset(0);
		WebPartnerListResult resultList = ydkPartnerMngrService.getAllPartners(params);
		
		if (resultList == null || !resultList.getOs().getIsSuccessful())
		{
			return new ModelAndView("errorPage", "errorMsg","无法获得合作伙伴类型");
		}
		if (resultList.getList().size() == 0)
		{
			return new ModelAndView("errorPage", "errorMsg","无法获得合作伙伴类型");
		}
		
		
		ReaderForm dataForm = new ReaderForm();

		
		Map model = new HashMap();
		
		model.put("dataForm", dataForm);
//		request.getRequest().getSession().setAttribute("typeList", resultList.getList())
		model.put("typeList", resultList.getList());
		
		return new ModelAndView(view, model);
	}
	
	
	
	
	@RequestMapping(value="/createReader.do", method=RequestMethod.POST)
	public ModelAndView createReaderPost(
			@ModelAttribute("dataForm")ReaderForm dataForm,
			BindingResult result,
			@ModelAttribute("typeList")List<Partner> typeList, 
			ModelMap model, ServletWebRequest request) {
		
		String view ="reader_create";
		ReaderValidator.validate(dataForm, result);

		if (result.hasErrors()) {
			return new ModelAndView(view);
		}
		
		WebServiceReader webInstance = new WebServiceReader();
		
		//Check uploaded files
		//1. all the images could be null
		//2. all the content should not be null
		
		//content file
		MultipartHttpServletRequest multipartRequest =
			(MultipartHttpServletRequest) request.getRequest();
		String checkResult = null;
		MultipartFile portraitImage = multipartRequest.getFile("portraitImage");
		
		if (portraitImage != null && !portraitImage.isEmpty())
		{
			checkResult = FileUploadBean.validateFile(portraitImage);
			if (checkResult != null)
			{
				return new ModelAndView(view, "operationMsg", checkResult);
			}
			
			try {
				webInstance.setPortraitImage(FileUploadBean.getUploadedFile(portraitImage));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ModelAndView(view, "operationMsg", "上传文件失败");
			}
		}
		
		
		
		
		
		
		
		
		
		//Now we need to check the Unique constrains of YdkAccount.
		WebServiceReader webInstanceCheck = ydkPartnerMngrService.getReaderByName(
				dataForm.getInstance().getNickName());
		if (webInstanceCheck.getReader() != null)
		{
			result.rejectValue("instance.title", "duplicate", "已经存在");
			return new ModelAndView(view);
		}
		
		//Input the infor
		Reader instance = dataForm.getInstance();
		
		for (Partner type : typeList)
		{
			if (type.getId().equals(dataForm.getInstance().getPartner().getId()))
			{
				dataForm.getInstance().setPartner(type);
			}
		}
		
		//OK, now it's time to store the Reader;
		webInstance.setReader(instance);
		webInstance.setOs(null);
		OperationStatus os = ydkPartnerMngrService.storeReader(webInstance);
		
		//Clear the sessioin;
		request.getRequest().getSession().removeAttribute("typeList");
		
		if (!os.getIsSuccessful())
		{
			return new ModelAndView("redirect:listReader.do",
					"operationMsg", "创建失败");
		}
		else
		{
			return new ModelAndView("redirect:listReader.do", 
					"operationMsg", "创建成功");
		}
		
	}
	
	
	@RequestMapping(value="/listReader.do", method=RequestMethod.GET)
	public ModelAndView listReaderGet(ServletWebRequest request) {

		String view ="reader_list";

		String page = request.getParameter("page");
		String pageNumber = request.getParameter("pageNumber");
		PagedListHolder pageList = null;
		if (page != null || pageNumber != null)
		{
			pageList = (PagedListHolder) request.getRequest().getSession().getAttribute("pageDataList");
		}
		else
		{
			WebServiceListParams params = new WebServiceListParams();
			params.setPageSize(WebServiceListParams.MAX_PAGESIZE);
			params.setPageOffset(0);
			WebReaderListResult resultList = ydkPartnerMngrService.getReaders(params);
			
			if (resultList == null || !resultList.getOs().getIsSuccessful())
			{
				return new ModelAndView("errorPage", "errorMsg","Could not get Readers");
			}
		
			pageList = new PagedListHolder(resultList.getList());
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
		String itemId = request.getParameter("itemId");
		Map model = new HashMap();
		ReaderForm dataForm = new ReaderForm();
		if (itemId != null && !itemId.isEmpty())
		{
			Long id = new Long(itemId);
			//user try to modify a record;
			WebServiceReader webInstance = ydkPartnerMngrService.getReaderById(id);
			if (webInstance.getOs().getIsSuccessful())
			{
				dataForm.setInstance(webInstance.getReader());
			}
			
			WebServiceListParams params = new WebServiceListParams();
			params.setPageSize(WebServiceListParams.MAX_PAGESIZE);
			params.setPageOffset(0);
			WebPartnerListResult resultList = ydkPartnerMngrService.getAllPartners(params);
			
			if (resultList == null || !resultList.getOs().getIsSuccessful())
			{
				return new ModelAndView("errorPage", "errorMsg","无法获得合作伙伴类型");
			}
			if (resultList.getList().size() == 0)
			{
				return new ModelAndView("errorPage", "errorMsg","无法获得合作伙伴类型");
			}
			model.put("typeList", resultList.getList());
			request.getRequest().getSession().setAttribute("dataFormSession", dataForm);
		}
		model.put("dataForm", dataForm);
		
		String operationMsg = request.getParameter("operationMsg");
		if (operationMsg != null && !operationMsg.isEmpty())
		{
			model.put("operationMsg", operationMsg);
		}
		
		
		request.getRequest().getSession().setAttribute("pageDataList", pageList);
		
		return new ModelAndView(view, model);
	}
	
	
	@RequestMapping(value="/listReader.do", method=RequestMethod.POST)
	public ModelAndView listReaderDelete(@ModelAttribute("dataForm")ReaderForm dataForm, 
			ServletWebRequest request) {


		for (Long id : dataForm.getKeys())
		{
			ydkPartnerMngrService.deleteReader(id);
		}
		
		return new ModelAndView("redirect:listReader.do", "operationMsg", "删除成功");
	}
	
	@RequestMapping(value="/modifyReader.do", method=RequestMethod.POST)
	public ModelAndView modifyReaderPost(@ModelAttribute("dataForm")ReaderForm dataForm, 
			BindingResult result, ServletWebRequest request) {


		ReaderValidator.validate(dataForm, result);
		
		ReaderForm dataFormSession = (ReaderForm)request.getRequest().getSession().getAttribute("dataFormSession");

		
		if (result.hasErrors() || dataFormSession == null) {
			return new ModelAndView("redirect:listReader.do", "operationMsg", "修改失败，请检查修改的值");
		}
		dataForm.prepareForModify(dataFormSession.getInstance());

		WebServiceReader instance = new WebServiceReader(dataFormSession.getInstance());
		ydkPartnerMngrService.updateReader(instance);
		
		
		return new ModelAndView("redirect:listReader.do");
	}
	
	
	
	
	@RequestMapping(value="/createTransactionType.do", method=RequestMethod.GET)
	public ModelAndView createTransactionTypeGet(ServletWebRequest request) {
		
		String view ="transaction_type_create";

//		WebYdkRoleListResult ydkRoleList = ydkAccountMngrServiceClient.getAllYdkRoles();
		
//		if (ydkRoleList == null || !ydkRoleList.getOs().getIsSuccessful())
//		{
//			return new ModelAndView("errorPage", "errorMsg","Could not get YdkRoles");
//		}
//		if (ydkRoleList.getYdkRoles().size() == 0)
//		{
//			return new ModelAndView("errorPage", "errorMsg","Could not get YdkRoles");
//		}
		
		
//		TransactionType instance = new TransactionType();
		TransactionTypeForm dataForm = new TransactionTypeForm();
//		dataForm.setInstance(instance);
		
//		ydkAccount.setYdkRole(new YdkRole());
		Map model = new HashMap();
		
		model.put("dataForm", dataForm);
//		model.put("ydkRoleList", ydkRoleList.getYdkRoles());
		
		return new ModelAndView(view, model);
	}
	
	
	@RequestMapping(value="/createTransactionType.do", method=RequestMethod.POST)
	public ModelAndView createTransactionTypePost(
			@ModelAttribute("dataForm")TransactionTypeForm dataForm,
			BindingResult result,
//			@ModelAttribute("ydkRoleList")List<YdkRole> ydkRoleList, 
			ModelMap model, ServletWebRequest request) {
		
		String view ="transaction_type_create";
		
		TransactionTypeValidator.validate(dataForm, result);

		if (result.hasErrors()) {
			return new ModelAndView(view, "operationMsg", "创建失败");
		}
		
		//Now we need to check the Unique constrains of YdkAccount.
		WebServiceTransactionType webAccount = ydkTransactionMngrService.getTransactionTypeByName(
				dataForm.getInstance().getType());
		if (webAccount.getTransactionType() != null)
		{
			result.rejectValue("instance.type", "duplicate", "duplicate");
			return new ModelAndView(view, "operationMsg", "创建失败");
		}
		
		//Input the infor
		TransactionType instance = dataForm.getInstance();
		
		
		
//		for (YdkRole role : ydkRoleList)
//		{
//			if (role.getId().equals(ydkAccountForm.getRoleId()))
//			{
//				ydkAccount.setYdkRole(role);
//			}
//		}
		
		//OK, now it's time to store the ydkAccount;
		webAccount.setTransactionType(instance);
		webAccount.setOs(null);
		OperationStatus os = ydkTransactionMngrService.storeTransactionType(webAccount);
		
		//Clear the sessioin;
//		request.getRequest().getSession().removeAttribute("ydkRoleList");
		
		if (!os.getIsSuccessful())
		{
			return new ModelAndView(view,
					"operationMsg", "创建失败");
		}
		else
		{
			return new ModelAndView("redirect:listTransactionType.do", 
					"operationMsg", "创建成功");
		}
		
	}
	
	
	@RequestMapping(value="/listTransactionType.do", method=RequestMethod.GET)
	public ModelAndView listTransactionTypeGet(ServletWebRequest request) {

		String view ="transaction_type_list";

		String page = request.getParameter("page");
		String pageNumber = request.getParameter("pageNumber");
		PagedListHolder pageList = null;
		if (page != null || pageNumber != null)
		{
			pageList = (PagedListHolder) request.getRequest().getSession().getAttribute("pageDataList");
		}
		else
		{
			WebServiceListParams params = new WebServiceListParams();
			params.setPageSize(WebServiceListParams.MAX_PAGESIZE);
			params.setPageOffset(0);
			WebTransactionTypeListResult resultList = ydkTransactionMngrService.getAllTransactionTypes(params);
			
			if (resultList == null || !resultList.getOs().getIsSuccessful())
			{
				return new ModelAndView("errorPage", "errorMsg","Could not get Partner Types");
			}
		
		
			pageList = new PagedListHolder(resultList.getList());
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
		String itemId = request.getParameter("itemId");
		Map model = new HashMap();
		TransactionTypeForm dataForm = new TransactionTypeForm();
		if (itemId != null && !itemId.isEmpty())
		{
			Long id = new Long(itemId);
			//user try to modify a record;
			WebServiceTransactionType webAccount = ydkTransactionMngrService.getTransactionTypeById(id);
			if (webAccount.getOs().getIsSuccessful())
			{
				dataForm.setInstance(webAccount.getTransactionType());
			}
			
//			WebYdkRoleListResult ydkRoleList = ydkAccountMngrServiceClient.getAllYdkRoles();
//			
//			if (ydkRoleList == null || !ydkRoleList.getOs().getIsSuccessful())
//			{
//				return new ModelAndView("errorPage", "errorMsg","Could not get YdkRoles");
//			}
//			if (ydkRoleList.getYdkRoles().size() == 0)
//			{
//				return new ModelAndView("errorPage", "errorMsg","Could not get YdkRoles");
//			}
//			model.put("ydkRoleList", ydkRoleList.getYdkRoles());
		}
		model.put("dataForm", dataForm);
		
		String operationMsg = request.getParameter("operationMsg");
		if (operationMsg != null && !operationMsg.isEmpty())
		{
			model.put("operationMsg", operationMsg);
		}
		
		
		request.getRequest().getSession().setAttribute("pageDataList", pageList);
		
		return new ModelAndView(view, model);
	}
	
	
	@RequestMapping(value="/listTransactionType.do", method=RequestMethod.POST)
	public ModelAndView listTransactionTypeDelete(@ModelAttribute("dataForm")TransactionTypeForm dataForm, 
			ServletWebRequest request) {


		for (Long id : dataForm.getKeys())
		{
			ydkTransactionMngrService.deleteTransactionType(id);
		}
		
		return new ModelAndView("redirect:listTransactionType.do", "operationMsg", "删除成功");
	}
	
	@RequestMapping(value="/modifyTransactionType.do", method=RequestMethod.POST)
	public ModelAndView modifyTransactionTypePost(@ModelAttribute("dataForm")TransactionTypeForm dataForm, 
			BindingResult result, ServletWebRequest request) {


		//Check the validation of ydkAccountForm and then store ydkAccountForm;
		if (dataForm.getInstance() == null 
				|| dataForm.getInstance().getId() == null 
				|| dataForm.getInstance().getType() == null
				|| dataForm.getInstance().getType().isEmpty())
		{
			result.rejectValue("instance.type", "required", "不能为空");
			return new ModelAndView("redirect:listTransactionType.do", "operationMsg", "修改失败");
		}

		WebServiceTransactionType instance = new WebServiceTransactionType(dataForm.getInstance());
		ydkTransactionMngrService.updateTransactionType(instance);
		
		
		return new ModelAndView("redirect:listTransactionType.do", "operationMsg", "修改成功");
	}
	
	
	
	/**
	 * 
	 * 
	 * 
	 * For Partner Controller
	 * 
	 * 
	 */
	
	@RequestMapping(value="/createTransaction.do", method=RequestMethod.GET)
	public ModelAndView createTransactionGet(ServletWebRequest request) {
		
		String view ="transaction_create";
		
		WebServiceListParams params = new WebServiceListParams();
		params.setPageSize(WebServiceListParams.MAX_PAGESIZE);
		params.setPageOffset(0);
		WebTransactionTypeListResult resultList = ydkTransactionMngrService.getAllTransactionTypes(params);
		
		if (resultList == null || !resultList.getOs().getIsSuccessful())
		{
			return new ModelAndView("errorPage", "errorMsg","无法获得交易类型");
		}
		if (resultList.getList().size() == 0)
		{
			return new ModelAndView("errorPage", "errorMsg","无法获得交易类型");
		}
		
		
		
		TransactionForm dataForm = new TransactionForm();

		
		Map model = new HashMap();
		
		model.put("dataForm", dataForm);
		model.put("typeList", resultList.getList());
		
		return new ModelAndView(view, model);
	}
	
	
	@RequestMapping(value="/createTransaction.do", method=RequestMethod.POST)
	public ModelAndView createTransactionPost(
			@ModelAttribute("dataForm")TransactionForm dataForm,
			BindingResult result,
			@ModelAttribute("typeList")List<TransactionType> typeList, 
			ModelMap model, ServletWebRequest request) {
		
		String view ="transaction_create";
		
		TransactionValidator.validate(dataForm, result);

		if (result.hasErrors()) {
			return new ModelAndView(view);
		}
		
		WebServiceTransaction webInstance = new WebServiceTransaction();
		
		//Now we need to check the Unique constrains of YdkAccount.
		WebServiceReader reader = this.ydkPartnerMngrService.getReaderByName(
				dataForm.getReaderName());
		if (reader == null || !reader.getOs().getIsSuccessful()
				|| reader.getReader() == null)
		{
			result.rejectValue("readerName", "required", "不存在");
			return new ModelAndView(view);
		}
		
		//Now we need to check the Unique constrains of YdkAccount.
		WebServiceBook book = this.ydkBookMngrService.getBookByYdkId(
				dataForm.getYdkBookId());
		if (book == null || !book.getOs().getIsSuccessful()
				|| book.getBook() == null)
		{
			result.rejectValue("ydkBookId", "required", "不存在");
			return new ModelAndView(view);
		}
		
		//Input the infor
		Transaction instance = dataForm.getInstance();
		instance.setBook(book.getBook());
		instance.setReader(reader.getReader());
		instance.setPartner(reader.getReader().getPartner());
		
		
		
		for (TransactionType type : typeList)
		{
			if (type.getId().equals(dataForm.getInstance().getTransactionType().getId()))
			{
				dataForm.getInstance().setTransactionType(type);
			}
		}
		//
		instance.setDate(new Date());
		
		//OK, now it's time to store the Transaction;
		webInstance.setTransaction(instance);
		webInstance.setOs(null);
		OperationStatus os = ydkTransactionMngrService.storeTransaction(webInstance);
		
		//Clear the sessioin;
		request.getRequest().getSession().removeAttribute("typeList");
		
		if (!os.getIsSuccessful())
		{
			return new ModelAndView("redirect:listTransaction.do",
					"operationMsg", "创建失败");
		}
		else
		{
			return new ModelAndView("redirect:listTransaction.do", 
					"operationMsg", "创建成功");
		}
		
	}
	
	
	@RequestMapping(value="/listTransaction.do", method=RequestMethod.GET)
	public ModelAndView listTransactionGet(ServletWebRequest request) {

		String view ="transaction_list";

		String page = request.getParameter("page");
		String pageNumber = request.getParameter("pageNumber");
		PagedListHolder pageList = null;
		if (page != null || pageNumber != null)
		{
			pageList = (PagedListHolder) request.getRequest().getSession().getAttribute("pageDataList");
		}
		else
		{
			WebServiceListParams params = new WebServiceListParams();
			params.setPageSize(WebServiceListParams.MAX_PAGESIZE);
			params.setPageOffset(0);
			WebTransactionListResult resultList = ydkTransactionMngrService.getAllTransactions(params);
			
			if (resultList == null || !resultList.getOs().getIsSuccessful())
			{
				return new ModelAndView("errorPage", "errorMsg","Could not get Transactions");
			}
		
			pageList = new PagedListHolder(resultList.getList());
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
		String itemId = request.getParameter("itemId");
		Map model = new HashMap();
		TransactionForm dataForm = new TransactionForm();
		if (itemId != null && !itemId.isEmpty())
		{
			Long id = new Long(itemId);
			//user try to modify a record;
			WebServiceTransaction webInstance = ydkTransactionMngrService.getTransactionById(id);
			if (webInstance.getOs().getIsSuccessful())
			{
				dataForm.setInstance(webInstance.getTransaction());
			}
			
			WebServiceListParams params = new WebServiceListParams();
			params.setPageSize(WebServiceListParams.MAX_PAGESIZE);
			params.setPageOffset(0);
			WebTransactionTypeListResult resultList = ydkTransactionMngrService.getAllTransactionTypes(params);
			
			if (resultList == null || !resultList.getOs().getIsSuccessful())
			{
				return new ModelAndView("errorPage", "errorMsg","无法获得合作伙伴类型");
			}
			if (resultList.getList().size() == 0)
			{
				return new ModelAndView("errorPage", "errorMsg","无法获得合作伙伴类型");
			}
			model.put("typeList", resultList.getList());
			request.getRequest().getSession().setAttribute("dataFormSession", dataForm);
		}
		model.put("dataForm", dataForm);
		
		String operationMsg = request.getParameter("operationMsg");
		if (operationMsg != null && !operationMsg.isEmpty())
		{
			model.put("operationMsg", operationMsg);
		}
		
		
		request.getRequest().getSession().setAttribute("pageDataList", pageList);
		
		return new ModelAndView(view, model);
	}
	
	
	@RequestMapping(value="/listTransaction.do", method=RequestMethod.POST)
	public ModelAndView listTransactionDelete(@ModelAttribute("dataForm")TransactionForm dataForm, 
			ServletWebRequest request) {


		for (Long id : dataForm.getKeys())
		{
			ydkTransactionMngrService.deleteTransaction(id);
		}
		
		return new ModelAndView("redirect:listTransaction.do", "operationMsg", "删除成功");
	}
	
	@RequestMapping(value="/modifyTransaction.do", method=RequestMethod.POST)
	public ModelAndView modifyTransactionPost(@ModelAttribute("dataForm")TransactionForm dataForm, 
			BindingResult result, ServletWebRequest request) {


		TransactionValidator.validate(dataForm, result);
		
		TransactionForm dataFormSession = (TransactionForm)request.getRequest().getSession().getAttribute("dataFormSession");

		
		if (result.hasErrors() || dataFormSession == null) {
			return new ModelAndView("redirect:listTransaction.do", "operationMsg", "修改失败，请检查修改的值");
		}
		dataForm.prepareForModify(dataFormSession.getInstance());

		WebServiceTransaction instance = new WebServiceTransaction(dataFormSession.getInstance());
		ydkTransactionMngrService.updateTransaction(instance);
		
		
		return new ModelAndView("redirect:listTransaction.do");
	}
	
	
	

	

}
