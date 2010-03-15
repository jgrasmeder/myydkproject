/**
 * 
 */
package com.ydk.book.persistence.operator;

import java.util.List;

import javax.persistence.EntityManager;

import com.ydk.account.persistence.entity.*;
import com.ydk.book.persistence.entity.BookNewsCategory;
import com.ydk.book.persistence.entity.BookReviewCategory;
import com.ydk.book.persistence.entity.EventCategory;
import com.ydk.book.persistence.entity.Interview;
import com.ydk.book.persistence.entity.InterviewCategory;
import com.ydk.persistence.QueryOperator;
/**
 * @author Zhang Yu
 *
 */
public class BookReviewCategoryQuery extends QueryOperator<BookReviewCategory> {
	
	public BookReviewCategoryQuery(EntityManager em)
	{
		setEntityManager(em);
		validate();
		setMaxResults(BookReviewCategoryQuery.MAX_RESULTS_NUM);
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
		return BookReviewCategory.class.getSimpleName();
	}
	
	/* 
	 * This is is used to provide information for Query
	 * Do not change them unless ...
	 */
	@Override
	public String getWholeEntityClassName() {
		// TODO Auto-generated method stub
		//return User.class.getSimpleName();
		return "com.ydk.book.persistence.entity.BookReviewCategory";
	}
	
	/*
	 * Search User with specified field
	 */
	public List<BookReviewCategory> searchBookReviewCategoryByField(String name, Object value)
	{
		setQueryAttribute(CASE_INSENSITIVE_RESTRICTION_SEARCH_ALL);
		addRestrictionParameter(name, value);
		List<BookReviewCategory> list = getResultList();
		return list;
	}
	
	/*
	 * Search BookReviewCategory with specified field
	 */
	public BookReviewCategory searchBookReviewCategoryByName(String name)
	{
		setQueryAttribute(CASE_SENSITIVE_RESTRICTION_SEARCH);
		addRestrictionParameter("name", name);
		List<BookReviewCategory> list = getResultList();
		if (list != null && list.size() >= 1)
		{
			return list.get(0);
		}
		return null;
	}
	

}
