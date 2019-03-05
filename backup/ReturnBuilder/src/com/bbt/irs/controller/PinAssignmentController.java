/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.deploy.IRS;
import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.exception.WebservicesException;
import com.bbt.irs.rest.client.RBRestClient;
import com.bbt.irs.vo.PinVO;
import com.bbt.irs.vo.ResponseVO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author tkola
 */
public class PinAssignmentController implements Initializable {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(PinAssignmentController.class);
    @FXML
    TextField token;
    @FXML
    TextField confirmToken;
    String username;
    @FXML
    Button submit;

    ResponseVO result = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.submit.setOnAction((ActionEvent event) -> {

        });
    }

    public void createPin() {

    }

    @FXML
    public void pinSubmitAction(ActionEvent e) {

        if (!token.getText().equals(confirmToken.getText())) {
            IRSDialog.showAlert("PinToken", "Mismatched Token");
        } else {
            PinVO pinvo = new PinVO();
            pinvo.setUsername(username);
            pinvo.setPin(token.getText());
            RBRestClient rbrs = new RBRestClient();
            try {
                result = rbrs.assignPINToUSer(pinvo);
                if (result.getResponseCode() == 0) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                IRS.getInstance().enterApplication("");
                            } catch (Exception ex) {
                                IRSDialog.showAlert("Login", "Error while login into the application");
                                LOGGER.error(Level.ERROR, ex);
                            }
                        }
                    });
                } else {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                IRS.getInstance().enterApplication("");
                            } catch (Exception ex) {
                                IRSDialog.showAlert("Login", "Error while login into the application");
                                LOGGER.error(Level.ERROR, ex);
                            }
                        }
                    });
                }
            } catch (WebservicesException webservicesException) {
                IRSDialog.showAlert("Login", "Unable to connect to webservice");
                LOGGER.error(Level.ERROR, webservicesException);
            }

        }
    }

}
