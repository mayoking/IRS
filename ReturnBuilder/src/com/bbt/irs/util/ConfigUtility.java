/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.util;

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


public class ConfigUtility {

    /**
     * @return the configProps
     */
    public static Properties getConfigProps() {
        return configProps;
    }

    private static final String PATH = System.getProperty("user.home");
    private static File configFile = new File(PATH + "/persistence.properties");
    private static Properties configProps;

    public static Properties loadProperties() throws IOException {
        System.out.println("configFile "+configFile.getAbsolutePath());
        Properties defaultProps = new Properties();
        defaultProps.setProperty("javax.persistence.jdbc.url", "jdbc:sqlserver://127.0.0.1;databaseName=RBSCT");
        defaultProps.setProperty("javax.persistence.jdbc.user", "ttt");
        defaultProps.setProperty("javax.persistence.jdbc.password", "ttt");
        defaultProps.setProperty("javax.persistence.jdbc.driver", "com.microsoft.sqlserver.jdbc.SQLServerDriver");

        configProps = new Properties(defaultProps);

        // loads properties from file
        System.out.println("configFile.exists( "+configFile.exists());
        if (configFile.exists()) {
            InputStream inputStream = new FileInputStream(configFile);
            getConfigProps().load(inputStream);
            inputStream.close();
        }

        return getConfigProps();
    }

    public void saveProperties(String host, String port, String user, String pass, String dbUrl, String dbuser, String dbpass, String dbDriver) throws IOException {
        getConfigProps().setProperty("javax.persistence.jdbc.url", "jdbc:sqlserver://127.0.0.1;databaseName=RBSCT");
        getConfigProps().setProperty("javax.persistence.jdbc.user", "irs");
        getConfigProps().setProperty("javax.persistence.jdbc.password", "irs");
        getConfigProps().setProperty("javax.persistence.jdbc.driver", "com.microsoft.sqlserver.jdbc.SQLServerDriver");


        OutputStream outputStream = new FileOutputStream(configFile);
        getConfigProps().store(outputStream, "host setttings");
        outputStream.close();
    }
}
