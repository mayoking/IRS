/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.deploy.IRS;
import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.deploy.Messages;
import com.bbt.irs.rest.client.RBRestClient;
import com.bbt.irs.vo.RuleSetVO;
import com.bbt.irs.vo.RulesetVOS;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author tkola
 */
public class AssertionRuleInterfaceController implements Initializable, Messages {

    enum Ruleaction {
        EDIT("View Rule"), NEW("New Rule");

        /**
         * @return the actions
         */
        public String getActions() {
            return actions;
        }

        /**
         * @param actions the actions to set
         */
        public void setActions(String actions) {
            this.actions = actions;
        }

        private String actions;

        private Ruleaction(String actions) {
            this.actions = actions;
        }
    };
    @FXML
    ComboBox comboAction;
    ComboBox<String> RuleSetCode;
    Label ruleSetCodeLabel;
    ComboBox selectRuleCombo;
    @FXML
    AnchorPane mainAnchor;
    private static String rulesetcode;
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(AssertionRuleInterfaceController.class);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ObservableList obsList = FXCollections.observableArrayList();
            obsList.setAll(Ruleaction.EDIT.getActions(), Ruleaction.NEW.getActions());
            comboAction.setItems(obsList);
        } catch (Exception ex) {
            LOGGER.log(org.apache.logging.log4j.Level.FATAL, ex);
        }
        addListener2comboAction();

    }

    public void addListener2comboAction() {
        comboAction.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue observable, String oldValue, String newValue) {
                try {

                    if (newValue.equalsIgnoreCase(Ruleaction.NEW.getActions())) {
                        MainController.getInstance().writeNewAssertion();
                    } else {
                        System.out.println("I am here");
                        RuleSetCode = getComboBox();

                        RBRestClient restclient = new RBRestClient();
                        RulesetVOS lsvos = restclient.loadRulesets();
                        List<String> ls = new ArrayList();
                        if (lsvos.getRulesets().size() > 0) {
                            for (RuleSetVO s : lsvos.getRulesets()) {
                                System.out.println("resultset code  " + s.getReturnCode());
                                ls.add(s.getReturnCode());
                            }
                        } else {
                            IRSDialog.showAlert(INFO, "No Result set available");
//                            return;
                        }
                        ObservableList obsList = FXCollections.observableArrayList();
                        obsList.setAll(ls);
                        RuleSetCode.setItems(obsList);
                        ruleSetCodeLabel = getComboBoxLabel("Rule Set");
                        addListener2ruleSetCode();
                        mainAnchor.getChildren().addAll(ruleSetCodeLabel, RuleSetCode);

                    }
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        }
        );
    }

    public void addListener2ruleSetCode() {
        RuleSetCode.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue observable, String oldValue, String newValue) {
                try {
                    rulesetcode = newValue;
                    listRules();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        );
    }

    public void listRules() {

        try {
            System.out.println("rulesetcode = newValue; "+rulesetcode);
            FXMLLoader loader = new FXMLLoader();
            Parent root = (Parent) loader.load(getClass().getResourceAsStream(FXMLPATH + "modifyviewassertion.fxml"));
            ModifyviewassertionController cont = (ModifyviewassertionController) loader.getController();
            cont.setRuleSetCode(rulesetcode);
            cont.loadData();
            Stage stage = new Stage(StageStyle.DECORATED);
            //cont.setModifyViewstage(stage);
            stage.centerOnScreen();
            stage.initOwner(IRS.getInstance().getMainStage());
            stage.setTitle("Modify Rule ");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException ex) {
            ex.printStackTrace();
            IRSDialog.showAlert("ERROR", "Error,Unable to load the Requested UI");
        }
    }

    public ComboBox getComboBox() {
        ComboBox combobox = new ComboBox();
        combobox.setLayoutX(271.0);
        combobox.setLayoutY(87.0);
        combobox.setPrefHeight(27.0);
        combobox.setPrefWidth(315.0);
        return combobox;
    }

    //<Label layoutX="55.0" layoutY="87.0" prefHeight="27.0" prefWidth="174.0" text="Work Collection"
    public Label getComboBoxLabel(String name) {
        Label label = new Label(name);
        label.setLayoutX(55.0);
        label.setLayoutY(87.0);
        label.setPrefHeight(27.0);
        label.setPrefWidth(174.0);

        return label;
    }

    public AnchorPane getLHS() {
        AnchorPane anchorpane = new AnchorPane();

        return anchorpane;
    }

}
