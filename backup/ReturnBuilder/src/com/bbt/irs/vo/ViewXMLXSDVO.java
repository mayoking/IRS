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
public class ViewXMLXSDVO {

    /**
     * @return the genericXsd
     */
    public String getGenericXsd() {
        return genericXsd;
    }

    /**
     * @param genericXsd the genericXsd to set
     */
    public void setGenericXsd(String genericXsd) {
        this.genericXsd = genericXsd;
    }

    /**
     * @return the responseCode
     */
    public int getResponseCode() {
        return responseCode;
    }

    /**
     * @return the responseDescription
     */
    public String getResponseDescription() {
        return responseDescription;
    }

    /**
     * @return the userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * @return the xml
     */
    public String getXml() {
        return xml;
    }

    /**
     * @return the xsd
     */
    public String getXsd() {
        return xsd;
    }

    /**
     * @param responseCode the responseCode to set
     */
    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * @param responseDescription the responseDescription to set
     */
    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * @param xml the xml to set
     */
    public void setXml(String xml) {
        this.xml = xml;
    }

    /**
     * @param xsd the xsd to set
     */
    public void setXsd(String xsd) {
        this.xsd = xsd;
    }
    private String userid;
    private String xml;
    private String xsd;
    private String genericXsd;
    private String responseDescription;
    private int  responseCode;
    
}
