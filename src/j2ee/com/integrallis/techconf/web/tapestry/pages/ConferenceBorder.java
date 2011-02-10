package com.integrallis.techconf.web.tapestry.pages;

import org.apache.tapestry.BaseComponent;
import org.apache.tapestry.IRequestCycle;

import com.integrallis.techconf.dto.UserSummary;
import com.integrallis.techconf.service.UserService;
import com.integrallis.techconf.web.tapestry.domain.LoggedInUser;

/**
 * This is a component for the border.
 * The main component of the border deals with logging a user in.
 * 
 * @author Joseph Nusairat
 */
public abstract class ConferenceBorder extends BaseComponent {
	/**
	 * This logs in a user to the site.
	 * And saves the user object back.
	 * @param cycle
	 */
	public void login(IRequestCycle cycle) {		
		LoggedInUser user = getUserInformation();
		
		// Attempt the log in, a null return signifies a failed login
		UserSummary userSummary =  getUserService().login(getLogin(), getPassword());
		
		// if they clear set their user summary information
		user.setUserSummary(userSummary);
		setUserInformation(user);
	}
	
	public void logout(IRequestCycle cycle) {
		LoggedInUser user = getUserInformation();
		user.setUserSummary(null);
		setUserInformation(user);
	}
	
	// Login objects
	public abstract void setLogin(String s);
	public abstract String getLogin();
	public abstract void setPassword(String s);
	public abstract String getPassword();
	
	// get and store the user information
	public abstract LoggedInUser getUserInformation();
	public abstract void setUserInformation(LoggedInUser u);
	
	// the page service
	public abstract UserService getUserService();
}