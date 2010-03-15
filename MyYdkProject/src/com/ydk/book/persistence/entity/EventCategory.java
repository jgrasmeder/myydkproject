/**
 * 
 */
package com.ydk.book.persistence.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 * @author y140zhan
 *
 */
@Entity
@Table(name = "event_category", catalog = "ydk",
		uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class EventCategory extends SubjectCategory implements Serializable {
	
	
	private List<Event> events = new ArrayList<Event>(0);

	public EventCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param introduction
	 */
	public EventCategory(String name, String introduction) {
		super(name, introduction);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param name
	 * @param introduction
	 */
	public EventCategory(String name, String introduction, 
			List<Event> events) {
		super(name, introduction);
		this.events = events;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the events
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "eventCategory", cascade = {CascadeType.REMOVE})
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
	
	@Transient
	public EventCategory setToPlainObject(EventCategory instance)
	{
		//Get self
		getName();
		//Eager get @ManytoOne, @OnetoOne
		
		
		//Null oneToMany;
		setEvents(null);

		return instance;
	}
	
	@Transient
	public EventCategory setToPlainObjectQuick(EventCategory instance)
	{
		//Get self
		getName();
		//Eager get @ManytoOne, @OnetoOne
		
		
		//Null oneToMany;
		setEvents(null);

		return instance;
	}
	
	
	
	
	

}
