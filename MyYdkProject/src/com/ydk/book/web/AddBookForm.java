package com.ydk.book.web;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;



import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ydk.account.persistence.entity.*;
import com.ydk.book.core.WebServiceBookHelper;
import com.ydk.book.persistence.entity.*;
import com.ydk.book.persistence.interfaces.BookDbMngr;
import com.ydk.book.validator.BookConstraintsValidator;
import com.ydk.book.validator.BookValidator;
import com.ydk.book.webservice.YdkBookService;
import com.ydk.book.core.BookHelper;
import com.ydk.book.core.WebServiceBookHelper;
import com.ydk.book.persistence.entity.Book;
import com.ydk.book.persistence.interfaces.BookDbMngr;
import com.ydk.book.persistence.operator.BookEntity;
import com.ydk.book.validator.BookConstraintsValidator;
import com.ydk.book.validator.BookValidator;
import com.ydk.book.webservice.YdkBookService;

/**
 * JavaBean form controller that is used to add a new <code>Owner</code> to
 * the system.
 *
 * @author Juergen Hoeller
 * @author Ken Krebs
 */
@Controller
@RequestMapping("/addBookOld.do")
@SessionAttributes({"book","bookHelper"})
@Transactional
public class AddBookForm {

	@Autowired
	@Qualifier("ydkBookServiceClient")
	private YdkBookService ydkBookServiceClient;
	
	public void setYdkBookServiceClient(YdkBookService ydkBookServiceClient) {
        this.ydkBookServiceClient = ydkBookServiceClient;
    }
	
	


	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(ModelMap model) {
		Book book = new Book();
		BookHelper bookHelper = new BookHelper();
		bookHelper.setBook(book);
		model.addAttribute("bookHelper", bookHelper);
		return "bookForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("bookHelper") BookHelper bookHelper, 
			BindingResult result, SessionStatus status, ServletWebRequest request) 
		throws DataAccessException, IOException {
		//First we check the given parameters
		//Then we perform store;
		
		if (! (request.getRequest() instanceof MultipartHttpServletRequest)) 
		{
			
			return null;
		}
		MultipartHttpServletRequest multipartRequest =
		(MultipartHttpServletRequest) request.getRequest();
		MultipartFile fileSmall = multipartRequest.getFile("uploadedFileSmall");
		bookHelper.getFileUploadBeanSmall().setFile(fileSmall);
		
		MultipartFile fileLarge = multipartRequest.getFile("uploadedFileLarge");
		bookHelper.getFileUploadBeanLarge().setFile(fileLarge);
		MultipartFile file3DSmall = multipartRequest.getFile("uploadedFile3DSmall");
		bookHelper.getFileUploadBean3DSmall().setFile(file3DSmall);
		MultipartFile file3DLarge = multipartRequest.getFile("uploadedFile3DLarge");
		bookHelper.getFileUploadBean3DLarge().setFile(file3DLarge);
		MultipartFile fileContent = multipartRequest.getFile("uploadedFileContent");
		bookHelper.getFileUploadBeanContent().setFile(fileContent);
		
//		//String name = file.getName();
//		String name = file.getOriginalFilename();
//		String type = file.getContentType();
//		Long size = (Long) file.getSize();
//		//bookHelper.getFileUploadBean().setFile(file);
//		//name = file.getOriginalFilename();
//		
		
		
		//Check
		BookValidator.validate(bookHelper.getBook(), result);
		if (result.hasErrors()) {
			return "bookForm";
		}
		BookConstraintsValidator.validate(bookHelper.getBook(),
				result, ydkBookServiceClient);
		
		if (result.hasErrors()) {
			return "bookForm";
		}
		else {
			//Finish checking, do the business
			
			//First we need to check if there is a image. 
			UploadedFile uploadedFileSmall = null;
			if (bookHelper.getFileUploadBeanSmall().getFile() != null)
			{
				//Store the upload image;
				uploadedFileSmall = new UploadedFile(
						bookHelper.getFileUploadBeanSmall().getFile().getOriginalFilename(),
						bookHelper.getFileUploadBeanSmall().getFile().getContentType(), 
						bookHelper.getFileUploadBeanSmall().getFile().getSize(),
						bookHelper.getFileUploadBeanSmall().getFile().getName(), 
						(bookHelper.getFileUploadBeanSmall().getFile().getBytes()));
				
			}
			
			UploadedFile uploadedFileLarge = null;
			if (bookHelper.getFileUploadBeanLarge().getFile() != null)
			{
				//Store the upload image;
				uploadedFileLarge = new UploadedFile(
						bookHelper.getFileUploadBeanLarge().getFile().getOriginalFilename(),
						bookHelper.getFileUploadBeanLarge().getFile().getContentType(), 
						bookHelper.getFileUploadBeanLarge().getFile().getSize(),
						bookHelper.getFileUploadBeanLarge().getFile().getName(), 
						(bookHelper.getFileUploadBeanLarge().getFile().getBytes()));
				
			}
			UploadedFile uploadedFile3DSmall = null;
			if (bookHelper.getFileUploadBean3DSmall().getFile() != null)
			{
				//Store the upload image;
				uploadedFile3DSmall = new UploadedFile(
						bookHelper.getFileUploadBean3DSmall().getFile().getOriginalFilename(),
						bookHelper.getFileUploadBean3DSmall().getFile().getContentType(), 
						bookHelper.getFileUploadBean3DSmall().getFile().getSize(),
						bookHelper.getFileUploadBean3DSmall().getFile().getName(), 
						(bookHelper.getFileUploadBean3DSmall().getFile().getBytes()));
				
			}
			UploadedFile uploadedFile3DLarge = null;
			if (bookHelper.getFileUploadBean3DLarge().getFile() != null)
			{
				//Store the upload image;
				uploadedFile3DLarge = new UploadedFile(
						bookHelper.getFileUploadBean3DLarge().getFile().getOriginalFilename(),
						bookHelper.getFileUploadBean3DLarge().getFile().getContentType(), 
						bookHelper.getFileUploadBean3DLarge().getFile().getSize(),
						bookHelper.getFileUploadBean3DLarge().getFile().getName(), 
						(bookHelper.getFileUploadBean3DLarge().getFile().getBytes()));
				
			}
			UploadedFile uploadedFileContent = null;
			if (bookHelper.getFileUploadBeanContent().getFile() != null)
			{
				//Store the upload image;
				uploadedFileContent = new UploadedFile(
						bookHelper.getFileUploadBeanContent().getFile().getOriginalFilename(),
						bookHelper.getFileUploadBeanContent().getFile().getContentType(), 
						bookHelper.getFileUploadBeanContent().getFile().getSize(),
						bookHelper.getFileUploadBeanContent().getFile().getName(), 
						(bookHelper.getFileUploadBeanContent().getFile().getBytes()));
				
			}
			
//			ydkBookServiceClient.saveBook(
//					WebServiceBookHelper.wrapBook(bookHelper.getPreparedBook(),
//							uploadedFileSmall,uploadedFileLarge,uploadedFile3DSmall,
//							uploadedFile3DLarge,uploadedFileContent));
			
			
//			try {
//				mailService.send(mailService.getYdkMail(bookHelper.getBook().getEmail(), "Hello SrpingEmail", true));
//			} catch (MessagingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			status.setComplete();
			return "redirect:book.do?bookId=" + bookHelper.getBook().getId();
		}
	}

}
