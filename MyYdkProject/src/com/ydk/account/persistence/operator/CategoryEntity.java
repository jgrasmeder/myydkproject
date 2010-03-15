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
public class CategoryEntity extends EntityOperator<Category> {
	
	public CategoryEntity(EntityManager em)
	{
		setEntityManager(em);
		validate();
		getInstance();
	}
	
	public CategoryEntity(EntityManager em, Object id)
	{
		setEntityManager(em);
		validate();
		getInstance();
		loadInstance(id);
	}
	
	public Category loadEntity(Object id)
	{
		return loadInstance(id);
	}
	
	public String remove()
	{
		Category entity = getInstance();
		getEntityManager().refresh(entity);
		return super.remove();
	}

}
