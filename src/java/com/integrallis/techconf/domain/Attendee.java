package com.integrallis.techconf.domain;

import java.io.Serializable;
import java.util.Set;

public class Attendee extends User implements Serializable {

	private static final long serialVersionUID = 1134601216671133516L;
	
	// fields
	private Boolean alumnus;
	private AttendeeGroup group;
	
    // collections
	private Set conferences;

	// constructors
	public Attendee() {
	}
	
	public Attendee(String firstName, String lastName, String email, String password) {
		super(firstName, lastName, email, password);
	}		

	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (!(obj instanceof Attendee))
			return false;
		else {
			Attendee mObj = (Attendee) obj;
			if (null == this.getId() || null == mObj.getId())
				return false;
			else
				return (this.getId().equals(mObj.getId()));
		}
	}

	public int hashCode() {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId())
				return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":"
						+ this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return hashCode;
	}

	/**
	 * @return Returns the alumnus.
	 */
	public Boolean isAlumnus() {
		return alumnus;
	}

	/**
	 * @return Returns the group.
	 */
	public AttendeeGroup getGroup() {
		return group;
	}

	/**
	 * @param alumnus The alumnus to set.
	 */
	public void setAlumnus(Boolean alumnus) {
		this.alumnus = alumnus;
	}

	/**
	 * @param group The group to set.
	 */
	public void setGroup(AttendeeGroup group) {
		this.group = group;
	}
	
	public Set getConferences() {
		return conferences;
	}

	public void setConferences(Set conferences) {
		this.conferences = conferences;
	}	

}