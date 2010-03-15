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
public class EvaluationEntity extends EntityOperator<Evaluation> {
	
	public EvaluationEntity(EntityManager em)
	{
		setEntityManager(em);
		validate();
		getInstance();
	}
	
	public EvaluationEntity(EntityManager em, Object id)
	{
		setEntityManager(em);
		validate();
		getInstance();
		loadInstance(id);
	}
	
	public Evaluation loadEntity(Object id)
	{
		return loadInstance(id);
	}
	
	public String remove()
	{
		Evaluation entity = getInstance();
		getEntityManager().refresh(entity);
		return super.remove();
	}

}
