/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.dao.MainDAO;
import com.bbt.irs.dao.RITypeDAO;
import com.bbt.irs.dao.ReturnsDAO;
import com.bbt.irs.deploy.IRSDialog;
import static com.bbt.irs.deploy.Messages.INFO;
import com.bbt.irs.entity.TCoreRiType;
import com.bbt.irs.entity.TRbReturnsMatrix;
import com.bbt.irs.entity.TRtnReturns;
import com.bbt.irs.entity.TSctTypeDownload;
import com.bbt.irs.exception.DatabaseException;
import com.bbt.irs.rest.client.RBRestClient;
import com.bbt.irs.util.Utility;
import com.bbt.irs.vo.ExtractorVO;
import com.bbt.irs.vo.ResponseVO;
import java.net.URL;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;

/**
 * FXML Controller class
 *
 * @author tkola
 */
public class SynchronizeextractorController implements Initializable {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(MainDAO.class);
    @FXML
    TextField fileName;
    @FXML
    TextField startvalidityDate;
    @FXML
    TextArea description;
    @FXML
    Button submit;
    @FXML
    ComboBox<TCoreRiType> riType;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<TCoreRiType> ritype = new RITypeDAO().getRIype();
            riType.setItems(FXCollections.observableList(ritype));
        } catch (DatabaseException ex) {
            IRSDialog.showAlert("Error", "Error while loading RiType");
            LOGGER.log(org.apache.logging.log4j.Level.FATAL, ex.getMessage());
        }
        addListenerRiType();
    }

    public void addListenerRiType() {
        riType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TCoreRiType>() {
            @Override
            public void changed(ObservableValue observable, TCoreRiType oldValue, TCoreRiType newValue) {
                try {
                    TSctTypeDownload sctd = getSCTDownload(riType.getSelectionModel().getSelectedItem().getRiTypeCode());
                    fileName.setText(sctd.getSctFileName());
                    startvalidityDate.setText(Utility.convertTimestamp2String(sctd.getStartValidityDate()));
                    description.setText(sctd.getDescription());
                } catch (DatabaseException ex) {
                    IRSDialog.showAlert("Error", ex.getMessage());
                    LOGGER.log(org.apache.logging.log4j.Level.FATAL, ex.getMessage());
                } catch (Exception ex) {
                    IRSDialog.showAlert("Error", "Unbale to load Smart Client Information");
                    LOGGER.log(org.apache.logging.log4j.Level.FATAL, ex.getMessage());
                }
            }

        });
    }

    public void getSCTToSynchronize() throws DatabaseException {
        TSctTypeDownload sctd = getSCTDownload(riType.getSelectionModel().getSelectedItem().getRiTypeCode());
        fileName.setText(sctd.getSctFileName());
        startvalidityDate.setText(Utility.convertTimestamp2String(sctd.getStartValidityDate()));
        description.setText(sctd.getDescription());
    }

    public TSctTypeDownload getSCTDownload(String riType) throws DatabaseException {
        MainDAO maindao = new MainDAO();
        TSctTypeDownload sctd = null;
        try {
            sctd = maindao.getSCTDownload(riType);
        } catch (DatabaseException ex) {
            LOGGER.log(org.apache.logging.log4j.Level.FATAL, ex.getMessage());
            throw new DatabaseException(ex.getMessage());
        }
        return sctd;
    }

    @FXML
    public void syncExtraction() {
        submit.setStyle("-fx-background-color: #ff0000;");
        submit.setDisable(true);
        Task createSmartclient = createWorker();
        new Thread(createSmartclient).start();
    }

    public Task createWorker() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                String result = null;
                try {
                    ExtractorVO extractorVO = new ExtractorVO();
                    RBRestClient rbRestClient = new RBRestClient();
                    ResponseVO res = rbRestClient.synchronizeExtractor(extractorVO);
                    IRSDialog.showAlert(INFO, res.getResponseDesc());

                } catch (Exception ex) {
                    ex.printStackTrace();
                    Utility.updateUIThread("Error", ex.getMessage());
                    LOGGER.log(org.apache.logging.log4j.Level.FATAL, ex.getMessage());
                } finally {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            submit.setStyle("-fx-background-color: #afcce0;");
                            submit.setDisable(false);

                        }
                    });

                }
                return result;

            }
        };

    }

}
