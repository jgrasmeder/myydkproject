/**
 * 
 */
package com.ydk.account.web;





import com.ydk.book.persistence.entity.BookReviewCategory;
import com.ydk.book.persistence.entity.BookTag;
import com.ydk.book.persistence.entity.Event;
import com.ydk.book.persistence.entity.EventCategory;


/**
 * @author y140zhan
 *
 */
public class BookTagForm {
	

	private BookTag instance;

	private Long[] keys ;
	
	/**
	 * 
	 */
	public BookTagForm() {
		super();
		instance = new BookTag();
		// TODO Auto-generated constructor stub
	}
	
	public BookTagForm(BookTag instance) {
		super();
		this.instance = instance;

	}
	
    

	/**
	 * @param instance
	 * @param keys
	 */
	public BookTagForm(BookTag instance, Long[] keys) {
		super();
		this.instance = instance;
		this.keys = keys;
	}
	

	/**
	 * @return the instance
	 */
	public BookTag getInstance() {
		return instance;
	}

	/**
	 * @param instance the instance to set
	 */
	public void setInstance(BookTag instance) {
		this.instance = instance;
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
	
	public void prepareForModify(BookTag instance)
	{
		instance.setName(this.instance.getName());

		
	}


	
	
	
	
	

}
