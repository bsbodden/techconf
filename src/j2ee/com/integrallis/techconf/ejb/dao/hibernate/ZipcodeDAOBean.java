/*
 * @(#)ZipcodeDAOBean.java	Dec 28, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.ejb.dao.hibernate;

import java.util.List;

import javax.ejb.Stateless;

import com.integrallis.techconf.dao.ZipcodeDAO;
import com.integrallis.techconf.domain.Zipcode;

@Stateless
public class ZipcodeDAOBean extends BaseAbstractDAO implements ZipcodeDAO {

	@SuppressWarnings("unchecked")
	public List<Zipcode> find(String zipCode) {
		zipCode = zipCode + "%";
		String queryString = "select distinct z from Zipcode z where z.Zip like :zipCode";
		return createQuery(queryString)
		    .setString("zipCode", zipCode)
		    .list();
	}

	public Zipcode getById(Integer id) {
		return (Zipcode) getEntityById(Zipcode.class, id);
	}

}
