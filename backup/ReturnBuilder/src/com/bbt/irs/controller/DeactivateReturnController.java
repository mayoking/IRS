/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.dao.MainDAO;
import com.bbt.irs.dao.ReturnsDAO;
import com.bbt.irs.dao.WorkCollectionDAO;
import com.bbt.irs.deploy.IRSDialog;
import static com.bbt.irs.deploy.Messages.INFO;
import static com.bbt.irs.deploy.Messages.UNSYNC_CODE;
import com.bbt.irs.entity.TCoreRiType;
import com.bbt.irs.entity.TRtnReturns;
import com.bbt.irs.entity.TRtnWorkCollectionDefn;
import com.bbt.irs.entity.TSctDbChanges;
import com.bbt.irs.exception.DatabaseException;
import com.bbt.irs.exception.WorkCollectionNotFoundException;
import com.bbt.irs.util.Utility;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author kaytush
 */
public class DeactivateReturnController implements Initializable {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(DeactivateReturnController.class);
    @FXML
    private ComboBox<TRtnWorkCollectionDefn> workCollection;
    @FXML
    private ComboBox<TRtnReturns> returns;
    @FXML
    private Button submit;
    List<TCoreRiType> riTypeList;
    List<TRtnWorkCollectionDefn> workCollectionList;
    List<TRtnReturns> returnList;
    ObservableList<TRtnWorkCollectionDefn> observableWorkCollectionList;
    ObservableList<TRtnReturns> observableReturnList;
    @FXML
    DatePicker validityDate;
    @FXML
    TextArea reason;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            workCollectionList = new WorkCollectionDAO().getWorkCollection();
            workCollection.setItems(FXCollections.observableArrayList(workCollectionList));
            addListener2eworkCol() ;
        } catch (Exception ex) {
            IRSDialog.showAlert("ERROR", "Unable to load work collection");
            LOGGER.log(Level.ERROR, ex);
        }
    }

    private boolean validateInput() throws ParseException {
        boolean result = true;
        if (workCollection == null || workCollection.getSelectionModel() == null || workCollection.getSelectionModel().getSelectedItem() == null) {
            IRSDialog.showAlert("Work Collection", "Work collection field can not be blank");
            return false;
        }
        if (returns == null || returns.getSelectionModel() == null || returns.getSelectionModel().getSelectedItem() == null) {
            IRSDialog.showAlert("Returns ", "Return field can not be blank");
            return false;
        }
        if(reason.getText()==null || reason.getText().isEmpty()){
             IRSDialog.showAlert("Reason ", "Reason field can not be blank");
            return false;
        }
//         if (Utility.convertLocalDate2UtilinTimeStamp(validityDate.getValue()).compareTo(Utility.getCurrentTime()) < 0) {
//            IRSDialog.showAlert("Info", FUTURE_DATE_ERROR);
//            //result = false;
//            return false;
//        }
        if(!valStartValidityDate()){
            return false;
        }
        return result;
    }
     public boolean  valStartValidityDate() throws ParseException{
           boolean result = false;
           List<TSctDbChanges> tsd = new ArrayList();
            System.out.println("this is the converted date "+Utility.convertLocalDate2Util(validityDate.getValue()));
           tsd = new MainDAO().getTSctDbChanges(UNSYNC_CODE,Utility.convertLocalDate2Util(validityDate.getValue()));
           List<Date> dt = new MainDAO().getUNSYNCDateDbChanges();
           System.out.println("");
           if(dt==null || dt.isEmpty()){
               result = true;
               return result;
           }
           
           if(!(Utility.convertLocalDate2Util(validityDate.getValue()).compareTo(dt.get(0))==0)){
               result =false;
               IRSDialog.showAlert(INFO,"The start validity date is different from other release date ");
           }else{
               result = true;
           }
           return result;
       }

    public void addListener2eworkCol() {
        workCollection.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<TRtnWorkCollectionDefn>() {
            @Override
            public void changed(ObservableValue observable, TRtnWorkCollectionDefn oldValue, TRtnWorkCollectionDefn newValue) {
                try {
                    returns.getItems().clear();
                    System.out.println("new value " + newValue.getDescription());
                    returnList = new ReturnsDAO().getReturns(newValue.getWorkCollectionCode());
                    observableReturnList = FXCollections.observableArrayList(returnList);
                    returns.setItems(observableReturnList);
                    System.out.println("Listener completed ");
                } catch (WorkCollectionNotFoundException ex) {
                    //IRSDialog.showAlert("ERROR", "nO ");
                    LOGGER.error(ex);
                }
            }
        }
        );
    }

    @FXML
    public void handleXSDXMLSubmitAction(ActionEvent event) {

        try {
            boolean result = validateInput();
            if (result) {
                ReturnsDAO dao = new ReturnsDAO();
                try {
                    System.out.println("About to do deactivation ");
                    TRtnReturns retu  = returns.getSelectionModel().getSelectedItem();
                    result = dao.checkReturnInSyncTable(retu.getReturnCode());
                    if(!result){
                        IRSDialog.showAlert("Info","Two opposing actions can not be performed on a single return"); 
                        return;
                    }
                    dao.deactivateReturns(retu,Utility.convertLocalDate2Util(validityDate.getValue()),reason.getText());
                    System.out.println("Deactivation completed");
                    IRSDialog.showAlert("Info", "Your request has been submitted for deactivation");
                } catch (DatabaseException ex) {
                    IRSDialog.showAlert("ERROR",ex.getMessage());
                    LOGGER.log(Level.ERROR, ex);
                }
            }
        } catch (Exception ex) {
            IRSDialog.showAlert("ERROR", ex.getMessage());
            LOGGER.log(Level.ERROR, "Fatal Error, Unable to deactivate return");
        }finally{
            resetDeactivation();
        }

    }
    
    public void resetDeactivation(){
        workCollection.getSelectionModel().clearSelection();
        returns.getSelectionModel().clearSelection();
        reason.setText("");
    }

}
