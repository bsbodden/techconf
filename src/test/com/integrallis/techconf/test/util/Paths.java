/*
 * @(#)Paths.java	Oct 8, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.test.util;

public class Paths {
	
	public static String BASEDIR;
	
	static {
		BASEDIR = System.getProperty("ant.basedir", ".");
	}

	private Paths() {
	}
}
