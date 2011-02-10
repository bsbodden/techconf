/*
 * @(#)ObjectRetrievalFailureException.java	Aug 26, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dao.exception;

/**
 * Used to wrap Exceptions produced by the underlying data access mechanism that
 * represent errors while accessing an object from 
 * @author Brian Sam-Bodden
 */
public class ObjectRetrievalFailureException extends DataAccessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2325551798402017997L;

	private Object persistentClass;

	private Object identifier;

	/**
	 * Create a new ObjectRetrievalFailureException for the given object,
	 * with the default "not found" message.
	 * @param persistentClass the persistent class
	 * @param identifier the ID of the object that should have been retrieved
	 */
	public ObjectRetrievalFailureException(Class persistentClass, Object identifier) {
		this(persistentClass, identifier,
				"Object of class [" + persistentClass.getName() + "] with identifier [" + identifier + "]: not found",
				null);
	}

	/**
	 * Create a new ObjectRetrievalFailureException for the given object,
	 * with the given explicit message and exception.
	 * @param persistentClass the persistent class
	 * @param identifier the ID of the object that should have been retrieved
	 * @param msg exception message
	 * @param ex source exception
	 */
	public ObjectRetrievalFailureException(
			Class persistentClass, Object identifier, String msg, Throwable ex) {
		super(msg, ex);
		this.persistentClass = persistentClass;
		this.identifier = identifier;
	}

	/**
	 * Create a new ObjectRetrievalFailureException for the given object,
	 * with the default "not found" message.
	 * @param persistentClassName the name of the persistent class
	 * @param identifier the ID of the object that should have been retrieved
	 */
	public ObjectRetrievalFailureException(String persistentClassName, Object identifier) {
		this(persistentClassName, identifier,
				"Object of class [" + persistentClassName + "] with identifier [" + identifier + "]: not found",
				null);
	}

	/**
	 * Create a new ObjectRetrievalFailureException for the given object,
	 * with the given explicit message and exception.
	 * @param persistentClassName the name of the persistent class
	 * @param identifier the ID of the object that should have been retrieved
	 * @param msg exception message
	 * @param ex source exception
	 */
	public ObjectRetrievalFailureException(
			String persistentClassName, Object identifier, String msg, Throwable ex) {
		super(msg, ex);
		this.persistentClass = persistentClassName;
		this.identifier = identifier;
	}
	
	/**
	 * Return the persistent class of the object that was not found.
	 * If no Class was specified, this method returns null.
	 */
	public Class getPersistentClass() {
		return (this.persistentClass instanceof Class ? (Class) this.persistentClass : null);
	}

	/**
	 * Return the name of the persistent class of the object that was not found.
	 * Will work for both Class objects and String names.
	 */
	public String getPersistentClassName() {
		return (this.persistentClass instanceof Class ?
				((Class) this.persistentClass).getName() : this.persistentClass.toString());
	}

	/**
	 * Return the identifier of the object that was not found.
	 */
	public Object getIdentifier() {
		return identifier;
	}


}
