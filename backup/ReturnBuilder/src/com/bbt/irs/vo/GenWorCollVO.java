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
public class GenWorCollVO {

 


    private String workCollectionCode;
    private String riType;
    private String returns;
    private String username;
    private boolean genWorkColl;

    /**
     * @return the returns
     */
    public String getReturns() {
        return returns;
    }

    /**
     * @return the riType
     */
    public String getRiType() {
        return riType;
    }

    /**
     * @return the workCollectionCode
     */
    public String getWorkCollectionCode() {
        return workCollectionCode;
    }

    /**
     * @param returns the returns to set
     */
    public void setReturns(String returns) {
        this.returns = returns;
    }

    /**
     * @param riType the riType to set
     */
    public void setRiType(String riType) {
        this.riType = riType;
    }

    /**
     * @param workCollectionCode the workCollectionCode to set
     */
    public void setWorkCollectionCode(String workCollectionCode) {
        this.workCollectionCode = workCollectionCode;
    }
   
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
       /**
     * @return the genWorkColl
     */
    public boolean isGenWorkColl() {
        return genWorkColl;
    }

    /**
     * @param genWorkColl the genWorkColl to set
     */
    public void setGenWorkColl(boolean genWorkColl) {
        this.genWorkColl = genWorkColl;
    }
}
