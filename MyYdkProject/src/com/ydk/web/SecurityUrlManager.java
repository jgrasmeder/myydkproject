/**
 * 
 */
package com.ydk.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author y140zhan
 *
 */
public class SecurityUrlManager {
	
	private	static String	url = null;
	
	private static Boolean defaultLoginNeeded = true; 
	protected static Map<String, String>	urls = null;
	protected static Map<String, String>	exceptUrls = null;
	

	public void setUrls(Map<String, String> urls)
	{
		this.urls = urls;
	}

	public  void setExceptUrls(Map<String, String> exceptUrls)
	{
		this.exceptUrls = exceptUrls;
	}
	public void setDefaultLoginNeeded(Boolean defaultLoginNeeded)
	{
		this.defaultLoginNeeded = defaultLoginNeeded;
	}
	
	protected void setUrl(String url)
	{
		this.url = url;
	}
	protected String getUrl()
	{
		return url;
	}
	
	public static Boolean isSecurityNeeded(String urlKey)
	{
		if (exceptUrls.containsKey(urlKey))
		{
			return false;
		}
		if (urls.containsKey(urlKey))
		{
			return true;
		}
		return defaultLoginNeeded;
	}
	

}
