/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.dao.MetadataTableDAO;
import static com.bbt.irs.deploy.ErrorNameDesc.ERROR;
import static com.bbt.irs.deploy.ErrorNameDesc.FIELD_BLANK;
import com.bbt.irs.deploy.IRS;
import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.entity.MetadataTable;
import com.bbt.irs.entity.TRbDatasize;
import com.bbt.irs.entity.TRbDatatype;
import com.bbt.irs.entity.TRtnReturns;
import com.bbt.irs.exception.WebservicesException;
import com.bbt.irs.rest.client.RBRestClient;
import com.bbt.irs.ui.CompositeHBox;
import com.bbt.irs.ui.CompositeVBox;
import com.bbt.irs.util.Utility;
import com.bbt.irs.vo.ColumnVO;
import com.bbt.irs.vo.ResponseVO;
import com.bbt.irs.vo.TableModificationVO;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.controlsfx.dialog.ProgressDialog;

/**
 *
 * @author tkola
 */
public class ModifyTemplateController implements Initializable {

    /**
     * @return the instance
     */
    public static ModifyTemplateController getInstance() {
        return instance;
    }

    /**
     * @param aInstance the instance to set
     */
    public static void setInstance(ModifyTemplateController aInstance) {
        instance = aInstance;
    }

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(WorkColRiTypeMappingController.class);

    private static ModifyTemplateController instance;
    @FXML
    ComboBox<String> tables;
    @FXML
    Label mod;
    @FXML
    ComboBox tempMod;
    List<TRtnReturns> ls = new ArrayList();
    String returnCode;
    String workCollectionCode;
    Integer counter = 0;
    HBox comboHbox;
    HBox btnsHbox;
    VBox colVbox;
    VBox overallVbox = new VBox();
    Button submitButton = new Button("Submit");
    Button submitButton2 = new Button("Submit");
    Button backButton = new Button("Remove");
    Button backButton2 = new Button("Remove");
    GridPane subroot = new GridPane();

    AnchorPane root = new AnchorPane();
    AnchorPane rootnode = new AnchorPane();
    BorderPane borderPane = new BorderPane();
    ScrollPane scrollPane = new ScrollPane();
    @FXML
    TextField cols;
    String submitID;
    private List<CompositeVBox> lsVbox;
    private List<CompositeHBox> lsHbox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            root = new AnchorPane();
            cols.setVisible(false);
            //cols.getText().replaceAll("[A-Za-z]", "");
            cols.setTextFormatter(new TextFormatter<>((change) -> {
            change.setText(change.getText().toUpperCase().replaceAll("[A-Za-z ]", "").replace(" ", "_").replace("*", ""));
            return change;
        }));
            mod.setVisible(false);
            comboHbox = new HBox();
            btnsHbox = new HBox();
            colVbox = new VBox();
            btnsHbox.getStyleClass().add("hbox");
            comboHbox.getStyleClass().add("hbox");
            btnsHbox.getChildren().addAll(backButton, submitButton);
            subroot.getChildren().clear();
            returnCode = ModificationUIController.instance.returns.getSelectionModel().getSelectedItem().getReturnCode();
            workCollectionCode = ModificationUIController.instance.workCollection.getSelectionModel().getSelectedItem().getWorkCollectionCode();
            List<String> lsTables = new MetadataTableDAO().getNumberTables(returnCode);
            tables.setItems(FXCollections.observableList(lsTables));
            tempMod.getItems().addAll("ADD COLUMN", "MODIFY COLUMN");
            addListener2Modify();
            addAction2Submit();
            addAction2Submit2();
            instance = this;
            lsVbox = new ArrayList();
            lsHbox = new ArrayList();
            //resetcontainers();
        } catch (Exception ex) {
            LOGGER.log(Level.ERROR, ex);
        }
    }

    public void addAction2Submit() {
        submitButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Task createWorker1 = createWorker();
                progressModal(createWorker1);
                System.out.println("progress modal called");
            }//TableModificationVO
        });
    }

    public void addAction2Submit2() {
        submitButton2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Task createWorker2 = createWorker2();
                progressModal(createWorker2);

            }//TableModificationVO
        });
    }

//    public boolean validateInput() {
//        boolean result = false;
//
//        return result;
//    }
    
     public   void progressModal(Task worker) {
        Task copyWorker = worker;
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

    public Task createWorker() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                String tableName = tables.getSelectionModel().getSelectedItem();
                ResponseVO res = null;
                try {
                    if (validateHeaders(lsVbox, tableName).equalsIgnoreCase("s")) {
                        System.out.println("after validation ");
                        TableModificationVO vo = new TableModificationVO();
                        vo.setReturnCode(returnCode);
                        vo.setTableName(tableName);
                        vo.setWorkCollectionCode(workCollectionCode);
                        vo.setUsername(IRS.getInstance().username);
                        System.out.println("username "+IRS.getInstance().username);
                        List<ColumnVO> colvos = new ArrayList();
                        for (int i = 0; i < lsVbox.size(); i++) {
                            System.out.println("I am inside vbox "+i);
                            ColumnVO colvo = new ColumnVO();
                            CompositeVBox vbox = lsVbox.get(i);
                            colvo.setColumnName(vbox.getCellName().getText());
                            colvo.setExcelHeader(vbox.getCellNo().getText());
                            colvo.setColumnName(vbox.getCellName().getText());
                            colvo.setDataSize((TRbDatasize) vbox.getDataSizeCombo().getSelectionModel().getSelectedItem());
                            colvo.setDataType((TRbDatatype) vbox.getDataTypeCombo().getSelectionModel().getSelectedItem());
                            colvos.add(colvo);
                        }
                        vo.setLs(colvos);
                        System.out.println("About to call web service ");
                        RBRestClient rbRestClient = new RBRestClient();
                        res = rbRestClient.addTemplateField(vo);
                        System.out.println("response frommweb service "+res);
                        Utility.updateUIThread("Info", res.getResponseDesc());
                         loadstartingPoint();
                    }
                } catch (WebservicesException webservicesException) {
                    webservicesException.printStackTrace();
                    LOGGER.error(webservicesException);
                    Utility.updateUIThread(ERROR, "Error while processing the request");
                } catch (Exception ex) {
                    LOGGER.error(ex);
                    Utility.updateUIThread(ERROR, "Fatal Error while processing the request");
                }
                return res;
            }

        };
    }

    public Task createWorker2() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                System.out.println("running create worker 2 ----- ");
                String tableName = tables.getSelectionModel().getSelectedItem();
                ResponseVO res = null;
                try {
                    lsHbox = validateHheaders(lsHbox, tableName);
                    if (lsHbox.size() > 0) {
                        TableModificationVO vo = new TableModificationVO();
                        vo.setReturnCode(returnCode);
                        vo.setTableName(tableName);
                        vo.setWorkCollectionCode(workCollectionCode);
                        vo.setUsername(IRS.getInstance().username);
                        List<ColumnVO> colvos = new ArrayList();
                        for (int i = 0; i < lsHbox.size(); i++) {
                            ColumnVO colvo = new ColumnVO();
                            CompositeHBox vbox = lsHbox.get(i);
                            colvo.setColumnName(vbox.getFieldName().getText());
                            colvo.setDataSize((TRbDatasize) vbox.getFieldDataSizeDropdown().getSelectionModel().getSelectedItem());
                            colvo.setDataType((TRbDatatype) vbox.getFieldDataTypeDropdown().getSelectionModel().getSelectedItem());
                           
                            colvos.add(colvo);
                        }
                        vo.setLs(colvos);
                        RBRestClient rbRestClient = new RBRestClient();
                        res = rbRestClient.modifyTemplateField(vo);
                        Utility.updateUIThread("Info", res.getResponseDesc());
                        loadstartingPoint();
                    }
                } catch (WebservicesException webservicesException) {
                    webservicesException.printStackTrace();
                    LOGGER.error(webservicesException);
                    Utility.updateUIThread(ERROR, "Error while processing the request");
                } catch (Exception ex) {
                    LOGGER.error(ex);
                    Utility.updateUIThread(ERROR, "Fatal Error while processing the request");
                }
                return res;
            }

        };
    }
    
    public void loadstartingPoint(){
          Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MainController.getInstance().loadUpdateXml();
                } catch (IOException ex) {
                    Logger.getLogger(ModifyTemplateController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }

            }
        });
    }

    public List<CompositeHBox> validateHheaders(List<CompositeHBox> colInfo, String tableName) {
        String result = "s";
        List<CompositeHBox> ls = new ArrayList();
        for (int i = 0; i < colInfo.size(); i++) {
            CompositeHBox vbox = colInfo.get(i);
            boolean res = validateCompositeHBox(vbox);
            if (res) {
                ls.add(vbox);
            }
        }
        return ls;
    }

    public String validateHeaders(List<CompositeVBox> colInfo, String tableName) {
        String result = "s";
        //List<CompositeVBox> colInfo = tablevo.getColInfo();

        if (cols.getText() == null) {
            result = "Field for number of columns is blank";
            return result;
        }
        if (!StringUtils.isNumeric(cols.getText())) {
            result = "The field for number columns contains other leters that are not numeric";
            return result;
        }
        for (int i = 0; i < colInfo.size(); i++) {
            CompositeVBox vbox = colInfo.get(i);
            if (!validateCompositeBox(vbox)) {
                result = "Cell " + vbox.getCellNo().getText() + "is blank";
                return result;
            }
            String columnHeader = vbox.getCellName().getText();
            if (!columnHeader.matches("^[aA-zZ]+([aA-zZ][0-9]_)?")) {
                result = "Cell " + vbox.getCellNo().getText() + "contains illegal character";
                return result;
            }
            for (int j = i + 1; j < colInfo.size(); j++) {
                CompositeVBox vboxInner = colInfo.get(i + 1);
                String columnHeaderInner = vboxInner.getCellName().getText();
                if (columnHeader.equals(columnHeaderInner)) {
                    result = "Cell " + vbox.getCellNo().getText() + " is a duplicate of " + vboxInner.getCellNo().getText() + "in selection " + tableName;
                    return result;
                }
            }
        }
        return result;
    }

    public boolean validateCompositeHBox(CompositeHBox compositeHBox) {
        boolean result = true;
        if (compositeHBox.getFieldDataTypeDropdown() == null || compositeHBox.getFieldDataTypeDropdown().getSelectionModel() == null || compositeHBox.getFieldDataTypeDropdown().getSelectionModel().getSelectedItem() == null) {
            result = false;
        }

        return result;
    }

    private boolean validateCompositeBox(CompositeVBox compositeVBox) {

        if (compositeVBox.getCellNo().getText() == null || compositeVBox.getCellNo().getText().isEmpty()) {
            IRSDialog.showAlert(ERROR, FIELD_BLANK);
            return false;

        }
        if (compositeVBox.getCellName().getText() == null || compositeVBox.getCellName().getText().isEmpty()) {
            IRSDialog.showAlert(ERROR, FIELD_BLANK);
            return false;

        }

        System.out.println("compositeVBox.getCellNo() " + compositeVBox.getCellNo());
        if (compositeVBox.getDataTypeCombo() == null || compositeVBox.getDataTypeCombo().getSelectionModel() == null || compositeVBox.getDataTypeCombo().getSelectionModel().getSelectedItem() == null) {
            IRSDialog.showAlert("Data Type", FIELD_BLANK);
            System.out.println("This is combo 1 " + compositeVBox.getDataTypeCombo());
            System.out.println("This is combo1.getSelectionModel() 1 " + compositeVBox.getDataTypeCombo().getSelectionModel());
            return false;
        }

        if (compositeVBox.getCellName().getText() == null || compositeVBox.getCellName().getText().isEmpty()) {
            IRSDialog.showAlert("Cell Name", FIELD_BLANK);
            return false;

        }

        if (compositeVBox.getDataTypeCombo() == null || compositeVBox.getDataTypeCombo().getSelectionModel() == null || compositeVBox.getDataTypeCombo().getSelectionModel().getSelectedItem() == null) {
            IRSDialog.showAlert("Data Size", FIELD_BLANK);
            return false;
        }
        System.out.println("this is the result ");
        return true;
    }

    public void addListener2Modify() {
        tempMod.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue observable, String oldValue, String newValue) {
                switch (newValue) {
                    case "ADD COLUMN":
                        cols.setVisible(true);
                        mod.setVisible(true);
                        submitID = "ADD COLUMN";
                        break;
                    case "MODIFY COLUMN":
                        cols.setVisible(false);
                        mod.setVisible(false);
                        submitID = "MODIFY COLUMN";
                        break;
                }

            }
        }
        );
    }

    @FXML
    public void submit(ActionEvent event) {
        if (submitID.equals("ADD COLUMN")) {
            generateAddFieldUI();
        } else if (submitID.equals("MODIFY COLUMN")) {
            generateModFieldUI();
        }
    }

    public void resetcontainers() {
        btnsHbox.getChildren().clear();
        colVbox.getChildren().clear();
        overallVbox.getChildren().clear();
        subroot.getChildren().clear();
        borderPane.getChildren().clear();
        rootnode.getChildren().clear();
        root.getChildren().clear();
    }

    public void generateModFieldUI() {
        try {
            resetcontainers();
            btnsHbox.getChildren().addAll(backButton2, submitButton2);
            String table = tables.getSelectionModel().getSelectedItem();
            System.out.println("inside generateModFieldUI submit ");
            List<MetadataTable> ls = new MetadataTableDAO().getmetadataList(table);
            for (int i = 0; i < ls.size(); i++) {
                CompositeHBox compositeHBox = new CompositeHBox("Field Name", "Filed Data Type", "Data Type","Data Size", i);
                System.out.println("ls.get(i).getHeaderDesc() "+ls.get(i).getHeaderDesc());
                compositeHBox.getFieldName().setText(ls.get(i).getHeaderDesc());
                compositeHBox.getFieldDataType().setText(ls.get(i).getDatatypeId().getDatatype());

                compositeHBox.getFieldDataTypeDropdown().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TRbDatatype>() {
                    @Override
                    public void changed(ObservableValue observable, TRbDatatype oldValue, TRbDatatype newValue) {
                        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhh");
                        if (newValue.getDatatype().equalsIgnoreCase(compositeHBox.getFieldDataType().getText())) {
                            IRSDialog.showAlert(ERROR, "Selected data type can not be the same wirh original data type");
                            compositeHBox.getFieldDataTypeDropdown().getSelectionModel().clearSelection();
                        } else if (compositeHBox.getFieldDataType().getText().equalsIgnoreCase("varchar") && (newValue.getDatatype().equalsIgnoreCase("numeric") || newValue.getDatatype().equalsIgnoreCase("datetime"))) {
                            IRSDialog.showAlert(ERROR, "conversion from original data type to selected data type is not allowed");
                            compositeHBox.getFieldDataTypeDropdown().getSelectionModel().clearSelection();

                        } else if (compositeHBox.getFieldDataType().getText().equalsIgnoreCase("datetime") && (newValue.getDatatype().equalsIgnoreCase("numeric") || newValue.getDatatype().equalsIgnoreCase("varchar"))) {
                            IRSDialog.showAlert(ERROR, "conversion from original data type to selected data type is not allowed");
                            compositeHBox.getFieldDataTypeDropdown().getSelectionModel().clearSelection();

                        } else if (compositeHBox.getFieldDataType().getText().equalsIgnoreCase("numeric") && (newValue.getDatatype().equalsIgnoreCase("datetime"))) {
                            IRSDialog.showAlert(ERROR, "conversion from original data type to selected data type is not allowed");
                            compositeHBox.getFieldDataTypeDropdown().getSelectionModel().clearSelection();

                        }
                    }
                }
                );
                colVbox.getChildren().add(compositeHBox.gethContainer());
                lsHbox.add(compositeHBox);

            }

            overallVbox.getChildren().addAll(colVbox, btnsHbox);
            subroot.getChildren().add(overallVbox);
            borderPane.setTop(subroot);
            subroot.setAlignment(Pos.CENTER);
            rootnode.getChildren().add(borderPane);
            AnchorPane.setTopAnchor(rootnode, 0.0);
            AnchorPane.setRightAnchor(rootnode, 0.0);
            AnchorPane.setBottomAnchor(rootnode, 0.0);
            AnchorPane.setLeftAnchor(rootnode, 0.0);
            AnchorPane.setTopAnchor(borderPane, 0.0);
            AnchorPane.setRightAnchor(borderPane, 0.0);
            AnchorPane.setBottomAnchor(borderPane, 0.0);
            AnchorPane.setLeftAnchor(borderPane, 0.0);
            AnchorPane.setTopAnchor(scrollPane, 0.0);
            AnchorPane.setRightAnchor(scrollPane, 0.0);
            AnchorPane.setBottomAnchor(scrollPane, 0.0);
            AnchorPane.setLeftAnchor(scrollPane, 0.0);
            //rootnode.setPrefWidth(1150.0);
            scrollPane.setContent(rootnode);
            root.getChildren().add(scrollPane);
            counter++;
            MainController.getInstance().loadModificationUI();
        } catch (Exception ex) {
            IRSDialog.showAlert("Error", "Unable to load New Table Headers");
            LOGGER.error(Level.ERROR, ex);
        }
    }

    public void generateAddFieldUI() {
        try {
            resetcontainers();
            btnsHbox.getChildren().addAll(backButton, submitButton);
            System.out.println("inside submit");

            for (int i = 0; i < Integer.valueOf(cols.getText()); i++) {
                CompositeVBox compositeVBox = new CompositeVBox("Actual Label", "Table Header", "Data Type", "Data Size", i);
                colVbox.getChildren().add(compositeVBox.getvContainer());
                lsVbox.add(compositeVBox);

            }

            overallVbox.getChildren().addAll(colVbox, btnsHbox);
            subroot.getChildren().add(overallVbox);
            borderPane.setTop(subroot);
            subroot.setAlignment(Pos.CENTER);
            rootnode.getChildren().add(borderPane);
            AnchorPane.setTopAnchor(rootnode, 0.0);
            AnchorPane.setRightAnchor(rootnode, 0.0);
            AnchorPane.setBottomAnchor(rootnode, 0.0);
            AnchorPane.setLeftAnchor(rootnode, 0.0);
            AnchorPane.setTopAnchor(borderPane, 0.0);
            AnchorPane.setRightAnchor(borderPane, 0.0);
            AnchorPane.setBottomAnchor(borderPane, 0.0);
            AnchorPane.setLeftAnchor(borderPane, 0.0);
            AnchorPane.setTopAnchor(scrollPane, 0.0);
            AnchorPane.setRightAnchor(scrollPane, 0.0);
            AnchorPane.setBottomAnchor(scrollPane, 0.0);
            AnchorPane.setLeftAnchor(scrollPane, 0.0);
            //rootnode.setPrefWidth(1150.0);
            scrollPane.setContent(rootnode);
            root.getChildren().add(scrollPane);
            counter++;
            MainController.getInstance().loadModificationUI();
        } catch (Exception ex) {
            IRSDialog.showAlert("Error", "Unable to load New Table Headers");
            LOGGER.error(Level.ERROR, ex);
        }
    }


//    public void addListener2returns() {
//        returnCollection.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TRtnReturns>() {
//            @Override
//            public void changed(ObservableValue observable, TRtnReturns oldValue, TRtnReturns newValue) {
//                try {
//                    List<String> lsTables = new MetadataTableDAO().getNumberTables(newValue.getReturnCode());
//                    tables.setItems(FXCollections.observableList(lsTables));
//
//                } catch (Exception ex) {
//                    LOGGER.error(ex);
//                }
//            }
//        }
//        );
//    }
}
