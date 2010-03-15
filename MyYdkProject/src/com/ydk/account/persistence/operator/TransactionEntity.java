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
public class TransactionEntity extends EntityOperator<Transaction> {
	
	public TransactionEntity(EntityManager em)
	{
		setEntityManager(em);
		validate();
		getInstance();
	}
	
	public TransactionEntity(EntityManager em, Object id)
	{
		setEntityManager(em);
		validate();
		getInstance();
		loadInstance(id);
	}
	
	public Transaction loadEntity(Object id)
	{
		return loadInstance(id);
	}
	
	public String remove()
	{
		Transaction entity = getInstance();
		getEntityManager().refresh(entity);
		return super.remove();
	}

}
