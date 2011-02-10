/*
 * @(#)Location.java	Dec 25, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dto;

import org.dynadto.DTO;

/**
 * @author Brian Sam-Bodden
 */
public interface Location extends DTO {
	/**
	 * @return Returns the city.
	 */
	String getCity();
	
	/**
	 * @return Returns the state.
	 */
	String getState();
	
	/**
	 * @return Returns the zip.
	 */
	String getZip();
	
	/**
	 * @return Return the string representation
	 */
	String toString();
}
