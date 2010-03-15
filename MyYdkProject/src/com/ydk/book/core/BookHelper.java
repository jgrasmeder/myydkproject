/**
 * 
 */
package com.ydk.book.core;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.fileupload.FileItem;
import org.hibernate.lob.BlobImpl;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ydk.book.core.FileUploadBean;
import com.ydk.book.persistence.entity.Book;
import com.ydk.account.persistence.entity.*;


/**
 * @author y140zhan
 *
 */
public class BookHelper {
	
	private Book book = null;
	private FileUploadBean fileUploadBeanSmall = new FileUploadBean();
	private FileUploadBean fileUploadBeanLarge = new FileUploadBean();
	private FileUploadBean fileUploadBean3DSmall = new FileUploadBean();
	private FileUploadBean fileUploadBean3DLarge = new FileUploadBean();
	private FileUploadBean fileUploadBeanContent = new FileUploadBean();
	
	public void setBook(Book book)
	{
		this.book = book;
	}
	
	public Book getBook()
	{
		return book;
	}
	
//	public void setFileUploadBean (FileUploadBean fileUploadBean)
//	{
//		this.fileUploadBean = fileUploadBean;
//	}
//	
//	public FileUploadBean getFileUploadBean ()
//	{
//		return fileUploadBean;
//	}
	
	public Book getPreparedBook() throws IOException
	{
//		if (fileUploadBeanSmall.getFile() != null)		{
//
//			FileDescriptor imageSmall = new FileDescriptor(fileUploadBeanSmall.getFile().getOriginalFilename(),
//					fileUploadBeanSmall.getFile().getContentType(), fileUploadBeanSmall.getFile().getSize(),
//					fileUploadBeanSmall.getFile().getName(), (null));
//			book.setImageSmall(imageSmall);
//
//		}
//		if (fileUploadBeanLarge.getFile() != null)		{
//
//			FileDescriptor imageLarge = new FileDescriptor(fileUploadBeanLarge.getFile().getOriginalFilename(),
//					fileUploadBeanLarge.getFile().getContentType(), fileUploadBeanLarge.getFile().getSize(),
//					fileUploadBeanLarge.getFile().getName(), (null));
//			book.setImageLarge(imageLarge);
//
//		}
//		if (fileUploadBean3DSmall.getFile() != null)		{
//
//			FileDescriptor image3DSmall = new FileDescriptor(fileUploadBean3DSmall.getFile().getOriginalFilename(),
//					fileUploadBean3DSmall.getFile().getContentType(), fileUploadBean3DSmall.getFile().getSize(),
//					fileUploadBean3DSmall.getFile().getName(), (null));
//			book.setImage3DSmall(image3DSmall);
//
//		}
//		if (fileUploadBean3DLarge.getFile() != null)		{
//
//			FileDescriptor image3DLarge = new FileDescriptor(fileUploadBean3DLarge.getFile().getOriginalFilename(),
//					fileUploadBean3DLarge.getFile().getContentType(), fileUploadBean3DLarge.getFile().getSize(),
//					fileUploadBean3DLarge.getFile().getName(), (null));
//			book.setImage3DLarge(image3DLarge);
//
//		}
//		if (fileUploadBeanContent.getFile() != null)		{
//
//			FileDescriptor bookContent = new FileDescriptor(fileUploadBeanContent.getFile().getOriginalFilename(),
//					fileUploadBeanContent.getFile().getContentType(), fileUploadBeanContent.getFile().getSize(),
//					fileUploadBeanContent.getFile().getName(), (null));
//			book.setBookContent(bookContent);
//
//		}
		
		return book;
	}
	
	public void getPreparedOutput() throws IOException, SQLException
	{
		
		if (book != null)
		{
//			CommonsMultipartFile file = 
//				new CommonsMultipartFile((FileItem) book.getImage().getBinaryStream());
//
//			fileUploadBean.setFile(file);
//			book.setImage(new BlobImpl(fileUploadBean.getFile().getInputStream(),
//					(int) fileUploadBean.getFile().getSize()));
		}
	}

	public void setFileUploadBeanSmall(FileUploadBean fileUploadBeanSmall) {
		this.fileUploadBeanSmall = fileUploadBeanSmall;
	}

	public FileUploadBean getFileUploadBeanSmall() {
		return fileUploadBeanSmall;
	}

	public void setFileUploadBeanLarge(FileUploadBean fileUploadBeanLarge) {
		this.fileUploadBeanLarge = fileUploadBeanLarge;
	}

	public FileUploadBean getFileUploadBeanLarge() {
		return fileUploadBeanLarge;
	}

	public void setFileUploadBean3DSmall(FileUploadBean fileUploadBean3DSmall) {
		this.fileUploadBean3DSmall = fileUploadBean3DSmall;
	}

	public FileUploadBean getFileUploadBean3DSmall() {
		return fileUploadBean3DSmall;
	}

	public void setFileUploadBean3DLarge(FileUploadBean fileUploadBean3DLarge) {
		this.fileUploadBean3DLarge = fileUploadBean3DLarge;
	}

	public FileUploadBean getFileUploadBean3DLarge() {
		return fileUploadBean3DLarge;
	}

	public void setFileUploadBeanContent(FileUploadBean fileUploadBeanContent) {
		this.fileUploadBeanContent = fileUploadBeanContent;
	}

	public FileUploadBean getFileUploadBeanContent() {
		return fileUploadBeanContent;
	}

}
