/**
 * 
 */
package com.ydk.book.persistence.operator;

import java.util.List;

import javax.persistence.EntityManager;

import com.ydk.account.persistence.entity.*;
import com.ydk.book.persistence.entity.Author;
import com.ydk.persistence.QueryOperator;
/**
 * @author Zhang Yu
 *
 */
public class AuthorQuery extends QueryOperator<Author> {
	
	public AuthorQuery(EntityManager em)
	{
		setEntityManager(em);
		validate();
		setMaxResults(AuthorQuery.MAX_RESULTS_NUM);
		//Explicitly set the searching rule
		setUserRestrictions(CASE_INSENSITIVE_RESTRICTION_SEARCH);
	}
	
	/* 
	 * This is is used to provide information for Query
	 * Do not change them unless ...
	 */
	@Override
	public String getEntityClassName() {
		// TODO Auto-generated method stub
		return Author.class.getSimpleName();
	}
	
	/* 
	 * This is is used to provide information for Query
	 * Do not change them unless ...
	 */
	@Override
	public String getWholeEntityClassName() {
		// TODO Auto-generated method stub
		//return User.class.getSimpleName();
		return "com.ydk.book.persistence.entity.Author";
	}
	
	/*
	 * Search User with specified field
	 */
	public List<Author> searchAuthorByField(String name, Object value)
	{
		setQueryAttribute(CASE_INSENSITIVE_RESTRICTION_SEARCH_ALL);
		addRestrictionParameter(name, value);
		List<Author> list = getResultList();
		return list;
	}
	
	/*
	 * Search with name field
	 */
	public Author searchEntityByName(String name)
	{
		setQueryAttribute(CASE_SENSITIVE_RESTRICTION_SEARCH);
		addRestrictionParameter("name", name);
		List<Author> list = getResultList();
		if (list != null && list.size() >= 1)
		{
			return list.get(0);
		}
		return null;
	}
	

}
