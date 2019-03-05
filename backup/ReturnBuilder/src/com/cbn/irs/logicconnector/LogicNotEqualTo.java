/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbn.irs.logicconnector;

import com.bbt.irs.deploy.Messages;
import static com.bbt.irs.deploy.Messages.EQUALTODESC;
import static com.bbt.irs.deploy.Messages.EQUALTOSIGN;
import static com.bbt.irs.deploy.Messages.NEGATIVEEQUALTODESC;
import com.bbt.irs.ui.HBoxGenerator;

/**
 *
 * @author tkola
 */
public class LogicNotEqualTo extends LogicConnector implements Messages {

    @Override
    public String getConnectorType() {

        return NOTEQUALTOSIGN;
    }

    @Override
    public String getConnectorDescription() {

        return NOTEQUALTODESC;
    }

    @Override
    public String getNegativeConnectorDescription() {
        return NEGATIVENOTEQUALTODESC;
    }

    @Override
    public HBoxGenerator addLogicOperatorUI(HBoxGenerator hboxGen) {

        return hboxGen;
    }
}
