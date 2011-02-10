package com.integrallis.techconf.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Presentation implements Serializable {

	private static final long serialVersionUID = 776259789161629667L;
	
	public static String PROP_ABSTRACT = "Abstract";
	public static String PROP_ID = "Id";

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Integer id;

	// many to one
	private Abstract presentationAbstract;

	// collections
	private Set<Session> sessions;

	// constructors
	public Presentation () {
	}

	public Integer getId () {
		return id;
	}

	public void setId (Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}

	public Abstract getAbstract () {
		return presentationAbstract;
	}

	public void setAbstract (Abstract presentationAbstract) {
		this.presentationAbstract = presentationAbstract;
	}

	public Set getSessions () {
		return sessions;
	}

	public void setSessions(Set<Session> sessions) {
		this.sessions = sessions;
	}
	
	@SuppressWarnings("unchecked")
	public void addToSessions (Session session) {
		if (null == sessions) sessions = new HashSet<Session>();
		sessions.add(session);
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof Presentation)) return false;
		else {
			Presentation mObj = (Presentation) obj;
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