/**
 * 
 */
package com.ydk.book.persistence.operator;

import javax.persistence.EntityManager;

import com.ydk.account.persistence.entity.*;
import com.ydk.book.persistence.entity.BookNews;
import com.ydk.book.persistence.entity.Event;
import com.ydk.book.persistence.entity.Interview;
import com.ydk.persistence.EntityOperator;

/**
 * @author y140zhan
 *
 */
public class BookNewsEntity extends EntityOperator<BookNews> {
	
	public BookNewsEntity(EntityManager em)
	{
		setEntityManager(em);
		validate();
		getInstance();
	}
	
	public BookNewsEntity(EntityManager em, Object id)
	{
		setEntityManager(em);
		validate();
		getInstance();
		loadInstance(id);
	}
	
	public BookNews loadEntity(Object id)
	{
		return loadInstance(id);
	}
	
	public String remove()
	{
		BookNews entity = getInstance();
		getEntityManager().refresh(entity);
		return super.remove();
	}

}
