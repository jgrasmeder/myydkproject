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
public class PartnerEntity extends EntityOperator<Partner> {
	
	public PartnerEntity(EntityManager em)
	{
		setEntityManager(em);
		validate();
		getInstance();
	}
	
	public PartnerEntity(EntityManager em, Object id)
	{
		setEntityManager(em);
		validate();
		getInstance();
		loadInstance(id);
	}
	
	public Partner loadEntity(Object id)
	{
		return loadInstance(id);
	}
	
	public String remove()
	{
		Partner entity = getInstance();
		getEntityManager().refresh(entity);
		return super.remove();
	}

}
