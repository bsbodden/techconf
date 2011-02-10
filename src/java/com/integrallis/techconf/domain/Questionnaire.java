package com.integrallis.techconf.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Questionnaire  implements Serializable {

	private static final long serialVersionUID = 4923645042664791895L;
	
	public static String PROP_ID = "Id";
	public static String PROP_NAME = "Name";

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Integer id;
	
	private String name;
	
	// many-to-many
	private Set<Question> questions;

	// constructors
	public Questionnaire () {
	}

	public Questionnaire(Integer questionnaireId) {
		setId(questionnaireId);
	}

	public Integer getId () {
		return id;
	}

	public void setId (Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof Questionnaire)) return false;
		else {
			Questionnaire mObj = (Questionnaire) obj;
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
	
	public void addQuestion(Question question) {
		if (null == questions) questions = new HashSet<Question>();
		questions.add(question);
	}

	/**
	 * @return Returns the questions.
	 */
	public Set<Question> getQuestions() {
		return questions;
	}

	/**
	 * @param questions The questions to set.
	 */
	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
}