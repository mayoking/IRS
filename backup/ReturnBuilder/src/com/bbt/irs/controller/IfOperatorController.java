/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.deploy.IRSDialog;
import static com.bbt.irs.deploy.Messages.FXMLPATH;
import static com.bbt.irs.deploy.Messages.INFO;
import com.bbt.irs.ui.HBoxGenerator;
import static com.bbt.irs.ui.HBoxGenerator.itemCodeAndColumnsVO;
import com.cbn.irs.logicconnector.LogicConnector;
import com.cbn.irs.ruleoperators.RuleOperator;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author tkola
 */
public class IfOperatorController extends ComplexUIController implements Initializable {

    @FXML
    VBox vbox4operator;
    @FXML
    HBox typeifHbox;
    @FXML
    ComboBox typeifCombo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList ls = FXCollections.observableArrayList();
        ls.add("Column");
        ls.add("Cell");
        typeifCombo.setItems(ls);
        addListenerToType();
    }

    public void addListenerToType() {
        typeifCombo.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equalsIgnoreCase("Column")) {
                    loadIfUI("collif.fxml");
                } else {
                    if (!itemCodeAndColumnsVO.getTemplateType().equalsIgnoreCase("1")) {
                        IRSDialog.showAlert(INFO, "The operation can not be applied to dynamic(variable) template");
                        return;
                    }
                    loadIfUI("cellif.fxml");
                }
            }

        }
        );
    }

    public void loadIfUI(String ui) {

        try {

            FXMLLoader loader = new FXMLLoader();
            Parent root = (Parent) loader.load(getClass().getResourceAsStream(FXMLPATH + ui));
            Stage stage = AssertionBuilderUIController.instance.getIfOperatorStage();//new Stage(StageStyle.DECORATED);
            stage.centerOnScreen();
            stage.setTitle("If Operator UI");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            AssertionBuilderUIController.instance.setIfOperatorStage(stage);
            AssertionBuilderUIController.instance.setIfOperatorScene(scene);
            stage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
            IRSDialog.showAlert("ERROR", "Error,Unable to Load the Requested UI");
        }
    }

    @Override
    public boolean validateOperands(Map<Integer, HBoxGenerator> ifmap, HBoxGenerator hboxGenerator, LogicConnector logicConn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean validateOperands(Map<Integer, HBoxGenerator> thenmap, HBoxGenerator hboxGenerator, RuleOperator ruleOperator) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
