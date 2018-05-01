/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.dao;

import com.bbt.irs.ConfigUtility;
import com.bbt.irs.util.ReadProperty4romXML;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.PersistenceException;

/**
 *
 * @author opeyemi
 */
public class CreateTableDAO {
    Connection conn ;
    private final static String urlKey = "javax.persistence.jdbc.url";
    private final static String urlUser = "javax.persistence.jdbc.user";//
    private final static String urlpassword = "javax.persistence.jdbc.password";
    
    public CreateTableDAO()throws SQLException,PersistenceException{
        //connect2db();
        connectToDB();
    }
    public final void connect2db()throws SQLException,PersistenceException  {
        try {
            List<String> ls = ReadProperty4romXML.getDBUrl();
            conn = DriverManager.getConnection(ls.get(2),ls.get(1),ls.get(0));
        } catch (SQLException ex) {
            Logger.getLogger(CreateTableDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
       public final void connectToDB() throws SQLException{
           Properties prop;
        try {
            prop = ConfigUtility.loadProperties();
            conn = DriverManager.getConnection(prop.getProperty(urlKey),prop.getProperty(urlUser),prop.getProperty(urlpassword));
        } catch (IOException ex) {
            Logger.getLogger(CreateTableDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    
    public boolean createTable(String query){
        PreparedStatement pstmt ;
        int result = -1;
        boolean rs =  false;
        try {
            pstmt = conn.prepareStatement(query);
           result = pstmt.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(CreateTableDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(result>=0){
            rs=true;
        }
        return rs;
    }
}
