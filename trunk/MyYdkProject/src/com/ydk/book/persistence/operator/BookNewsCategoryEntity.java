/**
 * 
 */
package com.ydk.book.persistence.operator;

import javax.persistence.EntityManager;

import com.ydk.account.persistence.entity.*;
import com.ydk.book.persistence.entity.BookNewsCategory;
import com.ydk.book.persistence.entity.EventCategory;
import com.ydk.book.persistence.entity.Interview;
import com.ydk.book.persistence.entity.InterviewCategory;
import com.ydk.persistence.EntityOperator;

/**
 * @author y140zhan
 *
 */
public class BookNewsCategoryEntity extends EntityOperator<BookNewsCategory> {
	
	public BookNewsCategoryEntity(EntityManager em)
	{
		setEntityManager(em);
		validate();
		getInstance();
	}
	
	public BookNewsCategoryEntity(EntityManager em, Object id)
	{
		setEntityManager(em);
		validate();
		getInstance();
		loadInstance(id);
	}
	
	public BookNewsCategory loadEntity(Object id)
	{
		return loadInstance(id);
	}
	
	public String remove()
	{
		BookNewsCategory entity = getInstance();
		getEntityManager().refresh(entity);
		return super.remove();
	}

}
