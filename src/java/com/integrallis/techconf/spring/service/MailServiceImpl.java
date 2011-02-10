/*
 * @(#)MailServiceImpl.java	Oct 29, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.spring.service;

import java.util.List;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.integrallis.techconf.service.MailService;
import com.integrallis.techconf.service.exception.ServiceException;

/**
 * @author Brian Sam-Bodden
 */
public class MailServiceImpl implements MailService {
	
	private MailSender mailSender;

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.service.MailService#sendEmail(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void sendEmail(String to, String from, String subject, String text)
			throws ServiceException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom(from);
        message.setSubject(subject);
        message.setText(text);
        try{
            mailSender.send(message);
        }
        catch(MailException mex) {
        	throw new ServiceException("Problem sending email", mex);
        }
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.service.MailService#sendEmail(java.util.List, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void sendEmail(List<String> recipients, String from, String subject,
			String text) throws ServiceException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipients.toArray(new String[0]));
        message.setFrom(from);
        message.setSubject(subject);
        message.setText(text);
        try{
            mailSender.send(message);
        }
        catch(MailException mex) {
        	throw new ServiceException("Problem sending email", mex);
        }
	}

	/**
	 * @param mailSender The mailSender to set.
	 */
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

}
