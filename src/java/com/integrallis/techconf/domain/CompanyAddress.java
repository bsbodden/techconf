package com.integrallis.techconf.domain;

import java.io.Serializable;


public class CompanyAddress  implements Serializable {

	private static final long serialVersionUID = -7209279858153584388L;

	// fields
	private String streetAddress;
	private String state;
	private String zipCode;
	private String city;

	// constructors
	public CompanyAddress () {
	}

	/**
	 * Return the value associated with the column: STREETADDRESS
	 */
	public String getStreetAddress () {
		return streetAddress;
	}

	/**
	 * Set the value related to the column: STREETADDRESS
	 * @param streetAddress the STREETADDRESS value
	 */
	public void setStreetAddress (String streetAddress) {
		this.streetAddress = streetAddress;
	}


	/**
	 * Return the value associated with the column: STATE
	 */
	public String getState () {
		return state;
	}

	/**
	 * Set the value related to the column: STATE
	 * @param state the STATE value
	 */
	public void setState (String state) {
		this.state = state;
	}


	/**
	 * Return the value associated with the column: ZIPCODE
	 */
	public String getZipCode () {
		return zipCode;
	}

	/**
	 * Set the value related to the column: ZIPCODE
	 * @param zipCode the ZIPCODE value
	 */
	public void setZipCode (String zipCode) {
		this.zipCode = zipCode;
	}


	/**
	 * Return the value associated with the column: CITY
	 */
	public String getCity () {
		return city;
	}

	/**
	 * Set the value related to the column: CITY
	 * @param city the CITY value
	 */
	public void setCity (String city) {
		this.city = city;
	}

}