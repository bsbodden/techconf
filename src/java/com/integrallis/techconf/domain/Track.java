package com.integrallis.techconf.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Track implements Serializable {

	private static final long serialVersionUID = -4763843428440599095L;
	
	public static String PROP_CONFERENCE = "Conference";
	public static String PROP_SUBTITLE = "Subtitle";
	public static String PROP_DESCRIPTION = "Description";
	public static String PROP_TITLE = "Title";
	public static String PROP_ID = "Id";

	// primary key
	private Integer id;

	// fields
	private String title;
	private String subtitle;
	private String description;

	// many to one
	private Conference conference;

	// constructors
	public Track() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Conference getConference() {
		return conference;
	}

	public void setConference(Conference conference) {
		this.conference = conference;
	}

	public boolean equals(Object object) {
		// short circuits
		if (object == null) return false;
		if (this == object) return true;
		if (!(object instanceof Track)) return false;
		
		final Track track = (Track) object;
		//NOTE always use getters on the passed object since it might be a Hibernate Proxy
		return new EqualsBuilder().
	       append(title, track.getTitle()).
		   isEquals();			
	}

	public int hashCode() {
	     return new HashCodeBuilder(21, 35).
	       append(title).
	       toHashCode();		
	}


	public String toString () {	
		return new ToStringBuilder(this).
	       append("title", title).
	       append("subtitle", subtitle).
	       append("description", description).	   
	       toString();
 	}
}