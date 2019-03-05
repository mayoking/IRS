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
public class LogicEqualTo extends LogicConnector implements Messages {

    
   

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

    @Override
    public HBoxGenerator addLogicOperatorUI(HBoxGenerator hboxGen) {

        return hboxGen;
    }

   

}
