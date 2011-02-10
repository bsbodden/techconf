package com.integrallis.techconf.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Conference  implements Serializable {

	private static final long serialVersionUID = -6474668984218140652L;
	
	public static String PROP_END_DATE = "EndDate";
	public static String PROP_VENUE = "Venue";
	public static String PROP_DESCRIPTION = "Description";
	public static String PROP_ABTRACT_SUBMISSION_END_DATE = "AbtractSubmissionEndDate";
	public static String PROP_ABTRACT_SUBMISSION_START_DATE = "AbtractSubmissionStartDate";
	public static String PROP_START_DATE = "StartDate";
	public static String PROP_NAME = "Name";
	public static String PROP_ID = "Id";

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Integer id;

	// fields
	private Date abstractSubmissionStartDate;
	private String name;
	private String description;
	private Date abstractSubmissionEndDate;
	private Date endDate;
	private Date startDate;

	// many to one
	private Venue venue;

	// collections
	private Set<Track> tracks;

	// constructors
	public Conference () {
	}

	public Integer getId () {
		return id;
	}

	public void setId (Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}

	/**
	 * Return the value associated with the column: ABTRACTSUBMISSIONSTARTDATE
	 */
	public Date getAbstractSubmissionStartDate () {
		return abstractSubmissionStartDate;
	}

	/**
	 * Set the value related to the column: ABTRACTSUBMISSIONSTARTDATE
	 * @param abtractSubmissionStartDate the ABTRACTSUBMISSIONSTARTDATE value
	 */
	public void setAbstractSubmissionStartDate (Date abtractSubmissionStartDate) {
		this.abstractSubmissionStartDate = abtractSubmissionStartDate;
	}


	/**
	 * Return the value associated with the column: NAME
	 */
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

	public Date getAbstractSubmissionEndDate () {
		return abstractSubmissionEndDate;
	}

	public void setAbstractSubmissionEndDate (Date abtractSubmissionEndDate) {
		this.abstractSubmissionEndDate = abtractSubmissionEndDate;
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

	public Venue getVenue () {
		return venue;
	}

	public void setVenue (Venue venue) {
		this.venue = venue;
	}

	public Set<Track> getTracks () {
		return tracks;
	}

	public void setTracks (Set<Track> tracks) {
		this.tracks = tracks;
	}
	
	
	public void addTrack(Track track) {
		if (null == this.tracks) this.tracks = new HashSet<Track>();
		track.setConference(this);
		tracks.add(track);
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof Conference)) return false;
		else {
			Conference mObj = (Conference) obj;
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


	public String toString () {	
		return new ToStringBuilder(this).
	       append("name", name).
	       append("description", description).	   
	       toString();
 	}

}