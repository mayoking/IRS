/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.apache.commons.lang.StringEscapeUtils;

/**
 * FXML Controller class
 *
 * @author tkola
 */
public class Assertionruledetailviewcontroller implements Initializable {

    @FXML
    TextField alias;
    @FXML
    TextField connector;
    @FXML
    Text message;
    @FXML
    Text errorMessage;
    @FXML
    Text assertionRule;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    public void loadDetail(ModifyviewassertionController.AssertionRuleView assertioassertionRulenRule){
        alias.setText(assertioassertionRulenRule.getAlias());
        alias.setDisable(true);
        connector.setText(assertioassertionRulenRule.getConnect());
        connector.setDisable(true);
        message.setText(assertioassertionRulenRule.getRuleMessage());
        message.setDisable(true);
        errorMessage.setText(assertioassertionRulenRule.getRuleErrorMessage());
        errorMessage.setDisable(true);
        assertionRule.setText(assertioassertionRulenRule.getRuleAssertionMessage());
        assertionRule.setDisable(true);
    }
    

    
}
