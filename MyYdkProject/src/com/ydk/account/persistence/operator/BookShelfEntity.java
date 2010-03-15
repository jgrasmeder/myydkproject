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
public class BookShelfEntity extends EntityOperator<BookShelf> {
	
	public BookShelfEntity(EntityManager em)
	{
		setEntityManager(em);
		validate();
		getInstance();
	}
	
	public BookShelfEntity(EntityManager em, Object id)
	{
		setEntityManager(em);
		validate();
		getInstance();
		loadInstance(id);
	}
	
	public BookShelf loadEntity(Object id)
	{
		return loadInstance(id);
	}
	
	public String remove()
	{
		BookShelf entity = getInstance();
		getEntityManager().refresh(entity);
		return super.remove();
	}

}
