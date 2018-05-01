/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.vo;

/**
 *
 * @author opeyemi
 */
public class DataType {

     public DataType(int id,String description,String value){
        this.id=id;
        this.description = description;
        this.value = value;
    }
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param Description the description to set
     */
    public void setDescription(String Description) {
        this.description = Description;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }
    private int id;
    private String description;
    private String value;
    
    @Override
    public String toString() {
            return getDescription() ;
        }
}
