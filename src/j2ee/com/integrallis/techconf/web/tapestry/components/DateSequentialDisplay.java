package com.integrallis.techconf.web.tapestry.components;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.tapestry.AbstractComponent;
import org.apache.tapestry.IMarkupWriter;
import org.apache.tapestry.IRequestCycle;
import org.apache.tapestry.annotations.ComponentClass;
import org.apache.tapestry.annotations.Parameter;

@ComponentClass(allowBody = true, allowInformalParameters = false)
public abstract class DateSequentialDisplay extends AbstractComponent {
	
	@Parameter(required = true)
	public abstract int getIncrement();
	
	@Parameter(required = true)
	public abstract Date getStartTime();
	
	@Parameter(required = true)
	public abstract Date getStopTime();
	
	@Parameter(required = false)
	public abstract String getElement();
	
	@Parameter(required = false)
	public abstract String getSubElement();
	
	@Parameter(required = false)
	public abstract Date getTimeOn();
	public abstract void setTimeOn(Date d);
	
	@Override
    protected void renderComponent(IMarkupWriter writer, IRequestCycle cycle)
    {
		// Formatter
		SimpleDateFormat format = new SimpleDateFormat("MM / dd / yyyy HH:mm aaa");
		
		// initialize our calendar to the first position
		Calendar c = Calendar.getInstance();
		c.setTime(getStartTime());
		
		// now iterate through a list of calendars.
		// till the calendar is before the stop time
		for ( ; c.getTime().compareTo(getStopTime()) < 0; c.add(Calendar.MINUTE, getIncrement())) {
			//TODO - change to only display if not null
			writer.begin(getElement() == null ? "" : getElement());
			writer.begin(getSubElement() == null ? "" : getSubElement());
			// set the time on
			setTimeOn(c.getTime());
			
			// write the date
			String dateStr = format.format(c.getTime());
			writer.print(dateStr);
			
			writer.end();
//System.out.println("Time - " + c.getTime());		
			// display the body
			renderBody(writer, cycle);
						
			writer.end();			
		}		
    }
	
}
