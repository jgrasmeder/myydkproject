/**
 * 
 */
package com.ydk.service.mail;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Session;


/**
 * @author y140zhan
 *
 */
public class YdkMail implements java.io.Serializable {

    private Boolean importance = false;
    private String fromAddress = EmailUtility.EMAIL_DEFAULT_FROMADDR;
    private String fromName = EmailUtility.EMAIL_DEFAULT_FROMNAME;;
    private String messageMimeType = EmailUtility.EMAIL_DEFAULT_MIMETYPE;
    private String charSet = EmailUtility.EMAIL_DEFAULT_CHARSET;
    private ArrayList<String> toAddresses = new ArrayList<String>();
    private ArrayList<String> ccAddresses = new ArrayList<String>();
    private ArrayList<String> bccAddresses = new ArrayList<String>();
    private String subject = null;
    private String content = null;
	private ArrayList<String> attachments = new ArrayList<String>();
    
    public YdkMail()
    {
    	;
    }
    
    public YdkMail(String to, String subject,Boolean importance)
    {
    	this.toAddresses.add(to);
    	this.subject = subject;
    	this.importance = importance;
    }

	/* (non-Javadoc)
	 * @see com.ydk.service.mail.YdkMail#addBcc(java.lang.String)
	 */
	public void addBcc(String bcc) {
		// TODO Auto-generated method stub
		bccAddresses.add(bcc);
	}
	public List<String> getBcc(){
		return bccAddresses;
	}

	public void addCc(String cc) {
		// TODO Auto-generated method stub
		ccAddresses.add(cc);
	}
	public List<String> getCc(){
		return ccAddresses;
	}

	public void addSendTo(String to) {
		// TODO Auto-generated method stub
		toAddresses.add(to);
	}
	public List<String> getSendTo(){
		return toAddresses;
	}

	public void setImportance(Boolean importance) {
		// TODO Auto-generated method stub
		this.importance = importance;
	}
	
	public Boolean getImportance(){
		return importance;
	}

	public void setSubject(String subject) {
		// TODO Auto-generated method stub
		this.subject = subject;
	}
	
	public String getSubject() {
		// TODO Auto-generated method stub
		return subject;
	}

	public void setFromAddress(String fromAddress) {
		// TODO Auto-generated method stub
		this.fromAddress = fromAddress;
	}
	
	public String getFromAddress() {
		// TODO Auto-generated method stub
		return fromAddress;
	}

	public void setFromName(String fromName) {
		// TODO Auto-generated method stub
		this.fromName = fromName;
	}
	public String getFromName() {
		// TODO Auto-generated method stub
		return fromName;
	}
	
	public void setContent(String content) {
		// TODO Auto-generated method stub
		this.content = content;
	}
	public String getContent() {
		// TODO Auto-generated method stub
		return content;
	}
	
	public void addAttachments(String attachment) {
		// TODO Auto-generated method stub
		this.attachments.add(attachment);
	}
	public List<String> getAttachments(){
		return attachments;
	}
	
	public Boolean isValid() {
		// TODO Auto-generated method stub
		if (toAddresses.isEmpty())
			return false;
		return true;
	}
	
	
}
