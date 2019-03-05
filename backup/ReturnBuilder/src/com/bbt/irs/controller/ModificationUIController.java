/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.dao.MainDAO;
import com.bbt.irs.dao.ReturnsDAO;
import com.bbt.irs.dao.WorkCollectionDAO;
import com.bbt.irs.deploy.IRSDialog;
import static com.bbt.irs.deploy.Messages.FXMLPATH;
import com.bbt.irs.entity.TCoreRiType;
import com.bbt.irs.entity.TRbModificationType;
import com.bbt.irs.entity.TRtnReturns;
import com.bbt.irs.entity.TRtnWorkCollectionDefn;
import com.bbt.irs.exception.WorkCollectionNotFoundException;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author kaytush
 */
public class ModificationUIController implements Initializable {

    public static boolean isASSERTION = true;
    @FXML
    ComboBox<TCoreRiType> riType;
    @FXML
    ComboBox<TRbModificationType> mType;
    @FXML
    AnchorPane modificationPane;
    @FXML
    ScrollPane modificationScolep;
    @FXML
    ComboBox<TRtnWorkCollectionDefn> workCollection;
    @FXML
    ComboBox<TRtnReturns> returns;
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(GenerateXSDXMLController.class);
    @FXML
    public Button updateActionClick;
    List<TRtnReturns> returnList;
    String returnCode;
    ComboBox<String> mTypeAssertion;
    Label mTypeAssertionLabel = new Label("Assertion Type");
    @FXML
    AnchorPane globalAchorPane;

    List<TCoreRiType> riTypeList;
    List<TRtnWorkCollectionDefn> workCollectionList;
    List<TRbModificationType> modificationTypes;
    ObservableList<TRtnWorkCollectionDefn> observableWorkCollectionList;
    ObservableList<TRtnReturns> observableReturnList;
    public static ModificationUIController instance;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            modificationTypes = new MainDAO().getModTypes();
            workCollectionList = new WorkCollectionDAO().getWorkCollection();
            workCollection.setItems(FXCollections.observableArrayList(workCollectionList));
            instance = this;
        } catch (Exception ex) {
            LOGGER.log(Level.ERROR, ex);
        }

        addListener2MType();
        addListener2eworkCol();
        //mType.getItems().addAll("ASSERTION", "XSD FIELD RESTRICTION","TEMPLATE MODIFICATION");
        mType.setItems(FXCollections.observableArrayList(modificationTypes));
        //mType.getSelectionModel().selectFirst();

    }

    public void removeNewComboBox() {
        globalAchorPane.getChildren().remove(mTypeAssertion);
    }

    public void createNewComboBox() {
        mTypeAssertion = new ComboBox();
        mTypeAssertion.setLayoutX(288.0);
        mTypeAssertion.setLayoutY(137.0);
        mTypeAssertion.setPrefWidth(293.0);
        mTypeAssertion.setPrefHeight(45.0);
        mTypeAssertion.setStyle("-fx-background-insets: 0; -fx-padding: 0; -fx-background-color: #F4F4F4; -fx-border-color: #CDCDCD;");

        mTypeAssertionLabel.setLayoutX(96.0);
        mTypeAssertionLabel.setLayoutY(146.0);
        mTypeAssertionLabel.setPrefWidth(162.0);
        mTypeAssertionLabel.setPrefHeight(27.0);
        mTypeAssertionLabel.setFont(Font.font("System Bold", 18));
        mTypeAssertionLabel.setTextFill(Color.web("#464444"));

        mTypeAssertion.getItems().addAll("INTRA VALIDATION", "INTER VALIDATION");
        globalAchorPane.getChildren().add(mTypeAssertion);

    }

    public void addListener2eworkCol() {
        workCollection.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<TRtnWorkCollectionDefn>() {
            @Override
            public void changed(ObservableValue observable, TRtnWorkCollectionDefn oldValue, TRtnWorkCollectionDefn newValue) {
                try {
                    //returns.getItems().clear();
                    System.out.println("new value " + newValue.getDescription());
                    returnList = new ReturnsDAO().getReturns(newValue.getWorkCollectionCode());
                    observableReturnList = FXCollections.observableArrayList(returnList);
                    returns.setItems(observableReturnList);
                    System.out.println("Listener completed ");
                    instance = ModificationUIController.this;
                } catch (WorkCollectionNotFoundException ex) {
                    LOGGER.error(ex);
                }
            }
        }
        );
    }

    public void addListener2MType() {
        mType.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<TRbModificationType>() {
            @Override
            public void changed(ObservableValue observable, TRbModificationType oldValue, TRbModificationType newValue) {
                //isASSERTION = !newValue.equals("FIELD MODIFICATION");
                if ((workCollection.getSelectionModel().getSelectedItem() == null && mType.getSelectionModel().getSelectedItem() != null) || returns.getSelectionModel().getSelectedItem() == null) {
                    IRSDialog.showAlert("ERROR", "Workcollection of Return Field can not be blank");
                    mType.getSelectionModel().clearSelection();
                    return;
                }
                switch (newValue.getModificationType()) {
                    case "ASSERT":
                        try {
                            createNewComboBox();
                            modificationPane.getChildren().clear();
                            FXMLLoader loader = new FXMLLoader();
                            Parent root = (Parent) loader.load(getClass().getResourceAsStream(FXMLPATH + "assertion.fxml"));
                            modificationPane.getChildren().add(root);
                        } catch (IOException ex) {
                            LOGGER.log(Level.ERROR, ex);
                        }
                        break;
                    //resetParameters();
                    case "XSDFIELDRESTR":
                        removeNewComboBox();
                        modificationPane.getChildren().clear();
                        try {
                            modificationPane.getChildren().clear();
                            MainController.getInstance().loadFieldModification();
                        } catch (IOException ex) {

                            LOGGER.error(ex);
                        }
                        break;
                    case "TEMPMOD":
                        System.out.println("Inside Template Modification");
                        removeNewComboBox();
                        modificationPane.getChildren().clear();
                        try {
                            modificationPane.getChildren().clear();
                            MainController.getInstance().loadTemplateModification();
                        } catch (IOException ex) {

                            LOGGER.error(ex);
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }

}
