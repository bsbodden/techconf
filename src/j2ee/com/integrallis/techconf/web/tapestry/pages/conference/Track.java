package com.integrallis.techconf.web.tapestry.pages.conference;

import java.util.List;

import org.apache.tapestry.IExternalPage;
import org.apache.tapestry.IRequestCycle;

import com.integrallis.techconf.dto.SessionInfo;
import com.integrallis.techconf.web.tapestry.pages.common.ActiveConferencePage;

/**
 * This will get a page for track information. 
 * 
 * @author Joseph Nusairat
 */
public abstract class Track extends ActiveConferencePage implements IExternalPage {
	
	/**
	 * Loads up the page with the track information.
	 */
	public void activateExternalPage(java.lang.Object[] parameters, IRequestCycle cycle) {
		List<SessionInfo> sessions = getConferenceService().getSessionsByTrack(getConferenceId(), ((Integer)parameters[0]).intValue());
		setSessionsForTrack(sessions);
	}
	
	public abstract void setSessionsForTrack(List<SessionInfo> s);
	public abstract List<SessionInfo> getSessionsForTrack();

}
