/*
 * @(#)UserServiceImpl.java	Oct 29, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.dynadto.Builder;
import org.dynadto.BuilderFactory;

import com.integrallis.techconf.dao.UserDAO;
import com.integrallis.techconf.domain.Address;
import com.integrallis.techconf.domain.Attendee;
import com.integrallis.techconf.domain.Book;
import com.integrallis.techconf.domain.Presenter;
import com.integrallis.techconf.domain.User;
import com.integrallis.techconf.dto.AttendeeInfo;
import com.integrallis.techconf.dto.BookInfo;
import com.integrallis.techconf.dto.PresenterInfo;
import com.integrallis.techconf.dto.UserSummary;
import com.integrallis.techconf.service.MailService;
import com.integrallis.techconf.service.UserService;
import com.integrallis.techconf.service.exception.InvalidPasswordException;
import com.integrallis.techconf.service.exception.NoSuchUserException;
import com.integrallis.techconf.service.exception.ServiceException;

/**
 * @author Brian Sam-Bodden
 */
public class UserServiceImpl implements UserService {
	
	// DAOs
	protected UserDAO userDAO;
	
	// DynaDTO BuilderFactory
	private BuilderFactory builderFactory;	
	
	// DynaDTO Builders
	protected Builder presenterInfoBuilder;
	protected Builder attendeeInfoBuilder;
	protected Builder userSummaryBuilder;	
	protected Builder bookInfoBuilder;
	
	// Services
	protected MailService mailService;
	
	public void initialization() {
		// constructs the DynaDTO builders
		presenterInfoBuilder = builderFactory.getBuilder(PresenterInfo.class);
		attendeeInfoBuilder = builderFactory.getBuilder(AttendeeInfo.class);
		userSummaryBuilder = builderFactory.getBuilder(UserSummary.class);
		bookInfoBuilder = builderFactory.getBuilder(BookInfo.class);
	}

	public PresenterInfo getPresenter(int presenterId) {
		Presenter presenter = userDAO.getPresenter(presenterId);
		return (PresenterInfo) presenterInfoBuilder.build(presenter);
	}

	public AttendeeInfo getAttendee(int attendeeId) {
		Attendee attendee = userDAO.getAttendee(attendeeId);
		return (AttendeeInfo) attendeeInfoBuilder.build(attendee);
	}	
	
	public PresenterInfo registerPresenter(PresenterInfo presenterInfo) {
		Presenter presenter = new Presenter();
		// populate the presenter object - dynadto should do this!
		presenter.setBio(presenterInfo.getBio());
		presenter.setCompany(presenterInfo.getCompany());
		presenter.setCompanyURL(presenterInfo.getCompanyURL());
		presenter.setEmail(presenterInfo.getEmail());
		presenter.setFax(presenterInfo.getFax());
		presenter.setFirstName(presenterInfo.getFirstName());
		presenter.setHomePhone(presenterInfo.getHomePhone());
		presenter.setLastName(presenterInfo.getLastName());
		presenter.setPassword(presenterInfo.getPassword());
		presenter.setWorkPhone(presenterInfo.getWorkPhone());
		
		presenter = userDAO.savePresenter(presenter);		
		return (PresenterInfo) presenterInfoBuilder.build(presenter);
	}

	public AttendeeInfo registerAttendee(AttendeeInfo attendeeInfo) {
		Attendee attendee = new Attendee();
		Address address = new Address();
		
        // populate the presenter object - dynadto should do this!
		attendee.setEmail(attendeeInfo.getEmail());
		attendee.setFax(attendeeInfo.getFax());
		attendee.setFirstName(attendeeInfo.getFirstName());
		attendee.setHomePhone(attendeeInfo.getHomePhone());
		attendee.setLastName(attendeeInfo.getLastName());
		attendee.setPassword(attendeeInfo.getPassword());
		attendee.setWorkPhone(attendeeInfo.getWorkPhone());		
		
		// Set Address	
		address.setStreetAddress(attendeeInfo.getAddress().getStreetAddress());
		address.setState(attendeeInfo.getAddress().getState());
		address.setCity(attendeeInfo.getAddress().getCity());
		address.setZipCode(attendeeInfo.getAddress().getZipCode());		
		address.setAptNumber(attendeeInfo.getAddress().getAptNumber());
		attendee.setAddress(address);

		attendee = userDAO.saveAttendee(attendee);		
		return (AttendeeInfo) attendeeInfoBuilder.build(attendee);
	}

	public UserSummary login(String email, String password) {
		UserSummary userSummary = null;
		// find user by email
		User user = userDAO.getUserByEmail(email);
		if (user != null) {
			if (user.getPassword().equals(password)) {
				userSummary = (UserSummary) userSummaryBuilder.build(user);
			}
			else {
				//TODO add more info to this exception
				throw new InvalidPasswordException("invalid password for user " + email);
			}
		}
		else {
			// no such user exception ?
			throw new NoSuchUserException("no such user " + email);
		}
		return userSummary;
	}

	public void sendPassword(String email) {
		//User user = userDAO.getUserByEmail(email);
//		if (user != null) {			
//			try {
//				manager.connect(); // internally create a JMS connection
//			    mailService.sendEmail(email, 
//			    		              "noreply@techconf.org", 
//			    		              "Your Techconf Password", 
//			    		              "Your Password is " + user.getPassword());
//			} catch (JMSException jmse) {
//				//TODO log the problem - throw app specific exception		
//			} finally {
//			    try {
//                    // clean up the JMS connection
//					manager.close();
//				} catch (JMSException e) {
//                    // do nothing
//				} 
//			}			
//		}
//		else {
//			throw new NoSuchUserException("There is no user with email " + email);
//		}
	}

	/**
	 * @return Returns the builderFactory.
	 */
	public BuilderFactory getBuilderFactory() {
		return builderFactory;
	}

	/**
	 * @param builderFactory The builderFactory to set.
	 */
	public void setBuilderFactory(BuilderFactory builderFactory) {
		this.builderFactory = builderFactory;
	}

	/**
	 * @param userDAO The userDAO to set.
	 */
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/**
	 * @param mailService The mailService to set.
	 */
	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public void submitBook(BookInfo bookInfo) throws ServiceException {
		Presenter presenter = userDAO.getPresenter(bookInfo.getUserId());
		if (presenter != null) {
			Book book = new Book();
			book.setAuthors(bookInfo.getAuthors());
			book.setDescription(bookInfo.getDescription());
			book.setInBookstore(bookInfo.getInBookstore());
			book.setPurchaseUrl(bookInfo.getPurchaseUrl());
			book.setTitle(bookInfo.getTitle());
			presenter.addToBooks(book);
			userDAO.savePresenter(presenter);
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<BookInfo> getBooksForPresenter(int presenterId) {
		List<Book> entities = new ArrayList<Book>();
		Presenter presenter = userDAO.getPresenter(presenterId);
		if (presenter != null) {
			entities.addAll(presenter.getBooks());;
		}
		return bookInfoBuilder.buildList(entities);		
	}

}
