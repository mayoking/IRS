/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbn.irs.logicoperator;

import com.bbt.irs.controller.AssertionBuilderUIController;
import com.bbt.irs.ui.HBoxGenerator;
import com.bbt.irs.vo.ColumnandTypeVO;
import com.cbn.irs.logicconnector.LogicConnector;
import java.util.Map;

/**
 *
 * @author tkola
 */
public class ThenLogicOperator extends LogicOperator {

    @Override
    public String getLogicOperatorType() {
        return "then";
    }

    @Override
    public String validateLogicConnector(LogicConnector connector, Map thenmapp) {
        if (connector == null) {
            //showError(thenErrorText, "No value to compare");
            return "No Logic Connector Available";
        }
        if (!thenmapp.isEmpty()) {
            //IRSDialog.showAlert(INFO, "AND,OR and THEN Logical Operator can not be clicked after THEN Logic Operator");
            return "AND,OR and THEN Logical Operator can not be clicked after THEN Logic Operator";
        }
        String value;
        boolean test = connector.isLiteral();
        if (!test) {
            System.out.println("not test");
            String itemCode = null;
            String tempType = AssertionBuilderUIController.instance.getItemCodeAndColumnsVO().getTemplateType();
            System.out.println("tempType "+connector.isIsRestrictionCode());
            System.out.println("tempType2 "+tempType.equals("1"));
            if (tempType.equals("1")&& !connector.isIsRestrictionCode()) {
                itemCode = (String) connector.getItemcodecombo().getSelectionModel().getSelectedItem();
            }
            ColumnandTypeVO columnandTypeVO = (ColumnandTypeVO) connector.getColcombo().getSelectionModel().getSelectedItem();
            if (itemCode != null && columnandTypeVO != null) {
                HBoxGenerator hboxgen = connector.getHboxGenerator();
                String itemCodeHbox = (String) hboxgen.getItemcodeCombo().getSelectionModel().getSelectedItem();
                ColumnandTypeVO columnandTypeVOHbox = (ColumnandTypeVO) hboxgen.getColumnCombo().getSelectionModel().getSelectedItem();
                if (itemCode.equalsIgnoreCase(itemCodeHbox) && columnandTypeVO.getColumnName().equals(columnandTypeVOHbox.getColumnName())) {
                    return "same cell can not be comapred in a conditional if statement";
                } else  {
                    value = "success";
                }
            }else if((itemCode == null && columnandTypeVO != null) && (connector.isIsRestrictionCode() ||!tempType.equals("1"))){
                HBoxGenerator hboxgen = connector.getHboxGenerator();
                ColumnandTypeVO columnandTypeVOHbox = (ColumnandTypeVO) hboxgen.getColumnCombo().getSelectionModel().getSelectedItem();
                if (columnandTypeVO.getColumnName().equals(columnandTypeVOHbox.getColumnName())) {
                    return "same cell can not be comapred in a conditional if statement";
                } else  {
                    value = "success";
                }
            }
            else {
                return "itemcode/column can not be blank";
            }
        } else {
            value = connector.getLogicConnectorTextField().getText();
        }

        if (value == null || value.isEmpty()) {
            //IRSDialog.showAlert(INFO, "No value to compare");
            return "No value to compare";
        }
        //this.connector = connector;
        return "success";
    }

}
