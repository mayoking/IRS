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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TopBarController implements Initializable{
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
            Logger.getLogger(TopBarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public static TopBarController getInstance() {
        return instance;
    }       
   
    
    
}
