/*
 * @(#)LinkDTO.java	Jul 2, 2005
 *
 * Copyright 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dto;

import org.dynadto.DTO;

/**
 * @author Brian Sam-Bodden
 */
public interface Link extends DTO {
	String getURL();
	String getName();
}
