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
public class ExcelNameValListener implements javafx.beans.value.ChangeListener<String> {

    private TextField textField;

    public ExcelNameValListener(TextField textField) {
        this.textField = textField;
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

        if (newValue == null) {
            return;
        }

        if (Pattern.matches("^[A-Z](?!.*[a-zA-Z]$)", newValue)) {
            System.out.println("test");
            textField.setText(newValue);
        }else {
            textField.setText(oldValue);
        }
    }
    
    public static void main(String [] args){
        String test = "ER2";System.out.println(Pattern.matches("^[A-Z]([A-Z])*?([0-9])+$", test));
    }

    public static boolean isInteger(String s, int radix) {
        if (s.isEmpty()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) == '-') {
                if (s.length() == 1) {
                    return false;
                } else {
                    continue;
                }
            }
            if (Character.digit(s.charAt(i), radix) < 0) {
                return false;
            }
        }
        return true;
    }
}
