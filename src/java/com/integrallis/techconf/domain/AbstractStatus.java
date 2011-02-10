package com.integrallis.techconf.domain;

import java.io.Serializable;


public class AbstractStatus  implements Serializable {

	private static final long serialVersionUID = 8291340946840639432L;
	
	public static String PROP_NAME = "Name";
	public static String PROP_ID = "Id";


	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Integer id;

	// fields
	private String name;


	// constructors
	public AbstractStatus () {
	}

	public AbstractStatus (Integer id) {
		this.setId(id);
	}

	public AbstractStatus (
		Integer id,
		String name) {

		this.setId(id);
		this.setName(name);
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
		if (!(obj instanceof AbstractStatus)) return false;
		else {
			AbstractStatus mObj = (AbstractStatus) obj;
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