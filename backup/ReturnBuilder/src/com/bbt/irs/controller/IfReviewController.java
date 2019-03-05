/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.cbn.irs.ruleoperators.RuleOperator;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author tkola
 */
public class IfReviewController extends RuleBuilderAbstractController implements Initializable {

    @FXML
    Text message;
    @FXML
    Text assertionMessages;
    @FXML
    Text errorMessages;
    private RuleOperator operator; 

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }
    
    @FXML
    public void ok(ActionEvent e){
//        HBoxGenerator hbox = map.get(map.size());
//        HBoxGenerator anchorPaneHBoxGenerator1 = new HBoxGenerator(lastYLayout, null);
//        operatorVbox.getChildren().remove(hbox.getHboxContainer());
//        anchorPaneHBoxGenerator1.generateCompositeHBox4ComplexOperator();
//        map.put(counter, anchorPaneHBoxGenerator1);
//        operatorVbox.getChildren().add(anchorPaneHBoxGenerator1.getHboxContainer());
//        anchorPaneHBoxGenerator1.setOperator(operator);
        Stage stage = AssertionBuilderUIController.instance.getIfOperatorStage();
        stage.close();
        AssertionBuilderUIController.instance.setIfOperatorActionReview(true);

    }
    
    
    /**
     * @return the operator
     */
    public RuleOperator getOperator() {
        return operator;
    }

    /**
     * @param operator the operator to set
     */
    public void setOperator(RuleOperator operator) {
        this.operator = operator;
    }
}
