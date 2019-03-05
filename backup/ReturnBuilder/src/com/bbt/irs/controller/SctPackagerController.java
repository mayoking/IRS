/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.dao.RITypeDAO;
import com.bbt.irs.dao.SynchronizerDAO;
import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.deploy.Messages;
import static com.bbt.irs.deploy.Messages.INFO;
import com.bbt.irs.deploy.SCTDialog2;
import com.bbt.irs.entity.TCoreRiType;
import com.bbt.irs.entity.TSctTypeDownload;
import com.bbt.irs.exception.DatabaseException;
import com.bbt.irs.exception.WebservicesException;
import com.bbt.irs.rest.client.RBRestClient;
import com.bbt.irs.util.Utility;
import com.bbt.irs.vo.ResponseVO;
import com.bbt.irs.vo.SCTPackagerVO;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import org.apache.logging.log4j.LogManager;

public class SctPackagerController implements Initializable, Messages {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(GenerateXSDXMLController.class);
    @FXML
    private ComboBox<TCoreRiType> riType;
    @FXML
    private Button submit;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextArea notes;
    private String riTypeCode;
    private SCTPackagerVO sctPackagerVO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            List<TCoreRiType> ls = new RITypeDAO().getRIype();
            riType.setItems(FXCollections.observableArrayList(ls));
            addListener2eriType();
        } catch (DatabaseException ex) {
            LOGGER.log(org.apache.logging.log4j.Level.FATAL, ex.getMessage());
            IRSDialog.showAlert("Error", ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(org.apache.logging.log4j.Level.FATAL, "Error while loading riType");
            IRSDialog.showAlert("Error", "Error while loading riType");
        }
        boolean dateResult = false;
        Date dt = null;
        try {
            dateResult = true;
            dt = new SynchronizerDAO().getStartValidityDateInVogue();

        } catch (DatabaseException ex) {
            dateResult = false;
            LOGGER.log(org.apache.logging.log4j.Level.FATAL, "Error while loading Date in vogue");
            //IRSDialog.showAlert("Error", "Error while loading Date in vogue");
        } catch (Exception ex) {
            dateResult = false;
            LOGGER.log(org.apache.logging.log4j.Level.FATAL, "Unkown Error while loading Date in vogue");
            // IRSDialog.showAlert("Error", "Unknown Error while loading Date in vogue");
        } finally {
            if (dateResult) {
                String datez = Utility.convertString2date(dt, "yyyy-MM-dd");
                datePicker.setValue(Utility.convertStringtoLocalDate(datez));
            } else {
                String datez = Utility.convertString2date(new Date(), "yyyy-MM-dd");
                datePicker.setValue(Utility.convertStringtoLocalDate(datez));

            }
            datePicker.setEditable(false);
        }
    }

    public void addAction2SubmitButton() {
        System.out.println(" I am called and setting background collor " + submit.getStyle());

        boolean thereisRelease = false;
        try {
            List<TSctTypeDownload> ls = new RITypeDAO().getLatestRitypeRelease(riTypeCode);
            for (TSctTypeDownload s : ls) {
                if ( s.getSctVersionType().equals("F")) {
                    IRSDialog.showAlert(INFO, "There is an exisitng Unreleased Smart Client");
                    thereisRelease = true;
                    break;
                }
            }
        } catch (DatabaseException ex) {
            thereisRelease = false;
           LOGGER.log(org.apache.logging.log4j.Level.FATAL, "Unkown Error while loading Date in vogue",ex);
        }
        if (!thereisRelease) {
              new Utility().progressModal(createWorker());
        }

    }

    public Task createWorker() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                String result = null;
                try {
                    System.out.println("I am inside ");
                    validateInput();
                    RBRestClient rbRestClient = new RBRestClient();
                    ResponseVO res = rbRestClient.createSmartClient(sctPackagerVO);
                    IRSDialog.showAlert(INFO, res.getResponseDesc());

                } catch (WebservicesException webservicesException) {
                    Utility.updateUIThread("Error", webservicesException.getMessage());
                    LOGGER.log(org.apache.logging.log4j.Level.FATAL, webservicesException.getMessage(),webservicesException);
                } finally {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            submit.setStyle("-fx-background-color: #afcce0;");
                            submit.setDisable(false);
                            datePicker.setValue(null);
                            notes.setText("");

                        }
                    });

                }
                return result;

            }
        };

    }

    public boolean validateInput() {
        boolean result = false;
        TCoreRiType res = riType.getSelectionModel().getSelectedItem();
        if (res == null) {
            SCTDialog2.alert(INFO, RITYPE_NOTIFICATION, result);
            return result;
        }
        System.out.println("datePicker value " + datePicker.getValue());
        if (datePicker.getValue() == null) {
            IRSDialog.showAlert(INFO, DATEFIELD_NOTIFICATION);
            return result;
        }

        if (datePicker.getValue() == null) {
            IRSDialog.showAlert(INFO, DATEFIELD_NOTIFICATION);
            return result;
        }
        if (notes.getText() == null || notes.getText().isEmpty()) {
            IRSDialog.showAlert(INFO, RELEASENOTES_NOTIFICATION);
            return result;
        }
        sctPackagerVO = new SCTPackagerVO();
        sctPackagerVO.setUsername(riTypeCode);
        try {
            sctPackagerVO.setFuturedate(Utility.convertLocalDate2UtilinTimeStamp(datePicker.getValue()));
        } catch (ParseException ex) {
            IRSDialog.showAlert(INFO, DATEERROR);
            Logger.getLogger(SctPackagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        sctPackagerVO.setRiType(res.getRiTypeCode());
        sctPackagerVO.setReleaseNote(notes.getText());
        return result;
    }

    public void addListener2eriType() {
        riType.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<TCoreRiType>() {
            @Override
            public void changed(ObservableValue observable, TCoreRiType oldValue, TCoreRiType newValue) {
                riTypeCode = newValue.getRiTypeCode();
            }
        }
        );
    }

}
