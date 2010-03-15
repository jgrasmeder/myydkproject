package com.ydk.account.web;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
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
import com.ydk.account.validator.BookNewsCategoryValidator;
import com.ydk.account.validator.BookNewsValidator;
import com.ydk.account.validator.BookReviewCategoryValidator;
import com.ydk.account.validator.BookReviewValidator;
import com.ydk.account.validator.CategoryValidator;
import com.ydk.account.validator.EventCategoryValidator;
import com.ydk.account.validator.EventValidator;
import com.ydk.account.validator.InterviewCategoryValidator;
import com.ydk.account.validator.InterviewValidator;
import com.ydk.account.validator.LibraryValidator;
import com.ydk.account.validator.PartnerTypeValidator;
import com.ydk.account.validator.PartnerValidator;
import com.ydk.account.validator.YdkAccountValidator;
import com.ydk.account.webservice.WebPartnerListResult;
import com.ydk.account.webservice.WebPartnerTypeListResult;
import com.ydk.account.webservice.WebServicePartner;
import com.ydk.account.webservice.WebServicePartnerType;
import com.ydk.account.webservice.WebServiceYdkAccount;
import com.ydk.account.webservice.WebYdkAccountListResult;
import com.ydk.account.webservice.WebYdkRoleListResult;
import com.ydk.account.webservice.YdkAccountMngrService;
import com.ydk.account.webservice.YdkAccountService;
import com.ydk.account.webservice.YdkBookMngrService;
import com.ydk.account.webservice.YdkBusiQueryService;
import com.ydk.account.webservice.YdkPartnerMngrService;
import com.ydk.account.webservice.YdkSubjectMngrService;
import com.ydk.book.persistence.entity.BookNews;
import com.ydk.book.persistence.entity.BookNewsCategory;
import com.ydk.book.persistence.entity.BookReview;
import com.ydk.book.persistence.entity.BookReviewCategory;
import com.ydk.book.persistence.entity.Event;
import com.ydk.book.persistence.entity.EventCategory;
import com.ydk.book.persistence.entity.Interview;
import com.ydk.book.persistence.entity.InterviewCategory;
import com.ydk.book.webservice.OperationStatus;
import com.ydk.book.webservice.WebBookCateListResult;
import com.ydk.book.webservice.WebBookNewsCateListResult;
import com.ydk.book.webservice.WebBookNewsListResult;
import com.ydk.book.webservice.WebBookReviewCateListResult;
import com.ydk.book.webservice.WebBookReviewListResult;
import com.ydk.book.webservice.WebEventCateListResult;
import com.ydk.book.webservice.WebEventListResult;
import com.ydk.book.webservice.WebInterviewCateListResult;
import com.ydk.book.webservice.WebInterviewListResult;
import com.ydk.book.webservice.WebLibraryListResult;
import com.ydk.book.webservice.WebServiceBookNews;
import com.ydk.book.webservice.WebServiceBookNewsCategory;
import com.ydk.book.webservice.WebServiceBookReview;
import com.ydk.book.webservice.WebServiceBookReviewCategory;
import com.ydk.book.webservice.WebServiceCategory;
import com.ydk.book.webservice.WebServiceEvent;
import com.ydk.book.webservice.WebServiceEventCategory;
import com.ydk.book.webservice.WebServiceFile;
import com.ydk.book.webservice.WebServiceInterview;
import com.ydk.book.webservice.WebServiceInterviewCategory;
import com.ydk.book.webservice.WebServiceLibrary;
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
public class MngrRersoucesController extends WebControllerBase {

	@Autowired
	@Qualifier("ydkAccountMngrServiceClient")
    private YdkAccountMngrService ydkAccountMngrServiceClient;
	
	public void setYdkAccountMngrServiceClient(YdkAccountMngrService ydkAccountMngrServiceClient) {
        this.ydkAccountMngrServiceClient = ydkAccountMngrServiceClient;
    }
	
	@Autowired
	@Qualifier("ydkBookFreeServiceClient")
    protected YdkBookFreeService ydkBookFreeService;
	
	public void setYdkBookFreeService(@Qualifier("ydkBookFreeServiceClient")YdkBookFreeService service) {
        this.ydkBookFreeService = service;
    }
	
	@Autowired
	@Qualifier("ydkSubjectServiceClient")
    protected YdkSubjectService ydkSubjectService;
	
	public void setYdkSubjectService(@Qualifier("ydkSubjectServiceClient")YdkSubjectService service) {
        this.ydkSubjectService = service;
    }
	
	@Autowired
	@Qualifier("ydkFrontEndServiceClient")
    protected YdkFrontEndWebService ydkFrontEndWebService;
	
	public void setYdkFrontEndWebService(@Qualifier("ydkFrontEndServiceClient")YdkFrontEndWebService service) {
        this.ydkFrontEndWebService = service;
    }
	
	@Autowired
	@Qualifier("ydkSubjectMngrServiceClient")
    protected YdkSubjectMngrService ydkSubjectMngrService;
	
	public void setYdkSubjectMngrService(@Qualifier("ydkSubjectMngrServiceClient")YdkSubjectMngrService service) {
        this.ydkSubjectMngrService = service;
    }
	
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
	
	@Autowired
	@Qualifier("ydkBusiQueryServiceClient")
    protected YdkBusiQueryService ydkBusiQueryService;
	
	public void setYdkBusiQueryService(@Qualifier("ydkBusiQueryServiceClient")YdkBusiQueryService service) {
        this.ydkBusiQueryService = service;
    }

	public MngrRersoucesController() {
		this.thisView = "mainframe";
		this.fromView = null;
		this.errorView = "mainframe";
		this.successView = "mainframe";
		this.successViewRedirect = successView + ".do";
		this.homeView = "login.do";
	}

	
	
	
	
	/**
	 * 
	 * 
	 * 
	 * For Controller
	 * 
	 * 
	 */
	@RequestMapping(value="/createBookNewsCategory.do", method=RequestMethod.GET)
	public ModelAndView createBookNewsCategoryGet(ServletWebRequest request) {
		
		String view ="booknews_cate_create";
		
		WebServiceListParams params = new WebServiceListParams();
		params.setPageSize(WebServiceListParams.MAX_PAGESIZE);
		params.setPageOffset(0);

		BookNewsCategoryForm dataForm = new BookNewsCategoryForm();

		
		Map model = new HashMap();
		
		model.put("dataForm", dataForm);
		
		return new ModelAndView(view, model);
	}
	
	@RequestMapping(value="/createBookNewsCategory.do", method=RequestMethod.POST)
	public ModelAndView createBookNewsCategoryPost(
			@ModelAttribute("dataForm")BookNewsCategoryForm dataForm,
			BindingResult result, ModelMap model, ServletWebRequest request) {
		
		String view ="booknews_cate_create";
		
		BookNewsCategoryValidator.validate(dataForm, result);

		if (result.hasErrors()) {
			return new ModelAndView(view);
		}
		
		//Now we need to check the Unique constrains of YdkAccount.
		WebServiceBookNewsCategory webInstance = ydkSubjectMngrService.getBookNewsCategoryByName(
				dataForm.getInstance().getName());
		if (webInstance.getBookNewsCategory() != null)
		{
			result.rejectValue("instance.name", "duplicate", "已经存在");
			return new ModelAndView(view);
		}
		
		//Input the infor
		BookNewsCategory instance = dataForm.getInstance();
		
		
		//OK, now it's time to store the BookNewsCategory;
		webInstance.setBookNewsCategory(instance);
		webInstance.setOs(null);
		OperationStatus os = ydkSubjectMngrService.storeBookNewsCategory(webInstance);
		
		//Clear the sessioin;
//		request.getRequest().getSession().removeAttribute("typeList");
		
		if (!os.getIsSuccessful())
		{
			return new ModelAndView("redirect:listBookNewsCategory.do",
					"operationMsg", "创建失败");
		}
		else
		{
			return new ModelAndView("redirect:listBookNewsCategory.do", 
					"operationMsg", "创建成功");
		}
		
	}
	
	
	@RequestMapping(value="/listBookNewsCategory.do", method=RequestMethod.GET)
	public ModelAndView listBookNewsCategoryGet(ServletWebRequest request) {

		String view ="booknews_cate_list";

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
			WebBookNewsCateListResult resultList = ydkSubjectMngrService.getBookNewsCategories(params);
			
			if (resultList == null || !resultList.getOs().getIsSuccessful())
			{
				return new ModelAndView("errorPage", "errorMsg","Could not get BookNewsCategorys");
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
		BookNewsCategoryForm dataForm = new BookNewsCategoryForm();
		if (itemId != null && !itemId.isEmpty())
		{
			Long id = new Long(itemId);
			//user try to modify a record;
			WebServiceBookNewsCategory webInstance = ydkSubjectMngrService.getBookNewsCategoryById(id);
			if (webInstance.getOs().getIsSuccessful())
			{
				dataForm.setInstance(webInstance.getBookNewsCategory());
			}
			
			//Get the TypeList of Instance
//			WebServiceListParams params = new WebServiceListParams();
//			params.setPageSize(WebServiceListParams.MAX_PAGESIZE);
//			params.setPageOffset(0);
//			WebBookNewsCategoryTypeListResult resultList = ydkSubjectMngrService.getAllBookNewsCategoryTypes(params);
//			
//			if (resultList == null || !resultList.getOs().getIsSuccessful())
//			{
//				return new ModelAndView("errorPage", "errorMsg","无法获得合作伙伴类型");
//			}
//			if (resultList.getList().size() == 0)
//			{
//				return new ModelAndView("errorPage", "errorMsg","无法获得合作伙伴类型");
//			}
//			model.put("typeList", resultList.getList());
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
	
	
	@RequestMapping(value="/listBookNewsCategory.do", method=RequestMethod.POST)
	public ModelAndView listBookNewsCategoryDelete(@ModelAttribute("dataForm")BookNewsCategoryForm dataForm, 
			ServletWebRequest request) {


		for (Long id : dataForm.getKeys())
		{
			ydkSubjectMngrService.deleteBookNewsCategory(id);
		}
		
		return new ModelAndView("redirect:listBookNewsCategory.do", "operationMsg", "删除成功");
	}
	
	@RequestMapping(value="/modifyBookNewsCategory.do", method=RequestMethod.POST)
	public ModelAndView modifyBookNewsCategoryPost(@ModelAttribute("dataForm")BookNewsCategoryForm dataForm, 
			BindingResult result, ServletWebRequest request) {


		BookNewsCategoryValidator.validate(dataForm, result);
		
		BookNewsCategoryForm dataFormSession = (BookNewsCategoryForm)request.getRequest().getSession().getAttribute("dataFormSession");

		
		if (result.hasErrors() || dataFormSession == null) {
			return new ModelAndView("redirect:listBookNewsCategory.do", "operationMsg", "修改失败，请检查修改的值");
		}
		dataForm.prepareForModify(dataFormSession.getInstance());

		WebServiceBookNewsCategory instance = new WebServiceBookNewsCategory(dataFormSession.getInstance());
		ydkSubjectMngrService.updateBookNewsCategory(instance);
		
		return new ModelAndView("redirect:listBookNewsCategory.do");
	}
	
	
	
	/**
	 * 
	 * 
	 * 
	 * For Controller
	 * 
	 * 
	 */
	@RequestMapping(value="/createBookReviewCategory.do", method=RequestMethod.GET)
	public ModelAndView createBookReviewCategoryGet(ServletWebRequest request) {
		
		String view ="bookreview_cate_create";
		
		WebServiceListParams params = new WebServiceListParams();
		params.setPageSize(WebServiceListParams.MAX_PAGESIZE);
		params.setPageOffset(0);

		BookReviewCategoryForm dataForm = new BookReviewCategoryForm();

		
		Map model = new HashMap();
		
		model.put("dataForm", dataForm);
		
		return new ModelAndView(view, model);
	}
	
	@RequestMapping(value="/createBookReviewCategory.do", method=RequestMethod.POST)
	public ModelAndView createBookReviewCategoryPost(
			@ModelAttribute("dataForm")BookReviewCategoryForm dataForm,
			BindingResult result, ModelMap model, ServletWebRequest request) {
		
		String view ="bookreview_cate_create";
		
		BookReviewCategoryValidator.validate(dataForm, result);

		if (result.hasErrors()) {
			return new ModelAndView(view);
		}
		
		//Now we need to check the Unique constrains of YdkAccount.
		WebServiceBookReviewCategory webInstance = ydkSubjectMngrService.getBookReviewCategoryByName(
				dataForm.getInstance().getName());
		if (webInstance.getBookReviewCategory() != null)
		{
			result.rejectValue("instance.name", "duplicate", "已经存在");
			return new ModelAndView(view);
		}
		
		//Input the infor
		BookReviewCategory instance = dataForm.getInstance();
		
		
		//OK, now it's time to store the BookReviewCategory;
		webInstance.setBookReviewCategory(instance);
		webInstance.setOs(null);
		OperationStatus os = ydkSubjectMngrService.storeBookReviewCategory(webInstance);
		
		//Clear the sessioin;
//		request.getRequest().getSession().removeAttribute("typeList");
		
		if (!os.getIsSuccessful())
		{
			return new ModelAndView("redirect:listBookReviewCategory.do",
					"operationMsg", "创建失败");
		}
		else
		{
			return new ModelAndView("redirect:listBookReviewCategory.do", 
					"operationMsg", "创建成功");
		}
		
	}
	
	
	@RequestMapping(value="/listBookReviewCategory.do", method=RequestMethod.GET)
	public ModelAndView listBookReviewCategoryGet(ServletWebRequest request) {

		String view ="bookreview_cate_list";

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
			WebBookReviewCateListResult resultList = ydkSubjectMngrService.getBookReviewCategories(params);
			
			if (resultList == null || !resultList.getOs().getIsSuccessful())
			{
				return new ModelAndView("errorPage", "errorMsg","Could not get BookReviewCategorys");
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
		BookReviewCategoryForm dataForm = new BookReviewCategoryForm();
		if (itemId != null && !itemId.isEmpty())
		{
			Long id = new Long(itemId);
			//user try to modify a record;
			WebServiceBookReviewCategory webInstance = ydkSubjectMngrService.getBookReviewCategoryById(id);
			if (webInstance.getOs().getIsSuccessful())
			{
				dataForm.setInstance(webInstance.getBookReviewCategory());
			}
			
			
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
	
	@RequestMapping(value="/listBookReviewCategory.do", method=RequestMethod.POST)
	public ModelAndView listBookReviewCategoryDelete(@ModelAttribute("dataForm")BookReviewCategoryForm dataForm, 
			ServletWebRequest request) {


		for (Long id : dataForm.getKeys())
		{
			ydkSubjectMngrService.deleteBookReviewCategory(id);
		}
		
		return new ModelAndView("redirect:listBookReviewCategory.do", "operationMsg", "删除成功");
	}
	
	@RequestMapping(value="/modifyBookReviewCategory.do", method=RequestMethod.POST)
	public ModelAndView modifyBookReviewCategoryPost(@ModelAttribute("dataForm")BookReviewCategoryForm dataForm, 
			BindingResult result, ServletWebRequest request) {


		BookReviewCategoryValidator.validate(dataForm, result);
		
		BookReviewCategoryForm dataFormSession = (BookReviewCategoryForm)request.getRequest().getSession().getAttribute("dataFormSession");

		
		if (result.hasErrors() || dataFormSession == null) {
			return new ModelAndView("redirect:listBookReviewCategory.do", "operationMsg", "修改失败，请检查修改的值");
		}
		dataForm.prepareForModify(dataFormSession.getInstance());

		WebServiceBookReviewCategory instance = new WebServiceBookReviewCategory(dataFormSession.getInstance());
		ydkSubjectMngrService.updateBookReviewCategory(instance);
		
		return new ModelAndView("redirect:listBookReviewCategory.do");
	}
	
	
	/**
	 * 
	 * 
	 * 
	 * For Controller
	 * 
	 * 
	 */
	@RequestMapping(value="/createEventCategory.do", method=RequestMethod.GET)
	public ModelAndView createEventCategoryGet(ServletWebRequest request) {
		
		String view ="event_cate_create";
		
		WebServiceListParams params = new WebServiceListParams();
		params.setPageSize(WebServiceListParams.MAX_PAGESIZE);
		params.setPageOffset(0);

		EventCategoryForm dataForm = new EventCategoryForm();

		
		Map model = new HashMap();
		
		model.put("dataForm", dataForm);
		
		return new ModelAndView(view, model);
	}
	
	@RequestMapping(value="/createEventCategory.do", method=RequestMethod.POST)
	public ModelAndView createEventCategoryPost(
			@ModelAttribute("dataForm")EventCategoryForm dataForm,
			BindingResult result, ModelMap model, ServletWebRequest request) {
		
		String view ="event_cate_create";
		
		EventCategoryValidator.validate(dataForm, result);

		if (result.hasErrors()) {
			return new ModelAndView(view);
		}
		
		//Now we need to check the Unique constrains of YdkAccount.
		WebServiceEventCategory webInstance = ydkSubjectMngrService.getEventCategoryByName(
				dataForm.getInstance().getName());
		if (webInstance.getEventCategory() != null)
		{
			result.rejectValue("instance.name", "duplicate", "已经存在");
			return new ModelAndView(view);
		}
		
		//Input the infor
		EventCategory instance = dataForm.getInstance();
		
		
		//OK, now it's time to store the EventCategory;
		webInstance.setEventCategory(instance);
		webInstance.setOs(null);
		OperationStatus os = ydkSubjectMngrService.storeEventCategory(webInstance);
		
		//Clear the sessioin;
//		request.getRequest().getSession().removeAttribute("typeList");
		
		if (!os.getIsSuccessful())
		{
			return new ModelAndView("redirect:listEventCategory.do",
					"operationMsg", "创建失败");
		}
		else
		{
			return new ModelAndView("redirect:listEventCategory.do", 
					"operationMsg", "创建成功");
		}
		
	}
	
	
	@RequestMapping(value="/listEventCategory.do", method=RequestMethod.GET)
	public ModelAndView listEventCategoryGet(ServletWebRequest request) {

		String view ="event_cate_list";

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
			WebEventCateListResult resultList = ydkSubjectMngrService.getEventCategories(params);
			
			if (resultList == null || !resultList.getOs().getIsSuccessful())
			{
				return new ModelAndView("errorPage", "errorMsg","Could not get EventCategorys");
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
		EventCategoryForm dataForm = new EventCategoryForm();
		if (itemId != null && !itemId.isEmpty())
		{
			Long id = new Long(itemId);
			//user try to modify a record;
			WebServiceEventCategory webInstance = ydkSubjectMngrService.getEventCategoryById(id);
			if (webInstance.getOs().getIsSuccessful())
			{
				dataForm.setInstance(webInstance.getEventCategory());
			}
			
			
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
	
	@RequestMapping(value="/listEventCategory.do", method=RequestMethod.POST)
	public ModelAndView listEventCategoryDelete(@ModelAttribute("dataForm")EventCategoryForm dataForm, 
			ServletWebRequest request) {


		for (Long id : dataForm.getKeys())
		{
			ydkSubjectMngrService.deleteEventCategory(id);
		}
		
		return new ModelAndView("redirect:listEventCategory.do", "operationMsg", "删除成功");
	}
	
	@RequestMapping(value="/modifyEventCategory.do", method=RequestMethod.POST)
	public ModelAndView modifyEventCategoryPost(@ModelAttribute("dataForm")EventCategoryForm dataForm, 
			BindingResult result, ServletWebRequest request) {


		EventCategoryValidator.validate(dataForm, result);
		
		EventCategoryForm dataFormSession = (EventCategoryForm)request.getRequest().getSession().getAttribute("dataFormSession");

		
		if (result.hasErrors() || dataFormSession == null) {
			return new ModelAndView("redirect:listEventCategory.do", "operationMsg", "修改失败，请检查修改的值");
		}
		dataForm.prepareForModify(dataFormSession.getInstance());

		WebServiceEventCategory instance = new WebServiceEventCategory(dataFormSession.getInstance());
		ydkSubjectMngrService.updateEventCategory(instance);
		
		return new ModelAndView("redirect:listEventCategory.do");
	}
	
	
	/**
	 * 
	 * 
	 * 
	 * For Controller
	 * 
	 * 
	 */
	@RequestMapping(value="/createInterviewCategory.do", method=RequestMethod.GET)
	public ModelAndView createInterviewCategoryGet(ServletWebRequest request) {
		
		String view ="interview_cate_create";
		
		WebServiceListParams params = new WebServiceListParams();
		params.setPageSize(WebServiceListParams.MAX_PAGESIZE);
		params.setPageOffset(0);

		InterviewCategoryForm dataForm = new InterviewCategoryForm();

		
		Map model = new HashMap();
		
		model.put("dataForm", dataForm);
		
		return new ModelAndView(view, model);
	}
	
	@RequestMapping(value="/createInterviewCategory.do", method=RequestMethod.POST)
	public ModelAndView createInterviewCategoryPost(
			@ModelAttribute("dataForm")InterviewCategoryForm dataForm,
			BindingResult result, ModelMap model, ServletWebRequest request) {
		
		String view ="interview_cate_create";
		
		InterviewCategoryValidator.validate(dataForm, result);

		if (result.hasErrors()) {
			return new ModelAndView(view);
		}
		
		//Now we need to check the Unique constrains of YdkAccount.
		WebServiceInterviewCategory webInstance = ydkSubjectMngrService.getInterviewCategoryByName(
				dataForm.getInstance().getName());
		if (webInstance.getInterviewCategory() != null)
		{
			result.rejectValue("instance.name", "duplicate", "已经存在");
			return new ModelAndView(view);
		}
		
		//Input the infor
		InterviewCategory instance = dataForm.getInstance();
		
		
		//OK, now it's time to store the InterviewCategory;
		webInstance.setInterviewCategory(instance);
		webInstance.setOs(null);
		OperationStatus os = ydkSubjectMngrService.storeInterviewCategory(webInstance);
		
		//Clear the sessioin;
//		request.getRequest().getSession().removeAttribute("typeList");
		
		if (!os.getIsSuccessful())
		{
			return new ModelAndView("redirect:listInterviewCategory.do",
					"operationMsg", "创建失败");
		}
		else
		{
			return new ModelAndView("redirect:listInterviewCategory.do", 
					"operationMsg", "创建成功");
		}
		
	}
	
	
	@RequestMapping(value="/listInterviewCategory.do", method=RequestMethod.GET)
	public ModelAndView listInterviewCategoryGet(ServletWebRequest request) {

		String view ="interview_cate_list";

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
			WebInterviewCateListResult resultList = ydkSubjectMngrService.getInterviewCategories(params);
			
			if (resultList == null || !resultList.getOs().getIsSuccessful())
			{
				return new ModelAndView("errorPage", "errorMsg","Could not get InterviewCategorys");
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
		InterviewCategoryForm dataForm = new InterviewCategoryForm();
		if (itemId != null && !itemId.isEmpty())
		{
			Long id = new Long(itemId);
			//user try to modify a record;
			WebServiceInterviewCategory webInstance = ydkSubjectMngrService.getInterviewCategoryById(id);
			if (webInstance.getOs().getIsSuccessful())
			{
				dataForm.setInstance(webInstance.getInterviewCategory());
			}
			
			
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
	
	@RequestMapping(value="/listInterviewCategory.do", method=RequestMethod.POST)
	public ModelAndView listInterviewCategoryDelete(@ModelAttribute("dataForm")InterviewCategoryForm dataForm, 
			ServletWebRequest request) {


		for (Long id : dataForm.getKeys())
		{
			ydkSubjectMngrService.deleteInterviewCategory(id);
		}
		
		return new ModelAndView("redirect:listInterviewCategory.do", "operationMsg", "删除成功");
	}
	
	@RequestMapping(value="/modifyInterviewCategory.do", method=RequestMethod.POST)
	public ModelAndView modifyInterviewCategoryPost(@ModelAttribute("dataForm")InterviewCategoryForm dataForm, 
			BindingResult result, ServletWebRequest request) {


		InterviewCategoryValidator.validate(dataForm, result);
		
		InterviewCategoryForm dataFormSession = (InterviewCategoryForm)request.getRequest().getSession().getAttribute("dataFormSession");

		
		if (result.hasErrors() || dataFormSession == null) {
			return new ModelAndView("redirect:listInterviewCategory.do", "operationMsg", "修改失败，请检查修改的值");
		}
		dataForm.prepareForModify(dataFormSession.getInstance());

		WebServiceInterviewCategory instance = new WebServiceInterviewCategory(dataFormSession.getInstance());
		ydkSubjectMngrService.updateInterviewCategory(instance);
		
		return new ModelAndView("redirect:listInterviewCategory.do");
	}
	
	
	
	/**
	 * 
	 * 
	 * 
	 * For Interview Controller
	 * 
	 * 
	 */
	
	@RequestMapping(value="/viewInterview.do", method=RequestMethod.GET)
	public ModelAndView viewInterviewGet(ServletWebRequest request) {
		
		String view ="interview_view";
		
		String sId = request.getParameter("itemId");
		if (sId == null || sId.isEmpty())
		{
			return new ModelAndView(view, "operationMsg", "无效的Id");
		}
		Long id = new Long((sId));
		InterviewForm dataForm = null;
		//user try to modify a record;
		WebServiceInterview webInstance = ydkSubjectMngrService.getInterviewById(id);
		if (webInstance.getOs().getIsSuccessful())
		{
			dataForm = new InterviewForm((webInstance.getInterview()));
		}
		
		Map model = new HashMap();
		model.put("dataForm", dataForm);
		//Get interview Cotent 
		WebServiceFile result = ydkFrontEndWebService.getFileByName(dataForm.getInstance().getContent());

		if (result != null && result.getOs().getIsSuccessful())
		{
			try {
				model.put("fileContent", new String(result.getFile().getFileContent(),"utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

//		request.getRequest().getSession().setAttribute("typeList", resultList.getList())
//		model.put("typeList", resultList.getList());
		
		return new ModelAndView(view, model);
	}
	
	
	
	@RequestMapping(value="/createInterview.do", method=RequestMethod.GET)
	public ModelAndView createInterviewGet(ServletWebRequest request) {
		
		String view ="interview_create";
		
		WebServiceListParams params = new WebServiceListParams();
		params.setPageSize(WebServiceListParams.MAX_PAGESIZE);
		params.setPageOffset(0);
		WebInterviewCateListResult resultList = ydkSubjectMngrService.getInterviewCategories(params);
		
		if (resultList == null || !resultList.getOs().getIsSuccessful())
		{
			return new ModelAndView("errorPage", "errorMsg","无法获得访谈类型");
		}
		if (resultList.getList().size() == 0)
		{
			return new ModelAndView("errorPage", "errorMsg","无法获得访谈类型");
		}
		
		
		InterviewForm dataForm = new InterviewForm();

		
		Map model = new HashMap();
		
		model.put("dataForm", dataForm);
//		request.getRequest().getSession().setAttribute("typeList", resultList.getList())
		model.put("typeList", resultList.getList());
		
		return new ModelAndView(view, model);
	}
	
	
	
	
	@RequestMapping(value="/createInterview.do", method=RequestMethod.POST)
	public ModelAndView createInterviewPost(
			@ModelAttribute("dataForm")InterviewForm dataForm,
			BindingResult result,
			@ModelAttribute("typeList")List<InterviewCategory> typeList, 
			ModelMap model, ServletWebRequest request) {
		
		String view ="interview_create";
		InterviewValidator.validate(dataForm, result);

		if (result.hasErrors()) {
			return new ModelAndView(view);
		}
		
		WebServiceInterview webInstance = new WebServiceInterview();
		
		//Check uploaded files
		//1. all the images could be null
		//2. all the content should not be null
		
		//content file
		MultipartHttpServletRequest multipartRequest =
			(MultipartHttpServletRequest) request.getRequest();
		MultipartFile contentFile = multipartRequest.getFile("contentFile");
		String checkResult = FileUploadBean.validateFile(contentFile);
		if (checkResult != null)
		{
			return new ModelAndView(view, "operationMsg", checkResult);
		}
		
		try {
			webInstance.setContentFile(FileUploadBean.getUploadedFile(contentFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView(view, "operationMsg", "上传文件失败");
		}
		
		
		
		//title Image
		MultipartFile titleImage = multipartRequest.getFile("titleImage");
		if (titleImage != null && !titleImage.isEmpty())
		{
			checkResult = FileUploadBean.validateFile(titleImage);
			if (checkResult != null)
			{
				return new ModelAndView(view, "operationMsg", checkResult);
			}
			try {
				webInstance.setTitleImage(FileUploadBean.getUploadedFile(titleImage));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ModelAndView(view, "operationMsg", "上传文件失败");
			}
		}
		
		
		//content Images count
		String contentImageCount = request.getParameter("fileCount");
		Integer cImageCount = 0;
		if (contentImageCount != null && !contentImageCount.isEmpty())
		{
			cImageCount = new Integer(contentImageCount);
		}
		
		//content Images
		for (int i=0; i < cImageCount; i ++)
		{
			MultipartFile contentImage = multipartRequest.getFile("contentImage" + (i + 1));
			if (contentImage != null && !contentImage.isEmpty())
			{
				checkResult = FileUploadBean.validateFile(contentImage);
				if (checkResult != null)
				{
					return new ModelAndView(view, "operationMsg", checkResult);
					
				}
				try {
					webInstance.getContentImages().add(FileUploadBean.getUploadedFile(contentImage));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return new ModelAndView(view, "operationMsg", "上传文件失败");
				}
			}
			
		}
		
		
		//Now we need to check the Unique constrains of YdkAccount.
		WebServiceInterview webInstanceCheck = ydkSubjectMngrService.getInterviewByTitle(
				dataForm.getInstance().getTitle());
		if (webInstanceCheck.getInterview() != null)
		{
			result.rejectValue("instance.title", "duplicate", "已经存在");
			return new ModelAndView(view);
		}
		
		//Input the infor
		Interview instance = dataForm.getInstance();
		
		
		
		for (InterviewCategory type : typeList)
		{
			if (type.getId().equals(dataForm.getInstance().getInterviewCategory().getId()))
			{
				dataForm.getInstance().setInterviewCategory(type);
			}
		}
		
		//OK, now it's time to store the Interview;
		webInstance.setInterview(instance);
		webInstance.setOs(null);
		OperationStatus os = ydkSubjectMngrService.storeInterview(webInstance);
		
		//Clear the sessioin;
		request.getRequest().getSession().removeAttribute("typeList");
		
		if (!os.getIsSuccessful())
		{
			return new ModelAndView("redirect:listInterview.do",
					"operationMsg", "创建失败");
		}
		else
		{
			return new ModelAndView("redirect:listInterview.do", 
					"operationMsg", "创建成功");
		}
		
	}
	
	
	@RequestMapping(value="/listInterview.do", method=RequestMethod.GET)
	public ModelAndView listInterviewGet(ServletWebRequest request) {

		String view ="interview_list";

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
			WebInterviewListResult resultList = ydkSubjectMngrService.listInterviews(params);
			
			if (resultList == null || !resultList.getOs().getIsSuccessful())
			{
				return new ModelAndView("errorPage", "errorMsg","Could not get Interviews");
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
		InterviewForm dataForm = new InterviewForm();
		if (itemId != null && !itemId.isEmpty())
		{
			Long id = new Long(itemId);
			//user try to modify a record;
			WebServiceInterview webInstance = ydkSubjectMngrService.getInterviewById(id);
			if (webInstance.getOs().getIsSuccessful())
			{
				dataForm.setInstance(webInstance.getInterview());
			}
			
			WebServiceListParams params = new WebServiceListParams();
			params.setPageSize(WebServiceListParams.MAX_PAGESIZE);
			params.setPageOffset(0);
			WebInterviewCateListResult resultList = ydkSubjectMngrService.getInterviewCategories(params);
			
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
	
	
	@RequestMapping(value="/listInterview.do", method=RequestMethod.POST)
	public ModelAndView listInterviewDelete(@ModelAttribute("dataForm")InterviewForm dataForm, 
			ServletWebRequest request) {


		for (Long id : dataForm.getKeys())
		{
			ydkSubjectMngrService.deleteInterview(id);
		}
		
		return new ModelAndView("redirect:listInterview.do", "operationMsg", "删除成功");
	}
	
	@RequestMapping(value="/modifyInterview.do", method=RequestMethod.POST)
	public ModelAndView modifyInterviewPost(@ModelAttribute("dataForm")InterviewForm dataForm, 
			BindingResult result, ServletWebRequest request) {


		InterviewValidator.validate(dataForm, result);
		
		InterviewForm dataFormSession = (InterviewForm)request.getRequest().getSession().getAttribute("dataFormSession");

		
		if (result.hasErrors() || dataFormSession == null) {
			return new ModelAndView("redirect:listInterview.do", "operationMsg", "修改失败，请检查修改的值");
		}
		dataForm.prepareForModify(dataFormSession.getInstance());

		WebServiceInterview instance = new WebServiceInterview(dataFormSession.getInstance());
		ydkSubjectMngrService.updateInterview(instance);
		
		
		return new ModelAndView("redirect:listInterview.do");
	}
	
	
	
	/**
	 * 
	 * 
	 * 
	 * For Event Controller
	 * 
	 * 
	 */
	
	@RequestMapping(value="/viewEvent.do", method=RequestMethod.GET)
	public ModelAndView viewEventGet(ServletWebRequest request) {
		
		String view ="event_view";
		
		String sId = request.getParameter("itemId");
		if (sId == null || sId.isEmpty())
		{
			return new ModelAndView(view, "operationMsg", "无效的Id");
		}
		Long id = new Long((sId));
		EventForm dataForm = null;
		//user try to modify a record;
		WebServiceEvent webInstance = ydkSubjectMngrService.getEventById(id);
		if (webInstance.getOs().getIsSuccessful())
		{
			dataForm = new EventForm((webInstance.getEvent()));
		}
		
		Map model = new HashMap();
		model.put("dataForm", dataForm);
		//Get Event Cotent 
		WebServiceFile result = ydkFrontEndWebService.getFileByName(dataForm.getInstance().getContent());

		if (result != null && result.getOs().getIsSuccessful())
		{
			try {
				model.put("fileContent", new String(result.getFile().getFileContent(),"utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		
		return new ModelAndView(view, model);
	}
	
	
	
	@RequestMapping(value="/createEvent.do", method=RequestMethod.GET)
	public ModelAndView createEventGet(ServletWebRequest request) {
		
		String view ="event_create";
		
		WebServiceListParams params = new WebServiceListParams();
		params.setPageSize(WebServiceListParams.MAX_PAGESIZE);
		params.setPageOffset(0);
		WebEventCateListResult resultList = ydkSubjectMngrService.getEventCategories(params);
		
		if (resultList == null || !resultList.getOs().getIsSuccessful())
		{
			return new ModelAndView("errorPage", "errorMsg","无法获得访谈类型");
		}
		if (resultList.getList().size() == 0)
		{
			return new ModelAndView("errorPage", "errorMsg","无法获得访谈类型");
		}
		
		
		EventForm dataForm = new EventForm();

		
		Map model = new HashMap();
		
		model.put("dataForm", dataForm);
//		request.getRequest().getSession().setAttribute("typeList", resultList.getList())
		model.put("typeList", resultList.getList());
		
		return new ModelAndView(view, model);
	}
	
	
	
	
	@RequestMapping(value="/createEvent.do", method=RequestMethod.POST)
	public ModelAndView createEventPost(
			@ModelAttribute("dataForm")EventForm dataForm,
			BindingResult result,
			@ModelAttribute("typeList")List<EventCategory> typeList, 
			ModelMap model, ServletWebRequest request) {
		
		String view ="event_create";
		EventValidator.validate(dataForm, result);

		if (result.hasErrors()) {
			return new ModelAndView(view);
		}
		
		WebServiceEvent webInstance = new WebServiceEvent();
		
		//Check uploaded files
		//1. all the images could be null
		//2. all the content should not be null
		
		//content file
		MultipartHttpServletRequest multipartRequest =
			(MultipartHttpServletRequest) request.getRequest();
		MultipartFile contentFile = multipartRequest.getFile("contentFile");
		String checkResult = FileUploadBean.validateFile(contentFile);
		if (checkResult != null)
		{
			return new ModelAndView(view, "operationMsg", checkResult);
		}
		
		try {
			webInstance.setContentFile(FileUploadBean.getUploadedFile(contentFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView(view, "operationMsg", "上传文件失败");
		}
		
		
		
		//title Image
		MultipartFile titleImage = multipartRequest.getFile("titleImage");
		if (titleImage != null && !titleImage.isEmpty())
		{
			checkResult = FileUploadBean.validateFile(titleImage);
			if (checkResult != null)
			{
				return new ModelAndView(view, "operationMsg", checkResult);
			}
			try {
				webInstance.setTitleImage(FileUploadBean.getUploadedFile(titleImage));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ModelAndView(view, "operationMsg", "上传文件失败");
			}
		}
		
		
		//content Images count
		String contentImageCount = request.getParameter("fileCount");
		Integer cImageCount = 0;
		if (contentImageCount != null && !contentImageCount.isEmpty())
		{
			cImageCount = new Integer(contentImageCount);
		}
		
		//content Images
		for (int i=0; i < cImageCount; i ++)
		{
			MultipartFile contentImage = multipartRequest.getFile("contentImage" + (i + 1));
			if (contentImage != null && !contentImage.isEmpty())
			{
				checkResult = FileUploadBean.validateFile(contentImage);
				if (checkResult != null)
				{
					return new ModelAndView(view, "operationMsg", checkResult);
					
				}
				try {
					webInstance.getContentImages().add(FileUploadBean.getUploadedFile(contentImage));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return new ModelAndView(view, "operationMsg", "上传文件失败");
				}
			}
			
		}
		
		
		//Now we need to check the Unique constrains of YdkAccount.
		WebServiceEvent webInstanceCheck = ydkSubjectMngrService.getEventByTitle(
				dataForm.getInstance().getTitle());
		if (webInstanceCheck.getEvent() != null)
		{
			result.rejectValue("instance.title", "duplicate", "已经存在");
			return new ModelAndView(view);
		}
		
		//Input the infor
		Event instance = dataForm.getInstance();
		
		
		
		for (EventCategory type : typeList)
		{
			if (type.getId().equals(dataForm.getInstance().getEventCategory().getId()))
			{
				dataForm.getInstance().setEventCategory(type);
			}
		}
		
		//OK, now it's time to store the Event;
		webInstance.setEvent(instance);
		webInstance.setOs(null);
		OperationStatus os = ydkSubjectMngrService.storeEvent(webInstance);
		
		//Clear the sessioin;
		request.getRequest().getSession().removeAttribute("typeList");
		
		if (!os.getIsSuccessful())
		{
			return new ModelAndView("redirect:listEvent.do",
					"operationMsg", "创建失败");
		}
		else
		{
			return new ModelAndView("redirect:listEvent.do", 
					"operationMsg", "创建成功");
		}
		
	}
	
	
	@RequestMapping(value="/listEvent.do", method=RequestMethod.GET)
	public ModelAndView listEventGet(ServletWebRequest request) {

		String view ="event_list";

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
			WebEventListResult resultList = ydkSubjectMngrService.listEvents(params);
			
			if (resultList == null || !resultList.getOs().getIsSuccessful())
			{
				return new ModelAndView("errorPage", "errorMsg","Could not get Events");
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
		EventForm dataForm = new EventForm();
		if (itemId != null && !itemId.isEmpty())
		{
			Long id = new Long(itemId);
			//user try to modify a record;
			WebServiceEvent webInstance = ydkSubjectMngrService.getEventById(id);
			if (webInstance.getOs().getIsSuccessful())
			{
				dataForm.setInstance(webInstance.getEvent());
			}
			
			WebServiceListParams params = new WebServiceListParams();
			params.setPageSize(WebServiceListParams.MAX_PAGESIZE);
			params.setPageOffset(0);
			WebEventCateListResult resultList = ydkSubjectMngrService.getEventCategories(params);
			
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
	
	
	@RequestMapping(value="/listEvent.do", method=RequestMethod.POST)
	public ModelAndView listEventDelete(@ModelAttribute("dataForm")EventForm dataForm, 
			ServletWebRequest request) {


		for (Long id : dataForm.getKeys())
		{
			ydkSubjectMngrService.deleteEvent(id);
		}
		
		return new ModelAndView("redirect:listEvent.do", "operationMsg", "删除成功");
	}
	
	@RequestMapping(value="/modifyEvent.do", method=RequestMethod.POST)
	public ModelAndView modifyEventPost(@ModelAttribute("dataForm")EventForm dataForm, 
			BindingResult result, ServletWebRequest request) {


		EventValidator.validate(dataForm, result);
		
		EventForm dataFormSession = (EventForm)request.getRequest().getSession().getAttribute("dataFormSession");

		
		if (result.hasErrors() || dataFormSession == null) {
			return new ModelAndView("redirect:listEvent.do", "operationMsg", "修改失败，请检查修改的值");
		}
		dataForm.prepareForModify(dataFormSession.getInstance());

		WebServiceEvent instance = new WebServiceEvent(dataFormSession.getInstance());
		ydkSubjectMngrService.updateEvent(instance);
		
		
		return new ModelAndView("redirect:listEvent.do");
	}
	
	
	
	/**
	 * 
	 * 
	 * 
	 * For BookReview Controller
	 * 
	 * 
	 */
	
	@RequestMapping(value="/viewBookReview.do", method=RequestMethod.GET)
	public ModelAndView viewBookReviewGet(ServletWebRequest request) {
		
		String view ="book_review_view";
		
		String sId = request.getParameter("itemId");
		if (sId == null || sId.isEmpty())
		{
			return new ModelAndView(view, "operationMsg", "无效的Id");
		}
		Long id = new Long((sId));
		BookReviewForm dataForm = null;
		//user try to modify a record;
		WebServiceBookReview webInstance = ydkSubjectMngrService.getBookReviewById(id);
		if (webInstance.getOs().getIsSuccessful())
		{
			dataForm = new BookReviewForm((webInstance.getBookReview()));
		}
		
		Map model = new HashMap();
		model.put("dataForm", dataForm);
		//Get BookReview Cotent 
		WebServiceFile result = ydkFrontEndWebService.getFileByName(dataForm.getInstance().getContent());

		if (result != null && result.getOs().getIsSuccessful())
		{
			try {
				model.put("fileContent", new String(result.getFile().getFileContent(),"utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		
		return new ModelAndView(view, model);
	}
	
	
	
	@RequestMapping(value="/createBookReview.do", method=RequestMethod.GET)
	public ModelAndView createBookReviewGet(ServletWebRequest request) {
		
		String view ="book_review_create";
		
		WebServiceListParams params = new WebServiceListParams();
		params.setPageSize(WebServiceListParams.MAX_PAGESIZE);
		params.setPageOffset(0);
		WebBookReviewCateListResult resultList = ydkSubjectMngrService.getBookReviewCategories(params);
		
		if (resultList == null || !resultList.getOs().getIsSuccessful())
		{
			return new ModelAndView("errorPage", "errorMsg","无法获得访谈类型");
		}
		if (resultList.getList().size() == 0)
		{
			return new ModelAndView("errorPage", "errorMsg","无法获得访谈类型");
		}
		
		
		BookReviewForm dataForm = new BookReviewForm();

		
		Map model = new HashMap();
		
		model.put("dataForm", dataForm);
//		request.getRequest().getSession().setAttribute("typeList", resultList.getList())
		model.put("typeList", resultList.getList());
		
		return new ModelAndView(view, model);
	}
	
	
	
	
	@RequestMapping(value="/createBookReview.do", method=RequestMethod.POST)
	public ModelAndView createBookReviewPost(
			@ModelAttribute("dataForm")BookReviewForm dataForm,
			BindingResult result,
			@ModelAttribute("typeList")List<BookReviewCategory> typeList, 
			ModelMap model, ServletWebRequest request) {
		
		String view ="book_review_create";
		BookReviewValidator.validate(dataForm, result);

		if (result.hasErrors()) {
			return new ModelAndView(view);
		}
		
		WebServiceBookReview webInstance = new WebServiceBookReview();
		
		//Check uploaded files
		//1. all the images could be null
		//2. all the content should not be null
		
		//content file
		MultipartHttpServletRequest multipartRequest =
			(MultipartHttpServletRequest) request.getRequest();
		MultipartFile contentFile = multipartRequest.getFile("contentFile");
		String checkResult = FileUploadBean.validateFile(contentFile);
		if (checkResult != null)
		{
			return new ModelAndView(view, "operationMsg", checkResult);
		}
		
		try {
			webInstance.setContentFile(FileUploadBean.getUploadedFile(contentFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView(view, "operationMsg", "上传文件失败");
		}
		
		
		
		//title Image
		MultipartFile titleImage = multipartRequest.getFile("titleImage");
		if (titleImage != null && !titleImage.isEmpty())
		{
			checkResult = FileUploadBean.validateFile(titleImage);
			if (checkResult != null)
			{
				return new ModelAndView(view, "operationMsg", checkResult);
			}
			try {
				webInstance.setTitleImage(FileUploadBean.getUploadedFile(titleImage));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ModelAndView(view, "operationMsg", "上传文件失败");
			}
		}
		
		
		//content Images count
		String contentImageCount = request.getParameter("fileCount");
		Integer cImageCount = 0;
		if (contentImageCount != null && !contentImageCount.isEmpty())
		{
			cImageCount = new Integer(contentImageCount);
		}
		
		//content Images
		for (int i=0; i < cImageCount; i ++)
		{
			MultipartFile contentImage = multipartRequest.getFile("contentImage" + (i + 1));
			if (contentImage != null && !contentImage.isEmpty())
			{
				checkResult = FileUploadBean.validateFile(contentImage);
				if (checkResult != null)
				{
					return new ModelAndView(view, "operationMsg", checkResult);
					
				}
				try {
					webInstance.getContentImages().add(FileUploadBean.getUploadedFile(contentImage));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return new ModelAndView(view, "operationMsg", "上传文件失败");
				}
			}
			
		}
		
		
		//Now we need to check the Unique constrains of YdkAccount.
		WebServiceBookReview webInstanceCheck = ydkSubjectMngrService.getBookReviewByTitle(
				dataForm.getInstance().getTitle());
		if (webInstanceCheck.getBookReview() != null)
		{
			result.rejectValue("instance.title", "duplicate", "已经存在");
			return new ModelAndView(view);
		}
		
		//Input the infor
		BookReview instance = dataForm.getInstance();
		
		
		
		for (BookReviewCategory type : typeList)
		{
			if (type.getId().equals(dataForm.getInstance().getBookReviewCategory().getId()))
			{
				dataForm.getInstance().setBookReviewCategory(type);
			}
		}
		
		//OK, now it's time to store the BookReview;
		webInstance.setBookReview(instance);
		webInstance.setOs(null);
		OperationStatus os = ydkSubjectMngrService.storeBookReview(webInstance);
		
		//Clear the sessioin;
		request.getRequest().getSession().removeAttribute("typeList");
		
		if (!os.getIsSuccessful())
		{
			return new ModelAndView("redirect:listBookReview.do",
					"operationMsg", "创建失败");
		}
		else
		{
			return new ModelAndView("redirect:listBookReview.do", 
					"operationMsg", "创建成功");
		}
		
	}
	
	
	@RequestMapping(value="/listBookReview.do", method=RequestMethod.GET)
	public ModelAndView listBookReviewGet(ServletWebRequest request) {

		String view ="book_review_list";

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
			WebBookReviewListResult resultList = ydkSubjectMngrService.listBookReviews(params);
			
			if (resultList == null || !resultList.getOs().getIsSuccessful())
			{
				return new ModelAndView("errorPage", "errorMsg","Could not get BookReviews");
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
		BookReviewForm dataForm = new BookReviewForm();
		if (itemId != null && !itemId.isEmpty())
		{
			Long id = new Long(itemId);
			//user try to modify a record;
			WebServiceBookReview webInstance = ydkSubjectMngrService.getBookReviewById(id);
			if (webInstance.getOs().getIsSuccessful())
			{
				dataForm.setInstance(webInstance.getBookReview());
			}
			
			WebServiceListParams params = new WebServiceListParams();
			params.setPageSize(WebServiceListParams.MAX_PAGESIZE);
			params.setPageOffset(0);
			WebBookReviewCateListResult resultList = ydkSubjectMngrService.getBookReviewCategories(params);
			
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
	
	
	@RequestMapping(value="/listBookReview.do", method=RequestMethod.POST)
	public ModelAndView listBookReviewDelete(@ModelAttribute("dataForm")BookReviewForm dataForm, 
			ServletWebRequest request) {


		for (Long id : dataForm.getKeys())
		{
			ydkSubjectMngrService.deleteBookReview(id);
		}
		
		return new ModelAndView("redirect:listBookReview.do", "operationMsg", "删除成功");
	}
	
	@RequestMapping(value="/modifyBookReview.do", method=RequestMethod.POST)
	public ModelAndView modifyBookReviewPost(@ModelAttribute("dataForm")BookReviewForm dataForm, 
			BindingResult result, ServletWebRequest request) {


		BookReviewValidator.validate(dataForm, result);
		
		BookReviewForm dataFormSession = (BookReviewForm)request.getRequest().getSession().getAttribute("dataFormSession");

		
		if (result.hasErrors() || dataFormSession == null) {
			return new ModelAndView("redirect:listBookReview.do", "operationMsg", "修改失败，请检查修改的值");
		}
		dataForm.prepareForModify(dataFormSession.getInstance());

		WebServiceBookReview instance = new WebServiceBookReview(dataFormSession.getInstance());
		ydkSubjectMngrService.updateBookReview(instance);
		
		
		return new ModelAndView("redirect:listBookReview.do");
	}
	
	
	/**
	 * 
	 * 
	 * 
	 * For BookNews Controller
	 * 
	 * 
	 */
	
	@RequestMapping(value="/viewBookNews.do", method=RequestMethod.GET)
	public ModelAndView viewBookNewsGet(ServletWebRequest request) {
		
		String view ="book_news_view";
		
		String sId = request.getParameter("itemId");
		if (sId == null || sId.isEmpty())
		{
			return new ModelAndView(view, "operationMsg", "无效的Id");
		}
		Long id = new Long((sId));
		BookNewsForm dataForm = null;
		//user try to modify a record;
		WebServiceBookNews webInstance = ydkSubjectMngrService.getBookNewsById(id);
		if (webInstance.getOs().getIsSuccessful())
		{
			dataForm = new BookNewsForm((webInstance.getBookNews()));
		}
		
		Map model = new HashMap();
		model.put("dataForm", dataForm);
		//Get BookNews Cotent 
		WebServiceFile result = ydkFrontEndWebService.getFileByName(dataForm.getInstance().getContent());

		if (result != null && result.getOs().getIsSuccessful())
		{
			try {
				model.put("fileContent", new String(result.getFile().getFileContent(),"utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		
		return new ModelAndView(view, model);
	}
	
	
	
	@RequestMapping(value="/createBookNews.do", method=RequestMethod.GET)
	public ModelAndView createBookNewsGet(ServletWebRequest request) {
		
		String view ="book_news_create";
		
		WebServiceListParams params = new WebServiceListParams();
		params.setPageSize(WebServiceListParams.MAX_PAGESIZE);
		params.setPageOffset(0);
		WebBookNewsCateListResult resultList = ydkSubjectMngrService.getBookNewsCategories(params);
		
		if (resultList == null || !resultList.getOs().getIsSuccessful())
		{
			return new ModelAndView("errorPage", "errorMsg","无法获得访谈类型");
		}
		if (resultList.getList().size() == 0)
		{
			return new ModelAndView("errorPage", "errorMsg","无法获得访谈类型");
		}
		
		
		BookNewsForm dataForm = new BookNewsForm();

		
		Map model = new HashMap();
		
		model.put("dataForm", dataForm);
//		request.getRequest().getSession().setAttribute("typeList", resultList.getList())
		model.put("typeList", resultList.getList());
		
		return new ModelAndView(view, model);
	}
	
	
	
	
	@RequestMapping(value="/createBookNews.do", method=RequestMethod.POST)
	public ModelAndView createBookNewsPost(
			@ModelAttribute("dataForm")BookNewsForm dataForm,
			BindingResult result,
			@ModelAttribute("typeList")List<BookNewsCategory> typeList, 
			ModelMap model, ServletWebRequest request) {
		
		String view ="book_news_create";
		BookNewsValidator.validate(dataForm, result);

		if (result.hasErrors()) {
			return new ModelAndView(view);
		}
		
		WebServiceBookNews webInstance = new WebServiceBookNews();
		
		//Check uploaded files
		//1. all the images could be null
		//2. all the content should not be null
		
		//content file
		MultipartHttpServletRequest multipartRequest =
			(MultipartHttpServletRequest) request.getRequest();
		MultipartFile contentFile = multipartRequest.getFile("contentFile");
		String checkResult = FileUploadBean.validateFile(contentFile);
		if (checkResult != null)
		{
			return new ModelAndView(view, "operationMsg", checkResult);
		}
		
		try {
			webInstance.setContentFile(FileUploadBean.getUploadedFile(contentFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView(view, "operationMsg", "上传文件失败");
		}
		
		
		
		//title Image
		MultipartFile titleImage = multipartRequest.getFile("titleImage");
		if (titleImage != null && !titleImage.isEmpty())
		{
			checkResult = FileUploadBean.validateFile(titleImage);
			if (checkResult != null)
			{
				return new ModelAndView(view, "operationMsg", checkResult);
			}
			try {
				webInstance.setTitleImage(FileUploadBean.getUploadedFile(titleImage));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ModelAndView(view, "operationMsg", "上传文件失败");
			}
		}
		
		
		//content Images count
		String contentImageCount = request.getParameter("fileCount");
		Integer cImageCount = 0;
		if (contentImageCount != null && !contentImageCount.isEmpty())
		{
			cImageCount = new Integer(contentImageCount);
		}
		
		//content Images
		for (int i=0; i < cImageCount; i ++)
		{
			MultipartFile contentImage = multipartRequest.getFile("contentImage" + (i + 1));
			if (contentImage != null && !contentImage.isEmpty())
			{
				checkResult = FileUploadBean.validateFile(contentImage);
				if (checkResult != null)
				{
					return new ModelAndView(view, "operationMsg", checkResult);
					
				}
				try {
					webInstance.getContentImages().add(FileUploadBean.getUploadedFile(contentImage));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return new ModelAndView(view, "operationMsg", "上传文件失败");
				}
			}
			
		}
		
		
		//Now we need to check the Unique constrains of YdkAccount.
		WebServiceBookNews webInstanceCheck = ydkSubjectMngrService.getBookNewsByTitle(
				dataForm.getInstance().getTitle());
		if (webInstanceCheck.getBookNews() != null)
		{
			result.rejectValue("instance.title", "duplicate", "已经存在");
			return new ModelAndView(view);
		}
		
		//Input the infor
		BookNews instance = dataForm.getInstance();
		
		
		
		for (BookNewsCategory type : typeList)
		{
			if (type.getId().equals(dataForm.getInstance().getBookNewsCategory().getId()))
			{
				dataForm.getInstance().setBookNewsCategory(type);
			}
		}
		
		//OK, now it's time to store the BookNews;
		webInstance.setBookNews(instance);
		webInstance.setOs(null);
		OperationStatus os = ydkSubjectMngrService.storeBookNews(webInstance);
		
		//Clear the sessioin;
		request.getRequest().getSession().removeAttribute("typeList");
		
		if (!os.getIsSuccessful())
		{
			return new ModelAndView("redirect:listBookNews.do",
					"operationMsg", "创建失败");
		}
		else
		{
			return new ModelAndView("redirect:listBookNews.do", 
					"operationMsg", "创建成功");
		}
		
	}
	
	
	@RequestMapping(value="/listBookNews.do", method=RequestMethod.GET)
	public ModelAndView listBookNewsGet(ServletWebRequest request) {

		String view ="book_news_list";

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
			WebBookNewsListResult resultList = ydkSubjectMngrService.listBookNewses(params);
			
			if (resultList == null || !resultList.getOs().getIsSuccessful())
			{
				return new ModelAndView("errorPage", "errorMsg","Could not get BookNewss");
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
		BookNewsForm dataForm = new BookNewsForm();
		if (itemId != null && !itemId.isEmpty())
		{
			Long id = new Long(itemId);
			//user try to modify a record;
			WebServiceBookNews webInstance = ydkSubjectMngrService.getBookNewsById(id);
			if (webInstance.getOs().getIsSuccessful())
			{
				dataForm.setInstance(webInstance.getBookNews());
			}
			
			WebServiceListParams params = new WebServiceListParams();
			params.setPageSize(WebServiceListParams.MAX_PAGESIZE);
			params.setPageOffset(0);
			WebBookNewsCateListResult resultList = ydkSubjectMngrService.getBookNewsCategories(params);
			
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
	
	
	@RequestMapping(value="/listBookNews.do", method=RequestMethod.POST)
	public ModelAndView listBookNewsDelete(@ModelAttribute("dataForm")BookNewsForm dataForm, 
			ServletWebRequest request) {


		for (Long id : dataForm.getKeys())
		{
			ydkSubjectMngrService.deleteBookNews(id);
		}
		
		return new ModelAndView("redirect:listBookNews.do", "operationMsg", "删除成功");
	}
	
	@RequestMapping(value="/modifyBookNews.do", method=RequestMethod.POST)
	public ModelAndView modifyBookNewsPost(@ModelAttribute("dataForm")BookNewsForm dataForm, 
			BindingResult result, ServletWebRequest request) {


		BookNewsValidator.validate(dataForm, result);
		
		BookNewsForm dataFormSession = (BookNewsForm)request.getRequest().getSession().getAttribute("dataFormSession");

		
		if (result.hasErrors() || dataFormSession == null) {
			return new ModelAndView("redirect:listBookNews.do", "operationMsg", "修改失败，请检查修改的值");
		}
		dataForm.prepareForModify(dataFormSession.getInstance());

		WebServiceBookNews instance = new WebServiceBookNews(dataFormSession.getInstance());
		ydkSubjectMngrService.updateBookNews(instance);
		
		
		return new ModelAndView("redirect:listBookNews.do");
	}
	

}
