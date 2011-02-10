/*
 * @(#)LocationRenderer.java	Dec 29, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.web.tapestry.common;

import java.util.Iterator;

import net.sf.tacos.ajax.components.ListItemRenderer;

import org.apache.tapestry.IMarkupWriter;
import org.apache.tapestry.IRequestCycle;

import com.integrallis.techconf.dto.Location;

/**
 * @author Brian Sam-Bodden
 */
public class LocationRenderer implements ListItemRenderer {

	/* (non-Javadoc)
	 * @see net.sf.tacos.ajax.components.ListItemRenderer#renderList(org.apache.tapestry.IMarkupWriter, org.apache.tapestry.IRequestCycle, java.util.Iterator)
	 */
	public void renderList(IMarkupWriter writer, IRequestCycle cycle, Iterator values) {
        if (cycle.isRewinding())
            return;
        
        writer.begin("ul");
        writer.attribute("class", "locations");
        
        while (values.hasNext()) {
            Location value = (Location)values.next();
            if (value == null)
                continue;
            
            writer.begin("li");
            writer.attribute("class", "location");
            writer.begin("div");
            writer.attribute("class", "zip");
            writer.print(value.getZip());
            writer.end("div");
            writer.begin("div");
            writer.attribute("class", "description");
            writer.begin("span");
            writer.attribute("class", "informal");
            writer.print(value.getCity() + ", " + value.getState());
            writer.end("span");
            writer.end("div");            
            writer.end("li");
            
        }
        
        writer.end();
	}
}
