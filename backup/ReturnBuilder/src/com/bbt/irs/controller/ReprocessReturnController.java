/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.deploy.Messages;
import static com.bbt.irs.deploy.Messages.INFO;
import com.bbt.irs.exception.GenericException;
import com.bbt.irs.exception.ReturnBuilderException;
import com.bbt.irs.exception.WebservicesException;
import com.bbt.irs.rest.client.RBRestClient;
import com.bbt.irs.util.Utility;
import com.bbt.irs.vo.FailedReturnTemplate;
import com.bbt.irs.vo.ResponseVO;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import org.apache.logging.log4j.LogManager;

/**
 * FXML Controller class
 *
 * @author tkola
 */
public class ReprocessReturnController implements Initializable, Messages {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(ReprocessReturnController.class);
    @FXML
    DatePicker startDate;
    @FXML
    DatePicker endDate;
    @FXML
    Button submit;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        try {
//            this.validateInput();
//
//        } catch (ReturnBuilderException ex) {
//            IRSDialog.showAlert("Error", "Error while parsing input date parameter");
//            LOGGER.log(org.apache.logging.log4j.Level.FATAL, ex);
//        } catch (GenericException ex) {
//            IRSDialog.showAlert("Error", "Fatal Error while parsing input Dates");
//            LOGGER.log(org.apache.logging.log4j.Level.FATAL, ex);
//        }
    }

    @FXML
    public void reloadFailedReturns() {
         boolean result=false;
        try {
           result = this.validateInput();
            //result = true;
        } catch (ReturnBuilderException ex) {
            IRSDialog.showAlert("Error", "Error while parsing input date parameter");
            LOGGER.log(org.apache.logging.log4j.Level.FATAL, ex);
        } catch (GenericException ex) {
            IRSDialog.showAlert("Error", "Fatal Error while parsing input Dates");
            LOGGER.log(org.apache.logging.log4j.Level.FATAL, ex);
        }
        System.out.println("This is the result "+result);
        if(result){
        submit.getStyle();
        submit.getStylesheets().clear();
        submit.setStyle("-fx-background-color: #ff0000;");
        submit.setDisable(true);
        Task reloadFailedReturns = createWorker();
        new Thread(reloadFailedReturns).start();
        }
    }

    public Task createWorker() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                ResponseVO res = null;
                try {
                    FailedReturnTemplate failedreturnTemplate = new FailedReturnTemplate();
                    RBRestClient rbRestClient = new RBRestClient();
                    res = rbRestClient.reloadFailedReturns(failedreturnTemplate);
                    IRSDialog.showAlert(INFO, res.getResponseDesc());
                } catch (WebservicesException webservicesException) {
                    Utility.updateUIThread("Error", webservicesException.getMessage());
                    LOGGER.log(org.apache.logging.log4j.Level.FATAL, webservicesException.getMessage());
                } finally {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            submit.setStyle("-fx-background-color: #afcce0;");
                            submit.setDisable(false);
                            startDate.setValue(null);
                            endDate.setValue(null);

                        }
                    });

                }

                return res;
            }

        };

    }

    public boolean validateInput() throws ReturnBuilderException, GenericException {
        boolean result = true;
        try {
            System.out.println("startDate "+startDate.getValue());
            if (startDate.getValue() == null) {
                result = false;
                IRSDialog.showAlert(INFO, "Start validity date can not be empty");
            }

            if (endDate.getValue() == null) {
                result = false;
                IRSDialog.showAlert(INFO, "End validity date can not be empty");
            }

            if (startDate.getValue().isAfter(endDate.getValue())) {
                result = false;
                IRSDialog.showAlert(INFO, "Start validity date can not be greater than end validity date");
            }

//            if (Utility.convertLocalDate2Util(startDate.getValue()).before(new Date())) {
//                result = false;
//                IRSDialog.showAlert(INFO, "Start validity date can not be less than current date");
//            }

        }  catch (Exception ex) {
            LOGGER.log(org.apache.logging.log4j.Level.FATAL, ex.getMessage());
            throw new GenericException("Fatal error while validating input date");

        }
        return result;
    }

}
