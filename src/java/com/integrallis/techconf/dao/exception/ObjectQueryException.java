/*
 * @(#)ObjectQueryException.java	Aug 26, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dao.exception;

/**
 * Represents an exception on the object query language of the 
 * underlying data access implementation
 * 
 * @author Brian Sam-Bodden
 */
public class ObjectQueryException extends DataAccessException {
	protected String queryString;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1123714513480262342L;

	public ObjectQueryException(Throwable t) {
		super(t.getMessage(), t);
	}

	/**
	 * @return Returns the queryString.
	 */
	public String getQueryString() {
		return queryString;
	}

	/**
	 * @param queryString The queryString to set.
	 */
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

}
