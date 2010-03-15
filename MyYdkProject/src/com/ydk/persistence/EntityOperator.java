/**
 * 
 */
package com.ydk.persistence;

import java.lang.reflect.*;

import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;



/**
 * Provide overall operation utilities of persistent entity  
 * @author y140zhan
 *
 */
public abstract class EntityOperator<E> {
	
	/** Logger that is available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	
    private EntityManager em = null;
	private Class<E> entityClass = null;
	private E instance = null;
   

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

	/**
	 * Check EntityManager availability
	 */
	public boolean isEmValid() {
		return (this.em != null);
	}
	
	//validate that QueryOperator is ready to run
	   public void validate()
	   {
	      if ( getEntityManager()==null ) 
	      {
	         throw new IllegalStateException("entityManager is null");
	      }
	   }

	/**
	 * Get the class of the entity being managed. <br />
	 * If not explicitly specified, the generic type of implementation is used.
	 */
	@SuppressWarnings("unchecked")
	public Class<E> getEntityClass() {
		if (entityClass == null) {
			Type type = getClass().getGenericSuperclass();
			if (type instanceof ParameterizedType) {
				ParameterizedType paramType = (ParameterizedType) type;
				if (paramType.getActualTypeArguments().length == 2) {
					// likely dealing with -> new
					// EntityOperator<Person>().getEntityClass()
					if (paramType.getActualTypeArguments()[1] instanceof TypeVariable) {
						throw new IllegalArgumentException(
								"Could not guess entity class by reflection");
					}
					// { ... }.getEntityClass()
					else {
						entityClass = (Class<E>) paramType
								.getActualTypeArguments()[1];
					}
				} else {
					// likely dealing with -> new PersonOperator().getEntityClass()
					// where PersonOperator extends EntityOperator<Person>
					entityClass = (Class<E>) paramType.getActualTypeArguments()[0];
				}
			} else {
				throw new IllegalArgumentException(
						"Could not guess entity class by reflection");
			}
		}
		return entityClass;
	}
   
	/**
	 * Create a new object of class E
	 */
	private E newInstance() {
		if (getEntityClass() != null) {
			try {
				return (E) getEntityClass().newInstance();
			} catch (Exception e) {
				// TODO log some info here
			}
		}
		return null;
	}

	/**
	 * Get instance of E
	 */
	public E getInstance() {
		if (instance == null) {
			instance = newInstance();
		}
		return instance;
	}
	
	/**
	 * Set instance of E
	 */
	public void setInstance(E instance) {
			this.instance = instance;
	}

	/**
	 * Flush any changes made to the managed entity instance to the underlying
	 * database. <br />
	 * TODO If the update is successful, a log message is printed, a
	 * {@link javax.faces.application.FacesMessage} is added and a transaction
	 * success event raised.
	 * 
	 * 
	 * @return "updated" if the update is successful
	 */

	public String update() {

		// if JTA is used, should be disabled when @PersistenceContext used
//		getEntityManager().joinTransaction();
		getEntityManager().merge(getInstance());
		getEntityManager().flush();
		// TODO Maybe we should raise some message here
		return "updated";
	}
   
   /**
	 * Persist unmanaged entity instance to the underlying database. TODO If the
	 * persist is successful, a log message is printed, a
	 * {@link javax.faces.application.FacesMessage } is added and a transaction
	 * success event raised.
	 * 
	 * @return "persisted" if the persist is successful
	 */
   public String persist()
   {
	// if JTA is used, should be disabled when @PersistenceContext used
//	  getEntityManager().joinTransaction();
	
	  logger.debug("Persist Entity");
      getEntityManager().persist( getInstance());
      getEntityManager().flush();
      //TODO Maybe we should raise some message here
      return "persisted";
   }
   
   /**
    * Remove managed entity instance from the underlying database.
    * TODO If the remove is successful, a log message is printed, a 
    * {@link javax.faces.application.FacesMessage} is added and a transaction 
    * success event raised.
    * 
    * @return "removed" if the remove is successful
    */
   public String remove()
   {
	// if JTA is used, should be disabled when @PersistenceContext used
//	  getEntityManager().joinTransaction();
      getEntityManager().remove( getInstance());
      getEntityManager().flush();
      //TODO Maybe we should raise some message here
      return "removed";
   }

   
   /**
    * Utility method to load entity instance from the {@link EntityManager}. 
    * Called by {@link #find()}.
    * <br />
    * Can be overridden to support eager fetching of associations.
    * 
    * @return The entity identified by {@link EntityOperator#getEntityClass() getEntityClass()}, 
    */
   protected E loadInstance(Object key) 
   {
//	  if (key instanceof Key)
//	  {
//		  logger.debug("Making key");
//		  logger.debug("loadInstance id" + ((Key)key).getId());
//		  logger.debug("loadInstance name" + ((Key)key).getName());
//		  logger.debug("loadInstance kine" + ((Key)key).getKind());
//		  logger.debug("loadInstance parent" + ((Key)key).getParent());
//
//		  
//		  key = ((Key)key).getName();
//	  }
      return instance = getEntityManager().find(getEntityClass(), (Object)key);
   }
   
   
   /**
    * Implementation of {@link EntityOperator#find() find()} for JPA
    * 
    * @see Home#find()
    */
   @Transactional
   public E find(Object key)
   {
      if (getEntityManager().isOpen())  
      {
         E result = loadInstance(key);
         if (result==null) 
         {
            //TODO exception here;
         }
         return result;
      }
      else 
      {
         return null;
      }
   }
   
   /**
    * Implementation of {@link Home#find() find()} for JPA
    * 
    * @see Home#find()
    */
   public void joinTransaction()
   {
      if (getEntityManager().isOpen())  
      {
    	  getEntityManager().joinTransaction();
      }

   }
}
