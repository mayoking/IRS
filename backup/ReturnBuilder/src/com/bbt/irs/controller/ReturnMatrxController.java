/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.dao.ReturnsDAO;
import com.bbt.irs.dao.WorkCollectionDAO;
import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.entity.TRbReturnsMatrix;
import com.bbt.irs.entity.TRtnReturns;
import com.bbt.irs.entity.TRtnWorkCollectionDefn;
import com.bbt.irs.exception.DatabaseException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author tkola
 */
public class ReturnMatrxController implements Initializable {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(WorkColRiTypeMappingController.class);
    @FXML
    Pane pane;
    // @FXML
    ListView dependencyView;
    @FXML
    ComboBox workCollection;
    @FXML
    ComboBox<TRtnReturns> returnCollection;
    List<TRtnReturns> ls = new ArrayList();
    @FXML
    Button okButton;
    @FXML
    Button dependencyCancel;
    List<ReturnMatrxController.Returns> selectedReturns = new ArrayList();
    ObservableList<String> data = FXCollections.observableArrayList();
    private Stage stageDialog;
    private boolean isOkClicked;
    private ObservableList<Returns> data4WorkColl;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<TRtnWorkCollectionDefn> lsWorkColl = new WorkCollectionDAO().getWorkCollection();
            workCollection.setItems(FXCollections.observableList(lsWorkColl));

        } catch (Exception ex) {
            Logger.getLogger(WorkColRiTypeMappingController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        initializeListView(null);
        addListener2eworkCol();
        addListener2Returns();
    }

    public void initializeListView(String workCollid) {
        this.dependencyView = new ListView();
        dependencyView.setLayoutX(23.0);
        dependencyView.setLayoutY(54.0);
        dependencyView.setPrefHeight(279.0);
        dependencyView.setPrefWidth(733.0);
        dependencyView.setOpacity(0.78);
        pane.getChildren().add(dependencyView);
        StringConverter<Returns> converter;
        converter = new StringConverter<Returns>() {
            @Override
            public String toString(Returns document) {
                return document.getTitle();
            }

            // not actually used by CheckBoxListCell
            @Override
            public Returns fromString(String string) {
                return null;
            }
        };
        List<Returns> totalList;
        if (workCollid == null) {
            totalList = getListofReturns();
        } else {
            totalList = getListofReturns(workCollid);
        }
        dependencyView.getItems().addAll(totalList);
        dependencyView.setCellFactory(CheckBoxListCell.forListView((Returns item) -> {
            BooleanProperty observable = item.completedProperty();
            observable.addListener((obs, wasSelected, isNowSelected) -> {
                System.out.println("Check box for " + item.getReturnCode() + " changed from " + wasSelected + " to " + isNowSelected);
                if (isNowSelected) {
                    selectedReturns.add(item);
                } else {
                    selectedReturns.remove(item);
                }

                if (item.getTitle().equals("ALL") && isNowSelected) {
                    selectedReturns.clear();
                    for (int i = 1; i < dependencyView.getItems().size(); i++) {
                        ((Returns) dependencyView.getItems().get(i)).setCompleted(true);
                        System.out.println("(Returns)wklistView.getItems().get(i) " + (Returns) dependencyView.getItems().get(i));
                        selectedReturns.add(((Returns) dependencyView.getItems().get(i)));
                    }
                } else if (item.getTitle().equals("ALL") && !isNowSelected) {
                    selectedReturns.clear();
                    for (int i = 1; i < dependencyView.getItems().size(); i++) {
                        ((Returns) dependencyView.getItems().get(i)).setCompleted(false);
                    }
                }

            }
            );

            return observable;
        }, converter));
    }

    public void addListener2eworkCol() {
        workCollection.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TRtnWorkCollectionDefn>() {
            @Override
            public void changed(ObservableValue observable, TRtnWorkCollectionDefn oldValue, TRtnWorkCollectionDefn newValue) {
                try {
                    List<Returns> returnz = getListofReturns(newValue.getWorkCollectionCode());
                    selectedReturns.clear();
                    clearSelectionList();
                    dependencyView.getSelectionModel().clearSelection();
                    dependencyView.getItems().clear();
                    pane.getChildren().remove(dependencyView);
                    dependencyView = new ListView();
                    //data4WorkColl = FXCollections.observableList(returnz);
                   // dependencyView.setItems(data4WorkColl);
                    initializeListView(newValue.getWorkCollectionCode());
                    ls = new ReturnsDAO().getReturns(newValue.getWorkCollectionCode());
                    returnCollection.setItems(FXCollections.observableArrayList(ls));
//                   

                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        }
        );
    }

    public void addListener2Returns() {
        returnCollection.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TRtnReturns>() {
            @Override
            public void changed(ObservableValue observable, TRtnReturns oldValue, TRtnReturns newValue) {
                try {
                    List<TRbReturnsMatrix> rtmatrix = new ReturnsDAO().getReturnMatrix(newValue.getReturnCode());
                    for (int i = 1; i < dependencyView.getItems().size(); i++) {
                        for (TRbReturnsMatrix s : rtmatrix) {
                            System.out.println("s.getRiTypeCode() " + s);
                            if (s.getDependnecy().equals(((Returns) dependencyView.getItems().get(i)).getReturnCode())) {
                                System.out.println("s inside " + s);
                                ((Returns) dependencyView.getItems().get(i)).setCompleted(true);
                                // selectedReturns.add(((RiType) dependencyView.getItems().get(i)));
                            }
                        }
                    }
                } catch (DatabaseException ex) {
                    Logger.getLogger(ReturnMatrxController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }

        });
    }

    public void clearSelectionList() {

        for (int i = 0; i < dependencyView.getItems().size(); i++) {
            ((Returns) dependencyView.getItems().get(i)).setCompleted(false);
        }
    }

    public static List<Returns> getListofReturns() {
        List<Returns> ls = null;
        try {
            List<TRtnReturns> returns = new ReturnsDAO().getReturns();
            ls = new ArrayList();
            ls.add(new Returns("ALL", "", false));
            for (int i = 0; i < returns.size(); i++) {
                ls.add(new Returns(returns.get(i).getReturnCode() + "--" + returns.get(i).getDescription().trim(), returns.get(i).getReturnCode(), false));
            }

        } catch (Exception ex) {
            LOGGER.log(Level.ERROR, ex);
        }
        return ls;
    }

    public static List<Returns> getListofReturns(String workcollID) {
        List<Returns> ls = null;
        try {
            List<TRtnReturns> returns = new ReturnsDAO().getReturns(workcollID);
            ls = new ArrayList();
            ls.add(new Returns("ALL", "", false));
            for (int i = 0; i < returns.size(); i++) {
                ls.add(new Returns(returns.get(i).getReturnCode() + "--" + returns.get(i).getDescription().trim(), returns.get(i).getReturnCode(), false));
            }
            System.out.println("size " + ls.size());

        } catch (Exception ex) {
            LOGGER.log(Level.ERROR, ex);
        }
        return ls;
    }

    /**
     * @return the isOkClicked
     */
    public boolean isIsOkClicked() {
        return isOkClicked;
    }

    @FXML
    public void submitAction() {

        try {
            boolean result = new ReturnsDAO().linkreturn2Dependencies(selectedReturns, returnCollection.getSelectionModel().getSelectedItem().getReturnCode());
            if (result) {
                IRSDialog.showAlert("Error", "Return dependency matrix linkage is successful ");
            } else {
                IRSDialog.showAlert("Error", "Error while associating dependency to return ");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            IRSDialog.showAlert("Error", "Error while associating dependency to return ");
            Logger.getLogger(WorkColRiTypeMappingController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }

    @FXML
    public String cancelAction() {
//        this.isOkClicked = false;
//        stageDialog.close();
        return null;
    }

    public static class Returns {

        private final StringProperty title = new SimpleStringProperty();
        private final BooleanProperty completed = new SimpleBooleanProperty();
        private final StringProperty returnCode = new SimpleStringProperty();

        public Returns(String title, String returnCode, boolean completed) {
            setTitle(title);
            setCompleted(completed);
            setReturnCode(returnCode);
        }

        public final StringProperty returnCodeProperty() {
            return this.returnCode;
        }

        public final String getReturnCode() {
            return this.returnCodeProperty().get();
        }

        public final void setReturnCode(final String riTypeCode) {
            this.returnCodeProperty().set(riTypeCode);
        }

        public final StringProperty titleProperty() {
            return this.title;
        }

        public final String getTitle() {
            return this.titleProperty().get();
        }

        public final void setTitle(final String title) {
            this.titleProperty().set(title);
        }

        public final BooleanProperty completedProperty() {
            return this.completed;
        }

        public final boolean isCompleted() {
            return this.completedProperty().get();
        }

        public final void setCompleted(final boolean completed) {
            this.completedProperty().set(completed);
        }

        @Override
        public String toString() {

            return getReturnCode();
        }

    }

}
