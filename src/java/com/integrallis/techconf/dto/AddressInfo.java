/*
 * @(#)AddressInfo.java	Oct 10, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dto;

import org.dynadto.DTO;

/**
 * @author Brian Sam-Bodden
 */
public interface AddressInfo extends DTO {
	Integer getId();
	String getStreetAddress();
	void setStreetAddress(String streetAddress);
	String getState();
	void setState(String state);
	String getZipCode();
	void setZipCode(String zipCode);
	String getCity();
	void setCity(String city);
	String getAptNumber();
	void setAptNumber(String aptNumber);
}
