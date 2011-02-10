/*
 * @(#)PresentationDAOBean.java	Sep 7, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.ejb.dao.hibernate;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.integrallis.techconf.dao.PresentationDAO;
import com.integrallis.techconf.domain.Abstract;
import com.integrallis.techconf.domain.Presentation;
import com.integrallis.techconf.domain.Session;

@Stateless
public class PresentationDAOBean extends BaseAbstractDAO implements
		PresentationDAO {
	
	public PresentationDAOBean() {
	}	

	public void save(Abstract presentationAbstract) {
		saveEntity(presentationAbstract);
	}

	public void update(Abstract presentationAbstract) {
		updateEntity(presentationAbstract);		
	}

	public void delete(Abstract presentationAbstract) {
		deleteEntity(presentationAbstract);		
	}

	public void delete(int abstractId) {
		deleteEntityById(Abstract.class, abstractId);		
	}

	@SuppressWarnings("unchecked")
	public Presentation getPresentation(int presentationId) {
		return (Presentation) getEntityById(Presentation.class, presentationId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Abstract> getAbstractsByPresenter(int presenterId) {
        return createCriteria(Abstract.class)
          .add(Expression.eq("Presenter.Id", presenterId))
          .list();
	}
	
	@SuppressWarnings("unchecked")
	
	public List<Presentation> getAllPresentations(int conferenceId) {
		String queryString = "select distinct s.Presentation from Session s where s.ConferenceId = :cid";
		return createQuery(queryString)
		    .setInteger("cid", conferenceId)
		    .list();
	}	
	
	@SuppressWarnings("unchecked")
	public List<Presentation> getPresentationsForPresenter(int conferenceId, int presenterId) {
		String queryString = "select distinct s.Presentation from Session s where s.ConferenceId = :cid and s.Presentation.Abstract.Presenter.Id = :presenterId";
		return createQuery(queryString)
		    .setInteger("cid", conferenceId)
		    .setInteger("presenterId", presenterId)
		    .list();
	}

	@SuppressWarnings("unchecked")
	public List<Presentation> getKeyNotePresentations(int conferenceId) {
		String queryString = "select distinct s.Presentation from Session s where s.ConferenceId = :cid and s.Presentation.Abstract.PresentationType.Name = :presentationTypeName";
		return createQuery(queryString)
		    .setInteger("cid", conferenceId)
		    .setString("presentationTypeName", "KeyNote")
		    .list(); 
	}

	@SuppressWarnings("unchecked")
	public List<Presentation> getPresentationByExample(Presentation template, int conferenceId) {
		// Create example object
		Example example =
			Example
				.create(template)
				.ignoreCase()
				.excludeZeroes()
				.excludeProperty("Id")
				.enableLike(MatchMode.ANYWHERE);
		
		// create the criteria, add the example and execute
		return createCriteria(Presentation.class).add(example).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Session> getAllSessions(int conferenceId) {
		String queryString = "select distinct s from Session s where s.ConferenceId = :cid";
		return createQuery(queryString)
		    .setInteger("cid", conferenceId)
		    .list();		
	}

	@SuppressWarnings("unchecked")
	public List<Session> getSessionsForPresentation(int presentationId, int conferenceId) {
		String queryString = "select distinct s.Presentation from Session s where s.ConferenceId = :cid and s.Presentation.Id = :presentationId";
		return createQuery(queryString)
		    .setInteger("cid", conferenceId)
		    .setInteger("presentationId", presentationId)
		    .list(); 
	}

	@SuppressWarnings("unchecked")
	public List<Session> getSessionsForRoom(int roomId, int conferenceId) {
		String queryString = "select distinct s from Session s where s.ConferenceId = :cid and s.Room.Id = :roomId";
		return createQuery(queryString)
		    .setInteger("cid", conferenceId)
		    .setInteger("roomId", roomId)
		    .list(); 
	}

	@SuppressWarnings("unchecked")
	public List<Session> getSessionsByExample(Session template) {
		// Create example object
		Example example =
			Example
				.create(template)
				.ignoreCase()
				.excludeZeroes()
				.excludeProperty("Id")
				.enableLike(MatchMode.ANYWHERE);
		
		// create the criteria, add the example and execute
		return createCriteria(Session.class).add(example).list();
	}

	@SuppressWarnings("unchecked")
	public List<Session> getSessionsByDate(Date date, int conferenceId) {
		Criteria criteria = createCriteria(Session.class)
            .add( Restrictions.ge(Session.PROP_DATE_TIME_END, date))
            .add( Restrictions.le(Session.PROP_DATE_TIME_BEGIN, date))
            .add( Expression.eq(Session.PROP_CONFERENCE_ID, conferenceId));
        return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Session> getSessionsByDateRange(Date startDate, Date endDate, int conferenceId) {
		Criteria criteria = createCriteria(Session.class)
	        .add( Restrictions.ge(Session.PROP_DATE_TIME_END, startDate))
	        .add( Restrictions.le(Session.PROP_DATE_TIME_BEGIN, endDate))
	        .add( Restrictions.eq(Session.PROP_CONFERENCE_ID, conferenceId))
			.addOrder(Order.asc(Session.PROP_DATE_TIME_BEGIN));		
	    return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Session> getSessionsByTrack(int conferenceId, int trackId) {
		String queryString = "select distinct s from Session s where s.ConferenceId = :cid and s.Presentation.Abstract.Track.Id = :trackId";
		return createQuery(queryString)
		    .setInteger("cid", conferenceId)
		    .setInteger("trackId", trackId)
		    .list();
	}
	public Abstract getAbstract(int abstractId) {
		return (Abstract) getEntityById(Abstract.class, abstractId);
	}

	public Session getSessionById(int sessionId) {		
		return (Session) getEntityById(Session.class, sessionId);
	}

}
