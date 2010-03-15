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
public class YdkAccountEntity extends EntityOperator<YdkAccount> {
	
	public YdkAccountEntity(EntityManager em)
	{
		setEntityManager(em);
		validate();
		getInstance();
	}
	
	public YdkAccountEntity(EntityManager em, Object id)
	{
		setEntityManager(em);
		validate();
		getInstance();
		loadInstance(id);
	}
	
	public YdkAccount loadEntity(Object id)
	{
		return loadInstance(id);
	}
	
	public String remove()
	{
		YdkAccount entity = getInstance();
		getEntityManager().refresh(entity);
		return super.remove();
	}

}
