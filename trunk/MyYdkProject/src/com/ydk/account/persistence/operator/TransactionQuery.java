/**
 * 
 */
package com.ydk.account.persistence.operator;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.ydk.account.persistence.entity.*;
import com.ydk.persistence.QueryOperator;
/**
 * @author Zhang Yu
 *
 */
public class TransactionQuery extends QueryOperator<Transaction> {
	
	
	public static final String[] TRANSACTION_RESTRICTION_SEARCH 
	={ 
		" (:Param0) like ((:ParamValue0))",
		" (:Param1) > ((:ParamValue1))",
		" (:Param2) < ((:ParamValue2))",
	};
	
	public TransactionQuery(EntityManager em)
	{
		setEntityManager(em);
		validate();
		setMaxResults(TransactionQuery.MAX_RESULTS_NUM);
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
		return Transaction.class.getSimpleName();
	}
	
	/* 
	 * This is is used to provide information for Query
	 * Do not change them unless ...
	 */
	@Override
	public String getWholeEntityClassName() {
		// TODO Auto-generated method stub
		//return User.class.getSimpleName();
		return "com.ydk.account.persistence.entity.Transaction";
	}
	
	/*
	 * Search User with specified field
	 */
	public List<Transaction> searchTransactionByField(String name, Object value)
	{
		setQueryAttribute(CASE_INSENSITIVE_RESTRICTION_SEARCH_ALL);
		addRestrictionParameter(name, value);
		List<Transaction> list = getResultList();
		return list;
	}
	
	public List<Transaction> searchByFieldWithDate(String name, Object value,
			Date startDate, Date endDate)
	{
		setQueryAttribute(TRANSACTION_RESTRICTION_SEARCH);
		addRestrictionParameter(name, value);
		if (startDate == null)
		{
			startDate = new Date();
			startDate.setYear(0);//1900
		}
		if (endDate == null)
		{
			endDate = new Date();
			endDate.setYear(200);//2100
		}
		addRestrictionParameter("date", startDate);
		addRestrictionParameter("date", endDate);
		List<Transaction> list = getResultList();
		return list;
	}
	

}
