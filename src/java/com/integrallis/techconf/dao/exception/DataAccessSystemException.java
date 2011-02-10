/*
 * @(#)DataAccessSystemException.java	Aug 26, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dao.exception;


/**
 * Represents an unclassified exception in the underlying data
 * access mechanism backing the DAO implementation
 * 
 * @author Brian Sam-Bodden
 */
public class DataAccessSystemException extends DataAccessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8194728583670429731L;

	/**
	 * @param message
	 */
	public DataAccessSystemException(Throwable t) {
		super(t.getMessage(), t);
	}


}
