package com.integrallis.techconf.domain;

import java.io.Serializable;

public class Room  implements Serializable {

	private static final long serialVersionUID = -7992126653880967706L;
	
	public static String PROP_CAPACITY = "Capacity";
	public static String PROP_VENUE = "Venue";
	public static String PROP_NOTES = "Notes";
	public static String PROP_NAME = "Name";
	public static String PROP_ID = "Id";

	public int hashCode = Integer.MIN_VALUE;

	// primary key
	private Integer id;

	// fields
	private String notes;
	private String name;
	private Integer capacity;

	// many to one
	private Venue venue;

	// constructors
	public Room () {
	}

	public Integer getId () {
		return id;
	}

	public void setId (Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}

	public String getNotes () {
		return notes;
	}

	public void setNotes (String notes) {
		this.notes = notes;
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public Integer getCapacity () {
		return capacity;
	}

	public void setCapacity (Integer capacity) {
		this.capacity = capacity;
	}

	public Venue getVenue () {
		return venue;
	}

	public void setVenue (Venue venue) {
		this.venue = venue;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof Room)) return false;
		else {
			Room mObj = (Room) obj;
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