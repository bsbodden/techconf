package com.integrallis.techconf.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


public class Address  implements Serializable {

	private static final long serialVersionUID = -7209279858153584388L;
	
	public static String PROP_STATE = "State";
	public static String PROP_ZIP_CODE = "ZipCode";
	public static String PROP_APT_NUMBER = "AptNumber";
	public static String PROP_CITY = "City";
	public static String PROP_STREET_ADDRESS = "StreetAddress";
	public static String PROP_ID = "Id";


	// primary key
	private Integer id;

	// fields
	private String streetAddress;
	private String state;
	private String zipCode;
	private String city;
	private String aptNumber;


	// constructors
	public Address() {};
	
	public Address (String streetAddress, String aptNumber, String city, String state, String zipCode) {
		this.streetAddress = streetAddress;
		this.state = state;
		this.zipCode = zipCode;
		this.city = city;
		this.aptNumber = aptNumber;
	}

	public Address (Integer id) {
		this.setId(id);
	}

	public Integer getId () {
		return id;
	}

	public void setId (Integer id) {
		this.id = id;
	}

	public String getStreetAddress () {
		return streetAddress;
	}

	public void setStreetAddress (String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getState () {
		return state;
	}

	public void setState (String state) {
		this.state = state;
	}

	public String getZipCode () {
		return zipCode;
	}

	public void setZipCode (String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity () {
		return city;
	}

	public void setCity (String city) {
		this.city = city;
	}

	public String getAptNumber () {
		return aptNumber;
	}

	public void setAptNumber (String aptNumber) {
		this.aptNumber = aptNumber;
	}


	/* Implementation of equals using Business Key Equality
	 * 
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals (Object object) {
		// short circuits
		if (object == null) return false;
		if (this == object) return true;
		if (!(object instanceof Address)) return false;
		
		final Address address = (Address) object;
		//NOTE always use getters on the passed object since it might be a Hibernate Proxy
		return new EqualsBuilder().
	       append(streetAddress, address.getStreetAddress()).
	       append(aptNumber, address.getAptNumber()).
	       append(city, address.getCity()).
		   append(state, address.getState()).
		   append(zipCode, address.getZipCode()).
		   isEquals();	
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode () {
	     // pick a hard-coded, randomly chosen, non-zero, odd number
	     // ideally different for each class
	     return new HashCodeBuilder(17, 37).
	       append(streetAddress).
	       append(aptNumber).
	       append(city).
		   append(state).
		   append(zipCode).
	       toHashCode();
	}


	/* A good toString makes testing/debugging much easier
	 * 
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString () {	
		return new ToStringBuilder(this).
	       append("streetAddress", streetAddress).
	       append("aptNumber", aptNumber).
	       append("city", city).
		   append("state", state).
		   append("zipCode", zipCode).		   
	       toString();
 	}
}