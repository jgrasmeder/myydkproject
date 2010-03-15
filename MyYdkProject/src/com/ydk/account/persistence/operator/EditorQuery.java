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
public class EditorQuery extends QueryOperator<Editor> {
	
	public EditorQuery(EntityManager em)
	{
		setEntityManager(em);
		validate();
		setMaxResults(EditorQuery.MAX_RESULTS_NUM);
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
		return Editor.class.getSimpleName();
	}
	
	/* 
	 * This is is used to provide information for Query
	 * Do not change them unless ...
	 */
	@Override
	public String getWholeEntityClassName() {
		// TODO Auto-generated method stub
		//return User.class.getSimpleName();
		return "com.ydk.account.persistence.entity.Editor";
	}
	
	/*
	 * Search User with specified field
	 */
	public List<Editor> searchEditorByField(String name, Object value)
	{
		setQueryAttribute(CASE_INSENSITIVE_RESTRICTION_SEARCH_ALL);
		addRestrictionParameter(name, value);
		List<Editor> list = getResultList();
		return list;
	}
	
	/*
	 * Search Editor with specified field
	 */
	public Editor searchEditorByName(String name)
	{
		setQueryAttribute(CASE_SENSITIVE_RESTRICTION_SEARCH);
		addRestrictionParameter("name", name);
		List<Editor> list = getResultList();
		if (list != null && list.size() >= 1)
		{
			return list.get(0);
		}
		return null;
	}
	

}
