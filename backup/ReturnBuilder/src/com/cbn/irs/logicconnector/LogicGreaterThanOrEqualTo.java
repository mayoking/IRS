/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbn.irs.logicconnector;

import com.bbt.irs.deploy.Messages;
import com.bbt.irs.ui.HBoxGenerator;

/**
 *
 * @author tkola
 */
public class LogicGreaterThanOrEqualTo extends LogicConnector implements Messages {

    @Override
    public String getConnectorType() {

        return GREATEROREQUALTOSIGN;
    }

    @Override
    public String getConnectorDescription() {

        return GREATERTHANOREQUALTODESC;
    }

    @Override
    public String getNegativeConnectorDescription() {

        return NEGATIVEGREATERTHANOREQUALTODESC;
    }

    @Override
    public HBoxGenerator addLogicOperatorUI(HBoxGenerator hboxGen) {

        return hboxGen;
    }

}
