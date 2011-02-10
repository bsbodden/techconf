package com.integrallis.techconf.domain;

import java.io.Serializable;

public class UserRole implements Serializable {

	private static final long serialVersionUID = 7158691759602638946L;
	
	public static String PROP_USER = "User";
	public static String PROP_ROLE = "Role";
	public static String PROP_ID = "Id";

	public int hashCode = Integer.MIN_VALUE;

	// primary key
	private Integer id;

	// many to one
	private Role role;
	private User user;

	// constructors
	public UserRole() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean equals(Object obj) {
		if  (null == obj) return false;
		if  (!(obj instanceof UserRole)) return false;
		else {
			UserRole mObj = (UserRole) obj;
			if (null == this.getId() || null == mObj.getId()) return false;
			else return (this.getId().equals(mObj.getId()));
		}
	}

	public int hashCode() {
		if (Integer.MIN_VALUE == this.hashCode) {
			if  (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return hashCode;
	}
}