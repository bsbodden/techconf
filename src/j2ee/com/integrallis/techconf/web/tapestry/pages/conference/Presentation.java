package com.integrallis.techconf.web.tapestry.pages.conference;

import org.apache.tapestry.IExternalPage;
import org.apache.tapestry.IRequestCycle;

import com.integrallis.techconf.dto.PresentationSummary;
import com.integrallis.techconf.web.tapestry.pages.common.ActiveConferencePage;

/**
 * This will display the presentation for a given id.
 * 
 * @author Joseph Nusairat
 */
public abstract class Presentation extends ActiveConferencePage implements IExternalPage {

	/**
	 * This will look up the presentation passed in the first argument.
	 */
	public void activateExternalPage(Object[] objs, IRequestCycle cycle) {
		PresentationSummary presentation = 
			getConferenceService().getPresentation(((Integer)objs[0]).intValue());

		// set the presentation
		setPresentation(presentation);
	}
	
	public abstract void setPresentation(PresentationSummary p);
	public abstract PresentationSummary getPresentation();

}
