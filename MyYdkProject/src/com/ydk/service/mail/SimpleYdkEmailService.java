/**
 * 
 */
package com.ydk.service.mail;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * @author y140zhan
 *
 */
public class SimpleYdkEmailService implements YdkEmailInterface {

	protected final Log logger = LogFactory.getLog(getClass());

    private JavaMailSenderImpl mailSender;
	
    private String mailHost;
    private String userName;
    private String password;
    private String mailPort;
    private boolean useSSL;
    
    private String subjectPrefix = null;
    private String subjectSuffix = null;
    
    
    public void setMailSender(JavaMailSenderImpl mailSender)
    {
    	this.mailSender = mailSender;
    }
    
    public void setSubjectSuffix(String subjectSuffix)
    {
    	this.subjectSuffix = subjectSuffix;
    }
    public void setSubjectPrefix(String subjectPrefix)
    {
    	this.subjectPrefix = subjectPrefix;
    }
    
    public YdkMail getYdkMail() 
	{
		// TODO Auto-generated method stub
		return new YdkMail();
	}

	public YdkMail getYdkMail(String to, String subject,Boolean importance) 
	{
		// TODO Auto-generated method stub
		return new YdkMail( to, subject, importance);
	}


	public Boolean send(YdkMail ydkMail) throws MessagingException{
		// TODO Auto-generated method stub
		if (ydkMail.isValid())
		{
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			message.setTo((String[])(ydkMail.getSendTo().toArray(
					new String[ydkMail.getSendTo().size()])));
			
			if(!ydkMail.getCc().isEmpty())
			{
				message.setCc((String[])(ydkMail.getCc().toArray(
						new String[ydkMail.getCc().size()])));
			}
			if(!ydkMail.getBcc().isEmpty())
			{
				message.setBcc((String[])(ydkMail.getBcc().toArray(
						new String[ydkMail.getBcc().size()])));
			}
			if(!ydkMail.getAttachments().isEmpty())
			{
				;
			}
			try {
				message.setFrom(ydkMail.getFromAddress(), ydkMail.getFromName());
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			message.setSubject(ydkMail.getSubject() + this.subjectSuffix);
			message.setText(ydkMail.getContent() + "</br>Best Regards</br>Ydk.com" 
					+ "</br><a title='visit Ydk' href='http://221.122.79.226:8080/MyYdkProject'><img src='cid:myLogo'></a></br>" 
					+ "You can visit Ydk.com form <a title='visit Ydk' href='http://221.122.79.226:8080/MyYdkProject'>here</a>"
					, true);

		    message.addInline("myLogo", new ClassPathResource("ydk_logo.gif"));
		    
		    logger.debug("send ydkMail to: " + ydkMail.getSendTo());
		    logger.debug("send ydkMail Subject: " + ydkMail.getSubject());
		    mailSender.send(message.getMimeMessage());
		    
		    return true;
		}
		logger.warn("send invalid ydkMail");
		return false;
	}


}
