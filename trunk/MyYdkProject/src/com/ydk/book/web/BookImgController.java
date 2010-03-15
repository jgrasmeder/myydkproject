package com.ydk.book.web;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ydk.book.persistence.entity.*;
import com.ydk.account.persistence.entity.*;
import com.ydk.book.persistence.interfaces.BookDbMngr;
import com.ydk.book.validator.BookConstraintsValidator;
import com.ydk.book.validator.BookValidator;
import com.ydk.book.webservice.YdkBookService;
import com.ydk.web.WebControllerBase;

/**
 * Process the login request
 *
 * @author Juergen Hoeller
 * @author Ken Krebs
 */
@Controller
@RequestMapping("/bookImg.do")
public class BookImgController extends WebControllerBase {

	@Autowired
	@Qualifier("ydkBookServiceClient")
    private YdkBookService ydkBookServiceClient;
	
	public void setYdkBookServiceClient(YdkBookService ydkBookServiceClient) {
        this.ydkBookServiceClient = ydkBookServiceClient;
    }

	public BookImgController() {
		this.thisView = "login";
		this.fromView = null;
		this.errorView = "login";
		this.successView = "welcome";
		this.successViewRedirect = successView + ".do";
		this.homeView = "welcome.do";
	}
	
	

	@RequestMapping(method = RequestMethod.GET)
	public void setupForm(ModelMap model, ServletWebRequest request, HttpServletResponse response) throws IOException, SQLException {
		Long imgId = new Long((String)request.getParameter("imgId"));		
		if (imgId != null)
		{
			UploadedFile img = ydkBookServiceClient.getUploadedFile(imgId);			

			if (img != null)
			{
				FileCopyUtils.copy(img.getFileContent(), response.getOutputStream());
			}
		}
		
		
//		Long imgIdSmall = new Long((String)request.getParameter("imgIdSmall"));		
//		if (imgIdSmall != null)
//		{
//			UploadedFile imgSmall = ydkBookServiceClient.getUploadedFile(imgIdSmall);			
//
//			if (imgSmall != null)
//			{
//				FileCopyUtils.copy(imgSmall.getFileContent(), response.getOutputStream());
//			}
//		}
//		
//		Long imgIdLarge = new Long((String)request.getParameter("imgIdLarge"));		
//		if (imgIdLarge != null)
//		{
//			UploadedFile imgLarge = ydkBookServiceClient.getUploadedFile(imgIdLarge);			
//
//			if (imgLarge!= null)
//			{
//				FileCopyUtils.copy(imgLarge.getFileContent(), response.getOutputStream());
//			}
//		}
//		Long imgId3DSmall = new Long((String)request.getParameter("imgId3DSmall"));		
//		if (imgId3DSmall != null)
//		{
//			UploadedFile img3DSmall = ydkBookServiceClient.getUploadedFile(imgId3DSmall);			
//
//			if (img3DSmall != null)
//			{
//				FileCopyUtils.copy(img3DSmall.getFileContent(), response.getOutputStream());
//			}
//		}
//		Long imgId3DLarge = new Long((String)request.getParameter("imgId3DLarge"));		
//		if (imgId3DLarge != null)
//		{
//			UploadedFile img3DLarge = ydkBookServiceClient.getUploadedFile(imgId3DLarge);			
//
//			if (img3DLarge != null)
//			{
//				FileCopyUtils.copy(img3DLarge.getFileContent(), response.getOutputStream());
//			}
//		}
//		
//		Long contentId = new Long((String)request.getParameter("contentId"));		
//		if (contentId != null)
//		{
//			UploadedFile bookContent = ydkBookServiceClient.getUploadedFile(contentId);			
//
//			if (bookContent != null)
//			{
//				FileCopyUtils.copy(bookContent.getFileContent(), response.getOutputStream());
//			}
//		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(BindingResult result, SessionStatus status, 
			Model model, ServletWebRequest request) {
		
		return "OK";
	}
	


}
