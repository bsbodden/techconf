package com.integrallis.techconf.domain;

public class GroupPricingRule extends BasePrincingRule {

	private static final long serialVersionUID = 980725544167181456L;
	
	public static String PROP_MAXIMUM_ATTENDEES = "MaximumAttendees";
	public static String PROP_DISCOUNT_RATE = "DiscountRate";
	public static String PROP_NAME = "Name";
	public static String PROP_MINIMUM_ATTENDEES = "MinimumAttendees";
	public static String PROP_ID = "Id";

	private int hashCode = Integer.MIN_VALUE;

	// fields
	private Integer minimumAttendees;
	private Double discountRate;
	private Integer maximumAttendees;


	// constructors
	public GroupPricingRule () {
	}

	public Integer getMinimumAttendees () {
		return minimumAttendees;
	}

	public void setMinimumAttendees (Integer minimumAttendees) {
		this.minimumAttendees = minimumAttendees;
	}

	public Double getDiscountRate () {
		return discountRate;
	}

	public void setDiscountRate (Double discountRate) {
		this.discountRate = discountRate;
	}

	public Integer getMaximumAttendees () {
		return maximumAttendees;
	}

	public void setMaximumAttendees (Integer maximumAttendees) {
		this.maximumAttendees = maximumAttendees;
	}


	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof GroupPricingRule)) return false;
		else {
			GroupPricingRule mObj = (GroupPricingRule) obj;
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