/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.exception.WebservicesException;
import com.bbt.irs.rest.client.RBRestClient;
import com.bbt.irs.vo.ResponseVO;
import com.bbt.irs.vo.RuleVO;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author tkola
 */
public class AssertionRemovalController implements Initializable {

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

    private Stage removalStage;
    private Map<String, RuleVO> actualVOMap;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void loadDetail(ModifyviewassertionController.AssertionRuleView assertioassertionRulenRule) {
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

    @FXML
    public void deleteAssertion(ActionEvent e) {
        try {
            String ruleID = alias.getText();
            RuleVO rsv = actualVOMap.get(ruleID);
            RBRestClient rbRestClient = new RBRestClient();
            ResponseVO vo = rbRestClient.assertruleremove(rsv);
            if (vo.getResponseCode() == 0) {
                IRSDialog.showAlert(ruleID, "The rule successfully removed");
                removalStage.close();
            } else {
                IRSDialog.showAlert(ruleID, "Error while removing the rules");
            }
        } catch (WebservicesException ex) {
            Logger.getLogger(AssertionRemovalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    public void backButton(ActionEvent e) {
        removalStage.close();
    }

    /**
     * @return the actualVOMap
     */
    public Map<String, RuleVO> getActualVOMap() {
        return actualVOMap;
    }

    /**
     * @param actualVOMap the actualVOMap to set
     */
    public void setActualVOMap(Map<String, RuleVO> actualVOMap) {
        this.actualVOMap = actualVOMap;
    }

    /**
     * @return the removalStage
     */
    public Stage getRemovalStage() {
        return removalStage;
    }

    /**
     * @param removalStage the removalStage to set
     */
    public void setRemovalStage(Stage removalStage) {
        this.removalStage = removalStage;
    }

}
