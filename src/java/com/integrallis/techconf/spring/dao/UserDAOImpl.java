/*
 * @(#)UserDAOImpl.java	Sep 4, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.spring.dao;

import java.util.List;

import com.integrallis.techconf.dao.UserDAO;
import com.integrallis.techconf.domain.Attendee;
import com.integrallis.techconf.domain.Presenter;
import com.integrallis.techconf.domain.User;

public class UserDAOImpl extends BaseAbstractDAO implements UserDAO {

	public UserDAOImpl() {
	}
	
	public Presenter savePresenter(Presenter presenter) {
		saveEntity(presenter);
		return presenter;
	}

	public Presenter updatePresenter(Presenter presenter) {
		updateEntity(presenter);
		return presenter;
	}

	public Presenter getPresenter(int presenterId) {
		return (Presenter) getEntityById(Presenter.class, presenterId);
	}

	public void deletePresenter(Presenter presenter) {
		deleteEntity(presenter);
	}

	public void deletePresenterById(int presenterId) {
		deleteEntityById(Presenter.class, presenterId);
	}

	public Attendee saveAttendee(Attendee attendee) {		
		saveEntity(attendee);
		return attendee;
	}

	public Attendee updateAttendee(Attendee attendee) {
		updateEntity(attendee);
		return attendee;
	}

	public Attendee getAttendee(int attendeeId) {
		return (Attendee) getEntityById(Attendee.class, attendeeId);		
	}

	public void deleteAttendee(Attendee attendee) {
		deleteEntity(attendee);

	}

	public void deleteAttendeeById(int attendeeId) {
		deleteEntityById(Attendee.class, attendeeId);
	}

	public User getUserByEmail(String email) {		
		return (User) findUniqueFiltered(User.class, User.PROP_EMAIL, email);
	}

	@SuppressWarnings("unchecked")
	public List<Presenter> getAllPresenters(int conferenceId) {
		String queryString = "select distinct s.Presentation.Abstract.Presenter from Session s where s.ConferenceId = :cid";
		return getHibernateTemplate().findByNamedParam(queryString, "cid", conferenceId);
	}

	@SuppressWarnings("unchecked")
	public List<Presenter> getRandomPresenters(int conferenceId, int count) {
		//TODO this query is MySQL specific - find a cross DB solution
		String queryString = "select distinct s.Presentation.Abstract.Presenter from Session s where s.ConferenceId = :cid order by rand() limit :count";
		return getHibernateTemplate().findByNamedParam(queryString, new String[]{"cid", "count"}, new Object[]{conferenceId, count});		
	}

	@SuppressWarnings("unchecked")
	public List<Presenter> getPresentersForTopic(int conferenceId, int topicId) {
		String queryString = "select distinct s.Presentation.Abstract.Presenter from Session s where s.ConferenceId = :cid and s.Presentation.Abstract.PresentationTopic.Id = :tid";
		return getHibernateTemplate().findByNamedParam(queryString, new String[]{"cid", "tid"}, new Object[]{conferenceId, topicId});
	}
	
	@SuppressWarnings("unchecked")
	public List<Presenter> getPresentersByPresentationType(int conferenceId, int presentationTypeId) {
		String queryString = "select distinct s.Presentation.Abstract.Presenter from Session s where s.ConferenceId = :cid and s.Presentation.Abstract.PresentationType.Id = :presentationTypeId";
		return getHibernateTemplate().findByNamedParam(queryString, new String[]{"cid", "presentationTypeId"}, new Object[]{conferenceId, presentationTypeId});	
	}	
	
	@SuppressWarnings("unchecked")
	public List<Presenter> getKeyNotePresenters(int conferenceId) {
		String queryString = "select distinct s.Presentation.Abstract.Presenter from Session s where s.ConferenceId = :cid and s.Presentation.Abstract.PresentationType.Name = :presentationTypeName";
		//TODO what if they change the name KeyNote?
		return getHibernateTemplate().findByNamedParam(queryString, new String[]{"cid", "presentationTypeName"}, new Object[]{conferenceId, "KeyNote"});		
	}

	public User getUserById(int userId) {		
		return (User) getEntityById(User.class, userId);
	}

}
