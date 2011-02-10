/*
 * @(#)TechConfController.java	Nov 2, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.spring.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class TechConfController implements Controller {
	
    /** Logger for this class and subclasses */
    protected final Log log = LogFactory.getLog(getClass());	

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info("Returning simple hello.jsp view");
		return new ModelAndView("hello.jsp");
	}

}
