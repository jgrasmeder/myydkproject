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
public class AccountEntity extends EntityOperator<Account> {
	
	public AccountEntity(EntityManager em)
	{
		setEntityManager(em);
		validate();
		getInstance();
	}
	
	public AccountEntity(EntityManager em, Object id)
	{
		setEntityManager(em);
		validate();
		getInstance();
		loadInstance(id);
	}
	
	public Account loadAccount(Object id)
	{
		return loadInstance(id);
	}
	
	public String remove()
	{
		Account account = getInstance();
		getEntityManager().refresh(account);

		return super.remove();
	}

}
