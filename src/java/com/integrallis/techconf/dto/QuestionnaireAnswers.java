/*
 * @(#)QuestionnaireAnswers.java	Oct 9, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dto;

import java.util.List;

import org.dynadto.DTO;

/**
 * @author Brian Sam-Bodden
 */
public interface QuestionnaireAnswers extends DTO {
    Integer getUserId();
    void setUserId(Integer userId);
    
    Integer getQuestionnaireId();
    void setQuestionnaireId(Integer questionnaireId);
    
    List<AnswerInfo> getAnswers();
    void setAnswers(List<AnswerInfo> answers); 
}
