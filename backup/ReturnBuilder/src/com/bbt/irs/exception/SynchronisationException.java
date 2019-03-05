/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.exception;

/**
 *
 * @author tkola
 */
public class SynchronisationException  extends Exception {

    public SynchronisationException(String message) {
        super(" : " + message);
    }
}
