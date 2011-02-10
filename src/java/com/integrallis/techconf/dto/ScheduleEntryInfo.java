/*
 * @(#)ScheduleEntryInfo.java	Sep 29, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dto;

import org.dynadto.DTO;

public interface ScheduleEntryInfo extends DTO {
	 Integer getId();
	 String getName();
	 void setName(String name);
	 String getDescription();
	 void setDescription(String description);
	 Integer getUserId();
	 void setUserId(Integer userId);
	 Integer getSessionId();
	 void setSessionId(Integer session);
}
