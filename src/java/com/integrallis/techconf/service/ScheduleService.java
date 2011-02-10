/*
 * @(#)ScheduleService.java	Jun 29, 2005
 *
 * Copyright 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.service;

import java.util.Date;
import java.util.List;

import com.integrallis.techconf.dto.ReminderInfo;
import com.integrallis.techconf.dto.ScheduleEntryInfo;

/**
 * @author Brian Sam-Bodden
 */
public interface ScheduleService {
    ScheduleEntryInfo scheduleSessionForUser(Integer userId, Integer sessionId);
    
    void unscheduleSessionForUser(Integer scheduleEntryID);
    
    List<ScheduleEntryInfo> getScheduleForUser(Integer userId);
    
    ReminderInfo createReminder(Integer scheduleEntry, Date dateTime, String message);
}
