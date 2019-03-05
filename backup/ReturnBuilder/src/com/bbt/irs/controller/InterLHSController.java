/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.dao.ReturnsDAO;
import com.bbt.irs.dao.WorkCollectionDAO;
import com.bbt.irs.entity.TRtnReturns;
import com.bbt.irs.entity.TRtnWorkCollectionDefn;
import com.bbt.irs.exception.WorkCollectionNotFoundException;
import com.bbt.irs.ui.HBoxGenerator;
import com.bbt.irs.vo.RequestVO;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;

/**
 *
 * @author tkola
 */
public class InterLHSController extends LHSController {

    private boolean isStatic;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            instance = this;
            List<TRtnWorkCollectionDefn> ls = new WorkCollectionDAO().getWorkCollection();
            workColl.setItems(FXCollections.observableArrayList(ls));
            addListener2LHSWorkColl();
            addListener2LHSReturn();
        } catch (Exception ex) {
            Logger.getLogger(LHSController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addListener2LHSWorkColl() {
        workColl.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<TRtnWorkCollectionDefn>() {
            @Override
            public void changed(ObservableValue<? extends TRtnWorkCollectionDefn> observable, TRtnWorkCollectionDefn oldValue, TRtnWorkCollectionDefn newValue) {
                try {
                    List<TRtnReturns> trtnReturns;
                    trtnReturns = new ReturnsDAO().getAllActiveReturns(newValue.getWorkCollectionCode());
                    getReturns().setItems(FXCollections.observableArrayList(trtnReturns));
                } catch (WorkCollectionNotFoundException ex) {
                    Logger.getLogger(LHSController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        );
    }

    @Override
    public void addListener2LHSReturn() {

        getReturns().getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<TRtnReturns>() {
            @Override
            public void changed(ObservableValue<? extends TRtnReturns> observable, TRtnReturns oldValue, TRtnReturns newValue) {
                if (operatorVbox != null && operatorVbox.getChildren() != null) {
                    operatorVbox.getChildren().clear();
                    System.out.println("clear ");
                }
                generateCellORCol();
                System.out.println("process completed");
            }

        }
        );
    }

    /**
     *
     */
    @Override
    public void generateCellORCol() {
        RequestVO requestVO = new RequestVO();
        requestVO.setReturnCode(getReturns().getSelectionModel().getSelectedItem().getReturnCode());
        System.out.println("requestvo returncode " + requestVO.getReturnCode());
        requestVO.setWorkCollectionCode(workColl.getSelectionModel().getSelectedItem().getWorkCollectionCode());
        HBoxGenerator anchorPaneHBoxGenerator = new HBoxGenerator(lastYLayout, requestVO);
        anchorPaneHBoxGenerator.setLhsControllerInstance(instance);       
        anchorPaneHBoxGenerator.generateCompositeHBox();
        operatorVbox.getChildren().add(anchorPaneHBoxGenerator.getHboxContainer());
        counter = counter + 1;
        map.put(counter, anchorPaneHBoxGenerator);
        anchorPaneHBoxGenerator.setCounterID(counter);
        lastYLayout = lastYLayout + ydifferential;
    }

    /**
     * @return the isStatic
     */
    public boolean isIsStatic() {
        return isStatic;
    }

    /**
     * @param isStatic the isStatic to set
     */
    public void setIsStatic(boolean isStatic) {
        this.isStatic = isStatic;
    }
}
