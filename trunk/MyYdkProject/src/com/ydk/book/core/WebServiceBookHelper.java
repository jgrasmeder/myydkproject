/**
 * 
 */
package com.ydk.book.core;

import java.util.ArrayList;
import java.util.List;

import com.ydk.account.webservice.WebServiceAccount;
import com.ydk.book.persistence.entity.Book;
import com.ydk.account.persistence.entity.*;
import com.ydk.book.webservice.WebServiceBook;

/**
 * @author y140zhan
 *
 */
public class WebServiceBookHelper {

	//Wrap book to WebServiceAccount
//	public static WebServiceBook wrapBook(Book book){
//		return new WebServiceBook(book,null, null, null, null, null);
//	}
//
//	
//	public static WebServiceBook wrapBook(Book book, UploadedFile fileSmall,UploadedFile fileLarge,
//			UploadedFile file3DSmall,UploadedFile file3DLarge,UploadedFile fileContent){
//		return new WebServiceBook(book, fileSmall,fileLarge,file3DSmall,file3DLarge,fileContent);
//	}
//	
//	public static List<WebServiceBook> wrapBookList(List<Book> books){
//    	List<WebServiceBook> webBooks = new ArrayList<WebServiceBook>();
//    	for (Book book: books)
//    	{
//    		webBooks.add(new WebServiceBook(book,null, null, null, null, null));
//    	}
//		return webBooks;
//	}
//	
//	
//	public static WebServiceBook wrapUploadedFileSmall(UploadedFile file){
//		return new WebServiceBook(null, file, null, null, null, null);
//	}
//	public static WebServiceBook wrapUploadedFileLarge(UploadedFile file){
//		return new WebServiceBook(null, null,file, null, null, null);
//	}
//	public static WebServiceBook wrapUploadedFile3DSmall(UploadedFile file){
//		return new WebServiceBook(null, null, null,file, null, null);
//	}
//	public static WebServiceBook wrapUploadedFile3DLarge(UploadedFile file){
//		return new WebServiceBook(null, null, null, null, file, null);
//	}
//	public static WebServiceBook wrapUploadedFileContent(UploadedFile file){
//		return new WebServiceBook(null, null, null, null, null, file);
//	}
//	
//	//Strip WebServiceAccount to book
//	public static Book stripBook(WebServiceBook book){
//		return book.getBook();
//	}
//	
//	public static UploadedFile stripUploadedFileSmall(WebServiceBook book){
//		return book.getUploadedFileSmall();
//	}
//	public static UploadedFile stripUploadedFileLarge(WebServiceBook book){
//		return book.getUploadedFileLarge();
//	}
//	
//	public static UploadedFile stripUploadedFile3DSmall(WebServiceBook book){
//		return book.getUploadedFile3DSmall();
//	}
//	
//	public static UploadedFile stripUploaded3DLarge(WebServiceBook book){
//		return book.getUploadedFile3DLarge();
//	}
//	
//	public static UploadedFile stripUploadedFileContent(WebServiceBook book){
//		return book.getUploadedFileContent();
//	}
	
	
	public static List<Book> stripBookList(List<WebServiceBook> books){
    	List<Book> webBooks = new ArrayList<Book>();
    	for (WebServiceBook book: books)
    	{
    		webBooks.add(book.getBook());
    	}
		return webBooks;
	}
	
	
}
