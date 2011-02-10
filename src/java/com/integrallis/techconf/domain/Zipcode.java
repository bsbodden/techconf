/*
 * @(#)Zipcode.java	Dec 28, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.domain;

import java.io.Serializable;

/**
 * @author Brian Sam-Bodden
 */
public class Zipcode implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 4503975783119151954L;
	private Integer zip;
	 private String city;
     private String state;
     
	/**
	 * @return Returns the city.
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @return Returns the state.
	 */
	public String getState() {
		return state;
	}
	/**
	 * @return Returns the zip.
	 */
	public Integer getZip() {
		return zip;
	}
	/**
	 * @param city The city to set.
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @param state The state to set.
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @param zip The zip to set.
	 */
	public void setZip(Integer zip) {
		this.zip = zip;
	}
}
