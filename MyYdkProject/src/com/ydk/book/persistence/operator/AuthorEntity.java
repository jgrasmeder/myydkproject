/**
 * 
 */
package com.ydk.book.persistence.operator;

import javax.persistence.EntityManager;

import com.ydk.account.persistence.entity.*;
import com.ydk.book.persistence.entity.Author;
import com.ydk.persistence.EntityOperator;

/**
 * @author y140zhan
 *
 */
public class AuthorEntity extends EntityOperator<Author> {
	
	public AuthorEntity(EntityManager em)
	{
		setEntityManager(em);
		validate();
		getInstance();
	}
	
	public AuthorEntity(EntityManager em, Object id)
	{
		setEntityManager(em);
		validate();
		getInstance();
		loadInstance(id);
	}
	
	public Author loadEntity(Object id)
	{
		return loadInstance(id);
	}
	
	public String remove()
	{
		Author entity = getInstance();
		getEntityManager().refresh(entity);
		return super.remove();
	}

}
