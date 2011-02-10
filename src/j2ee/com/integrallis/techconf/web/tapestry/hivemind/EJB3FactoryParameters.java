/*
 * @(#)EJB3FactoryParameters.java	Jul 14, 2005
 *
 * Copyright 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.web.tapestry.hivemind;

/**
 * Parameters for the {@link com.integrallis.techconf.web.tapestry.hivemind.EJB3Factory}
 * 
 * @author Joseph Nusairat
 */
public class EJB3FactoryParameters {
	private String remoteInterface;

	public String getRemoteInterface() {
		return remoteInterface;
	}

	public void setRemoteInterface(String remoteInterface) {
		this.remoteInterface = remoteInterface;
	}
}
