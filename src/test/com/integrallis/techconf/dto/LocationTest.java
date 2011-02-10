/*
 * @(#)BlogEntryTest.java	Sep 29, 2005
 *
 * Copyright 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dto;

import java.io.File;
import java.net.MalformedURLException;

import org.dynadto.Builder;
import org.dynadto.BuilderFactory;
import org.dynadto.ConfigurationLoader;
import org.dynadto.exception.ConfigurationException;
import org.testng.Assert;
import org.testng.annotations.Configuration;
import org.testng.annotations.Test;

import com.integrallis.techconf.domain.Zipcode;
import com.integrallis.techconf.test.util.Paths;

public class LocationTest {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Configuration(beforeTestClass = true)
	protected void setUp() throws ConfigurationException {
		ConfigurationLoader.loadMapping(new File(Paths.BASEDIR + "/dd/dynadto/Location.dto.xml"));	
	}
	
	@SuppressWarnings("unchecked")
	@Test(groups = {"dto"})
	public void testLocationCreation() throws MalformedURLException {
	    Zipcode zipcode = new Zipcode();
	    zipcode.setZip(43081);
	    zipcode.setCity("Westerville");
	    zipcode.setState("OH");
		
        Builder builder = BuilderFactory.getInstance().getBuilder(Location.class);
        Location location = (Location) builder.build(zipcode);
        
        System.out.println(location);
        
		Assert.assertEquals(location.getZip(), zipcode.getZip().toString());
		Assert.assertEquals(location.getCity(), zipcode.getCity());
		Assert.assertEquals(location.getState(), zipcode.getState());	

	}
	
	

}
