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
import com.bbt.irs.entity.TRtnReturns;
import com.bbt.irs.entity.TRtnWorkCollectionDefn;
import com.bbt.irs.entity.TSctDbChanges;
import com.bbt.irs.exception.WebservicesException;
import com.bbt.irs.exception.WorkCollectionNotFoundException;
import com.bbt.irs.rest.client.RBRestClient;
import com.bbt.irs.util.Utility;
import com.bbt.irs.vo.GenWorCollVO;
import com.bbt.irs.vo.ResponseVO;
import java.io.File;
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
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author tkola
 */
public class TemplateCodeModification implements Initializable {

    @FXML
    private ComboBox<TRtnWorkCollectionDefn> workCollection;
    @FXML
    private ComboBox<TRtnReturns> returns;
    @FXML
    private Button submit;
    @FXML
    private TextField uploadUrl;
    @FXML
    private Button uploadButton;
    private FileChooser fileChooser = new FileChooser();
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
            uploadButton.setPrefWidth(75.0);
            uploadButton.setMinWidth(75.0);
            uploadButton.setMaxWidth(75.0);
            SynchronizerDAO synchronizerDAO = new SynchronizerDAO();
            List<TSctDbChanges> tsctDbChanges = synchronizerDAO.getTSctDbChanges();
            workCollectionList = new WorkCollectionDAO().getWorkCollection();
            workCollection.setItems(FXCollections.observableArrayList(workCollectionList));
            uploadButton.setOnAction(this::upload4XLS);
        } catch (Exception ex) {
            LOGGER.log(org.apache.logging.log4j.Level.FATAL, ex);
        }
        addListener2eworkCol();

    }

    /**
     * This method is used to set the path of the to the XLS to upload
     *
     *
     * @param actionEvent
     */
    public void upload4XLS(javafx.event.ActionEvent actionEvent) {
        System.out.println("About to get File chooser");
        fileChooser.setTitle("Upload XLS");

        File file = fileChooser.showOpenDialog(MainController.getInstance().getMainStage1());
        if (file != null) {
            uploadUrl.setText(file.getAbsolutePath());

        }
    }

    private boolean validateInput() {
        boolean result = true;
        if (workCollection == null || workCollection.getSelectionModel() == null || workCollection.getSelectionModel().getSelectedItem() == null) {
            IRSDialog.showAlert("Work Collection", "Work collection field can not be blank");
            return false;
        }
        LOGGER.info("****************************88 "+returns);
        if (returns == null || returns.getSelectionModel() == null || returns.getSelectionModel().getSelectedItem() == null) {
            IRSDialog.showAlert("Return", "Return field can not be blank");
            return false;
        }
        return result;
    }

    public Task createWorker() {
        return new Task() {
            String result = null;
          
            @Override
            protected Object call() throws Exception {
                File file2Upload = new File(uploadUrl.getText());;
                File newFileName=new File(uploadUrl.getText());
                LOGGER.info("================ Inside create worker ================== ");
                try {

                    String workCollCod = workCollection.getSelectionModel().getSelectedItem().getWorkCollectionCode();
                    String rtnCode = returns.getSelectionModel().getSelectedItem().getReturnCode();
                    RBRestClient rbRestClient = new RBRestClient();
                    LOGGER.info("this is the work collection " + workCollection.getSelectionModel().getSelectedItem().getWorkCollectionCode());
                    LOGGER.info("this is the return " +rtnCode);
                    
                    
                    newFileName = new File(file2Upload + "_" + workCollCod + "_" +rtnCode+"_"+IRS.getInstance().username);
                    boolean result = file2Upload.renameTo(newFileName);
                    LOGGER.info("=================== This is the new name result "+result);
                    ResponseVO res = rbRestClient.uploadExcelFile4TemplateCode(newFileName.getAbsolutePath());
                    if (res.getResponseCode() == 0) {
                        Utility.updateUIThread("Info", "Template Codes successfully generated");

                    } else {

                        Utility.updateUIThread(ERROR, res.getResponseDesc());
                        LOGGER.log(org.apache.logging.log4j.Level.FATAL, ERROR_DROPDOWN, res.getResponseDesc());
                    }

                } catch (WebservicesException ex) {
                    IRSDialog.showAlert(ERROR, ex.getMessage());
                    LOGGER.error(Level.ERROR,ex);
                } finally {
                     newFileName.renameTo(file2Upload);
                }
                return result;
            }
        };
    }

    @FXML
    public void handleXSDXMLSubmitAction() {
        boolean goahead = IRSDialog.showConfirmDialog("XSD", "This action will only affect a new version of Smart Client . Do you want to proceed?");
        if (!goahead) {
            return;
        }
        boolean result = validateInput();
        if (result) {
            new Utility().progressModal(createWorker());
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
//                    for (TRtnReturns s : returnList) {
//                        if (ls.contains(s.getReturnCode())) {
//                            returnAvailList.add(s);
//                        }
//                    }
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
}
