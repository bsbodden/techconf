/*
 * @(#)ConferenceServiceImpl.java	Oct 29, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.spring.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dynadto.Builder;
import org.dynadto.BuilderFactory;

import com.integrallis.techconf.dao.BlogDAO;
import com.integrallis.techconf.dao.ConferenceDAO;
import com.integrallis.techconf.dao.NewsDAO;
import com.integrallis.techconf.dao.PresentationDAO;
import com.integrallis.techconf.dao.UserDAO;
import com.integrallis.techconf.domain.Abstract;
import com.integrallis.techconf.domain.BlogLink;
import com.integrallis.techconf.domain.Conference;
import com.integrallis.techconf.domain.News;
import com.integrallis.techconf.domain.Presentation;
import com.integrallis.techconf.domain.Presenter;
import com.integrallis.techconf.domain.Room;
import com.integrallis.techconf.domain.Session;
import com.integrallis.techconf.dto.BlogEntry;
import com.integrallis.techconf.dto.ConferenceSummary;
import com.integrallis.techconf.dto.NewsItem;
import com.integrallis.techconf.dto.PresentationAbstract;
import com.integrallis.techconf.dto.PresentationSummary;
import com.integrallis.techconf.dto.PresenterSummary;
import com.integrallis.techconf.dto.RoomInfo;
import com.integrallis.techconf.dto.SessionInfo;
import com.integrallis.techconf.rss.RSSFeedManager;
import com.integrallis.techconf.service.ConferenceService;

import de.nava.informa.core.ItemIF;

public class ConferenceServiceImpl implements ConferenceService {
	
	// DAOs
	protected ConferenceDAO conferenceDAO;
	protected NewsDAO newsDAO;
	protected UserDAO userDAO;
	protected PresentationDAO presentationDAO;
	protected BlogDAO blogDAO;
	
	// DynaDTO BuilderFactory
	private BuilderFactory builderFactory;
	
	// DynaDTO Builders
	protected Builder conferenceBuilder;
	protected Builder newsBuilder;
	protected Builder presenterSummaryBuilder;
	protected Builder presentationSummaryBuilder;
	protected Builder sessionInfoBuilder;
	protected Builder presentationAbstractBuilder;
	protected Builder blogEntryBuilder;
	protected Builder roomBuilder;	
	
	public void initialization() {	
		// constructs the DynaDTO builders
		conferenceBuilder = builderFactory.getBuilder(ConferenceSummary.class);
		newsBuilder = builderFactory.getBuilder(NewsItem.class);
		presenterSummaryBuilder = builderFactory.getBuilder(PresenterSummary.class);
		presentationSummaryBuilder = builderFactory.getBuilder(PresentationSummary.class);
		sessionInfoBuilder = builderFactory.getBuilder(SessionInfo.class);
		presentationAbstractBuilder = builderFactory.getBuilder(PresentationAbstract.class);
		blogEntryBuilder = builderFactory.getBuilder(BlogEntry.class);	
		roomBuilder = builderFactory.getBuilder(RoomInfo.class);
	}	

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.service.ConferenceService#getConferenceSummary(int)
	 */
	public ConferenceSummary getConferenceSummary(int conferenceId) {
		Conference conference = conferenceDAO.getConference(conferenceId);

		ConferenceSummary conferenceSummary = null;
		if (conference != null) {
			conferenceSummary = (ConferenceSummary) conferenceBuilder.build(conference);
		}
		return conferenceSummary;
	}
	
	@SuppressWarnings("unchecked")
	public List<ConferenceSummary> getActiveConferences() {
		List<Conference> entities = conferenceDAO.getActiveConferences(new Date());
		return conferenceBuilder.buildList(entities);
	}

	@SuppressWarnings("unchecked")
	public List<ConferenceSummary> getAllConferences() {
		List<Conference> entities = conferenceDAO.getAllConferences();
		return conferenceBuilder.buildList(entities);
	}	
	
	@SuppressWarnings("unchecked")
	public List<RoomInfo> getRooms(int venueId) {
		List<Room> entities = conferenceDAO.getRooms(venueId);
		return roomBuilder.buildList(entities);		
	}
	
	// ------------------------------------------------------------------------
	// blogs
    // ------------------------------------------------------------------------	
	
	@SuppressWarnings("unchecked")
	public List<BlogEntry> getBlogEntries(int conferenceId) {
		List<BlogLink> links = blogDAO.getAllBlogLinks(conferenceId);
		List<ItemIF> items = new ArrayList<ItemIF>();
		for (Iterator<BlogLink> i = links.iterator(); i.hasNext();) {
			BlogLink link = i.next();
			RSSFeedManager feedManager = new RSSFeedManager();
			items.addAll(feedManager.getChannelItems(link.getFeedURL()));
		}
		return blogEntryBuilder.buildList(items);
	}
	
	@SuppressWarnings("unchecked")
	public List<BlogEntry> getBlogEntries(int conferenceId, int entriesPerFeed) {
		List<BlogLink> links = blogDAO.getAllBlogLinks(conferenceId);
		List<ItemIF> items = new ArrayList<ItemIF>();
		for (Iterator<BlogLink> i = links.iterator(); i.hasNext();) {
			BlogLink link = i.next();
			RSSFeedManager feedManager = new RSSFeedManager();
			items.addAll(feedManager.getChannelItems(link.getFeedURL(), entriesPerFeed));
		}
		return blogEntryBuilder.buildList(items);
	}	

	@SuppressWarnings("unchecked")
	public List<BlogEntry> getBlogEntriesForPresenter(int presenterId) {
		BlogLink link = blogDAO.getBlogLinkForPresenter(presenterId);
		List<ItemIF> items = new ArrayList<ItemIF>();
		// only look up the rssfeed if a link is brought back.
		if (link != null) {
		    RSSFeedManager feedManager = new RSSFeedManager();
		    items.addAll(feedManager.getChannelItems(link.getFeedURL()));
		}
		return blogEntryBuilder.buildList(items);
	}	
	
	// ------------------------------------------------------------------------
	// news
    // ------------------------------------------------------------------------		
	@SuppressWarnings("unchecked")
	public List<NewsItem> getNews(int conferenceId) {
		List<News> entities = newsDAO.getAllNews(conferenceId);
		return newsBuilder.buildList(entities);
	}
	
	@SuppressWarnings("unchecked")
	public List<NewsItem> getNewsForDate(int conferenceId, Date date) {
		List<News> entities = newsDAO.getNewsForDate(conferenceId, date);
		return newsBuilder.buildList(entities);
	}

	public NewsItem submitNewsItem(NewsItem newsItem) {
		News news = new News();
		// TODO - DynaDTO should take care of this
		news.setBody(newsItem.getBody());
		news.setConferenceId(newsItem.getConferenceId());
		news.setCreatedOn(newsItem.getCreatedOn());
		news.setDate(newsItem.getDate());
		news.setIsGlobal(newsItem.getIsGlobal());
		news.setIsPublished(newsItem.getIsPublished());
		news.setRemoveOn(newsItem.getRemoveOn());
		news.setTitle(newsItem.getTitle());
		news.setUserId(newsItem.getUserId());
		news = newsDAO.saveNewsItem(news);
		return (NewsItem) newsBuilder.build(news);
	}	
	
	// ------------------------------------------------------------------------
	// presenters
    // ------------------------------------------------------------------------	

	@SuppressWarnings("unchecked")
	public List<PresenterSummary> getFeaturedPresenters(int conferenceId, int count) {
		List<Presenter> entities = userDAO.getRandomPresenters(conferenceId, count);
		return presenterSummaryBuilder.buildList(entities);
	}
	
	@SuppressWarnings("unchecked")
	public List<PresenterSummary> getPresentersSummaryList(int conferenceId) {
		List<Presenter> entities = userDAO.getAllPresenters(conferenceId);
		return presenterSummaryBuilder.buildList(entities);
	}	
	
	@SuppressWarnings("unchecked")
	public List<PresenterSummary> getKeynotePresenters(int conferenceId) {
		List<Presenter> entities = userDAO.getKeyNotePresenters(conferenceId);
		return presenterSummaryBuilder.buildList(entities);
	}	

	// ------------------------------------------------------------------------
	// presentations
    // ------------------------------------------------------------------------
	@SuppressWarnings("unchecked")
	public PresentationSummary getPresentation(int presentationId) {
		Presentation presentation = presentationDAO.getPresentation(presentationId);
		return (PresentationSummary)presenterSummaryBuilder.build(presentation);
	}
	
	@SuppressWarnings("unchecked")
	public List<PresentationSummary> getPresentationsForPresenter(int conferenceId, int presenterId) {
		List<Presentation> entities = presentationDAO.getPresentationsForPresenter(conferenceId, presenterId);
		return presentationSummaryBuilder.buildList(entities);
	}

	@SuppressWarnings("unchecked")
	public List<PresentationSummary> getKeynotes(int conferenceId) {
		List<Presentation> entities = presentationDAO.getKeyNotePresentations(conferenceId);
		return presentationSummaryBuilder.buildList(entities);
	}
	
	@SuppressWarnings("unchecked")
	public List<PresentationSummary> getPresentationsForConference(int conferenceId) {
		List<Presentation> entities = presentationDAO.getAllPresentations(conferenceId);
		return presentationSummaryBuilder.buildList(entities);
	}	
	
	// ------------------------------------------------------------------------
	// sessions
    // ------------------------------------------------------------------------
	
	@SuppressWarnings("unchecked")
	public List<SessionInfo> getSessionsForConference(int conferenceId) {
		List<Session> entities = presentationDAO.getAllSessions(conferenceId);
		return sessionInfoBuilder.buildList(entities);
	}
	
	@SuppressWarnings("unchecked")
	public List<SessionInfo> getSessionsForPresentation(int presentationId, int conferenceId) {
		List<Session> entities = presentationDAO.getSessionsForPresentation(presentationId, conferenceId);
		return sessionInfoBuilder.buildList(entities);
	}

	@SuppressWarnings("unchecked")
	public List<SessionInfo> getSessionsForRoom(int roomId, int conferenceId) {
		List<Session> entities = presentationDAO.getSessionsForRoom(roomId, conferenceId);
		return sessionInfoBuilder.buildList(entities);
	}

	@SuppressWarnings("unchecked")
	public List<SessionInfo> getSessionsByExample(SessionInfo sessionInfo) {
		Session template = null;
		List<Session> entities = presentationDAO.getSessionsByExample(template);
		return sessionInfoBuilder.buildList(entities);
	}

	@SuppressWarnings("unchecked")
	public List<SessionInfo> getSessionsByDate(Date date, int conferenceId) {
		List<Session> entities = presentationDAO.getSessionsByDate(date, conferenceId);
		return sessionInfoBuilder.buildList(entities);
	}
	
	@SuppressWarnings("unchecked")
	public List<SessionInfo> getSessionsByDateRange(Date startDate, Date endDate, int conferenceId) {
		List<Session> entities = presentationDAO.getSessionsByDateRange(startDate, endDate, conferenceId);
		return sessionInfoBuilder.buildList(entities);
	}
	
	@SuppressWarnings("unchecked")
	public List<SessionInfo> getSessionsByTrack(int conferenceId, int trackId) {
		List<Session> entities = presentationDAO.getSessionsByTrack(conferenceId, trackId);
		return sessionInfoBuilder.buildList(entities);
	}
	
	// ------------------------------------------------------------------------
	// abstracts
    // ------------------------------------------------------------------------		

	@SuppressWarnings("unchecked")
	public List<PresentationAbstract> getAbstractsForPresenter(int presenterId) {
		List<Abstract> entities = presentationDAO.getAbstractsByPresenter(presenterId);
		return presentationAbstractBuilder.buildList(entities);
	}

	public PresentationAbstract submitAbstract(PresentationAbstract presentationAbstract) {
		Abstract submission = new Abstract();
		presentationDAO.save(submission);
		return (PresentationAbstract) presentationAbstractBuilder.build(submission);
	}	
	
	// ------------------------------------------------------------------------
	// testing
    // ------------------------------------------------------------------------		
	
	public String getTestString() {
		return "Hello World";
	}

	/**
	 * @param blogDAO The blogDAO to set.
	 */
	public void setBlogDAO(BlogDAO blogDAO) {
		this.blogDAO = blogDAO;
	}

	/**
	 * @param conferenceDAO The conferenceDAO to set.
	 */
	public void setConferenceDAO(ConferenceDAO conferenceDAO) {
		this.conferenceDAO = conferenceDAO;
	}

	/**
	 * @param newsDAO The newsDAO to set.
	 */
	public void setNewsDAO(NewsDAO newsDAO) {
		this.newsDAO = newsDAO;
	}

	/**
	 * @param presentationDAO The presentationDAO to set.
	 */
	public void setPresentationDAO(PresentationDAO presentationDAO) {
		this.presentationDAO = presentationDAO;
	}

	/**
	 * @param userDAO The userDAO to set.
	 */
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/**
	 * @return Returns the builderFactory.
	 */
	public BuilderFactory getBuilderFactory() {
		return builderFactory;
	}

	/**
	 * @param builderFactory The builderFactory to set.
	 */
	public void setBuilderFactory(BuilderFactory builderFactory) {
		this.builderFactory = builderFactory;
	}

}
