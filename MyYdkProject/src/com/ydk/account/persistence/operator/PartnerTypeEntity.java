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
public class PartnerTypeEntity extends EntityOperator<PartnerType> {
	
	public PartnerTypeEntity(EntityManager em)
	{
		setEntityManager(em);
		validate();
		getInstance();
	}
	
	public PartnerTypeEntity(EntityManager em, Object id)
	{
		setEntityManager(em);
		validate();
		getInstance();
		loadInstance(id);
	}
	
	public PartnerType loadEntity(Object id)
	{
		return loadInstance(id);
	}
	
	public String remove()
	{
		PartnerType entity = getInstance();
		getEntityManager().refresh(entity);
		return super.remove();
	}

}
