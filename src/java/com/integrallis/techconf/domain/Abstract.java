package com.integrallis.techconf.domain;

import java.io.Serializable;


public class Abstract implements Serializable {

	private static final long serialVersionUID = 5747211423047847106L;
	
	public static String PROP_PRESENTATION_LEVEL = "PresentationLevel";
	public static String PROP_PRESENTER = "Presenter";
	public static String PROP_ABSTRACT_STATUS = "AbstractStatus";
	public static String PROP_PRESENTATION_TYPE = "PresentationType";
	public static String PROP_PRESENTATION_TOPIC = "PresentationTopic";
	public static String PROP_BODY = "Body";
	public static String PROP_TITLE = "Title";
	public static String PROP_ID = "Id";


	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Integer id;

	// fields
	private String title;
	private String body;

	// many to one
	private PresentationLevel presentationLevel;
	private AbstractStatus abstractStatus;
	private PresentationType presentationType;
	private Presenter presenter;
	private PresentationTopic presentationTopic;
	private Track track;


	// constructors
	public Abstract () {
	}

	public Integer getId() {
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

	public String getBody () {
		return body;
	}

	public void setBody (String body) {
		this.body = body;
	}

	public PresentationLevel getPresentationLevel () {
		return presentationLevel;
	}

	public void setPresentationLevel (PresentationLevel presentationLevel) {
		this.presentationLevel = presentationLevel;
	}

	public AbstractStatus getAbstractStatus () {
		return abstractStatus;
	}

	public void setAbstractStatus (AbstractStatus abstractStatus) {
		this.abstractStatus = abstractStatus;
	}

	public PresentationType getPresentationType () {
		return presentationType;
	}

	public void setPresentationType (PresentationType presentationType) {
		this.presentationType = presentationType;
	}

	public Presenter getPresenter () {
		return presenter;
	}

	public void setPresenter (Presenter presenter) {
		this.presenter = presenter;
	}

	public PresentationTopic getPresentationTopic () {
		return presentationTopic;
	}

	public void setPresentationTopic (PresentationTopic presentationTopic) {
		this.presentationTopic = presentationTopic;
	}


	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof Abstract)) return false;
		else {
			Abstract mObj = (Abstract) obj;
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
	 * @return Returns the track.
	 */
	public Track getTrack() {
		return track;
	}

	/**
	 * @param track The track to set.
	 */
	public void setTrack(Track track) {
		this.track = track;
	}

}