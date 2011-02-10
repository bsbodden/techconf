/*
 * @(#)BlogLink.java	Sep 1, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.domain;

public class BlogLink {
	// primary key
	private Integer id;
	
    private String blogURL;
    private String feedURL;
    private String feedType;
	/**
	 * @return Returns the blogURL.
	 */
	public String getBlogURL() {
		return blogURL;
	}
	/**
	 * @return Returns the feedType.
	 */
	public String getFeedType() {
		return feedType;
	}
	/**
	 * @return Returns the feedURL.
	 */
	public String getFeedURL() {
		return feedURL;
	}
	/**
	 * @param blogURL The blogURL to set.
	 */
	public void setBlogURL(String blogURL) {
		this.blogURL = blogURL;
	}
	/**
	 * @param feedType The feedType to set.
	 */
	public void setFeedType(String feedType) {
		this.feedType = feedType;
	}
	/**
	 * @param feedURL The feedURL to set.
	 */
	public void setFeedURL(String feedURL) {
		this.feedURL = feedURL;
	}
	/**
	 * @return Returns the id.
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(Integer id) {
		this.id = id;
	}
}
