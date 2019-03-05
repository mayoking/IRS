/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.dao.WorkCollectionDAO;
import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.deploy.Messages;
import static com.bbt.irs.deploy.Messages.FXMLPATH;
import com.bbt.irs.entity.TRtnWorkCollectionDefn;
import com.bbt.irs.util.Utility;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;

/**
 *
 * @author tkola
 */
public class WorkCollRiTypeMapChildController implements Initializable, Messages {

    private static final org.apache.log4j.Logger LOGGER = LogManager.getLogger(WorkCollRiTypeMapChildController.class);
    @FXML
    TableView<RiTypeDataModel> workCollFromRiType;
    @FXML
    TableColumn<RiTypeDataModel, String> idCol;
    @FXML
    TableColumn<RiTypeDataModel, String> worColCodeCol;
    @FXML
    TableColumn<RiTypeDataModel, String> frequencyCol;
    @FXML
    TableColumn<RiTypeDataModel, String> descriptionCol;
    @FXML
    TableColumn<RiTypeDataModel, String> createdByCol;
    @FXML
    TableColumn<RiTypeDataModel, String> createdDateCol;//dateofStartValDate

    public WorkCollRiTypeMapChildController workCollRiTypeMapChildController;
    public static WorkCollRiTypeMapChildController instance;
    List<TRtnWorkCollectionDefn> aList;
    Stage dissociatingStage;
    List<WorkCollRiTypeMapChildController.RiTypeDataModel> ls;

    public void WorkCollRiTypeMapChildController() {
        instance = this;
    }
    ObservableList<WorkCollRiTypeMapChildController.RiTypeDataModel> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCol();
        loadData();
        instance = this;
    }

    @FXML
    public void handleDissociateOption() {
        try {
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("/library/assistant/ui/addbook/syncadd.fxml"));
            FXMLLoader loader = new FXMLLoader();
            Parent root = (Parent) loader.load(getClass().getResourceAsStream(FXMLPATH + "dissociatericontroller.fxml"));

            DissociateRIController controller = (DissociateRIController) loader.getController();

            dissociatingStage = new Stage(StageStyle.DECORATED);
            dissociatingStage.setTitle("Dissociate Work Collection From RI Type");
            dissociatingStage.setScene(new Scene(root));
            dissociatingStage.show();

            dissociatingStage.setOnCloseRequest((e) -> {
                handleRefresh();
            });

        } catch (IOException ex) {
            IRSDialog.showAlert("ERROR", "Error,Unable to load page");
            LOGGER.log(Level.ERROR, ex);
        }
    }

    @FXML
    public void handleRefresh() {
        loadData();
    }

    private void initCol() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        worColCodeCol.setCellValueFactory(new PropertyValueFactory<>("worColCode"));
        frequencyCol.setCellValueFactory(new PropertyValueFactory<>("frequency"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        createdByCol.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        createdDateCol.setCellValueFactory(new PropertyValueFactory<>("createdDate"));

    }

    public void loadData() {
        try {
            list.clear();
            String ritypecode = WorkCollRiTypeMappingController.instance.riTypeCollection.getSelectionModel().getSelectedItem().getRiTypeCode();
            System.out.println("v " + ritypecode);
            if (ritypecode == null) {
                aList = new WorkCollectionDAO().getWorkCollection();
            } else {
                aList = new WorkCollectionDAO().getWorkCollection(ritypecode);
            }
            ls = new ArrayList();
            for (TRtnWorkCollectionDefn s : aList) {
                WorkCollRiTypeMapChildController.RiTypeDataModel changeTableDataModel = new WorkCollRiTypeMapChildController.RiTypeDataModel(String.valueOf(s.getWorkCollectionId()), s.getWorkCollectionCode(), s.getFrequency().getFreqDesc(), s.getDescription(), s.getCreatedBy(), Utility.convertString2date(s.getCreatedDate(), "dd-MM-yyyy"));
                ls.add(changeTableDataModel);
            }

            System.out.println("alist " + aList.size());
            for (WorkCollRiTypeMapChildController.RiTypeDataModel s : ls) {
                list.add(s);
            }

            workCollFromRiType.setItems(list);

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public static class RiTypeDataModel {

        private final StringProperty id;
        private final StringProperty worColCode;
        private final StringProperty frequency;
        private final StringProperty description;
        private final StringProperty createdBy;
        private final StringProperty createdDate;

        public String getId() {
            return id.get();
        }

        public String getWorColCode() {
            return worColCode.get();
        }

        /**
         * @return the createdBy
         */
        public String getCreatedBy() {
            return createdBy.get();
        }

        /**
         * @return the status
         */
        public String getFrequency() {
            return frequency.get();
        }

        /**
         * @return the typeChanges
         */
        public String getDescription() {
            return description.get();
        }

        public String getCreatedDate() {
            return createdDate.get();
        }

        public RiTypeDataModel(String id, String worColCode, String frequency, String description, String createdBy, String createdDate) {
            this.id = new SimpleStringProperty(id);
            this.worColCode = new SimpleStringProperty(worColCode);
            this.frequency = new SimpleStringProperty(frequency);
            this.description = new SimpleStringProperty(description);
            this.createdDate = new SimpleStringProperty(createdDate);
            this.createdBy = new SimpleStringProperty(createdBy);

        }

    }
}
