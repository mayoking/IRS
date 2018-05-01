/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.util;



import java.util.Properties;

/**
 * @author user
 */
public class LoadProperties extends Properties {



    public LoadProperties() {
        super();
    }

    @Override
    public String getProperty(String key, String mandatory) {
        String value = super.getProperty(key);
     
        if ((value == null || value.equals("")) && mandatory.equals("Y")) {
            System.out.println("testttttt is null");
        }
        return value;
    }
}
