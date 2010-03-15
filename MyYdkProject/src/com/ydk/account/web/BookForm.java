/**
 * 
 */
package com.ydk.account.web;





import com.ydk.account.persistence.entity.Category;
import com.ydk.book.persistence.entity.Author;
import com.ydk.book.persistence.entity.Book;
import com.ydk.book.persistence.entity.BookReviewCategory;
import com.ydk.book.persistence.entity.Event;
import com.ydk.book.persistence.entity.EventCategory;
import com.ydk.book.persistence.entity.Translator;


/**
 * @author y140zhan
 *
 */
public class BookForm {
	

	private Book instance;

	private Long[] keys ;
	
	/**
	 * 
	 */
	public BookForm() {
		super();
		instance = new Book();
		instance.setCategory(new Category());
		if (instance.getAuthor() == null)
		{
			instance.setAuthor(new Author());
		}
		if (instance.getAuthor1() == null)
		{
			instance.setAuthor1(new Author());
		}
		if (instance.getAuthor2() == null)
		{
			instance.setAuthor2(new Author());
		}
		if (instance.getAuthor3() == null)
		{
			instance.setAuthor3(new Author());
		}
		
		if (instance.getTranslator() == null)
		{
			instance.setTranslator(new Translator());
		}
		if (instance.getTranslator1() == null)
		{
			instance.setTranslator1(new Translator());
		}
		if (instance.getTranslator2() == null)
		{
			instance.setTranslator2(new Translator());
		}
		if (instance.getTranslator3() == null)
		{
			instance.setTranslator3(new Translator());
		}
		// TODO Auto-generated constructor stub
	}
	
	public BookForm(Book book) {
		super();
		instance = new Book();
		instance.cloneBook(book);
		
		if (instance.getCategory() == null)
			instance.setCategory(new Category());
		if (instance.getAuthor() == null)
		{
			instance.setAuthor(new Author());
		}
		if (instance.getAuthor1() == null)
		{
			instance.setAuthor1(new Author());
		}
		if (instance.getAuthor2() == null)
		{
			instance.setAuthor2(new Author());
		}
		if (instance.getAuthor3() == null)
		{
			instance.setAuthor3(new Author());
		}
		
		if (instance.getTranslator() == null)
		{
			instance.setTranslator(new Translator());
		}
		if (instance.getTranslator1() == null)
		{
			instance.setTranslator1(new Translator());
		}
		if (instance.getTranslator2() == null)
		{
			instance.setTranslator2(new Translator());
		}
		if (instance.getTranslator3() == null)
		{
			instance.setTranslator3(new Translator());
		}
	}
	
    

	/**
	 * @param instance
	 * @param keys
	 */
	public BookForm(Book instance, Long[] keys) {
		super();
		this.instance = instance;
		this.keys = keys;
	}
	

	/**
	 * @return the instance
	 */
	public Book getInstance() {
		return instance;
	}

	/**
	 * @param instance the instance to set
	 */
	public void setInstance(Book book) {
		instance = new Book();
		instance.cloneBook(book);
		
		if (instance.getCategory() == null)
			instance.setCategory(new Category());
		if (instance.getAuthor() == null)
		{
			instance.setAuthor(new Author());
		}
		if (instance.getAuthor1() == null)
		{
			instance.setAuthor1(new Author());
		}
		if (instance.getAuthor2() == null)
		{
			instance.setAuthor2(new Author());
		}
		if (instance.getAuthor3() == null)
		{
			instance.setAuthor3(new Author());
		}
		
		if (instance.getTranslator() == null)
		{
			instance.setTranslator(new Translator());
		}
		if (instance.getTranslator1() == null)
		{
			instance.setTranslator1(new Translator());
		}
		if (instance.getTranslator2() == null)
		{
			instance.setTranslator2(new Translator());
		}
		if (instance.getTranslator3() == null)
		{
			instance.setTranslator3(new Translator());
		}
		
		
	}


	/**
	 * @return the keys
	 */
	public Long[] getKeys() {
		return keys;
	}

	/**
	 * @param keys the keys to set
	 */
	public void setKeys(Long[] keys) {
		this.keys = keys;
	}
	
	public void prepareForModify(Book instance)
	{
		instance.setTitle(this.instance.getTitle());
		if (this.instance.getCategory() != null 
				&& this.instance.getCategory().getId() != null)
			instance.setCategory(this.instance.getCategory());
		if (this.instance.getAuthor() != null 
				&& this.instance.getAuthor().getId() != null)
		{
			instance.setAuthor(this.instance.getAuthor());
		}
		if (this.instance.getAuthor1() != null 
				&& this.instance.getAuthor1().getId() != null)
		{
			instance.setAuthor1(this.instance.getAuthor1());
		}
		if (this.instance.getAuthor2() != null 
				&& this.instance.getAuthor2().getId() != null)
		{
			instance.setAuthor2(this.instance.getAuthor2());
		}
		if (this.instance.getAuthor3() != null 
				&& this.instance.getAuthor3().getId() != null)
		{
			instance.setAuthor3(this.instance.getAuthor3());
		}
		
		if (this.instance.getTranslator() != null 
				&& this.instance.getTranslator().getId() != null)
		{
			instance.setTranslator(this.instance.getTranslator());
		}
		if (this.instance.getTranslator1() != null 
				&& this.instance.getTranslator1().getId() != null)
		{
			instance.setTranslator1(this.instance.getTranslator1());
		}
		if (this.instance.getTranslator2() != null 
				&& this.instance.getTranslator2().getId() != null)
		{
			instance.setTranslator2(this.instance.getTranslator2());
		}
		if (this.instance.getTranslator3() != null 
				&& this.instance.getTranslator3().getId() != null)
		{
			instance.setTranslator3(this.instance.getTranslator3());
		}
//		instance.setTitle(this.instance.getTitle());
//		instance.setContent(this.instance.getContent());

		
	}


	
	
	
	
	

}
