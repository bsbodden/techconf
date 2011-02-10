/*
 * @(#)LocationLookupServiceBean.java	Dec 25, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.ejb;

import java.util.List;

import javax.annotation.EJB;
import javax.annotation.Resource;
import javax.ejb.PostConstruct;
import javax.ejb.Stateless;

import org.dynadto.Builder;
import org.dynadto.BuilderFactory;

import com.integrallis.techconf.dao.ZipcodeDAO;
import com.integrallis.techconf.domain.Zipcode;
import com.integrallis.techconf.dto.Location;
import com.integrallis.techconf.service.LocationLookupService;

/**
 * @author Brian Sam-Bodden
 */
@Stateless
public class LocationLookupServiceBean implements LocationLookupService {
	
	@Resource(name = "java:/dynadto/BuilderFactory")
	protected BuilderFactory builderFactory;	
	
	// DAOs
	@EJB protected ZipcodeDAO zipcodeDAO;	
	
	// DynaDTO Builders
	protected Builder locationBuilder;	
	
	@PostConstruct
	public void initialization() {	
		locationBuilder = builderFactory.getBuilder(Location.class);
	}

	/* (non-Javadoc)
	 * @see com.integrallis.techconf.service.LocationLookupService#getLocations(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Location> searchLocations(String zipCode) {
		List<Zipcode> entities = zipcodeDAO.find(zipCode);
		return locationBuilder.buildList(entities);
	}

	public Location getLocationByZipCode(String zipCode) {
		Integer id = Integer.parseInt(zipCode);
		Zipcode zip = zipcodeDAO.getById(id);

		Location location = null;
		if (zip != null) {
			location = (Location) locationBuilder.build(zip);
		}
		return location;		
	}

	/**
	 * @param zipcodeDAO The zipcodeDAO to set.
	 */
	public void setZipcodeDAO(ZipcodeDAO zipcodeDAO) {
		this.zipcodeDAO = zipcodeDAO;
	}

	/**
	 * @param builderFactory The builderFactory to set.
	 */
	public void setBuilderFactory(BuilderFactory builderFactory) {
		this.builderFactory = builderFactory;
	}

}
