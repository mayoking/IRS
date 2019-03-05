/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.vo;

import java.util.List;

/**
 *
 * @author tkola
 */
public class OperandsVO {

    

    private String placeHolder;
    private String complexOperatorType;
    private List<ComplexOperandVO> complexOperand;
    private boolean iscomplexOperator;
    private boolean isRHS;
    private boolean isLHS;
    private String itemCode;
    private String columnName;
    private String initialOperatorType;
    private String followingOperatorType;
    private int orderNumber;
    private String userid;
    private String versionId;
    private String errorMessage;
    private Integer errorCode;

    public OperandsVO() {

    }

    /**
     * @return the followingOperatorType
     */
    public String getFollowingOperatorType() {
        return followingOperatorType;
    }

    /**
     * @return the initialOperatorType
     */
    public String getInitialOperatorType() {
        return initialOperatorType;
    }

    /**
     * @return the itemCode
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * @return the orderNumber
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * @return the placeHolder
     */
    public String getPlaceHolder() {
        return placeHolder;
    }

    /**
     * @return the iscomplexOperator
     */
    public boolean isIscomplexOperator() {
        return iscomplexOperator;
    }

    /**
     * @param followingOperatorType the followingOperatorType to set
     */
    public void setFollowingOperatorType(String followingOperatorType) {
        this.followingOperatorType = followingOperatorType;
    }

    /**
     * @param initialOperatorType the initialOperatorType to set
     */
    public void setInitialOperatorType(String initialOperatorType) {
        this.initialOperatorType = initialOperatorType;
    }

    /**
     * @param iscomplexOperator the iscomplexOperator to set
     */
    public void setIscomplexOperator(boolean iscomplexOperator) {
        this.iscomplexOperator = iscomplexOperator;
    }

    /**
     * @param itemCode the itemCode to set
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * @param orderNumber the orderNumber to set
     */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * @param placeHolder the placeHolder to set
     */
    public void setPlaceHolder(String placeHolder) {
        this.placeHolder = placeHolder;
    }

    /**
     * @return the columnName
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * @param columnName the columnName to set
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    /**
     * @return the complexOperatorType
     */
    public String getComplexOperatorType() {
        return complexOperatorType;
    }

    /**
     * @param complexOperatorType the complexOperatorType to set
     */
    public void setComplexOperatorType(String complexOperatorType) {
        this.complexOperatorType = complexOperatorType;
    }

    /**
     * @return the isRHS
     */
    public boolean isIsRHS() {
        return isRHS;
    }

    /**
     * @param isRHS the isRHS to set
     */
    public void setIsRHS(boolean isRHS) {
        this.isRHS = isRHS;
    }

    /**
     * @return the isLHS
     */
    public boolean isIsLHS() {
        return isLHS;
    }

    /**
     * @param isLHS the isLHS to set
     */
    public void setIsLHS(boolean isLHS) {
        this.isLHS = isLHS;
    }

    /**
     * @return the complexOperand
     */
    public List<ComplexOperandVO> getComplexOperand() {
        return complexOperand;
    }

    /**
     * @param complexOperand the complexOperand to set
     */
    public void setComplexOperand(List<ComplexOperandVO> complexOperand) {
        this.complexOperand = complexOperand;
    }

    /**
     * @return the userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }
    
    /**
     * @return the errorCode
     */
    public Integer getErrorCode() {
        return errorCode;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return the versionId
     */
    public String getVersionId() {
        return versionId;
    }

    /**
     * @param versionId the versionId to set
     */
    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

}
