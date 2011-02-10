/*
 * @(#)ZipcodeDAO.java	Dec 28, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.dao;

import java.util.List;

import com.integrallis.techconf.domain.Zipcode;

public interface ZipcodeDAO {
    List<Zipcode> find(String zipCode);
    Zipcode getById(Integer id);
}
