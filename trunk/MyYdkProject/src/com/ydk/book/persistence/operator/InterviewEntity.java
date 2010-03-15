/**
 * 
 */
package com.ydk.book.persistence.operator;

import javax.persistence.EntityManager;

import com.ydk.account.persistence.entity.*;
import com.ydk.book.persistence.entity.Interview;
import com.ydk.persistence.EntityOperator;

/**
 * @author y140zhan
 *
 */
public class InterviewEntity extends EntityOperator<Interview> {
	
	public InterviewEntity(EntityManager em)
	{
		setEntityManager(em);
		validate();
		getInstance();
	}
	
	public InterviewEntity(EntityManager em, Object id)
	{
		setEntityManager(em);
		validate();
		getInstance();
		loadInstance(id);
	}
	
	public Interview loadEntity(Object id)
	{
		return loadInstance(id);
	}
	
	public String remove()
	{
		Interview entity = getInstance();
		getEntityManager().refresh(entity);
		return super.remove();
	}

}
