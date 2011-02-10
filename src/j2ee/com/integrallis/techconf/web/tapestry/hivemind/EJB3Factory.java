/*
 * @(#)EJB3Factory.java	Jul 14, 2005
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
import org.apache.hivemind.internal.Module;

/**
 * An implementation of {@link org.apache.hivemind.ServiceImplementationFactory}
 * that retrieved a stateless session EJB via JNDI.
 * 
 * @author Joseph Nusairat
 * @author Brian Sam-Bodden
 */
public class EJB3Factory implements	ServiceImplementationFactory {
	private static Log log = LogFactory.getLog(EJB3Factory.class);

	/* (non-Javadoc)
	 * @see org.apache.hivemind.ServiceImplementationFactory#createCoreServiceImplementation(org.apache.hivemind.ServiceImplementationFactoryParameters)
	 */
	public Object createCoreServiceImplementation(
			ServiceImplementationFactoryParameters factoryParameters) {
		EJB3FactoryParameters proxyParameters = (EJB3FactoryParameters) factoryParameters
				.getParameters().get(0);
		String remoteInterfaceClass = proxyParameters.getRemoteInterface();

		Module module = factoryParameters.getInvokingModule();

		Class remoteInterface = module.resolveType(remoteInterfaceClass);

		Object result = null;
		try {
			InitialContext context = new InitialContext();
			result = context.lookup(remoteInterface.getName());
		} catch (Exception ex) {
			log.error("Could not retrieve service", ex);
			throw new ApplicationRuntimeException(ex);
		}
		return result;
	}

}
