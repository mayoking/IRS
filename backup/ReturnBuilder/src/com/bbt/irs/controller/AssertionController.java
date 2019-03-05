/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.deploy.IRS;
import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.deploy.Messages;
import com.bbt.irs.exception.WebservicesException;
import com.bbt.irs.rest.client.RBRestClient;
import com.bbt.irs.util.Utility;
import com.bbt.irs.vo.AssertionRequestVO;
import com.bbt.irs.vo.AssertionResponseVO;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.StageStyle;
import static org.apache.logging.log4j.Level.ERROR;
import org.apache.logging.log4j.LogManager;
import org.controlsfx.dialog.ProgressDialog;

/**
 *
 * @author tkola
 */
public class AssertionController implements Initializable, Messages {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(WorkColRiTypeMappingController.class);

    @FXML
    TextArea clean;
    @FXML
    TextArea unclean;
    @FXML
    Button validate;

    String returnCode;
    String workCollectionCode;
    String typesAssertion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        returnCode = ModificationUIController.instance.returns.getSelectionModel().getSelectedItem().getReturnCode();
        workCollectionCode = ModificationUIController.instance.workCollection.getSelectionModel().getSelectedItem().getWorkCollectionCode();
        typesAssertion = ModificationUIController.instance.mTypeAssertion.getSelectionModel().getSelectedItem();
    }

    public static String replaceInputAssert(String assertz) {
        Pattern p = Pattern.compile("([<]{1}[a-z]+[:]{1}assert)({1}[a-z]+[:]{1}message)");
        Pattern p2 = Pattern.compile("{1}[a-z]+[:]{1}message");
        Pattern p3 = Pattern.compile("assertion");
        Matcher m = p.matcher(assertz);
        StringBuffer sb = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        StringBuffer sb3 = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "<xs:assert ");
        }
        m.appendTail(sb);
        Matcher n = p2.matcher(sb);
        while (n.find()) {
            n.appendReplacement(sb2, "xs:message");
        }
        n.appendTail(sb2);
        Matcher o = p3.matcher(sb);
        while (n.find()) {
            n.appendReplacement(sb3, "");
        }

        return sb3.toString();
    }

    @FXML
    public void submitAssertbutton(ActionEvent event) {
        boolean isInter = false;
        typesAssertion = ModificationUIController.instance.mTypeAssertion.getSelectionModel().getSelectedItem();
        if (typesAssertion == null || typesAssertion.isEmpty()) {
            IRSDialog.showAlert(INFO, "Select the type of assertion");
            return;
        }
        if (clean.getText() == null || unclean.getText().isEmpty()) {
            IRSDialog.showAlert(INFO, "You can only submitted validated xml");
            return;
        }

        if (typesAssertion.equals("INTRA VALIDATION")) {
            isInter = false;
        } else if (typesAssertion.equals("INTER VALIDATION")) {
            isInter = true;
        }
        boolean goahead = IRSDialog.showConfirmDialog("Submission", "This action will generate a new version of XSD. Do you want to proceed?");
        if (goahead) {
            Task worker = createWorker2(isInter);
            progressModal(worker);
        }
    }

    @FXML
    public void validateAssertion(ActionEvent event) {
        boolean isInter = false;
        typesAssertion = ModificationUIController.instance.mTypeAssertion.getSelectionModel().getSelectedItem();
        if (typesAssertion == null || typesAssertion.isEmpty()) {
            IRSDialog.showAlert(INFO, "Select the type of assertion");
            return;
        }
        if (unclean.getText() == null || unclean.getText().isEmpty()) {
            IRSDialog.showAlert(INFO, "Assertion container can not be empty");
            return;
        }

        if (typesAssertion.equals("INTRA VALIDATION")) {
            isInter = false;
        } else if (typesAssertion.equals("INTER VALIDATION")) {
            isInter = true;
        }

        boolean goahead = IRSDialog.showConfirmDialog("Submission", "Do you want to validate Assertion ?");
        if (goahead) {
            Task worker = createWorker(isInter);
            progressModal(worker);
        }

    }

    public void progressModal(Task worker) {
        Task copyWorker = worker;
        ProgressDialog dialog = new ProgressDialog(copyWorker);
        dialog.initStyle(StageStyle.TRANSPARENT);

        dialog.setGraphic(null);
        dialog.initStyle(StageStyle.TRANSPARENT);
        dialog.setHeaderText(null);
        dialog.setGraphic(null);
        dialog.initStyle(StageStyle.UTILITY);
        new Thread(copyWorker).start();
        dialog.showAndWait();
    }

    public Task createWorker(boolean isInter) {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                AssertionResponseVO res = null;
                try {
                    AssertionRequestVO vo = new AssertionRequestVO();
                    vo.setIsInter(isInter);
                    vo.setReturnCode(returnCode);
                    vo.setWorkCollectionCode(workCollectionCode);
                    vo.setUsername(IRS.getInstance().username);
                    vo.setUserAssertion(unclean.getText());
                    RBRestClient rbRestClient = new RBRestClient();
                    res = rbRestClient.validateAssertion(vo);
                    if (res.getDesc().isEmpty() || res.getDesc().equals("\n")) {
                        clean.setText(res.getSysAssertion());
                    } else {
                        Utility.updateUIThread("Info", res.getDesc());
                    }

                } catch (WebservicesException webservicesException) {
                    Utility.updateUIThread("Error", "Error while connecting to the server");
                    LOGGER.error(ERROR, webservicesException);
                }
                return res;
            }
        };
    }

    public Task createWorker2(boolean isInter) {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                AssertionResponseVO res = null;
                try {
                    AssertionRequestVO vo = new AssertionRequestVO();
                    vo.setIsInter(isInter);
                    vo.setReturnCode(returnCode);
                    vo.setWorkCollectionCode(workCollectionCode);
                    vo.setUsername(IRS.getInstance().username);
                    vo.setUserAssertion(clean.getText());
                    RBRestClient rbRestClient = new RBRestClient();
                    res = rbRestClient.submitAssertion(vo);
                    if (res.getDesc().isEmpty()) {
                        unclean.setText(res.getSysAssertion());
                    } else {
                        Utility.updateUIThread("Info", res.getDesc());
                    }

                } catch (WebservicesException webservicesException) {
                    Utility.updateUIThread("Error", "Error while connecting to the server");
                    LOGGER.error(ERROR, webservicesException);
                }
                return res;
            }
        };
    }
}
