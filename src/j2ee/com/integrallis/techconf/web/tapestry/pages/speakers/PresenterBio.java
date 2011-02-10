package com.integrallis.techconf.web.tapestry.pages.speakers;

import java.util.List;

import org.apache.tapestry.IExternalPage;
import org.apache.tapestry.IRequestCycle;

import com.integrallis.techconf.dto.BlogEntry;
import com.integrallis.techconf.dto.PresentationSummary;
import com.integrallis.techconf.web.tapestry.pages.common.ActiveConferencePage;

/**
 * Thi
 * @author Joseph Nusairat
 */
public abstract class PresenterBio extends ActiveConferencePage implements IExternalPage { 
	
	/**
	 * This will pull the speaker information.
	 * 
	 * @param cycle
	 */
	public void activateExternalPage(java.lang.Object[] parameters, IRequestCycle cycle) {
		// Presenter Id
		int presenterId = ((Integer)parameters[0]).intValue();

		// Get the general info about the presenter
		
		// get the sessions they are teaching
		setPresentationList(
				getConferenceService().getPresentationsForPresenter(getConferenceId(), presenterId)
		);
		
		// get the blogs for the user
		setPresenterBlogs(
				getConferenceService().getBlogEntriesForPresenter(presenterId)
		);
	}

	public abstract void setPresentationList(List<PresentationSummary> l);
	public abstract List<PresentationSummary> getPresentationList();

	public abstract void setPresenterBlogs(List<BlogEntry> l);
	public abstract List<BlogEntry> getPresenterBlogs();
}
