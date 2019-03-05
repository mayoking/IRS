/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.ui;

import com.bbt.irs.vo.RequestVO;
import javafx.scene.layout.HBox;

/**
 *
 * @author tkola
 */
public class IFHboxGenerator extends HBoxGenerator {

    public IFHboxGenerator(double lastYLayout, RequestVO requestVO) {
        super(lastYLayout, requestVO);
        hSpacing = 2.0;
        xLayout = 10.0;
        ydifferential = 64.0;

    }

    @Override
    public void generateCompositeHBox() {

        hboxContainer = new HBox();
        hboxContainer.setSpacing(hSpacing);
        hboxContainer.setLayoutX(xLayout);
        hboxContainer.setLayoutY(lastYLayout + ydifferential);
    }
}
