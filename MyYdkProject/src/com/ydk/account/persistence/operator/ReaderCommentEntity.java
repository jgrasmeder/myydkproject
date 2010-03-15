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
public class ReaderCommentEntity extends EntityOperator<ReaderComment> {
	
	public ReaderCommentEntity(EntityManager em)
	{
		setEntityManager(em);
		validate();
		getInstance();
	}
	
	public ReaderCommentEntity(EntityManager em, Object id)
	{
		setEntityManager(em);
		validate();
		getInstance();
		loadInstance(id);
	}
	
	public ReaderComment loadEntity(Object id)
	{
		return loadInstance(id);
	}
	
	public String remove()
	{
		ReaderComment entity = getInstance();
		getEntityManager().refresh(entity);
		return super.remove();
	}

}
