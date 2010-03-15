/**
 * 
 */
package com.ydk.web;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;

import com.ydk.account.persistence.entity.FileDescriptor;
import com.ydk.account.webservice.WebServiceAccount;
import com.ydk.account.webservice.YdkAccountService;
import com.ydk.webservice.contactus.ContactUsService;


@Controller
public final class webserviceController {
	
	@Autowired
	@Qualifier("contactUsServiceClient")
    private ContactUsService contactUsServiceClient;
	
	@Autowired
	@Qualifier("ydkAccountServiceClient")
    private YdkAccountService ydkAccountServiceClient;
    
    public void setContactUsServiceClient(ContactUsService contactUsServiceClient) {
        this.contactUsServiceClient = contactUsServiceClient;
    }
    public void setYdkAccountServiceClient(YdkAccountService ydkAccountServiceClient) {
        this.ydkAccountServiceClient = ydkAccountServiceClient;
    }
    
    @RequestMapping("/viewmessages.do")
    public ModelMap viewMessages() {
        return new ModelMap("messages", contactUsServiceClient.getMessages());
    }
    
    @RequestMapping("/viewaccounts.do")
    public ModelMap viewAccounts() {
        return new ModelMap("messages", ydkAccountServiceClient.getAllAccounts());
    }
}
