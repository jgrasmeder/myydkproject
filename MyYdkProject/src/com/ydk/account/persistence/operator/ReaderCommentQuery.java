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
public class ReaderCommentQuery extends QueryOperator<ReaderComment> {
	
	public ReaderCommentQuery(EntityManager em)
	{
		setEntityManager(em);
		validate();
		setMaxResults(ReaderCommentQuery.MAX_RESULTS_NUM);
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
		return ReaderComment.class.getSimpleName();
	}
	
	/* 
	 * This is is used to provide information for Query
	 * Do not change them unless ...
	 */
	@Override
	public String getWholeEntityClassName() {
		// TODO Auto-generated method stub
		//return User.class.getSimpleName();
		return "com.ydk.account.persistence.entity.ReaderComment";
	}
	
	/*
	 * Search User with specified field
	 */
	public List<ReaderComment> searchReaderCommentByField(String name, Object value)
	{
		setQueryAttribute(CASE_INSENSITIVE_RESTRICTION_SEARCH_ALL);
		addRestrictionParameter(name, value);
		List<ReaderComment> list = getResultList();
		return list;
	}
	

}
