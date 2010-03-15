package com.ydk.account.web;


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

import com.ydk.account.persistence.entity.*;
import com.ydk.account.persistence.interfaces.AccountDbMngr;
import com.ydk.account.validator.AccountConstraintsValidator;
import com.ydk.account.validator.AccountValidator;
import com.ydk.account.webservice.YdkAccountService;
import com.ydk.web.WebControllerBase;

/**
 * Process the login request
 *
 * @author Juergen Hoeller
 * @author Ken Krebs
 */
@Controller
@RequestMapping("/accountImg.do")
public class AccountImgController extends WebControllerBase {

	@Autowired
	@Qualifier("ydkAccountServiceClient")
    private YdkAccountService ydkAccountServiceClient;
	
	public void setYdkAccountServiceClient(YdkAccountService ydkAccountServiceClient) {
        this.ydkAccountServiceClient = ydkAccountServiceClient;
    }

	public AccountImgController() {
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
			UploadedFile img = ydkAccountServiceClient.getUploadedFile(imgId).getUploadedFile();
			
	//		request.getResponse().setContentType();
			if (img != null)
			{
				FileCopyUtils.copy(img.getFileContent(), response.getOutputStream());
			}
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(BindingResult result, SessionStatus status, 
			Model model, ServletWebRequest request) {
		
		return "OK";
	}
	


}
