/*
 * @(#)ConferenceDAOImpl.java	Aug 25, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.spring.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.integrallis.techconf.dao.ConferenceDAO;
import com.integrallis.techconf.domain.Conference;
import com.integrallis.techconf.domain.Room;

public class ConferenceDAOImpl extends BaseAbstractDAO implements ConferenceDAO {
	
	public ConferenceDAOImpl() {}
	
	public Conference getConference(int conferenceId) {
		return (Conference) getEntityById(Conference.class, conferenceId);
	}

	public Conference getConferenceByName(String name) {
		return (Conference) findUniqueFiltered(Conference.class, Conference.PROP_NAME, name);
	}

	@SuppressWarnings("unchecked")
	public List<Conference> getActiveConferences(Date beginDate, Date endDate) {
    	return getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(Conference.class).add(
						Restrictions.between(Conference.PROP_START_DATE,
								beginDate, endDate)).add(
						Restrictions.between(Conference.PROP_END_DATE,
								beginDate, endDate)));		
	}

	@SuppressWarnings("unchecked")
	public List<Conference> getActiveConferences(Date date) {
		return getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(Conference.class)
            .add( Restrictions.le(Conference.PROP_START_DATE, date))
            .add( Restrictions.ge(Conference.PROP_END_DATE, date)));		
	}

	public void save(Conference conference) {
		saveEntity(conference);
	}

	public void update(Conference conference) {
		updateEntity(conference);
	}

	public void delete(Conference conference) {
		deleteEntity(conference);		
	}

	public void delete(int conferenceId) {
		deleteEntityById(Conference.class, conferenceId);
	}

	@SuppressWarnings("unchecked")
	public List<Conference> getAllConferences() {		
		return findAll(Conference.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Room> getRooms(int venueId) {
		return findFiltered(Room.class, "Venue.Id", venueId);
	}
}
