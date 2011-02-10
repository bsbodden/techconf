package com.integrallis.techconf.spring.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.integrallis.techconf.dto.ConferenceSummary;
import com.integrallis.techconf.service.ConferenceService;

/**
 * @author Brian Sam-Bodden
 */
public class ListConferencesController extends AbstractController {

	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		List<ConferenceSummary> conferences = conferenceService
				.getAllConferences();

		return new ModelAndView("conferenceList", "conferences", conferences);
	}

	private ConferenceService conferenceService;

	public void setConferenceService(ConferenceService conferenceService) {
		this.conferenceService = conferenceService;
	}
}
