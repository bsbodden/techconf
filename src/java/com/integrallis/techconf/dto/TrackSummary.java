/*
 * @(#)TrackSummary.java	Oct 2, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dto;

import org.dynadto.DTO;

public interface TrackSummary extends DTO {
	Integer getId();
	String getTitle();
	String getSubtitle();
	String getDescription();
}
