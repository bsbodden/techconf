/*
 * @(#)ConferenceSummaryTest.java	Jul 19, 2005
 *
 * Copyright 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dto;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dynadto.Builder;
import org.dynadto.BuilderFactory;
import org.dynadto.ConfigurationLoader;
import org.dynadto.exception.ConfigurationException;
import org.testng.Assert;
import org.testng.annotations.Configuration;
import org.testng.annotations.Test;

import com.integrallis.techconf.domain.Address;
import com.integrallis.techconf.domain.Conference;
import com.integrallis.techconf.domain.Track;
import com.integrallis.techconf.domain.Venue;
import com.integrallis.techconf.test.util.Paths;

public class ConferenceSummaryTest {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Configuration(beforeTestClass = true)
	protected void setUp() throws ConfigurationException {
    	ConfigurationLoader.loadMapping(new File(Paths.BASEDIR + "/dd/dynadto/ConferenceSummary.dto.xml"));	
    	ConfigurationLoader.loadMapping(new File(Paths.BASEDIR + "/dd/dynadto/TrackSummary.dto.xml"));
	}
	
	@SuppressWarnings("unchecked")
	@Test(groups = {"dto"})
	public void testConferenceSummaryCreation() {
		// utility objects
		Date today = new Date();
		
		// 
		String confDesc = "A Test Conference";
		String confName = "TestConf 2005";
		Integer id1 = new Integer(1);
		String fax = "614.867.5309";
		String venueName = "Integrallis Campus";
		String venuePhone = "614.888.8888";
		String appNumber = "n/a";
		String city = "Westerville";
		String state = "OH";
		String streetAddress = "204 Bluestone Court";
		String zipCode = "43081";
		
		String addressLine2 = city + ", " + state + " " + zipCode;
		
		// create the required domain objects
		Conference conference = new Conference();
		conference.setAbstractSubmissionEndDate(today);
		conference.setAbstractSubmissionStartDate(today);
		conference.setDescription(confDesc);
		conference.setEndDate(today);
		conference.setId(id1);
		conference.setName(confName);
		conference.setStartDate(today);
		
		// venue
		Venue venue = new Venue();
		venue.setFax(fax);
		venue.setId(id1);
		venue.setName(venueName);
		venue.setPhone(venuePhone);		
		conference.setVenue(venue);
		
		// venue-->address
		Address address = new Address();
		address.setAptNumber(appNumber);
		address.setCity(city);
		address.setId(id1);
		address.setState(state);
		address.setStreetAddress(streetAddress);
		address.setZipCode(zipCode);
		
		venue.setAddress(address);	
		
		String jseTrackName = "JSE";
		String jeeTrackName = "JEE";
		String jmeTrackName = "JME";
		
		List<String> trackNames = new ArrayList<String>();
		trackNames.add(jseTrackName);
		trackNames.add(jeeTrackName);
		trackNames.add(jmeTrackName);
		
		// tracks
		Track jseTrack = new Track();
		jseTrack.setDescription("Learn how to build powerful Java desktop applications");
		jseTrack.setId(new Integer(1));
		jseTrack.setSubtitle("Java Standard Edition");
		jseTrack.setTitle(jseTrackName);
		
		Track jeeTrack = new Track();
		jeeTrack.setDescription("Learn how to build powerful Enterprise applications");
		jeeTrack.setId(new Integer(2));
		jeeTrack.setSubtitle("Java Enterprise Edition");
		jeeTrack.setTitle(jeeTrackName);		
		
		Track jmeTrack = new Track();
		jmeTrack.setDescription("Learn how to bring cellphone and PDAs alive with Java");
		jmeTrack.setId(new Integer(3));
		jmeTrack.setSubtitle("Java Micro Edition");
		jmeTrack.setTitle(jmeTrackName);
		
		conference.addTrack(jseTrack);
		conference.addTrack(jeeTrack);
		conference.addTrack(jmeTrack);
        
        Builder builder = BuilderFactory.getInstance().getBuilder(ConferenceSummary.class);
        ConferenceSummary conferenceSummary = (ConferenceSummary) builder.build("conference", conference);

		Assert.assertEquals(confDesc, conferenceSummary.getConferenceSubtitle());
		Assert.assertEquals(confName, conferenceSummary.getConferenceTitle());
		Assert.assertEquals(id1.intValue(), conferenceSummary.getConferenceId());
		Assert.assertEquals(venueName, conferenceSummary.getVenueName());
		Assert.assertEquals(venuePhone, conferenceSummary.getVenuePhone());
		Assert.assertEquals(streetAddress, conferenceSummary.getVenueAddressLine1());
		Assert.assertEquals(addressLine2, conferenceSummary.getVenueAddressLine2());	

		Assert.assertEquals(today, conferenceSummary.getStartDate());
		Assert.assertEquals(today, conferenceSummary.getEndDate());
		Assert.assertEquals(today, conferenceSummary.getAbstractSubmissionStartDate());
		Assert.assertEquals(today, conferenceSummary.getAbstractSubmissionEndDate());
		
		List<TrackSummary> trackSummaries = conferenceSummary.getTracks();
		if (trackSummaries != null) {
			for (TrackSummary trackSummary : trackSummaries) {
				assert trackNames.contains(trackSummary.getTitle());
			}			
		}
        
	}
	
	

}
