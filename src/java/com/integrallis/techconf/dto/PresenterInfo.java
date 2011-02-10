/*
 * @(#)PresenterInfo.java	Sep 27, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dto;

public interface PresenterInfo extends UserInfo {
	String getBio();
	void setBio(String bio);
	String getCompany();
	void setCompany(String company);
	String getCompanyURL();
	void setCompanyURL(String companyURL);
}
