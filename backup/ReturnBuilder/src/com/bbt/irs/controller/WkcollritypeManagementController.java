/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.dao.RITypeDAO;
import com.bbt.irs.dao.WorkCollectionDAO;
import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.entity.TCoreRiType;
import com.bbt.irs.entity.TRtnWorkCollectionDefn;
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
public class WkcollritypeManagementController implements Initializable {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(WorkColRiTypeMappingController.class);
    @FXML
    ListView wklistView;
    @FXML
    ComboBox<TCoreRiType> workCollection;
    List<TCoreRiType> ls = new ArrayList();
    @FXML
    Button okButton;
    @FXML
    Button wkCancel;
    List<WkcollritypeManagementController.worCollection> selectedReturns = new ArrayList();
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
            ls = new RITypeDAO().getRIype();
            workCollection.setItems(FXCollections.observableArrayList(ls));
        } catch (Exception ex) {
            Logger.getLogger(WorkColRiTypeMappingController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        initializeListView();
    }

    public void addListener2eworkCol() {
        addItemToWorkCList(null);
        workCollection.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<TCoreRiType>() {
            @Override
            public void changed(ObservableValue observable, TCoreRiType oldValue, TCoreRiType newValue) {
                try {
               
                    wklistView.getItems().clear();
                    List<worCollection> lsdd = getListofWorkCollections(newValue.getRiTypeCode());
                    System.out.println("ls  " + lsdd);
                    wklistView.getItems().addAll(lsdd);
                   // wklistView.refresh();
                    //addItemToWorkCList(newValue.getRiTypeCode());

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

       
        addListener2eworkCol();
    }

    public void addItemToWorkCList(String riType) {
        StringConverter<worCollection> converter;
        converter = new StringConverter<worCollection>() {
            @Override
            public String toString(worCollection document) {
                return document.getTitle();
            }

            // not actually used by CheckBoxListCell
            @Override
            public worCollection fromString(String string) {
                return null;
            }
        };
        ///List<worCollection> lsdd = getListofWorkCollections(riType);
        wklistView.getItems().clear();
        wklistView.refresh();
       // wklistView.getItems().addAll(lsdd);
        wklistView.setCellFactory(CheckBoxListCell.forListView((worCollection item) -> {
            BooleanProperty observable = item.completedProperty();
            observable.addListener((obs, wasSelected, isNowSelected) -> {
                System.out.println("Check box for " + item.getRiTypeCode() + " changed from " + wasSelected + " to " + isNowSelected);
                if (isNowSelected) {
                    selectedReturns.add(item);
                } else {
                    selectedReturns.remove(item);
                }

                if (item.getTitle().equals("ALL") && isNowSelected) {
                    selectedReturns.clear();
                    for (int i = 1; i < wklistView.getItems().size(); i++) {
                        ((worCollection) wklistView.getItems().get(i)).setCompleted(true);
                        System.out.println("(Returns)wklistView.getItems().get(i) " + (worCollection) wklistView.getItems().get(i));
                        selectedReturns.add(((worCollection) wklistView.getItems().get(i)));
                    }
                } else if (item.getTitle().equals("ALL") && !isNowSelected) {
                    selectedReturns.clear();
                    for (int i = 1; i < wklistView.getItems().size(); i++) {
                        ((worCollection) wklistView.getItems().get(i)).setCompleted(false);
                    }
                }
                System.out.println(" checkbox size " + selectedReturns.size());
            }
            );

            return observable;
        }, converter));
    }

    public static List<worCollection> getListofWorkCollections(String riType) {
        List<worCollection> ls = null;
        List<TRtnWorkCollectionDefn> worColls;
        try {
            if (riType == null || riType.isEmpty()) {
                worColls = new WorkCollectionDAO().getWorkCollection();
            } else {
                worColls = new WorkCollectionDAO().getWorkCollection(riType);
            }
            ls = new ArrayList();
            ls.add(new worCollection("ALL", "", false));
            for (int i = 0; i < worColls.size(); i++) {
                ls.add(new worCollection(worColls.get(i).getDescription().trim(), worColls.get(i).getWorkCollectionCode(), false));
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

    public static class worCollection {

        private final StringProperty title = new SimpleStringProperty();
        private final BooleanProperty completed = new SimpleBooleanProperty();
        private final StringProperty riTypeCode = new SimpleStringProperty();

        public worCollection(String title, String riTypeCode, boolean completed) {
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
