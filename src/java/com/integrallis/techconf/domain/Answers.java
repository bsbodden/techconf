/*
 * @(#)Answers.java	Oct 9, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author Brian Sam-Bodden
 */
public class Answers implements Serializable {
	
	private List<Answer> answers;
	private Integer userId;
	private Integer questionnaireId;
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3100695812522148574L;

	/**
	 * 
	 */
	public Answers(Integer userId, Integer questionnaireId) {
		setUserId(userId);
		setQuestionnaireId(questionnaireId);
	}

	/**
	 * @return Returns the answers.
	 */
	public List<Answer> getAnswers() {
		return answers;
	}

	/**
	 * @param answers The answers to set.
	 */
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
    public Integer getUserId() {
    	return userId;
    }
    
    public void setUserId(Integer userId) {
    	this.userId = userId;
    }
    
    public Integer getQuestionnaireId() {
    	return questionnaireId;
    }
    
    public void setQuestionnaireId(Integer questionnaireId) {
    	this.questionnaireId = questionnaireId;
    }

}
