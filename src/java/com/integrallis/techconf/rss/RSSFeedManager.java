/*
 * @(#)RSSFeedManager.java	Sep 29, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.rss;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import de.nava.informa.core.ChannelIF;
import de.nava.informa.core.FeedIF;
import de.nava.informa.core.ItemIF;
import de.nava.informa.utils.FeedManager;
import de.nava.informa.utils.FeedManagerException;

public class RSSFeedManager {

	// singleton - temporary in-memory until informa updates their
	// persistence manager to use Hibernate 3.X
	private static FeedManager feedManager = new FeedManager();
	
	public ChannelIF getChannelForFeed(String feedUri) {		
		FeedIF feed = null;
		try {
			feed = feedManager.addFeed(feedUri);
		} catch (FeedManagerException fme) {
            
		}		
		
		ChannelIF result = null;
		if (feed != null) {
			result = feed.getChannel();
		}
		
		return result;	
	}
	
	@SuppressWarnings("unchecked")
	public List<ItemIF> getChannelItems(String feedUri) {
		ChannelIF channel = getChannelForFeed(feedUri);
		// if working offline u will get null channels or if errors
		if (channel != null) {
			return new ArrayList<ItemIF>(channel.getItems());
		}
		else {
			return new ArrayList<ItemIF>(0);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ItemIF> getChannelItems(String feedUri, int numberOfItems) {
		ChannelIF channel = getChannelForFeed(feedUri);
		if (channel != null) {
			Collection items = channel.getItems();
			Collection<ItemIF> selected = Collections.EMPTY_LIST;
			if (items.size() <= numberOfItems) {
				selected = items;
			}
			else {
				selected = new ArrayList(numberOfItems);
				
				for (Iterator<ItemIF> i = items.iterator(); i.hasNext() && (selected.size() < numberOfItems);) {				
					selected.add(i.next());
				}
			}
			return new ArrayList<ItemIF>(selected);
		}
		else {
			return new ArrayList<ItemIF>(0);
		}
	}
}
