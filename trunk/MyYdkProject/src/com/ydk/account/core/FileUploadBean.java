/**
 * 
 */
package com.ydk.account.core;

import java.io.IOException;

import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

import com.ydk.account.persistence.entity.Account;
import com.ydk.account.persistence.entity.FileDescriptor;
import com.ydk.account.persistence.entity.UploadedFile;

/**
 * @author Zhang Yu
 *
 */
public class FileUploadBean {
	
    private MultipartFile file;

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public MultipartFile getFile() {
        return file;
    }
    
    public static FileDescriptor getFileDescriptor(MultipartFile file)
	{
		if (file != null)
		{
			return new FileDescriptor(file.getOriginalFilename(),
					file.getContentType(), file.getSize(),
					file.getName(), (null));
		}
		return null;
	}
    
    public static UploadedFile getUploadedFile(MultipartFile file) throws IOException
	{
		if (file != null)
		{
			return new UploadedFile(file.getOriginalFilename(),
					file.getContentType(), file.getSize(),
					file.getName(), (file.getBytes()));
		}
		return null;
	}
    
    public static String validateFile(MultipartFile file)
    {
    	if (file == null || file.getOriginalFilename().isEmpty())
    	{
    		return "上传文件不能为空";
    	}
    	//Check the uploaded file size;
		return null;
    	
    }
}
