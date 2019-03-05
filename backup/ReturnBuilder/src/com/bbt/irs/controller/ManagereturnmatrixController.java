/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.dao.RITypeDAO;
import com.bbt.irs.dao.ReturnsDAO;
import com.bbt.irs.dao.WorkCollectionDAO;
import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.entity.TRbReturnsMatrix;
import com.bbt.irs.entity.TRtnReturns;
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
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

/**
 * FXML Controller class
 *
 * @author tkola
 */
public class ManagereturnmatrixController implements Initializable {
 private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(WorkColRiTypeMappingController.class);
    @FXML
    ListView returnDepListView;
    @FXML
    ComboBox<TRtnReturns> returnCollection;
    List<TRtnReturns> ls = new ArrayList();
    @FXML
    Button okButton;
    @FXML
    Button wkCancel;
    List<ManagereturnmatrixController.Dependencies> listofDependencies = new ArrayList();
    ObservableList<String> data = FXCollections.observableArrayList();
    private Stage stageDialog;
    private boolean isOkClicked;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            // ls = new WorkCollectionDAO().getWorkCollection();
            ls = new ReturnsDAO().getReturns();
            returnCollection.setItems(FXCollections.observableArrayList(ls));
        } catch (Exception ex) {
            Logger.getLogger(WorkColRiTypeMappingController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        initializeListView();
    }

    public void addListener2ReturnCol() {
        addItemToWorkCList(null);
        returnCollection.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TRtnReturns>() {
            @Override
            public void changed(ObservableValue observable, TRtnReturns oldValue, TRtnReturns newValue) {
                try {
               
                    returnDepListView.getItems().clear();
                    List<Dependencies> lsdd = getListofDependencies(newValue.getReturnCode());
                    System.out.println("ls  " + lsdd);
                    returnDepListView.getItems().addAll(lsdd);

                } catch (Exception ex) {
                    ex.printStackTrace();
                    IRSDialog.showAlert("ERROR", "Unable to load workcollection for the selected RI Type");
                    LOGGER.error(ex);
                }
            }
        }
        );
    }

    public void initializeListView() {

       
        addListener2ReturnCol();
    }

    public void addItemToWorkCList(String riType) {
        StringConverter<Dependencies> converter;
        converter = new StringConverter<Dependencies>() {
            @Override
            public String toString(Dependencies document) {
                return document.getTitle();
            }

            // not actually used by CheckBoxListCell
            @Override
            public Dependencies fromString(String string) {
                return null;
            }
        };
        ///List<worCollection> lsdd = getListofWorkCollections(riType);
        returnDepListView.getItems().clear();
        returnDepListView.refresh();
       // wklistView.getItems().addAll(lsdd);
        returnDepListView.setCellFactory(CheckBoxListCell.forListView((Dependencies item) -> {
            BooleanProperty observable = item.completedProperty();
            observable.addListener((obs, wasSelected, isNowSelected) -> {
                System.out.println("Check box for " + item.getRiTypeCode() + " changed from " + wasSelected + " to " + isNowSelected);
                if (isNowSelected) {
                    listofDependencies.add(item);
                } else {
                    listofDependencies.remove(item);
                }

                if (item.getTitle().equals("ALL") && isNowSelected) {
                    listofDependencies.clear();
                    for (int i = 1; i < returnDepListView.getItems().size(); i++) {
                        ((Dependencies) returnDepListView.getItems().get(i)).setCompleted(true);
                        System.out.println("(Returns)wklistView.getItems().get(i) " + (Dependencies) returnDepListView.getItems().get(i));
                        listofDependencies.add(((Dependencies) returnDepListView.getItems().get(i)));
                    }
                } else if (item.getTitle().equals("ALL") && !isNowSelected) {
                    listofDependencies.clear();
                    for (int i = 1; i < returnDepListView.getItems().size(); i++) {
                        ((Dependencies) returnDepListView.getItems().get(i)).setCompleted(false);
                    }
                }
                System.out.println(" checkbox size " + listofDependencies.size());
            }
            );

            return observable;
        }, converter));
    }

    public static List<Dependencies> getListofDependencies(String returnCode) {
        List<Dependencies> ls = null;
        List<TRbReturnsMatrix> returnMatrix;
        try {
            if (returnCode == null || returnCode.isEmpty()) {
                returnMatrix = new ReturnsDAO().getReturnMatrix(returnCode);
            } else {
                returnMatrix = new ReturnsDAO().getReturnMatrix(returnCode);
            }
            ls = new ArrayList();
            ls.add(new Dependencies("ALL", "", false));
            for (int i = 0; i < returnMatrix.size(); i++) {
                ls.add(new Dependencies(returnMatrix.get(i).getDependnecy().trim(), returnMatrix.get(i).getDependnecy(), false));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
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
//
//        try {
//            boolean result = new RITypeDAO().linkworkcoll2RiTypes(selectedReturns, workCollection.getSelectionModel().getSelectedItem().getWorkCollectionCode());
//            if (result) {
//                IRSDialog.showAlert("Error", "Ri Types associated with work collection successfully. ");
//            } else {
//                IRSDialog.showAlert("Error", "Error while associating work collection with ritype ");
//            }
//        } finally {
//
//        }
//        catch (DatabaseException ex) {
//            ex.printStackTrace();
//            IRSDialog.showAlert("Error", "Error while associating work collection with ritypes ");
//            Logger.getLogger(WorkColRiTypeMappingController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
    }

    @FXML
    public String cancelAction() {
        this.isOkClicked = false;
        stageDialog.close();
        return null;
    }

    public static class Dependencies {

        private final StringProperty title = new SimpleStringProperty();
        private final BooleanProperty completed = new SimpleBooleanProperty();
        private final StringProperty riTypeCode = new SimpleStringProperty();

        public Dependencies(String title, String riTypeCode, boolean completed) {
            setTitle(title);
            setCompleted(completed);
            setRiTypeCode(riTypeCode);
        }

        public final StringProperty riTypeCodeProperty() {
            return this.riTypeCode;
        }

        public final String getRiTypeCode() {
            return this.riTypeCodeProperty().get();
        }

        public final void setRiTypeCode(final String riTypeCode) {
            this.riTypeCodeProperty().set(riTypeCode);
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

    }
}
