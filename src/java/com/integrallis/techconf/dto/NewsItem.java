/*
 * @(#)NewsItemDTO.java	Jun 30, 2005
 *
 * Copyright 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dto;

import java.util.Date;

import org.dynadto.DTO;

/**
 * @author Brian Sam-Bodden
 */
public interface NewsItem extends DTO {
	Integer getId();
	void setId(Integer id);
	
	String getTitle();
	void setTitle(String title);
	
	boolean getIsPublished();
	void setIsPublished(boolean published);
	
	String getBody();
	void setBody(String body);
	
	Date getDate();
	void setDate(Date date);
	
	Date getCreatedOn();
	void setCreatedOn(Date createdOn);
	
	Date getRemoveOn();
	void setRemoveOn(Date removeOn);
	
	int getUserId();
	void setUserId(int userId);
	
	int getConferenceId();
	void setConferenceId(int conferenceId);
	
	// whether it applies to all conferences
	Boolean getIsGlobal();
	void setIsGlobal(Boolean global);
}
