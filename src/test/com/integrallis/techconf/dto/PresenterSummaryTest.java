/*
 * @(#)ConferenceSummaryTest.java	Jul 19, 2005
 *
 * Copyright 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dto;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dynadto.Builder;
import org.dynadto.BuilderFactory;
import org.dynadto.ConfigurationLoader;
import org.dynadto.exception.ConfigurationException;
import org.testng.Assert;
import org.testng.annotations.Configuration;
import org.testng.annotations.Test;

import com.integrallis.techconf.domain.Presenter;
import com.integrallis.techconf.test.util.Paths;

public class PresenterSummaryTest {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Configuration(beforeTestClass = true)
	protected void setUp() throws ConfigurationException {
    	ConfigurationLoader.loadMapping(new File(Paths.BASEDIR + "/dd/dynadto/PresenterSummary.dto.xml"));
    	ConfigurationLoader.loadMapping(new File(Paths.BASEDIR + "/dd/dynadto/Link.dto.xml"));
	}
	
	@Test(groups = {"dto"})	
	protected void testLinkFromPresenterCreation() {
		Presenter presenter = new Presenter();
		presenter.setId(1);
		presenter.setFirstName("Brian");
		presenter.setLastName("Sam-Bodden");
		presenter.setCompany("Integrallis Software, LLC");
		presenter.setCompanyURL("http://www.integrallis.com");		
		
        Builder builder = BuilderFactory.getInstance().getBuilder(Link.class);
        Link link = (Link) builder.build(presenter);
        
		Assert.assertEquals(presenter.getCompany(), link.getName());
		Assert.assertEquals(presenter.getCompanyURL(), link.getURL());		
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
		
        Builder builder = BuilderFactory.getInstance().getBuilder(PresenterSummary.class);
        PresenterSummary presenterSummary = (PresenterSummary) builder.build(presenter);
        
		Assert.assertEquals(presenter.getId(), presenterSummary.getPresenterId());
		Assert.assertEquals(presenter.getFirstName() + " " + presenter.getLastName(), presenterSummary.getName());		
		Assert.assertEquals(presenter.getCompany(), presenterSummary.getCompany().getName());
		Assert.assertEquals(presenter.getCompanyURL(), presenterSummary.getCompany().getURL());
   
	}
	
	@SuppressWarnings("unchecked")
	@Test(groups = {"dto"})
	public void testPresenterSummaryList() {
		Presenter presenter1 = new Presenter();
		presenter1.setId(1);
		presenter1.setFirstName("Brian");
		presenter1.setLastName("Sam-Bodden");
		presenter1.setCompany("Integrallis Software, LLC");
		presenter1.setCompanyURL("http://www.integrallis.com");
		
		Presenter presenter2 = new Presenter();
		presenter2.setId(2);
		presenter2.setFirstName("Joseph");
		presenter2.setLastName("Nusairat");
		presenter2.setCompany("Joseph's Web Shop");
		presenter2.setCompanyURL("http://www.deedeedee.com");
		
		Presenter presenter3 = new Presenter();
		presenter3.setId(3);
		presenter3.setFirstName("Jim");
		presenter3.setLastName("Brown");
		presenter3.setCompany("Jim's Ruby Shop");
		presenter3.setCompanyURL("http://www.whooby.com");
		
		List<Presenter> presenters = new ArrayList<Presenter>();
		presenters.add(presenter1);
		presenters.add(presenter2);
		presenters.add(presenter3);
		
        Builder builder = BuilderFactory.getInstance().getBuilder(PresenterSummary.class);
		List<PresenterSummary> summaries = builder.buildList(presenters);
		for (Iterator i = summaries.iterator(); i.hasNext();) {
			PresenterSummary presenterSummary = (PresenterSummary) i.next();
			Presenter presenter = null;
			switch (presenterSummary.getPresenterId()) {
			case 1:
			    presenter = presenter1;
				break;
			case 2:
				presenter = presenter2;
				break;
			case 3:
				presenter = presenter3;
				break;
			default:
				// fail the test
			}
			
			Assert.assertEquals(presenter.getId(), presenterSummary.getPresenterId());
			Assert.assertEquals(presenter.getFirstName() + " " + presenter.getLastName(), presenterSummary.getName());		
			Assert.assertEquals(presenter.getCompany(), presenterSummary.getCompany().getName());
			Assert.assertEquals(presenter.getCompanyURL(), presenterSummary.getCompany().getURL());				
		}
	}
	
	

}
