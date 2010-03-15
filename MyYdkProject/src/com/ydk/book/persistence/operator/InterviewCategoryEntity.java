/**
 * 
 */
package com.ydk.book.persistence.operator;

import javax.persistence.EntityManager;

import com.ydk.account.persistence.entity.*;
import com.ydk.book.persistence.entity.Interview;
import com.ydk.book.persistence.entity.InterviewCategory;
import com.ydk.persistence.EntityOperator;

/**
 * @author y140zhan
 *
 */
public class InterviewCategoryEntity extends EntityOperator<InterviewCategory> {
	
	public InterviewCategoryEntity(EntityManager em)
	{
		setEntityManager(em);
		validate();
		getInstance();
	}
	
	public InterviewCategoryEntity(EntityManager em, Object id)
	{
		setEntityManager(em);
		validate();
		getInstance();
		loadInstance(id);
	}
	
	public InterviewCategory loadEntity(Object id)
	{
		return loadInstance(id);
	}
	
	public String remove()
	{
		InterviewCategory entity = getInstance();
		getEntityManager().refresh(entity);
		return super.remove();
	}

}
