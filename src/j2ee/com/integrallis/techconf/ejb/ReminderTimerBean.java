/*
 * @(#)ReminderTimerBean.java	Nov 17, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.ejb;

import java.text.MessageFormat;

import javax.annotation.EJB;
import javax.annotation.Resource;
import javax.ejb.PostConstruct;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.jms.JMSException;

import org.jboss.ejb3.mdb.ProducerManager;
import org.jboss.ejb3.mdb.ProducerObject;

import com.integrallis.techconf.dao.ScheduleDAO;
import com.integrallis.techconf.domain.Reminder;
import com.integrallis.techconf.domain.ScheduleEntry;
import com.integrallis.techconf.domain.User;
import com.integrallis.techconf.service.MailService;
import com.integrallis.techconf.service.ReminderService;

/**
 * @author Brian Sam-Bodden
 */
@Stateless
public class ReminderTimerBean implements ReminderService {
	
	// DAOs
	@EJB protected ScheduleDAO scheduleDAO;
	
	// Session Context
	@Resource private SessionContext sessionContext;
	
	// EJBs
	@Resource(name = "com.integrallis.techconf.service.MailService")
	protected MailService mailService;
	
	ProducerManager manager;
	
	@PostConstruct
	public void initialization() {		
		// initialize the producer manager
		ProducerObject producerObject = (ProducerObject) mailService;
		manager = producerObject.getProducerManager();		
	}	

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.service.ReminderService#scheduleReminder(java.lang.Integer)
	 */
	public void scheduleReminder(Integer reminderLocator) {
        Reminder reminder = scheduleDAO.getReminder(reminderLocator);                
        if (reminder != null) {
        	sessionContext.getTimerService().createTimer(reminder.getDateAndTime(), reminderLocator);       	
        }
	}
	
	@Timeout
	public void sendReminder(Timer timer) {
		Integer reminderLocator = (Integer) timer.getInfo();
        Reminder reminder = scheduleDAO.getReminder(reminderLocator);
        if (reminder != null) {
        	ScheduleEntry scheduleEntry = reminder.getScheduleEntry();
        	User user = scheduleEntry.getUser();
        	String message = MessageFormat.format(MESSAGE_TEMPLATE, new Object[]{user.getFirstName(),
        			                                                             scheduleEntry.getName(),
        			                                                             scheduleEntry.getSession().getDateTimeBegin(),
        			                                                             reminder.getMessage()});
        	String subject = MessageFormat.format(SUBJECT_TEMPLATE, new Object[]{scheduleEntry.getName()});
        	
			try {
				manager.connect(); // internally create a JMS connection
				mailService.sendEmail(user.getEmail(), "noreply@techconf.org", subject, message);
				reminder.setSent(true);
				scheduleDAO.saveReminder(reminder);
			} catch (JMSException jmse) {
				//TODO log the problem - throw app specific exception		
			} finally {
			    try {
                    // clean up the JMS connection
					manager.close();
				} catch (JMSException e) {
                    // do nothing
				} 
			}	        	
        	        	
        }	
        timer.cancel();
	}
	
	private static String MESSAGE_TEMPLATE = "Dear {0},\n This is a reminder that the event {1} is schedule for {2,date,long}.\n Your message: {3}\nSincerely,\n The TechConf Team";
	private static String SUBJECT_TEMPLATE = "Regarding: {0}";

}
