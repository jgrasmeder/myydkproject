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
public class AddressEntity extends EntityOperator<Address> {
	
	public AddressEntity(EntityManager em)
	{
		setEntityManager(em);
		validate();
		getInstance();
	}
	
	public AddressEntity(EntityManager em, Object id)
	{
		setEntityManager(em);
		validate();
		getInstance();
		loadInstance(id);
	}
	
	public Address loadEntity(Object id)
	{
		return loadInstance(id);
	}
	
	public String remove()
	{
		Address entity = getInstance();
		getEntityManager().refresh(entity);
		return super.remove();
	}

}
