/*
 * @(#)BlogDAO.java	Sep 29, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dao;

import java.util.List;

import com.integrallis.techconf.domain.BlogLink;

public interface BlogDAO {
	BlogLink save(BlogLink blogLink);
	BlogLink update(BlogLink blogLink);
	void delete(BlogLink blogLink);
	void delete(int blogLinkId);
	
	List<BlogLink> getAllBlogLinks(int conferenceId);
	BlogLink getBlogLinkForPresenter(int presenterId);
	BlogLink getBlogLinkById(int blogLinkId);
}
