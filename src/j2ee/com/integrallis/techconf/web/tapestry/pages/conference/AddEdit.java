package com.integrallis.techconf.web.tapestry.pages.conference;

import org.apache.tapestry.IRequestCycle;
import org.apache.tapestry.event.PageBeginRenderListener;
import org.apache.tapestry.event.PageEvent;
import org.apache.tapestry.html.BasePage;
import org.apache.tapestry.valid.IValidationDelegate;
import org.dynadto.Builder;
import org.dynadto.BuilderFactory;

import com.integrallis.techconf.dto.ConferenceSummary;
import com.integrallis.techconf.service.ConferenceService;

public abstract class AddEdit extends BasePage implements PageBeginRenderListener{
	
	private ConferenceSummary summary = null;
	
	/**
	 * Gets the conference summary for a particular conference.
	 */
	public void pageBeginRender(PageEvent event) {
	   
    	// TODO - Need a method to get a list of all the converences		
		//ConferenceSummary summary = (ConferenceSummary)FactoryHelper.createDTO(ConferenceSummary.class);
		Builder builder = getBuilderFactory().getBuilder(ConferenceSummary.class);
		summary = (ConferenceSummary)builder.build();
		//ConferenceSummaryBean summary = new ConferenceSummaryBean();
		//MapDTO map = new MapDTO();
		//ConferenceSummary summary = (ConferenceSummary)MapProxy.newInstance(map, ConferenceSummary.class);
	}	
		
	/**
	 * Gets the conference summary for a particular conference.
	 */
	public String add(IRequestCycle cycle) {
		System.out.println("IN Add");
		// Dont use init, use detach maybe??
		//super.initialize();
		//super.detach();
		// Only use IPAge if you need to set variables on that page first.
		//IPage page = cycle.getPage("conference/Summary");        
        //cycle.activate(page);
		// TODO - somehow i want to create a DTO from this instead
		// define a converter maybe??
		IValidationDelegate delegate = (IValidationDelegate)getBeans().getBean("delegate");
		if (delegate.getHasErrors()) {
			return null;
		}
		System.out.println("Released errors");
		System.out.println("Here we go - "+getConferenceSummary().getConferenceTitle());
		return "conference/AddEdit";
	}

	public void setConferenceSummary(ConferenceSummary s) {
		summary = s;
	}
	
	public ConferenceSummary getConferenceSummary() {
		return summary;
	}

	
	public abstract BuilderFactory getBuilderFactory();	
	public abstract ConferenceService getConferenceService();
}
