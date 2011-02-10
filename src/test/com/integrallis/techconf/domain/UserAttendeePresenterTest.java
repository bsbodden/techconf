/*
 * @(#)UserAttendeePresenterTest.java	Sep 3, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.domain;

import java.util.Iterator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UserAttendeePresenterTest extends BaseHibernateTestCase {
	private final static String[] APT_NUMBERS ={"N/A", "N/A", "3-A"};
	private final static String[] CITIES ={"Westerville", "Columbus", "New York"};
	private final static String[] STATES = {"OH", "GA", "NY"};
	private final static String[] STREET_ADDRESSES = {"204 Bluestone Court", "123 Main Street", "129 West 81st Street"};
	private final static String[] ZIP_CODES = {"43081", "32456", "10010"};
	
	private final static String[] FIRST_NAMES = {"John", "John", "Jerry"};
	private final static String[] LAST_NAMES = {"Doe", "Smith", "Seinfeld"};
	private final static String[] EMAILS = {"jdoe@", "jsmith@acmeco.com", "jerry@yadayada.com"};
	private final static String[] PASSWORDS = {"password", "smithy", "cosmo"};	
	private final static String[] HOME_PHONES = {"614-716-2534", "234-255-5674", "356-764-8967"};
	private final static String[] WORK_PHONES = {"614-765-4321", "345-356-7867", "234-564-7801"};
	private final static String[] FAXES = {"614-123-4567", "614-567-4380", "675-783-5424"};	
	
	private final static boolean ALUMNUS = true; 
	
	private final static String BIO = "He works hard, very hard";
	private final static String COMPANY = "Acme Co.";
	private final static String COMPANY_URL = "www.acmeco.com";
	private final static String BLOG_URL = "www.acmeco.com/blogs/jdoe";
	
	@Test(groups = {"persistence"})
	public void testCreateUser() { 	    
		User user = new User();			
		setUserFields(user, 0);
		
  	    // save the user to the database
  	    User savedUser = (User) persist(user);
  	    
  	    // get the id of the newly created entry
  	    Integer id = savedUser.getId();
  	    
  	    // did the db assign an id?
  	    Assert.assertNotNull(id);
  	    
  	    // load the user from the database using the id
  	    User retrievedUser = (User) getByPk(User.class, id);
  	    
  	    // did it get saved?
  	    Assert.assertNotNull(retrievedUser);
  	    
  	    // ok, compare the values
  	    assertUserContents(retrievedUser, 0);
  	    
  	    Address savedAddress = retrievedUser.getAddress();
  	    
  	    assertAddressContents(savedAddress, 0);
  	    
  	    Integer savedAddressId = savedAddress.getId();
  	    
  	    //clean up
  	    delete(retrievedUser);
  	    
  	    // test cascading delete
  	    // load the address from the database using the id
  	    Address retrievedAddress = (Address) getByPk(Address.class, savedAddressId);
  	    
  	    // did it get deleted?
  	    Assert.assertNull(retrievedAddress);  	    
    }
	
	@Test(groups = {"persistence"})
	public void testCreateAttendee() {
		Attendee attendee = new Attendee();	

		// user fields
		setUserFields(attendee, 1);
		
		attendee.setAlumnus(ALUMNUS);
		
  	    // save the user to the database
  	    Attendee savedAttendee = (Attendee) persist(attendee);
  	    
  	    // get the id of the newly created entry
  	    Integer id = savedAttendee.getId();
  	    
  	    // did the db assign an id?
  	    Assert.assertNotNull(id);
  	    
  	    // load the user from the database using the id
  	    Attendee retrievedAttendee = (Attendee) getByPk(Attendee.class, id);
  	    
  	    // did it get saved?
  	    Assert.assertNotNull(retrievedAttendee);
  	    
  	    // ok, compare the values
  	    assertUserContents(retrievedAttendee, 1);
  	    
  	    Assert.assertEquals(retrievedAttendee.isAlumnus().booleanValue(), ALUMNUS);
  	    
   	    //clean up
  	    delete(retrievedAttendee);    
    }	
	
	@Test(groups = {"persistence"})
	public void testCreatePresenter() {
		Presenter presenter = new Presenter();	

		// user fields
		setUserFields(presenter, 2);
		
		presenter.setBio(BIO);
		presenter.setCompany(COMPANY);
		presenter.setCompanyURL(COMPANY_URL);

		BlogLink blogLink = new BlogLink();
		blogLink.setBlogURL(BLOG_URL);
		
		presenter.setBlogLink(blogLink);
		
  	    // save the user to the database
  	    Presenter savedPresenter = (Presenter) persist(presenter);
  	    
  	    // get the id of the newly created entry
  	    Integer id = savedPresenter.getId();
  	    
  	    // did the db assign an id?
  	    Assert.assertNotNull(id);
  	    
  	    // load the user from the database using the id
  	    Presenter retrievedPresenter = (Presenter) getByPk(Presenter.class, id);
  	    
  	    // did it get saved?
  	    Assert.assertNotNull(retrievedPresenter);
  	    
  	    // ok, compare the values
  	    assertUserContents(retrievedPresenter, 2);
  	    
  	    Assert.assertEquals(retrievedPresenter.getBio(), BIO);
  	    Assert.assertEquals(retrievedPresenter.getCompany(), COMPANY);
  	    Assert.assertEquals(retrievedPresenter.getCompanyURL(), COMPANY_URL);
  	    
  	    BlogLink savedBlogLink = retrievedPresenter.getBlogLink();
  	    
  	    Assert.assertEquals(savedBlogLink.getBlogURL(), BLOG_URL);
  	    
  	    Integer savedBlogLinkId = savedBlogLink.getId();
  	    
  	    //clean up
  	    delete(retrievedPresenter);
  	    
  	    // test cascading delete
  	    // load the blog link from the database using the id
  	    BlogLink retrievedBlogLink = (BlogLink) getByPk(BlogLink.class, savedBlogLinkId);
  	    
  	    // did it get deleted?
  	    Assert.assertNull(retrievedBlogLink);  	    
    }	
	
	@Test(groups = {"persistence"})
	public void testPolymorphicQueries() {	
		User user = new User();			
		setUserFields(user, 0);		
  	    
		Attendee attendee = new Attendee();	
		setUserFields(attendee, 1);		
		attendee.setAlumnus(ALUMNUS);

		Presenter presenter = new Presenter();	
		setUserFields(presenter, 2);
		presenter.setBio(BIO);
		presenter.setCompany(COMPANY);
		presenter.setCompanyURL(COMPANY_URL);
		BlogLink blogLink = new BlogLink();
		blogLink.setBlogURL(BLOG_URL);		
		presenter.setBlogLink(blogLink);
		
  	    // save the entities to the database
  	    User savedUser = (User) persist(user);	
  	    Attendee savedAttendee = (Attendee) persist(attendee); 	
  	    Presenter savedPresenter = (Presenter) persist(presenter); 
  	    
  	    List users = findAll(User.class);
  	    
  	    Assert.assertEquals(users.size(), 3);
  	    
  	    for (Iterator i = users.iterator(); i.hasNext();) {
			User u = (User) i.next();
			if (u.getEmail().equals(EMAILS[0])) {
				Assert.assertFalse(tryToCastAsAttendee(u));
				Assert.assertFalse(tryToCastAsPresenter(u));
			}
			else if (u.getEmail().equals(EMAILS[1])) {
				Assert.assertTrue(tryToCastAsAttendee(u));
				Assert.assertFalse(tryToCastAsPresenter(u));
			}
			else if (u.getEmail().equals(EMAILS[2])) {
				Assert.assertFalse(tryToCastAsAttendee(u));
				Assert.assertTrue(tryToCastAsPresenter(u));
			}
		}
  	    
  	    List attendees = findAll(Attendee.class);
  	    Assert.assertEquals(attendees.size(), 1);
  	    
  	    List presenters = findAll(Presenter.class);
  	    Assert.assertEquals(presenters.size(), 1);  	    
  	    
  	    // clean up
  	    delete(savedUser);
  	    delete(savedAttendee);
  	    delete(savedPresenter);
	}
	
	//
	// private utility methods
	//
	
	private boolean tryToCastAsPresenter(Object o) {
	    boolean result = true;
		try {
			@SuppressWarnings("unused") 
			Presenter p = (Presenter)o;
		} catch (ClassCastException cce) {
			result = false;
		}
		return result;
	}
	
	private boolean tryToCastAsAttendee(Object o) {
	    boolean result = true;
		try {
			@SuppressWarnings("unused") 
			Attendee a = (Attendee)o;
		} catch (ClassCastException cce) {
			result = false;
		}
		return result;
	}	
	
	private Address createAddress(int index) {
    	// create and populate an address object
  	    Address address = new Address(); 
  	    address.setAptNumber(APT_NUMBERS[index]);
  	    address.setCity(CITIES[index]);
  	    address.setState(STATES[index]);
  	    address.setStreetAddress(STREET_ADDRESSES[index]);
  	    address.setZipCode(ZIP_CODES[index]);
  	    return address;
	}
	
	private void setUserFields(User user, int index) {
		user.setFirstName(FIRST_NAMES[index]);
		user.setLastName(LAST_NAMES[index]);
		user.setEmail(EMAILS[index]);
		user.setPassword(PASSWORDS[index]);
		user.setHomePhone(HOME_PHONES[index]);
		user.setWorkPhone(WORK_PHONES[index]);
		user.setFax(FAXES[index]);	
		
		Address address = createAddress(index);
		user.setAddress(address);
	}
	
	private void assertAddressContents(Address address, int index) {
  	    Assert.assertEquals(address.getAptNumber(), APT_NUMBERS[index]);
  	    Assert.assertEquals(address.getCity(), CITIES[index]);
  	    Assert.assertEquals(address.getState(), STATES[index]);
  	    Assert.assertEquals(address.getStreetAddress(), STREET_ADDRESSES[index]);
  	    Assert.assertEquals(address.getZipCode(), ZIP_CODES[index]);
	}
	
	private void assertUserContents(User user, int index) {
  	    Assert.assertEquals(user.getFirstName(), FIRST_NAMES[index]);
  	    Assert.assertEquals(user.getLastName(), LAST_NAMES[index]);
  	    Assert.assertEquals(user.getEmail(), EMAILS[index]);
  	    Assert.assertEquals(user.getPassword(), PASSWORDS[index]);
  	    Assert.assertEquals(user.getHomePhone(), HOME_PHONES[index]);
  	    Assert.assertEquals(user.getWorkPhone(), WORK_PHONES[index]);
  	    Assert.assertEquals(user.getFax(), FAXES[index]);		
	}

}
