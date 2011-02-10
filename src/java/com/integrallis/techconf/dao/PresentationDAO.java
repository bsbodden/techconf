/*
 * @(#)PresentationDAO.java	Sep 7, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dao;

import java.util.Date;
import java.util.List;

import com.integrallis.techconf.domain.Abstract;
import com.integrallis.techconf.domain.Presentation;
import com.integrallis.techconf.domain.Session;

public interface PresentationDAO {
    //
	// abstracts
	//	
	void save(Abstract presentationAbstract);
	void update(Abstract presentationAbstract);
	void delete(Abstract presentationAbstract);
	void delete(int abstractId);	
	Abstract getAbstract(int abstractId);	
	List<Abstract> getAbstractsByPresenter(int presenterId);
	
    //
	// presentation
	//
	Presentation getPresentation(int presentationId);
	List<Presentation> getAllPresentations(int conferenceId);
	List<Presentation> getPresentationsForPresenter(int conferenceId, int presenterId);
	List<Presentation> getKeyNotePresentations(int conferenceId);
	List<Presentation> getPresentationByExample(Presentation template, int conferenceId);
	
	//
	// sessions
	//
	Session getSessionById(int sessionId);
	List<Session> getAllSessions(int conferenceId);
	List<Session> getSessionsForPresentation(int presentationId, int conferenceId);
	List<Session> getSessionsForRoom(int roomId, int conferenceId);
	List<Session> getSessionsByExample(Session template);
	List<Session> getSessionsByDate(Date date, int conferenceId);
	List<Session> getSessionsByDateRange(Date startDate, Date endDate, int conferenceId);
	List<Session> getSessionsByTrack(int conferenceId, int trackId);
}
