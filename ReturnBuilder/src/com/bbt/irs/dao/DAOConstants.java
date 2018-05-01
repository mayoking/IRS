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
  public final  String GETRITYPE = "select a from TCoreRiType a";
  public final  String GETTEMPLATETYPE = "select a from TemplateType a";
  public final  String GETDATASIZE = "select a from Datasize a";
  public final  String GETDATATYPE = "select a from Datatype a";
  public final  String GETWORKCOLLECTION = "select a from TRtnWorkCollectionSchedule a";
  public final  String CREATETABLE = "create table `%s`";
  public final  String QUOTE = "";
}
