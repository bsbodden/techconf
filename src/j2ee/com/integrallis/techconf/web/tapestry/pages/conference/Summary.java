package com.integrallis.techconf.web.tapestry.pages.conference;

import java.util.List;

import org.apache.tapestry.IExternalPage;
import org.apache.tapestry.IRequestCycle;
import org.apache.tapestry.PageRedirectException;

import com.integrallis.techconf.dto.BlogEntry;
import com.integrallis.techconf.dto.ConferenceSummary;
import com.integrallis.techconf.dto.PresenterSummary;
import com.integrallis.techconf.web.tapestry.domain.LoggedInUser;
import com.integrallis.techconf.web.tapestry.pages.common.ActiveConferencePage;

/**
 * This is the summary page for a conference.
 * It will load up the main page for a conference.
 * 
 * @author Joseph Nusairat
 *
 */
public abstract class Summary extends ActiveConferencePage implements IExternalPage {

	private static final int BLOG_ENTRIES = 2;
	private static final int FEATURED_PRESENTERS = 3;
	
	/**
	 * This method is used when being activated from an external page.
	 * This will load up the conference based on the conference id passed through to it
	 * setting it on the state object.
	 * 
	 */
	public void activateExternalPage(java.lang.Object[] parameters, IRequestCycle cycle) {
		Integer conferenceId = (Integer)parameters[0];
		// retrieve the conference Summary and stores it to the user informaton
		ConferenceSummary summary = retrieveConferenceSummary(conferenceId);
		if (summary != null) {
			// set the summary object on the user object
			LoggedInUser user = getUserInformation();
			user.setConferenceSummary(summary);
			// just good practice
			setUserInformation(user);
		}
		else {
			throw new PageRedirectException(getHomePage());
		}
	}
	
	/**
	 * Retrieves the conference summary.
	 * 
	 * @param id
	 * @return
	 */
	private ConferenceSummary retrieveConferenceSummary(Integer id) {
		ConferenceSummary result = null;
		
		// Get the summary for a particular conference
    	result = getConferenceService().getConferenceSummary(id);
    	
    	if (result != null) {    	
        	// Get the featured speakers
        	result.setFeaturedSpeakers(
        			getConferenceService().getFeaturedPresenters(id, FEATURED_PRESENTERS));
    		
        	// get the news
        	result.setNews(
        			getConferenceService().getNews(id));   	

        	// also retrieve the blogs
        	try {
    	    	List<BlogEntry> blogEntries = getConferenceService().getBlogEntries(id, BLOG_ENTRIES);
    	    	setBlogEntries(blogEntries);
        	} catch(Exception e) {
        		// do nothing its ok to not get them, this allows for development in
        		// a machine without internet access
        	}
    	}
    	
    	return result;
	}
		
	/**
	 * Gets the image for the speaker.
	 * @return
	 */
	public String getSpeakerImage() {
		return "../speakerImages/" + getSpeaker().getPresenterId().toString() + ".jpg";	
	}	
	
    /**
     * Page level variables.
     */
	public abstract PresenterSummary getSpeaker();
	
	public abstract void setBlogEntries(List<BlogEntry> blogs);
		
}
