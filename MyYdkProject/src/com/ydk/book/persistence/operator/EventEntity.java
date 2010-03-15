/**
 * 
 */
package com.ydk.book.persistence.operator;

import javax.persistence.EntityManager;

import com.ydk.account.persistence.entity.*;
import com.ydk.book.persistence.entity.Event;
import com.ydk.book.persistence.entity.Interview;
import com.ydk.persistence.EntityOperator;

/**
 * @author y140zhan
 *
 */
public class EventEntity extends EntityOperator<Event> {
	
	public EventEntity(EntityManager em)
	{
		setEntityManager(em);
		validate();
		getInstance();
	}
	
	public EventEntity(EntityManager em, Object id)
	{
		setEntityManager(em);
		validate();
		getInstance();
		loadInstance(id);
	}
	
	public Event loadEntity(Object id)
	{
		return loadInstance(id);
	}
	
	public String remove()
	{
		Event entity = getInstance();
		getEntityManager().refresh(entity);
		return super.remove();
	}

}
