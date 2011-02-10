package com.integrallis.techconf.domain;

import java.io.Serializable;

public class QuestionType  implements Serializable {

	private static final long serialVersionUID = -1453326418714664082L;
	
	public static String PROP_NAME = "Name";
	public static String PROP_ID = "Id";

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Integer id;

	// fields
	private String name;

	// constructors
	public QuestionType () {
	}
	
	public QuestionType (String name) {
		setName(name);
	}	

	public Integer getId () {
		return id;
	}

	public void setId (Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof QuestionType)) return false;
		else {
			QuestionType mObj = (QuestionType) obj;
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