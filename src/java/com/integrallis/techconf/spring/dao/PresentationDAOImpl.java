/*
 * @(#)PresentationDAOImpl.java	Sep 7, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.spring.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.integrallis.techconf.dao.PresentationDAO;
import com.integrallis.techconf.domain.Abstract;
import com.integrallis.techconf.domain.Presentation;
import com.integrallis.techconf.domain.Session;

public class PresentationDAOImpl extends BaseAbstractDAO implements
		PresentationDAO {
	
	public PresentationDAOImpl() {
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
		return getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(Abstract.class)
          .add(Expression.eq("Presenter.Id", presenterId)));
	}
	
	@SuppressWarnings("unchecked")
	public List<Presentation> getAllPresentations(int conferenceId) {
		String queryString = "select distinct s.Presentation from Session s where s.ConferenceId = :cid";
		return getHibernateTemplate().findByNamedParam(queryString, "cid", conferenceId);
	}	
	
	@SuppressWarnings("unchecked")
	public List<Presentation> getPresentationsForPresenter(int conferenceId, int presenterId) {
		String queryString = "select distinct s.Presentation from Session s where s.ConferenceId = :cid and s.Presentation.Abstract.Presenter.Id = :presenterId";
		return getHibernateTemplate().findByNamedParam(queryString, new String[]{"cid", "presenterId"}, new Object[]{conferenceId, presenterId});
	}

	@SuppressWarnings("unchecked")
	public List<Presentation> getKeyNotePresentations(int conferenceId) {
		String queryString = "select distinct s.Presentation from Session s where s.ConferenceId = :cid and s.Presentation.Abstract.PresentationType.Name = :presentationTypeName";
		return getHibernateTemplate().findByNamedParam(queryString, new String[]{"cid", "presentationTypeName"}, new Object[]{conferenceId, "KeyNote"});		
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
		return getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(Presentation.class).add(example));
	}
	
	@SuppressWarnings("unchecked")
	public List<Session> getAllSessions(int conferenceId) {
		String queryString = "select distinct s from Session s where s.ConferenceId = :cid";
		return getHibernateTemplate().findByNamedParam(queryString, "cid", conferenceId);		
	}

	@SuppressWarnings("unchecked")
	public List<Session> getSessionsForPresentation(int presentationId, int conferenceId) {
		String queryString = "select distinct s.Presentation from Session s where s.ConferenceId = :cid and s.Presentation.Id = :presentationId";
		return getHibernateTemplate().findByNamedParam(queryString, new String[]{"cid", "presentationId"}, new Object[]{conferenceId, presentationId});		
	}

	@SuppressWarnings("unchecked")
	public List<Session> getSessionsForRoom(int roomId, int conferenceId) {
		String queryString = "select distinct s from Session s where s.ConferenceId = :cid and s.Room.Id = :roomId";
		return getHibernateTemplate().findByNamedParam(queryString, new String[]{"cid", "roomId"}, new Object[]{conferenceId, roomId});
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
		return getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(Session.class).add(example));		
	}

	@SuppressWarnings("unchecked")
	public List<Session> getSessionsByDate(Date date, int conferenceId) {
		return getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(Session.class)
            .add( Restrictions.ge(Session.PROP_DATE_TIME_END, date))
            .add( Restrictions.le(Session.PROP_DATE_TIME_BEGIN, date))
            .add( Expression.eq(Session.PROP_CONFERENCE_ID, conferenceId)));
	}

	@SuppressWarnings("unchecked")
	public List<Session> getSessionsByDateRange(Date startDate, Date endDate, int conferenceId) {
		return getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(Session.class)
	        .add( Restrictions.ge(Session.PROP_DATE_TIME_END, startDate))
	        .add( Restrictions.le(Session.PROP_DATE_TIME_BEGIN, endDate))
	        .add( Restrictions.eq(Session.PROP_CONFERENCE_ID, conferenceId))
			.addOrder(Order.asc(Session.PROP_DATE_TIME_BEGIN)));
	}
	
	@SuppressWarnings("unchecked")
	public List<Session> getSessionsByTrack(int conferenceId, int trackId) {
		String queryString = "select distinct s from Session s where s.ConferenceId = :cid and s.Presentation.Abstract.Track.Id = :trackId";
		return getHibernateTemplate().findByNamedParam(queryString, new String[]{"cid", "trackId"}, new Object[]{conferenceId, trackId});		
	}
	
	public Abstract getAbstract(int abstractId) {
		return (Abstract) getEntityById(Abstract.class, abstractId);
	}

	public Session getSessionById(int sessionId) {		
		return (Session) getEntityById(Session.class, sessionId);
	}

}
