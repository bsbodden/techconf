/*
 * @(#)BlogDAOImpl.java	Sep 29, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.spring.dao;

import java.util.List;

import com.integrallis.techconf.dao.BlogDAO;
import com.integrallis.techconf.domain.BlogLink;
import com.integrallis.techconf.domain.Presenter;

/**
 * @author Brian Sam-Bodden
 */
public class BlogDAOImpl extends BaseAbstractDAO implements BlogDAO {

	public BlogDAOImpl() {
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.dao.BlogDAO#save(com.integrallis.techconf.domain.BlogLink)
	 */
	public BlogLink save(BlogLink blogLink) {
		saveEntity(blogLink);
		return blogLink;
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.dao.BlogDAO#update(com.integrallis.techconf.domain.BlogLink)
	 */
	public BlogLink update(BlogLink blogLink) {
		updateEntity(blogLink);
		return blogLink;
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.dao.BlogDAO#delete(com.integrallis.techconf.domain.BlogLink)
	 */
	public void delete(BlogLink blogLink) {
		deleteEntity(blogLink);
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.dao.BlogDAO#delete(int)
	 */
	public void delete(int blogLinkId) {
		deleteEntityById(BlogLink.class, blogLinkId);
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.dao.BlogDAO#getAllBlogLinks(int)
	 */
	@SuppressWarnings("unchecked")
	public List<BlogLink> getAllBlogLinks(int conferenceId) {		
		String queryString = "select distinct s.Presentation.Abstract.Presenter.BlogLink from Session s where s.ConferenceId = :cid";
		return getHibernateTemplate().findByNamedParam(queryString, "cid", conferenceId);
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.dao.BlogDAO#getBlogLinkForPresenter(int, int)
	 */
	public BlogLink getBlogLinkForPresenter(int presenterId) {
		Presenter presenter = (Presenter) getEntityById(Presenter.class, presenterId);
		return presenter.getBlogLink();
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.dao.BlogDAO#getBlogLinkById(int)
	 */
	public BlogLink getBlogLinkById(int blogLinkId) {		
		return (BlogLink) getEntityById(BlogLink.class, blogLinkId);
	}

}
