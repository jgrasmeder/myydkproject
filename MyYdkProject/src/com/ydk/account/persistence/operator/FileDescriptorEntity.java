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
public class FileDescriptorEntity extends EntityOperator<FileDescriptor> {
	
	public FileDescriptorEntity(EntityManager em)
	{
		setEntityManager(em);
		validate();
		getInstance();
	}
	
	public FileDescriptorEntity(EntityManager em, Object id)
	{
		setEntityManager(em);
		validate();
		getInstance();
		loadInstance(id);
	}
	
	public FileDescriptor loadFile(Object id)
	{
		return loadInstance(id);
	}
	
	public String remove()
	{
		FileDescriptor file = getInstance();
		getEntityManager().refresh(file);

		return super.remove();
	}

}
