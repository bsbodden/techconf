/*
 * @(#)ConferenceDAO.java	Aug 25, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dao;

import java.util.Date;
import java.util.List;

import com.integrallis.techconf.domain.Conference;
import com.integrallis.techconf.domain.Room;

public interface ConferenceDAO {
	Conference getConference(int conferenceId);
	Conference getConferenceByName(String name);
	List<Conference> getActiveConferences(Date beginDate, Date endDate);
	List<Conference> getActiveConferences(Date date);
	List<Conference> getAllConferences();
	List<Room> getRooms(int venueId);
	
	void save(Conference conference);
	void update(Conference conference);
	void delete(Conference conference);
	void delete(int conferenceId);
}
