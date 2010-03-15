/**
 * 
 */
package com.ydk.book.persistence.operator;

import javax.persistence.EntityManager;

import com.ydk.account.persistence.entity.*;
import com.ydk.book.persistence.entity.Translator;
import com.ydk.persistence.EntityOperator;

/**
 * @author y140zhan
 *
 */
public class TranslatorEntity extends EntityOperator<Translator> {
	
	public TranslatorEntity(EntityManager em)
	{
		setEntityManager(em);
		validate();
		getInstance();
	}
	
	public TranslatorEntity(EntityManager em, Object id)
	{
		setEntityManager(em);
		validate();
		getInstance();
		loadInstance(id);
	}
	
	public Translator loadEntity(Object id)
	{
		return loadInstance(id);
	}
	
	public String remove()
	{
		Translator entity = getInstance();
		getEntityManager().refresh(entity);
		return super.remove();
	}

}
