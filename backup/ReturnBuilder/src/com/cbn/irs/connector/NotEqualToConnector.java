/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbn.irs.connector;

import com.bbt.irs.deploy.Messages;
import static com.bbt.irs.deploy.Messages.EQUALTODESC;
import static com.bbt.irs.deploy.Messages.EQUALTOSIGN;
import static com.bbt.irs.deploy.Messages.NEGATIVEEQUALTODESC;

/**
 *
 * @author tkola
 */
public class NotEqualToConnector extends Connector implements Messages{
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
    
}
