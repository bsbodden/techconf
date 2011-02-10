/*
 * @(#)ActiveConferencePage.java	Sep 29, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.web.tapestry.pages.common;

import org.apache.tapestry.PageRedirectException;
import org.apache.tapestry.annotations.InjectObject;
import org.apache.tapestry.annotations.InjectPage;
import org.apache.tapestry.annotations.InjectState;
import org.apache.tapestry.event.PageBeginRenderListener;
import org.apache.tapestry.event.PageEvent;
import org.apache.tapestry.html.BasePage;

import com.integrallis.techconf.dto.ConferenceSummary;
import com.integrallis.techconf.service.ConferenceService;
import com.integrallis.techconf.web.tapestry.domain.LoggedInUser;
import com.integrallis.techconf.web.tapestry.pages.Home;

/**
 * This is the base page for any conference page, which should be all but the 
 * home page of the site.
 * 
 * Common utility methods belong in here.
 * 
 * @author Joseph Nusairat
 */
public abstract class ActiveConferencePage extends BasePage implements PageBeginRenderListener {

	/**
	 * This checks to make sure that there is an active conference always loaded.
	 * If not it redirects one to the home page of the site.
	 */
	public void pageBeginRender(PageEvent event) {
		ConferenceSummary summary = getUserInformation().getConferenceSummary();		
		if (summary == null) {			
			// if it is null, no active conference is present
			// forward to the home page
			// the exception will force the page to the home page.
			throw new PageRedirectException(getHomePage());
		}
	}
	
	/**
	 * Gets the conference id.
	 * @return
	 */
	protected int getConferenceId() {
		return getUserInformation().getConferenceSummary().getConferenceId();
	}
	
	// get and store the user information
	@InjectState("logged-in-user-data")
	public abstract LoggedInUser getUserInformation();
	public abstract void setUserInformation(LoggedInUser u);
	
	@InjectPage("Home")
	public abstract Home getHomePage();
	
	// The conference service is needed across all conference pages
	@InjectObject("service:app.ConferenceService")	
	public abstract ConferenceService getConferenceService();
}