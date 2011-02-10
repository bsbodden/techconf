package com.integrallis.techconf.web.tapestry.common;

import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.tapestry.form.IPropertySelectionModel;

public class DatePropertySelectionModel implements IPropertySelectionModel
{
    private List<Date> dates;

    /**
     * Standard constructor.
     *
     * The options are retained (not copied).
     **/

    public DatePropertySelectionModel(List<Date> dates)
    {
        this.dates = dates;
    }

    public int getOptionCount()
    {
        return dates.size();
    }

    public Object getOption(int index)
    {
        return dates.get(index);
    }

    /**
     *  Labels match options.
     *  TODO - figure out how to format this?
     **/
    public String getLabel(int index)
    {
    	return DateFormat.getInstance().format(dates.get(index));    	
    }

    /**
     *  Values are indexes into the array of options.
     *
     **/
    public String getValue(int index)
    {
    	return String.valueOf(dates.get(index).getTime());
    }

    public Object translateValue(String value)
    {
        long index = Long.parseLong(value);

        Iterator it = dates.iterator();
        while (it.hasNext()) {
        	Date date = (Date)it.next();
        	if (date.getTime() == index) {
        		return date;
        	}
        }
        return null;
    }

}