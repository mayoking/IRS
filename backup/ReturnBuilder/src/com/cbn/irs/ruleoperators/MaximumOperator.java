/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbn.irs.ruleoperators;

import com.bbt.irs.controller.AssertionBuilderUIController;
import com.bbt.irs.controller.RuleBuilderAbstractController;
import com.bbt.irs.deploy.IRS;
import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.deploy.Messages;
import static com.bbt.irs.deploy.Messages.FXMLPATH;
import com.bbt.irs.ui.HBoxGenerator;
import com.bbt.irs.vo.AssertionRuleCompVO;
import com.bbt.irs.vo.ItemCodeAndColumnsVO;
import java.io.IOException;
import java.util.Map;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author tkola
 */
public class MaximumOperator extends RuleOperator implements Messages {

    @Override
    public String getOperatorType() {

        return "max";
    }

    @Override
    public AssertionRuleCompVO processInfo(String returnCode, int order, HBoxGenerator anchorPaneHBoxGenerator, boolean isrhs, boolean islast, boolean isInter) {
        this.isLast = islast;
        return processComplexinfo(returnCode, order, anchorPaneHBoxGenerator, isrhs, isInter);
    }

    @Override
    public void getComplexUI(RuleBuilderAbstractController callingParent, Map<Integer, HBoxGenerator> mapp, VBox operatorVbox, Integer counterzz) {
        try {
            AssertionBuilderUIController.instance.setIfOperatorActionReview(false);
            ItemCodeAndColumnsVO itemcodeandColumnsVO = AssertionBuilderUIController.instance.getItemCodeAndColumnsVO();
            FXMLLoader loader = new FXMLLoader();
            Parent root;
            ifOperatorStage = new Stage(StageStyle.DECORATED);
            ifOperatorStage.centerOnScreen();
            ifOperatorStage.initOwner(IRS.getInstance().getMainStage());
            root = (Parent) loader.load(getClass().getResourceAsStream(FXMLPATH + "max.fxml"));
            ifOperatorStage.setTitle("Maximum");
            ifOPeratorScene = new Scene(root);
            ifOperatorStage.setScene(ifOPeratorScene);
            AssertionBuilderUIController.instance.setIfOperatorStage(ifOperatorStage);
            AssertionBuilderUIController.instance.setIfOperatorScene(ifOPeratorScene);
            ifOperatorStage.showAndWait();

            if (AssertionBuilderUIController.instance.isIfOperatorActionReview()) {
                updateParent(callingParent, mapp, operatorVbox, this, counterzz, "Maximum");
                AssertionBuilderUIController.instance.setIfOperatorActionReview(false);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            IRSDialog.showAlert("ERROR", "Error,Unable to load the Requested UI");
        }
    }
}
