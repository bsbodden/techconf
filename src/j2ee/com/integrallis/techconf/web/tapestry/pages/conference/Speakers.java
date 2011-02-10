package com.integrallis.techconf.web.tapestry.pages.conference;

import java.util.List;

import org.apache.tapestry.event.PageEvent;

import com.integrallis.techconf.dto.Link;
import com.integrallis.techconf.dto.PresenterSummary;
import com.integrallis.techconf.web.tapestry.pages.common.ActiveConferencePage;

public abstract class Speakers extends ActiveConferencePage {
	
	public void pageBeginRender(PageEvent event) {
		super.pageBeginRender(event);
		
		// get and set the presenters
		List<PresenterSummary> presenters = getConferenceService().getPresentersSummaryList(getConferenceId());

		setPresenters(presenters);
	}
	
	/**
	 * Gets the image for the speaker.
	 * @return
	 */
	public String getSpeakerImage() {
		return "../speakerImages/" + getPresenter().getPresenterId().toString() + ".jpg";	
	}	
	
	public abstract PresenterSummary getPresenter();
	public abstract void setPresenter(PresenterSummary p);
	
	public abstract void setPresenters(List<PresenterSummary> p);
	public abstract List<PresenterSummary> getPresenters();

	public String processLink(Link link) {
		if (link == null) {
			return "";
		}
		// else
		return link.getName();
	}
}
