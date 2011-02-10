/*
 * @(#)DbUnitUtils.java	Dec 4, 2005
 *
 * Copyright (c) 2005 Integrallis Software, LLC. All rights reserved.
 */
package com.integrallis.techconf.test.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;

public class DbUnitUtils {
    /**
     * Creates a DbUnit database connection.
     * @return test database connection
     */
    public static IDatabaseConnection createConnection()
        throws Exception {
    	Properties properties = new Properties();
        properties.load(new FileInputStream(Paths.BASEDIR + "/build.properties"));
        
        String driver = properties.getProperty("test.db.driver");
        String url = properties.getProperty("test.db.url");
        String user = properties.getProperty("test.db.username");
        String password = properties.getProperty("test.db.password");
        
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);

        return new DatabaseConnection(connection);
    }

    /**
     * Creates a DbUnit dataset based on a xml file.
     * @param xmlFile path to an xml file containing the dataset
     * @return requested dataset
     */
    public static IDataSet createDataSet(String file) throws Exception {
        return new XmlDataSet(new FileInputStream(Paths.BASEDIR + "/setup/data/dbunit/" + file));
    }
}
