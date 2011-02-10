/*
 * @(#)UserInfo.java	Sep 27, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dto;

import org.dynadto.DTO;

public interface UserInfo extends DTO {
	Integer getId();

	String getHomePhone();
	void setHomePhone(String homePhone);

	String getPassword();
	void setPassword(String password);

	String getFax();
	void setFax(String fax);

	String getWorkPhone();
	void setWorkPhone(String workPhone);

	String getLastName();
	void setLastName(String lastName);

	String getFirstName();
	void setFirstName(String firstName);

	String getEmail();
	void setEmail(String email);
	
	AddressInfo getAddress();
	void setAddress(AddressInfo address);
	
	String getCountry();
	void setCountry(String c);
}
