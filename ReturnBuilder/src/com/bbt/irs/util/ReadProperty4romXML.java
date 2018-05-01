/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.util;

/**
 *
 * @author opeyemi
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public class ReadProperty4romXML {

    private final static String urlKey = "javax.persistence.jdbc.url";
    private final static String urlUser = "javax.persistence.jdbc.user";//
    private final static String urlpassword = "javax.persistence.jdbc.password";

    public static List<String> getDBUrl()throws PersistenceException {

        Map<String, Object> map = readPersistenceFile();
        System.out.println("map.size() "+map.size());
        String Url = (String) map.get(urlKey);
        //String [] UrlArray = Url.split("?");
        // String realURL  = UrlArray[0];
        String user = (String) map.get(urlUser);
        String password = (String) map.get(urlpassword);
        Properties ppty = new Properties();
        ppty.setProperty(user, Url);
        //String dbUrl = Url+"?&User="+user+"&Password="+password;
        String dbUrl = Url;
        System.out.println(dbUrl);
        List<String> ls = new ArrayList();
        ls.add(user);
        ls.add(password);
        ls.add(dbUrl);
        return ls;
    }

    public static Map<String, Object> readPersistenceFile() throws PersistenceException {
        System.out.println("inside persistence reader");
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ReturnBuilderPU");
        Map<String, Object> propertiesMap = entityManagerFactory.getProperties();
        System.out.println("These are the properties "+propertiesMap.size());

        for (Map.Entry<String, Object> e : propertiesMap.entrySet()) {
            System.out.println("key=" + e.getKey() + " value = " + e.getValue().toString());
        }

        return propertiesMap;
    }
    
    public static Map<String, String> readPropertyFile(){
        Map map = null;
        
        return map;
    }

    public static boolean populatePersistenceFile() {
        boolean result;
        EntityManagerFactory managerFactory ;
        Map<String, String> persistenceMap = new HashMap();

        persistenceMap.put("javax.persistence.jdbc.url", "jdbc:sqlserver://127.0.0.1;databaseName=RBSCT");
        persistenceMap.put("javax.persistence.jdbc.user", "irs");
        persistenceMap.put("javax.persistence.jdbc.password", "irs");
        persistenceMap.put("javax.persistence.jdbc.driver", "com.microsoft.sqlserver.jdbc.SQLServerDriver");

        managerFactory = Persistence.createEntityManagerFactory("ReturnBuilderPU", persistenceMap);
        EntityManager manager = managerFactory.createEntityManager();
        result =true;
        return result;
    }
    
    public static void main(String [] args){
        try {
            
             String path = System.getProperty("user.home");//
            System.out.println("This is the main method"+path);
            //populatePersistenceFile();
           // System.out.println("About to call the method");
           // readPersistenceFile();
        } catch (PersistenceException ex) {
            Logger.getLogger(ReadProperty4romXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
