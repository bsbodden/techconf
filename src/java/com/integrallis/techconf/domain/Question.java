package com.integrallis.techconf.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Question  implements Serializable {

	private static final long serialVersionUID = -5127475964843213073L;
	
	public static String PROP_QUESTION_TYPE = "QuestionType";
	public static String PROP_TEXT = "Text";
	public static String PROP_ID = "Id";

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Integer id;

	// fields
	private String text;

	// many to one
	private QuestionType questionType;

	// collections
	private Set<QuestionChoice> questionChoices;

	// constructors
	public Question () {
	}
	
	public Question(Integer id) {
		setId(id);
	}	
	
	public Question (String text, QuestionType type) {
		setText(text);
		setQuestionType(type);
	}

	public Integer getId () {
		return id;
	}

	public void setId (Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}

	public String getText () {
		return text;
	}

	public void setText (String text) {
		this.text = text;
	}

	public QuestionType getQuestionType () {
		return questionType;
	}

	public void setQuestionType (QuestionType questionType) {
		this.questionType = questionType;
	}

	public Set<QuestionChoice> getQuestionChoices () {
		return questionChoices;
	}

	public void setQuestionChoices (Set<QuestionChoice> questionChoices) {
		this.questionChoices = questionChoices;
	}
	
	@SuppressWarnings("unchecked")
	public void addToQuestionChoices (QuestionChoice questionChoice) {
		if (null == this.questionChoices) this.questionChoices = new HashSet();
		this.questionChoices.add(questionChoice);
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof Question)) return false;
		else {
			Question mObj = (Question) obj;
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
}