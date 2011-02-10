/*
 * @(#)NoSuchUserException.java	Sep 28, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.service.exception;

public class NoSuchUserException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7273956384121740195L;
	
	public NoSuchUserException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public NoSuchUserException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public NoSuchUserException(Throwable cause) {
		super(cause);
	}
	

}
