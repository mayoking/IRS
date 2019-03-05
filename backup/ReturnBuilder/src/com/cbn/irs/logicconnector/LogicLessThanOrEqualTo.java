/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbn.irs.logicconnector;

import com.bbt.irs.deploy.Messages;
import static com.bbt.irs.deploy.Messages.LESSTHANOREQUALTODESC;
import com.bbt.irs.ui.HBoxGenerator;

/**
 *
 * @author tkola
 */
public class LogicLessThanOrEqualTo extends LogicConnector implements Messages {

    @Override
    public String getConnectorType() {

        return LESSTHANOREQUALTOSIGN;
    }

    @Override
    public String getConnectorDescription() {

        return LESSTHANOREQUALTODESC;
    }

    @Override
    public String getNegativeConnectorDescription() {

        return NEGATIVELESSTHANOREQUALTODESC;
    }

    @Override
    public HBoxGenerator addLogicOperatorUI(HBoxGenerator hboxGen) {

        return hboxGen;
    }

}
