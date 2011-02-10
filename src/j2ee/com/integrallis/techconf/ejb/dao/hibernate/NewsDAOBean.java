/*
 * @(#)NewsDAOBean.java	Sep 4, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.ejb.dao.hibernate;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;

import com.integrallis.techconf.dao.NewsDAO;
import com.integrallis.techconf.domain.News;

@Stateless
public class NewsDAOBean extends BaseAbstractDAO implements NewsDAO {
	
	public NewsDAOBean() {
	}

	@SuppressWarnings("unchecked")
	public List<News> getAllNews(int conferenceId) {	
		return findFiltered(News.class, News.PROP_CONFERENCE_ID, conferenceId, News.PROP_DATE);
	}

	@SuppressWarnings("unchecked")
	public List<News> getNewsForDate(int conferenceId, Date date) {
		Criteria criteria = createCriteria(News.class)
	        .add(Expression.eq(News.PROP_CONFERENCE_ID, conferenceId))
	        .add(Expression.eq(News.PROP_DATE, date));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<News> getNewsForPeriod(int conferenceId, Date startDate,
			Date endDate) {
		Criteria criteria = createCriteria(News.class)
            .add( Restrictions.le(News.PROP_DATE, endDate))
            .add( Restrictions.ge(News.PROP_DATE, startDate));
        return criteria.list();		
	}

	public News saveNewsItem(News item) {
		saveEntity(item);
		return item;
	}

	public News updateNewsItem(News item) {
		updateEntity(item);
		return item;
	}

	public void purgeOldNews(int conferenceId) {
		String queryString = "delete from News where ConferenceId = :cid and RemoveOn >= :now";
		int purgeCount = createQuery(queryString)
		    .setInteger("cid", conferenceId)
		    .setDate("now", new Date())
		    .executeUpdate();
        System.out.println("purgeOldNews purged " + purgeCount + " news items");
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
