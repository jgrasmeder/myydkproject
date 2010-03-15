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
public class TransactionTypeEntity extends EntityOperator<TransactionType> {
	
	public TransactionTypeEntity(EntityManager em)
	{
		setEntityManager(em);
		validate();
		getInstance();
	}
	
	public TransactionTypeEntity(EntityManager em, Object id)
	{
		setEntityManager(em);
		validate();
		getInstance();
		loadInstance(id);
	}
	
	public TransactionType loadEntity(Object id)
	{
		return loadInstance(id);
	}
	
	public String remove()
	{
		TransactionType entity = getInstance();
		getEntityManager().refresh(entity);
		return super.remove();
	}

}
