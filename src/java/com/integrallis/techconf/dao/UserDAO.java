/*
 * @(#)UserDAO.java	Sep 4, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dao;

import java.util.List;

import com.integrallis.techconf.domain.Attendee;
import com.integrallis.techconf.domain.Presenter;
import com.integrallis.techconf.domain.User;

public interface UserDAO {
	Presenter savePresenter(Presenter presenter);
	Presenter updatePresenter(Presenter presenter);
	Presenter getPresenter(int presenterId);
	void deletePresenter(Presenter presenter);
	void deletePresenterById(int presenterId);
	
	Attendee saveAttendee(Attendee presenter);
	Attendee updateAttendee(Attendee presenter);
	Attendee getAttendee(int attendeeId);
	void deleteAttendee(Attendee presenter);
	void deleteAttendeeById(int attendeeId);
	
	User getUserByEmail(String email);
	User getUserById(int userId);
	
    List<Presenter> getAllPresenters(int conferenceId);
    List<Presenter> getRandomPresenters(int conferenceId, int count);
    List<Presenter> getPresentersForTopic(int conferenceId, int topicId); 
    
	List<Presenter> getPresentersByPresentationType(int conferenceId, int presentationTypeId);
	List<Presenter> getKeyNotePresenters(int conferenceId);    
}
