package com.integrallis.techconf.web.tapestry.pages.attendee;

import org.apache.tapestry.IExternalPage;
import org.apache.tapestry.IRequestCycle;

import com.integrallis.techconf.dto.PresentationAbstract;
import com.integrallis.techconf.web.tapestry.pages.common.ActiveConferencePage;

/**
 * This is designed to submit an abstract presentation.
 * 
 * @author Joseph Nusairat
 */
public abstract class AbstractSubmission extends ActiveConferencePage implements IExternalPage {
	
	public void activateExternalPage(Object[] params, IRequestCycle cycle) {
		// TODO i think we have to initialize the presentation abstract
	}
	
	public String submit(IRequestCycle cycle) {
		// check the validation errors
		//if (getDelegate().getHasErrors()) {
			//return null;
		//}
		
		// now submit it
		getConferenceService().submitAbstract(getPresentationAbstract());
		
		return "attendee/AbstractSubmission";
	}
	
	// Gets and sets the presentation abstract
	public abstract PresentationAbstract getPresentationAbstract();
	public abstract void setPresentationAbstract(PresentationAbstract a);
}
