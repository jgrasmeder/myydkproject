/**
 * 
 */
package com.ydk.account.web;





import com.ydk.book.persistence.entity.BookReviewCategory;
import com.ydk.book.persistence.entity.Event;
import com.ydk.book.persistence.entity.EventCategory;


/**
 * @author y140zhan
 *
 */
public class EventForm {
	

	private Event instance;

	private Long[] keys ;
	
	/**
	 * 
	 */
	public EventForm() {
		super();
		instance = new Event();
		instance.setEventCategory(new EventCategory());
		// TODO Auto-generated constructor stub
	}
	
	public EventForm(Event instance) {
		super();
		this.instance = instance;

	}
	
    

	/**
	 * @param instance
	 * @param keys
	 */
	public EventForm(Event instance, Long[] keys) {
		super();
		this.instance = instance;
		this.keys = keys;
	}
	

	/**
	 * @return the instance
	 */
	public Event getInstance() {
		return instance;
	}

	/**
	 * @param instance the instance to set
	 */
	public void setInstance(Event instance) {
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
	
	public void prepareForModify(Event instance)
	{
		instance.setTitle(this.instance.getTitle());
//		instance.setTitle(this.instance.getTitle());
//		instance.setContent(this.instance.getContent());
		instance.setIntroduction(this.instance.getIntroduction());
		instance.setLocation(this.instance.getLocation());
		instance.setDate(this.instance.getDate());
		instance.setOrder(this.instance.getOrder());
		instance.setRecommanded(this.instance.getRecommanded());
		instance.setIsNew(this.instance.getIsNew());
		
	}


	
	
	
	
	

}
