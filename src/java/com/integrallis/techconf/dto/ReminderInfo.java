/*
 * @(#)ReminderInfo.java	Sep 29, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dto;

import java.util.Date;

import org.dynadto.DTO;

public interface ReminderInfo extends DTO {
	 Integer getId ();
	 Date getDateAndTime();
	 void setDateAndTime(Date dateAndTime);
	 String getMessage();
	 void setMessage(String message);
	 Integer getScheduleEntryId();
	 void setScheduleEntryId(Integer scheduleEntryId);
	 Integer getUserId();
	 void setUserId(Integer userId);
}
