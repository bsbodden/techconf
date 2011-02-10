/*
 * @(#)QuestionInfoTest.java	Oct 10, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dto;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.dynadto.Builder;
import org.dynadto.BuilderFactory;
import org.dynadto.ConfigurationLoader;
import org.dynadto.exception.ConfigurationException;
import org.testng.Assert;
import org.testng.annotations.Configuration;
import org.testng.annotations.Test;

import com.integrallis.techconf.domain.Question;
import com.integrallis.techconf.domain.QuestionChoice;
import com.integrallis.techconf.domain.QuestionType;
import com.integrallis.techconf.test.util.Paths;

/**
 * @author Brian Sam-Bodden
 */
public class QuestionInfoTest {

	@Configuration(beforeTestClass = true)
	protected void setUp() throws ConfigurationException {
		ConfigurationLoader.loadMapping(new File(Paths.BASEDIR + "/dd/dynadto/QuestionInfo.dto.xml"));	
	}
	
	@Test(groups = {"dto"})
	public void testQuestionInfoCreation() {
		QuestionType radio = new QuestionType("radio");
		radio.setId(1);
		
    	QuestionChoice stronglyAgree = new QuestionChoice("Strongly Agree");
    	stronglyAgree.setId(2);
    	QuestionChoice agree = new QuestionChoice("Agree");
    	agree.setId(3);
    	QuestionChoice neutral = new QuestionChoice("Neutral");
    	neutral.setId(4);
    	QuestionChoice disagree = new QuestionChoice("Disagree");
    	disagree.setId(5);
    	QuestionChoice stronglyDisagree = new QuestionChoice("Strongly Disagree");
    	stronglyDisagree.setId(6);
    	QuestionChoice notApplicable = new QuestionChoice("N/A");
    	notApplicable.setId(7);
    	
    	Set<QuestionChoice> rating = new HashSet<QuestionChoice>();
    	rating.add(stronglyAgree);
    	rating.add(agree);
    	rating.add(neutral);
    	rating.add(disagree);
    	rating.add(stronglyDisagree);
    	rating.add(notApplicable);    	
    	
		Question q1 = new Question("The Speaker provided content that was useful", radio);
		q1.setId(8);
		q1.setQuestionChoices(rating);
		
        Builder builder = BuilderFactory.getInstance().getBuilder(QuestionInfo.class);
        QuestionInfo questionInfo = (QuestionInfo) builder.build(q1);
        
        Assert.assertEquals(questionInfo.getId(), q1.getId());
        Assert.assertEquals(questionInfo.getQuestionTypeId(), q1.getQuestionType().getId());
        Assert.assertTrue(questionInfo.getQuestionChoiceIds().contains(stronglyAgree.getId()));
        Assert.assertTrue(questionInfo.getQuestionChoiceIds().contains(agree.getId()));
        Assert.assertTrue(questionInfo.getQuestionChoiceIds().contains(neutral.getId()));
        Assert.assertTrue(questionInfo.getQuestionChoiceIds().contains(disagree.getId()));
        Assert.assertTrue(questionInfo.getQuestionChoiceIds().contains(stronglyDisagree.getId()));
        Assert.assertTrue(questionInfo.getQuestionChoiceIds().contains(notApplicable.getId()));
        Assert.assertTrue(questionInfo.getQuestionChoiceIds().contains(stronglyAgree.getId()));
	}

}
