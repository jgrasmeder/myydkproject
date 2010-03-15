/**
 * 
 */
package com.ydk.epub;

import java.util.Date;
import java.util.Random;

import com.ydk.account.utility.YdkRandomString;
import com.ydk.book.persistence.entity.Author;
import com.ydk.book.persistence.entity.Book;
import com.ydk.book.persistence.entity.Publisher;
import com.ydk.book.persistence.entity.Translator;
import com.ydk.epub.api.ePubClient;
import com.adobe.epubcheck.xml.XMLAttribute;
import com.adobe.epubcheck.xml.XMLElement;

/**
 * @author y1zhao
 *
 */
public class BookEPubClient implements ePubClient {

	private Book book = new Book();
	
	/**
	 * @return the book
	 */
	public Book getBook() {
		
		book.setTitle(YdkRandomString.getRandomString(8));
		book.setAuthor(new Author(YdkRandomString.getRandomString(8)));
		book.setAuthor1(new Author(YdkRandomString.getRandomString(8)));
		book.setAuthor2(new Author(YdkRandomString.getRandomString(8)));
		book.setAuthor3(new Author(YdkRandomString.getRandomString(8)));
		
		book.setTranslator(new Translator(YdkRandomString.getRandomString(8)));
		book.setTranslator1(new Translator(YdkRandomString.getRandomString(8)));
		book.setTranslator2(new Translator(YdkRandomString.getRandomString(8)));
		book.setTranslator3(new Translator(YdkRandomString.getRandomString(8)));
		book.setEdition(YdkRandomString.getRandomString(4));
		book.setNew(true);
		book.setPublisher(new Publisher(YdkRandomString.getRandomString(4)));
		
		book.setPages((new Random()).nextInt(100));
		book.setMarketPrice((new Random()).nextDouble() * 100);
		book.setYdkPaperPrice((new Random()).nextDouble() * 100);
		book.setPaperPrice((new Random()).nextDouble() * 100);
		book.setSelectedEPrice((new Random()).nextDouble() * 100);
		
		book.setPublishDate(new Date());
		
		return book;
	}

	/**
	 * @param book the book to set
	 */
	public void setBook(Book book) {
		this.book = book;
	}

	public void handleItem(String id, String href, String mimeType,
			String fallback, String fallbackStyle, String namespace) {
		
		
		System.out.println("id:"+id+" href:"+href+" mimeType:"+mimeType+" fallback:"+fallback+" fallbackStyle:"+fallbackStyle
				+" namespace:"+namespace);
		
	}

	public void handlePrimaryId(XMLElement e,String idAttr) {

		
		for(int i=0;i<e.getAttributeCount();i++)
		{
			XMLAttribute attr = e.getAttribute(i);
			System.out.println("value:" + attr.getValue()+" getName: "+attr.getName()
					+" getNamespace: "+attr.getNamespace()+" getPrefix: "+attr.getPrefix());			
		}
		
	}

	public void handleAttribute(XMLElement e, String content) {
		System.out.println("element: "+e.getName());
		for(int i=0;i<e.getAttributeCount();i++)
		{
			XMLAttribute attr = e.getAttribute(i);
			System.out.println("value:" + attr.getValue()+" getName: "+attr.getName()
					+" getNamespace: "+attr.getNamespace()+" getPrefix: "+attr.getPrefix());			
		}
		System.out.println(content);				
	}

}
