/*
 * @(#)QuestionnaireDAO.java	Oct 9, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dao;

import java.util.List;

import com.integrallis.techconf.domain.Answer;
import com.integrallis.techconf.domain.Question;
import com.integrallis.techconf.domain.QuestionChoice;
import com.integrallis.techconf.domain.QuestionType;
import com.integrallis.techconf.domain.Questionnaire;

/**
 * @author Brian Sam-Bodden
 */
public interface QuestionnaireDAO {
    List<QuestionType> getQuestionTypes();
    List<QuestionChoice> getQuestionChoices();    
    Questionnaire getQuestionnaireByName(String name);
    Questionnaire getQuestionnaireById(int questionnaireId);
    
    List<QuestionChoice> getQuestionChoices(int questionId);
    
    Question getQuestionById(int questionId);
    
    Answer save(Answer answer);
    Answer update(Answer answer);
    void delete(Answer answer);
    void delete(int answerId);
    
    List<Answer> getAnswers(int questionnaireId, int userId);
    Answer getAnswer(int questionnaireId, int questionId, int userId);  
    void saveAnswers(List<Answer> answers);
}
