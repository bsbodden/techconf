package com.integrallis.techconf.web.tapestry.pages.conference;

import java.util.List;

import org.apache.tapestry.event.PageEvent;

import com.integrallis.techconf.dto.SessionInfo;
import com.integrallis.techconf.web.tapestry.pages.common.ActiveConferencePage;

public abstract class Sessions extends ActiveConferencePage {

	public void pageBeginRender(PageEvent event) {
		super.pageBeginRender(event);
		
		setConferenceSessions(getSessions(getConferenceId()));
System.out.println("What about sesions - "+getConferenceSessions());		
	}
	
	private List<SessionInfo> getSessions(int conferenceId) {
		return getConferenceService().getSessionsForConference(conferenceId);
	}
	
	public abstract List<SessionInfo> getConferenceSessions();
	public abstract void setConferenceSessions(List<SessionInfo> s);
}
