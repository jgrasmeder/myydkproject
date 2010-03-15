/**
 * 
 */
package com.ydk.book.persistence.operator;

import javax.persistence.EntityManager;

import com.ydk.account.persistence.entity.*;
import com.ydk.book.persistence.entity.EventCategory;
import com.ydk.book.persistence.entity.Interview;
import com.ydk.book.persistence.entity.InterviewCategory;
import com.ydk.persistence.EntityOperator;

/**
 * @author y140zhan
 *
 */
public class EventCategoryEntity extends EntityOperator<EventCategory> {
	
	public EventCategoryEntity(EntityManager em)
	{
		setEntityManager(em);
		validate();
		getInstance();
	}
	
	public EventCategoryEntity(EntityManager em, Object id)
	{
		setEntityManager(em);
		validate();
		getInstance();
		loadInstance(id);
	}
	
	public EventCategory loadEntity(Object id)
	{
		return loadInstance(id);
	}
	
	public String remove()
	{
		EventCategory entity = getInstance();
		getEntityManager().refresh(entity);
		return super.remove();
	}

}
