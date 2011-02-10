package com.integrallis.techconf.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;

public class ScheduleEntry  implements Serializable {

	private static final long serialVersionUID = -8086373522392068547L;
	
	public static String PROP_SESSION = "Session";
	public static String PROP_DESCRIPTION = "Description";
	public static String PROP_USER = "User";
	public static String PROP_NAME = "Name";
	public static String PROP_ID = "Id";

	// primary key
	private Integer id;

	// fields
	private String name;
	private String description;

	// many to one
	private User user;
	private Session session;

	// collections
	private Set<Reminder> reminders;

	// constructors
	public ScheduleEntry() {
	}
	
	public ScheduleEntry(Integer id) {
		this.id = id;
	}	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Session getSession() {
		return session;
	}

	public void setSession (Session session) {
		this.session = session;
	}

	public Set getReminders () {
		return reminders;
	}

	public void setReminders(Set<Reminder> reminders) {
		this.reminders = reminders;
	}
	
	public void addToReminders(Reminder reminder) {
		if (null == this.reminders) this.reminders = new HashSet<Reminder>();
		this.reminders.add(reminder);
	}
	
	public boolean equals(Object object) {
		if (object == null) return false;
		if (this == object) return true;
		if (!(object instanceof ScheduleEntry)) return false;
		
		final ScheduleEntry scheduleEntry = (ScheduleEntry) object;
		return new EqualsBuilder().
	       append(getDescription(), scheduleEntry.getDescription()).
	       append(getName(), scheduleEntry.getName()).
	       append(getSession().getId(), scheduleEntry.getSession().getId()).
	       append(getUser().getId(), scheduleEntry.getUser().getId()).
		   isEquals();			
	}	
}