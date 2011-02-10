/*
 * @(#)ConferenceSummaryDTO.java	Jun 30, 2005
 *
 * Copyright 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dto;

import java.util.Date;
import java.util.List;

import org.dynadto.DTO;

/**
 * @author Brian Sam-Bodden
 */
public interface ConferenceSummary extends DTO {
	int getConferenceId();
    List<TrackSummary> getTracks();
    List<NewsItem> getNews();
    List<PresenterSummary> getFeaturedSpeakers();
    String getConferenceTitle();
    void setConferenceTitle(String s);
    String getConferenceSubtitle();
    Date getStartDate();
    Date getEndDate();
    Date getAbstractSubmissionEndDate();
    Date getAbstractSubmissionStartDate();
    int getVenueId();
    String getVenueName();
    String getVenueAddressLine1();
    String getVenueAddressLine2();
    String getVenuePhone();
    
    // Necessary to have because they are not explicitly set
    // due to large size, could be set via other tiers.
    void setFeaturedSpeakers(List<PresenterSummary> t);
    void setNews(List<NewsItem> n);
}
