/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.dao.MainDAO;
import com.bbt.irs.dao.ReturnsDAO;
import com.bbt.irs.dao.SynchronizerDAO;
import com.bbt.irs.dao.WorkCollectionDAO;
import static com.bbt.irs.deploy.ErrorNameDesc.ERROR;
import static com.bbt.irs.deploy.ErrorNameDesc.ERROR_DROPDOWN;
import com.bbt.irs.deploy.IRS;
import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.deploy.Messages;
import com.bbt.irs.entity.TCoreRiType;
import com.bbt.irs.entity.TRtnReturns;
import com.bbt.irs.entity.TRtnWorkCollectionDefn;
import com.bbt.irs.entity.TSctDbChanges;
import com.bbt.irs.exception.WebservicesException;
import com.bbt.irs.exception.WorkCollectionNotFoundException;
import com.bbt.irs.rest.client.RBRestClient;
import com.bbt.irs.util.Utility;
import com.bbt.irs.vo.GenWorCollVO;
import com.bbt.irs.vo.ResponseVO;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author tkola
 */
public class GenerateXSDXMLController implements Initializable, Messages {

//    @FXML
//    private ComboBox<TCoreRiType> riType;
    @FXML
    private ComboBox<TRtnWorkCollectionDefn> workCollection;
    @FXML
    private ComboBox<TRtnReturns> returns;
    @FXML
    private Button submit;
    List<TCoreRiType> riTypeList;
    List<TRtnWorkCollectionDefn> workCollectionList;
    List<TRtnReturns> returnList;
    List<TRtnReturns> returnAvailList = new ArrayList();
    ObservableList<TRtnWorkCollectionDefn> observableWorkCollectionList;
    ObservableList<TRtnReturns> observableReturnList;
    @FXML
    CheckBox bulkgen;
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(GenerateXSDXMLController.class);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            SynchronizerDAO synchronizerDAO = new SynchronizerDAO();
            List<TSctDbChanges> tsctDbChanges = synchronizerDAO.getTSctDbChanges();
            workCollectionList = new WorkCollectionDAO().getWorkCollection();
            workCollection.setItems(FXCollections.observableArrayList(workCollectionList));
        } catch (Exception ex) {
            LOGGER.log(org.apache.logging.log4j.Level.FATAL, ex);
        }
        addListener2eworkCol();
        addListener2CheckedBox();

    }

    public void addListener2CheckedBox() {
        bulkgen.selectedProperty().addListener(
                new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    returns.setDisable(true);
                } else {
                    returns.setDisable(false);

                }
            }
        });
    }

    private boolean validateInput() {
        boolean result = true;
        if (workCollection == null || workCollection.getSelectionModel() == null || workCollection.getSelectionModel().getSelectedItem() == null) {
            IRSDialog.showAlert(INFO, WORKCOLFIELDNOBLANK);
            return false;
        }
        if (!bulkgen.isSelected()) {
            if (returns == null || returns.getSelectionModel() == null || returns.getSelectionModel().getSelectedItem() == null) {
                IRSDialog.showAlert(INFO , RETURNFIELDNOBLANK);
                return false;
            }
        }
        return result;
    }

    public Task createWorker() {
        return new Task() {
            String result = null;

            @Override
            protected Object call() throws Exception {

                try {
                    GenWorCollVO genWorCollVO = new GenWorCollVO();
                    if (!bulkgen.isSelected()) {
                        genWorCollVO.setReturns(returns.getSelectionModel().getSelectedItem().getReturnCode());
                    }
                    genWorCollVO.setWorkCollectionCode(workCollection.getSelectionModel().getSelectedItem().getWorkCollectionCode());
                    genWorCollVO.setGenWorkColl(bulkgen.isSelected());
                    RBRestClient rbRestClient = new RBRestClient();
                    ResponseVO res = rbRestClient.generateXMLXSD(genWorCollVO);
                    if (res.getResponseCode() == 0) {
                        Utility.updateUIThread(INFO, SUCCXSDGEN);

                    } else {

                        Utility.updateUIThread(ERROR, res.getResponseDesc());
                        LOGGER.log(org.apache.logging.log4j.Level.FATAL, ERROR_DROPDOWN, res.getResponseDesc());
                    }
                } catch (WebservicesException ex) {
                    IRSDialog.showAlert(ERROR, ex.getMessage());
                    LOGGER.error(ex);
                }
                return result;
            }
        };
    }

    @FXML
    public void handleXSDXMLSubmitAction() {
        boolean goahead = IRSDialog.showConfirmDialog(XSDTITLE, NEWVERSIONWARNING);
        if (!goahead) {
            return;
        }
        String currentVersion = IRS.appconfig.getVersion();
        String futureVersion = IRS.appconfig.getFutureVersion();
        String requiredNewVersion = IRS.appconfig.getRequireChangesInVersion();
        if (!(currentVersion.equals(futureVersion)) && !requiredNewVersion.equals(REQUIRESNEWVESION)) {
            IRSDialog.showAlert(XSDTITLE, FUTURVERSIONWARNING);
            return;
        }
        if ((currentVersion.equals(futureVersion)) && requiredNewVersion.equals(REQUIRESNEWVESION)) {
            IRSDialog.showAlert(XSDTITLE, SYNCCOMPLWARNING);
            return;
        }
        if ((currentVersion.equals(futureVersion)) && !requiredNewVersion.equals(REQUIRESNEWVESION)) {
            boolean result = validateInput();
            if (result) {
                new Utility().progressModal(createWorker());
            }
        } else {
            IRSDialog.showAlert(XSDTITLE, VERSIONINDETERMSTATE);
        }

    }

    public void addListener2eworkCol() {
        workCollection.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<TRtnWorkCollectionDefn>() {
            @Override
            public void changed(ObservableValue observable, TRtnWorkCollectionDefn oldValue, TRtnWorkCollectionDefn newValue) {
                try {
                    returns.getItems().clear();
                    System.out.println("new value " + newValue.getDescription());
                    returnList = new ReturnsDAO().getAllActiveReturns(newValue.getWorkCollectionCode());
                    System.out.println("returnList " + returnList);
                    List<String> ls = new MainDAO().getAllUNSYNCNewReturnsAsString();
                    System.out.println("lssss " + ls.size());
                    for (TRtnReturns s : returnList) {
                        if (ls.contains(s.getReturnCode())) {
                            returnAvailList.add(s);
                        }
                    }
                    // observableReturnList = FXCollections.observableArrayList(returnAvailList);
                    observableReturnList = FXCollections.observableArrayList(returnList);
                    returns.setItems(observableReturnList);
                    System.out.println("Listener completed ");
                } catch (WorkCollectionNotFoundException ex) {
                    LOGGER.error(ex);
                }
            }
        }
        );
    }

//    public void addListener2Ritype() {
//        riType.getSelectionModel().selectedItemProperty().addListener(
//                new ChangeListener<TCoreRiType>() {
//            @Override
//            public void changed(ObservableValue observable, TCoreRiType oldValue, TCoreRiType newValue) {
//                try {
//                    System.out.println("new value " + newValue.getDescription());
//                    workCollection.getItems().clear();
//                    workCollectionList = new IRSDAO().getFullWorkCollection(newValue.getRiTypeCode());
//                    observableWorkCollectionList = FXCollections.observableArrayList(workCollectionList);
//                    workCollection.setItems(observableWorkCollectionList);
//                    System.out.println("Listener completed ");
//                } catch (WorkCollectionNotFoundException ex) {
//                    LOGGER.error(ex);
//                }
//            }
//        }
//        );
//    }
}
