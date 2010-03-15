/**
 * 
 */
package com.ydk.book.persistence.operator;

import java.util.List;

import javax.persistence.EntityManager;

import com.ydk.account.persistence.entity.*;
import com.ydk.book.persistence.entity.BookReview;
import com.ydk.book.persistence.entity.Event;
import com.ydk.book.persistence.entity.Interview;
import com.ydk.persistence.QueryOperator;
/**
 * @author Zhang Yu
 *
 */
public class EventQuery extends QueryOperator<Event> {
	
	public EventQuery(EntityManager em)
	{
		setEntityManager(em);
		validate();
		setMaxResults(EventQuery.MAX_RESULTS_NUM);
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
		return Event.class.getSimpleName();
	}
	
	/* 
	 * This is is used to provide information for Query
	 * Do not change them unless ...
	 */
	@Override
	public String getWholeEntityClassName() {
		// TODO Auto-generated method stub
		//return User.class.getSimpleName();
		return "com.ydk.book.persistence.entity.Event";
	}
	
	/*
	 * Search User with specified field
	 */
	public List<Event> searchEventByField(String name, Object value)
	{
		setQueryAttribute(CASE_INSENSITIVE_RESTRICTION_SEARCH_ALL);
		addRestrictionParameter(name, value);
		List<Event> list = getResultList();
		return list;
	}
	
	
	/*
	 * Search BookReview with specified field
	 */
	public Event searchEventByTitle(String title)
	{
		setQueryAttribute(CASE_SENSITIVE_RESTRICTION_SEARCH);
		addRestrictionParameter("title", title);
		List<Event> list = getResultList();
		if (list != null && list.size() >= 1)
		{
			return list.get(0);
		}
		return null;
	}
	

}
