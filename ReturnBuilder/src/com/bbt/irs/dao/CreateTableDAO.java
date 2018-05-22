/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.dao;

import com.bbt.irs.controller.MainController;
import com.bbt.irs.util.ConfigUtility;
import com.bbt.irs.util.ReadProperty4romXML;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;
import javax.persistence.PersistenceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author opeyemi
 */
public class CreateTableDAO {
 private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(MainController.class);
    Connection conn;
    private final static String urlKey = "javax.persistence.jdbc.url";
    private final static String urlUser = "javax.persistence.jdbc.user";//
    private final static String urlpassword = "javax.persistence.jdbc.password";

    public CreateTableDAO() throws SQLException, PersistenceException, IOException {
        //connect2db();
        connectToDB();
    }

    public final void connect2db() throws SQLException, PersistenceException {
        try {
            List<String> ls = ReadProperty4romXML.getDBUrl();
            conn = DriverManager.getConnection(ls.get(2), ls.get(1), ls.get(0));
        } catch (SQLException ex) {
             LOGGER.log(Level.FATAL, "connect2db had issue", ex);
        }
    }

    public final void connectToDB() throws SQLException,IOException {
        Properties prop;
        try {
            prop = ConfigUtility.loadProperties();
            conn = DriverManager.getConnection(prop.getProperty(urlKey), prop.getProperty(urlUser), prop.getProperty(urlpassword));
        } catch (IOException ex) {
            LOGGER.log(Level.FATAL, "connectToDB had issue", ex);
        }
    }

    public boolean createTable(String query) {
        PreparedStatement pstmt=null;
        int result = -1;
        boolean rs = false;
        try {
            pstmt = conn.prepareStatement(query);
            result = pstmt.executeUpdate();

        } catch (SQLException ex) {
             LOGGER.log(Level.FATAL, "createTable had issue", ex);
        }finally{
            if(pstmt!=null){
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    LOGGER.log(Level.FATAL, "pstmt.close had issue", ex);
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException ex) {
                    LOGGER.log(Level.FATAL, "conn.close had issue", ex);
                }
            }
        }
        if (result >= 0) {
            rs = true;
        }
        return rs;
    }
//    public static PreparedStatement pstmt=null;
//    public static 
    public  void deleteTables(List<String> ls){
        //String query = null;
        for(int i=0;i<ls.size();i++){
            try {
                PreparedStatement pstmt = conn.prepareStatement("drop table "+ls.get(i));
                int result = pstmt.executeUpdate();
            } catch (SQLException ex) {
                LOGGER.log(Level.FATAL, "Can not drop table", ex);
            }
        }
    }
}
