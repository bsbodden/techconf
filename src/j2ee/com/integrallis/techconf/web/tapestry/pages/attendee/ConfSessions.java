package com.integrallis.techconf.web.tapestry.pages.attendee;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.tacos.ajax.AjaxDirectService;

import org.apache.tapestry.form.IPropertySelectionModel;

import com.integrallis.techconf.dto.ConferenceSummary;
import com.integrallis.techconf.dto.RoomInfo;
import com.integrallis.techconf.dto.ScheduleEntryInfo;
import com.integrallis.techconf.dto.SessionInfo;
import com.integrallis.techconf.service.ScheduleService;
import com.integrallis.techconf.web.tapestry.Util;
import com.integrallis.techconf.web.tapestry.common.DatePropertySelectionModel;
import com.integrallis.techconf.web.tapestry.pages.common.ActiveConferencePage;

public abstract class ConfSessions extends ActiveConferencePage {

	SessionInfo session = null;	
	
	/**
	 * This is for initial entry into the page.
	 * 
	 * It will load up two main sets of data
	 * A list of all the available sessions and a map of all the sessions one has signed up for.
	 * 
	 * @param cycle
	 */
	 protected void finishLoad()
	 {
		 // load up non specific items
		 load();
	    
		 // get the sessions already added
		 processSchedulesForUser(
				getScheduleService().getScheduleForUser(getUserInformation().getUserSummary().getId())
		);
	 }      
	
	
	 /**
	  * Loads up the components for the page.
	  * These are not derived by anything set except the user object.
	  *
	  */
	 private void load() {
		// get a list of the dates and times we can select from.
		// we will base the date off of the conference summary range
		// and by the official start and end time.
		ConferenceSummary summary = getUserInformation().getConferenceSummary();
		Date startDateTime = Util.getBeginingOfConferenceDay(summary.getStartDate());
		Date endDateTime = Util.getEndOfConferenceDay(summary.getStartDate());
	
		// Get a list of all the available sessions.
		// for the left layer
		List<SessionInfo> sessions = getConferenceService()
										.getSessionsByDateRange(
												startDateTime, 
												endDateTime,
												summary.getConferenceId());
		
		// set available sesions
		setAllSessions(sessions);				
		
		// let a list of rooms for the right side
		int venueId = getUserInformation().getConferenceSummary().getVenueId();
		setRooms(getConferenceService().getRooms(venueId));
		
		// get a list of all the sessions the user has signed up for
		
		// Save a list of the date ranges to be used for the start of
		// the day to end of the day.
		setStartTime(startDateTime);
		setStopTime(endDateTime);
		
		// save the date range for the different dates the conference is in session
		// we will save it as a property selection model
		setDateRangeModel(createDatePropertySelection());
	}
	
	/**
	 * This will add a session to the sessions assigned.
	 * 
	 * @param cycle	
	 */
// Another way to display it	
//public void addSession(IRequestCycle cycle) {
//	Integer currSessionId = (Integer)cycle.getListenerParameters()[0];	
	public void addSession(Integer currSessionId) {
//		Force removal of our component on the client
//        if (getAjaxEngine() != null 
  //              && getAjaxEngine().getAjaxRequest().isValidRequest()) {        	
        	/*
            AjaxWebRequest ajaxr = getAjaxEngine().getAjaxRequest();            
            ajaxr.getResponseBuilder()
            .getScriptWriter()
            .printRaw("<script>"
                    + "setTimeout((function() { dojo.dom.removeNode("
                    + "document.getElementById('note_" + currSessionId + "')); }), 10);"
                    + "</script>");
            */
        	// now add the schedule
        	ScheduleEntryInfo info = getScheduleService().scheduleSessionForUser(
								        			getUserInformation().getUserSummary().getId(),
								        			currSessionId );
        	// now add it to the map
        	Map<Integer, ScheduleEntryInfo> map = getSessionsAssigned();
        	map.put(currSessionId, info);
        	setSessionsAssigned(map);
    //    }
        
	}
	
	/**
	 * Removes the schedule entry from our schedule and remove it from the sessions assigned.
	 * 
	 * @param scheduleEntryId
	 * @param scheduleId
	 */
	public void removeSession(Integer scheduleEntryId, Integer scheduleId) {
		// Remove the schedule
		getScheduleService().unscheduleSessionForUser(scheduleEntryId);

		// remove it from the map
		Map<Integer, ScheduleEntryInfo> map = getSessionsAssigned();
		map.remove(scheduleId);
		setSessionsAssigned(map);
	}
	
	/**
	 * This is used to change the date at the top.
	 * 
	 * @param cycle
	 */
	public void changeConferenceDate() {
		// does nothing.
	}
	
	/**
	 * Convert the list of schedules to a map of the schedules assigned.
	 * 
	 * @param list
	 */
	@SuppressWarnings("unchecked")
	protected void processSchedulesForUser(List<ScheduleEntryInfo> list) {
		
		Map<Integer, ScheduleEntryInfo> map = new HashMap<Integer, ScheduleEntryInfo>();
		
		for (ScheduleEntryInfo info : list) {
			map.put(info.getSessionId(), info);
		}
		setSessionsAssigned(map);
	}
	
	/**
	 * This will create the date range to be displayed on this page.
	 * This will be created as a date property selection model.
	 * 
	 * @return
	 */
	protected IPropertySelectionModel createDatePropertySelection() {
		List<Date> list = new ArrayList<Date>();
		
		// the start and end date.
		Date startDate = getUserInformation().getConferenceSummary().getStartDate();
		Date endDate = getUserInformation().getConferenceSummary().getEndDate();

		// A list of dates from start to end		
		Calendar c = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c.setTime(startDate);		
		c2.setTime(endDate);

		// A selection for each day.
		for (c.setTime(startDate); c.before(c2); c.add(Calendar.DAY_OF_MONTH, 1)) {
			list.add(c.getTime());
		}

		// convert to a date property selection model
		return new DatePropertySelectionModel(list);
	}
	
	// Service & Engine Objects
	public abstract AjaxDirectService getAjaxEngine();
	public abstract ScheduleService getScheduleService();
	/**
	 * These are all the user objects for the page.
	 */
	// All sessions to be displayed on the page
	public abstract List<SessionInfo> getAllSessions();
	public abstract void setAllSessions(List<SessionInfo> s);
	
	// A Map of the sessions assigned to the user.
	public abstract Map<Integer, ScheduleEntryInfo> getSessionsAssigned();
	public abstract void setSessionsAssigned(Map<Integer, ScheduleEntryInfo> s);
	
	// Selection model for the date ranges for the top of the page.
	public abstract IPropertySelectionModel getDateRangeModel();
	public abstract void setDateRangeModel(IPropertySelectionModel i);
	
	// Possible roms
	public abstract List<RoomInfo> getRooms();
	public abstract void setRooms(List<RoomInfo> r);
	
	
	
	
	
	public abstract Date getDateSelect();
	public abstract void setDateSelect(Date d);
	
	
	
	public abstract Date getStartTime();
	public abstract void setStartTime(Date d);
	public abstract Date getStopTime();
	public abstract void setStopTime(Date d);
	
	// Used for setting the session Info.
	public SessionInfo getSession() {
		return session;
	}
	
	public void setSession(SessionInfo s) {
		session = s;
	}
	
}
