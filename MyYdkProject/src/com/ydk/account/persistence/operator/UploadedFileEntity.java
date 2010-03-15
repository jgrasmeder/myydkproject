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
public class UploadedFileEntity extends EntityOperator<UploadedFile> {
	
	public UploadedFileEntity(EntityManager em)
	{
		setEntityManager(em);
		validate();
		getInstance();
	}
	
	public UploadedFileEntity(EntityManager em, Object id)
	{
		setEntityManager(em);
		validate();
		getInstance();
		loadInstance(id);
	}
	
	public UploadedFile loadFile(Object id)
	{
		return loadInstance(id);
	}
	
	public String remove()
	{
		UploadedFile file = getInstance();
		getEntityManager().refresh(file);

		return super.remove();
	}

}
