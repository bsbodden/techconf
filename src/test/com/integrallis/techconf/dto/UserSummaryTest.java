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

public class UserSummaryTest {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Configuration(beforeTestClass = true)
	protected void setUp() throws ConfigurationException {
    	ConfigurationLoader.loadMapping(new File(Paths.BASEDIR + "/dd/dynadto/UserSummary.dto.xml"));
	}
	
	@SuppressWarnings("unchecked")
	@Test(groups = {"dto"})
	public void testUserSummaryCreationFromPresenter() {	
		Presenter presenter = new Presenter();
		presenter.setId(1);
		presenter.setFirstName("Brian");
		presenter.setLastName("Sam-Bodden");
		presenter.setCompany("Integrallis Software, LLC");
		presenter.setCompanyURL("http://www.integrallis.com");		
		
        Builder builder = BuilderFactory.getInstance().getBuilder(UserSummary.class);
        UserSummary userSummary = (UserSummary) builder.build(presenter);
        
        System.out.println(userSummary);
        
		Assert.assertEquals(userSummary.getId(), presenter.getId());
		Assert.assertEquals(userSummary.getDisplayName(),  presenter.getFirstName() + " " + presenter.getLastName());		
		Assert.assertEquals(userSummary.isPresenter(), new Boolean(true));
	}
	
	@SuppressWarnings("unchecked")
	@Test(groups = {"dto"})
	public void testUserSummaryCreationFromAttendee() {	
		Attendee attendee = new Attendee();
		attendee.setId(1);
		attendee.setFirstName("Brian");
		attendee.setLastName("Sam-Bodden");
		
        Builder builder = BuilderFactory.getInstance().getBuilder(UserSummary.class);
        UserSummary userSummary = (UserSummary) builder.build(attendee);
        
        System.out.println(userSummary);
        
		Assert.assertEquals(userSummary.getId(), attendee.getId());
		Assert.assertEquals(userSummary.getDisplayName(),  attendee.getFirstName() + " " + attendee.getLastName());		
		Assert.assertEquals(userSummary.isPresenter(), new Boolean(false));
	}	
}
