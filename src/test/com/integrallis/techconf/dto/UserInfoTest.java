/*
 * @(#)ConferenceSummaryTest.java	Jul 19, 2005
 *
 * Copyright 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dto;

import java.io.File;

import org.dynadto.Builder;
import org.dynadto.BuilderFactory;
import org.dynadto.ConfigurationLoader;
import org.dynadto.exception.ConfigurationException;
import org.testng.Assert;
import org.testng.annotations.Configuration;
import org.testng.annotations.Test;

import com.integrallis.techconf.domain.Attendee;
import com.integrallis.techconf.domain.Presenter;
import com.integrallis.techconf.test.util.Paths;

public class UserInfoTest {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Configuration(beforeTestClass = true)
	protected void setUp() throws ConfigurationException {
    	ConfigurationLoader.loadMapping(new File(Paths.BASEDIR + "/dd/dynadto/AttendeeInfo.dto.xml"));
    	ConfigurationLoader.loadMapping(new File(Paths.BASEDIR + "/dd/dynadto/PresenterInfo.dto.xml"));
	}
	
	@SuppressWarnings("unchecked")
	@Test(groups = {"dto"})
	public void testAttendeeSummaryCreation() {	
		Attendee attendee = new Attendee();
		attendee.setId(1);
		attendee.setFirstName("Brian");
		attendee.setLastName("Sam-Bodden");
		
        Builder builder = BuilderFactory.getInstance().getBuilder(AttendeeInfo.class);
        AttendeeInfo attendeeInfo = (AttendeeInfo) builder.build(attendee);
        
        Assert.assertEquals(attendeeInfo.getId(), attendee.getId());
        Assert.assertEquals(attendeeInfo.getHomePhone(), attendee.getHomePhone());
        Assert.assertEquals(attendeeInfo.getPassword(), attendee.getPassword());
        Assert.assertEquals(attendeeInfo.getFax(), attendee.getFax());
        Assert.assertEquals(attendeeInfo.getWorkPhone(), attendee.getWorkPhone());
        Assert.assertEquals(attendeeInfo.getLastName(), attendee.getLastName());
        Assert.assertEquals(attendeeInfo.getFirstName(), attendee.getFirstName());
        Assert.assertEquals(attendeeInfo.getEmail(), attendee.getEmail());        
        
        System.out.println(attendeeInfo);        

	}	
	
	@SuppressWarnings("unchecked")
	@Test(groups = {"dto"})
	public void testPresenterSummaryCreation() {	
		Presenter presenter = new Presenter();
		presenter.setId(1);
		presenter.setFirstName("Brian");
		presenter.setLastName("Sam-Bodden");
		presenter.setCompany("Integrallis Software, LLC");
		presenter.setCompanyURL("http://www.integrallis.com");
		
        Builder builder = BuilderFactory.getInstance().getBuilder(PresenterInfo.class);
        PresenterInfo presenterInfo = (PresenterInfo) builder.build(presenter);
        
        Assert.assertEquals(presenterInfo.getId(), presenter.getId());
        Assert.assertEquals(presenterInfo.getHomePhone(), presenter.getHomePhone());
        Assert.assertEquals(presenterInfo.getPassword(), presenter.getPassword());
        Assert.assertEquals(presenterInfo.getFax(), presenter.getFax());
        Assert.assertEquals(presenterInfo.getWorkPhone(), presenter.getWorkPhone());
        Assert.assertEquals(presenterInfo.getLastName(), presenter.getLastName());
        Assert.assertEquals(presenterInfo.getFirstName(), presenter.getFirstName());
        Assert.assertEquals(presenterInfo.getEmail(), presenter.getEmail());         
        
        System.out.println(presenterInfo);        

	}		
	

}
