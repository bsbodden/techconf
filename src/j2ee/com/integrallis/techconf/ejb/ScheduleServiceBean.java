/*
 * @(#)UserServiceBean.java	Sep 28, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.ejb;

import java.util.Date;
import java.util.List;

import javax.annotation.EJB;
import javax.annotation.Resource;
import javax.ejb.PostConstruct;
import javax.ejb.Stateless;

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
import com.integrallis.techconf.service.ReminderService;
import com.integrallis.techconf.service.ScheduleService;

@Stateless
public class ScheduleServiceBean implements ScheduleService {

	@Resource(name = "java:/dynadto/BuilderFactory")
	protected BuilderFactory builderFactory;
	
	@PostConstruct
	public void initialization() {	
		// constructs the DynaDTO builders
		scheduleEntryInfoBuilder = builderFactory.getBuilder(ScheduleEntryInfo.class);
		reminderInfoBuilder = builderFactory.getBuilder(ReminderInfo.class);
	}
	
	// DAOs
	@EJB protected ScheduleDAO scheduleDAO;
	@EJB protected PresentationDAO presentationDAO;
	@EJB protected UserDAO userDAO;
	@EJB protected ReminderService reminderTimerBean;
	
	// DynaDTO Builders
	protected Builder scheduleEntryInfoBuilder;
	protected Builder reminderInfoBuilder;

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
		scheduleDAO.deleteScheduleEntry(scheduleEntryId);
	}
	
	@SuppressWarnings("unchecked")
	public List<ScheduleEntryInfo> getScheduleForUser(Integer userId) {
		List<ScheduleEntry> entities = scheduleDAO.getScheduleEntriesForUser(userId);
		return scheduleEntryInfoBuilder.buildList(entities);
	}
	
	public ReminderInfo createReminder(Integer scheduleEntryId, Date dateTime, String message) {
		ReminderInfo result = null;
		// if we wanted to be safe we would look up the schedule entry
		ScheduleEntry scheduleEntry = scheduleDAO.getScheduleEntryById(scheduleEntryId);
		
		if (scheduleEntry != null) {
			Reminder reminder = new Reminder();
			reminder.setDateAndTime(dateTime);
			reminder.setMessage(message);
			reminder.setScheduleEntry(scheduleEntry);
			reminder.setUser(scheduleEntry.getUser());		
			scheduleDAO.saveReminder(reminder);
			
			reminderTimerBean.scheduleReminder(reminder.getId());
			result = (ReminderInfo) reminderInfoBuilder.build(reminder);
		}
		
		return result;
	}
	

}
