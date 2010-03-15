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
public class TransactionTypeQuery extends QueryOperator<TransactionType> {
	
	public TransactionTypeQuery(EntityManager em)
	{
		setEntityManager(em);
		validate();
		setMaxResults(TransactionTypeQuery.MAX_RESULTS_NUM);
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
		return TransactionType.class.getSimpleName();
	}
	
	/* 
	 * This is is used to provide information for Query
	 * Do not change them unless ...
	 */
	@Override
	public String getWholeEntityClassName() {
		// TODO Auto-generated method stub
		//return User.class.getSimpleName();
		return "com.ydk.account.persistence.entity.TransactionType";
	}
	
	/*
	 * Search User with specified field
	 */
	public List<TransactionType> searchTransactionTypeByField(String name, Object value)
	{
		setQueryAttribute(CASE_INSENSITIVE_RESTRICTION_SEARCH_ALL);
		addRestrictionParameter(name, value);
		List<TransactionType> list = getResultList();
		return list;
	}
	
	/*
	 * Search TransactionType with specified field
	 */
	public TransactionType searchTransactionTypeByType(String type)
	{
		setQueryAttribute(CASE_SENSITIVE_RESTRICTION_SEARCH);
		addRestrictionParameter("type", type);
		List<TransactionType> list = getResultList();
		if (list != null && list.size() >= 1)
		{
			return list.get(0);
		}
		return null;
	}
	

}
