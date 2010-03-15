/**
 * 
 */
package com.ydk.book.persistence.operator;

import javax.persistence.EntityManager;

import com.ydk.account.persistence.entity.*;
import com.ydk.book.persistence.entity.Publisher;
import com.ydk.persistence.EntityOperator;

/**
 * @author y140zhan
 *
 */
public class PublisherEntity extends EntityOperator<Publisher> {
	
	public PublisherEntity(EntityManager em)
	{
		setEntityManager(em);
		validate();
		getInstance();
	}
	
	public PublisherEntity(EntityManager em, Object id)
	{
		setEntityManager(em);
		validate();
		getInstance();
		loadInstance(id);
	}
	
	public Publisher loadEntity(Object id)
	{
		return loadInstance(id);
	}
	
	public String remove()
	{
		Publisher entity = getInstance();
		getEntityManager().refresh(entity);
		return super.remove();
	}

}
