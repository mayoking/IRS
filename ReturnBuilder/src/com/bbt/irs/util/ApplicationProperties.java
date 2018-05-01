/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ApplicationProperties {
private static final Logger LOGGER = LogManager.getLogger(ApplicationProperties.class);
    private String dbpath;
    private String dbpath2;
    private String dbIdpath;
    private String autoDataSyncScript;
    private String dataSyncBatchFile;
    private String autoDataSyncBatchFile;
    private String childrenPath;
    private String adultPath;
    private String onlinePath;
    private String syncFilePath;
    private String helpPath;
    private String appPath;
    private String logPath;
    private String aesKeys;
    private static ApplicationProperties properties;
    HashMap map;

    ApplicationProperties() {
        this.initConfigFile();
    }

    public static ApplicationProperties getApplicationProperties() {
        return properties;
    }

    static void setApplicationProperties(ApplicationProperties properties) {
        ApplicationProperties.properties = properties;
    }

    /**
     * @return the dbpath
     */
    public String getDbpath() {
        return dbpath;
    }

    /**
     * @param dbpath the dbpath to set
     */
    public void setDbpath(String dbpath) {
        this.dbpath = dbpath;
    }


     public void initConfigFile() {
        LoadProperties props = new LoadProperties();

        System.out.println(System.getProperty("user.home"));
        System.out.println("TTTTTTTTTTTTHHHHHHHHHHHHHHHHIIIIIIIIIIIIIIISSSSSSSSSSSSSSS"+System.getProperty("user.dir"));
       try {
             
           // props.load(new FileInputStream(System.getProperty("user.dir") + "/PROPERTIES.properties"));
            props.load(new FileInputStream(System.getProperty("user.home") + "/PROPERTIES.properties"));
            this.setAppPath(props.getProperty("APPPATH", ""));
            this.setDbpath("jdbc:hsqldb:"+getAppPath()+"/Module/db/database");
            this.setLogPath(getAppPath()+"Module/logs/lasrra.log");
            
            
                    
        } catch (Exception ex) {
            ex.getMessage();
        }
    }



 

    /**
     * @return the appPath
     */
    public String getAppPath() {
        return appPath;
    }

    /**
     * @param appPath the appPath to set
     */
    public void setAppPath(String appPath) {
        this.appPath = appPath;
    }

    /**
     * @return the logPath
     */
    public String getLogPath() {
        return logPath;
    }

    /**
     * @param logPath the logPath to set
     */
    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    public String getApplicationDirectory() {
        String applicationDir = null;;
        try {
            URL url = getClass().getResource("").toURI().toURL();
            applicationDir = url.getPath();

            if (url.getProtocol().equals("jar")) {
                applicationDir = new File(((JarURLConnection) url.openConnection()).getJarFileURL().getFile()).getParent();
            }
        } catch (MalformedURLException ex) {
             LOGGER.log(Level.FATAL,"Too bad", ex);
        } catch (IOException | URISyntaxException ex) {
            LOGGER.log(Level.FATAL,"Too bad", ex);
        }
        //logger.info(" full app path new "+applicationDir);
        return applicationDir;
    }

   

    /**
     * @return the dbpath2
     */
    public String getDbpath2() {
        return dbpath2;
    }

    /**
     * @param dbpath2 the dbpath2 to set
     */
    public void setDbpath2(String dbpath2) {
        this.dbpath2 = dbpath2;
    }


    /**
     * @return the dbIdpath
     */
    public String getDbIdpath() {
        return dbIdpath;
    }

    /**
     * @param dbIdpath the dbIdpath to set
     */
    public void setDbIdpath(String dbIdpath) {
        this.dbIdpath = dbIdpath;
    }
}
