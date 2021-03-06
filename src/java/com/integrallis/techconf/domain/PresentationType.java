package com.integrallis.techconf.domain;

import java.io.Serializable;

public class PresentationType  implements Serializable {

	private static final long serialVersionUID = 6330153173834123906L;
	
	public static String PROP_DESCRIPTION = "Description";
	public static String PROP_NAME = "Name";
	public static String PROP_ID = "Id";

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Integer id;

	// fields
	private String name;
	private String description;

	// constructors
	public PresentationType () {
	}

	public Integer getId () {
		return id;
	}

	public void setId (Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public String getDescription () {
		return description;
	}

	public void setDescription (String description) {
		this.description = description;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof PresentationType)) return false;
		else {
			PresentationType mObj = (PresentationType) obj;
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