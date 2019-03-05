/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.dao.IRSDAO;
import com.bbt.irs.dao.RITypeDAO;
import com.bbt.irs.dao.ReturnsDAO;
import com.bbt.irs.deploy.ErrorNameDesc;
import static com.bbt.irs.deploy.ErrorNameDesc.ERROR;
import static com.bbt.irs.deploy.ErrorNameDesc.ERROR_DROPDOWN;
import com.bbt.irs.deploy.IRS;
import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.entity.TCoreRiType;
import com.bbt.irs.entity.TRtnReturns;
import com.bbt.irs.entity.TRtnWorkCollectionDefn;
import com.bbt.irs.exception.WebservicesException;
import com.bbt.irs.exception.WorkCollectionNotFoundException;
import com.bbt.irs.rest.client.RBRestClient;
import com.bbt.irs.vo.GenWorCollVO;
import com.bbt.irs.vo.ViewXMLXSDVO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javax.ws.rs.ProcessingException;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author tkola
 */
public class XSDXMLViewController implements Initializable, ErrorNameDesc {

    @FXML
    private ComboBox<String> version;
    @FXML
    private ComboBox<TRtnWorkCollectionDefn> workCollection;
    @FXML
    private ComboBox<TRtnReturns> returns;
    @FXML
    private Button submit;
    @FXML
    Button downloadxsd;
    @FXML
    Button downloadxml;
    List<TCoreRiType> riTypeList;
    List<TRtnWorkCollectionDefn> workCollectionList;
    List<TRtnReturns> returnList;
    ObservableList<TRtnWorkCollectionDefn> observableWorkCollectionList;
    ObservableList<TRtnReturns> observableReturnList;
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(GenerateXSDXMLController.class);
    public static String xsdname;
    public static String xmlname;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            workCollectionList = new IRSDAO().getFullWorkCollection();
            observableWorkCollectionList = FXCollections.observableArrayList(workCollectionList);
            workCollection.setItems(observableWorkCollectionList);

        } catch (Exception ex) {
            Logger.getLogger(GenerateXSDXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //addListener2Ritype();
        addListener2eworkCol();

    }

    private boolean validateInput() {
        boolean result = true;
//        if (version == null || version.getSelectionModel() == null || version.getSelectionModel().getSelectedItem() == null) {
//            IRSDialog.showAlert("version", "vesion field can not be blank");
//            return false;
//        }
        if (workCollection == null || workCollection.getSelectionModel() == null || workCollection.getSelectionModel().getSelectedItem() == null) {
            IRSDialog.showAlert("Work Collection", "Work collection field can not be blank");
            return false;
        }
        if (returns == null || returns.getSelectionModel() == null || returns.getSelectionModel().getSelectedItem() == null) {
            IRSDialog.showAlert("Returns ", "Return field can not be blank");
            return false;
        }
        return result;
    }

    @FXML
    public void handleXSDXMLSubmitAction() {

        boolean result = validateInput();
        if (result) {
            try {
                xsdname = returns.getSelectionModel().getSelectedItem().getReturnCode() + ".xsd";
                xmlname = returns.getSelectionModel().getSelectedItem().getReturnCode() + ".xml";
                GenWorCollVO genWorCollVO = new GenWorCollVO();
                genWorCollVO.setReturns(returns.getSelectionModel().getSelectedItem().getReturnCode());
                genWorCollVO.setWorkCollectionCode(workCollection.getSelectionModel().getSelectedItem().getWorkCollectionCode());
                genWorCollVO.setRiType("");
                RBRestClient rbRestClient = new RBRestClient();
                ViewXMLXSDVO res = rbRestClient.viewXSDXML(genWorCollVO);
                if (res.getResponseCode() == 0) {
                    IRS.getInstance().xml = res.getXml();
                    IRS.getInstance().xsd = res.getXsd();

                    MainController.getInstance().viewXMLXSD();

                } else {
                    IRSDialog.showAlert(ERROR, res.getResponseDescription());
                    LOGGER.log(org.apache.logging.log4j.Level.FATAL, ERROR_DROPDOWN, res.getResponseDescription());
                }
            } catch (WebservicesException ex) {
                IRSDialog.showAlert(ERROR, ex.getMessage());
                LOGGER.log(org.apache.logging.log4j.Level.FATAL, ex.getMessage());
            } catch (IOException ex) {
                LOGGER.log(org.apache.logging.log4j.Level.FATAL, IOEXCEPTION);
                IRSDialog.showAlert(ERROR, IOEXCEPTION);
            } catch (ProcessingException ex) {
                IRSDialog.showAlert(ERROR, PROCESSINEXCEPTION);
                LOGGER.log(org.apache.logging.log4j.Level.FATAL, PROCESSINEXCEPTION);
            } catch (Exception ex) {
                IRSDialog.showAlert(ERROR, ex.getMessage());
                LOGGER.log(org.apache.logging.log4j.Level.FATAL, ex);
            }
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
                    returnList = new ReturnsDAO().getReturns(newValue.getWorkCollectionCode());
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

    public void addListener2Ritype() {
        version.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue observable, String oldValue, String newValue) {
                try {
                    System.out.println("new value " + newValue);
                    workCollection.getItems().clear();
                    workCollectionList = new IRSDAO().getFullWorkCollection();
                    observableWorkCollectionList = FXCollections.observableArrayList(workCollectionList);
                    workCollection.setItems(observableWorkCollectionList);
                    System.out.println("Listener completed ");
                } catch (WorkCollectionNotFoundException ex) {
                    LOGGER.error(ex);
                }
            }
        }
        );
    }

    @FXML
    public void addListener2Downloadxsd() {

    }

    public static String convertResourceStream2File(InputStream ins, String outputFile, String returnCode, String workcollectionCode) throws IOException {

        Path target = Paths.get(new File(outputFile).getAbsolutePath() + (workcollectionCode == null ? "" : File.separator + workcollectionCode));
        boolean dirExists = Files.exists(target);
        if (!dirExists) {
            Files.createDirectories(target);
        }
        String xsdp = target + File.separator + returnCode + ".xsd";
        if (!new File(xsdp).exists()) {
            Files.copy(ins, Paths.get(xsdp));
        }
        return xsdp;

    }

}
