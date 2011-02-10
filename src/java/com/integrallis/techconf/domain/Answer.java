package com.integrallis.techconf.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Answer implements Serializable {

	private static final long serialVersionUID = 323232264242295002L;
	
	public static String PROP_ANSWER = "Answer";
	public static String PROP_QUESTION = "Question";
	public static String PROP_USER = "User";
	public static String PROP_QUESTIONNAIRE = "Questionnaire";
	public static String PROP_ID = "Id";

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Integer id;

	// fields
	private String answer;

	// many to one
	private Questionnaire questionnaire;
	private User user;
	private Question question;
	
	//
	private Set<QuestionChoice> questionChoices;

	// constructors
	public Answer () {
	}

	public Answer (Integer id) {
		this.setId(id);
	}

	public Integer getId () {
		return id;
	}

	public void setId (Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}

	public String getAnswer () {
		return answer;
	}

	public void setAnswer (String answer) {
		this.answer = answer;
	}

	public Questionnaire getQuestionnaire () {
		return questionnaire;
	}

	public void setQuestionnaire (Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public User getUser () {
		return user;
	}

	public void setUser (User user) {
		this.user = user;
	}

	public Question getQuestion () {
		return question;
	}

	public void setQuestion (Question question) {
		this.question = question;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof Answer)) return false;
		else {
			Answer mObj = (Answer) obj;
			if (null == this.getId() || null == mObj.getId()) return false;
			else return (this.getId().equals(mObj.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return hashCode;
	}
	
	public void addQuestionChoice(QuestionChoice questionChoice) {
		if (null == questionChoices) questionChoices = new HashSet<QuestionChoice>();
		questionChoices.add(questionChoice);
	}

	/**
	 * @return Returns the questionChoices.
	 */
	public Set<QuestionChoice> getQuestionChoices() {
		return questionChoices;
	}

	/**
	 * @param questionChoices The questionChoices to set.
	 */
	public void setQuestionChoices(Set<QuestionChoice> questionChoices) {
		this.questionChoices = questionChoices;
	}
}