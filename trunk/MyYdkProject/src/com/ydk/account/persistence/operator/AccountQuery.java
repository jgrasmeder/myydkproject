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
public class AccountQuery extends QueryOperator<Account> {
	
	public AccountQuery(EntityManager em)
	{
		setEntityManager(em);
		validate();
		setMaxResults(AccountQuery.MAX_RESULTS_NUM);
		//Explicitly set the searching rule
		setUserRestrictions(CASE_INSENSITIVE_RESTRICTION_SEARCH);
	}
	
	/*
	 * Create a AccountQuery whose search key is accountName
	 */
	
	public AccountQuery(EntityManager em, String accountName)
	{
		setEntityManager(em);
		validate();
		clearRestrictions();
		clearAllParameters();
		setMaxResults(AccountQuery.MAX_RESULTS_NUM);
		//Explicitly set the searching rule
		setUserRestrictions(CASE_INSENSITIVE_RESTRICTION_SEARCH);
		addRestrictionParameter(" accountName ", accountName);
	}
	
	public AccountQuery(EntityManager em, String ejbql, String ristriction)
	{
		setEntityManager(em);
		validate();
		setMaxResults(AccountQuery.MAX_RESULTS_NUM);
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
		return Account.class.getSimpleName();
	}
	
	@Override
	public String getWholeEntityClassName() {
		// TODO Auto-generated method stub
		//return User.class.getSimpleName();
		return "com.ydk.account.persistence.entity.Account";
	}
	
	/*
	 * Search User with specified field
	 */
	public List<Account> searchAccountByField(String name, Object value)
	{
		clearRestrictions();
		clearAllParameters();
		setMaxResults(AccountQuery.MAX_RESULTS_NUM);
		setUserRestrictions(CASE_INSENSITIVE_RESTRICTION_SEARCH_ALL);
		addRestrictionParameter(name, value);
		List<Account> list = getResultList();
		
		logger.debug("searchAccountByField: size:" + list.size());
		
		return list;
	}
	

}
