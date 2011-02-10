package com.integrallis.techconf.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable {

	private static final long serialVersionUID = 8143116004661990405L;
	
	public static String PROP_PASSWORD = "Password";
	public static String PROP_EMAIL = "Email";
	public static String PROP_FAX = "Fax";
	public static String PROP_FIRST_NAME = "FirstName";
	public static String PROP_HOME_PHONE = "HomePhone";
	public static String PROP_ADDRESS = "Address";
	public static String PROP_WORK_PHONE = "WorkPhone";
	public static String PROP_ID = "Id";
	public static String PROP_LAST_NAME = "LastName";

	public int hashCode = Integer.MIN_VALUE;

	// primary key
	private Integer id;

	// fields
	private String homePhone;
	private String password;
	private String fax;
	private String workPhone;
	private String lastName;
	private String firstName;
	private String email;

	// many to one
	private Address address;

	// collections
	private Set roles;
	private Set reminders;

	// constructors
	public User() {
	}
	
	public User(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;			
		this.email = email;
		this.password = password;
	}	

	public User(Integer userId) {
		setId(userId);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set getRoles() {
		return roles;
	}

	public void setRoles(Set roles) {
		this.roles = roles;
	}
	
	@SuppressWarnings("unchecked")
	public void addToRoles(Object obj) {
		if  (null == this.roles) this.roles = new HashSet();
		this.roles.add(obj);
	}

	/**
	 * Return the value associated with the column: Reminders
	 */
	public Set getReminders() {
		return reminders;
	}

	/**
	 * Set the value related to the column: Reminders
	 * @param _reminders the Reminders value
	 */
	public void setReminders(Set reminders) {
		this.reminders = reminders;
	}
	
	@SuppressWarnings("unchecked")
	public void addToReminders(Object obj) {
		if (null == this.reminders) this.reminders = new HashSet();
		this.reminders.add(obj);
	}
	
	public boolean equals(Object obj) {
		if (null == obj) return false;
		if  (!(obj instanceof User)) return false;
		else {
			User mObj =  (User) obj;
			if (null == this.getId() || null == mObj.getId()) return false;
			else return  this.getId().equals(mObj.getId());
		}
	}

	public int hashCode() {
		if  (Integer.MIN_VALUE == this.hashCode) {
			if  (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return hashCode;
	}
	

}