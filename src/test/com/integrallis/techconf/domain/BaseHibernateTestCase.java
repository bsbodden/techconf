/*
 * @(#)BaseHibernateTestCase.java	Sep 2, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.integrallis.techconf.test.util.Paths;

public abstract class BaseHibernateTestCase {
	// logger
	protected static Log logger = LogFactory.getLog(BaseHibernateTestCase.class);
	
	// hibernate session factory
	protected SessionFactory factory;
	
	// persistent classes to be used in the test(s)
	protected List<Class> persistentClasses = new ArrayList<Class>();
	
	@org.testng.annotations.Configuration(beforeTestClass = true)
	protected void setUp() throws FileNotFoundException, IOException {
		logger.info("[BaseHibernateTestCase] hibernate initializing...");
		
		// load build properties
        Properties properties = new Properties();
        properties.load(new FileInputStream(Paths.BASEDIR + "/build.properties"));
		
        // load hibernate cfg
		File configFile = new File(Paths.BASEDIR +"/dd/hibernate/hibernate.cfg.xml");
		
		Configuration configuration = null;
		if (configFile.exists()) {
		    configuration = new Configuration().configure(configFile);
		}
		else {
			configuration = new Configuration();
		}
		
        String dialect = properties.getProperty("test.db.hibernate.dialect");
        String driver = properties.getProperty("test.db.driver");
        String url = properties.getProperty("test.db.url");
        String user = properties.getProperty("test.db.username");
        String password = properties.getProperty("test.db.password");
        
        configuration.setProperty("hibernate.dialect", dialect);
        configuration.setProperty("hibernate.connection.driver_class", driver);
        configuration.setProperty("hibernate.connection.url", url);
        configuration.setProperty("hibernate.connection.username", user);
        configuration.setProperty("hibernate.connection.password", password);        
		
        addPersistentClasses();
        
        for (Iterator i = persistentClasses.iterator(); i.hasNext();) {
			Class clazz = (Class) i.next();
			configuration.addClass(clazz);
		}
        
        factory = configuration.buildSessionFactory();	
        logger.info("[BaseHibernateTestCase] hibernate initialized");
	}
	
	protected void addPersistentClasses() {};
	
    protected Object persist(Object object) {
		Session session = null;
		Transaction tx = null;
	    try {
	    	session = factory.openSession();
	        tx = session.beginTransaction();        
	        session.persist(object);	        
	        tx.commit();	        
	    } catch (Exception ex) {
	    	if (tx != null) {
	    	    tx.rollback();
	    	}	       
	    } finally {
    		session.close();
	    } 
	    return object;
    }	
	
	
    protected void delete(Object object) {
		Session session = null;
		Transaction tx = null;
	    try {
	    	session = factory.openSession();
	        tx = session.beginTransaction();        
	        session.delete(object);	        
	        tx.commit();
	    } catch (Exception ex) {
	    	if (tx != null) {
	            tx.rollback();
	    	}	       
	    } finally {
	    	session.close();
	    }    	
    }  
    
    protected void delete(Class c, Serializable pk) {
        Object o = getByPk(c, pk);
        delete(o);
    }      
    
    protected Object getByPk(Class c, Serializable pk) {
    	Object result = null;
    	Session session = null;
	    try {
	    	session = factory.openSession();
	        result = session.get(c, pk);	        
	    } finally {
    		session.close();
	    } 
	    return result;    	
    } 
    
    protected List findAll(Class clazz) {
    	List result = Collections.EMPTY_LIST;
    	Session session = null;
	    try {
	    	session = factory.openSession();
	        result = createCriteria(clazz, session).list();	        
	    } finally {
    		session.close();
	    } 
	    return result; 
    }
	 
	 protected Criteria createCriteria(Class c, Session s) {
		 return s.createCriteria(c);
     }	
	 
	 protected Session getSession() {
	     return factory.openSession();
	 }
  
}
