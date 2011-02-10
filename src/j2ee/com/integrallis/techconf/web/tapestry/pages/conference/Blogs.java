package com.integrallis.techconf.web.tapestry.pages.conference;

import java.util.List;

import org.apache.tapestry.event.PageEvent;

import com.integrallis.techconf.dto.BlogEntry;
import com.integrallis.techconf.web.tapestry.pages.common.ActiveConferencePage;

public abstract class Blogs extends ActiveConferencePage {
	
	public void pageBeginRender(PageEvent event) {
		super.pageBeginRender(event);
		
		// now grab all the blogs.
		List<BlogEntry> blogs = getConferenceService().getBlogEntries(getConferenceId());
		
		setBlogEntries(blogs);
	}
	
	public abstract void setBlogEntries(List<BlogEntry> b);
	public abstract List<BlogEntry> getBlogEntries();

}
