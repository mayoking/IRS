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
public class LogicLessThan extends LogicConnector implements Messages {

    @Override
    public String getConnectorType() {

        return LESSTHANSIGN;
    }

    @Override
    public String getConnectorDescription() {

        return LESSTHANDESC;
    }

    @Override
    public String getNegativeConnectorDescription() {

        return NEGATIVELESSTHANDESC;
    }

    @Override
    public HBoxGenerator addLogicOperatorUI(HBoxGenerator hboxGen) {

        return hboxGen;
    }

}
