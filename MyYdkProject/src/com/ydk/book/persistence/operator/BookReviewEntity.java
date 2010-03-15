/**
 * 
 */
package com.ydk.book.persistence.operator;

import javax.persistence.EntityManager;

import com.ydk.account.persistence.entity.*;
import com.ydk.book.persistence.entity.BookReview;
import com.ydk.book.persistence.entity.Event;
import com.ydk.book.persistence.entity.Interview;
import com.ydk.persistence.EntityOperator;

/**
 * @author y140zhan
 *
 */
public class BookReviewEntity extends EntityOperator<BookReview> {
	
	public BookReviewEntity(EntityManager em)
	{
		setEntityManager(em);
		validate();
		getInstance();
	}
	
	public BookReviewEntity(EntityManager em, Object id)
	{
		setEntityManager(em);
		validate();
		getInstance();
		loadInstance(id);
	}
	
	public BookReview loadEntity(Object id)
	{
		return loadInstance(id);
	}
	
	public String remove()
	{
		BookReview entity = getInstance();
		getEntityManager().refresh(entity);
		return super.remove();
	}

}
