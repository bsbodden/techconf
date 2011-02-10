/*
 * @(#)NewsDAO.java	Sep 4, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dao;

import java.util.Date;
import java.util.List;

import com.integrallis.techconf.domain.News;

public interface NewsDAO {
	List<News> getAllNews(int conferenceId);
	List<News> getNewsForDate(int conferenceId, Date date);
	List<News> getNewsForPeriod(int conferenceId, Date startDate, Date endDate);
	News saveNewsItem(News item);
	News updateNewsItem(News item);
	News getNewsItem(int id);
	void purgeOldNews(int conferenceId);
	void deleteNewsItem(News item);
	void deleteNewsItem(int id);	
	boolean publishNewsItem(int id);
}
