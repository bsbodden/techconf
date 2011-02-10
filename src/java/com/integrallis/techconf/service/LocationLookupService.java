/*
 * @(#)LocationLookupService.java	Dec 25, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.service;

import java.util.List;

import com.integrallis.techconf.dto.Location;


/**
 * @author Brian Sam-Bodden
 */
public interface LocationLookupService {
	List<Location> searchLocations(String zipCode);
	Location getLocationByZipCode(String zipCode);
}
