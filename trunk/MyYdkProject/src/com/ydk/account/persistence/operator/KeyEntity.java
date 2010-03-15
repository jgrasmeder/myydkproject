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
public class KeyEntity extends EntityOperator<Key> {
	
	public KeyEntity(EntityManager em)
	{
		setEntityManager(em);
		validate();
		getInstance();
	}
	
	public KeyEntity(EntityManager em, Object id)
	{
		setEntityManager(em);
		validate();
		getInstance();
		loadInstance(id);
	}
	
	public Key loadEntity(Object id)
	{
		return loadInstance(id);
	}
	
	public String remove()
	{
		Key entity = getInstance();
		getEntityManager().refresh(entity);
		return super.remove();
	}

}
