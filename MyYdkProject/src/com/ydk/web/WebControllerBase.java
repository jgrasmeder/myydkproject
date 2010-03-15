/**
 * 
 */
package com.ydk.web;

/**
 * @author y140zhan
 *
 */
public class WebControllerBase {
	
	protected	String	homeView = null;
	protected	String	fromView = null;
	protected	String	thisView = null;
	protected	String	errorView = null;
	protected	String	successView = null;
	protected	String	successViewRedirect = null;
	
	
	protected void setHomeView(String homeView)
	{
		this.homeView = homeView;
	}
	protected String getHomeView()
	{
		return homeView;
	}
	protected void setFromView(String fromView)
	{
		this.fromView = fromView;
	}
	protected String getFromView()
	{
		return fromView;
	}
	
	protected void setThisView(String thisView)
	{
		this.thisView = thisView;
	}
	protected String getThisView()
	{
		return thisView;
	}
	
	protected void setErrorView(String errorView)
	{
		this.errorView = errorView;
	}
	protected String getErrorView()
	{
		return errorView;
	}
	
	protected void setSuccessView(String successView)
	{
		this.successView = successView;
	}
	protected String getSuccessView()
	{
		return successView;
	}
	
	protected void setSuccessViewRedirect(String successViewRedirect)
	{
		this.successViewRedirect = successViewRedirect;
	}
	protected String getSuccessViewRedirect()
	{
		return successViewRedirect;
	}
}
