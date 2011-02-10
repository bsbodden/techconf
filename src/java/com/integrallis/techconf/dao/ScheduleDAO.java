/*
 * @(#)ScheduleDAO.java	Sep 29, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dao;

import java.util.List;

import com.integrallis.techconf.domain.Reminder;
import com.integrallis.techconf.domain.ScheduleEntry;

public interface ScheduleDAO {
	ScheduleEntry saveScheduleEntry(ScheduleEntry scheduleEntry);
	ScheduleEntry updateScheduleEntry(ScheduleEntry scheduleEntry);
	void deleteScheduleEntry(ScheduleEntry scheduleEntry);
	void deleteScheduleEntry(int scheduleEntryId);
	
	List<ScheduleEntry> getScheduleEntriesForUser(int userId);
	ScheduleEntry getScheduleEntryById(int scheduleEntryId);	
	
	Reminder getReminder(int reminderId);
	Reminder saveReminder(Reminder reminder);
	Reminder updateReminder(Reminder reminder);
	void deleteReminder(Reminder reminder);
	void deleteReminder(int reminderId);	
	
	List<Reminder> getRemindersForScheduleEntry(int scheduleEntryId);
	List<Reminder> getRemindersForUser(int userId);
}
