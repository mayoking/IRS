/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.dao.MetadataTableDAO;
import com.bbt.irs.dao.RestrictionDAO;
import com.bbt.irs.dao.WorkCollectionDAO;
import com.bbt.irs.deploy.IRS;
import com.bbt.irs.deploy.IRSDialog;
import static com.bbt.irs.deploy.Messages.FXMLPATH;
import com.bbt.irs.entity.MetadataTable;
import com.bbt.irs.entity.TRbRestrictedField;
import com.bbt.irs.entity.TRbRestrictionCodes;
import com.bbt.irs.exception.DatabaseException;
import com.bbt.irs.exception.WebservicesException;
import com.bbt.irs.rest.client.RBRestClient;
import com.bbt.irs.util.Utility;
import com.bbt.irs.vo.ResponseVO;
import com.bbt.irs.vo.XSDModificationVO;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author tkola
 */
public class MainFieldModificationController implements Initializable {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(WorkCollectionDAO.class);
    @FXML
    ComboBox<String> table;
    @FXML
    ComboBox<MetadataTable> colname;
    @FXML
    ComboBox<TRbRestrictionCodes> restType;
    @FXML
    AnchorPane modificationPane;
    public static MainFieldModificationController instance;
    List<String> tableList;
    List<MetadataTable> colnameList;
    List<TRbRestrictionCodes> restTypeList;
    LinkedList<MainFieldModChildController.TableFieldDataModel> childStore = new LinkedList();
    //LinkedList 
    MainFieldModChildController mainFieldModChildController;
    String returnCode;
    String workCollectionCode;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        returnCode = ModificationUIController.instance.returns.getSelectionModel().getSelectedItem().getReturnCode();
        workCollectionCode = ModificationUIController.instance.workCollection.getSelectionModel().getSelectedItem().getWorkCollectionCode();
        try {
            tableList = new MetadataTableDAO().getNumberTables(returnCode);
            table.setItems(FXCollections.observableArrayList(tableList));
            restTypeList = new RestrictionDAO().getRestrictonCodes();
            restType.setItems(FXCollections.observableArrayList(restTypeList));
            instance = this;
            addListener2Table();
        } catch (DatabaseException ex) {
            Logger.getLogger(MainFieldModificationController.class.getName()).log(Level.SEVERE, null, ex);
            IRSDialog.showAlert("Error", "Unable to load Dropdowns");
        }
    }

    public void addListener2Table() {
        table.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue observable, String oldValue, String newValue) {
                try {
                    colnameList = new MetadataTableDAO().getmetadataList(newValue);
                    System.out.println("colnameList " + colnameList.size());
                    colname.setItems(FXCollections.observableArrayList(colnameList));
                } catch (Exception ex) {
                    IRSDialog.showAlert("ERROR", "Unable to get associated columns to the table");
                    LOGGER.error(ex);
                }
            }
        }
        );
    }

    public List<TRbRestrictedField> genRestrictionField(LinkedList<MainFieldModChildController.TableFieldDataModel> ls) {
        List<TRbRestrictedField> restField = new ArrayList();
        for (MainFieldModChildController.TableFieldDataModel s : ls) {
            try {
                TRbRestrictedField tRbRestrictedField = new TRbRestrictedField();
                tRbRestrictedField.setCreatedBy(IRS.getInstance().username);
                tRbRestrictedField.setCreatedDate(new Date());
                tRbRestrictedField.setReturnCode(returnCode);
                
                tRbRestrictedField.setReturnField(s.getTableField());
                RestrictionDAO dao = new RestrictionDAO();
                System.out.println("s.getFieldRestriction() "+s.getFieldRestriction());
                TRbRestrictionCodes test = dao.getRestrictonCodes(s.getFieldRestriction());
                System.out.println("test============== "+test.getRestrictionType());
                tRbRestrictedField.setRestrictionCode(test);
                restField.add(tRbRestrictedField);
            } catch (DatabaseException ex) {
                Logger.getLogger(MainFieldModificationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return restField;
    }

    @FXML
    public void loadChildFXML(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        Parent root = (Parent) loader.load(getClass().getResourceAsStream(FXMLPATH + "themodificationchild.fxml"));
        mainFieldModChildController = (MainFieldModChildController) loader.getController();
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);
        AnchorPane.setBottomAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        modificationPane.getChildren().clear();
        modificationPane.getChildren().add((AnchorPane) root);
    }

    public void submitModification(ActionEvent event) {
        new Utility().progressModal(createWorker());
    }

    public Task createWorker() {
        return new Task() {
            String result = null;

            @Override
            protected Object call() throws Exception {
                try {
                    List<TRbRestrictedField> listTRbRestrictedFields = genRestrictionField(childStore);
                    XSDModificationVO xsdModificationVO = new XSDModificationVO();
                    xsdModificationVO.setReturncode(returnCode);
                    xsdModificationVO.setUserName(IRS.getInstance().username);
                    xsdModificationVO.setWorkCollecion(workCollectionCode);
                    System.out.println("list of restrictedfields "+listTRbRestrictedFields.size());
                    xsdModificationVO.settRbRestrictedField(listTRbRestrictedFields);

                    RBRestClient rbRestClient = new RBRestClient();
                    ResponseVO res = rbRestClient.modifyreturnfield(xsdModificationVO);
                    if (res.getResponseCode() == 0) {
                        IRSDialog.showAlert("Info", "XSD modifcation Successfully completed");
                    } else {
                        IRSDialog.showAlert("Info", "XSD modifcation Failed");
                    }
                } catch (WebservicesException webservicesException) {
                    IRSDialog.showAlert("Error", webservicesException.getMessage());
                    LOGGER.log(org.apache.logging.log4j.Level.FATAL, webservicesException.getMessage());
                }

                return result;
            }
        };
    }

}
