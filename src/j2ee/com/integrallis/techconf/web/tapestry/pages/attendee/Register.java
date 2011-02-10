package com.integrallis.techconf.web.tapestry.pages.attendee;

import java.util.Collection;
import java.util.List;

import net.sf.tacos.ajax.components.ListItemRenderer;

import org.apache.tapestry.IRequestCycle;
import org.apache.tapestry.event.PageEvent;
import org.apache.tapestry.valid.IValidationDelegate;
import org.dynadto.Builder;
import org.dynadto.BuilderFactory;

import com.integrallis.techconf.dto.AddressInfo;
import com.integrallis.techconf.dto.AttendeeInfo;
import com.integrallis.techconf.dto.Location;
import com.integrallis.techconf.service.LocationLookupService;
import com.integrallis.techconf.service.UserService;
import com.integrallis.techconf.web.tapestry.common.LocationRenderer;
import com.integrallis.techconf.web.tapestry.pages.common.ActiveConferencePage;

public abstract class Register extends ActiveConferencePage {
	
    /** List html renderer */
    private static final ListItemRenderer LOCATION_RENDERER = new LocationRenderer();
    
    /** DynaDTO builders */
    private Builder attendeeBuilder = null;
    private Builder addressBuilder = null;
    
    /**
     * The renderer
     * @return
     */
    public ListItemRenderer getListRenderer() {
        return LOCATION_RENDERER;
    }	

	public void pageBeginRender(PageEvent event) {
		super.pageBeginRender(event);
		if (getUser() == null) {
			// get the builders if necessary
			if (attendeeBuilder == null) {
				attendeeBuilder = getBuilderFactory().getBuilder(AttendeeInfo.class);
			}
			if (addressBuilder == null) {
				addressBuilder = getBuilderFactory().getBuilder(AddressInfo.class);
			}
            // set the user/address empty DTOs if one is not already set
		    setUser((AttendeeInfo)attendeeBuilder.build());
		    getUser().setAddress((AddressInfo) addressBuilder.build());
		}
	}	
	
	public String add(IRequestCycle cycle ) {
		
		IValidationDelegate delegate = (IValidationDelegate)getBeans().getBean("delegate");
		if (delegate.getHasErrors()) {
			return null;
		}
		
		// if it passes validation then register them
		setUser( getUserService().registerAttendee(getUser()) );
		
		// back to the page
		return "attendee/Register";
	}
	
	public void searchZipCodes(String searchString) {
		System.out.println("Searching for Zip Codes...");
		List<Location> locations = getLocationLookupService().searchLocations(searchString);
		setZipCodeList(locations);
	}

    public abstract Collection getZipCodeList();
    public abstract void setZipCodeList(Collection values);
	
	public abstract void setUser(AttendeeInfo i);
	public abstract AttendeeInfo getUser();
	
	public abstract UserService getUserService();
	public abstract LocationLookupService getLocationLookupService();
	
	public abstract BuilderFactory getBuilderFactory();
}
