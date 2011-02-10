/*
 * @(#)PresentationSummary.java	Sep 1, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dto;

import org.dynadto.DTO;

public interface PresentationSummary extends DTO {
    int getPresentationId();
    int getPresenterId();
    String getTitle();
    String getLevel();
    String getType();
    String getTopic();
    String getTrack();
    String getDescription();
    String getPresenterName();
}
