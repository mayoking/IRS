/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.dao.ReturnsDAO;
import com.bbt.irs.dao.WorkCollectionDAO;
import com.bbt.irs.deploy.IRS;
import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.entity.TRtnReturns;
import com.bbt.irs.entity.TRtnWorkCollectionDefn;
import com.bbt.irs.exception.DatabaseException;
import java.io.IOException;
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
 * @author kaytush
 */
public class WorkColReturnMappingController implements Initializable {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(WorkColReturnMappingController.class);
    @FXML
    ListView wklistView;
    @FXML
    Pane listPane;
    @FXML
    Button okButton;
    @FXML
    ComboBox<TRtnWorkCollectionDefn> workCollection;
    List<TRtnWorkCollectionDefn> ls = new ArrayList();
    @FXML
    Button wkCancel;
    List<WorkColReturnMappingController.ReturnName> selectedReturns = new ArrayList();
    ObservableList<String> data = FXCollections.observableArrayList();
    private Stage stageDialog;
    private boolean isOkClicked;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ls = new WorkCollectionDAO().getWorkCollection();
            workCollection.setItems(FXCollections.observableArrayList(ls));
        } catch (Exception ex) {
            Logger.getLogger(WorkColRiTypeMappingController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // initializeListView();
        addListener2eworkCol();

    }

    @FXML
    public String cancelAction() {
        this.isOkClicked = false;
        stageDialog.close();
        return null;
    }

    @FXML
    public void submitAction() {

        try {
            ReturnsDAO ret = new ReturnsDAO();

            boolean result = new ReturnsDAO().linkworkcoll2Returns(selectedReturns, workCollection.getSelectionModel().getSelectedItem().getWorkCollectionCode());
            if (result) {
                IRSDialog.showAlert("Success", "Retun associated with work collection successfully. ");
                try {
                    MainController.getInstance().assocateReturn();
                } catch (IOException ex) {
                    LOGGER.error(Level.FATAL, ex);
                }
              

            } else {
                IRSDialog.showAlert("Error", "Error while associating work collection with return ");
            }
        } catch (DatabaseException ex) {
            ex.printStackTrace();
            IRSDialog.showAlert("Error", "Error while associating work collection with return ");
            Logger.getLogger(WorkColRiTypeMappingController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public void initializeListView(String wkcol) {
        StringConverter<ReturnName> converter;
        converter = new StringConverter<ReturnName>() {
            @Override
            public String toString(ReturnName document) {
                return document.getTitle();
            }

            @Override
            public ReturnName fromString(String string) {
                return null;
            }
        };
        List<ReturnName> totalList = getListofReturnswithout(wkcol);
        wklistView.getItems().addAll(totalList);
        wklistView.setCellFactory(CheckBoxListCell.forListView((ReturnName item) -> {
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
                    for (int i = 1; i < wklistView.getItems().size(); i++) {
                        ((WorkColReturnMappingController.ReturnName) wklistView.getItems().get(i)).setCompleted(true);
                        System.out.println("(Returns)wklistView.getItems().get(i) " + (WorkColReturnMappingController.ReturnName) wklistView.getItems().get(i));
                        selectedReturns.add(((WorkColReturnMappingController.ReturnName) wklistView.getItems().get(i)));
                    }
                } else if (item.getTitle().equals("ALL") && !isNowSelected) {
                    selectedReturns.clear();
                    for (int i = 1; i < wklistView.getItems().size(); i++) {
                        ((WorkColReturnMappingController.ReturnName) wklistView.getItems().get(i)).setCompleted(false);
                    }
                }
                System.out.println(" checkbox size " + selectedReturns.size());
                System.out.println(" checkbox size " + selectedReturns);
            }
            );

            return observable;
        }, converter));
    }

    public void createNewCellFactory() {

    }

    public void addListener2eworkCol() {

        workCollection.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<TRtnWorkCollectionDefn>() {
            @Override
            public void changed(ObservableValue observable, TRtnWorkCollectionDefn oldValue, TRtnWorkCollectionDefn newValue) {
                try {
                    listPane.getChildren().remove(wklistView);
                    //layoutX="23.0" opacity="0.78" prefHeight="333.0" prefWidth="733.0"
                    wklistView = new ListView();
                    wklistView.setLayoutX(23.0);
                    wklistView.setOpacity(0.78);
                    wklistView.setPrefSize(733.0, 333.0);
                    listPane.getChildren().add(wklistView);
                    wklistView.setFocusTraversable(false);
                    initializeListView(newValue.getWorkCollectionCode());
                    List<ReturnName> returntypes = getListofReturns(newValue.getWorkCollectionCode());
                    selectedReturns.clear();
                    // clearSelectionList();
                    wklistView.refresh();
                    //initializeListView();
//                    for (int i = 1; i < wklistView.getItems().size(); i++) {
////                        System.out.println("(Returns)wklistView.getItems().get(i) " + (ReturnName) wklistView.getItems().get(i));
////                        System.out.println("return " + returntypes);
//                        for (ReturnName s : returntypes) {
//                            System.out.println("s.getListofReturns() " + s);
//                            if (s.toString().equals((String) wklistView.getItems().get(i).toString())) {
//                                System.out.println("s inside " + s);
//                                ((ReturnName) wklistView.getItems().get(i)).setCompleted(true);
//
//                            }
//                        }
//                    }
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        }
        );
    }

    public void clearSelectionList() {

        for (int i = 0; i < wklistView.getItems().size(); i++) {
            ((ReturnName) wklistView.getItems().get(i)).setCompleted(false);
        }
    }

    public static List<ReturnName> getListofReturns() {
        List<ReturnName> ls = null;
        try {
            List<TRtnReturns> allReturns = new ReturnsDAO().getAllReturns();
            ls = new ArrayList();
            ls.add(new ReturnName("ALL", "", false));
            for (int i = 0; i < allReturns.size(); i++) {
                ls.add(new ReturnName(allReturns.get(i).getReturnCode().trim() + "---" + allReturns.get(i).getDescription().trim(), allReturns.get(i).getReturnCode(), false));
            }

        } catch (Exception ex) {
            LOGGER.log(Level.ERROR, ex);
        }
        return ls;
    }

    public static List<ReturnName> getListofReturns(String workcollcode) {
        List<ReturnName> ls = null;
        try {
            List<TRtnReturns> allReturns = new ReturnsDAO().getReturnsWithout(workcollcode);
            ls = new ArrayList();
            ls.add(new ReturnName("ALL", "", false));
            for (int i = 0; i < allReturns.size(); i++) {
                ls.add(new ReturnName(allReturns.get(i).getReturnCode().trim() + "---" + allReturns.get(i).getDescription().trim(), allReturns.get(i).getReturnCode(), false));
            }

        } catch (Exception ex) {
            LOGGER.log(Level.ERROR, ex);
        }
        return ls;
    }

//    public void clearSelectionList(){
//         
//         for (int i = 0; i < wklistView.getItems().size(); i++) {
//             ((ReturnName) wklistView.getItems().get(i)).setCompleted(false);
//         }
//    }
    public static List<ReturnName> getListofReturnswithout(String workCollectionCode) {
        List<ReturnName> ls = null;
        try {
            // List<TRtnReturns> getReturns = new ReturnsDAO().getReturns(workCollectionCode);
            List<TRtnReturns> getReturns = new ReturnsDAO().getReturnsWithout(workCollectionCode);
            ls = new ArrayList();
            ls.add(new ReturnName("ALL", "", false));
            for (int i = 0; i < getReturns.size(); i++) {
                ls.add(new ReturnName(getReturns.get(i).getReturnCode().trim(), getReturns.get(i).getReturnCode(), false));
            }
            System.out.println("size " + ls.size());

        } catch (Exception ex) {
            LOGGER.log(Level.ERROR, ex);
        }
        return ls;
    }

    public static class ReturnName {

        private final StringProperty title = new SimpleStringProperty();
        private final BooleanProperty completed = new SimpleBooleanProperty();
        private final StringProperty returnCode = new SimpleStringProperty();

        public ReturnName(String title, String returnCode, boolean completed) {
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

        public final void setReturnCode(final String returnCode) {
            this.returnCodeProperty().set(returnCode);
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
