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
import com.ydk.book.persistence.entity.Translator;


public final class WebServiceTranslator implements java.io.Serializable {
    private Translator translator;
    private OperationStatus os;
    
    
	

	public WebServiceTranslator() {
		// TODO Auto-generated constructor stub
		translator = null;
		os = new OperationStatus();
	}
    
	/**
	 * @param translator
	 * @param os
	 */
	public WebServiceTranslator(Translator translator) {
		this.translator = translator;
		this.os = new OperationStatus();
	}

	

	/**
	 * @return the translator
	 */
	public Translator getTranslator() {
		return translator;
	}

	/**
	 * @param translator the translator to set
	 */
	public void setTranslator(Translator translator) {
		this.translator = translator;
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
	
	
	public static WebServiceTranslator wrapTranslator(Translator translator){
	return new WebServiceTranslator(translator);
}

	public static List<WebServiceTranslator> wrapTranslatorList(List<Translator> translators){
	List<WebServiceTranslator> webTranslators = new ArrayList<WebServiceTranslator>();
	for (Translator translator: translators)
	{
		webTranslators.add(new WebServiceTranslator(translator));
	}
	return webTranslators;
}

	public static Translator stripTranslator(WebServiceTranslator translator){
		return translator.getTranslator();
	}
	public static List<Translator> stripTranslatorList(List<WebServiceTranslator> translators){
    	List<Translator> webTranslators = new ArrayList<Translator>();
    	for (WebServiceTranslator translator: translators)
    	{
    		webTranslators.add(translator.getTranslator());
    	}
		return webTranslators;
	}
    
}
