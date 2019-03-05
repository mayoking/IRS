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
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

/**
 * FXML Controller class
 *
 * @author tkola
 */
public class WorkColRiTypeMappingController implements Initializable {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(WorkColRiTypeMappingController.class);
    @FXML
    ListView wklistView;
    @FXML
    ComboBox<TRtnWorkCollectionDefn> workCollection;
    List<TRtnWorkCollectionDefn> ls = new ArrayList();
    @FXML
    Button okButton;
    @FXML
    Button wkCancel;
    List<WorkColRiTypeMappingController.RiType> selectedReturns = new ArrayList();
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
            ls = new WorkCollectionDAO().getWorkCollection();
            workCollection.setItems(FXCollections.observableArrayList(ls));
        } catch (Exception ex) {
            Logger.getLogger(WorkColRiTypeMappingController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        initializeListView();
        addListener2eworkCol();
    }

    public void initializeListView() {
        StringConverter<RiType> converter;
        converter = new StringConverter<RiType>() {
            @Override
            public String toString(RiType document) {
                return document.getTitle();
            }

            // not actually used by CheckBoxListCell
            @Override
            public RiType fromString(String string) {
                return null;
            }
        };
        List<RiType> totalList = getListofRitypes();
        wklistView.getItems().addAll(totalList);
        wklistView.setCellFactory(CheckBoxListCell.forListView((RiType item) -> {
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
                        ((RiType) wklistView.getItems().get(i)).setCompleted(true);
                        System.out.println("(Returns)wklistView.getItems().get(i) " + (RiType) wklistView.getItems().get(i));
                        selectedReturns.add(((RiType) wklistView.getItems().get(i)));
                    }
                } else if (item.getTitle().equals("ALL") && !isNowSelected) {
                    selectedReturns.clear();
                    for (int i = 1; i < wklistView.getItems().size(); i++) {
                        ((RiType) wklistView.getItems().get(i)).setCompleted(false);
                    }
                }
                System.out.println(" checkbox size " + selectedReturns.size());
                 System.out.println(" checkbox size " + selectedReturns);
            }
            );

            return observable;
        }, converter));
    }

    public void addListener2eworkCol() {
        workCollection.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<TRtnWorkCollectionDefn>() {
            @Override
            public void changed(ObservableValue observable, TRtnWorkCollectionDefn oldValue, TRtnWorkCollectionDefn newValue) {
                try {
                    List<RiType> ritypes = getListofRitypes(newValue.getWorkCollectionId());
                    selectedReturns.clear();                  
                    clearSelectionList();
                    wklistView.getSelectionModel().clearSelection();
                    for (int i = 1; i < wklistView.getItems().size(); i++) {

                        System.out.println("(Returns)wklistView.getItems().get(i) " + (RiType) wklistView.getItems().get(i));
                        System.out.println("ritypes "+ritypes);
                        for(RiType s :  ritypes  ){
                            System.out.println("s.getRiTypeCode() "+s);
                        if (s.toString().equals((String)wklistView.getItems().get(i).toString())) {
                            System.out.println("s inside "+s);
                            ((RiType) wklistView.getItems().get(i)).setCompleted(true);
                           // selectedReturns.add(((RiType) wklistView.getItems().get(i)));
                        }
                        }
                    }

                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        }
        );
    }
    
    public void clearSelectionList(){
         
         for (int i = 0; i < wklistView.getItems().size(); i++) {
             ((RiType) wklistView.getItems().get(i)).setCompleted(false);
         }
    }

    public static List<RiType> getListofRitypes() {
        List<RiType> ls = null;
        try {
            List<TCoreRiType> riType = new RITypeDAO().getRIype();
            ls = new ArrayList();
            ls.add(new RiType("ALL", "", false));
            for (int i = 0; i < riType.size(); i++) {
                ls.add(new RiType(riType.get(i).getDescription().trim(), riType.get(i).getRiTypeCode(), false));
            }

        } catch (Exception ex) {
            LOGGER.log(Level.ERROR, ex);
        }
        return ls;
    }

    public static List<RiType> getListofRitypes(Integer workcollID) {
        List<RiType> ls = null;
        try {
            List<TCoreRiType> riType = new RITypeDAO().getRIype(workcollID);
            ls = new ArrayList();
            ls.add(new RiType("ALL", "", false));
            for (int i = 0; i < riType.size(); i++) {
                ls.add(new RiType(riType.get(i).getDescription().trim(), riType.get(i).getRiTypeCode(), false));
            }
            System.out.println("size "+ls.size());

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
            
            boolean result = new RITypeDAO().linkworkcoll2RiTypes(selectedReturns, workCollection.getSelectionModel().getSelectedItem().getWorkCollectionCode());
            if (result) {
                IRSDialog.showAlert("Error", "Ri Types associated with work collection successfully. ");
            } else {
                IRSDialog.showAlert("Error", "Error while associating work collection with ritype ");
            }
        } catch (DatabaseException ex) {
            ex.printStackTrace();
            IRSDialog.showAlert("Error", "Error while associating work collection with ritypes ");
            Logger.getLogger(WorkColRiTypeMappingController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }

    @FXML
    public String cancelAction() {
        this.isOkClicked = false;
        stageDialog.close();
        return null;
    }

    public static class RiType {

        private final StringProperty title = new SimpleStringProperty();
        private final BooleanProperty completed = new SimpleBooleanProperty();
        private final StringProperty riTypeCode = new SimpleStringProperty();

        public RiType(String title, String riTypeCode, boolean completed) {
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
        
        @Override
        public String toString(){
            
            return getRiTypeCode();
        }

    }

}
