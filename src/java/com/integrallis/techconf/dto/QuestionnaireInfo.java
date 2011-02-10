/*
 * @(#)QuestionnaireInfo.java	Oct 9, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dto;

import java.util.List;

import org.dynadto.DTO;

/**
 * @author Brian Sam-Bodden
 */
public interface QuestionnaireInfo extends DTO {
	Integer getId();	
	String getName();
	List<QuestionInfo> getQuestions();
}
