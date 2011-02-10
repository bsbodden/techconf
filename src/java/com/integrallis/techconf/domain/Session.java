package com.integrallis.techconf.domain;

import java.io.Serializable;
import java.util.Date;

public class Session implements Serializable {

	private static final long serialVersionUID = -3677255177015748994L;
	
	public static String PROP_ROOM = "Room";
	public static String PROP_DATE_TIME_BEGIN = "DateTimeBegin";
	public static String PROP_DATE_TIME_END = "DateTimeEnd";
	public static String PROP_ID = "Id";
	public static String PROP_PRESENTATION = "Presentation";
	public static String PROP_CONFERENCE_ID = "ConferenceId";

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Integer id;

	// fields
	private Date dateTimeBegin;
	private Date dateTimeEnd;

	// many to one
	private Presentation presentation;
	private Room room;
	
	// many to one - key only
	private Integer conferenceId;

	// constructors
	public Session() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}

	public Date getDateTimeBegin() {
		return dateTimeBegin;
	}

	public void setDateTimeBegin(Date dateTimeBegin) {
		this.dateTimeBegin = dateTimeBegin;
	}

	public Date getDateTimeEnd() {
		return dateTimeEnd;
	}

	public void setDateTimeEnd(Date dateTimeEnd) {
		this.dateTimeEnd = dateTimeEnd;
	}

	public Presentation getPresentation() {
		return presentation;
	}

	public void setPresentation(Presentation presentation) {
		this.presentation = presentation;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public boolean equals(Object obj) {
		if (null == obj) return false;
		if  (!(obj instanceof Session)) return false;
		else {
			Session mObj = (Session) obj;
			if (null == this.getId() || null == mObj.getId()) return false;
			else return  this.getId().equals(mObj.getId());
		}
	}

	public int hashCode() {
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
	 * @return Returns the conferenceId.
	 */
	public Integer getConferenceId() {
		return conferenceId;
	}

	/**
	 * @param conferenceId The conferenceId to set.
	 */
	public void setConferenceId(Integer conferenceId) {
		this.conferenceId = conferenceId;
	}

}