package com.integrallis.techconf.web.tapestry.components;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.tapestry.AbstractComponent;
import org.apache.tapestry.IMarkupWriter;
import org.apache.tapestry.IRequestCycle;
import org.apache.tapestry.annotations.ComponentClass;
import org.apache.tapestry.annotations.Parameter;

import com.integrallis.techconf.dto.RoomInfo;
import com.integrallis.techconf.dto.SessionInfo;

/**
 * Allows for the display of the session component that is available if it exists for the time frame and the room.
 * And that it is in the specfici increment.
 * 
 * @author Joseph Nusairat
 */
@ComponentClass(allowBody = true, allowInformalParameters = false)
public abstract class InsertRoom extends AbstractComponent {

	@Parameter(required = true)
	public abstract RoomInfo getRoom();
	
	@Parameter(required = true)
	public abstract int getIncrement();
	
	@Parameter(required = true)
	public abstract Date getDate();

	@Parameter(required = true)
	public abstract List<SessionInfo> getSource();
	
	@Parameter(required = false)
	public abstract String getElement();
	
	// not used cause its dynamic binding
	@Parameter(required = true)
	public abstract SessionInfo getValue();
	public abstract void setValue(SessionInfo s);
	
	@Override
    protected void renderComponent(IMarkupWriter writer, IRequestCycle cycle)
    {
		boolean found = false;
		String element = getElement();		
		Iterator<SessionInfo> it = getSource().iterator();
		// TODO - should these lines be moved above hte while
		Calendar cal = Calendar.getInstance();
		cal.setTime(getDate());
		cal.add(Calendar.MINUTE, getIncrement());
		
		while (it.hasNext()) {
			SessionInfo session = it.next();

			// TODO - should be a room id
			// Date should be before date time begin
			// Mod Date should be After start date
			if (session.getRoomName().equals(getRoom().getRoomName())
				&& getDate().compareTo(session.getDateTimeBegin()) <= 0
				&& cal.getTime().compareTo(session.getDateTimeBegin()) > 0 ) {
				
				// set the session for the display object
				// and renders the rest of the body.
				if (element != null) {
					writer.begin(element);
					writer.attribute("rowspan", getRowSpan(session));
				}
				// write the rest
				setValue(session);								
				renderBody(writer, cycle);
				
				// end
				if (element != null)
					writer.end();
				found = true;
				break;
			}
			// you want to check to make sure
			else if (	session.getRoomName().equals(getRoom().getRoomName())
						&& getDate().compareTo(session.getDateTimeBegin()) >= 0 
						&& getDate().compareTo(session.getDateTimeEnd()) <= 0 ) {
				// do nothing, the row span has been expanded
				found = true;				
				break;
			}
		}
		
		// if it is not found, display the generic tag
		if (!found) {
			if (element != null) {
				writer.begin(element);
				writer.end();
			}
		}
    }
	
	/**
	 * This will determine the row span based on the incrememnt size.
	 * 
	 * @return
	 */
	private int getRowSpan(SessionInfo session) {
		int i = 0;
		Calendar cal = Calendar.getInstance();
		cal.setTime(getDate());
		Date endDateTime = session.getDateTimeEnd();
		
		// run the loop till the current time is less than the next time
		// TODO Should this be less than or less than and equal to?
		for ( ; cal.getTime().compareTo(endDateTime) <= 0 ; cal.add(Calendar.MINUTE, getIncrement())) {
			cal.add(Calendar.MINUTE, getIncrement());
			i++;
		}
		return i;
	}
}
