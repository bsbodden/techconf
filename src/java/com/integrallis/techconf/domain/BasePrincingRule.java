/*
 * @(#)BasePrincingRule.java	Jun 27, 2005
 *
 * Copyright 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.domain;

/**
 * @author Brian Sam-Bodden
 */
public abstract class BasePrincingRule implements PricingRule {

	// primary key
	protected Integer id;

	// fields
	protected Integer priority;
	protected String name;
	protected boolean active;
	
	// foreign key
	protected Integer conferenceId;
	
	/* (non-Javadoc)
	 * @see com.integrallis.techconf.domain.PricingRule#getId()
	 */
	public Integer getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.domain.PricingRule#setId(java.lang.Integer)
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.domain.PricingRule#getName()
	 */
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.domain.PricingRule#setName(java.lang.String)
	 */
	public void setName(String name) {
		this.name = name;

	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.domain.PricingRule#getWeight()
	 */
	public Integer getPriority() {
		return priority;
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.domain.PricingRule#setWeight(java.lang.Integer)
	 */
	public void setPriority(Integer weight) {
		this.priority = weight;
	}

	/**
	 * @return Returns the active.
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active The active to set.
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return Returns the conferenceId.
	 */
	public Integer getConferenceId() {
		return conferenceId;
	}

	/**
	 * @param conferenceId The conferenceId to set.
	 */
	public void setConferenceId(Integer conferenceId) {
		this.conferenceId = conferenceId;
	}

}
