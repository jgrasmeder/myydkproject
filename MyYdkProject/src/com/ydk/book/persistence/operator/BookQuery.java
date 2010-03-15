/**
 * 
 */
package com.ydk.book.persistence.operator;

import java.util.List;

import javax.persistence.EntityManager;

import com.ydk.book.persistence.entity.*;
import com.ydk.persistence.QueryOperator;
/**
 * @author Zhang Yu
 *
 */
public class BookQuery extends QueryOperator<Book> {
	
	public BookQuery(EntityManager em)
	{
		setEntityManager(em);
		validate();
		setMaxResults(BookQuery.MAX_RESULTS_NUM);
		//Explicitly set the searching rule
		setUserRestrictions(CASE_INSENSITIVE_RESTRICTION_SEARCH);
	}
	
	/*
	 * Create a BookQuery whose search key is bookName
	 */
	
	public BookQuery(EntityManager em, String bookName)
	{
		setEntityManager(em);
		validate();
		clearRestrictions();
		clearAllParameters();
		setMaxResults(BookQuery.MAX_RESULTS_NUM);
		//Explicitly set the searching rule
		setUserRestrictions(CASE_INSENSITIVE_RESTRICTION_SEARCH);
		addRestrictionParameter(" title ", bookName);
	}
	
	public BookQuery(EntityManager em, String ejbql, String ristriction)
	{
		setEntityManager(em);
		validate();
		setMaxResults(BookQuery.MAX_RESULTS_NUM);
		//Explicitly set the searching rule
		setUserRestrictions(CASE_INSENSITIVE_RESTRICTION_SEARCH_ALL);
		
		setEjbql(ejbql);
		if (ristriction != null)
		addRestrictions(ristriction);
	}
	/* (non-Javadoc)
	 * @see com.assetKeeper.server.template.QueryOperator#getEntityClassName()
	 */
	@Override
	public String getEntityClassName() {
		// TODO Auto-generated method stub
		return Book.class.getSimpleName();
	}
	
	@Override
	public String getWholeEntityClassName() {
		// TODO Auto-generated method stub
		//return User.class.getSimpleName();
		return "com.ydk.book.persistence.entity.Book";
	}
	
	/*
	 * Search User with specified field
	 */
	public List<Book> searchBookByField(String name, Object value)
	{
		clearRestrictions();
		clearAllParameters();
		setMaxResults(BookQuery.MAX_RESULTS_NUM);
		setUserRestrictions(CASE_INSENSITIVE_RESTRICTION_SEARCH_ALL);
		addRestrictionParameter(name, value);
		List<Book> list = getResultList();
		
		logger.debug("searchBookByField: size:" + list.size());
		
		return list;
	}
	
	/*
	 * Search User with specified field
	 */
	public Book searchBookByYdkId(String ydkId)
	{
		setQueryAttribute(CASE_SENSITIVE_RESTRICTION_SEARCH);
		addRestrictionParameter("ydkBookId", ydkId);
		List<Book> list = getResultList();
		if (list != null && list.size() >= 1)
		{
			return list.get(0);
		}
		return null;
	}
	

}
