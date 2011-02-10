/*
 * @(#)ConferenceSummaryTest.java	Jul 19, 2005
 *
 * Copyright 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dto;

import java.io.File;
import java.util.Date;

import org.dynadto.Builder;
import org.dynadto.BuilderFactory;
import org.dynadto.ConfigurationLoader;
import org.dynadto.exception.ConfigurationException;
import org.testng.Assert;
import org.testng.annotations.Configuration;
import org.testng.annotations.Test;

import com.integrallis.techconf.domain.News;
import com.integrallis.techconf.test.util.Paths;

public class NewsItemTest {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Configuration(beforeTestClass = true)
	protected void setUp() throws ConfigurationException {
    	ConfigurationLoader.loadMapping(new File(Paths.BASEDIR + "/dd/dynadto/NewsItem.dto.xml"));	
	}
	
	@SuppressWarnings("unchecked")
	@Test(groups = {"dto"})
	public void testNewsItemCreation() {
		// utility objects
		Date today = new Date();
		
		// create a News domain object
		News news = new News();
		news.setId(1);
		news.setUserId(4);
		news.setTitle("this is the title");		
		news.setBody("this body");
		news.setConferenceId(1);
		news.setCreatedOn(today);
		news.setDate(today);
		news.setIsGlobal(false);
		news.setIsPublished(true);
		news.setRemoveOn(today);
		
        Builder builder = BuilderFactory.getInstance().getBuilder(NewsItem.class);
        NewsItem newsItem = (NewsItem) builder.build(news);
        
		Assert.assertEquals(news.getId(), newsItem.getId());
		Assert.assertEquals(news.getTitle(), newsItem.getTitle());		
		Assert.assertEquals(news.getBody(), newsItem.getBody());
		Assert.assertEquals(news.getConferenceId().intValue(), newsItem.getConferenceId());
		Assert.assertEquals(news.getCreatedOn(), newsItem.getCreatedOn());
		Assert.assertEquals(news.getDate(), newsItem.getDate());
		Assert.assertEquals(news.getUserId().intValue(), newsItem.getUserId());
		Assert.assertEquals(news.getIsGlobal(), newsItem.getIsGlobal());
		Assert.assertEquals(news.getIsPublished().booleanValue(), newsItem.getIsPublished());
		Assert.assertEquals(news.getRemoveOn(), newsItem.getRemoveOn());        
	}
	
	

}
