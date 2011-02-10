/*
 * @(#)InvalidPasswordException.java	Sep 28, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.service.exception;

public class InvalidPasswordException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2999337588413029491L;
	
	public InvalidPasswordException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InvalidPasswordException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public InvalidPasswordException(Throwable cause) {
		super(cause);
	}

}
