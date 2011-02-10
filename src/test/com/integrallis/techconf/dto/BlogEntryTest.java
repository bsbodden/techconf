/*
 * @(#)BlogEntryTest.java	Sep 29, 2005
 *
 * Copyright 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dto;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import org.dynadto.Builder;
import org.dynadto.BuilderFactory;
import org.dynadto.ConfigurationLoader;
import org.dynadto.exception.ConfigurationException;
import org.testng.Assert;
import org.testng.annotations.Configuration;
import org.testng.annotations.Test;

import com.integrallis.techconf.test.util.Paths;

import de.nava.informa.core.ItemIF;
import de.nava.informa.impl.basic.Item;

public class BlogEntryTest {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Configuration(beforeTestClass = true)
	protected void setUp() throws ConfigurationException {
		ConfigurationLoader.loadMapping(new File(Paths.BASEDIR + "/dd/dynadto/BlogEntry.dto.xml"));	
	}
	
	@SuppressWarnings("unchecked")
	@Test(groups = {"dto"})
	public void testNewsItemCreation() throws MalformedURLException {
		// utility objects
		Date today = new Date();
		
		// create a News domain object
		ItemIF item = new Item();
		item.setTitle("Open Source Java Rocks");
		item.setLink(new URL("http://www.integrallis.com/blogs/sambodden?id=2"));
		item.setDescription("101 Reasons Open Source Java Is Great");
		item.setDate(today);
		
        Builder builder = BuilderFactory.getInstance().getBuilder(BlogEntry.class);
        BlogEntry blogEntry = (BlogEntry) builder.build(item);
        
        System.out.println(blogEntry);
        
		Assert.assertEquals(blogEntry.getDescription(), item.getDescription());
		Assert.assertEquals(blogEntry.getTitle(), item.getTitle());
		Assert.assertEquals(blogEntry.getLink(), item.getLink().toString());
		Assert.assertEquals(blogEntry.getPublishedDate(), item.getDate());
	}

}
