package com.integrallis.techconf.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Book implements Serializable {

	public static String PROP_USER_ID = "UserId";
	public static String PROP_TITLE = "Title";
	public static String PROP_DESCRIPTION = "Description";
	public static String PROP_ID = "Id";
	public static String PROP_PURCHASE_URL = "PurchaseUrl";
	public static String PROP_IN_BOOKSTORE = "InBookstore";
	

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Integer id;
	private String title;
	private String description;
	private String authors;
	private String purchaseUrl;
	private Boolean inBookstore;
	
	// fk
	private Integer userId;

	// constructors
	public Book () {
	}

	public Integer getId () {
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

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof Book)) return false;
		else {
			Book mObj = (Book) obj;
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
	 * @return Returns the userId.
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId The userId to set.
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return Returns the inBookstore.
	 */
	public Boolean getInBookstore() {
		return inBookstore;
	}

	/**
	 * @return Returns the purchaseUrl.
	 */
	public String getPurchaseUrl() {
		return purchaseUrl;
	}

	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param inBookstore The inBookstore to set.
	 */
	public void setInBookstore(Boolean inBookstore) {
		this.inBookstore = inBookstore;
	}

	/**
	 * @param purchaseUrl The purchaseUrl to set.
	 */
	public void setPurchaseUrl(String purchaseUrl) {
		this.purchaseUrl = purchaseUrl;
	}

	/**
	 * @return Returns the authors.
	 */
	public String getAuthors() {
		return authors;
	}

	/**
	 * @param authors The authors to set.
	 */
	public void setAuthors(String authors) {
		this.authors = authors;
	}

}