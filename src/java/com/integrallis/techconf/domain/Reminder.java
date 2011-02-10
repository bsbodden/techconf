package com.integrallis.techconf.domain;

import java.io.Serializable;
import java.util.Date;

public class Reminder  implements Serializable {

	private static final long serialVersionUID = 2966612517342469854L;
	
	public static String PROP_DATE_AND_TIME = "DateAndTime";
	public static String PROP_USER = "User";
	public static String PROP_MESSAGE = "Message";
	public static String PROP_SCHEDULE_ENTRY = "ScheduleEntry";
	public static String PROP_ID = "Id";
	public static String PROP_SENT = "Sent";

	public int hashCode = Integer.MIN_VALUE;

	// primary key
	private Integer id;

	// fields
	private Date dateAndTime;
	private String message;
	private Boolean sent;

	// many to one
	private ScheduleEntry scheduleEntry;
	private User user;

	// constructors
	public Reminder () {
	}
	
	public Integer getId () {
		return id;
	}

	public void setId (Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}

	public Date getDateAndTime () {
		return dateAndTime;
	}

	public void setDateAndTime (Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public String getMessage () {
		return message;
	}

	public void setMessage (String message) {
		this.message = message;
	}

	public ScheduleEntry getScheduleEntry () {
		return scheduleEntry;
	}

	public void setScheduleEntry (ScheduleEntry scheduleEntry) {
		this.scheduleEntry = scheduleEntry;
	}

	public User getUser () {
		return user;
	}

	public void setUser (User user) {
		this.user = user;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof Reminder)) return false;
		else {
			Reminder mObj = (Reminder) obj;
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
	 * @return Returns the sent.
	 */
	public Boolean getSent() {
		return sent;
	}

	/**
	 * @param sent The sent to set.
	 */
	public void setSent(Boolean sent) {
		this.sent = sent;
	}
}