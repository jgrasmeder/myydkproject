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
import com.ydk.book.persistence.entity.Translator;
import com.ydk.account.persistence.entity.*;
import com.ydk.book.persistence.interfaces.AuthorDbMngr;
import com.ydk.book.persistence.interfaces.BookDbMngr;
import com.ydk.book.persistence.interfaces.TranslatorDbMngr;
import com.ydk.book.webservice.YdkBookService;
/**
 * @author y140zhan
 *
 */

@WebService(endpointInterface = "com.ydk.book.webservice.YdkTranslatorService")
@Transactional
public final class YdkTranslatorServiceImpl implements YdkTranslatorService {

	protected final TranslatorDbMngr translatorDbMngr;
	
	@Autowired
	public YdkTranslatorServiceImpl(TranslatorDbMngr translatorDbMngr) {
		this.translatorDbMngr = translatorDbMngr;
	}

   public List<String> getNameList() {
	    	List<Translator> translators = (List<Translator>) translatorDbMngr.getTranslators();
	    	List<String> nameList = new ArrayList<String>();
	    	
	    	for (Translator translator: translators)
	    	{
	    		nameList.add(translator.getName());
	    	}   	
	    	
	        return nameList;
	    }
	
    public List<WebServiceTranslator> getAllTranslators() {
    	List<Translator> translators = (List<Translator>) translatorDbMngr.getTranslators();
    	
        return WebServiceTranslator.wrapTranslatorList(translators);
    }

	public void deleteTranslatorByName(String translatorName) {
		// TODO Maybe we could user Thread pool here
		translatorDbMngr.deleteTranslatorByName(translatorName);
		
	}

	public void deleteTranslator(Long id) {
		// TODO Auto-generated method stub
		translatorDbMngr.deleteTranslator(id);
	}

	public List<WebServiceTranslator> findTranslators(String translatorName) {
		// TODO Auto-generated method stub
		List<Translator> translators = (List<Translator>) translatorDbMngr.findTranslators(translatorName);
		
		return WebServiceTranslator.wrapTranslatorList(translators);
	}

	public List<WebServiceTranslator> findLimitedTranslators(String translatorName,
			Integer maxNumPerPage) {
		List<Translator> translators = (List<Translator>) translatorDbMngr.findTranslators(translatorName, maxNumPerPage);
		
		return WebServiceTranslator.wrapTranslatorList(translators);
	}

	public WebServiceTranslator getTranslatorByName(String translatorName) {
		
		return WebServiceTranslator.wrapTranslator(translatorDbMngr.getTranslatorByName(translatorName));
	}

	public WebServiceTranslator getTranslator(Long id) {
		// TODO Auto-generated method stub
		return WebServiceTranslator.wrapTranslator(translatorDbMngr.getTranslator(id));
	}

	public List<WebServiceTranslator> getTranslators(Integer maxNumPerPage) {
		// TODO Auto-generated method stub
		List<Translator> translators = 
			(List<Translator>) translatorDbMngr.getTranslators(maxNumPerPage);
		
		return WebServiceTranslator.wrapTranslatorList(translators);
	}
	

	public void saveTranslator(WebServiceTranslator translator) {
		translatorDbMngr.saveTranslator(translator.getTranslator());
		
	}
	public void addTranslator(String name, String nation, 
			String sex, String introduction){
		Translator translator = new Translator();
		translator.setName(name);
//		translator.setNation(nation);
//		translator.setSex(sex);
//		translator.setIntroduction(introduction);
		translatorDbMngr.saveTranslator(translator);		
	}

}
