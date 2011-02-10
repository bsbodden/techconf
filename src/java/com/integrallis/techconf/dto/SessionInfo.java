/*
 * @(#)SessionInfo.java	Sep 8, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dto;

import java.util.Date;

import org.dynadto.DTO;

public interface SessionInfo extends DTO {
	Integer getSessionId();
    PresentationSummary getPresentation();
    String getRoomName();
	Date getDateTimeBegin();
	Date getDateTimeEnd();
	Integer getConferenceId();    
}
