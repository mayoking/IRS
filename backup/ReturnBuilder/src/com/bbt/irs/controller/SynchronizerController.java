/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.dao.MainDAO;
import static com.bbt.irs.deploy.ErrorNameDesc.ERROR;
import static com.bbt.irs.deploy.ErrorNameDesc.PROCESSINEXCEPTION;
import com.bbt.irs.deploy.IRS;
import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.deploy.Messages;
import static com.bbt.irs.deploy.Messages.FXMLPATH;
import static com.bbt.irs.deploy.Messages.INFO;
import com.bbt.irs.entity.TSctDbChanges;
import com.bbt.irs.exception.DatabaseException;
import com.bbt.irs.rest.client.RBRestClient;
import com.bbt.irs.util.Utility;
import com.bbt.irs.vo.GenWorCollVO;
import com.bbt.irs.vo.ResponseVO;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.ws.rs.ProcessingException;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.controlsfx.dialog.ProgressDialog;

/**
 *
 * @author tkola
 */
public class SynchronizerController implements Initializable, Messages {

    private static final org.apache.log4j.Logger LOGGER = LogManager.getLogger(SynchronizerController.class);
    @FXML
    TableView<ChangeTableDataModel> changeSynchronizer;
    @FXML
    TableColumn<ChangeTableDataModel, String> changeTypeCol;
    @FXML
    TableColumn<ChangeTableDataModel, String> changeCodeCol;
    @FXML
    TableColumn<ChangeTableDataModel, String> dateofChangeCol;
    @FXML
    TableColumn<ChangeTableDataModel, String> statusCol;
    @FXML
    TableColumn<ChangeTableDataModel, String> changeByCol;//dateofStartValDate
    @FXML
    TableColumn<ChangeTableDataModel, String> dateofStartValDateCol;//
    @FXML
    TableColumn<ChangeTableDataModel, String> changeIdCol;
    @FXML
    Button synchronize;
    boolean result = true;

    //ObservableList<ChangeTableDataModel> list = FXCollections.observableArrayList();
    List<TSctDbChanges> aList;
    List<ChangeTableDataModel> ls;
    ObservableList<ChangeTableDataModel> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            initCol();
            loadData();
        } catch (DatabaseException ex) {
            IRSDialog.showAlert("Error", ex.getMessage());
            LOGGER.log(Level.ERROR, ex);
        }

    }

    public void loadData() throws DatabaseException {
        try {
            list.clear();
            aList = new MainDAO().getTSctDbChanges();
            ls = new ArrayList();

            for (TSctDbChanges s : aList) {
                ChangeTableDataModel changeTableDataModel = new ChangeTableDataModel(s.getChangeCode(), s.getTypeChanges().getTypeDesc(), s.getStatus().getStatusDesc(), Utility.convertString2date(s.getChangeDate(), "dd-MM-yyyy"), s.getCreatedBy(), s.getModifiedBy(), Utility.convertString2date(s.getLastModified(), "dd-MM-yyyy"), Utility.convertString2date(s.getValidityDate(), "dd-MM-yyyy"), String.valueOf(s.getChangeId()));
                ls.add(changeTableDataModel);
            }

            System.out.println("alist " + aList.size());
            for (ChangeTableDataModel s : ls) {
                list.add(s);
            }

            changeSynchronizer.setItems(list);

        } catch (DatabaseException ex) {
            result = false;
            LOGGER.log(Level.ERROR, ex);
            throw new DatabaseException(ex.getMessage());

        } catch (Exception ex) {
            LOGGER.log(Level.ERROR, ex);
            throw new DatabaseException("Error while synchronizing ");
        }
    }

    public Task createWorker() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                String result = null;
                GenWorCollVO genWorCollVO = new GenWorCollVO();
                try {
                    genWorCollVO.setUsername(IRS.getInstance().username);
                    RBRestClient rbRestClient = new RBRestClient();
                    ResponseVO res = rbRestClient.synchronize(genWorCollVO);
                    if (res.getResponseCode() == 0) {
                        IRSDialog.showAlert("Info", "Data Successfully Synchronised.");
                    } else {
                        IRSDialog.showAlert("ERROR", res.getResponseDesc());
                        LOGGER.log(Level.ERROR, res.getResponseDesc());
                    }
                } catch (ProcessingException ex) {
                    IRSDialog.showAlert(ERROR, PROCESSINEXCEPTION);
                    LOGGER.log(Level.FATAL, PROCESSINEXCEPTION);
                } catch (Exception ex) {
                    IRSDialog.showAlert(ERROR, ex.getMessage());
                    LOGGER.log(Level.FATAL, ex);
                } finally {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            synchronize.setStyle("-fx-background-color: #afcce0;");
                            synchronize.setDisable(false);

                        }
                    });

                }
                return result;

            }
        };

    }

    public void progressModal() {
        Task copyWorker = createWorker();
        ProgressDialog dialog = new ProgressDialog(copyWorker);
        dialog.initStyle(StageStyle.TRANSPARENT);

        dialog.setGraphic(null);
        dialog.initStyle(StageStyle.TRANSPARENT);
        dialog.setHeaderText(null);
        dialog.setGraphic(null);
        dialog.initStyle(StageStyle.UTILITY);
        new Thread(copyWorker).start();
        dialog.showAndWait();
    }

    @FXML
    private void synchronize() {
        if (result) {
//        synchronize.setStyle("-fx-background-color: #ff0000;");
//        synchronize.setDisable(true);
//        Task synchronize = createWorker();
//        new Thread(synchronize).start();
            progressModal();
        } else {
            IRSDialog.showAlert(INFO, "No data to synchronize");
        }
    }

    @FXML
    private void extendReleaseDate() throws DatabaseException {

        try {
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("/library/assistant/ui/addbook/syncadd.fxml"));
            FXMLLoader loader = new FXMLLoader();
            Parent root = (Parent) loader.load(getClass().getResourceAsStream(FXMLPATH + "extendreleasedate.fxml"));

            ReleasedateController controller = (ReleasedateController) loader.getController();

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Change Releae Date");
            stage.setScene(new Scene(root));
            stage.show();

            stage.setOnCloseRequest((e) -> {
                handleRefresh(new ActionEvent());
            });

        } catch (IOException ex) {
            IRSDialog.showAlert("ERROR", "Error,Unable to extend relesase date");
            LOGGER.log(Level.ERROR, ex);
        }
    }

    @FXML
    private void handleChangeEditOption(ActionEvent event) {
        //Fetch the selected row
        ChangeTableDataModel selectedForEdit = changeSynchronizer.getSelectionModel().getSelectedItem();
        if (selectedForEdit == null) {
            IRSDialog.showAlert("No Item selected", "Please select an item for edit.");
            return;
        }
        try {
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("/library/assistant/ui/addbook/syncadd.fxml"));
            FXMLLoader loader = new FXMLLoader();
            Parent root = (Parent) loader.load(getClass().getResourceAsStream(FXMLPATH + "syncadd.fxml"));
            //Parent parent = loader.load();

            SyncAddController controller = (SyncAddController) loader.getController();
            controller.inflateUI(selectedForEdit);

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Edit Request");
            stage.setScene(new Scene(root));
            stage.show();
            //LibraryAssistantUtil.setStageIcon(stage);

            stage.setOnCloseRequest((e) -> {
                handleRefresh(new ActionEvent());
            });

        } catch (IOException ex) {
            IRSDialog.showAlert("Error", "Unable to handle edit");
            LOGGER.log(Level.ERROR, ex);
        }
    }

    @FXML
    private void handleRefresh(ActionEvent event) {
        try {
            loadData();
        } catch (DatabaseException ex) {
            IRSDialog.showAlert("Error", "Unable to handle refresh");
            LOGGER.log(Level.ERROR, ex);
        }
    }

    private void initCol() {
        changeTypeCol.setCellValueFactory(new PropertyValueFactory<>("typeChanges"));
        changeCodeCol.setCellValueFactory(new PropertyValueFactory<>("changeCode"));
        dateofChangeCol.setCellValueFactory(new PropertyValueFactory<>("changeDate"));
        changeByCol.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        dateofStartValDateCol.setCellValueFactory(new PropertyValueFactory<>("startValDate"));
        changeIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    }

    public static class ChangeTableDataModel {

        /**
         * @return the changeCode
         */
        public String getChangeCode() {
            return changeCode.get();
        }

        /**
         * @return the changeDate
         */
        public String getChangeDate() {
            return changeDate.get();
        }

        /**
         * @return the createdBy
         */
        public String getCreatedBy() {
            return createdBy.get();
        }

        /**
         * @return the modifiedBy
         */
        public String getModifiedBy() {
            return modifiedBy.get();
        }

        /**
         * @return the status
         */
        public String getStatus() {
            return status.get();
        }

        /**
         * @return the typeChanges
         */
        public String getTypeChanges() {
            return typeChanges.get();
        }

        public String getStartValDate() {
            return startValDate.get();
        }

        public String getId() {
            return id.get();
        }

        /**
         * @param modifiedBy the modifiedBy to set
         */
        public void setModifiedBy(StringProperty modifiedBy) {
            this.modifiedBy = modifiedBy;
        }

        private final StringProperty changeCode;
        private final StringProperty changeDate;
        private final StringProperty createdBy;
        private StringProperty modifiedBy;
        private final StringProperty status;
        private final StringProperty typeChanges;
        private final StringProperty startValDate;
        private final StringProperty id;

        public ChangeTableDataModel(String changeCode, String typeChanges, String status, String changeDate, String createdBy, String modifiedBy, String lastModified, String startValDate, String id) {
            this.changeCode = new SimpleStringProperty(changeCode);
            this.typeChanges = new SimpleStringProperty(typeChanges);
            this.changeDate = new SimpleStringProperty(changeDate);
            this.status = new SimpleStringProperty(status);
            this.createdBy = new SimpleStringProperty(createdBy);
            this.modifiedBy = new SimpleStringProperty(modifiedBy);//lastModified
            this.startValDate = new SimpleStringProperty(startValDate);
            this.id = new SimpleStringProperty(id);
            System.out.println("typeChanges " + typeChanges);
//        this.modifiedBy = new SimpleStringProperty(lastModified);

        }

    }

}
