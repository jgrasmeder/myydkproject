/**
 * This Class is used to do some validation and define some configuration for Email
 */
package com.ydk.service.mail;

import org.apache.commons.validator.EmailValidator;

/**
 * @author y140zhan
 *
 */
public class EmailUtility {
	
	public final static String EMAIL_DEFAULT_CHARSET = "UTF-8";
	
	public final static String EMAIL_DEFAULT_MIMETYPE = "text/html";
	
	public final static String EMAIL_DEFAULT_FROMADDR = "yu.zhang@nsn.com";
	
	public final static String EMAIL_DEFAULT_FROMNAME = "Ydk";
	
	public static boolean isValid(String address) {
        return EmailValidator.getInstance().isValid(address);
    }

}
