/*
 * @(#)QuestionnaireService.java	Oct 9, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.service;

import java.util.List;

import com.integrallis.techconf.dto.QuestionChoiceInfo;
import com.integrallis.techconf.dto.QuestionTypeInfo;
import com.integrallis.techconf.dto.QuestionnaireAnswers;
import com.integrallis.techconf.dto.QuestionnaireInfo;

/**
 * @author Brian Sam-Bodden
 */
public interface QuestionnaireService {
	List<QuestionChoiceInfo> getQuestionChoices();
	List<QuestionTypeInfo> getQuestionTypes();
    QuestionnaireInfo getQuestionnaire(String name);
    void submitQuestionnaireAnswers(QuestionnaireAnswers answers);
}
