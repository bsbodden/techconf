/*
 * @(#)PrincingRuleTest.java	Sep 3, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.domain;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrincingRuleTest extends BaseHibernateTestCase {
	
	@Test(groups = {"persistence"})
	public void testCreateGroupPricingRule() {
		Conference conference = createConference();
		persist(conference);
		
		GroupPricingRule gpr = new GroupPricingRule();
		gpr.setActive(true);
		gpr.setConferenceId(conference.getId());
		gpr.setDiscountRate(15.0);
		gpr.setMinimumAttendees(5);
		gpr.setMaximumAttendees(10);
		gpr.setName("between5and10");
		gpr.setPriority(10);
		
		GroupPricingRule saved = (GroupPricingRule) persist(gpr);
		
  	    // get the id of the newly created entry
  	    Integer id = saved.getId();
  	    
  	    // did the db assign an id?
  	    Assert.assertNotNull(id);
  	    
  	    // load the entity from the database using the id
  	    GroupPricingRule retrieved = (GroupPricingRule) getByPk(GroupPricingRule.class, id);
  	    
  	    // did it get saved?
  	    Assert.assertNotNull(retrieved);
  	    
  	    // ok, compare the values
  	    Assert.assertEquals(gpr.getConferenceId(), retrieved.getConferenceId());
  	    Assert.assertEquals(gpr.getDiscountRate(), retrieved.getDiscountRate());
  	    Assert.assertEquals(gpr.getMaximumAttendees(), retrieved.getMaximumAttendees());
  	    Assert.assertEquals(gpr.getMinimumAttendees(), retrieved.getMinimumAttendees());
  	    Assert.assertEquals(gpr.getName(), retrieved.getName());
  	    Assert.assertEquals(gpr.getPriority(), retrieved.getPriority());
  	    
  	    delete(retrieved);  
  	    Venue venue = conference.getVenue();  	    
  	    delete(conference);
  	    delete(venue);
	}
	
	@Test(groups = {"persistence"})
	public void testCreateRegistrationDatePricingRule() {
		Conference conference = createConference();
		persist(conference);
		
		RegistrationDatePricingRule rdpr = new RegistrationDatePricingRule();
		rdpr.setActive(true);
		rdpr.setConferenceId(conference.getId());
		rdpr.setEndDate(new Date());
		rdpr.setPrice(0.0);
		rdpr.setPriority(5);
		rdpr.setStartDate(new Date());
		rdpr.setName("regdaterule");
		
		RegistrationDatePricingRule saved = (RegistrationDatePricingRule) persist(rdpr);
		
  	    // get the id of the newly created entry
  	    Integer id = saved.getId();
  	    
  	    // did the db assign an id?
  	    Assert.assertNotNull(id);
  	    
  	    // load the entity from the database using the id
  	    RegistrationDatePricingRule retrieved = (RegistrationDatePricingRule) getByPk(RegistrationDatePricingRule.class, id);
  	    
  	    // did it get saved?
  	    Assert.assertNotNull(retrieved);
  	    
  	    // ok, compare the values
  	    Assert.assertEquals(rdpr.getConferenceId(), retrieved.getConferenceId());
  	    //Assert.assertEquals(rdpr.getStartDate().getTime(), retrieved.getStartDate().getTime());
  	    //Assert.assertEquals(rdpr.getEndDate().getTime(), retrieved.getEndDate().getTime());
  	    Assert.assertEquals(rdpr.getPrice(), retrieved.getPrice());
  	    Assert.assertEquals(rdpr.getName(), retrieved.getName());
  	    Assert.assertEquals(rdpr.getPriority(), retrieved.getPriority());
  	    
  	    delete(retrieved);  
  	    Venue venue = conference.getVenue();  	    
  	    delete(conference);
  	    delete(venue);
	}
	
	@Test(groups = {"persistence"})
	public void testPolymorphicQueries() {
		Conference conference = createConference();
		persist(conference);
		
		GroupPricingRule gpr = new GroupPricingRule();
		gpr.setActive(true);
		gpr.setConferenceId(conference.getId());
		gpr.setDiscountRate(15.0);
		gpr.setMinimumAttendees(5);
		gpr.setMaximumAttendees(10);
		gpr.setName("between5and10");
		gpr.setPriority(10);	
		
		RegistrationDatePricingRule rdpr = new RegistrationDatePricingRule();
		rdpr.setActive(true);
		rdpr.setConferenceId(conference.getId());
		rdpr.setEndDate(new Date());
		rdpr.setPrice(0.0);
		rdpr.setPriority(5);
		rdpr.setStartDate(new Date());
		rdpr.setName("regdaterule");
		
		persist(gpr);
		persist(rdpr);		
		
		List allPricingRules = findAll(PricingRule.class);
		
		Assert.assertEquals(allPricingRules.size(), 2);
		
  	    for (Iterator i = allPricingRules.iterator(); i.hasNext();) {
			PricingRule pr = (PricingRule) i.next();
			if (pr.getName().equals("between5and10")) {
				Assert.assertTrue(tryToCastAsGroupPricingRule(pr));
				Assert.assertFalse(tryToCastAsRegistrationDatePricingRule(pr));
			}
			else if (pr.getName().equals("regdaterule")) {
				Assert.assertFalse(tryToCastAsGroupPricingRule(pr));
				Assert.assertTrue(tryToCastAsRegistrationDatePricingRule(pr));
			}
		}
  	    
  	    List gprs = findAll(GroupPricingRule.class);
  	    Assert.assertEquals(gprs.size(), 1);
  	    
  	    List rdprs = findAll(RegistrationDatePricingRule.class);
  	    Assert.assertEquals(rdprs.size(), 1); 

  	    delete(gpr);
  	    delete(rdpr);
  	    Venue venue = conference.getVenue();  	    
  	    delete(conference);
  	    delete(venue);
	}
	
	//
	// private methods
	//
	
	private boolean tryToCastAsGroupPricingRule(Object o) {
	    boolean result = true;
		try {
			@SuppressWarnings("unused") 
			GroupPricingRule gpr = (GroupPricingRule)o;
		} catch (ClassCastException cce) {
			result = false;
		}
		return result;		
	}
	
	private boolean tryToCastAsRegistrationDatePricingRule(Object o) {
	    boolean result = true;
		try {
			@SuppressWarnings("unused") 
			RegistrationDatePricingRule rdpr = (RegistrationDatePricingRule)o;
		} catch (ClassCastException cce) {
			result = false;
		}
		return result;		
	}
	
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
