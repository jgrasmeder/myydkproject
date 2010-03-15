/**
 * 
 */
package com.ydk.account.persistence.operator;

import java.util.List;

import javax.persistence.EntityManager;

import com.ydk.account.persistence.entity.*;
import com.ydk.persistence.QueryOperator;
/**
 * @author Zhang Yu
 *
 */
public class ReaderQuery extends QueryOperator<Reader> {
	
	public ReaderQuery(EntityManager em)
	{
		setEntityManager(em);
		validate();
		setMaxResults(ReaderQuery.MAX_RESULTS_NUM);
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
		return Reader.class.getSimpleName();
	}
	
	/* 
	 * This is is used to provide information for Query
	 * Do not change them unless ...
	 */
	@Override
	public String getWholeEntityClassName() {
		// TODO Auto-generated method stub
		//return User.class.getSimpleName();
		return "com.ydk.account.persistence.entity.Reader";
	}
	
	/*
	 * Search User with specified field
	 */
	public List<Reader> searchReaderByField(String name, Object value)
	{
		setQueryAttribute(CASE_INSENSITIVE_RESTRICTION_SEARCH_ALL);
		addRestrictionParameter(name, value);
		List<Reader> list = getResultList();
		return list;
	}
	
	/*
	 * Search Reader with specified field
	 */
	public Reader searchReaderByName(String name)
	{
		setQueryAttribute(CASE_SENSITIVE_RESTRICTION_SEARCH);
		addRestrictionParameter("nickName", name);
		List<Reader> list = getResultList();
		if (list != null && list.size() >= 1)
		{
			return list.get(0);
		}
		return null;
	}
	
	
	

}
