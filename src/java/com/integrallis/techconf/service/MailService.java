/*
 * @(#)MailService.java	Sep 30, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.service;

import java.util.List;

import org.jboss.annotation.ejb.Producer;

import com.integrallis.techconf.service.exception.ServiceException;

@Producer
public interface MailService {
	void sendEmail(String to, String from, String subject, String text) throws ServiceException;
	void sendEmail(List<String> recipients, String from, String subject, String text) throws ServiceException;	
}
