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
public class ComplexOperandVO {

    private String placeHolder;
    private String complexOperatorType;
    private boolean iscomplexOperator;
    private String itemCode;
    private String columnName;
    private String logicalOperatorType;
    private String followingOperatorType;
    private int orderNumber;
    private boolean isRHS;
    private boolean isLHS;
    private String logicConnectorType;
    private String logicConnectorCol;
    private String logicConnectoritemCode;
    private String logicConnectorliteralText;
    private boolean isLiteral;
    private boolean isIfpart;
    private boolean isthenPart;
    private String initialOperatorType;
    private String versionId;
    private String userid;
    private String errorMessage;
    private Integer errorCode;

    /**
     * @return the columnName
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * @return the complexOperatorType
     */
    public String getComplexOperatorType() {
        return complexOperatorType;
    }

    /**
     * @return the followingOperatorType
     */
    public String getFollowingOperatorType() {
        return followingOperatorType;
    }

    /**
     * @return the logicalOperatorType
     */
    public String getLogicalOperatorType() {
        return logicalOperatorType;
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
     * @param columnName the columnName to set
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    /**
     * @param complexOperatorType the complexOperatorType to set
     */
    public void setComplexOperatorType(String complexOperatorType) {
        this.complexOperatorType = complexOperatorType;
    }

    /**
     * @param followingOperatorType the followingOperatorType to set
     */
    public void setFollowingOperatorType(String followingOperatorType) {
        this.followingOperatorType = followingOperatorType;
    }

    /**
     * @param logicalOperatorType the logicalOperatorType to set
     */
    public void setLogicalOperatorType(String logicalOperatorType) {
        this.logicalOperatorType = logicalOperatorType;
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
     * @return the initialOperatorType
     */
    public String getInitialOperatorType() {
        return initialOperatorType;
    }

    /**
     * @return the logicConnectorType
     */
    public String getLogicConnectorType() {
        return logicConnectorType;
    }

    /**
     * @return the isIfpart
     */
    public boolean isIsIfpart() {
        return isIfpart;
    }

    /**
     * @return the isLiteral
     */
    public boolean isIsLiteral() {
        return isLiteral;
    }

    /**
     * @return the isthenPart
     */
    public boolean isIsthenPart() {
        return isthenPart;
    }

    /**
     * @param initialOperatorType the initialOperatorType to set
     */
    public void setInitialOperatorType(String initialOperatorType) {
        this.initialOperatorType = initialOperatorType;
    }

    /**
     * @param isIfpart the isIfpart to set
     */
    public void setIsIfpart(boolean isIfpart) {
        this.isIfpart = isIfpart;
    }

    /**
     * @param isLiteral the isLiteral to set
     */
    public void setIsLiteral(boolean isLiteral) {
        this.isLiteral = isLiteral;
    }

    /**
     * @param isthenPart the isthenPart to set
     */
    public void setIsthenPart(boolean isthenPart) {
        this.isthenPart = isthenPart;
    }

    /**
     * @param logicConnectorType the logicConnectorType to set
     */
    public void setLogicConnectorType(String logicConnectorType) {
        this.logicConnectorType = logicConnectorType;
    }

    /**
     * @return the logicConnectorCol
     */
    public String getLogicConnectorCol() {
        return logicConnectorCol;
    }

    /**
     * @return the logicConnectoritemCode
     */
    public String getLogicConnectoritemCode() {
        return logicConnectoritemCode;
    }

    /**
     * @return the logicConnectorliteralText
     */
    public String getLogicConnectorliteralText() {
        return logicConnectorliteralText;
    }

    /**
     * @param logicConnectorCol the logicConnectorCol to set
     */
    public void setLogicConnectorCol(String logicConnectorCol) {
        this.logicConnectorCol = logicConnectorCol;
    }

    /**
     * @param logicConnectoritemCode the logicConnectoritemCode to set
     */
    public void setLogicConnectoritemCode(String logicConnectoritemCode) {
        this.logicConnectoritemCode = logicConnectoritemCode;
    }

    /**
     * @param logicConnectorliteralText the logicConnectorliteralText to set
     */
    public void setLogicConnectorliteralText(String logicConnectorliteralText) {
        this.logicConnectorliteralText = logicConnectorliteralText;
    }

    /**
     * @return the isLHS
     */
    public boolean isIsLHS() {
        return isLHS;
    }

    /**
     * @return the isRHS
     */
    public boolean isIsRHS() {
        return isRHS;
    }

    /**
     * @param isLHS the isLHS to set
     */
    public void setIsLHS(boolean isLHS) {
        this.isLHS = isLHS;
    }

    /**
     * @param isRHS the isRHS to set
     */
    public void setIsRHS(boolean isRHS) {
        this.isRHS = isRHS;
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
