package com.integrallis.techconf.domain;

import java.io.Serializable;

public class Booth  implements Serializable {

	private static final long serialVersionUID = 3354469343363230963L;
	
	public static String PROP_VENUE = "Venue";
	public static String PROP_NOTES = "Notes";
	public static String PROP_SIZE = "Size";
	public static String PROP_ID = "Id";


	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Integer id;

	// fields
	private String size;
	private String notes;

	// many to one
	private Venue venue;


	// constructors
	public Booth () {
	}

	public Booth (Integer id) {
		this.setId(id);
	}

	public Integer getId () {
		return id;
	}

	public void setId (Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}

	public String getSize () {
		return size;
	}

	public void setSize (String size) {
		this.size = size;
	}

	public String getNotes () {
		return notes;
	}

	public void setNotes (String notes) {
		this.notes = notes;
	}

	public Venue getVenue () {
		return venue;
	}

	public void setVenue (Venue venue) {
		this.venue = venue;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof Booth)) return false;
		else {
			Booth mObj = (Booth) obj;
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