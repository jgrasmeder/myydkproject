/**
 * 
 */
package com.ydk.account.core;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.fileupload.FileItem;
import org.hibernate.lob.BlobImpl;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ydk.account.persistence.entity.Account;
import com.ydk.account.persistence.entity.FileDescriptor;


/**
 * @author y140zhan
 *
 */
public class AccountHelper {
	
	private Account account = null;
	private FileUploadBean fileUploadBean = new FileUploadBean();
	
	public void setAccount(Account account)
	{
		this.account = account;
	}
	
	public Account getAccount()
	{
		return account;
	}
	
	public void setFileUploadBean (FileUploadBean fileUploadBean)
	{
		this.fileUploadBean = fileUploadBean;
	}
	
	public FileUploadBean getFileUploadBean ()
	{
		return fileUploadBean;
	}
	
	public Account getPreparedAccount() throws IOException
	{
		if (fileUploadBean.getFile() != null)
		{
//			FileDescriptor image = new FileDescriptor(fileUploadBean.getFile().getOriginalFilename(),
//					fileUploadBean.getFile().getContentType(), fileUploadBean.getFile().getSize(),
//					fileUploadBean.getFile().getName(), new BlobImpl(fileUploadBean.getFile().getInputStream(),
//							(int) fileUploadBean.getFile().getSize()));
			FileDescriptor image = new FileDescriptor(fileUploadBean.getFile().getOriginalFilename(),
					fileUploadBean.getFile().getContentType(), fileUploadBean.getFile().getSize(),
					fileUploadBean.getFile().getName(), (null));
			account.setImage(image);
//			account.setImage(new BlobImpl(fileUploadBean.getFile().getInputStream(),
//					(int) fileUploadBean.getFile().getSize()));
		}
		return account;
	}
	
	public void getPreparedOutput() throws IOException, SQLException
	{
		
		if (account != null)
		{
//			CommonsMultipartFile file = 
//				new CommonsMultipartFile((FileItem) account.getImage().getBinaryStream());
//
//			fileUploadBean.setFile(file);
//			account.setImage(new BlobImpl(fileUploadBean.getFile().getInputStream(),
//					(int) fileUploadBean.getFile().getSize()));
		}
	}

}
