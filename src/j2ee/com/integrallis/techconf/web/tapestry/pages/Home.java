package com.integrallis.techconf.web.tapestry.pages;

import java.util.List;

import org.apache.tapestry.event.PageBeginRenderListener;
import org.apache.tapestry.event.PageEvent;
import org.apache.tapestry.html.BasePage;

import com.integrallis.techconf.dto.ConferenceSummary;
import com.integrallis.techconf.service.ConferenceService;

/**
 * This is the home page for the app.
 * 
 * This will just get the list of all the conferences for page display.
 * 
 * @author Joseph Nusairat
 */
public abstract class Home extends BasePage implements PageBeginRenderListener {

	/**
	 * Gets the conference summary for a particular conference.
	 */
	public void pageBeginRender(PageEvent event) {
	   
		setConferences(getConferenceService().getAllConferences());		
	}	
	
	public abstract ConferenceService getConferenceService();
	
	public abstract void setConferences(List<ConferenceSummary> s);
	public abstract List<ConferenceSummary> getConferences();
}
