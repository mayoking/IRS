/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.vo;

import com.bbt.irs.entity.TRbRestrictedField;
import java.util.List;

/**
 *
 * @author tkola
 */
public class XSDModificationVO {

    private String workCollecion;
    private String returncode;
    private String userName;
    private List<TRbRestrictedField> tRbRestrictedField;

    /**
     * @return the returncode
     */
    public String getReturncode() {
        return returncode;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @return the workCollecion
     */
    public String getWorkCollecion() {
        return workCollecion;
    }

    /**
     * @return the tRbRestrictedField
     */
    public List<TRbRestrictedField> gettRbRestrictedField() {
        return tRbRestrictedField;
    }

    /**
     * @param returncode the returncode to set
     */
    public void setReturncode(String returncode) {
        this.returncode = returncode;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @param workCollecion the workCollecion to set
     */
    public void setWorkCollecion(String workCollecion) {
        this.workCollecion = workCollecion;
    }

    /**
     * @param tRbRestrictedField the tRbRestrictedField to set
     */
    public void settRbRestrictedField(List<TRbRestrictedField> tRbRestrictedField) {
        this.tRbRestrictedField = tRbRestrictedField;
    }

}
