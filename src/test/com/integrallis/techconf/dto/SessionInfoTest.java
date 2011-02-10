/*
 * @(#)SessionInfoTest.java	Sep 23, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dto;

import java.io.File;
import java.util.Date;

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
import com.integrallis.techconf.domain.Room;
import com.integrallis.techconf.domain.Session;
import com.integrallis.techconf.domain.Track;
import com.integrallis.techconf.test.util.Paths;

public class SessionInfoTest {
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Configuration(beforeTestClass = true)
	protected void setUp() throws ConfigurationException {
    	ConfigurationLoader.loadMapping(new File(Paths.BASEDIR + "/dd/dynadto/SessionInfo.dto.xml"));
    	ConfigurationLoader.loadMapping(new File(Paths.BASEDIR + "/dd/dynadto/PresentationSummary.dto.xml"));
	}
	
	@SuppressWarnings("unchecked")
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
		
		Room room = new Room();
		room.setName("The Purple Room");
		
		Date beginDate = new Date();
		Date endDate = new Date();
		
		Session session = new Session();
		session.setConferenceId(1);
		session.setDateTimeBegin(beginDate);
		session.setDateTimeEnd(endDate);
		session.setId(1);
		session.setPresentation(presentation);
		session.setRoom(room);
		
        Builder builder = BuilderFactory.getInstance().getBuilder(SessionInfo.class);
        SessionInfo sessionInfo = (SessionInfo) builder.build(session);
        
        System.out.println(sessionInfo.getPresentation().getClassName());
        
        PresentationSummary presentationSummary = sessionInfo.getPresentation();
        
        Assert.assertEquals(presentation.getId().intValue(), presentationSummary.getPresentationId());
        Assert.assertEquals(presenter.getId().intValue(), presentationSummary.getPresenterId());
        Assert.assertEquals(presentation.getAbstract().getTitle(), presentationSummary.getTitle());
        Assert.assertEquals(level.getName(), presentationSummary.getLevel());
        Assert.assertEquals(type.getName(), presentationSummary.getType());
        Assert.assertEquals(topic.getName(), presentationSummary.getTopic());
        Assert.assertEquals(presentation.getAbstract().getBody(), presentationSummary.getDescription());
        Assert.assertEquals(jseTrack.getTitle(), presentationSummary.getTrack());        
        
        Assert.assertEquals(session.getId(), sessionInfo.getSessionId());
        Assert.assertEquals(session.getRoom().getName(), sessionInfo.getRoomName());
        Assert.assertEquals(session.getDateTimeBegin(), sessionInfo.getDateTimeBegin());
        Assert.assertEquals(session.getDateTimeEnd(), sessionInfo.getDateTimeEnd());
        Assert.assertEquals(session.getConferenceId(), sessionInfo.getConferenceId());         
   
	}
	
}
