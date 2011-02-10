package com.integrallis.techconf.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Venue  implements Serializable {

	private static final long serialVersionUID = -4850043811835426673L;
	
	public static String PROP_FAX = "Fax";
	public static String PROP_ADDRESS = "Address";
	public static String PROP_NAME = "Name";
	public static String PROP_ID = "Id";
	public static String PROP_PHONE = "Phone";

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Integer id;

	// fields
	private String fax;
	private String name;
	private String phone;

	// many to one
	private Address address;

	// collections
	private Set booths;
	private Set rooms;

	// constructors
	public Venue () {
	}

	public Integer getId () {
		return id;
	}

	public void setId (Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}

	public String getFax () {
		return fax;
	}

	public void setFax (String fax) {
		this.fax = fax;
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public String getPhone () {
		return phone;
	}

	public void setPhone (String phone) {
		this.phone = phone;
	}

	public Address getAddress () {
		return address;
	}

	public void setAddress (Address address) {
		this.address = address;
	}

	public Set getBooths () {
		return booths;
	}

	public void setBooths (Set booths) {
		this.booths = booths;
	}
	
	@SuppressWarnings("unchecked")
	public void addToBooths (Object obj) {
		if (null == this.booths) this.booths = new HashSet();
		this.booths.add(obj);
	}

	public Set getRooms () {
		return rooms;
	}

	public void setRooms (Set rooms) {
		this.rooms = rooms;
	}
	
	@SuppressWarnings("unchecked")
	public void addToRooms (Object obj) {
		if (null == this.rooms) this.rooms = new HashSet();
		this.rooms.add(obj);
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof Venue)) return false;
		else {
			Venue mObj = (Venue) obj;
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