package com.integrallis.techconf.web.tapestry.pages.conference;

import java.util.List;

import org.apache.tapestry.event.PageEvent;

import com.integrallis.techconf.dto.PresentationSummary;
import com.integrallis.techconf.web.tapestry.pages.common.ActiveConferencePage;

/**
 * Pull the keynote prsentations for the conference.
 * 
 * @author Joseph Nusairat
 *
 */
public abstract class Keynote extends ActiveConferencePage {
	
	public void pageBeginRender(PageEvent event) {
		super.pageBeginRender(event);
		
		// get and set the presenters
		List<PresentationSummary> presenters = getConferenceService().getKeynotes(getConferenceId());
		setPresentations(presenters);
	}
	
	public abstract void setPresentations(List<PresentationSummary> p);
	public abstract List<PresentationSummary> getPresentations();

}
