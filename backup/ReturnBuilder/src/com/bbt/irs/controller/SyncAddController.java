/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.dao.MainDAO;
import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.entity.TSctDbChanges;
import com.bbt.irs.exception.SynchronisationException;
import com.bbt.irs.util.Utility;
import java.text.ParseException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

/**
 *
 * @author tkola
 */
public class SyncAddController {

    TSctDbChanges tsctDbChanges;
    @FXML
    DatePicker dateofStartValDateCol;//
    @FXML
    Button button;
    SynchronizerController.ChangeTableDataModel changeTableDataModel;

    public void inflateUI(SynchronizerController.ChangeTableDataModel changeTableDataModel) {
        dateofStartValDateCol.setValue(Utility.convertStringtoLocalDate(changeTableDataModel.getStartValDate(),"dd-MM-yyyy"));
        this.changeTableDataModel = changeTableDataModel;
    }

    @FXML
    public void handleEditOperation() throws ParseException, SynchronisationException {
        MainDAO test = new MainDAO();
        List<TSctDbChanges> changes = null;
        try {
            changes = test.getTSctDbChangesByidForSync(changeTableDataModel.getId(), Utility.convertStringToDate(changeTableDataModel.getStartValDate(), "dd-MM-yyyy"));
        } catch (ParseException parseException) {
            IRSDialog.showAlert("ERROR", " The unput date can not be parsed");
        } catch (SynchronisationException synchronisationException) {
            IRSDialog.showAlert("ERROR", " The date can not be changed from other exisitng release date");
            
        }
        TSctDbChanges change = changes==null ? null : changes.get(0);
        if(change!=null){
            change.setValidityDate(Utility.convertLocalDate2Util(dateofStartValDateCol.getValue(),"dd-MM-yyyy"));
             boolean upd = test.updateTSctDbChanges(change);
        }else{
            System.out.println("TSctDbChanges  is null");
        }
       
    }

}
