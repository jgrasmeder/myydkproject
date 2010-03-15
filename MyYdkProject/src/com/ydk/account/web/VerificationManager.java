/**
 * 
 */
package com.ydk.account.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author y140zhan
 *
 */
public class VerificationManager {
	

	protected static Map<String, String>	pairs = null;
	protected static List<String>	keys = null;
	
	@Autowired
	public void setCodes(Map<String, String> codes)
	{
		pairs = codes;
		keys = new ArrayList<String>(pairs.keySet());
	}
	
	public static String getRandomKey()
	{
		int i = (int) (Math.floor(Math.random()* pairs.size()));
		return keys.get(i);
	}
	
	public static Boolean validateKey(String key, String value)
	{
		String valueResult = pairs.get(key);
		if (valueResult != null)
		{
			return value.equals(valueResult);
		}
		
		return false;
	}
	

}
