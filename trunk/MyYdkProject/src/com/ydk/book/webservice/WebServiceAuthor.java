/**
 * 
 */
package com.ydk.book.webservice;

/**
 * @author y140zhan
 *
 */

import java.util.ArrayList;
import java.util.List;

import com.ydk.account.persistence.entity.UploadedFile;
import com.ydk.book.persistence.entity.Author;
import com.ydk.book.persistence.entity.Book;


public final class WebServiceAuthor implements java.io.Serializable {
    private Author author;
    private OperationStatus os;
    
    
	

	public WebServiceAuthor() {
		// TODO Auto-generated constructor stub
		author = null;
		os = new OperationStatus();
	}
    
	/**
	 * @param author
	 * @param os
	 */
	public WebServiceAuthor(Author author) {
		this.author = author;
		this.os = new OperationStatus();
	}

	

	/**
	 * @return the author
	 */
	public Author getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(Author author) {
		this.author = author;
	}

	/**
	 * @return the os
	 */
	public OperationStatus getOs() {
		return os;
	}

	/**
	 * @param os the os to set
	 */
	public void setOs(OperationStatus os) {
		this.os = os;
	}
	
	
	public static WebServiceAuthor wrapAuthor(Author author){
	return new WebServiceAuthor(author);
}

	public static List<WebServiceAuthor> wrapAuthorList(List<Author> authors){
	List<WebServiceAuthor> webAuthors = new ArrayList<WebServiceAuthor>();
	for (Author author: authors)
	{
		webAuthors.add(new WebServiceAuthor(author));
	}
	return webAuthors;
}

	public static Author stripAuthor(WebServiceAuthor author){
		return author.getAuthor();
	}
	public static List<Author> stripAuthorList(List<WebServiceAuthor> authors){
    	List<Author> webAuthors = new ArrayList<Author>();
    	for (WebServiceAuthor author: authors)
    	{
    		webAuthors.add(author.getAuthor());
    	}
		return webAuthors;
	}
    
}
