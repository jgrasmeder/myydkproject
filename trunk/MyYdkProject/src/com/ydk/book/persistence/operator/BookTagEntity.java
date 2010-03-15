/**
 * 
 */
package com.ydk.book.persistence.operator;

import javax.persistence.EntityManager;

import com.ydk.account.persistence.entity.*;
import com.ydk.book.persistence.entity.BookTag;
import com.ydk.book.persistence.entity.Interview;
import com.ydk.book.persistence.entity.InterviewCategory;
import com.ydk.persistence.EntityOperator;

/**
 * @author y140zhan
 *
 */
public class BookTagEntity extends EntityOperator<BookTag> {
	
	public BookTagEntity(EntityManager em)
	{
		setEntityManager(em);
		validate();
		getInstance();
	}
	
	public BookTagEntity(EntityManager em, Object id)
	{
		setEntityManager(em);
		validate();
		getInstance();
		loadInstance(id);
	}
	
	public BookTag loadEntity(Object id)
	{
		return loadInstance(id);
	}
	
	public String remove()
	{
		BookTag entity = getInstance();
		return super.remove();
	}

}
