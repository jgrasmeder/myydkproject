/**
 * 
 */
package com.ydk.account.webservice;

/**
 * @author y140zhan
 *
 */

import java.util.List;

import javax.activation.DataHandler;

import org.apache.cxf.aegis.type.java5.IgnoreProperty;

import com.ydk.account.persistence.entity.Account;
import com.ydk.account.persistence.entity.UploadedFile;

public final class WebServiceRole {
    private Account account;
    private UploadedFile uploadedFile;
    
    public WebServiceRole() {
    }
    
    public WebServiceRole(Account account, UploadedFile uploadedFile) {
        this.account = account;
        this.uploadedFile = uploadedFile;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
}
