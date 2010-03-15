/**
 * This class is used to define an interface of all email service for Ydk project.
 */
package com.ydk.service.mail;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

/**
 * @author Zhang Yu
 *
 */
public interface YdkEmailInterface {
	
	/**
	 * Send the message;
	 * @return
	 * @throws MessagingException 
	 * @throws UnsupportedEncodingException 
	 */
	Boolean send(YdkMail ydkMail) throws MessagingException;
	
	YdkMail getYdkMail(String to, String subject, Boolean importance);
	YdkMail getYdkMail();
	
	

}
