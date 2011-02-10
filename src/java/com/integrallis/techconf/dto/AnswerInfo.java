/*
 * @(#)AnswerInfo.java	Oct 9, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dto;

import java.util.List;

import org.dynadto.DTO;

/**
 * @author Brian Sam-Bodden
 */
public interface AnswerInfo extends DTO {
	Integer getId();
	
	String getAnswer();
	void setAnswer(String answer);
	
	Integer getQuestionId();
	void setQuestionId(Integer questionId);
	
	List<Integer> getQuestionChoiceIds();
	void setQuestionChoiceIds(List<Integer> questionChoiceIds);
}
