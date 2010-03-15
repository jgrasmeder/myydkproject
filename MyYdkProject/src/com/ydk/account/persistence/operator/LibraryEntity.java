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
public class LibraryEntity extends EntityOperator<Library> {
	
	public LibraryEntity(EntityManager em)
	{
		setEntityManager(em);
		validate();
		getInstance();
	}
	
	public LibraryEntity(EntityManager em, Object id)
	{
		setEntityManager(em);
		validate();
		getInstance();
		loadInstance(id);
	}
	
	public Library loadEntity(Object id)
	{
		return loadInstance(id);
	}
	
	public String remove()
	{
		Library entity = getInstance();
		getEntityManager().refresh(entity);
		return super.remove();
	}

}
