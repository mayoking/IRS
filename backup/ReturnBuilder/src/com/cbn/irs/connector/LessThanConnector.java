/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbn.irs.connector;

import com.bbt.irs.deploy.Messages;
import static com.bbt.irs.deploy.Messages.GREATEROREQUALTOSIGN;

/**
 *
 * @author tkola
 */
public class LessThanConnector extends Connector implements Messages {

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
}
