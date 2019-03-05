/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.dao.WorkCollectionDAO;
import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.entity.TCoreRiType;
import com.bbt.irs.entity.TRtnWorkCollectionMapping;
import com.bbt.irs.exception.DatabaseException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;

/**
 * FXML Controller class
 *
 * @author tkola
 */
public class DissociateRIController implements Initializable {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(DissociateRIController.class);
    @FXML
    TextField ritypeCode;
    @FXML
    TextField workcollcode;
    @FXML
    TextField workcollDesc;
    WorkCollRiTypeMapChildController.RiTypeDataModel ritypeDataModel;
    TCoreRiType tcoreRiType;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ritypeDataModel = WorkCollRiTypeMapChildController.instance.workCollFromRiType.getSelectionModel().getSelectedItem();
            tcoreRiType = WorkCollRiTypeMappingController.instance.riTypeCollection.getSelectionModel().getSelectedItem();
            ritypeCode.setText(tcoreRiType.getDescription());
            workcollcode.setText(ritypeDataModel.getWorColCode());
            workcollDesc.setText(ritypeDataModel.getDescription());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void submit() {
        try {
            WorkCollectionDAO workDAO = new WorkCollectionDAO();
            TRtnWorkCollectionMapping mapping = workDAO.getWorCollRiTypeMapping(ritypeDataModel.getWorColCode(), tcoreRiType.getRiTypeCode());
            boolean result = workDAO.updateWorkCollectionMapping(mapping);
            System.out.println("result");
            IRSDialog.showAlert("Info", "Work Collection successfully dissociated from the riType");
        } catch (DatabaseException ex) {
            IRSDialog.showAlert("Error", "Error while dissociating workCollection from Ritype Mapping");
            LOGGER.log(org.apache.logging.log4j.Level.FATAL, "RI Type can not be loaded", ex);
        }finally{
            WorkCollRiTypeMapChildController.instance.dissociatingStage.close();
        }
    }

    @FXML
    public void cancel() {
      WorkCollRiTypeMapChildController.instance.handleRefresh();
      WorkCollRiTypeMapChildController.instance.dissociatingStage.close();
      System.out.println("closed");
    }

}
