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
public class ReaderEntity extends EntityOperator<Reader> {
	
	public ReaderEntity(EntityManager em)
	{
		setEntityManager(em);
		validate();
		getInstance();
	}
	
	public ReaderEntity(EntityManager em, Object id)
	{
		setEntityManager(em);
		validate();
		getInstance();
		loadInstance(id);
	}
	
	public Reader loadEntity(Object id)
	{
		return loadInstance(id);
	}
	
	public String remove()
	{
		Reader entity = getInstance();
		getEntityManager().refresh(entity);
		return super.remove();
	}

}
