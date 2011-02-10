package com.integrallis.techconf.web.tapestry.domain;

import java.io.Serializable;

import com.integrallis.techconf.dto.ConferenceSummary;
import com.integrallis.techconf.dto.UserSummary;

/**
 * Object to keep track of the logged in user
 * 
 * @author Joseph Nusairat
 */
public class LoggedInUser implements Serializable {
	
	private static final long serialVersionUID = 2206056358155827173L;

	private UserSummary userSummary;
	private ConferenceSummary conferenceSummary;

	public ConferenceSummary getConferenceSummary() {
		return conferenceSummary;
	}

	public void setConferenceSummary(ConferenceSummary conferenceSummary) {
		this.conferenceSummary = conferenceSummary;
	}

	public UserSummary getUserSummary() {
		return userSummary;
	}

	public void setUserSummary(UserSummary userSummary) {
		this.userSummary = userSummary;
	}

}
