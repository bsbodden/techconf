/*
 * @(#)QuestionnaireServiceBean.java	Oct 9, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.EJB;
import javax.annotation.Resource;
import javax.ejb.PostConstruct;
import javax.ejb.Stateless;

import org.dynadto.Builder;
import org.dynadto.BuilderFactory;

import com.integrallis.techconf.dao.QuestionnaireDAO;
import com.integrallis.techconf.domain.Answer;
import com.integrallis.techconf.domain.Question;
import com.integrallis.techconf.domain.QuestionChoice;
import com.integrallis.techconf.domain.QuestionType;
import com.integrallis.techconf.domain.Questionnaire;
import com.integrallis.techconf.domain.User;
import com.integrallis.techconf.dto.AnswerInfo;
import com.integrallis.techconf.dto.QuestionChoiceInfo;
import com.integrallis.techconf.dto.QuestionTypeInfo;
import com.integrallis.techconf.dto.QuestionnaireAnswers;
import com.integrallis.techconf.dto.QuestionnaireInfo;
import com.integrallis.techconf.service.QuestionnaireService;

/**
 * @author Brian Sam-Bodden
 */
@Stateless
public class QuestionnaireServiceBean implements QuestionnaireService {

	@Resource(name = "java:/dynadto/BuilderFactory")
	protected BuilderFactory builderFactory;
	
	@PostConstruct
	public void initialization() {
		// constructs the DynaDTO builders
		questionChoiceInfoBuilder = builderFactory.getBuilder(QuestionChoiceInfo.class);
		questionTypeInfoBuilder = builderFactory.getBuilder(QuestionTypeInfo.class);
		questionnaireInfoBuilder = builderFactory.getBuilder(QuestionnaireInfo.class);
		questionnaireAnswersBuilder = builderFactory.getBuilder(QuestionnaireAnswers.class);
	}
	
	// DAOs
	@EJB protected QuestionnaireDAO questionnaireDAO;
	
	// DynaDTO Builders
	protected Builder questionChoiceInfoBuilder;
	protected Builder questionTypeInfoBuilder;
	protected Builder questionnaireInfoBuilder;
	protected Builder questionnaireAnswersBuilder;

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.service.QuestionnaireService#getQuestionChoices()
	 */
	@SuppressWarnings("unchecked")
	public List<QuestionChoiceInfo> getQuestionChoices() {
		List<QuestionChoice> entities = questionnaireDAO.getQuestionChoices();
		return questionChoiceInfoBuilder.buildList(entities);
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.service.QuestionnaireService#getQuestionTypes()
	 */
	@SuppressWarnings("unchecked")
	public List<QuestionTypeInfo> getQuestionTypes() {
		List<QuestionType> entities = questionnaireDAO.getQuestionTypes();
		return questionTypeInfoBuilder.buildList(entities);
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.service.QuestionnaireService#getQuestionnaire(java.lang.String)
	 */
	public QuestionnaireInfo getQuestionnaire(String name) {
		Questionnaire questionnaire = questionnaireDAO.getQuestionnaireByName(name);
		return (QuestionnaireInfo) questionnaireInfoBuilder.build(questionnaire);
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.service.QuestionnaireService#submitQuestionnaireAnswers(com.integrallis.techconf.dto.QuestionnaireAnswers)
	 */
	public void submitQuestionnaireAnswers(QuestionnaireAnswers answers) {
		List<Answer> entities = new ArrayList<Answer>();
		for (AnswerInfo answerInfo : answers.getAnswers()) {
			Answer answer = new Answer();
			answer.setAnswer(answerInfo.getAnswer());
			answer.setQuestion(new Question(answerInfo.getQuestionId()));
			for (Integer choiceId : answerInfo.getQuestionChoiceIds()) {
			    answer.addQuestionChoice(new QuestionChoice(choiceId));
			}
			answer.setQuestionnaire(new Questionnaire(answers.getQuestionnaireId()));
			answer.setUser(new User(answers.getUserId()));
			
			entities.add(answer);
		}
		
		questionnaireDAO.saveAnswers(entities);
	}

}
