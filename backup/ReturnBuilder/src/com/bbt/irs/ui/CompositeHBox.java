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
public class CompositeHBox {

    /**
     * @return the fieldDataSizeDropdown
     */
    public ComboBox<TRbDatasize> getFieldDataSizeDropdown() {
        return fieldDataSizeDropdown;
    }

    /**
     * @param fieldDataSizeDropdown the fieldDataSizeDropdown to set
     */
    public void setFieldDataSizeDropdown(ComboBox<TRbDatasize> fieldDataSizeDropdown) {
        this.fieldDataSizeDropdown = fieldDataSizeDropdown;
    }

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(CompositeVBox.class);
    Separator separator;

    private VBox hContainer;
    private HBox textFieldComboHbox;
    private TextField fieldName;
    private TextField fieldDataType;
    private ComboBox<TRbDatatype> fieldDataTypeDropdown;
    private ComboBox<TRbDatasize> fieldDataSizeDropdown;
    private Label fieldNameLabel;
    private Label fieldDataTypelabel;
    private Label fieldDataTypeDropdownLabel;
    private Label fieldDataSizeDropdownLabel;

    private List<TRbDatatype> dataType;
    private List<TRbDatasize> dataSize;
    ObservableList<TRbDatatype> data;
    ObservableList<TRbDatasize> datasize;

    public CompositeHBox(String fieldNameLab, String fieldDataTypelab, String fieldDataTypeDropdownLab, String fieldDataSizeDropdownLab, int counter) {
        try {
            dataType = new DataTypeDAO().getDataType();
            data = FXCollections.observableArrayList(dataType);
            dataSize = new DataSizeDAO().getDataSize();
            datasize = FXCollections.observableArrayList(dataSize);
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(ex);
        }

        hContainer = new VBox();
        textFieldComboHbox = new HBox();
        fieldName = new TextField();
        fieldDataType = new TextField();
        fieldDataTypeDropdown = new ComboBox();
        fieldDataTypeDropdown.setItems(data);
        fieldDataSizeDropdown = new ComboBox();
        fieldDataSizeDropdown.setItems(datasize);
        fieldNameLabel = new Label(fieldNameLab + counter);
        fieldDataTypelabel = new Label(fieldDataTypelab + counter);
        fieldDataTypeDropdownLabel = new Label(fieldDataTypeDropdownLab + counter);
        fieldDataSizeDropdownLabel = new Label(fieldDataTypeDropdownLab + counter);
        textFieldComboHbox = new HBox();
        textFieldComboHbox.getStyleClass().add("hbox");
        separator = new Separator();
        generateHboxUI();
    }

    public void generateHboxUI() {

        fieldName.setTextFormatter(new TextFormatter<>((change) -> {
            change.setText(change.getText().toUpperCase());//.replaceAll("[^a\\dA-Za-z ]", "").replace("*", ""));
            return change;
        }));
        fieldDataType.setTextFormatter(new TextFormatter<>((change) -> {
            change.setText(change.getText().toUpperCase().replaceAll("[^\\dA-Za-z ]", "").replace("*", ""));
            return change;
        }));
        fieldName.getStyleClass().add("text-field");
        fieldDataType.getStyleClass().add("text-field");
        fieldDataTypeDropdown.getStyleClass().add("combobox");
        fieldDataSizeDropdown.getStyleClass().add("combobox");
        fieldDataTypeDropdownLabel.getStyleClass().add("label9");
        fieldNameLabel.getStyleClass().add("label9");
        fieldDataTypelabel.getStyleClass().add("label9");
        fieldDataTypeDropdownLabel.getStyleClass().add("label9");
        textFieldComboHbox.getChildren().addAll(fieldNameLabel, fieldName, fieldDataTypelabel, fieldDataType, fieldDataTypeDropdownLabel, fieldDataTypeDropdown, fieldDataSizeDropdownLabel, fieldDataSizeDropdown);
        fieldDataTypeDropdownLabel.setTooltip(new Tooltip("Text is text, Number is Number, Date is Date"));
        hContainer.getChildren().addAll(textFieldComboHbox, new Separator());
        this.sethContainer(hContainer);

    }

    /**
     * @return the hContainer
     */
    public VBox gethContainer() {
        return hContainer;
    }

    /**
     * @param hContainer the hContainer to set
     */
    public void sethContainer(VBox hContainer) {
        this.hContainer = hContainer;
    }

    /**
     * @return the fieldDataType
     */
    public TextField getFieldDataType() {
        return fieldDataType;
    }

    /**
     * @return the fieldDataTypeDropdown
     */
    public ComboBox getFieldDataTypeDropdown() {
        return fieldDataTypeDropdown;
    }

    /**
     * @return the fieldDataTypeDropdownLabel
     */
    public Label getFieldDataTypeDropdownLabel() {
        return fieldDataTypeDropdownLabel;
    }

    /**
     * @return the fieldDataTypelabel
     */
    public Label getFieldDataTypelabel() {
        return fieldDataTypelabel;
    }

    /**
     * @return the fieldName
     */
    public TextField getFieldName() {
        return fieldName;
    }

    /**
     * @return the fieldNameLabel
     */
    public Label getFieldNameLabel() {
        return fieldNameLabel;
    }

    /**
     * @return the textFieldComboHbox
     */
    public HBox getTextFieldComboHbox() {
        return textFieldComboHbox;
    }

    /**
     * @param fieldDataType the fieldDataType to set
     */
    public void setFieldDataType(TextField fieldDataType) {
        this.fieldDataType = fieldDataType;
    }

    /**
     * @param fieldDataTypeDropdown the fieldDataTypeDropdown to set
     */
    public void setFieldDataTypeDropdown(ComboBox fieldDataTypeDropdown) {
        this.fieldDataTypeDropdown = fieldDataTypeDropdown;
    }

    /**
     * @param fieldDataTypeDropdownLabel the fieldDataTypeDropdownLabel to set
     */
    public void setFieldDataTypeDropdownLabel(Label fieldDataTypeDropdownLabel) {
        this.fieldDataTypeDropdownLabel = fieldDataTypeDropdownLabel;
    }

    /**
     * @param fieldDataTypelabel the fieldDataTypelabel to set
     */
    public void setFieldDataTypelabel(Label fieldDataTypelabel) {
        this.fieldDataTypelabel = fieldDataTypelabel;
    }

    /**
     * @param fieldName the fieldName to set
     */
    public void setFieldName(TextField fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * @param fieldNameLabel the fieldNameLabel to set
     */
    public void setFieldNameLabel(Label fieldNameLabel) {
        this.fieldNameLabel = fieldNameLabel;
    }

    /**
     * @param textFieldComboHbox the textFieldComboHbox to set
     */
    public void setTextFieldComboHbox(HBox textFieldComboHbox) {
        this.textFieldComboHbox = textFieldComboHbox;
    }

}
