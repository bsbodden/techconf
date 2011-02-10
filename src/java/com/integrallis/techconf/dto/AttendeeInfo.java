/*
 * @(#)AttendeeInfo.java	Sep 27, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dto;

public interface AttendeeInfo extends UserInfo {
	Integer getAttendeeGroupId();
	void setAttendeeGroupId(Integer attendeeGroup);
	
	Boolean isAlumnus();
	void setAlumnus(Boolean alumnus);
}
