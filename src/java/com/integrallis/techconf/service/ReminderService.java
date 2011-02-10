/*
 * @(#)ReminderService.java	Nov 17, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.service;

/**
 * @author Brian Sam-Bodden
 */
public interface ReminderService {
    void scheduleReminder(Integer reminderLocator);
}
