/*
 * @(#)NewsDAOImpl.java	Sep 4, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.spring.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.integrallis.techconf.dao.NewsDAO;
import com.integrallis.techconf.domain.News;

public class NewsDAOImpl extends BaseAbstractDAO implements NewsDAO {
	
	public NewsDAOImpl() {
	}

	@SuppressWarnings("unchecked")
	public List<News> getAllNews(int conferenceId) {	
		return findFiltered(News.class, News.PROP_CONFERENCE_ID, conferenceId, News.PROP_DATE);
	}

	@SuppressWarnings("unchecked")
	public List<News> getNewsForDate(int conferenceId, Date date) {
		return getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(News.class)
	        .add(Expression.eq(News.PROP_CONFERENCE_ID, conferenceId))
	        .add(Expression.eq(News.PROP_DATE, date)));
	}

	@SuppressWarnings("unchecked")
	public List<News> getNewsForPeriod(int conferenceId, Date startDate,
			Date endDate) {
		return getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(News.class)
            .add( Restrictions.le(News.PROP_DATE, endDate))
            .add( Restrictions.ge(News.PROP_DATE, startDate)));		
	}

	public News saveNewsItem(News item) {
		saveEntity(item);
		return item;
	}

	public News updateNewsItem(News item) {
		updateEntity(item);
		return item;
	}

	public void purgeOldNews(final int conferenceId) {
		final String queryString = "delete from News where ConferenceId = :cid and RemoveOn >= :now";
		getHibernateTemplate().execute(new HibernateCallback(){			
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				session.createQuery(queryString)
		               .setInteger("cid", conferenceId)
		               .setDate("now", new Date())
					   .executeUpdate();
				return null;
			}
		});	
	}

	public void deleteNewsItem(News item) {
		deleteEntity(item);
	}

	public void deleteNewsItem(int id) {
		deleteEntityById(News.class, id);
	}

	public boolean publishNewsItem(int id) {
		boolean result = true;
		News item = getNewsItem(id);
		if (item != null) {
           if (!item.getIsPublished()) {
        	   item.setIsPublished(true);
        	   updateNewsItem(item);
           }
		}
		else {
			result = false;
		}
		return result;
	}

	public News getNewsItem(int id) {		
		return (News) getEntityById(News.class, id);
	}

}
