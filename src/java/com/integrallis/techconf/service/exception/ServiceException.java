/*
 * @(#)ServiceException.java	Sep 28, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.service.exception;

/**
 * @author Brian Sam-Bodden
 */
public class ServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2982144784685065244L;

	/**
	 * @param message
	 */
	public ServiceException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public ServiceException(Throwable cause) {
		super(cause);
	}

}
