/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbn.irs.connector;

import com.bbt.irs.deploy.Messages;

/**
 *
 * @author tkola
 */
public class LessThanOrEqualToConnector extends Connector implements Messages {

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
}
