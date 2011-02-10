/*
 * @(#)UserSummary.java	Sep 27, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dto;

import org.dynadto.DTO;

public interface UserSummary extends DTO {
	Integer getId();
    String getDisplayName();
    Boolean isPresenter();    
}
