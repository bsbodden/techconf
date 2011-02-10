/*
 * @(#)HibernateExceptionConverterAspectTest.java	Aug 28, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dao.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.ObjectDeletedException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.QueryException;
import org.hibernate.UnresolvableObjectException;
import org.hibernate.WrongClassException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.integrallis.techconf.dao.exception.DataAccessSystemException;
import com.integrallis.techconf.dao.exception.ObjectQueryException;
import com.integrallis.techconf.dao.exception.ObjectRetrievalFailureException;

public class HibernateExceptionConverterAspectTest {
	private static final String FAKE_QUERY = "fake query";

	@Test(groups = {"aspects"})
    public void testQueryException() {
    	try {
			throwHibernateQueryException();
		} catch (Exception e) {
			Assert.assertTrue(e instanceof ObjectQueryException);
		}
    }
	
	@Test(groups = {"aspects"})
    public void testUnresolvableObjectException() {
    	try {
    		throwHibernateUnresolvableObjectException();
		} catch (Exception e) {
			Assert.assertTrue(e instanceof ObjectRetrievalFailureException);
		}
    }
	
	@Test(groups = {"aspects"})
    public void testObjectNotFoundException() {
    	try {
    		throwHibernateObjectNotFoundException();
		} catch (Exception e) {
			Assert.assertTrue(e instanceof ObjectRetrievalFailureException);
		}
    }	
	
	@Test(groups = {"aspects"})
    public void testObjectDeletedException() {
    	try {
    		throwHibernateObjectDeletedException();
		} catch (Exception e) {
			Assert.assertTrue(e instanceof ObjectRetrievalFailureException);
		}
    }	
	
	@Test(groups = {"aspects"})
    public void testWrongClassException() {
    	try {
    		throwHibernateWrongClassException();
		} catch (Exception e) {
			Assert.assertTrue(e instanceof ObjectRetrievalFailureException);
		}
    }	
	
	@Test(groups = {"aspects"})
    public void testHibernateException() {
    	try {
    		throwHibernateException();
		} catch (Exception e) {
			Assert.assertTrue(e instanceof DataAccessSystemException);
		}
    }	
	
	//
	// throw the different hibernate exceptions to be intercepted
	//
	
	private void throwHibernateQueryException() {
		throw new QueryException(FAKE_QUERY);
	}
	
	private void throwHibernateUnresolvableObjectException() {
		throw new UnresolvableObjectException(null, "StringArg");
	}
	
	private void throwHibernateObjectNotFoundException() {
		throw new ObjectNotFoundException(null, "StringArg");
	}	
	
	private void throwHibernateObjectDeletedException() {
		throw new ObjectDeletedException("StringArg1", null, "StringArg2");
	}	
	
	private void throwHibernateWrongClassException() {
		throw new WrongClassException("StringArg1", null, "StringArg2");
	}	
	
	private void throwHibernateException() {
		throw new HibernateException("StringArg");
	}	
}
