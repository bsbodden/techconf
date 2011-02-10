/*
 * @(#)BlogEntryDTO.java	Jun 30, 2005
 *
 * Copyright 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dto;

import java.util.Date;

import org.dynadto.DTO;

/**
 * @author Brian Sam-Bodden
 */
public interface BlogEntry extends DTO {
	Date getPublishedDate();
	String getTitle();
	String getDescription();
	String getLink();
}
