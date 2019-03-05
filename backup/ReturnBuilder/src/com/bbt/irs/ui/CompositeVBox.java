/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.ui;

import com.bbt.irs.dao.DataSizeDAO;
import com.bbt.irs.dao.DataTypeDAO;
import com.bbt.irs.entity.TRbDatasize;
import com.bbt.irs.entity.TRbDatatype;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author tkola
 */
public final class CompositeVBox {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(CompositeVBox.class);
    Separator separator;
    private VBox vContainer;
    private HBox textFieldHbox;
    private HBox comboHbox;
    private TextField cellNo;
    private TextField cellName;
    private ComboBox dataTypeCombo;
    private ComboBox dataSizeCombo;
    private List<TRbDatasize> dataSizes;
    private List<TRbDatatype> dataType;
    private Label cellNoLabel;
    private Label cellNameLabel;
    Label dataTypeComboLabel;
    Label dataSizeCombolabel;
    ObservableList<TRbDatatype> data;

    ObservableList<TRbDatasize> dataSize;

    public CompositeVBox(String cellno, String cellname, String datatype, String datasize, int counter) {
        try {
            dataSizes = new DataSizeDAO().getDataSize();
            dataType = new DataTypeDAO().getDataType();
            data = FXCollections.observableArrayList(dataType);
            dataSize = FXCollections.observableArrayList(dataSizes);
            System.out.println("dtaSize " + dataSize.size());
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(ex);
        }

        vContainer = new VBox();
        textFieldHbox = new HBox();
        cellNo = new TextField();
        cellName = new TextField(cellname + counter);
        cellNoLabel = new Label(cellno + counter);
        cellNameLabel = new Label(cellname + counter);
        dataTypeComboLabel = new Label(datatype + counter);
        dataSizeCombolabel = new Label(datasize + counter);
        dataTypeCombo = new ComboBox();
        dataTypeCombo.setItems(data);
        dataSizeCombo = new ComboBox();
        dataSizeCombo.setItems(dataSize);
        comboHbox = new HBox();
        comboHbox.getStyleClass().add("hbox");
        textFieldHbox = new HBox();
        textFieldHbox.getStyleClass().add("hbox");
        separator = new Separator();
        generateVboxUI();
    }

    public void generateVboxUI() {

        cellNo.setTextFormatter(new TextFormatter<>((change) -> {
            change.setText(change.getText().toUpperCase().replaceAll("[^a\\dA-Za-z ]", "").replace(" ", "_").replace("*", ""));
            return change;
        }));
        cellName.setTextFormatter(new TextFormatter<>((change) -> {
            change.setText(restrict(change.getText().toUpperCase().replaceAll("[^\\dA-Za-z_ ]", "").replace(" ", "_").replace("*", "")));
            return change;
        }));
        cellNo.getStyleClass().add("text-field");
        cellName.getStyleClass().add("text-field");
        dataTypeCombo.getStyleClass().add("combobox");
        dataSizeCombo.getStyleClass().add("combobox");
        cellNoLabel.getStyleClass().add("label9");
        cellNameLabel.getStyleClass().add("label9");
        dataTypeComboLabel.getStyleClass().add("label9");
        dataSizeCombolabel.getStyleClass().add("label9");
        this.textFieldHbox.getChildren().addAll(cellNoLabel, cellNo, cellNameLabel, cellName);
        this.comboHbox.getChildren().addAll(dataTypeComboLabel, dataTypeCombo, dataSizeCombolabel, dataSizeCombo);
        //vContainer.getStyleClass().add("vbox");
        dataTypeCombo.setTooltip(new Tooltip("Text is text Number is Number, Date is Date"));
        dataSizeCombo.setTooltip(new Tooltip("Short is what short is, Mideum is what medium is, Long is what long is."));
        vContainer.getChildren().addAll(textFieldHbox, comboHbox, new Separator());
        this.setvContainer(vContainer);

    }

    private String restrict(String text) {
        String result = null;
        if (text == null || text.length() < 45) {
            result = text;
        } else {
            result = text.substring(0, 45);
        }
        return result;
    }

    /**
     * @return the vContainer
     */
    public VBox getvContainer() {
        return vContainer;
    }

    /**
     * @param vContainer the vContainer to set
     */
    public void setvContainer(VBox vContainer) {
        this.vContainer = vContainer;
    }

    /**
     * @return the textFieldHbox
     */
    public HBox getTextFieldHbox() {
        return textFieldHbox;
    }

    /**
     * @param textFieldHbox the textFieldHbox to set
     */
    public void setTextFieldHbox(HBox textFieldHbox) {
        this.textFieldHbox = textFieldHbox;
    }

    /**
     * @return the comboHbox
     */
    public HBox getComboHbox() {
        return comboHbox;
    }

    /**
     * @param comboHbox the comboHbox to set
     */
    public void setComboHbox(HBox comboHbox) {
        this.comboHbox = comboHbox;
    }

    /**
     * @return the cellNo
     */
    public TextField getCellNo() {
        return cellNo;
    }

    /**
     * @param cellNo the cellNo to set
     */
    public void setCellNo(TextField cellNo) {
        this.cellNo = cellNo;
    }

    /**
     * @return the cellName
     */
    public TextField getCellName() {
        return cellName;
    }

    /**
     * @param cellName the cellName to set
     */
    public void setCellName(TextField cellName) {
        this.cellName = cellName;
    }

    /**
     * @return the dataTypeCombo
     */
    public ComboBox getDataTypeCombo() {
        return dataTypeCombo;
    }

    /**
     * @param dataTypeCombo the dataTypeCombo to set
     */
    public void setDataTypeCombo(ComboBox dataTypeCombo) {
        this.dataTypeCombo = dataTypeCombo;
    }

    /**
     * @return the dataSizeCombo
     */
    public ComboBox getDataSizeCombo() {
        return dataSizeCombo;
    }

    /**
     * @param dataSizeCombo the dataSizeCombo to set
     */
    public void setDataSizeCombo(ComboBox dataSizeCombo) {
        this.dataSizeCombo = dataSizeCombo;
    }

    /**
     * @return the dataSizes
     */
    public List<TRbDatasize> getDataSizes() {
        return dataSizes;
    }

    /**
     * @param dataSizes the dataSizes to set
     */
    public void setDataSizes(List<TRbDatasize> dataSizes) {
        this.dataSizes = dataSizes;
    }

    /**
     * @return the dataType
     */
    public List<TRbDatatype> getDataType() {
        return dataType;
    }

    /**
     * @param dataType the dataType to set
     */
    public void setDataType(List<TRbDatatype> dataType) {
        this.dataType = dataType;
    }

    /**
     * @return the cellNoLabel
     */
    public Label getCellNoLabel() {
        return cellNoLabel;
    }

    /**
     * @param cellNoLabel the cellNoLabel to set
     */
    public void setCellNoLabel(Label cellNoLabel) {
        this.cellNoLabel = cellNoLabel;
    }

    /**
     * @return the cellNameLabel
     */
    public Label getCellNameLabel() {
        return cellNameLabel;
    }

    /**
     * @param cellNameLabel the cellNameLabel to set
     */
    public void setCellNameLabel(Label cellNameLabel) {
        this.cellNameLabel = cellNameLabel;
    }

}
