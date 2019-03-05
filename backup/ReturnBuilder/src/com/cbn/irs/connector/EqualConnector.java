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
public class EqualConnector extends Connector implements Messages {

    @Override
    public String getConnectorType() {
       
        return EQUALTOSIGN;
    }

    @Override
    public String getConnectorDescription() {
       
        return EQUALTODESC;
    }

    @Override
    public String getNegativeConnectorDescription() {
         return NEGATIVEEQUALTODESC;
    }
    
    
}
