/*
 * @(#)MailServiceBean.java	Sep 30, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.ejb;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.jboss.annotation.ejb.Consumer;

import com.integrallis.techconf.service.MailService;
import com.integrallis.techconf.service.exception.ServiceException;

@Consumer(activateConfig =
{
  @ActivationConfigProperty(propertyName="destinationType",
    propertyValue="javax.jms.Queue"),
  @ActivationConfigProperty(propertyName="destination",
    propertyValue="queue/techconfmailqueue")
})
public class MailServiceBean implements MailService {
	
	@Resource(name = "java:/Mail")
	protected Session session;

	public void sendEmail(String to, String from, String subject, String text) {
		try {
			if (session != null) {
				Message message = new MimeMessage(session);
				message.setContent(text, "text/plain");
				message.setFrom(new InternetAddress(from));
				message.setRecipients(RecipientType.TO, InternetAddress.parse(to, false));
				message.setSubject(subject);
				message.setText(text);
				Transport.send(message);
			}			
		} catch (MessagingException me) {
			throw new ServiceException("could not send email to " + to, me);
		}

	}

	public void sendEmail(List<String> recipients, String from, String subject, String text) throws ServiceException {
		try {
			if (session != null) {
				Message message = new MimeMessage(session);
				message.setContent(text, "text/plain");
				message.setFrom(new InternetAddress(from));
				
				InternetAddress[] recipientAddresses = new InternetAddress[recipients.size()];
                for (int i = 0; i < recipients.size(); i++) {
	                recipientAddresses[i] = new InternetAddress(recipients.get(i));
                }
				message.setRecipients(RecipientType.TO, recipientAddresses);
				message.setSubject(subject);
				message.setText(text);
				Transport.send(message);
			}			
		} catch (MessagingException me) {
			Iterator i = recipients.iterator();
			StringBuffer sb = new StringBuffer();
			while (i.hasNext()) {
				sb.append((String) i.next());
				if (i.hasNext()) {
					sb.append(", ");
				}
			}
			throw new ServiceException("could not send email to " + sb.toString(), me);
		}
		
	}
}
