/**
 * 
 */
package com.ydk.persistence;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.transaction.annotation.Transactional;

import com.ydk.account.persistence.entity.Partner;
import com.ydk.account.persistence.operator.PartnerQuery;


/**
 * Provide overall query operation utilities
 * @author ZhangYu
 *
 */
public abstract class QueryOperator<E> {
	
	public static final Integer MAX_RESULTS_NUM = 99999999;
	public static final String[] CASE_INSENSITIVE_RESTRICTION_SEARCH 
		={ 
			" lower(:Param0) like (lower(:ParamValue0))",
			" lower(:Param1) like (lower(:ParamValue1))",
			" lower(:Param2) like (lower(:ParamValue2))",
			" lower(:Param3) like (lower(:ParamValue3))",
			" lower(:Param4) like (lower(:ParamValue4))",
			" lower(:Param5) like (lower(:ParamValue5))",
			" lower(:Param6) like (lower(:ParamValue6))",
			" lower(:Param7) like (lower(:ParamValue7))",
			" lower(:Param8) like (lower(:ParamValue8))",
			" lower(:Param9) like (lower(:ParamValue9))",
			" lower(:Param10) like (lower(:ParamValue10))",
			" lower(:Param11) like (lower(:ParamValue11))",
			" lower(:Param12) like (lower(:ParamValue12))",
			" lower(:Param13) like (lower(:ParamValue13))",
		};
	public static final String[] CASE_SENSITIVE_RESTRICTION_SEARCH 
	={ 
		" (:Param0) like ((:ParamValue0))",
		" (:Param1) like ((:ParamValue1))",
		" (:Param2) like ((:ParamValue2))",
		" (:Param3) like ((:ParamValue3))",
		" (:Param4) like ((:ParamValue4))",
		" (:Param5) like ((:ParamValue5))",
		" (:Param6) like ((:ParamValue6))",
		" (:Param7) like ((:ParamValue7))",
		" (:Param8) like ((:ParamValue8))",
		" (:Param9) like ((:ParamValue9))",
		" (:Param10) like ((:ParamValue10))",
		" (:Param11) like ((:ParamValue11))",
		" (:Param12) like ((:ParamValue12))",
		" (:Param13) like ((:ParamValue13))",
	};
	
	public static final String[] CASE_INSENSITIVE_RESTRICTION_SEARCH_ALL 
	={ 
		" (:Param0) like concat('%', concat(lower(:ParamValue0),'%'))",
		" (:Param1) like concat('%', concat(lower(:ParamValue1),'%'))",
		" (:Param2) like concat('%', concat(lower(:ParamValue2),'%'))",
		" (:Param3) like concat('%', concat(lower(:ParamValue3),'%'))",
		" (:Param4) like concat('%', concat(lower(:ParamValue4),'%'))",
		" (:Param5) like concat('%', concat(lower(:ParamValue5),'%'))",
		" (:Param6) like concat('%', concat(lower(:ParamValue6),'%'))",
		" (:Param7) like concat('%', concat(lower(:ParamValue7),'%'))",
		" (:Param8) like concat('%', concat(lower(:ParamValue8),'%'))",
		" (:Param9) like concat('%', concat(lower(:ParamValue9),'%'))",
		" (:Param10) like concat('%', concat(lower(:ParamValue10),'%'))",
		" (:Param11) like concat('%', concat(lower(:ParamValue11),'%'))",
		" (:Param12) like concat('%', concat(lower(:ParamValue12),'%'))",
		" (:Param13) like concat('%', concat(lower(:ParamValue13),'%'))",
	};
	public static final String[] CASE_INSENSITIVE_RESTRICTION_SEARCH_STARTWITH
	={ 
		" (:Param0) like (concat(lower(:ParamValue0),'%'))",
		" (:Param1) like (concat(lower(:ParamValue1),'%'))",
		" (:Param2) like (concat(lower(:ParamValue2),'%'))",
		" (:Param3) like (concat(lower(:ParamValue3),'%'))",
		" (:Param4) like (concat(lower(:ParamValue4),'%'))",
		" (:Param5) like (concat(lower(:ParamValue5),'%'))",
		" (:Param6) like (concat(lower(:ParamValue6),'%'))",
		" (:Param7) like (concat(lower(:ParamValue7),'%'))",
		" (:Param8) like (concat(lower(:ParamValue8),'%'))",
		" (:Param9) like (concat(lower(:ParamValue9),'%'))",
		" (:Param10) like (concat(lower(:ParamValue10),'%'))",
		" (:Param11) like (concat(lower(:ParamValue11),'%'))",
		" (:Param12) like (concat(lower(:ParamValue12),'%'))",
		" (:Param13) like (concat(lower(:ParamValue13),'%'))",
	};
	
	/**For App engine
	public static final String[] APPENGINE_CASE_INSENSITIVE_RESTRICTION_SEARCH 
	={ 
		" (:Param0) = (:ParamValue0)",
		" lower(:Param1) = concat(lower(:ParamValue1),'')",
		" lower(:Param2) = concat(lower(:ParamValue2),'')",
		" lower(:Param3) = concat(lower(:ParamValue3),'')",
		" lower(:Param4) = concat(lower(:ParamValue4),'')",
		" lower(:Param5) = concat(lower(:ParamValue5),'')",
		" lower(:Param6) = concat(lower(:ParamValue6),'')",
		" lower(:Param7) = concat(lower(:ParamValue7),'')",
		" lower(:Param8) = concat(lower(:ParamValue8),'')",
		" lower(:Param9) = concat(lower(:ParamValue9),'')",
		" lower(:Param10) = concat(lower(:ParamValue10),'')",
		" lower(:Param11) = concat(lower(:ParamValue11),'')",
		" lower(:Param12) = concat(lower(:ParamValue12),'')",
		" lower(:Param13) = concat(lower(:ParamValue13),'')",
	};
	public static final String[] APPENGINE_RESTRICTION_SEARCH 
	={ 
		" (:Param0) = concat((:ParamValue0),'%')",
		" (:Param1) = concat((:ParamValue1),'%')",
		" (:Param2) = concat((:ParamValue2),'%')",
		" (:Param3) = concat((:ParamValue3),'%')",
		" (:Param4) = concat((:ParamValue4),'%')",
		" (:Param5) = concat((:ParamValue5),'%')",
		" (:Param6) = concat((:ParamValue6),'%')",
		" (:Param7) = concat((:ParamValue7),'%')",
		" (:Param8) = concat((:ParamValue8),'%')",
		" (:Param9) = concat((:ParamValue9),'%')",
		" (:Param10) = concat((:ParamValue10),'%')",
		" (:Param11) = concat((:ParamValue11),'%')",
		" (:Param12) = concat((:ParamValue12),'%')",
		" (:Param13) = concat((:ParamValue13),'%')",
	};
	
	public static final String[] APPENGINE_RESTRICTION_SEARCH_FULL 
	={ 
		" (:Param0) = concat('%', concat(lower(:ParamValue0),'%'))",
		" (:Param1) = concat('%', concat(lower(:ParamValue1),'%'))",
		" (:Param2) = concat('%', concat(lower(:ParamValue2),'%'))",
		" (:Param3) = concat('%', concat(lower(:ParamValue3),'%'))",
		" (:Param4) = concat('%', concat(lower(:ParamValue4),'%'))",
		" (:Param5) = concat('%', concat(lower(:ParamValue5),'%'))",
		" (:Param6) = concat('%', concat(lower(:ParamValue6),'%'))",
		" (:Param7) = concat('%', concat(lower(:ParamValue7),'%'))",
		" (:Param8) = concat('%', concat(lower(:ParamValue8),'%'))",
		" (:Param9) = concat('%', concat(lower(:ParamValue9),'%'))",
		" (:Param10) = concat('%', concat(lower(:ParamValue10),'%'))",
		" (:Param11) = concat('%', concat(lower(:ParamValue11),'%'))",
		" (:Param12) = concat('%', concat(lower(:ParamValue12),'%'))",
		" (:Param13) = concat('%', concat(lower(:ParamValue13),'%'))",
	};
	**/
	
	public static final String ID_SEARCH_BT 
	= " (:Param0) > (:ParamValue0)";
	public static final String ID_SEARCH_LT 
	= " (:Param0) < (:ParamValue0)";
	public static final String ID_SEARCH_EQ 
	= " (:Param0) = (:ParamValue0)";
	
	/** Logger that is available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	private static final Pattern FROM_PATTERN = Pattern.compile(
			"(^|\\s)(from)\\s", Pattern.CASE_INSENSITIVE);
	private static final Pattern ORDER_PATTERN = Pattern.compile(
			"\\s(order)(\\s)+by\\s", Pattern.CASE_INSENSITIVE);
	private static final Pattern WHERE_PATTERN = Pattern.compile(
			"\\s(where)\\s", Pattern.CASE_INSENSITIVE);

	private static final Pattern ORDER_CLAUSE_PATTERN = Pattern
			.compile("^[\\w\\.,\\s]*$");

	//EJB Sql
	private String ejbql;
	private String parsedEjbql;

	//Page count control
	private Integer firstResult;
	private Integer maxResults = MAX_RESULTS_NUM;

	//Restrictions control
	private List<String> restrictions = null;
	private List<String> parsedRestrictions = null;
	private String order;
	private Map<String, String> hints;

	//Parameters used in query
	private List<String> queryParameters;
	private List<Object> queryParameterValues;
	private List<String> restrictionParameters;
	private List<Object> restrictionParameterValues;

	//List to return
	private List<E> resultList;
	private E singleResult;
	private Long resultCount;

	private EntityManager em = null;

	//Subclass need to provide their Class Name, though we can get it
	public abstract String getEntityClassName();
	//Subclass need to provide their whole Class Name, though we can get it
	public abstract String getWholeEntityClassName();

	/**
	 * The EntityManager should be injected
	 * 
	 * @Future This method should support get em from AppContext
	 */
	public EntityManager getEntityManager() {
		return em;
	}

	/**
	 * The EntityManager should be injected
	 */
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	//validate that QueryOperator is ready to run
	public void validate() {
		if (getEntityManager() == null) {
			throw new IllegalStateException("entityManager is null");
		}
	}

	/**
	 * Get the list of results this query returns
	 * 
	 * Any changed restriction values will be applied
	 */
	@Transactional
	public List<E> getResultList() {
		initResultList();
		return truncResultList(resultList);
	}
	
	/*
	 * 
	 */
	public void refresh() {
		this.resultList = null;
	}

	private void initResultList() {
		if (resultList == null) {
			javax.persistence.Query query = createQuery();
			resultList = query == null ? null : query.getResultList();
		}
	}

	protected List<E> truncResultList(List<E> results) {
		Integer mr = getMaxResults();
		if (mr != null && results.size() > mr) {
			return results.subList(0, mr);
		} else {
			return results;
		}
	}

	/**
	 * Get the number of results this query returns
	 * 
	 * Any changed restriction values will be applied
	 */
	@Transactional
	public Long getResultCount() {

		initResultCount();
		return resultCount;
	}

	private void initResultCount() {
		if (resultCount == null) {
			javax.persistence.Query query = createCountQuery();
			resultCount = query == null ? null : (Long) query.getSingleResult();
		}
	}

	protected javax.persistence.Query createCountQuery() {
		parseEjbql();

		//TODO We need to guess the parameter value form params input
		//evaluateAllParameters();
		//TODO Maybe we should join in transaction manually
		//joinTransaction();

		javax.persistence.Query query = getEntityManager().createQuery(
				getCountEjbql());
		setParameters(query, getQueryParameters(), getQueryParameterValues(), 0);
		//TODO we could consider to put two kinds of params into one List
		setParameters(query, getRestrictionParameters(),
				getRestrictionParameterValues(), getQueryParameterValues()
						.size());
		return query;
	}

	/*
	 * hardcoded a general EJBQL
	 */
	public String getEjbql() {
		if (this.ejbql == null)
		{
			ejbql = "select " + getEntityClassName().toLowerCase() + " from "
			+ getWholeEntityClassName() + " "
			+ getEntityClassName().toLowerCase();
			// emulate something like:  select class from Class class
		}
		return ejbql;
	}

	/**
	 * Set the ejbql to use.  Calling this causes the ejbql to be reparsed and
	 * the query to be refreshed
	 */
	public void setEjbql(String ejbql) {
		this.ejbql = ejbql;
		parsedEjbql = null;
	}

	/*
	 * Create a Query object to execute
	 */

	protected javax.persistence.Query createQuery() {
		
		parseEjbql();

		//TODO We need to guess the parameter values for the pamrater input
		//evaluateAllParameters();

		//If JTA is needed
		//getEntityManager().joinTransaction();
		logger.debug("createQuery: ParsedEjbql: " + this.parsedEjbql);
		logger.debug("createQuery: RenderedEjbql: " + getRenderedEjbql());

		javax.persistence.Query query = getEntityManager().createQuery(
				getRenderedEjbql());
		setParameters(query, getQueryParameters(), getQueryParameterValues(), 0);
		setParameters(query, getRestrictionParameters(),
				getRestrictionParameterValues(), getQueryParameterValues()
						.size());
		if (getFirstResult() != null)
			query.setFirstResult(getFirstResult());
		if (getMaxResults() != null)
			query.setMaxResults(getMaxResults() + 1); //add one, so we can tell if there is another page
		if (getHints() != null) {
			for (Map.Entry<String, String> me : getHints().entrySet()) {
				query.setHint(me.getKey(), me.getValue());
			}
		}
		return query;
	}

	private void setParameters(javax.persistence.Query query,
			List<String> paramNames, List<Object> parameters, int start) {
		for (int i = 0; i < parameters.size(); i++) {
			Object parameterValue = parameters.get(i);
			if (isRestrictionParameterSet(parameterValue)) {
				//query.setParameter("Param" + (new Integer(i)).toString(), paramNames.get(i));
				logger.debug("setParameters: " +  " ParamValue" + (Integer.toString(i)) + " to "+ parameterValue);
				query.setParameter("ParamValue" + Integer.toString(i), parameterValue);
				
			}
		}
	}

	public Map<String, String> getHints() {
		return hints;
	}

	public void setHints(Map<String, String> hints) {
		this.hints = hints;
	}

	/**
	 * Returns true if next page exists
	 */
	public boolean isNextExists() {
		return resultList != null && getMaxResults() != null
				&& resultList.size() > getMaxResults();
	}

	/**
	 * The page size
	 */
	public Integer getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}

	/**
	 * Get the total number of pages
	 * 
	 */
	public Integer getPageCount() {
		if (getMaxResults() == null) {
			return null;
		} else {
			int rc = getResultCount().intValue();
			int mr = getMaxResults().intValue();
			int pages = rc / mr;
			return rc % mr == 0 ? pages : pages + 1;
		}
	}

	/**
	 * Move the result set cursor to the beginning of the last page
	 * 
	 */
	public void last() {
		setFirstResult(getLastFirstResult().intValue());
	}

	/**
	 * Move the result set cursor to the beginning of the next page
	 * 
	 */
	public void next() {
		setFirstResult(getNextFirstResult());
	}

	/**
	 * Move the result set cursor to the beginning of the previous page
	 * 
	 */
	public void previous() {
		setFirstResult(getPreviousFirstResult());
	}

	/**
	 * Move the result set cursor to the beginning of the first page
	 * 
	 */
	public void first() {
		setFirstResult(0);
	}

	/**
	 * Get the index of the first result of the last page
	 * 
	 */
	public Long getLastFirstResult() {
		Integer pc = getPageCount();
		return pc == null ? null : (pc.longValue() - 1) * getMaxResults();
	}

	/**
	 * Get the index of the first result of the next page
	 * 
	 */
	public int getNextFirstResult() {
		Integer fr = getFirstResult();
		return (fr == null ? 0 : fr) + getMaxResults();
	}

	/**
	 * Get the index of the first result of the previous page
	 * 
	 */
	public int getPreviousFirstResult() {
		Integer fr = getFirstResult();
		Integer mr = getMaxResults();
		return mr >= (fr == null ? 0 : fr) ? 0 : fr - mr;
	}

	/**
	 * Returns the index of the first result of the current page
	 */
	public Integer getFirstResult() {
		return firstResult;
	}

	/**
	 * Set the index at which the page to display should start
	 */
	public void setFirstResult(Integer firstResult) {
		this.firstResult = firstResult;
	}

	/**
	 * parse the specified EJBQL
	 */

	protected void parseEjbql() {
		if (parsedEjbql == null || parsedRestrictions == null) {
			parsedEjbql = getEjbql();
			parsedRestrictions = getRestrictions();
			//Manapulate the column field
			for (int i = 0; i < getRestrictionParameters().size(); i++)
			{
				
				logger.debug("parseEjbql: replace filed: " 
						+ getRestrictionParameters().get(i));
				logger.debug("parseEjbql: parsedRestrictions: " 
						+ parsedRestrictions);
				logger.debug("parseEjbql: parsedRestrictions size: " 
						+ parsedRestrictions.size());
				
				String result = new String(parsedRestrictions.get(i));
				logger.debug("parseEjbql: result befor manapulation: " 
						+ result);
				result = result.replace((":Param" + Integer.toString(i)), (String)getRestrictionParameters().get(i));
				logger.debug("parseEjbql: replace filed: result " 
						+ result);	
				parsedRestrictions.set(i, result );
				logger.debug("parseEjbql: replace filed: " 
						+ parsedRestrictions.get(i));		
			}
			
			//TODO we need to set 
			//queryParameters
			//parsedEjbql
			//parsedRestrictions
			//restrictionParameters
			//parsedRestrictions;
			//restrictionParameters
			/*
			QueryParser qp = new QueryParser( getEjbql() );
			queryParameters = qp.getParameterValueBindings();
			parsedEjbql = qp.getEjbql();
			
			List<String> restrictionFragments = getRestrictions();
			parsedRestrictions = new ArrayList<String>( restrictionFragments.size() );
			restrictionParameters = new ArrayList<ValueExpression>( restrictionFragments.size() );         
			for ( String restriction: restrictionFragments )
			{
			  QueryParser rqp = new QueryParser( restriction, queryParameters.size() + restrictionParameters.size() );            
			  if ( rqp.getParameterValueBindings().size()!=1 ) 
			  {
			     throw new IllegalArgumentException("there should be exactly one value binding in a restriction: " + restriction);
			  }            
			  parsedRestrictions.add( rqp.getEjbql() );
			  restrictionParameters.addAll( rqp.getParameterValueBindings() );
			}
			 */

		}
	}

	/**
	 * List of restrictions to apply to the query.
	 * 
	 * For a query such as 'from Foo f' a restriction could be 
	 * 'f.bar = #{foo.bar}'
	 */
	public List<String> getRestrictions() {
		if (restrictions == null)
		{
			restrictions = new ArrayList<String>();
			for (String res: Arrays.asList(CASE_SENSITIVE_RESTRICTION_SEARCH))
			{
				restrictions.add(new String(res));
				logger.debug("getRestrictions : " + res);
			}
		}
		return restrictions;
	}

	/**
	 * Calling setRestrictions causes the restrictions to be reparsed and the 
	 * query refreshed
	 */
	public void setRestrictions(List<String> restrictions) {
		this.restrictions = restrictions;
		parsedRestrictions = null;
	}
	
	/**
	 * Calling setRestrictions causes the restrictions to be reparsed and the 
	 * query refreshed
	 */
	public void setUserRestrictions(String[] restrictions) {
		if (restrictions != null)
		{
			this.restrictions = new ArrayList<String>();
			for (String res: Arrays.asList(restrictions))
			{
				this.restrictions.add(new String(res));
//				logger.debug("setRestrictions : " + res);
			}
//			this.restrictions = restrictions;
			parsedRestrictions = null;
		}
	}
	
	/**
	 * Calling addRestrictions causes the restrictions to be reparsed and the 
	 * query refreshed
	 */
	public void addRestrictions(String restriction) {
		getRestrictions().add(restriction);
		parsedRestrictions = null;
	}
	/**
	 * Calling clearRestrictions causes the restrictions to be reparsed and the 
	 * query refreshed
	 */
	public void clearRestrictions() {
		//2009/11/19, allow user to choose restrictions. ZhangYu
//		setRestrictions(null);
		parsedRestrictions = null;
	}
	

	/**
	 * The order of the query
	 */
	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		if (order != null && !ORDER_CLAUSE_PATTERN.matcher(order).find()) {
			throw new IllegalArgumentException("invalid order column (\""
					+ order + "\" must match the regular expression \""
					+ ORDER_CLAUSE_PATTERN + "\")");
		}
		this.order = order;
	}
	
	public void setOrderAsc(String order) {

		this.order = order + " asc";
	}
	
	public void setOrderDesc(String order) {
		this.order = order + " desc";
	}

	protected String getRenderedEjbql() {
		StringBuilder builder = new StringBuilder().append(parsedEjbql);
		
		for (int i = 0; (i < getRestrictions().size()) 
		&& (i < getRestrictionParameters().size()) ; i++) {
			//TODO here we need to get the parameter value
			Object parameter = this.getRestrictionParameters().get(i);
			if (isRestrictionParameterSet(parameter)) {
				if (WHERE_PATTERN.matcher(builder).find()) {
					builder.append(" and ");
				} else {
					builder.append(" where ");
				}

				builder.append(parsedRestrictions.get(i));
			}
		}

		if (getOrder() != null)
			builder.append(" order by ").append(getOrder());

		return builder.toString();
	}

	protected boolean isRestrictionParameterSet(Object parameterValue) {
		return parameterValue != null && !"".equals(parameterValue);
	}

	/**
	 * Return the ejbql to used in a count query (for calculating number of
	 * results)
	 * @return String The ejbql query
	 */
	protected String getCountEjbql() {
		String ejbql = getRenderedEjbql();

		Matcher fromMatcher = FROM_PATTERN.matcher(ejbql);
		if (!fromMatcher.find()) {
			throw new IllegalArgumentException("no from clause found in query");
		}
		int fromLoc = fromMatcher.start(2);

		Matcher orderMatcher = ORDER_PATTERN.matcher(ejbql);
		int orderLoc = orderMatcher.find() ? orderMatcher.start(1) : ejbql
				.length();

		return "select count(*) " + ejbql.substring(fromLoc, orderLoc);
	}

	/**
	 * Returns true if the previous page exists
	 */
	public boolean isPreviousExists() {
		return getFirstResult() != null && getFirstResult() != 0;
	}

	public List<String> getQueryParameters() {
		if (queryParameters == null) {
			queryParameters = new ArrayList<String>();
		}
		return queryParameters;
	}
	
	public void setQueryParameters(List<String> queryParameters) {
		this.queryParameters = queryParameters;
	}

	public List<String> getRestrictionParameters() {
		if (restrictionParameters == null) {
			restrictionParameters = new ArrayList<String>();
		}
		return restrictionParameters;
	}
	public void setRestrictionParameters(List<String> restrictionParameters) {
		this.restrictionParameters = restrictionParameters;
	}
	

	public List<Object> getQueryParameterValues() {
		if (queryParameterValues == null) {
			queryParameterValues = new ArrayList<Object>();
		}
		return queryParameterValues;
	}

	public void setQueryParameterValues(List<Object> queryParameterValues) {
		this.queryParameterValues = queryParameterValues;
	}

	public void addQueryParameter(String name, Object value) {
		getQueryParameters().add(name);
		getQueryParameterValues().add(value);
	}

	public void removeQueryParameter(String name, Object value) {
		int pos = getQueryParameters().indexOf(name);
		getQueryParameters().remove(pos);
		getQueryParameterValues().remove(pos);
	}

	public List<Object> getRestrictionParameterValues() {
		if (restrictionParameterValues == null) {
			restrictionParameterValues = new ArrayList<Object>();
		}
		return restrictionParameterValues;
	}

	public void setRestrictionParameterValues(
			List<Object> restrictionParameterValues) {
		this.restrictionParameterValues = restrictionParameterValues;
	}

	public void addRestrictionParameter(String name, Object value) {
		this.getRestrictionParameters().add(name);
		this.getRestrictionParameterValues().add(value);
	}
	public void addRestrictionParameter(List<String> names, List<Object> values) {
		setRestrictionParameters(names);
		setRestrictionParameterValues(values);
	}
	/*
	 * Add RestrictionParameter accord to names from entity
	 */
	public void addRestrictionParameter(List<String> names, E entity) {
		setRestrictionParameters(names);
		if (entity == null)
			throw new IllegalStateException("addRestrictionParameter: entity null");
		for (String name: names)
		{
			try {
				
				Field field = entity.getClass().getDeclaredField(name);
				field.setAccessible(true);
				logger.fatal("addRestrictionParameter: field: " + name 
						+ "value : " + field.get(entity));
				getRestrictionParameterValues().add(field.get(entity));
			} catch (SecurityException e) {
				logger.fatal("Field: " + name + "not EXIST!");
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				logger.fatal("Field: " + name + "not EXIST!");
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				logger.fatal("Field: " + name + "wrong!");
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				logger.fatal("Field: " + name + "wrong!");
				e.printStackTrace();
			}
		}
	}

	public void removeRestrictionParameter(String name, Object value) {
		int pos = getRestrictionParameters().indexOf(name);
		getRestrictionParameters().remove(pos);
		getRestrictionParameterValues().remove(pos);
	}
	
	/*
	 * clear parameters to get ready for new search 
	 */
	
	public void clearAllParameters(){
		restrictionParameters = null;
		restrictionParameterValues = null;
		queryParameters = null;
		queryParameterValues = null;
	}
	
	/*
	 * Search User with multiple fields
	 */
	
	public List<E> searchWithParams(List<String> params, E entity)
	{
		clearRestrictions();
		clearAllParameters();
		addRestrictionParameter(params, entity);
		List<E> entities = getResultList();
		
		logger.debug("searchWithParams: size:" + entities.size());
		
		return entities;
	}
	
	/*
	 * Search User with multiple fields
	 */
	
	public List<E> searchWithParams(List<String> params, List<Object> values)
	{
		clearRestrictions();
		clearAllParameters();
		addRestrictionParameter(params, values);
		List<E> entities = getResultList();
		
		logger.debug("searchWithParams: size:" + entities.size());
		
		return entities;
	}
	
	/*
	 * Search User with specified field
	 */
	public void setQueryAttribute(String[] queryRule)
	{
		clearRestrictions();
		clearAllParameters();
		setMaxResults(PartnerQuery.MAX_RESULTS_NUM);
		setUserRestrictions(queryRule);
	}


}
