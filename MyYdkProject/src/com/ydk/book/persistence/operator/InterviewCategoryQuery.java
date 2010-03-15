/**
 * 
 */
package com.ydk.book.persistence.operator;

import java.util.List;

import javax.persistence.EntityManager;

import com.ydk.account.persistence.entity.*;
import com.ydk.book.persistence.entity.EventCategory;
import com.ydk.book.persistence.entity.Interview;
import com.ydk.book.persistence.entity.InterviewCategory;
import com.ydk.persistence.QueryOperator;
/**
 * @author Zhang Yu
 *
 */
public class InterviewCategoryQuery extends QueryOperator<InterviewCategory> {
	
	public InterviewCategoryQuery(EntityManager em)
	{
		setEntityManager(em);
		validate();
		setMaxResults(InterviewCategoryQuery.MAX_RESULTS_NUM);
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
		return InterviewCategory.class.getSimpleName();
	}
	
	/* 
	 * This is is used to provide information for Query
	 * Do not change them unless ...
	 */
	@Override
	public String getWholeEntityClassName() {
		// TODO Auto-generated method stub
		//return User.class.getSimpleName();
		return "com.ydk.book.persistence.entity.InterviewCategory";
	}
	
	/*
	 * Search User with specified field
	 */
	public List<InterviewCategory> searchInterviewCategoryByField(String name, Object value)
	{
		setQueryAttribute(CASE_INSENSITIVE_RESTRICTION_SEARCH_ALL);
		addRestrictionParameter(name, value);
		List<InterviewCategory> list = getResultList();
		return list;
	}
	
	/*
	 * Search InterviewCategory with specified field
	 */
	public InterviewCategory searchInterviewCategoryByName(String name)
	{
		setQueryAttribute(CASE_SENSITIVE_RESTRICTION_SEARCH);
		addRestrictionParameter("name", name);
		List<InterviewCategory> list = getResultList();
		if (list != null && list.size() >= 1)
		{
			return list.get(0);
		}
		return null;
	}
	

}
