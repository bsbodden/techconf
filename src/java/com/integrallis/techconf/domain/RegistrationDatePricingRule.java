package com.integrallis.techconf.domain;

import java.util.Date;

public class RegistrationDatePricingRule extends BasePrincingRule {

	private static final long serialVersionUID = -7276131198089091708L;
	
	public static String PROP_END_DATE = "EndDate";
	public static String PROP_PRICE = "Price";
	public static String PROP_START_DATE = "StartDate";
	public static String PROP_NAME = "Name";
	public static String PROP_ID = "Id";

	public int hashCode = Integer.MIN_VALUE;

	// fields
	private Double price;
	private Date endDate;
	private Date startDate;

	// constructors
	public RegistrationDatePricingRule () {
	}

	public Double getPrice () {
		return price;
	}

	public void setPrice (Double price) {
		this.price = price;
	}

	public Date getEndDate () {
		return endDate;
	}

	public void setEndDate (Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate () {
		return startDate;
	}

	public void setStartDate (Date startDate) {
		this.startDate = startDate;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof RegistrationDatePricingRule)) return false;
		else {
			RegistrationDatePricingRule mObj = (RegistrationDatePricingRule) obj;
			if (null == this.getId() || null == mObj.getId()) return false;
			else return (this.getId().equals(mObj.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return hashCode;
	}
}