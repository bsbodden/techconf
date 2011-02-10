/*
 * @(#)ScheduleDAOBean.java	Sep 29, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.ejb.dao.hibernate;

import java.util.List;

import javax.ejb.Stateless;

import com.integrallis.techconf.dao.ScheduleDAO;
import com.integrallis.techconf.domain.Reminder;
import com.integrallis.techconf.domain.ScheduleEntry;
import com.integrallis.techconf.domain.User;

/**
 * @author Brian Sam-Bodden
 */
@Stateless
public class ScheduleDAOBean extends BaseAbstractDAO implements ScheduleDAO {

	/**
	 * @param sessionFactory
	 */
	public ScheduleDAOBean() {
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.dao.ScheduleDAO#saveScheduleEntry(com.integrallis.techconf.domain.ScheduleEntry)
	 */
	public ScheduleEntry saveScheduleEntry(ScheduleEntry scheduleEntry) {
		saveEntity(scheduleEntry);
		return scheduleEntry;
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.dao.ScheduleDAO#updateScheduleEntry(com.integrallis.techconf.domain.ScheduleEntry)
	 */
	public ScheduleEntry updateScheduleEntry(ScheduleEntry scheduleEntry) {
		updateEntity(scheduleEntry);
		return scheduleEntry;
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.dao.ScheduleDAO#deleteScheduleEntry(com.integrallis.techconf.domain.ScheduleEntry)
	 */
	public void deleteScheduleEntry(ScheduleEntry scheduleEntry) {
		deleteEntity(scheduleEntry);
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.dao.ScheduleDAO#deleteScheduleEntry(int)
	 */
	public void deleteScheduleEntry(int scheduleEntryId) {
		deleteEntityById(ScheduleEntry.class, scheduleEntryId);
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.dao.ScheduleDAO#getScheduleEntriesForUser(int)
	 */
	@SuppressWarnings("unchecked")
	public List<ScheduleEntry> getScheduleEntriesForUser(int userId) {
		return findFiltered(ScheduleEntry.class, ScheduleEntry.PROP_USER, new User(userId));
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.dao.ScheduleDAO#getScheduleEntryById(int)
	 */
	public ScheduleEntry getScheduleEntryById(int scheduleEntryId) {
		return (ScheduleEntry) getEntityById(ScheduleEntry.class, scheduleEntryId);
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.dao.ScheduleDAO#saveReminder(com.integrallis.techconf.domain.Reminder)
	 */
	public Reminder saveReminder(Reminder reminder) {
		saveEntity(reminder);
		return reminder;
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.dao.ScheduleDAO#updateReminder(com.integrallis.techconf.domain.Reminder)
	 */
	public Reminder updateReminder(Reminder reminder) {
		updateEntity(reminder);
		return reminder;
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.dao.ScheduleDAO#deleteReminder(com.integrallis.techconf.domain.Reminder)
	 */
	public void deleteReminder(Reminder reminder) {
		deleteEntity(reminder);
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.dao.ScheduleDAO#deleteReminder(int)
	 */
	public void deleteReminder(int reminderId) {
		deleteEntityById(Reminder.class, reminderId);
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.dao.ScheduleDAO#getRemindersForScheduleEntry(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Reminder> getRemindersForScheduleEntry(int scheduleEntryId) {
		return findFiltered(Reminder.class, Reminder.PROP_SCHEDULE_ENTRY, new ScheduleEntry(scheduleEntryId));
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.dao.ScheduleDAO#getRemindersForUser(int)
	 */
	@SuppressWarnings("unchecked")
	public List<Reminder> getRemindersForUser(int userId) {
		return findFiltered(Reminder.class, Reminder.PROP_USER, new User(userId));
	}
	
	/* (non-Javadoc)
	 * @see com.integrallis.techconf.dao.ScheduleDAO#getReminder(int)
	 */
	public Reminder getReminder(int reminderId) {
		return (Reminder) getEntityById(Reminder.class, reminderId);
	}	

}
