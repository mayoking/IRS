/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbn.irs.assertion;

import com.bbt.irs.controller.LHSController;
import com.bbt.irs.controller.RHSController;
import com.bbt.irs.exception.WebservicesException;
import com.bbt.irs.rest.client.RBRestClient;
import com.bbt.irs.vo.ItemCodeAndColumnsVO;
import com.bbt.irs.vo.RequestVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.persistence.sessions.Connector;

/**
 *
 * @author tkola
 */
public class AssertionComboLoader {

    private static final Logger LOGGER = LogManager.getLogger(AssertionComboLoader.class);
    RequestVO requestvo;
    private LHSController lhs;
    private RHSController rhs;
    private Connector connector;

    public AssertionComboLoader(RequestVO requestvo) {
        this.requestvo = requestvo;
    }

    public ItemCodeAndColumnsVO getData() throws WebservicesException {
        ItemCodeAndColumnsVO itemCodeAndColumnsVO;
        RBRestClient rbrestclient = new RBRestClient();
        itemCodeAndColumnsVO = rbrestclient.assertreq(requestvo);
        return itemCodeAndColumnsVO;
    }

    /**
     * @return the connector
     */
    public Connector getConnector() {
        return connector;
    }

    /**
     * @return the lhs
     */
    public LHSController getLhs() {
        return lhs;
    }

    /**
     * @return the rhs
     */
    public RHSController getRhs() {
        return rhs;
    }

    /**
     * @param connector the connector to set
     */
    public void setConnector(Connector connector) {
        this.connector = connector;
    }

    /**
     * @param lhs the lhs to set
     */
    public void setLhs(LHSController lhs) {
        this.lhs = lhs;
    }

    /**
     * @param rhs the rhs to set
     */
    public void setRhs(RHSController rhs) {
        this.rhs = rhs;
    }

}
