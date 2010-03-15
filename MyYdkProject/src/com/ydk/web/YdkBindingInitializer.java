package com.ydk.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.ydk.account.webservice.YdkAccountService;

/**
 * Shared WebBindingInitializer for PetClinic's custom editors.
 *
 * <p>Alternatively, such init-binder code may be put into
 * {@link org.springframework.web.bind.annotation.InitBinder}
 * annotated methods on the controller classes themselves.
 *
 * @author Juergen Hoeller
 */
public class YdkBindingInitializer implements WebBindingInitializer {

	/**
	 * Maybe we need to access the Database
	 */
//	@Autowired
//	@Qualifier("ydkAccountServiceClient")
//    private YdkAccountService ydkAccountServiceClient;
	
//	public void setYdkAccountServiceClient(YdkAccountService ydkAccountServiceClient) {
//        this.ydkAccountServiceClient = ydkAccountServiceClient;
//    }


	public void initBinder(WebDataBinder binder, WebRequest request) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
		binder.registerCustomEditor(Long.class, new CustomNumberEditor(Long.class, true));
		binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true)); 
		binder.registerCustomEditor(Float.class, new CustomNumberEditor(Float.class, true));
		binder.registerCustomEditor(Boolean.class,new CustomBooleanEditor(false));
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}

}
