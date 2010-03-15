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
import com.ydk.book.webservice.WebServiceFile;
import com.ydk.book.webservice.YdkFrontEndWebService;
import com.ydk.web.WebControllerBase;

/**
 * Process the login request
 *
 * @author Juergen Hoeller
 * @author Ken Krebs
 */
@Controller
public class YdkFileController extends WebControllerBase {
	
	@Autowired
	@Qualifier("ydkFrontEndServiceClient")
    protected YdkFrontEndWebService ydkFrontEndWebService;
	
	public void setYdkFrontEndWebService(@Qualifier("ydkFrontEndServiceClient")YdkFrontEndWebService service) {
        this.ydkFrontEndWebService = service;
    }


	
	@RequestMapping(value="/ydkGetImage.do", method = RequestMethod.GET)
	public void getImage(ModelMap model, ServletWebRequest request, HttpServletResponse response) throws IOException, SQLException {
		
		String fileName = ((String)request.getParameter("fileName"));
		
		
		//TODO use cache here;
		if (fileName != null && !fileName.isEmpty())
		{
			WebServiceFile file = ydkFrontEndWebService.getFileByName(fileName);
				
	//		request.getResponse().setContentType();
			if (file != null && file.getOs().getIsSuccessful())
			{
				FileCopyUtils.copy(file.getFile().getFileContent(), response.getOutputStream());
			}
		}
	}
	
	@RequestMapping(value="/ydkGetContent.do", method = RequestMethod.GET)
	public void getContent(ModelMap model, ServletWebRequest request, HttpServletResponse response) throws IOException, SQLException {
		
		String fileName = ((String)request.getParameter("fileName"));
		
		
		//TODO use cache here;
		if (fileName != null && !fileName.isEmpty())
		{
			WebServiceFile file = ydkFrontEndWebService.getFileByName(fileName);
				
	//		request.getResponse().setContentType();
			if (file != null && file.getOs().getIsSuccessful())
			{
				StringBuffer stringBuffer = new StringBuffer();
				String temp = new String(file.getFile().getFileContent(), "utf-8");
				FileCopyUtils.copy(temp.getBytes(), response.getOutputStream());
			}
		}
	}

	


}
