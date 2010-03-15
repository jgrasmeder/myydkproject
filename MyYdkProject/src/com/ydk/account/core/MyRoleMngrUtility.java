/**
 * 
 */
package com.ydk.account.core;


import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * @author y140zhan
 *
 */
public class MyRoleMngrUtility {
	private LocaleResolver localeResolver = null;
	private MessageSource messageSource = null;
	
	
	@Autowired
	public void setLocaleResolver(LocaleResolver localeResolver) {
	this.localeResolver = localeResolver;
	}
	
	
	@Autowired
	public void setMessageSource(MessageSource messageSource) {
	this.messageSource = messageSource;
	}
	
	private static String MSG_ROLE_GUEST = "account.role.mngr.guest";
	private static String MSG_ROLE_VIP = "account.role.mngr.vip";
	private static String MSG_ROLE_ADMIN = "account.role.mngr.admin";
	
	


//	static public String getRoleMngrGuest(HttpServletRequest request){
//		
//		Locale locale = localeResolver.resolveLocale(request);
//		
//		return (messageSource.getMessage(MSG_ROLE_GUEST, null, locale));
//
//	}
//	
//	static public String getRoleMngrVip(HttpServletRequest request){
//		
//		Locale locale = localeResolver.resolveLocale(request);
//		
//		return (messageSource.getMessage(MSG_ROLE_VIP, null, locale));
//
//	}
//	
//	static public String getRoleMngrAdmin(HttpServletRequest request){
//		
//		Locale locale = localeResolver.resolveLocale(request);
//		
//		return (messageSource.getMessage(MSG_ROLE_ADMIN, null, locale));
//
//	}

}
