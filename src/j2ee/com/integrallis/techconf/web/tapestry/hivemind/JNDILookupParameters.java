/*
 * @(#)JNDILookupParameters.java	Jul 14, 2005
 *
 * Copyright 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.web.tapestry.hivemind;

/**
 * The named resource paramater.
 * 
 * @author Joseph Nusairat
 */
public class JNDILookupParameters {
	
	private String resourceName;

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}	
}
