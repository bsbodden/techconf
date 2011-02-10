package com.integrallis.techconf.domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class News implements Serializable {

	public static String PROP_DATE = "Date";
	public static String PROP_USER_ID = "UserId";
	public static String PROP_PUBLISHED = "Published";
	public static String PROP_CREATED_ON = "CreatedOn";
	public static String PROP_BODY = "Body";
	public static String PROP_TITLE = "Title";
	public static String PROP_REMOVE_ON = "RemoveOn";
	public static String PROP_ID = "Id";
	public static String PROP_CONFERENCE_ID = "ConferenceId";

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Integer id;

	// fields
	private String title;
	private Boolean published;
	private String body;
	private Date date;
	private Date createdOn;
	private Date removeOn;
	private Boolean global;
	
	// fk
	private Integer conferenceId;
	private Integer userId;

	// constructors
	public News () {
	}

	public Integer getId () {
		return id;
	}

	public void setId (Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}

	public String getTitle () {
		return title;
	}

	public void setTitle (String title) {
		this.title = title;
	}

	public Boolean getIsPublished () {
		return published;
	}

	public void setIsPublished (Boolean published) {
		this.published = published;
	}

	public String getBody () {
		return body;
	}

	public void setBody (String body) {
		this.body = body;
	}

	public Date getDate () {
		return date;
	}

	public void setDate (Date date) {
		this.date = date;
	}

	public Date getCreatedOn () {
		return createdOn;
	}

	public void setCreatedOn (Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getRemoveOn () {
		return removeOn;
	}

	public void setRemoveOn (Date removeOn) {
		this.removeOn = removeOn;
	}


	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof News)) return false;
		else {
			News mObj = (News) obj;
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

	/**
	 * @return Returns the global.
	 */
	public Boolean getIsGlobal() {
		return global;
	}

	/**
	 * @param global The global to set.
	 */
	public void setIsGlobal(Boolean global) {
		this.global = global;
		if (global.booleanValue()) {
			conferenceId = null;
		}
	}

	/**
	 * @return Returns the conferenceId.
	 */
	public Integer getConferenceId() {
		return conferenceId;
	}

	/**
	 * @return Returns the userId.
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param conferenceId The conferenceId to set.
	 */
	public void setConferenceId(Integer conferenceId) {
		this.conferenceId = conferenceId;
		if (conferenceId != null) {
			global = false;
		}
	}

	/**
	 * @param userId The userId to set.
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}