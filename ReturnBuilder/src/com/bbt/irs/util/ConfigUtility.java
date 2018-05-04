/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.fxml;

/**
 *
 * @author hp
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * A utility class that reads/saves SMTP settings from/to a properties file.
 *
 * @author www.codejava.net
 *
 */
public class ConfigUtility {

    private static final String PATH = System.getProperty("user.home");
    private static File configFile = new File(PATH + "persistence.properties");
    private static Properties configProps;

    public static Properties loadProperties() throws IOException {

        Properties defaultProps = new Properties();
        defaultProps.setProperty("javax.persistence.jdbc.url", "jdbc:sqlserver://127.0.0.1;databaseName=RBSCT");
        defaultProps.setProperty("javax.persistence.jdbc.user", "irs");
        defaultProps.setProperty("javax.persistence.jdbc.password", "irs");
        defaultProps.setProperty("javax.persistence.jdbc.driver", "com.microsoft.sqlserver.jdbc.SQLServerDriver");

        configProps = new Properties(defaultProps);

        // loads properties from file
        if (configFile.exists()) {
            InputStream inputStream = new FileInputStream(configFile);
            configProps.load(inputStream);
            inputStream.close();
        }

        return configProps;
    }

    public void saveProperties(String host, String port, String user, String pass, String dbUrl, String dbuser, String dbpass, String dbDriver) throws IOException {
        configProps.setProperty("javax.persistence.jdbc.url", "jdbc:sqlserver://127.0.0.1;databaseName=RBSCT");
        configProps.setProperty("javax.persistence.jdbc.user", "irs");
        configProps.setProperty("javax.persistence.jdbc.password", "irs");
        configProps.setProperty("javax.persistence.jdbc.driver", "com.microsoft.sqlserver.jdbc.SQLServerDriver");


        OutputStream outputStream = new FileOutputStream(configFile);
        configProps.store(outputStream, "host setttings");
        outputStream.close();
    }
}
