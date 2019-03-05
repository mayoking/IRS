/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.ui;

import com.bbt.irs.controller.AssertionBuilderUIController;
import com.bbt.irs.deploy.IRSDialog;
import static com.bbt.irs.deploy.Messages.INFO;
import static com.bbt.irs.ui.HBoxGenerator.itemCodeAndColumnsVO;
import com.bbt.irs.vo.RequestVO;
import javafx.collections.FXCollections;
import javafx.scene.layout.HBox;

/**
 *
 * @author tkola
 */
public class HBoxGenerator4AbsoluteOperator extends HBoxGenerator4Ifoperator {
    
    public HBoxGenerator4AbsoluteOperator(double lastYLayout, RequestVO requestVO) {
        super(lastYLayout, requestVO);
        label.setText("Field");
    }

  @Override
    public void generateCompositeHBox() {
        hboxContainer = new HBox();
        hboxContainer.setSpacing(hSpacing);
        hboxContainer.setLayoutX(xLayout);
        hboxContainer.setLayoutY(lastYLayout + ydifferential);

        hboxContainer.getChildren().addAll(label, columnCombo);
        boolean result;
        itemCodeAndColumnsVO = AssertionBuilderUIController.instance.getItemCodeAndColumnsVO();
        if (itemCodeAndColumnsVO != null) {//need to test in case in future if is not restricted to starting operator
        
            columnCombo.setItems(FXCollections.observableList(itemCodeAndColumnsVO.getColNames()));
        } else {
            result = getRequiredData();
            if (result) {
                columnCombo.setItems(FXCollections.observableList(itemCodeAndColumnsVO.getColNames()));
            } else {
                IRSDialog.showAlert(INFO, "The return field can not be populated");
            }
        }
        this.setHboxContainer(hboxContainer);

    }
}
