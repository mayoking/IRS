/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.deploy.IRS;
import com.bbt.irs.deploy.Version;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

public class TopBarController implements Initializable{
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(MainController.class);
    @FXML
    private Label version;
    @FXML
    public Label currentStatus;
    @FXML
    public Label userMessage;
    @FXML
    private Button logOut;
     private static TopBarController instance;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        version.setText(Version.version);
    }
    
           @FXML
    protected void processLogout(ActionEvent event) {
        try {
            IRS.getInstance().loadLoginStage();
        } catch (IOException ex) {
            LOGGER.log(Level.FATAL, "ERROR_MENU", ex);
        }
    }
   
    public static TopBarController getInstance() {
        return instance;
    }       
   
    
    
}
