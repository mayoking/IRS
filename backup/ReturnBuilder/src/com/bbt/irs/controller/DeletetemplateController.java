/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.dao.MainDAO;
import com.bbt.irs.dao.ReturnsDAO;
import com.bbt.irs.dao.SchemaDAO;
import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.entity.TRtnReturns;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author tkola
 */
public class DeletetemplateController implements Initializable {

    @FXML
    ComboBox<TRtnReturns> returnCollection;
    List<TRtnReturns> ls = new ArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {

            // ls = new WorkCollectionDAO().getWorkCollection();
            ls = new ReturnsDAO().getReturns();
            returnCollection.setItems(FXCollections.observableArrayList(ls));
        } catch (Exception ex) {
            Logger.getLogger(WorkColRiTypeMappingController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void deleteAllrequiredTables() {
        try {
            MainDAO dao = new MainDAO();
            
            dao.deleteProblematicTemplate(returnCollection.getSelectionModel().getSelectedItem().getReturnCode());
            IRSDialog.showAlert("Info", "deletion of returns Successful");
        } catch (Exception ex) {
            ex.printStackTrace();
            IRSDialog.showAlert("Error", "deletion of returns Failed");
        }
    }
    
       public void deleteAllrequiredTables(String returnCode) {
        try {
            MainDAO dao = new MainDAO();
            
            dao.deleteProblematicTemplate(returnCode);
            IRSDialog.showAlert("Info", "deletion of returns Successful");
        } catch (Exception ex) {
            ex.printStackTrace();
            IRSDialog.showAlert("Error", "deletion of returns Failed");
        }
    }


}
