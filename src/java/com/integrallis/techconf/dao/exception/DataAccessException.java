/*
 * @(#)DataAccessException.java	Aug 25, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dao.exception;


/**
 * Base class of the custom DAO layer exception hierarchy
 * @author Brian Sam-Bodden
 */
public abstract class DataAccessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 541007991385682341L;
	
	/**
	 * @param message
	 */
	public DataAccessException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public DataAccessException(Throwable cause) {
		super(cause);
	}

}
