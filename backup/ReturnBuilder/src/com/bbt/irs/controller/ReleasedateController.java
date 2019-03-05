/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.dao.MainDAO;
import com.bbt.irs.deploy.IRSDialog;
import static com.bbt.irs.deploy.Messages.INFO;
import com.bbt.irs.exception.DatabaseException;
import com.bbt.irs.util.Utility;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

/**
 * FXML Controller class
 *
 * @author tkola
 */
public class ReleasedateController implements Initializable {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(ReleasedateController.class);
    /**
     * Initializes the controller class.
     */
    @FXML
    DatePicker extendeddate;

    @FXML
    AnchorPane rootAnchor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void extendReleaseDate() {
        MainDAO maindao = new MainDAO();
        try {
            if (extendeddate.getValue() == null) {
                IRSDialog.showAlert("Info", "Date field can not be blank");
                return;
            }
            System.out.println("Utility.getCurrentTime() "+Utility.getCurrentTime());
            if ((Utility.convertLocalDate2Util(extendeddate.getValue()).compareTo(Utility.getdate()) < 0)) {
                IRSDialog.showAlert(INFO, "The start validity date can not be less than todays Date ");
                return;
            }
            boolean res = maindao.extendReleasedate(Utility.convertLocalDate2Util(extendeddate.getValue()));
            if (res) {

                IRSDialog.showAlert("Error", "Release date successfully updated ");
            }
        } catch (DatabaseException ex) {
            LOGGER.log(Level.ERROR, ex);
            IRSDialog.showAlert("Error", ex.getMessage());
        } catch (ParseException ex) {
            LOGGER.log(Level.ERROR, ex);
            IRSDialog.showAlert("Error", "Unable to extend Date");
        } finally {
            Stage stage = (Stage) rootAnchor.getScene().getWindow();
            stage.close();
        }

    }

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) rootAnchor.getScene().getWindow();
        stage.close();
    }

}
