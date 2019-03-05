/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.vo;

/**
 *
 * @author tkola
 */
public class RBLoginVO {

    private int responseCode;
    private String responseDesc;
    private AppConfigVO appconfig;

    /**
     * @return the responseCode
     */
    public int getResponseCode() {
        return responseCode;
    }

    /**
     * @return the responseDesc
     */
    public String getResponseDesc() {
        return responseDesc;
    }

    /**
     * @param responseCode the responseCode to set
     */
    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * @param responseDesc the responseDesc to set
     */
    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }

    /**
     * @return the appconfig
     */
    public AppConfigVO getAppconfig() {
        return appconfig;
    }

    /**
     * @param appconfig the appconfig to set
     */
    public void setAppconfig(AppConfigVO appconfig) {
        this.appconfig = appconfig;
    }

}
