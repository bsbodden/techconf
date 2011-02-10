/*
 * @(#)UserService.java	Jun 29, 2005
 *
 * Copyright 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.service;

import java.util.List;

import com.integrallis.techconf.dto.AttendeeInfo;
import com.integrallis.techconf.dto.BookInfo;
import com.integrallis.techconf.dto.PresenterInfo;
import com.integrallis.techconf.dto.UserSummary;
import com.integrallis.techconf.service.exception.InvalidPasswordException;
import com.integrallis.techconf.service.exception.NoSuchUserException;
import com.integrallis.techconf.service.exception.ServiceException;

/**
 * @author Brian Sam-Bodden
 */
public interface UserService {
	//
	//
	//
	PresenterInfo getPresenter(int presenterId);
	AttendeeInfo getAttendee(int attendeeId);
	
	//
	// registration methods
	//
    PresenterInfo registerPresenter(PresenterInfo presenter);
    AttendeeInfo registerAttendee(AttendeeInfo attendee);
    
    //
    // login
    //
    UserSummary login(String email, String password) throws NoSuchUserException, InvalidPasswordException;
    
    //
    // other
    //
    void sendPassword(String email) throws ServiceException;
    
    //
    // books
    //
    void submitBook(BookInfo book) throws ServiceException;
    List<BookInfo> getBooksForPresenter(int presenterId);
}
