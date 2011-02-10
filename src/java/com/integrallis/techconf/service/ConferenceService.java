/*
 * @(#)ConferenceService.java	Jun 29, 2005
 *
 * Copyright 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.service;

import java.util.Date;
import java.util.List;

import com.integrallis.techconf.dto.BlogEntry;
import com.integrallis.techconf.dto.ConferenceSummary;
import com.integrallis.techconf.dto.NewsItem;
import com.integrallis.techconf.dto.PresentationAbstract;
import com.integrallis.techconf.dto.PresentationSummary;
import com.integrallis.techconf.dto.PresenterSummary;
import com.integrallis.techconf.dto.RoomInfo;
import com.integrallis.techconf.dto.SessionInfo;

/**
 * @author Brian Sam-Bodden
 */
public interface ConferenceService {
	//
	// conference
	//
	List<ConferenceSummary> getActiveConferences();
	List<ConferenceSummary> getAllConferences();
	ConferenceSummary getConferenceSummary(int conferenceId);
	List<RoomInfo> getRooms(int venueId);
	
	//
	// blogs
	//
	List<BlogEntry> getBlogEntries(int conferenceId);
	List<BlogEntry> getBlogEntries(int conferenceId, int entriesPerFeed);
	List<BlogEntry> getBlogEntriesForPresenter(int presenterId);
		
	//
	// news
	//
	List<NewsItem> getNews(int conferenceId);
	List<NewsItem> getNewsForDate(int conferenceId, Date date);
	NewsItem submitNewsItem(NewsItem newsItem);
	
	//
	// presenters
	//
	List<PresenterSummary> getFeaturedPresenters(int conferenceId, int count);
	List<PresenterSummary> getPresentersSummaryList(int conferenceId);
	List<PresenterSummary> getKeynotePresenters(int conferenceId);
		
	//
	// presentations
	//	
	PresentationSummary getPresentation(int presentationId);
	List<PresentationSummary> getPresentationsForConference(int conferenceId);
	List<PresentationSummary> getPresentationsForPresenter(int conferenceId, int presenterId);
	List<PresentationSummary> getKeynotes(int conferenceId);
	
	//
	// sessions
	//
	List<SessionInfo> getSessionsForConference(int conferenceId);
	List<SessionInfo> getSessionsForPresentation(int presentationId, int conferenceId);
	List<SessionInfo> getSessionsForRoom(int roomId, int conferenceId);
	List<SessionInfo> getSessionsByExample(SessionInfo sessionInfo);
	List<SessionInfo> getSessionsByDate(Date date, int conferenceId);
	List<SessionInfo> getSessionsByDateRange(Date startDate, Date endDate, int conferenceId);
	List<SessionInfo> getSessionsByTrack(int conferenceId, int trackId);
	
	//
	// abstracts
	//
	List<PresentationAbstract> getAbstractsForPresenter(int presenterId);
	PresentationAbstract submitAbstract(PresentationAbstract presentationAbstract);
	
	//
	// testing
	//
	String getTestString();
}
