package com.integrallis.techconf.spring.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.integrallis.techconf.dto.ConferenceSummary;
import com.integrallis.techconf.dto.SessionInfo;
import com.integrallis.techconf.service.ConferenceService;

/**
 * @author Brian Sam-Bodden
 */
public class ListSessionsController extends AbstractController {

	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int conferenceId = Integer.parseInt(request.getParameter("id"));
		
		List<SessionInfo> sessions = conferenceService.getSessionsForConference(conferenceId);	
		
		ConferenceSummary conference = conferenceService.getConferenceSummary(conferenceId);
		
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("sessions", sessions);
		model.put("conference", conference);//TODO put this in the session, duh!

		return new ModelAndView("sessionList", model);
	}

	private ConferenceService conferenceService;

	public void setConferenceService(ConferenceService conferenceService) {
		this.conferenceService = conferenceService;
	}
}
