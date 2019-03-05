/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbn.irs.connector;

import com.bbt.irs.ui.HBoxGenerator;

/**
 *
 * @author tkola
 */
public abstract class Connector {

    public abstract String getConnectorType();
    public abstract String getConnectorDescription();
    public abstract String getNegativeConnectorDescription();

    
    
}
