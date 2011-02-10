/*
 * @(#)PresentationSummaryTest.java	Sep 23, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
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

import com.integrallis.techconf.domain.Abstract;
import com.integrallis.techconf.domain.Presentation;
import com.integrallis.techconf.domain.PresentationLevel;
import com.integrallis.techconf.domain.PresentationTopic;
import com.integrallis.techconf.domain.PresentationType;
import com.integrallis.techconf.domain.Presenter;
import com.integrallis.techconf.domain.Track;
import com.integrallis.techconf.test.util.Paths;

public class PresentationSummaryTest {
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Configuration(beforeTestClass = true)
	protected void setUp() throws ConfigurationException {
    	ConfigurationLoader.loadMapping(new File(Paths.BASEDIR + "/dd/dynadto/PresentationSummary.dto.xml"));
	}
	
	@Test(groups = {"dto"})
	public void testPresentationSummaryCreation() {	
		Presenter presenter = new Presenter();
		presenter.setId(1);
		presenter.setFirstName("Brian");
		presenter.setLastName("Sam-Bodden");
		presenter.setCompany("Integrallis Software, LLC");
		presenter.setCompanyURL("http://www.integrallis.com");	
		
		PresentationLevel level = new PresentationLevel();
		level.setId(1);
		level.setName("Beginner");
		level.setDescription("Entry Level Topic, 1 year or less of experience req.");
		
		PresentationTopic topic = new PresentationTopic();
		topic.setId(1);
		topic.setName("ORM");
		topic.setDescription("Object Relational Mapping Tools");
		
		PresentationType type = new PresentationType();
		type.setId(1);
		type.setName("Regular");
		type.setDescription("A one hour and half presentation");
		
		Track jseTrack = new Track();
		jseTrack.setDescription("Learn how to build powerful Java desktop applications");
		jseTrack.setId(new Integer(1));
		jseTrack.setSubtitle("Java Standard Edition");
		jseTrack.setTitle("JSE");		
		
		Abstract presentationAbstract = new Abstract();
		presentationAbstract.setId(1);
		presentationAbstract.setTitle("Foundations of the Java Language");
		presentationAbstract.setBody("");
		
		presentationAbstract.setPresentationLevel(level);		
		presentationAbstract.setPresentationTopic(topic);		
		presentationAbstract.setPresentationType(type);
		presentationAbstract.setPresenter(presenter);
		
		presentationAbstract.setTrack(jseTrack);
		
		Presentation presentation = new Presentation();
		presentation.setAbstract(presentationAbstract);
		presentation.setId(1);
		
        Builder builder = BuilderFactory.getInstance().getBuilder(PresentationSummary.class);
        PresentationSummary presentationSummary = (PresentationSummary) builder.build(presentation);
        
        Assert.assertEquals(presentationSummary.getPresentationId(), presentation.getId().intValue());
        Assert.assertEquals(presentationSummary.getPresenterId(), presenter.getId().intValue());
        Assert.assertEquals(presentationSummary.getTitle(), presentation.getAbstract().getTitle());
        Assert.assertEquals(presentationSummary.getLevel(), level.getName());
        Assert.assertEquals(presentationSummary.getType(), type.getName());
        Assert.assertEquals(presentationSummary.getTopic(), topic.getName());
        Assert.assertEquals(presentationSummary.getDescription(), presentation.getAbstract().getBody());
        Assert.assertEquals(presentationSummary.getPresenterName(), presenter.getFirstName() + " " + presenter.getLastName());
        Assert.assertEquals(presentationSummary.getTrack(), jseTrack.getTitle());
   	}

}
