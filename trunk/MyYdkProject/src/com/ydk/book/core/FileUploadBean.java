/**
 * 
 */
package com.ydk.book.core;

import org.springframework.web.multipart.MultipartFile;

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
}
