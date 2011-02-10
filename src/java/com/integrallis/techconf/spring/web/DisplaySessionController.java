package com.integrallis.techconf.spring.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.integrallis.techconf.dto.ConferenceSummary;
import com.integrallis.techconf.service.ConferenceService;

/**
 * @author Brian Sam-Bodden
 */
public class DisplaySessionController extends AbstractController {

	public DisplaySessionController() {
	}

	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int conferenceId = Integer.parseInt(request.getParameter("id"));

		ConferenceSummary conference = conferenceService
				.getConferenceSummary(conferenceId);

		return new ModelAndView("conferenceDetail", "conference", conference);
	}

	private ConferenceService conferenceService;

	public void setConferenceService(ConferenceService conferenceService) {
		this.conferenceService = conferenceService;
	}
}
