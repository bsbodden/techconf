package com.integrallis.techconf.spring.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.integrallis.techconf.dto.ConferenceSummary;
import com.integrallis.techconf.dto.PresenterSummary;
import com.integrallis.techconf.service.ConferenceService;

/**
 * @author Brian Sam-Bodden
 */
public class ListSpeakersController extends AbstractController {

	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		int conferenceId = Integer.parseInt(request.getParameter("id"));
		
		List<PresenterSummary> speakers = conferenceService.getPresentersSummaryList(conferenceId);
		
		ConferenceSummary conference = conferenceService.getConferenceSummary(conferenceId);
		
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("speakers", speakers);//TODO put this in the session, duh!
		model.put("conference", conference);		

		return new ModelAndView("speakerList", model);
	}

	private ConferenceService conferenceService;

	public void setConferenceService(ConferenceService conferenceService) {
		this.conferenceService = conferenceService;
	}
}
