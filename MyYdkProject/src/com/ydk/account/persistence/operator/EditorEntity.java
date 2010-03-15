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
public class EditorEntity extends EntityOperator<Editor> {
	
	public EditorEntity(EntityManager em)
	{
		setEntityManager(em);
		validate();
		getInstance();
	}
	
	public EditorEntity(EntityManager em, Object id)
	{
		setEntityManager(em);
		validate();
		getInstance();
		loadInstance(id);
	}
	
	public Editor loadEntity(Object id)
	{
		return loadInstance(id);
	}
	
	public String remove()
	{
		Editor entity = getInstance();
		getEntityManager().refresh(entity);
		return super.remove();
	}

}
