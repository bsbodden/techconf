/*
 * @(#)BookInfo.java	Nov 17, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dto;

import org.dynadto.DTO;

/**
 * @author Brian Sam-Bodden
 */
public interface BookInfo extends DTO {
	Integer getId();
	String getTitle();
	void setTitle(String title);
    Integer getUserId();
    void setUserId(Integer userId);
    String getDescription();
    Boolean getInBookstore();
    String getPurchaseUrl();
    void setDescription(String description);
    void setInBookstore(Boolean inBookstore);
    void setPurchaseUrl(String purchaseUrl);
    String getAuthors();
    void setAuthors(String authors);
 }
