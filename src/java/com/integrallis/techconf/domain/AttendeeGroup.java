/*
 * @(#)AttendeeGroup.java	Sep 1, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.domain;

/**
 * @author Brian Sam-Bodden
 */
public class AttendeeGroup {
	// primary key
	private Integer id;
	
	private String company;	
	private String companyURL;
	private CompanyAddress address;
	private String fax;
	private String phone;
	private String contact;
	private String email;
	private Double pricingPerAttendee;
	
	public AttendeeGroup() {}
	
	public AttendeeGroup(Integer id) {
		this.id = id;
	}
	
	/**
	 * @return Returns the id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(Integer id) {
		this.id = id;
	}	
	
	/**
	 * @return Returns the company.
	 */
	public String getCompany() {
		return company;
	}
	
	/**
	 * @return Returns the companyURL.
	 */
	public String getCompanyURL() {
		return companyURL;
	}
	
	/**
	 * @return Returns the contact.
	 */
	public String getContact() {
		return contact;
	}
	
	/**
	 * @return Returns the email.
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @return Returns the fax.
	 */
	public String getFax() {
		return fax;
	}
	
	/**
	 * @return Returns the phone.
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * @return Returns the pricingPerAttendee.
	 */
	public Double getPricingPerAttendee() {
		return pricingPerAttendee;
	}
	
	/**
	 * @param company The company to set.
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	
	/**
	 * @param companyURL The companyURL to set.
	 */
	public void setCompanyURL(String companyURL) {
		this.companyURL = companyURL;
	}
	
	/**
	 * @param contact The contact to set.
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	/**
	 * @param email The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @param fax The fax to set.
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}
	
	/**
	 * @param phone The phone to set.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * @param pricingPerAttendee The pricingPerAttendee to set.
	 */
	public void setPricingPerAttendee(Double pricingPerAttendee) {
		this.pricingPerAttendee = pricingPerAttendee;
	}

	/**
	 * @return Returns the address.
	 */
	public CompanyAddress getAddress() {
		return address;
	}

	/**
	 * @param address The address to set.
	 */
	public void setAddress(CompanyAddress address) {
		this.address = address;
	}
	
}
