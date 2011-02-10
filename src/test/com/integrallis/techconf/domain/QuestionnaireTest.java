/*
 * @(#)QuestionnaireTest.java	Oct 8, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.domain;

import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.Test;

/**
 * TODO finish this test
 * @author Brian Sam-Bodden
 */
public class QuestionnaireTest extends BaseHibernateTestCase {
	
	@Test(groups = {"persistence"})
    public void testCreateQuestionnaire() {
    	QuestionType radio = new QuestionType("radio");   	
    	QuestionType checkbox = new QuestionType("checkbox");   	
    	QuestionType text = new QuestionType("text");
    	
    	persist(radio);
    	persist(checkbox);
    	persist(text);
    	
    	QuestionChoice yes = new QuestionChoice("Yes");
    	QuestionChoice no = new QuestionChoice("No");
    	
    	persist(yes);
    	persist(no);
    	
    	Set<QuestionChoice> yesNo = new HashSet<QuestionChoice>();
    	yesNo.add(yes);
    	yesNo.add(no);
    	
    	QuestionChoice stronglyAgree = new QuestionChoice("Strongly Agree");
    	QuestionChoice agree = new QuestionChoice("Agree");
    	QuestionChoice neutral = new QuestionChoice("Neutral");
    	QuestionChoice disagree = new QuestionChoice("Disagree");
    	QuestionChoice stronglyDisagree = new QuestionChoice("Strongly Disagree");
    	QuestionChoice notApplicable = new QuestionChoice("N/A");
    	
    	persist(stronglyAgree);
    	persist(agree);
    	persist(neutral);
    	persist(disagree);
    	persist(stronglyDisagree);
    	persist(notApplicable);
    	
    	Set<QuestionChoice> rating = new HashSet<QuestionChoice>();
    	rating.add(stronglyAgree);
    	rating.add(agree);
    	rating.add(neutral);
    	rating.add(disagree);
    	rating.add(stronglyDisagree);
    	rating.add(notApplicable);
    	
    	Question q1 = new Question("The Speaker provided content that was useful", radio);
    	Question q2 = new Question("The technical level of the information met my expectations", radio);
    	Question q3 = new Question("The Speaker had relevant knowledge and expertise", radio);
    	Question q4 = new Question("The Speaker came prepared for the presentation", radio);
    	Question q5 = new Question("The Speaker provided a values Q & A period", radio);
    	Question q6 = new Question("The Speaker had real world orientation", radio);
    	
    	Question q7 = new Question("Would you recommend this speaker?", radio);
    	
    	Question q8 = new Question("Additional Comments", text);
    	
    	q1.setQuestionChoices(rating);
    	q2.setQuestionChoices(rating);
    	q3.setQuestionChoices(rating);
    	q4.setQuestionChoices(rating);
    	q5.setQuestionChoices(rating);
    	q6.setQuestionChoices(rating);    	
    	q7.setQuestionChoices(yesNo);
    	
    	persist(q1);
    	persist(q2);
    	persist(q3);
    	persist(q4);
    	persist(q5);
    	persist(q6);
    	persist(q7);
    	persist(q8);
    	
    	Questionnaire questionnaire = new Questionnaire();
    	questionnaire.setName("Session Speaker Evaluation");
    	questionnaire.addQuestion(q1);
    	questionnaire.addQuestion(q2);
    	questionnaire.addQuestion(q3);
    	questionnaire.addQuestion(q4);
    	questionnaire.addQuestion(q5);
    	questionnaire.addQuestion(q6);
    	questionnaire.addQuestion(q7);
    	
    	persist(questionnaire);
    	
    	User user = new User();
		user.setFirstName("Joe");
		user.setLastName("Attendee");
		user.setEmail("joe@gmail.com");
		user.setPassword("joejoe");
		
		Address address = new Address(); 
  	    address.setAptNumber("123");
  	    address.setCity("Columbus");
  	    address.setState("OH");
  	    address.setStreetAddress("6000 SomeParkWay");
  	    address.setZipCode("43809");	
  	    
  	    user.setAddress(address);
		
		persist(user);
    	
    	Answer a1 = new Answer();
    	a1.setQuestion(q1);
    	a1.setQuestionnaire(questionnaire);
    	a1.setUser(user);
    	a1.addQuestionChoice(stronglyAgree);
    	
    	persist(a1);
    	
    }

}
