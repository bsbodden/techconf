/*
 * @(#)QuestionnaireDAOBean.java	Oct 9, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.ejb.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;

import com.integrallis.techconf.dao.QuestionnaireDAO;
import com.integrallis.techconf.domain.Answer;
import com.integrallis.techconf.domain.Question;
import com.integrallis.techconf.domain.QuestionChoice;
import com.integrallis.techconf.domain.QuestionType;
import com.integrallis.techconf.domain.Questionnaire;

/**
 * @author Brian Sam-Bodden
 */
@Stateless
public class QuestionnaireDAOBean extends BaseAbstractDAO implements
		QuestionnaireDAO {

	/**
	 * @param sessionFactory
	 */
	public QuestionnaireDAOBean() {
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.dao.QuestionnaireDAO#getQuestionTypes()
	 */
	@SuppressWarnings("unchecked")
	public List<QuestionType> getQuestionTypes() {
		return findAll(QuestionType.class);
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.dao.QuestionnaireDAO#getQuestionChoices()
	 */
	@SuppressWarnings("unchecked")
	public List<QuestionChoice> getQuestionChoices() {
		return findAll(QuestionChoice.class);
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.dao.QuestionnaireDAO#getQuestionnaireByName(java.lang.String)
	 */
	public Questionnaire getQuestionnaireByName(String name) {
		return (Questionnaire) findUniqueFiltered(Questionnaire.class, Questionnaire.PROP_NAME, name);
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.dao.QuestionnaireDAO#getQuestionnaireById(int)
	 */
	public Questionnaire getQuestionnaireById(int questionnaireId) {
		return (Questionnaire) getEntityById(Questionnaire.class, questionnaireId);
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.dao.QuestionnaireDAO#getQuestionChoices(int)
	 */
	public List<QuestionChoice> getQuestionChoices(int questionId) {
		List<QuestionChoice> result = new ArrayList<QuestionChoice>();
		Question question = getQuestionById(questionId);
		if (null != question) {
			result.addAll(question.getQuestionChoices());
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.dao.QuestionnaireDAO#save(com.integrallis.techconf.domain.Answer)
	 */
	public Answer save(Answer answer) {
		saveEntity(answer);
		return answer;
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.dao.QuestionnaireDAO#update(com.integrallis.techconf.domain.Answer)
	 */
	public Answer update(Answer answer) {
		updateEntity(answer);
		return answer;
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.dao.QuestionnaireDAO#delete(com.integrallis.techconf.domain.Answer)
	 */
	public void delete(Answer answer) {
		deleteEntity(answer);
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.dao.QuestionnaireDAO#delete(int)
	 */
	public void delete(int answerId) {
		deleteEntityById(Answer.class, answerId);
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.dao.QuestionnaireDAO#getAnswers(int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<Answer> getAnswers(int questionnaireId, int userId) {
		return createCriteria(Answer.class)
		    .add(Expression.eq("Questionnaire.Id", questionnaireId))
		    .add(Expression.eq("User.Id", userId))
		    .list();
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.dao.QuestionnaireDAO#getAnswer(int, int, int)
	 */
	public Answer getAnswer(int questionnaireId, int questionId, int userId) {
		return (Answer) createCriteria(Answer.class)
	        .add(Expression.eq("Questionnaire.Id", questionnaireId))
	        .add(Expression.eq("User.Id", userId))
	        .add(Expression.eq("Question.Id", questionId))
	        .uniqueResult();
	}

	public Question getQuestionById(int questionId) {		
		return (Question) getEntityById(Question.class, questionId);
	}

	public void saveAnswers(List<Answer> answers) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();		
		try {
			int i = 0;
			for (Answer answer : answers) {
				session.save(answer);
				i++;
				if (i % 25 == 0) { // manage session cache size - flush every 25 saves 
					session.flush();
					session.clear();
				}
			}
			tx.commit();			
		} catch (HibernateException he) {
			tx.rollback();
			throw he;
		} 	
	}

}
