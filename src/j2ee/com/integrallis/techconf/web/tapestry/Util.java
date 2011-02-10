package com.integrallis.techconf.web.tapestry;

import java.util.Calendar;
import java.util.Date;

/**
 * General Util class for the web tier.
 * 
 * @author Joseph Nusairat
 */
public class Util {
	
	//TODO This conversion needs to be pulled from somewhere else.
	
	/**
	 * Gets the begining of the day.
	 * 
	 * @param d
	 * @return
	 */
	public static Date getBeginingOfConferenceDay(Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.set(Calendar.HOUR, 8);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		return cal.getTime();
	}
	
	/**
	 * Gets the date for the end of the day.
	 * 
	 * @param d
	 * @return
	 */
	public static Date getEndOfConferenceDay(Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.set(Calendar.HOUR, 17);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		return cal.getTime();
	}

}
