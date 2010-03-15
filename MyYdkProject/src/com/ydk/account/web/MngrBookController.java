package com.ydk.account.web;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.ydk.account.validator.BookTagValidator;
import com.ydk.account.validator.BookValidator;
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
import com.ydk.book.persistence.entity.Author;
import com.ydk.book.persistence.entity.Book;
import com.ydk.book.persistence.entity.BookNews;
import com.ydk.book.persistence.entity.BookNewsCategory;
import com.ydk.book.persistence.entity.BookReview;
import com.ydk.book.persistence.entity.BookReviewCategory;
import com.ydk.book.persistence.entity.BookTag;
import com.ydk.book.persistence.entity.Event;
import com.ydk.book.persistence.entity.EventCategory;
import com.ydk.book.persistence.entity.Interview;
import com.ydk.book.persistence.entity.InterviewCategory;
import com.ydk.book.persistence.entity.Translator;
import com.ydk.book.webservice.OperationStatus;
import com.ydk.book.webservice.WebBookCateListResult;
import com.ydk.book.webservice.WebBookListResult;
import com.ydk.book.webservice.WebBookNewsCateListResult;
import com.ydk.book.webservice.WebBookNewsListResult;
import com.ydk.book.webservice.WebBookReviewCateListResult;
import com.ydk.book.webservice.WebBookReviewListResult;
import com.ydk.book.webservice.WebBookTagListResult;
import com.ydk.book.webservice.WebEventCateListResult;
import com.ydk.book.webservice.WebEventListResult;
import com.ydk.book.webservice.WebInterviewCateListResult;
import com.ydk.book.webservice.WebInterviewListResult;
import com.ydk.book.webservice.WebLibraryListResult;
import com.ydk.book.webservice.WebServiceBook;
import com.ydk.book.webservice.WebServiceBookNews;
import com.ydk.book.webservice.WebServiceBookNewsCategory;
import com.ydk.book.webservice.WebServiceBookReview;
import com.ydk.book.webservice.WebServiceBookReviewCategory;
import com.ydk.book.webservice.WebServiceBookTag;
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
public class MngrBookController extends WebControllerBase {

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

	public MngrBookController() {
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
	 * For Library Controller
	 * 
	 * 
	 */
	@RequestMapping(value="/createLibrary.do", method=RequestMethod.GET)
	public ModelAndView createLibraryGet(ServletWebRequest request) {
		
		String view ="library_create";
		
		WebServiceListParams params = new WebServiceListParams();
		params.setPageSize(WebServiceListParams.MAX_PAGESIZE);
		params.setPageOffset(0);

		LibraryForm dataForm = new LibraryForm();

		
		Map model = new HashMap();
		
		model.put("dataForm", dataForm);
		
		return new ModelAndView(view, model);
	}
	
	@RequestMapping(value="/createLibrary.do", method=RequestMethod.POST)
	public ModelAndView createLibraryPost(
			@ModelAttribute("dataForm")LibraryForm dataForm,
			BindingResult result, ModelMap model, ServletWebRequest request) {
		
		String view ="library_create";
		
		LibraryValidator.validate(dataForm, result);

		if (result.hasErrors()) {
			return new ModelAndView(view);
		}
		
		//Now we need to check the Unique constrains of YdkAccount.
		WebServiceLibrary webInstance = ydkBookMngrService.getLibraryByName(
				dataForm.getInstance().getName());
		if (webInstance.getLibrary() != null)
		{
			result.rejectValue("instance.name", "duplicate", "已经存在");
			return new ModelAndView(view);
		}
		
		//Input the infor
		Library instance = dataForm.getInstance();
		
		
		
		//OK, now it's time to store the Library;
		webInstance.setLibrary(instance);
		webInstance.setOs(null);
		OperationStatus os = ydkBookMngrService.storeLibrary(webInstance);
		
		//Clear the sessioin;
//		request.getRequest().getSession().removeAttribute("typeList");
		
		if (!os.getIsSuccessful())
		{
			return new ModelAndView("redirect:listLibrary.do",
					"operationMsg", "创建失败");
		}
		else
		{
			return new ModelAndView("redirect:listLibrary.do", 
					"operationMsg", "创建成功");
		}
		
	}
	
	
	@RequestMapping(value="/listLibrary.do", method=RequestMethod.GET)
	public ModelAndView listLibraryGet(ServletWebRequest request) {

		String view ="library_list";

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
			WebLibraryListResult resultList = ydkBookMngrService.getLibraries(params);
			
			if (resultList == null || !resultList.getOs().getIsSuccessful())
			{
				return new ModelAndView("errorPage", "errorMsg","Could not get Librarys");
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
		LibraryForm dataForm = new LibraryForm();
		if (itemId != null && !itemId.isEmpty())
		{
			Long id = new Long(itemId);
			//user try to modify a record;
			WebServiceLibrary webInstance = ydkBookMngrService.getLibraryById(id);
			if (webInstance.getOs().getIsSuccessful())
			{
				dataForm.setInstance(webInstance.getLibrary());
			}
			
			//Get the TypeList of Instance
//			WebServiceListParams params = new WebServiceListParams();
//			params.setPageSize(WebServiceListParams.MAX_PAGESIZE);
//			params.setPageOffset(0);
//			WebLibraryTypeListResult resultList = ydkBookMngrService.getAllLibraryTypes(params);
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
	
	
	@RequestMapping(value="/listLibrary.do", method=RequestMethod.POST)
	public ModelAndView listLibraryDelete(@ModelAttribute("dataForm")LibraryForm dataForm, 
			ServletWebRequest request) {


		for (Long id : dataForm.getKeys())
		{
			ydkBookMngrService.deleteLibrary(id);
		}
		
		return new ModelAndView("redirect:listLibrary.do", "operationMsg", "删除成功");
	}
	
	@RequestMapping(value="/modifyLibrary.do", method=RequestMethod.POST)
	public ModelAndView modifyLibraryPost(@ModelAttribute("dataForm")LibraryForm dataForm, 
			BindingResult result, ServletWebRequest request) {


		LibraryValidator.validate(dataForm, result);
		
		LibraryForm dataFormSession = (LibraryForm)request.getRequest().getSession().getAttribute("dataFormSession");

		
		if (result.hasErrors() || dataFormSession == null) {
			return new ModelAndView("redirect:listLibrary.do", "operationMsg", "修改失败，请检查修改的值");
		}
		dataForm.prepareForModify(dataFormSession.getInstance());

		WebServiceLibrary instance = new WebServiceLibrary(dataFormSession.getInstance());
		ydkBookMngrService.updateLibrary(instance);
		
		return new ModelAndView("redirect:listLibrary.do");
	}
	
	
	
	/**
	 * 
	 * 
	 * For Category Controller
	 * 
	 */
	
	@RequestMapping(value="/createCategory.do", method=RequestMethod.GET)
	public ModelAndView createCategoryGet(ServletWebRequest request) {
		
		String view ="category_create";
		
		WebServiceListParams params = new WebServiceListParams();
		params.setPageSize(WebServiceListParams.MAX_PAGESIZE);
		params.setPageOffset(0);
		WebLibraryListResult resultList = ydkBookMngrService.getLibraries(params);
		
		
		if (resultList == null || !resultList.getOs().getIsSuccessful())
		{
			return new ModelAndView("errorPage", "errorMsg","无法获得书馆信息");
		}
		if (resultList.getList().size() == 0)
		{
			return new ModelAndView("errorPage", "errorMsg","无法获得书馆信息");
		}
		
		
		CategoryForm dataForm = new CategoryForm();

		Map model = new HashMap();
		
		model.put("dataForm", dataForm);
		model.put("typeList", resultList.getList());
		
		return new ModelAndView(view, model);
	}
	
	
	@RequestMapping(value="/createCategory.do", method=RequestMethod.POST)
	public ModelAndView createCategoryPost(
			@ModelAttribute("dataForm")CategoryForm dataForm,
			BindingResult result,
			@ModelAttribute("typeList")List<Library> typeList, 
			ModelMap model, ServletWebRequest request) {
		
		String view ="category_create";
		
		CategoryValidator.validate(dataForm, result);

		if (result.hasErrors()) {
			return new ModelAndView(view);
		}
		
		//Now we need to check the Unique constrains of YdkAccount.
		WebServiceCategory webInstance = ydkBookMngrService.getCategoryByName(
				dataForm.getInstance().getName());
		if (webInstance.getCategory() != null)
		{
			result.rejectValue("instance.name", "duplicate", "已经存在");
			return new ModelAndView(view);
		}
		
		//Input the infor
		Category instance = dataForm.getInstance();
		
		
		
		for (Library type : typeList)
		{
			if (type.getId().equals(dataForm.getInstance().getLibrary().getId()))
			{
				dataForm.getInstance().setLibrary(type);
			}
		}
		
		//OK, now it's time to store the Category;
		webInstance.setCategory(instance);
		webInstance.setOs(null);
		OperationStatus os = ydkBookMngrService.storeCategory(webInstance);
		
		//Clear the sessioin;
		request.getRequest().getSession().removeAttribute("typeList");
		
		if (!os.getIsSuccessful())
		{
			return new ModelAndView("redirect:listCategory.do",
					"operationMsg", "创建失败");
		}
		else
		{
			return new ModelAndView("redirect:listCategory.do", 
					"operationMsg", "创建成功");
		}
		
	}
	
	
	@RequestMapping(value="/listCategory.do", method=RequestMethod.GET)
	public ModelAndView listCategoryGet(ServletWebRequest request) {

		String view ="category_list";

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
			WebBookCateListResult resultList = ydkBookMngrService.getCategories(params);
			
			if (resultList == null || !resultList.getOs().getIsSuccessful())
			{
				return new ModelAndView("errorPage", "errorMsg","Could not get Categorys");
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
		CategoryForm dataForm = new CategoryForm();
		if (itemId != null && !itemId.isEmpty())
		{
			Long id = new Long(itemId);
			//user try to modify a record;
			WebServiceCategory webInstance = ydkBookMngrService.getCategoryById(id);
			if (webInstance.getOs().getIsSuccessful())
			{
				dataForm.setInstance(webInstance.getCategory());
			}
			
			WebServiceListParams params = new WebServiceListParams();
			params.setPageSize(WebServiceListParams.MAX_PAGESIZE);
			params.setPageOffset(0);
			WebLibraryListResult resultList = ydkBookMngrService.getLibraries(params);
			
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
	
	
	@RequestMapping(value="/listCategory.do", method=RequestMethod.POST)
	public ModelAndView listCategoryDelete(@ModelAttribute("dataForm")CategoryForm dataForm, 
			ServletWebRequest request) {


		for (Long id : dataForm.getKeys())
		{
			ydkBookMngrService.deleteCategory(id);
		}
		
		return new ModelAndView("redirect:listCategory.do", "operationMsg", "删除成功");
	}
	
	@RequestMapping(value="/modifyCategory.do", method=RequestMethod.POST)
	public ModelAndView modifyCategoryPost(@ModelAttribute("dataForm")CategoryForm dataForm, 
			BindingResult result, ServletWebRequest request) {


		CategoryValidator.validate(dataForm, result);
		
		CategoryForm dataFormSession = (CategoryForm)request.getRequest().getSession().getAttribute("dataFormSession");

		
		if (result.hasErrors() || dataFormSession == null) {
			return new ModelAndView("redirect:listCategory.do", "operationMsg", "修改失败，请检查修改的值");
		}
		dataForm.prepareForModify(dataFormSession.getInstance());

		WebServiceCategory instance = new WebServiceCategory(dataFormSession.getInstance());
		ydkBookMngrService.updateCategory(instance);
		
		
		return new ModelAndView("redirect:listCategory.do");
	}

	
	/**
	 * 
	 * 
	 * 
	 * For BookTag Controller
	 * 
	 * 
	 */
	
	@RequestMapping(value="/viewBookTag.do", method=RequestMethod.GET)
	public ModelAndView viewBookTagGet(ServletWebRequest request) {
		
		String view ="book_tag_view";
		
		String sId = request.getParameter("itemId");
		if (sId == null || sId.isEmpty())
		{
			return new ModelAndView(view, "operationMsg", "无效的Id");
		}
		Long id = new Long((sId));
		BookTagForm dataForm = null;
		//user try to modify a record;
		WebServiceBookTag webInstance = ydkSubjectMngrService.getBookTagById(id);
		if (webInstance.getOs().getIsSuccessful())
		{
			dataForm = new BookTagForm((webInstance.getBookTag()));
		}
		
		Map model = new HashMap();
		model.put("dataForm", dataForm);
		


		
		return new ModelAndView(view, model);
	}
	
	
	
	@RequestMapping(value="/createBookTag.do", method=RequestMethod.GET)
	public ModelAndView createBookTagGet(ServletWebRequest request) {
		
		String view ="book_tag_create";
		
		WebServiceListParams params = new WebServiceListParams();
		params.setPageSize(WebServiceListParams.MAX_PAGESIZE);
		params.setPageOffset(0);
		
		
		BookTagForm dataForm = new BookTagForm();

		
		Map model = new HashMap();
		
		model.put("dataForm", dataForm);
//		request.getRequest().getSession().setAttribute("typeList", resultList.getList())
		
		return new ModelAndView(view, model);
	}
	
	
	
	
	@RequestMapping(value="/createBookTag.do", method=RequestMethod.POST)
	public ModelAndView createBookTagPost(
			@ModelAttribute("dataForm")BookTagForm dataForm,
			BindingResult result,
			ModelMap model, ServletWebRequest request) {
		
		String view ="book_tag_create";
		BookTagValidator.validate(dataForm, result);

		if (result.hasErrors()) {
			return new ModelAndView(view);
		}
		
		WebServiceBookTag webInstance = new WebServiceBookTag();
		
//		//Check uploaded files
//		//1. all the images could be null
//		//2. all the content should not be null
//		
//		//content file
//		MultipartHttpServletRequest multipartRequest =
//			(MultipartHttpServletRequest) request.getRequest();
//		MultipartFile contentFile = multipartRequest.getFile("contentFile");
//		String checkResult = FileUploadBean.validateFile(contentFile);
//		if (checkResult != null)
//		{
//			return new ModelAndView(view, "operationMsg", checkResult);
//		}
//		
//		try {
//			webInstance.setContentFile(FileUploadBean.getUploadedFile(contentFile));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return new ModelAndView(view, "operationMsg", "上传文件失败");
//		}
//		
//		
//		
//		//title Image
//		MultipartFile titleImage = multipartRequest.getFile("titleImage");
//		if (titleImage != null && !titleImage.isEmpty())
//		{
//			checkResult = FileUploadBean.validateFile(titleImage);
//			if (checkResult != null)
//			{
//				return new ModelAndView(view, "operationMsg", checkResult);
//			}
//			try {
//				webInstance.setTitleImage(FileUploadBean.getUploadedFile(titleImage));
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				return new ModelAndView(view, "operationMsg", "上传文件失败");
//			}
//		}
//		
//		
//		//content Images count
//		String contentImageCount = request.getParameter("fileCount");
//		Integer cImageCount = 0;
//		if (contentImageCount != null && !contentImageCount.isEmpty())
//		{
//			cImageCount = new Integer(contentImageCount);
//		}
//		
//		//content Images
//		for (int i=0; i < cImageCount; i ++)
//		{
//			MultipartFile contentImage = multipartRequest.getFile("contentImage" + (i + 1));
//			if (contentImage != null && !contentImage.isEmpty())
//			{
//				checkResult = FileUploadBean.validateFile(contentImage);
//				if (checkResult != null)
//				{
//					return new ModelAndView(view, "operationMsg", checkResult);
//					
//				}
//				try {
//					webInstance.getContentImages().add(FileUploadBean.getUploadedFile(contentImage));
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//					return new ModelAndView(view, "operationMsg", "上传文件失败");
//				}
//			}
//			
//		}
		
		
		//Now we need to check the Unique constrains of YdkAccount.
		WebServiceBookTag webInstanceCheck = ydkSubjectMngrService.getBookTagByName(
				dataForm.getInstance().getName());
		if (webInstanceCheck.getBookTag() != null)
		{
			result.rejectValue("instance.name", "duplicate", "已经存在");
			return new ModelAndView(view);
		}
		
		//Input the infor
		BookTag instance = dataForm.getInstance();
		
		
		
		//OK, now it's time to store the BookTag;
		webInstance.setBookTag(instance);
		webInstance.setOs(null);
		OperationStatus os = ydkSubjectMngrService.storeBookTag(webInstance);
		
		//Clear the sessioin;
		request.getRequest().getSession().removeAttribute("typeList");
		
		if (!os.getIsSuccessful())
		{
			return new ModelAndView("redirect:listBookTag.do",
					"operationMsg", "创建失败");
		}
		else
		{
			return new ModelAndView("redirect:listBookTag.do", 
					"operationMsg", "创建成功");
		}
		
	}
	
	
	@RequestMapping(value="/listBookTag.do", method=RequestMethod.GET)
	public ModelAndView listBookTagGet(ServletWebRequest request) {

		String view ="book_tag_list";

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
			WebBookTagListResult resultList = ydkSubjectMngrService.listBookTags(params);
			
			if (resultList == null || !resultList.getOs().getIsSuccessful())
			{
				return new ModelAndView("errorPage", "errorMsg","Could not get BookTags");
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
		BookTagForm dataForm = new BookTagForm();
		if (itemId != null && !itemId.isEmpty())
		{
			Long id = new Long(itemId);
			//user try to modify a record;
			WebServiceBookTag webInstance = ydkSubjectMngrService.getBookTagById(id);
			if (webInstance.getOs().getIsSuccessful())
			{
				dataForm.setInstance(webInstance.getBookTag());
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
	
	
	@RequestMapping(value="/listBookTag.do", method=RequestMethod.POST)
	public ModelAndView listBookTagDelete(@ModelAttribute("dataForm")BookTagForm dataForm, 
			ServletWebRequest request) {


		for (Long id : dataForm.getKeys())
		{
			ydkSubjectMngrService.deleteBookTag(id);
		}
		
		return new ModelAndView("redirect:listBookTag.do", "operationMsg", "删除成功");
	}
	
	@RequestMapping(value="/modifyBookTag.do", method=RequestMethod.POST)
	public ModelAndView modifyBookTagPost(@ModelAttribute("dataForm")BookTagForm dataForm, 
			BindingResult result, ServletWebRequest request) {


		BookTagValidator.validate(dataForm, result);
		
		BookTagForm dataFormSession = (BookTagForm)request.getRequest().getSession().getAttribute("dataFormSession");

		
		if (result.hasErrors() || dataFormSession == null) {
			return new ModelAndView("redirect:listBookTag.do", "operationMsg", "修改失败，请检查修改的值");
		}
		dataForm.prepareForModify(dataFormSession.getInstance());

		WebServiceBookTag instance = new WebServiceBookTag(dataFormSession.getInstance());
		ydkSubjectMngrService.updateBookTag(instance);
		
		
		return new ModelAndView("redirect:listBookTag.do");
	}
	
	
	
	/**
	 * 
	 * 
	 * 
	 * For Book Controller
	 * 
	 * 
	 */
	
	@RequestMapping(value="/viewBook.do", method=RequestMethod.GET)
	public ModelAndView viewBookGet(ServletWebRequest request) {
		
		String view ="book_view";
		
		String sId = request.getParameter("itemId");
		if (sId == null || sId.isEmpty())
		{
			return new ModelAndView(view, "operationMsg", "无效的Id");
		}
		Long id = new Long((sId));
		BookForm dataForm = null;
		//user try to modify a record;
		WebServiceBook webInstance = ydkBookMngrService.getBookById(id);
		if (webInstance.getOs().getIsSuccessful())
		{
			dataForm = new BookForm((webInstance.getBook()));
		}
		
		Map model = new HashMap();
		model.put("dataForm", dataForm);
		//Get Book Cotent 
//		WebServiceFile result = ydkFrontEndWebService.getFileByName(dataForm.getInstance().getBookContent());
//
//		if (result != null && result.getOs().getIsSuccessful())
//		{
//			try {
//				model.put("fileContent", new String(result.getFile().getFileContent(),"utf-8"));
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
		WebServiceListParams params = new WebServiceListParams();
		params.setPageSize(WebServiceListParams.MAX_PAGESIZE);
		params.setPageOffset(0);
		WebBookTagListResult tagList = ydkSubjectMngrService.listBookTags(params);
		
		if (tagList == null || !tagList.getOs().getIsSuccessful())
		{
			return new ModelAndView("errorPage", "errorMsg","获得栏目列表错误");
		}

		model.put("tagList", tagList);
		
		return new ModelAndView(view, model);
	}
	
	
	
	@RequestMapping(value="/createBook.do", method=RequestMethod.GET)
	public ModelAndView createBookGet(ServletWebRequest request) {
		
		String view ="book_create";
		
		WebServiceListParams params = new WebServiceListParams();
		params.setPageSize(WebServiceListParams.MAX_PAGESIZE);
		params.setPageOffset(0);
		WebBookCateListResult resultList = ydkBookMngrService.getCategories(params);
		
		if (resultList == null || !resultList.getOs().getIsSuccessful())
		{
			return new ModelAndView("errorPage", "errorMsg","无法获得访谈类型");
		}
		if (resultList.getList().size() == 0)
		{
			return new ModelAndView("errorPage", "errorMsg","无法获得访谈类型");
		}
		
		
		
		
		BookForm dataForm = new BookForm();

		
		Map model = new HashMap();
		
		model.put("dataForm", dataForm);

//		request.getRequest().getSession().setAttribute("typeList", resultList.getList())
		model.put("typeList", resultList.getList());
		
		return new ModelAndView(view, model);
	}
	
	
	@RequestMapping(value="/flashAddBook.do", method=RequestMethod.GET)
	public ModelAndView flashAddBookGet(ServletWebRequest request) {
		
		String view ="book_flash_create";
		
		WebServiceListParams params = new WebServiceListParams();
		params.setPageSize(WebServiceListParams.MAX_PAGESIZE);
		params.setPageOffset(0);
		WebBookCateListResult resultList = ydkBookMngrService.getCategories(params);
		
		if (resultList == null || !resultList.getOs().getIsSuccessful())
		{
			return new ModelAndView("errorPage", "errorMsg","无法获得书籍分类");
		}
		if (resultList.getList().size() == 0)
		{
			return new ModelAndView("errorPage", "errorMsg","无法获得书籍分类");
		}
		
		
		BookForm dataForm = new BookForm();

		
		Map model = new HashMap();
		
//		request.getRequest().getSession().setAttribute("typeList", resultList.getList())
		model.put("typeList", resultList.getList());
		
		return new ModelAndView(view, model);
	}
	
	
	@RequestMapping(value="/flashAddBook.do", method=RequestMethod.POST)
	public ModelAndView flashAddBook(
				ServletWebRequest request) {
		
		String view ="book_flash_create";

		WebServiceBook webInstance = new WebServiceBook();
		Book instance = new Book();
		Key key = new Key();
		
		String params = request.getParameter("ydkBookId");
		if (params == null)
		{
			return new ModelAndView("errorPage", "errorMsg","ydkBookId不能为空");
		}
		instance.setYdkBookId(params);
		key.setYdkBookId(params);
		params = request.getParameter("title");
		if (params == null)
		{
			return new ModelAndView("errorPage", "errorMsg","title不能为空");
		}
		instance.setTitle(params);
		params = request.getParameter("author");
		if (params != null)
		{
			instance.setAuthor(new Author(params));
		}
		 params = request.getParameter("author1");
		 if (params != null)
		{
		 instance.setAuthor1(new Author(params));
		}
		 params = request.getParameter("author2");
		 if (params != null)
		{
		 instance.setAuthor2(new Author(params));
		}
		 params = request.getParameter("author3");
		 if (params != null)
		{
		 instance.setAuthor3(new Author(params));
		}
		 params = request.getParameter("translator");
		 if (params != null)
		{
		instance.setTranslator(new Translator(params));
		}
		 params = request.getParameter("translator1");
		 if (params != null)
		{
		instance.setTranslator1(new Translator(params));
		}
		 params = request.getParameter("translator2");
		 if (params != null)
		{
		instance.setTranslator2(new Translator(params));
		}
		 params = request.getParameter("translator3");
		 if (params != null)
		{
		instance.setTranslator3(new Translator(params));
		}
		
		params = request.getParameter("edition");
		if (params != null)
		{
		instance.setEdition(params);
		}
		params = request.getParameter("summary");
		if (params != null)
		{
		instance.setSummary(params);
		}
		params = request.getParameter("toc");
		if (params != null)
		{
		instance.setToc(params);
		}
		params = request.getParameter("key");
		if (params != null)
		{
			instance.setToc(params);
		}
		params = request.getParameter("category");
		if (params != null)
		{	
			instance.setCategory(new Category(params));
		}
		
		params = request.getParameter("publishDate");
		if (params != null)
		{
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				instance.setPublishDate(dateFormat.parse(params));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		params = request.getParameter("pages");
		if (params != null)
		{
			instance.setPages(Integer.parseInt(params));
		}
		params = request.getParameter("isbn");
		if (params != null)
		{
			instance.setIsbn((params));
		}
		params = request.getParameter("scoreReaderNum");
		if (params != null)
		{
			instance.setScoreReaderNum(Integer.parseInt(params));
		}
		
		params = request.getParameter("size");
		if (params != null)
		{
			instance.setSize(Double.parseDouble(params));
		}
		
		params = request.getParameter("paperPrice");
		if (params != null)
		{
			instance.setPaperPrice(Double.parseDouble(params));
		}
		
		params = request.getParameter("marketPrice");
		if (params != null)
		{
			instance.setMarketPrice(Double.parseDouble(params));
		}
		
		params = request.getParameter("selectedEPrice");
		if (params != null)
		{
			instance.setSelectedEPrice(Double.parseDouble(params));
		}
		
		params = request.getParameter("ydkPaperPrice");
		if (params != null)
		{
			instance.setYdkPaperPrice(Double.parseDouble(params));
		}
		
		params = request.getParameter("score");
		if (params != null)
		{
			instance.setScore(Double.parseDouble(params));
		}
		 
		//Check uploaded files
		//1. all the images could be null
		//2. all the content should not be null
		
		
		//content file
		MultipartHttpServletRequest multipartRequest =
			(MultipartHttpServletRequest) request.getRequest();
		String checkResult = null;
		
		MultipartFile contentFile = multipartRequest.getFile("imageSmall");
		if (contentFile != null && !contentFile.isEmpty())
		{
			try {
				webInstance.setUploadedImageSmall(FileUploadBean.getUploadedFile(contentFile));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ModelAndView(view, "operationMsg", "上传文件失败");
			}
		}
		
		//imageLarge
		contentFile = multipartRequest.getFile("imageLarge");
		if (contentFile != null && !contentFile.isEmpty())
		{
			try {
				webInstance.setUploadedImageLarge(FileUploadBean.getUploadedFile(contentFile));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ModelAndView(view, "operationMsg", "上传文件失败");
			}
		}
		
		//image3DSmall
		contentFile = multipartRequest.getFile("image3DSmall");
		if (contentFile != null && !contentFile.isEmpty())
		{
			try {
				webInstance.setUploadedImage3DSmall(FileUploadBean.getUploadedFile(contentFile));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ModelAndView(view, "operationMsg", "上传文件失败");
			}
		}
		
		//image3DLarge
		contentFile = multipartRequest.getFile("image3DLarge");
		if (contentFile != null && !contentFile.isEmpty())
		{
			try {
				webInstance.setUploadedImage3DLarge(FileUploadBean.getUploadedFile(contentFile));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ModelAndView(view, "operationMsg", "上传文件失败");
			}
		}
		
		//bookContent
		contentFile = multipartRequest.getFile("bookContent");
		if (contentFile != null && !contentFile.isEmpty())
		{
			try {
				webInstance.setBookPackage(FileUploadBean.getUploadedFile(contentFile));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ModelAndView(view, "operationMsg", "上传文件失败");
			}
		}
		
		webInstance.setBook(instance);
		webInstance.setOs(null);
		webInstance.setKey(key);
		OperationStatus os = ydkBookMngrService.storeBook(webInstance);
		
		if (!os.getIsSuccessful())
		{
			return new ModelAndView("redirect:listBook.do",
					"operationMsg", "创建失败");
		}
		else
		{
			return new ModelAndView("redirect:listBook.do", 
					"operationMsg", "创建成功");
		}
		
	}
	
	@RequestMapping(value="/addBook.do", method=RequestMethod.POST)
	public ModelAndView addBookPost(ServletWebRequest request) {
		
		
		WebServiceBook webInstance = new WebServiceBook();
		
		//Check uploaded files
		//1. all the images could be null
		//2. all the content should not be null
		
		
		//content file
		MultipartHttpServletRequest multipartRequest =
			(MultipartHttpServletRequest) request.getRequest();
		String checkResult = null;
//		MultipartFile contentFile = multipartRequest.getFile("contentFile");
//		String checkResult = FileUploadBean.validateFile(contentFile);
//		if (checkResult != null)
//		{
//			return new ModelAndView(view, "operationMsg", checkResult);
//		}
//		
//		try {
//			webInstance.setBookPackage(FileUploadBean.getUploadedFile(contentFile));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return new ModelAndView(view, "operationMsg", "上传文件失败");
//		}
		
		
		webInstance.setBookPackages(new ArrayList<UploadedFile>(0));
		//content Images
		
		MultipartFile bookPackage = multipartRequest.getFile("contentFile");
		checkResult = FileUploadBean.validateFile(bookPackage);
		if (checkResult != null)
		{
			return new ModelAndView("errorPage", "errorMsg", checkResult);
			
		}
		try {
			webInstance.getBookPackages().add(FileUploadBean.getUploadedFile(bookPackage));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView("errorPage", "errorMsg", "上传文件失败");
		}

		webInstance.setOs(null);
		OperationStatus os = ydkBookMngrService.storeBook(webInstance);
		
		//Clear the sessioin;
//		request.getRequest().getSession().removeAttribute("typeList");
		
		if (!os.getIsSuccessful())
		{
			return new ModelAndView("redirect:listBook.do",
					"operationMsg", "创建失败");
		}
		else
		{
			return new ModelAndView("redirect:listBook.do", 
					"operationMsg", "创建成功");
		}
		
	}
	
	
	
	
	@RequestMapping(value="/createBook.do", method=RequestMethod.POST)
	public ModelAndView createBookPost(
			@ModelAttribute("dataForm")BookForm dataForm,
			BindingResult result,
			@ModelAttribute("typeList")List<Category> typeList, 
			ModelMap model, ServletWebRequest request) {
		
		String view ="book_create";
		BookValidator.validate(dataForm, result);

		if (result.hasErrors()) {
			return new ModelAndView(view);
		}
		
		WebServiceBook webInstance = new WebServiceBook();
		
		//Check uploaded files
		//1. all the images could be null
		//2. all the content should not be null
		
		
		//content file
		MultipartHttpServletRequest multipartRequest =
			(MultipartHttpServletRequest) request.getRequest();
		String checkResult = null;
//		MultipartFile contentFile = multipartRequest.getFile("contentFile");
//		String checkResult = FileUploadBean.validateFile(contentFile);
//		if (checkResult != null)
//		{
//			return new ModelAndView(view, "operationMsg", checkResult);
//		}
//		
//		try {
//			webInstance.setBookPackage(FileUploadBean.getUploadedFile(contentFile));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return new ModelAndView(view, "operationMsg", "上传文件失败");
//		}
		
		
		String contentCount = request.getParameter("fileCount");
		Integer cCount = 0;
		if (contentCount != null && !contentCount.isEmpty())
		{
			cCount = new Integer(contentCount);
		}
		
		webInstance.setBookPackages(new ArrayList<UploadedFile>(0));
		//content Images
		for (int i=0; i < cCount; i ++)
		{
			MultipartFile bookPackage = multipartRequest.getFile("contentFile" + (i + 1));
			checkResult = FileUploadBean.validateFile(bookPackage);
			if (checkResult != null)
			{
				return new ModelAndView(view, "operationMsg", checkResult);
				
			}
			try {
				webInstance.getBookPackages().add(FileUploadBean.getUploadedFile(bookPackage));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ModelAndView(view, "operationMsg", "上传文件失败");
			}

			
		}
		webInstance.setOs(null);
		OperationStatus os = ydkBookMngrService.storeBook(webInstance);
		
		//Clear the sessioin;
		request.getRequest().getSession().removeAttribute("typeList");
		
		if (!os.getIsSuccessful())
		{
			return new ModelAndView("redirect:listBook.do",
					"operationMsg", "创建失败");
		}
		else
		{
			return new ModelAndView("redirect:listBook.do", 
					"operationMsg", "创建成功");
		}
		
	}
	
	
	@RequestMapping(value="/listBook.do", method=RequestMethod.GET)
	public ModelAndView listBookGet(ServletWebRequest request) {

		String view ="book_list";

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
			WebBookListResult resultList = ydkBookMngrService.listBooks(params);
			
			if (resultList == null || !resultList.getOs().getIsSuccessful())
			{
				return new ModelAndView("errorPage", "errorMsg","Could not get Books");
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
		BookForm dataForm = new BookForm();
		if (itemId != null && !itemId.isEmpty())
		{
			Long id = new Long(itemId);
			//user try to modify a record;
			WebServiceBook webInstance = ydkBookMngrService.getBookById(id);
			if (webInstance.getOs().getIsSuccessful())
			{
				request.getRequest().getSession().setAttribute("dataSession", webInstance.getBook());
				dataForm.setInstance(webInstance.getBook());
			}
			
			WebServiceListParams params = new WebServiceListParams();
			params.setPageSize(WebServiceListParams.MAX_PAGESIZE);
			params.setPageOffset(0);
			WebBookCateListResult resultList = ydkBookMngrService.getCategories(params);
			
			if (resultList == null || !resultList.getOs().getIsSuccessful())
			{
				return new ModelAndView("errorPage", "errorMsg","无法获得书籍类型");
			}
			if (resultList.getList().size() == 0)
			{
				return new ModelAndView("errorPage", "errorMsg","无法获得书籍类型");
			}
			model.put("typeList", resultList.getList());
			
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
	
	
	@RequestMapping(value="/listBook.do", method=RequestMethod.POST)
	public ModelAndView listBookDelete(@ModelAttribute("dataForm")BookForm dataForm, 
			ServletWebRequest request) {


		for (Long id : dataForm.getKeys())
		{
			ydkBookMngrService.deleteBook(id);
		}
		
		return new ModelAndView("redirect:listBook.do", "operationMsg", "删除成功");
	}
	
	@RequestMapping(value="/modifyBook.do", method=RequestMethod.POST)
	public ModelAndView modifyBookPost(@ModelAttribute("dataForm")BookForm dataForm, 
			BindingResult result, ServletWebRequest request) {


		BookValidator.validate(dataForm, result);
		
		Book dataFormSession = (Book)request.getRequest().getSession().getAttribute("dataSession");

		
		if (result.hasErrors() || dataFormSession == null) {
			return new ModelAndView("redirect:listBook.do", "operationMsg", "修改失败，请检查修改的值");
		}
		dataForm.prepareForModify(dataFormSession);

		WebServiceBook instance = new WebServiceBook(dataFormSession);
		ydkBookMngrService.updateBook(instance);
		
		//Remove dataFormSession from session
		
		
		return new ModelAndView("redirect:listBook.do");
	}
	
	@RequestMapping(value="/tagBook.do", method=RequestMethod.POST)
	public ModelAndView tagBookPost(@ModelAttribute("dataForm")BookForm dataForm, 
			BindingResult result, ServletWebRequest request) {

		
		Long id = dataForm.getInstance().getId();
		//user try to modify a record;
		WebServiceBook webInstance = ydkBookMngrService.getBookById(id);
		if (webInstance.getOs().getIsSuccessful())
		{
			request.getRequest().getSession().setAttribute("dataSession", webInstance.getBook());
			dataForm.setInstance(webInstance.getBook());
		}
		
		if (dataForm.getKeys()!= null && dataForm.getKeys().length > 0)
		{
			webInstance.getBook().setBookTags(new ArrayList<BookTag>(0));
		}
		
		for (Long tagId : dataForm.getKeys())
		{
//			if (webInstance.getBook().getBookTags() == null)
//			{
//				webInstance.getBook().setBookTags(new ArrayList<BookTag>(0));
//			}
			webInstance.getBook().getBookTags().add(new BookTag(tagId));
		}
		
		ydkBookMngrService.updateBook(webInstance);
		
		return new ModelAndView("redirect:viewBook.do?itemId=" + id, "operationMsg", "成功添加栏目");
	}
	
	
	@RequestMapping(value="/searchBooks.do", method=RequestMethod.GET)
	public ModelAndView searchBooksGet(ServletWebRequest request) {

		String view ="book_list";

		
		String page = request.getParameter("page");
		String pageNumber = request.getParameter("pageNumber");
		Map model = new HashMap();
		PagedListHolder pageList = null;
		if (page != null || pageNumber != null)
		{
			pageList = (PagedListHolder) request.getRequest().getSession().getAttribute("pageDataList");
		}
		else
		{
			String keywords = request.getParameter("keywords");
			if (keywords == null)
			{
				return new ModelAndView("errorPage", "errorMsg","请输入关键字");
			}
			try {
				keywords = new String(keywords.getBytes("ISO-8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.put("searchedKeywords", keywords);
			WebServiceListParams params = new WebServiceListParams();
			params.setPageSize(WebServiceListParams.MAX_PAGESIZE);
			params.setPageOffset(0);
			WebBookListResult resultList = 
				ydkBookFreeService.searchBooks(keywords, params);
			
			if (resultList == null || !resultList.getOs().getIsSuccessful())
			{
				return new ModelAndView("errorPage", "errorMsg","无法获得书籍");
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

		BookForm dataForm = new BookForm();
		if (itemId != null && !itemId.isEmpty())
		{
			Long id = new Long(itemId);
			//user try to modify a record;
			WebServiceBook webInstance = ydkBookMngrService.getBookById(id);
			if (webInstance.getOs().getIsSuccessful())
			{
				request.getRequest().getSession().setAttribute("dataSession", webInstance.getBook());
				dataForm.setInstance(webInstance.getBook());
			}
			
			WebServiceListParams params = new WebServiceListParams();
			params.setPageSize(WebServiceListParams.MAX_PAGESIZE);
			params.setPageOffset(0);
			WebBookCateListResult resultList = ydkBookMngrService.getCategories(params);
			
			if (resultList == null || !resultList.getOs().getIsSuccessful())
			{
				return new ModelAndView("errorPage", "errorMsg","无法获得书籍类型");
			}
			if (resultList.getList().size() == 0)
			{
				return new ModelAndView("errorPage", "errorMsg","无法获得书籍类型");
			}
			model.put("typeList", resultList.getList());
			
		}
		model.put("dataForm", dataForm);
		

		
		String operationMsg = request.getParameter("operationMsg");
		if (operationMsg != null && !operationMsg.isEmpty())
		{
			model.put("operationMsg", operationMsg);
		}
		
		if (pageList.getNrOfElements() == 0)
		{
			model.put("operationMsg", "没有匹配的记录");
		}
		
		
		
		request.getRequest().getSession().setAttribute("pageDataList", pageList);
		
		return new ModelAndView(view, model);
	}
	
	
	
	
	
	

	

}
