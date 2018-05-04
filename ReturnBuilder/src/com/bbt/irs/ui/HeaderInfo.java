/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.ui;

import static com.bbt.irs.deploy.ErrorNameDesc.FIELD_BLANK;
import static com.bbt.irs.deploy.ErrorNameDesc.NON_EXIST_CONTAINER;
import static com.bbt.irs.deploy.Messages.CELLNOID;
import com.bbt.irs.dao.DataSizeDAO;
import com.bbt.irs.dao.DataTypeDAO;
import com.bbt.irs.entity.Datasize;
import com.bbt.irs.entity.Datatype;
import com.bbt.irs.deploy.IRS;
import com.bbt.irs.controller.MainController;
import com.bbt.irs.deploy.ErrorNameDesc;
import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.deploy.Messages;
import com.bbt.irs.vo.BasicInfoVO;
import com.bbt.irs.vo.HeaderInfoVO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.controlsfx.control.spreadsheet.SpreadsheetView;

/**
 *
 * @author opeyemi
 */
public class HeaderInfo implements Messages,ErrorNameDesc {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(UploadTemplateUI.class);
    private static List<LinkedHashMap> linekdHashMaps = new ArrayList();

    public HeaderInfo() {

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        GridPane subRoot = new GridPane();
        btn.getStyleClass().add("button");
        btn1.getStyleClass().add("button");
        btn2.getStyleClass().add("button");
        btn3.getStyleClass().add("button");

        btn.setOnAction((ActionEvent event) -> {
            boolean test;
            if (!headerInfoHashmap.isEmpty()) {
                test = validateHeaderInfo();
            } else {
                test = true;
            }
            if (test) {
                VBox vb = getCompositeVBOX("CellNo", "Cell Name", "Data Type", "Data Size");
                vbox.getChildren().add(vb);
                headerInfoHashmap.put(counter, vb);
            }
        });

        btn1.setText("remove");
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Node node = vboxMain.lookup("#" + counter);
                System.out.println("node " + node.getId());
                vbox.getChildren().removeAll(node);
                headerInfoHashmap.remove(counter);
                counter--;
            }
        });
        /**
         * *
         * This button actions do th following
         * <li>loop through the table controls stored in headerInfoHashmap </li>
         * <li>validate all the controls above</li>
         * <li>Return a new table to the UI</li> ****
         */
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    boolean result = validateHeaderInfo();
                    if (result) {
                        for (int i = 0; i < headerInfoHashmap.size(); i++) {
                            HeaderInfoVO headerInfoVO = getHeaderInfoObject();
                            ls.addFirst(headerInfoVO);

                        }
                        IRS.getAllHeaderInfo().put(IRS.getFinalCounter(), ls);

                        if (IRS.getFinalCounter() < IRS.numberOfTables) {
                            MainController.getInstance().loadStaticHeaderForm();
                            IRS.setFinalCounter(IRS.getFinalCounter() + 1);
                            System.out.println("allHeaderInfo size " + IRS.getAllHeaderInfo().size());
                        } else {
                            IRS.getLinkedHashMap().put(2, IRS.getAllHeaderInfo());
                            IRS.setHeaderInfoCompleted(true);
                            readXLS();
                            //MainController.getInstance().loadXLS();
                        }

                    }
                } catch (IOException ex) {
                   LOGGER.log(Level.FATAL, "error", ex);
                }
            }
        });

        btn3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //btn2.getScene().setRoot(IRS.getBasicInfoRoot());
                AnchorPane previous = ((AnchorPane) IRS.scene.lookup("#centerPane"));
                previous.getChildren().clear();
                previous.getChildren().add(IRS.getUploadXlsUI());
            }
        });

        HBox hbox = new HBox();
        hbox.getStyleClass().add("hbox");
        hbox.getChildren().add(btn3);
        hbox.getChildren().add(btn);
        hbox.getChildren().add(btn1);
        hbox.getChildren().add(btn2);

        vboxMain.getChildren().addAll(vbox, hbox);
        subRoot.getChildren().add(vboxMain);
        borderPane.setTop(subRoot);
        subRoot.setAlignment(Pos.CENTER);
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
        rootnode.setPrefWidth(1150.0);
        scrollPane.setContent(rootnode);
        root.getChildren().add(scrollPane);

    }

    public HBox getHBox() {
        HBox hbox = new HBox();
        hbox.getStyleClass().add("hbox");
        return hbox;
    }

    public Label getLabel(String name) {
        Label label = new Label(name);
        return label;
    }

    public TextField getTextFied() {
        TextField textField = new TextField();//
        textField.getStyleClass().add("text-field");
        return textField;
    }

    public VBox getVBox() {
        VBox vbox = new VBox();

        return vbox;
    }

    public ComboBox getComboBox(boolean type) {
        ComboBox comboBox;
        if (type) {
            comboBox = new ComboBox(data);
            //comboBox.getSelectionModel().selectFirst(); // Select first as default
            comboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Datatype>() {

                @Override
                public void changed(ObservableValue<? extends Datatype> arg0, Datatype arg1, Datatype arg2) {
                    if (arg2 != null) {
                        System.out.println("Selected dataType: " + arg2.getDatatypeDesc());
                    }
                }
            });
        } else {
            comboBox = new ComboBox(dataSize);
            //comboBox.getSelectionModel().selectFirst(); // Select first as default
            comboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Datasize>() {

                @Override
                public void changed(ObservableValue<? extends Datasize> arg0, Datasize arg1, Datasize arg2) {
                    if (arg2 != null) {
                        System.out.println("Selected dataTpe: " + arg2.getDataSizeDesc());
                    }
                }
            });
        }
        comboBox.getStyleClass().add("combobox");
        return comboBox;
    }

    public VBox getCompositeVBOX(String name1, String name2, String name3, String name4) {
        counter++;
        Separator separator = new Separator();
        VBox vboxl = getVBox();
        HBox hbox1 = getHBox();
        hbox1.setId("hboxinner1");
        Label label1 = getLabel(name1 + counter);
        Label label2 = getLabel(name2 + counter);
        Label label3 = getLabel(name3 + counter);
        Label label4 = getLabel(name4 + counter);
        TextField field1 = getTextFied();
        field1.setTextFormatter(new TextFormatter<>((change) -> {
            change.setText(change.getText().toUpperCase().replace(" ", "_").replace("*", ""));
            return change;
        }));
        TextField field2 = getTextFied();
        field2.setTextFormatter(new TextFormatter<>((change) -> {
            change.setText(change.getText().toUpperCase().replace(" ", "_").replace("*", ""));
            return change;
        }));

        ComboBox combo1 = getComboBox(true);
        ComboBox combo2 = getComboBox(false);
        combo1.setId(DATATYPEID + counter);
        combo2.setId(DATASIZEID + counter);
        field1.setId(CELLNOID + counter);
        field2.setId(CELLNANEID + counter);
        label1.getStyleClass().add("label9");
        label2.getStyleClass().add("label9");
        label3.getStyleClass().add("label9");
        label4.getStyleClass().add("label9");
        hbox1.getChildren().add(label1);
        hbox1.getChildren().add(field1);
        hbox1.getChildren().add(label2);
        hbox1.getChildren().add(field2);
        HBox hbox2 = getHBox();
        hbox2.setId("hboxinner2");
        hbox2.getChildren().add(label3);
        hbox2.getChildren().add(combo1);
        hbox2.getChildren().add(label4);
        hbox2.getChildren().add(combo2);
        vboxl.getChildren().add(hbox1);
        vboxl.getChildren().add(hbox2);
        vboxl.getChildren().add(separator);
        vboxl.setId(counter + "");
        return vboxl;
    }

    public boolean validateHeaderInfo() {
        boolean result;
        BasicInfoVO info = (BasicInfoVO) IRS.getLinkedHashMap().get(1);
        IRS.numberOfTables = Integer.parseInt(info.getNumTables());
        //  int i = headerInfoHashmap.size() - 1;//vboxMain
        // VBox vb = (VBox) headerInfoHashmap.get(counter);
        System.out.println("this is the counter "+counter);
        VBox vb = (VBox) vboxMain.lookup("#" + counter);
        if (vb == null) {
            IRSDialog.showAlert(ERROR,NON_EXIST_CONTAINER);
            return false;
        }
        System.out.println("About to check hb1");
        HBox hb1 = (HBox) vb.lookup("#hboxinner1");
        HBox hb2 = (HBox) vb.lookup("#hboxinner2");
        if (hb1 == null || hb2 == null) {
            IRSDialog.showAlert(ERROR,NON_EXIST_CONTAINER);
            return false;
        }

        TextField field1 = (TextField) hb1.lookup("#" + CELLNOID + counter);
        TextField field2 = (TextField) hb1.lookup("#" + CELLNANEID + counter);
        ComboBox combo1 = (ComboBox) hb2.lookup("#" + DATATYPEID + counter);
        ComboBox combo2 = (ComboBox) hb2.lookup("#" + DATASIZEID + counter);
        result = checkControl(field1, field2, combo1, combo2);
        if (result) {
            headerInfoHashmap.put(counter, vb);
        }
        return result;
    }

    private boolean checkControl(TextField field1, TextField field2, ComboBox combo1, ComboBox combo2) {

        if (field1.getText().isEmpty()) {
            IRSDialog.showAlert(ERROR, FIELD_BLANK);
            return false;

        }
        System.out.println("field1.getText() " + field1.getText());
        if (combo1 == null || combo1.getSelectionModel() == null || combo1.getSelectionModel().getSelectedItem() == null) {
            IRSDialog.showAlert("Data Type", FIELD_BLANK);
            System.out.println("This is combo 1 " + combo1);
            System.out.println("This is combo1.getSelectionModel() 1 " + combo1.getSelectionModel());
            return false;
        }

        if (field2.getText().isEmpty()) {
            IRSDialog.showAlert("Cell Name", FIELD_BLANK);
            return false;

        }

        if (combo2 == null || combo2.getSelectionModel() == null || combo2.getSelectionModel().getSelectedItem() == null) {
            IRSDialog.showAlert("Data Size", FIELD_BLANK);
            return false;
        }

        return true;
    }

    public HeaderInfoVO getHeaderInfoObject() {
        HeaderInfoVO result = new HeaderInfoVO();
        VBox vb = (VBox) vboxMain.lookup("#" + counter);
        System.out.println("About to check hb1");
        HBox hb1 = (HBox) vb.lookup("#hboxinner1");
        HBox hb2 = (HBox) vb.lookup("#hboxinner2");
        TextField field1 = (TextField) hb1.lookup("#" + CELLNOID + counter);
        TextField field2 = (TextField) hb1.lookup("#" + CELLNANEID + counter);
        ComboBox combo1 = (ComboBox) hb2.lookup("#" + DATATYPEID + counter);
        ComboBox combo2 = (ComboBox) hb2.lookup("#" + DATASIZEID + counter);
        result.setCellNO(field1.getText());
        result.setCellName(field2.getText());
        result.setDataSize((Datasize) combo2.getSelectionModel().getSelectedItem());
        result.setDataType((Datatype) combo1.getSelectionModel().getSelectedItem());
//         result.setActualLabel(getActualLabel(field1.getText()));
        System.out.println("tetsing id getDataType "+result.getDataType().getId());
         System.out.println("tetsing id getDataSize "+result.getDataSize().getId());
        this.counter--;//decreasing the counter to get all the object in the linkedmap
        return result;
    }

    
    public void readXLS() {
        try {
            ExcelView view = new ExcelView(IRS.getExcelPath(), 0, true);
            SpreadsheetView xlsView = view.getView(IRS.getExcelPath());
            MainController.getInstance().viewXLS(xlsView);
        } catch (Exception ex) {
            IRSDialog.showAlert(ERROR, READ_XLS_FAILED);
            LOGGER.log(org.apache.logging.log4j.Level.FATAL, READ_XLS_FAILED, ex);
        }
    }

    List<Datasize> dataSizes = new DataSizeDAO().getDataSize();
    List<Datatype> dataType = new DataTypeDAO().getDataType();
    private final ObservableList<Datatype> data
            = FXCollections.observableArrayList(
                    dataType);

    private final ObservableList<Datasize> dataSize
            = FXCollections.observableArrayList(
                    dataSizes);

    public int counter;
    public int finalCounter = 0;
    public Button btn3 = new Button("Back");
    public Button btn = new Button("Add");
    public Button btn1 = new Button();
    public Button btn2 = new Button("Next");

    public VBox vboxMain = new VBox();
    public AnchorPane root = new AnchorPane();
    AnchorPane rootnode = new AnchorPane();
    BorderPane borderPane = new BorderPane();
    VBox vbox = new VBox();

    private final LinkedHashMap headerInfoHashmap = new LinkedHashMap();
    private final LinkedList<HeaderInfoVO> ls = new LinkedList();
    private String excelPath = null;
   

}
