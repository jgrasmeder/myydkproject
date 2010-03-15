package com.ydk.account.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.ydk.account.persistence.entity.Account;
import com.ydk.account.persistence.entity.YdkAccount;
import com.ydk.web.SecurityUrlManager;

/**
 * @author Zhang Yu
 * @since Nov. 04 2009
 */
public class SignonInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getServletPath();
		
		/**
		 *		This is for selective security url 
		 *
		 */
		if(SecurityUrlManager.isSecurityNeeded(url))
		{
			YdkAccount userSession = (YdkAccount) WebUtils.getSessionAttribute(request, "userLogIn");
			if (userSession == null) {
				
				String query = request.getQueryString();
				ModelAndView modelAndView = new ModelAndView("login");
				AccountLogin login= new AccountLogin();
				login.setAccountName("请输入用户名");
//				login.setVerificationCode(VerificationManager.getRandomKey());
				modelAndView.addObject("user", login);
				
				if (query != null) {
					modelAndView.addObject("signonForwardAction", url+"?"+query);
					request.setAttribute("myForwardAction", url+"?"+query);
				}
				else {
					modelAndView.addObject("signonForwardAction", url);
					request.setAttribute("myForwardAction", url);
				}
				modelAndView.addObject("operationMsg", "请先登陆");
				throw new ModelAndViewDefiningException(modelAndView);
			}
		}
		
		
		return true;
	}

}
