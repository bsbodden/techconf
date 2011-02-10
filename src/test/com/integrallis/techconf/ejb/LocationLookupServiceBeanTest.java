/*
 * @(#)LocationLookupServiceBeanTest.java	Feb 3, 2006
 *
 * Copyright (c) 2006 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.ejb;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dynadto.BuilderFactory;
import org.dynadto.ConfigurationLoader;
import org.dynadto.exception.ConfigurationException;
import org.testng.Assert;
import org.testng.annotations.Configuration;
import org.testng.annotations.Test;

import com.integrallis.techconf.dao.ZipcodeDAO;
import com.integrallis.techconf.domain.Zipcode;
import com.integrallis.techconf.dto.Location;
import com.integrallis.techconf.test.util.Paths;

/**
 * @author Brian Sam-Bodden
 */
public class LocationLookupServiceBeanTest {
	
	private LocationLookupServiceBean service;
	private ZipcodeDAO mock;

	@Configuration(beforeTestClass = true)
	protected void setUp() throws ConfigurationException {	
		ConfigurationLoader.loadMapping(new File(Paths.BASEDIR + "/dd/dynadto/Location.dto.xml"));
		
        mock = createMock(ZipcodeDAO.class);
        service = new LocationLookupServiceBean();
        service.setZipcodeDAO(mock);
        service.setBuilderFactory(BuilderFactory.getInstance());
        service.initialization();
	}
	
	@Test(groups = {"services"})
	public void testSearchLocations() {
		// data stub		
	    Zipcode stub = new Zipcode();
	    stub.setZip(43081);
	    stub.setCity("Westerville");
	    stub.setState("OH");
	    
	    List<Zipcode> stubList = new ArrayList<Zipcode>();
		stubList.add(stub);		
		
		// set expectations
		expect(mock.find("43081")).andReturn(stubList);
		replay(mock);
		
		// execute the test
		List<Location> locations = service.searchLocations("43081");		
		verify(mock);	
		
		// check results
		Assert.assertEquals(locations.size(), 1);		
		Location location = locations.get(0);		
		Assert.assertEquals(location.getZip(), stub.getZip().toString());
		Assert.assertEquals(location.getCity(), stub.getCity());
		Assert.assertEquals(location.getState(), stub.getState());		
		
	}
}
