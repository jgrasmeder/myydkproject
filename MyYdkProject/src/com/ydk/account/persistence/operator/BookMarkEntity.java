/**
 * 
 */
package com.ydk.account.persistence.operator;

import javax.persistence.EntityManager;

import com.ydk.account.persistence.entity.*;
import com.ydk.persistence.EntityOperator;

/**
 * @author y140zhan
 *
 */
public class BookMarkEntity extends EntityOperator<BookMark> {
	
	public BookMarkEntity(EntityManager em)
	{
		setEntityManager(em);
		validate();
		getInstance();
	}
	
	public BookMarkEntity(EntityManager em, Object id)
	{
		setEntityManager(em);
		validate();
		getInstance();
		loadInstance(id);
	}
	
	public BookMark loadEntity(Object id)
	{
		return loadInstance(id);
	}
	
	public String remove()
	{
		BookMark entity = getInstance();
		getEntityManager().refresh(entity);
		return super.remove();
	}

}
