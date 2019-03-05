/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.util;

import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 *
 * @author tkola
 */
public class IRSListener implements javafx.beans.value.ChangeListener<String> {
    
    
    private int maxLength;
    private TextField textField;


    public IRSListener(TextField textField, int maxLength) {
        this.textField= textField;
        this.maxLength = maxLength;
    }


    public int getMaxLength() {
        return maxLength;
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {


        if (newValue == null) {
            return;
        }


        if (newValue.length() > maxLength) {
            textField.setText(oldValue);
        } else {
            textField.setText(newValue);
        }
    }
    
    
    
    
}
