/*
 * @(#)ScheduleServiceImpl.java	Oct 29, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.spring.service;

import java.util.Date;
import java.util.List;

import org.dynadto.Builder;
import org.dynadto.BuilderFactory;

import com.integrallis.techconf.dao.PresentationDAO;
import com.integrallis.techconf.dao.ScheduleDAO;
import com.integrallis.techconf.dao.UserDAO;
import com.integrallis.techconf.domain.Presentation;
import com.integrallis.techconf.domain.Reminder;
import com.integrallis.techconf.domain.ScheduleEntry;
import com.integrallis.techconf.domain.Session;
import com.integrallis.techconf.domain.User;
import com.integrallis.techconf.dto.ReminderInfo;
import com.integrallis.techconf.dto.ScheduleEntryInfo;
import com.integrallis.techconf.service.ScheduleService;

/**
 * @author Brian Sam-Bodden
 */
public class ScheduleServiceImpl implements ScheduleService {
	
	// DAOs
	protected ScheduleDAO scheduleDAO;
	protected PresentationDAO presentationDAO;
	protected UserDAO userDAO;
	
	// DynaDTO BuilderFactory
	private BuilderFactory builderFactory;	
	
	// DynaDTO Builders
	protected Builder scheduleEntryInfoBuilder;
	protected Builder reminderInfoBuilder;
	
	public void initialization() {	
		// constructs the DynaDTO builders
		scheduleEntryInfoBuilder = builderFactory.getBuilder(ScheduleEntryInfo.class);
		reminderInfoBuilder = builderFactory.getBuilder(ReminderInfo.class);
	}	

	public ScheduleEntryInfo scheduleSessionForUser(Integer userId, Integer sessionId) {
		// look up the Session
		Session session = presentationDAO.getSessionById(sessionId);
		Presentation presentation = session.getPresentation();
		User user = userDAO.getUserById(userId);
		
	    String name = presentation.getAbstract().getTitle();
	    String description = "In room " + session.getRoom().getName() 
	                         + " starting at " + session.getDateTimeBegin();
		
		ScheduleEntry scheduleEntry = new ScheduleEntry();
		scheduleEntry.setName(name);		
		scheduleEntry.setDescription(description);		
		scheduleEntry.setSession(session);
		scheduleEntry.setUser(user);
		
		scheduleDAO.saveScheduleEntry(scheduleEntry);
		
		return (ScheduleEntryInfo) scheduleEntryInfoBuilder.build(scheduleEntry);
	}
	
	public void unscheduleSessionForUser(Integer scheduleEntryId) {
		// TODO - Brian is this correct?
		scheduleDAO.deleteScheduleEntry(scheduleEntryId);
	}
	
	@SuppressWarnings("unchecked")
	public List<ScheduleEntryInfo> getScheduleForUser(Integer userId) {
		List<ScheduleEntry> entities = scheduleDAO.getScheduleEntriesForUser(userId);
		return scheduleEntryInfoBuilder.buildList(entities);
	}
	
	public ReminderInfo createReminder(Integer scheduleEntryId, Date dateTime, String message) {
		ScheduleEntry scheduleEntry = new ScheduleEntry();
		scheduleEntry.setId(scheduleEntryId);
		
		Reminder reminder = new Reminder();
		reminder.setDateAndTime(dateTime);
		reminder.setMessage(message);
		reminder.setScheduleEntry(scheduleEntry);
		
		scheduleDAO.saveReminder(reminder);
		
		return (ReminderInfo) reminderInfoBuilder.build(reminder);
	}

	/**
	 * @param presentationDAO The presentationDAO to set.
	 */
	public void setPresentationDAO(PresentationDAO presentationDAO) {
		this.presentationDAO = presentationDAO;
	}

	/**
	 * @param scheduleDAO The scheduleDAO to set.
	 */
	public void setScheduleDAO(ScheduleDAO scheduleDAO) {
		this.scheduleDAO = scheduleDAO;
	}

	/**
	 * @param userDAO The userDAO to set.
	 */
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/**
	 * @return Returns the builderFactory.
	 */
	public BuilderFactory getBuilderFactory() {
		return builderFactory;
	}

	/**
	 * @param builderFactory The builderFactory to set.
	 */
	public void setBuilderFactory(BuilderFactory builderFactory) {
		this.builderFactory = builderFactory;
	}	

}
