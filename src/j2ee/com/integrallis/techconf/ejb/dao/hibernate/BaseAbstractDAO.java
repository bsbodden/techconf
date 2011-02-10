/*
 * @(#)BaseAbstractDAO.java	Aug 26, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.ejb.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

/**
 * Wraps some basic Hibernate methods to simplify the concrete
 * DAO implementations
 * 
 * @author Brian Sam-Bodden
 */
public abstract class BaseAbstractDAO {

	@Resource(name = "java:/hibernate/SessionFactory")
	protected SessionFactory sessionFactory;
	
	public BaseAbstractDAO() {
	}
	
	public static final int SESSION_RETRIEVAL_STRATEGY_USE_CURRENT = 0;
	public static final int SESSION_RETRIEVAL_STRATEGY_CREATE_NEW = 1;
	
	protected int sessionRetrievalStrategy = SESSION_RETRIEVAL_STRATEGY_USE_CURRENT;
	
    protected void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	protected Session getSession() {
		Session result = null;
		if (sessionRetrievalStrategy == SESSION_RETRIEVAL_STRATEGY_USE_CURRENT) {
			result = sessionFactory.getCurrentSession();
		}
		else {
			result = sessionFactory.openSession();
		}
		return result;
	}
	
	
	
	protected Criteria createCriteria(Class clazz) {
		return getSession().createCriteria(clazz);
	}
	
	protected Query createQuery(String query) {
		return getSession().createQuery(query);
	}	
	
	protected Object getEntityById(Class clazz, Serializable id) {
		return getSession().get(clazz, id);
	}
	
	protected void saveEntity(Object entity) {
		getSession().persist(entity);
	}
	
	protected void saveOrUpdateEntity(Object entity) {
		getSession().saveOrUpdate(entity);
	}	

	protected void updateEntity(Object entity) {
		getSession().update(entity);
	}

	protected void deleteEntity(Object entity) {
		getSession().delete(entity);		
	}
	
	protected void deleteEntityById(Class clazz, Serializable id) {
		Object entity = getEntityById(clazz, id);
		if (entity != null) {
		    deleteEntity(entity);	
		}				
	}	
	
    protected List findAll(Class clazz) {
    	Session session = getSession();
    	if (session != null) {
    		System.out.println("Again, Session is not null");
    	}
    	return session.createCriteria(clazz).list();	        
    }
    
    protected List findAll(Class clazz, String orderBy) {    	
    	return getSession()
    	    .createCriteria(clazz)
    	    .addOrder(Order.asc(orderBy))
    	    .list();	        
    }   
    
	protected List findFiltered(Class clazz, String property, Object filter) {
    	return getSession()
	        .createCriteria(clazz)
	        .add(Expression.eq(property, filter))
	        .list();		
	}    
	
	protected List findFiltered(Class clazz, String property, Object filter, String orderBy) {
    	return getSession()
	        .createCriteria(clazz)
	        .add(Expression.eq(property, filter))
	        .addOrder(Order.asc(orderBy))
	        .list();		
	}  	
	
	protected Object findUniqueFiltered(Class clazz, String property, Object filter) {
    	return getSession()
	        .createCriteria(clazz)
	        .add(Expression.eq(property, filter))
	        .uniqueResult();		
	}    
	
	protected Object findUniqueFiltered(Class clazz, String property, Object filter, String orderBy) {
    	return getSession()
	        .createCriteria(clazz)
	        .add(Expression.eq(property, filter))
	        .addOrder(Order.asc(orderBy))
	        .uniqueResult();		
	}

	/**
	 * @param sessionRetrievalStrategy The sessionRetrievalStrategy to set.
	 */
	public void setSessionRetrievalStrategy(int sessionRetrievalStrategy) {
		this.sessionRetrievalStrategy = sessionRetrievalStrategy;
	} 	

}
