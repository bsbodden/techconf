/*
 * @(#)PresenterDTO.java	Jul 2, 2005
 *
 * Copyright 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dto;

import org.dynadto.DTO;

/**
 * @author Brian Sam-Bodden
 */
public interface PresenterSummary extends DTO {
	Integer getPresenterId();
	String getName();
	Link getCompany();
	Link getPictureURL();
}
