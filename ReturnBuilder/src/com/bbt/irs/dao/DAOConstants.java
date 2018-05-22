/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package com.bbt.irs.dao;

/**
 *
 * @author opeyemi
 */
public interface DAOConstants {

    public final String GETRITYPE = "select a from TCoreRiType a";
    public final String GETTEMPLATETYPE = "select a from TemplateType a";
    public final String GETDATASIZE = "SELECT d FROM Datasize d";
    public final String GETDATATYPE = "select a from Datatype a";
    public final String GETWORKCOLLECTION = "select a from TRtnWorkCollection a";
    public final String GETMERGEDTYPE = "select a from TRbMergedtype a";
    public final String FREQUENCY = "select a from TLkupFrequency a";
   // public final String CREATETABLE = "create table";
    /**
     * It is used for mysql
     */
    public final String QUOTE = "";
    /**
     * Needed for sqlserver
     */
    public final String ENDQUOTE = "]";
    /**
     * Needed for sql server
     */
    public final String STARTQUOTE = "[";
}
