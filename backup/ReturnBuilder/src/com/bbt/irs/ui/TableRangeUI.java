/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.ui;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;

/**
 *
 * @author tkola
 */
public final class TableRangeUI {

    private HBox rangeContainer;
    private Label startRangeLabel;
    private Label endRangeLabel;
    private TextField startRange;
    private TextField endRange;

    public TableRangeUI(int counter) {
        rangeContainer = new HBox();
        startRangeLabel = new Label("Initia Cell " + counter);
        endRangeLabel = new Label("End Cell " + counter);
        startRange = new TextField();
        endRange = new TextField();
        generateRangeUI();
    }

    public void generateRangeUI() {
        startRange.setTextFormatter(new TextFormatter<>((change) -> {
//            change.setText(change.getText().toUpperCase().replaceAll("[^\\dA-Za-z ]", "").replace(" ", "").replace("*", ""));             
            String changeText = change.getText();
            if (changeText == null || "".equals(changeText)) {
                
            } else {
                if (changeText.length()==0) {
                  changeText =  changeText.replaceFirst("^[0-9*&$#@%()!+_-{}\\[\\]><,./;:'\"^?]", ""); 
                }
            }
            //firstChar = change.getText();
            change.setText(change.getText().toUpperCase().substring(change.getText().length()).replaceFirst("^[0-9*&$#@%()!+_-{}\\[\\]><,./;:'\"^?]", "").substring(1));
            //"^[A-Z]([A-Z])*?([0-9])+$"
            return change;
        }));
        //startRange.textProperty().addListener(new ExcelNameValListener(startRange));
        //startRange.textProperty().addListener(new ExcelNameValListener(startRange));
        endRange.setTextFormatter(new TextFormatter<>((change) -> {
            change.setText(change.getText().toUpperCase().replaceAll("[^\\dA-Za-z ]", "").replace(" ", "").replace("*", ""));
            return change;
        }));
        startRange.setTooltip(new Tooltip("Enter Initial range of the Cell"));
        startRange.setPromptText("Enter Initial range of the Cell");
        endRange.setTooltip(new Tooltip("Enter Final range of the Cell"));
        //endRange.textProperty().addListener(new ExcelNameValListener(endRange));
        endRange.setPromptText("Enter Final range of the Cell");
        startRange.getStyleClass().add("text-field");
        endRange.getStyleClass().add("text-field");
        rangeContainer.getStyleClass().add("hbox");
        rangeContainer.getChildren().addAll(startRange, endRange);

    }

    /**
     * @return the rangeContainer
     */
    public HBox getRangeContainer() {
        return rangeContainer;
    }

    /**
     * @param rangeContainer the rangeContainer to set
     */
    public void setRangeContainer(HBox rangeContainer) {
        this.rangeContainer = rangeContainer;
    }

    /**
     * @return the startRange
     */
    public TextField getStartRange() {
        return startRange;
    }

    /**
     * @param startRange the startRange to set
     */
    public void setStartRange(TextField startRange) {
        this.startRange = startRange;
    }

    /**
     * @return the endRange
     */
    public TextField getEndRange() {
        return endRange;
    }

    /**
     * @param endRange the endRange to set
     */
    public void setEndRange(TextField endRange) {
        this.endRange = endRange;
    }

}
