/**
 * 
 */
package com.ydk.book.persistence.operator;

import javax.persistence.EntityManager;

import com.ydk.account.persistence.entity.*;
import com.ydk.book.persistence.entity.Book;
import com.ydk.persistence.EntityOperator;

/**
 * @author y140zhan
 *
 */
public class BookEntity extends EntityOperator<Book> {
	
	public BookEntity(EntityManager em)
	{
		setEntityManager(em);
		validate();
		getInstance();
	}
	
	public BookEntity(EntityManager em, Object id)
	{
		setEntityManager(em);
		validate();
		getInstance();
		loadInstance(id);
	}
	
	public Book loadBook(Object id)
	{
		return loadInstance(id);
	}
	
	public String remove()
	{
		Book account = getInstance();
		getEntityManager().refresh(account);

		return super.remove();
	}

}
