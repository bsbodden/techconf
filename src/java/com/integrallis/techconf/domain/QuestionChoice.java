package com.integrallis.techconf.domain;

import java.io.Serializable;

public class QuestionChoice  implements Serializable {

	private static final long serialVersionUID = 3361922695510723884L;
	
	public static String PROP_TEXT = "Text";
	public static String PROP_ID = "Id";

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Integer id;

	// fields
	private String text;

	// constructors
	public QuestionChoice () {
	}
	
	public QuestionChoice (Integer id) {
		setId(id);
	}	
	
	public QuestionChoice (String text) {
		setText(text);
	}	

	public Integer getId () {
		return id;
	}

	public void setId (Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}

	public String getText () {
		return text;
	}

	public void setText (String text) {
		this.text = text;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof QuestionChoice)) return false;
		else {
			QuestionChoice mObj = (QuestionChoice) obj;
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