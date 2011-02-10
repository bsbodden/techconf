/*
 * @(#)LoggingAndTracingAspect.java	Sep 27, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.logging;

import org.aspectj.lang.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Basic Aspect for Logging and Tracing
 * It traces entering, exiting and exceptions for 
 * method implementing the interfaces in the DAO and
 * Services packages
 * 
 * Other Ideas:
 * - Track user interaction with the app if inside an EJB
 *   by using ejbContext().getPrincipal().getName()
 * - Should I apply naming convention to specific methods to
 *   be able to control logging in a more granular way?
 *  
 * @author Brian Sam-Bodden
 */
public aspect LoggingAndTracingAspect {
	
	declare precedence : LoggingAndTracingAspect, *;

	/**
	 * Pointcut for:
	 * - all methods implementing the DAO interfaces
	 * - all methods implementing the Service interfaces
	 * - exclude this aspect
	 * - exclude calls to toString, hashCode, equals
	 */
    pointcut traceMethods() : 
    	execution(* com.integrallis.techconf.dao.*DAO.*(..)) 
    	|| execution(* com.integrallis.techconf.service.*.*(..))
	    && !within(LoggingAndTracingAspect)
	    && !cflow(execution(boolean *.equals(..)))
	    && !cflow(execution(int *.hashCode()))
	    && !cflow(execution(String *.toString()));

    /**
     * 
     */
    before() : traceMethods() {
	    Signature signature = thisJoinPointStaticPart.getSignature();
        traceMethodWithMessage(signature, "Entering");    
    } 
    
    after() returning : traceMethods() {
	    Signature signature = thisJoinPointStaticPart.getSignature();
        traceMethodWithMessage(signature, "Exiting"); 
    } 
    
    after() throwing(Throwable t) : traceMethods() {
	    Signature signature = thisJoinPointStaticPart.getSignature();
        traceMethodWithMessage(signature, "Exception", t); 
    }   
    
    //
    // private methods
    //
    
    private void traceMethodWithMessage(Signature signature, String message) {
    	traceMethodWithMessage(signature, message, null);
    }
    
    private void traceMethodWithMessage(Signature signature, String message, Throwable t) {
        Log log = LogFactory.getLog(signature.getDeclaringType());
        if (log.isTraceEnabled()) {
        	String logMessage = message + " [" + getMethodName(signature) + "]" + (t != null ? t : "");
            log.trace(logMessage);
        }
    }
    
    private String getMethodName(Signature signature) {
    	return signature.getDeclaringType().getName() + "." + signature.getName();
    }    
}
