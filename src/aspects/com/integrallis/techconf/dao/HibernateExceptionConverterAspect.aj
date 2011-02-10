/*
 * @(#)HibernateExceptionConverterAspect.java	Aug 28, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dao;

import org.hibernate.HibernateException;
import org.hibernate.ObjectDeletedException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.UnresolvableObjectException;
import org.hibernate.WrongClassException;
import org.hibernate.QueryException;

import com.integrallis.techconf.dao.exception.*;

public aspect HibernateExceptionConverterAspect {
	pointcut daoClasses() : call(* com.integrallis.techconf.ejb.dao.hibernate.*DAO*.*(..));
	pointcut testClasses() : call(* com.integrallis.techconf.dao.hibernate.*Test.*(..));
	
    Object around() : daoClasses() || testClasses() {
    	try {
    		return proceed();    		
    	}
    	catch (Throwable t) {
    		convertAndThrow(t);
    	} 
    	return null;
    }
    
    /**
     * Converts a HibernateException to a custom DAO exception
     * @param t
     */
    public static void convertAndThrow(Throwable t) {
    	/**
    	 * Catch any Hibernate QueryExceptions and retrow
    	 * the as ObjectQueryException
    	 */
    	if (t instanceof QueryException) {
    		QueryException ex = (QueryException)t;
    		ObjectQueryException oqe = new ObjectQueryException(t);
    		oqe.setQueryString(ex.getQueryString());
    		throw oqe;
    	}
    	/**
    	 * Catch Hibernate's Object retrieval exceptions and retrow
    	 * as ObjectRetrievalException 
    	 */
    	else if (t instanceof UnresolvableObjectException) {
    		UnresolvableObjectException ex = (UnresolvableObjectException)t;
    		throw new ObjectRetrievalFailureException(ex.getEntityName(), ex.getIdentifier(), ex.getMessage(), ex);
    	}
    	else if (t instanceof ObjectNotFoundException) {
    		ObjectNotFoundException ex = (ObjectNotFoundException)t;
    		throw new ObjectRetrievalFailureException(ex.getEntityName(), ex.getIdentifier(), ex.getMessage(), ex);
    	}
    	else if (t instanceof ObjectDeletedException) {
    		ObjectDeletedException ex = (ObjectDeletedException)t;
    		throw new ObjectRetrievalFailureException(ex.getEntityName(), ex.getIdentifier(), ex.getMessage(), ex);
    	}
    	else if (t instanceof WrongClassException) {
    		WrongClassException ex = (WrongClassException)t;
    		throw new ObjectRetrievalFailureException(ex.getEntityName(), ex.getIdentifier(), ex.getMessage(), ex);
    	}	
    	/**
    	 * Catch any HibernateException
    	 */
    	else if (t instanceof HibernateException) {
    		throw new DataAccessSystemException(t);
    	}
    	/**
    	 * If hibernate throws anything else that is not a HibernateException
    	 * or a child of it, then retrow it as a RuntimeException
    	 */
    	else {
    		throw new RuntimeException(t);
    	}
    }
}
