/*
 * @(#)JNDILookupFactory.java	Jul 14, 2005
 *
 * Copyright 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.web.tapestry.hivemind;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hivemind.ApplicationRuntimeException;
import org.apache.hivemind.ServiceImplementationFactory;
import org.apache.hivemind.ServiceImplementationFactoryParameters;

/**
 * An implementation of {@link org.apache.hivemind.ServiceImplementationFactory}
 * that retrieved an object from JNDI.
 * 
 * @author Joseph Nusairat
 * @author Brian Sam-Bodden
 */
public class JNDILookupFactory implements ServiceImplementationFactory {
	private static Log log = LogFactory.getLog(JNDILookupFactory.class); 	

	public Object createCoreServiceImplementation(
			ServiceImplementationFactoryParameters factoryParameters) {
		
		JNDILookupParameters proxyParameters = (JNDILookupParameters) factoryParameters.getParameters().get(0);
		String resourceName = proxyParameters.getResourceName();

		Object result = null;
		try {
			InitialContext context = new InitialContext();
			result = context.lookup(resourceName);
		} catch (Exception ex) {
			log.error("Could not retrieve service", ex);
			throw new ApplicationRuntimeException(ex);
		}
		return result;
	}

}
