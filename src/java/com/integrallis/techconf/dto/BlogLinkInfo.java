/*
 * @(#)BlogLinkInfo.java	Sep 27, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dto;

public interface BlogLinkInfo {
	String getBlogURL();
	String getFeedType();
	String getFeedURL();
	void setBlogURL(String blogURL);
	void setFeedType(String feedType);
	void setFeedURL(String feedURL);
	Integer getId();
	void setId(Integer id);
}