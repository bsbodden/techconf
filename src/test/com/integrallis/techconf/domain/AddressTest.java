/*
 * @(#)AddressTest.java	Sep 3, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.domain;

import org.testng.Assert;
import org.testng.annotations.Test;


public class AddressTest extends BaseHibernateTestCase {
	
	// some values
	private final static String APT_NUMBER = "N/A";
	private final static String CITY = "Westerville";
	private final static String STATE = "OH";
	private final static String STREET_ADDRESS = "204 Bluestone Court";
	private final static String ZIP_CODE = "43081";	
	
	@Test(groups = {"persistence"})
	public void testCreateAddress() {
    	// create and populate an address object
  	    Address address = new Address(); 
  	    address.setAptNumber(APT_NUMBER);
  	    address.setCity(CITY);
  	    address.setState(STATE);
  	    address.setStreetAddress(STREET_ADDRESS);
  	    address.setZipCode(ZIP_CODE);
  	    
  	    // save the address to the database
  	    Address savedAddress = (Address) persist(address);
  	    
  	    // get the id of the newly created entry
  	    Integer id = savedAddress.getId();
  	    
  	    // did the db assign an id?
  	    Assert.assertNotNull(id);
  	    
  	    // load the address from the database using the id
  	    Address retrievedAddress = (Address) getByPk(Address.class, id);
  	    
  	    // did it get saved?
  	    Assert.assertNotNull(retrievedAddress);
  	    
  	    // ok, compare the values
  	    Assert.assertEquals(retrievedAddress.getAptNumber(), APT_NUMBER);
  	    Assert.assertEquals(retrievedAddress.getCity(), CITY);
  	    Assert.assertEquals(retrievedAddress.getState(), STATE);
  	    Assert.assertEquals(retrievedAddress.getStreetAddress(), STREET_ADDRESS);
  	    Assert.assertEquals(retrievedAddress.getZipCode(), ZIP_CODE);
  	    
  	    //clean up
  	    delete(retrievedAddress);		
	}
 

}
