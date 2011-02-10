/*
 * @(#)QuestionInfo.java	Oct 9, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dto;

import java.util.List;

import org.dynadto.DTO;

/**
 * @author Brian Sam-Bodden
 */
public interface QuestionInfo extends DTO {
	Integer getId();
	String getText();
	Integer getQuestionTypeId();
	List<Integer> getQuestionChoiceIds();
}
