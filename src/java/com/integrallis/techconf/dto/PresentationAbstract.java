/*
 * @(#)PresentationAbstract.java	Sep 7, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dto;

import org.dynadto.DTO;

public interface PresentationAbstract extends DTO {	
	
    // this needs to know the conference the abstract is being submitted for
	// this is only needed at submission time to determine if the abstract 
	// can be accepted, or do we just turn the link off for abstract submission
	// at that point?
	Integer getConferenceId();
	void setConferenceId();
	
	Integer getPresenterId();
	void setPresenterId(Integer presenterId);	
	
	Integer getId();
	void setId(Integer id);

	String getTitle();
	void setTitle(String title);

	String getBody();
	void setBody (String body);
	
	Integer getPresentationLevelId();
	void setPresentationLevelId(Integer presentationLevelId);
	
	Integer getPresentationTypeId();
	void setPresentationTypeId(Integer presentationTypeId);

	Integer getPresentationTopicId();	
	void setPresentationTopicId(Integer presentationTopicId);	
	
	Integer getTrackId();	
	void setTrackId(Integer trackId);
	
	String getStatus();
}
