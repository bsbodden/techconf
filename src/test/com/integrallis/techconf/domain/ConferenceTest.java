/*
 * @(#)ConferenceTest.java	Sep 3, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.domain;

import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ConferenceTest extends BaseHibernateTestCase {

	@Test(groups = {"persistence"})
	public void testCreateConference() {
		Conference conference = createConference();
		
  	    // save the user to the database
  	    Conference savedConference = (Conference) persist(conference);
  	    
  	    // get the id of the newly created entry
  	    Integer id = savedConference.getId();
  	    
  	    // did the db assign an id?
  	    Assert.assertNotNull(id);
  	    
  	    // load the user from the database using the id
  	    Conference retrievedConference = (Conference) getByPk(Conference.class, id);
  	    
  	    Assert.assertNotNull(retrievedConference);
  	    
  	    Venue venue = retrievedConference.getVenue();
  	    
  	    Integer venueId = venue.getId();
  	    
  	    Assert.assertNotNull(venue);
  	    
  	    Address address = venue.getAddress();
  	    
  	    Integer addressId = address.getId();
  	    
  	    Assert.assertNotNull(address);
  	    
  	    //clean up
  	    delete(retrievedConference);
  	    
  	    // test cascading delete - should not delete venue
  	    
  	    // load the address from the database using the id
  	    Venue retrievedVenue = (Venue) getByPk(Venue.class, venueId);
  	    
  	    // did it get deleted?
  	    Assert.assertNotNull(retrievedVenue);   	    
  	    
  	    // load the address from the database using the id
  	    Assert.assertNotNull(getByPk(Address.class, addressId));
  	    
  	    // now delete the venue  	    
  	    delete(retrievedVenue);
  	    
  	    // test the cascading delete
  	    
  	    // load the address from the database using the id
  	    Assert.assertNull(getByPk(Address.class, addressId));
	}
	
	//
	// private methods
	//
	
	private Conference createConference() {
		Conference conference = new Conference();

		// utility objects
		Date today = new Date();
		
		String confDesc = "A Test Conference";
		String confName = "TestConf 2005";
		String fax = "614.867.5309";
		String venueName = "Integrallis Campus";
		String venuePhone = "614.888.8888";
		String appNumber = "n/a";
		String city = "Westerville";
		String state = "OH";
		String streetAddress = "204 Bluestone Court";
		String zipCode = "43081";
		
		conference.setAbstractSubmissionEndDate(today);
		conference.setAbstractSubmissionStartDate(today);
		conference.setDescription(confDesc);
		conference.setEndDate(today);
		conference.setName(confName);
		conference.setStartDate(today);
		
		// venue
		Venue venue = new Venue();
		venue.setFax(fax);
		venue.setName(venueName);
		venue.setPhone(venuePhone);
		
		// venue-->address
		Address address = new Address();
		address.setAptNumber(appNumber);
		address.setCity(city);
		address.setState(state);
		address.setStreetAddress(streetAddress);
		address.setZipCode(zipCode);
		
		venue.setAddress(address);	
		
		conference.setVenue(venue);
		
		return conference;
	}
}
