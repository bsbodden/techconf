/*
 * @(#)PricingRule.java	Jun 27, 2005
 *
 * Copyright 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.domain;

import java.io.Serializable;

/**
 * @author Brian Sam-Bodden
 */
public interface PricingRule extends Serializable {
	Integer getId();
	void setId(Integer id);
	String getName();
	void setName(String name);
	Integer getPriority();
	void setPriority(Integer priority);
	boolean isActive();
	void setActive(boolean active);
	Integer getConferenceId();
	void setConferenceId(Integer conferenceId);
}
