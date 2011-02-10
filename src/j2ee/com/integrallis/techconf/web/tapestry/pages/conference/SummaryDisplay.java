package com.integrallis.techconf.web.tapestry.pages.conference;

import org.apache.tapestry.html.BasePage;

public abstract class SummaryDisplay extends BasePage
{
	public abstract String getUserName();
    public abstract void setUserName(String userName);
}