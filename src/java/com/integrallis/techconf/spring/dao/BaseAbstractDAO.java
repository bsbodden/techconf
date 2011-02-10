/*
 * @(#)BaseAbstractDAO.java	Oct 29, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.spring.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BaseAbstractDAO extends HibernateDaoSupport {

	protected Object getEntityById(Class clazz, Serializable id) {
		return getHibernateTemplate().get(clazz, id);
	}
	
	protected void saveEntity(Object entity) {
		getHibernateTemplate().persist(entity);
	}
	
	protected void saveOrUpdateEntity(Object entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}	

	protected void updateEntity(Object entity) {
		getHibernateTemplate().update(entity);
	}

	protected void deleteEntity(Object entity) {
		getHibernateTemplate().delete(entity);		
	}
	
	protected void deleteEntityById(Class clazz, Serializable id) {
		Object entity = getEntityById(clazz, id);
		if (entity != null) {
		    deleteEntity(entity);	
		}				
	}	
	
    protected List findAll(Class clazz) {
    	return getHibernateTemplate().loadAll(clazz);        
    }
    
    protected List findAll(Class clazz, String orderBy) {  
    	return getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(clazz).addOrder(Order.asc(orderBy)));	        
    }   
    
	protected List findFiltered(Class clazz, String property, Object filter) {
		return getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(clazz).add(
						Expression.eq(property, filter)));		
	}    
	
	protected List findFiltered(Class clazz, String property, Object filter, String orderBy) {
		return getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(clazz).add(
						Expression.eq(property, filter)).addOrder(
						Order.asc(orderBy)));		
	}  	
	
	protected Object findUniqueFiltered(Class clazz, String property, Object filter) {
		return DataAccessUtils.requiredUniqueResult(getHibernateTemplate()
				.findByCriteria(
						DetachedCriteria.forClass(clazz).add(
								Expression.eq(property, filter))));		
	}    
	
	protected Object findUniqueFiltered(Class clazz, String property, Object filter, String orderBy) {
		return DataAccessUtils.requiredUniqueResult(getHibernateTemplate()
				.findByCriteria(
						DetachedCriteria.forClass(clazz).add(
								Expression.eq(property, filter)).addOrder(
								Order.asc(orderBy))));
	} 	

}
