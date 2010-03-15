/**
 * 
 */
package com.ydk.book.webservice;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ydk.book.core.WebServiceBookHelper;
import com.ydk.book.persistence.entity.Author;
import com.ydk.book.persistence.entity.Book;
import com.ydk.book.persistence.entity.Publisher;
import com.ydk.account.persistence.entity.*;
import com.ydk.book.persistence.interfaces.AuthorDbMngr;
import com.ydk.book.persistence.interfaces.BookDbMngr;
import com.ydk.book.webservice.YdkBookService;
/**
 * @author y140zhan
 *
 */

@WebService(endpointInterface = "com.ydk.book.webservice.YdkAuthorService")
@Transactional
public final class YdkAuthorServiceImpl implements YdkAuthorService {

	protected final AuthorDbMngr authorDbMngr;
	
	@Autowired
	public YdkAuthorServiceImpl(AuthorDbMngr authorDbMngr) {
		this.authorDbMngr = authorDbMngr;
	}

	
    public List<WebServiceAuthor> getAllAuthors() {
    	List<Author> authors = (List<Author>) authorDbMngr.getAuthors();
    	
        return WebServiceAuthor.wrapAuthorList(authors);
    }
    public List<String> getNameList() {
    	List<Author> authors = (List<Author>) authorDbMngr.getAuthors();
    	List<String> nameList = new ArrayList<String>();
    	
    	for (Author author: authors)
    	{
    		nameList.add(author.getName());
    	}   	
    	
        return nameList;
    }
    
	public void deleteAuthorByName(String authorName) {
		// TODO Maybe we could user Thread pool here
		authorDbMngr.deleteAuthorByName(authorName);
		
	}

	public void deleteAuthor(Long id) {
		// TODO Auto-generated method stub
		authorDbMngr.deleteAuthor(id);
	}

	public List<WebServiceAuthor> findAuthors(String authorName) {
		// TODO Auto-generated method stub
		List<Author> authors = (List<Author>) authorDbMngr.findAuthors(authorName);
		
		return WebServiceAuthor.wrapAuthorList(authors);
	}

	public List<WebServiceAuthor> findLimitedAuthors(String authorName,
			Integer maxNumPerPage) {
		List<Author> authors = (List<Author>) authorDbMngr.findAuthors(authorName, maxNumPerPage);
		
		return WebServiceAuthor.wrapAuthorList(authors);
	}

	public WebServiceAuthor getAuthorByName(String authorName) {
		
		return WebServiceAuthor.wrapAuthor(authorDbMngr.getAuthorByName(authorName));
	}

	public WebServiceAuthor getAuthor(Long id) {
		// TODO Auto-generated method stub
		return WebServiceAuthor.wrapAuthor(authorDbMngr.getAuthor(id));
	}

	public List<WebServiceAuthor> getAuthors(Integer maxNumPerPage) {
		// TODO Auto-generated method stub
		List<Author> authors = 
			(List<Author>) authorDbMngr.getAuthors(maxNumPerPage);
		
		return WebServiceAuthor.wrapAuthorList(authors);
	}
	

	public void saveAuthor(WebServiceAuthor author) {
		authorDbMngr.saveAuthor(author.getAuthor());
		
	}
	public void addAuthor(String name, String nation, 
			String sex, String introduction){
		Author author = new Author();
		author.setName(name);
//		author.setNation(nation);
//		author.setSex(sex);
//		author.setIntroduction(introduction);
		authorDbMngr.saveAuthor(author);		
	}

}
