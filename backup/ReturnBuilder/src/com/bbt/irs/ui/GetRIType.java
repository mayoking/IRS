/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template in the editor.
*/
package com.bbt.irs.ui;

import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.dao.DataSizeDAO;
import com.bbt.irs.dao.DataTypeDAO;
import com.bbt.irs.dao.RITypeDAO;
import com.bbt.irs.deploy.ErrorNameDesc;
import com.bbt.irs.deploy.Messages;
import com.bbt.irs.entity.TRbDatasize;
import com.bbt.irs.entity.TRbDatatype;
import com.bbt.irs.entity.TCoreRiType;
import com.bbt.irs.vo.GetRITypeVO;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
//import org.apache.commons.io.FileUtils;

/**
 * @author Olamide
*/
public class GetRIType implements Messages, ErrorNameDesc 
{

   Label RITypeLabel = new Label("RI Types");
   List<TCoreRiType> riTypes;
   List<String> RWCMDAO;
   List<TRbDatasize> dataSize;
   List<TRbDatatype> dataType;
   List<String> RetExist = new ArrayList<>();
   List<String> RetExist2 = new ArrayList<>();
   List<String> RetNotExist = new ArrayList<>();
   List<String> ColRetExist = new ArrayList<>();
   List<String> ColRetNotExist = new ArrayList<>();
   private ObservableList<TCoreRiType> typesofRI;
   ComboBox Ritype = new ComboBox();
   Separator separator = new Separator();
   public static String RITypeSelected;

   public GetRIType() 
   {
      try 
      {
         loadComboBox();
         setComboBox();
      } 
      catch (Exception ex) 
      {
         IRSDialog.showAlert(ERROR, ERROR_DROPDOWN);
         LOGGER.log(org.apache.logging.log4j.Level.FATAL, ERROR_DROPDOWN, ex);
      }
;

          //Ritype.setOnAction(new EventHandler<ActionEvent>() {@Override public void handle(ActionEvent e) {RITypeChanged();}});
   }

   public void RITypeChanged() 
   {
      String sCol = "C:/Users/Olamide/Documents/Holythirst/BBT/";
      File depDB = new File(sCol + "dist/db/");
      File depLib = new File(sCol + "dist/lib/");
      if(depDB.exists())
      {
         if(depLib.exists())
         {
            RITypeSelected = this.Ritype.getSelectionModel().getSelectedItem().toString();
            try 
            {
               loadRIWkCol();
            } 
            catch (Exception ex) 
            {
               IRSDialog.showAlert(ERROR, "Unable to Load Work Collections");
               LOGGER.log(org.apache.logging.log4j.Level.FATAL, "Unable to Load Work Collections", ex);
            }
         }
         else
         {
            IRSDialog.showAlert(ERROR, "Missing Lib Folder");
         }
      }
      else
      {
         IRSDialog.showAlert(ERROR, "Missing DB Folder");
      }
   }

   public boolean validateGetRIType() 
   {
      boolean result = true;
      if (this.Ritype.getSelectionModel().getSelectedItem() == null) 
      {
         IRSDialog.showAlert(RITypeLabel.getText(), FIELD_BLANK);
         result = false;
      } 
      else 
      {
         RITypeSelected = this.Ritype.getSelectionModel().getSelectedItem().toString();
         result = true;
      }
      return result;
   }

   private GetRITypeVO setGetRITypeObject() 
   {
      GetRITypeVO info = new GetRITypeVO();
      info.setRitype((TCoreRiType) Ritype.getSelectionModel().getSelectedItem());
      return info;
   }



   public void loadHistory() {}



   private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(GetRIType.class);


   private void loadComboBox() throws Exception 
   {
      riTypes = new RITypeDAO().getRIype();
      dataSize = new DataSizeDAO().getDataSize();
      dataType = new DataTypeDAO().getDataType();
      Ritype.setVisibleRowCount(5);
   }

   private void setComboBox() 
   {
      typesofRI = FXCollections.observableList(riTypes);
      Ritype = new ComboBox(typesofRI);
   }

   private void loadRIWkCol() throws Exception 
   {
      ArrayList<String> wkCols = new ArrayList();
      String colNEx = "";
      String colRet = "";
      try 
      {
         Connection conn;
         conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=OSMLiteDatabase", "victor", "super90-");
         ResultSet rs = conn.createStatement().executeQuery("SELECT a.* FROM t_rtn_work_collection a JOIN t_core_ri_type b ON b.ri_type_id = a.ri_type_id WHERE b.description = '" + RITypeSelected + "'");
         while (rs.next()) 
         {
            wkCols.add(rs.getString("work_collection_code"));  
         }
         
         //wkCols = new WorkCollectionDAO(). //.getWorkCollection(RITypeSelected);
         Map<String, List<String>> mapRet = new HashMap<>();
         for (String s : wkCols)
         {
            List<String> returns = new ArrayList<>();//get query that will return list of return codes
            returns.add(loadRIWkColRet(s).toString());
            mapRet.put(s, returns);
         }
         if(!RetNotExist.isEmpty())
         {
            for (String s : ColRetExist)
            {
               copyCol(s);
            }
         }
         else
         {
            colRet += "\nThe following Return(s) is/are missing:\n";
            for (String ne: RetNotExist)
            {
               colRet += ne + "\n";
            }
            IRSDialog.showAlert("Missing Work Collections and Returns", colNEx + "\n\n" + colRet);
         }
      } 
      catch (SQLException ex) 
      {
         System.err.println("Error" + ex);
         LOGGER.log(Level.FATAL, "RI Work Collection can not be loaded.", ex);
      }
   }

   private List<String> loadRIWkColRet(String WCS) throws Exception 
   {
      WCS = WCS.trim();
      List<String> wkColsRet = new ArrayList<>();
      try 
      {
         //RWCMDAO = new ReturnWorkCollectionMappingDAO(IRS.getEm()).getReturns(WCS);
         for (String rsRet: RWCMDAO)
         {
            wkColsRet.add(rsRet.trim());
            String sCol = "C:/Users/Olamide/Documents/Holythirst/BBT/dist/schema/" + WCS.toLowerCase();
            String sXML = "C:/Users/Olamide/Documents/Holythirst/BBT/dist/xml/" + rsRet.trim() + ".xml";
            String sXSD = "C:/Users/Olamide/Documents/Holythirst/BBT/dist/schema/" + WCS.toLowerCase() + "/" + rsRet.trim() + ".xsd";
            File myCol = new File(sCol);
            File myXML = new File(sXML);
            File myXSD = new File(sXSD);
            if(myCol.exists())
            {
               ColRetExist.add(WCS);
               if(myXML.exists() && myXSD.exists())
               {
                  RetExist.add(rsRet.trim());
               }
               else
               {
                  RetNotExist.add(WCS.toLowerCase() + "/" + rsRet.trim());
               }
            }
            else
            {
               ColRetNotExist.add(sCol);
            }
         }
      }
      catch (Exception ex) 
      {
         System.err.println("Error" + ex);
         LOGGER.log(Level.FATAL, "Work Collection Return cannot be loaded.", ex);
      }
      return wkColsRet;
   }

   public void copyCol(String myCol) throws Exception
   {
      myCol = myCol.toLowerCase();
      String sCol = "C:/Users/Olamide/Documents/Holythirst/BBT/";
      File myDestColRetFolder = new File(sCol + "Dest_SmartClient/SmartClient/");
      File myDestColRetFolder1 = new File(sCol + "Dest_SmartClient/SmartClient/schema/");
      File myDestColRetFolder2 = new File(sCol + "Dest_SmartClient/SmartClient/xml/");
      File myDestColRetFolder3 = new File(sCol + "Dest_SmartClient/SmartClient/schema/" + myCol);
      File myDestColRetFolder4 = new File(sCol + "Dest_SmartClient/SmartClient/xml/" + myCol);
      File destDB1 = new File(sCol + "Dest_SmartClient/db/");
      File destLib1 = new File(sCol + "Dest_SmartClient/lib/");
      File distDB1 = new File(sCol + "dist/db/");
      File distLib1 = new File(sCol + "dist/lib/");
      FileSystem fileSys = FileSystems.getDefault();
      //Path destDB = fileSys.getPath(sCol + "Dest_SmartClient/SmartClient/db/");
      //Path destLib = fileSys.getPath(sCol + "Dest_SmartClient/SmartClient/lib/");
      Path destSmartClientJar = fileSys.getPath(sCol + "Dest_SmartClient/SmartClient/SmartClient.jar");
      Path destSmartClientJnlp = fileSys.getPath(sCol + "Dest_SmartClient/SmartClient/SmartClient.jnlp");
      //Path smartClientDB = fileSys.getPath(sCol + "dist/db/");
      //Path smartClientLib = fileSys.getPath(sCol + "dist/lib/");
      Path smartClientJar = fileSys.getPath(sCol + "dist/SmartClient.jar");
      Path smartClientJnlp = fileSys.getPath(sCol + "dist/SmartClient.jnlp");
      try
      {
         //Copying the db and lib folders
         String files[] = destDB1.list();
         for(String file:files)
         {
            File srcFl = new File(distDB1, file);
            File destFl = new File(destDB1, file);
            Files.copy(srcFl.toPath(), destFl.toPath(), StandardCopyOption.REPLACE_EXISTING);
         }
         String files2[] = destLib1.list();
         for(String file: files2)
         {
            File srcFl2 = new File(distLib1, file);
            File destFl2 = new File(destLib1, file);
            Files.copy(srcFl2.toPath(), destFl2.toPath(), StandardCopyOption.REPLACE_EXISTING);
         }
         //Files.copy(smartClientDB, destDB, StandardCopyOption.REPLACE_EXISTING);
         //Files.copy(smartClientLib, destLib, StandardCopyOption.REPLACE_EXISTING);
         
         if(!myDestColRetFolder.exists()){myDestColRetFolder.mkdir();}
         Files.copy(smartClientJar, destSmartClientJar, StandardCopyOption.REPLACE_EXISTING);
         Files.copy(smartClientJnlp, destSmartClientJnlp, StandardCopyOption.REPLACE_EXISTING);   
         myDestColRetFolder1.mkdir();
         myDestColRetFolder2.mkdir();
         myDestColRetFolder3.mkdir();
         myDestColRetFolder4.mkdir();
         for(String x: RetExist)
         {
            Path srcPathxml = fileSys.getPath(sCol + "dist/xml/" + x + ".xml");
            Path srcPathxsd = fileSys.getPath(sCol + "dist/schema/" + myCol + "/" + x + ".xsd");
            Path destPathxml = fileSys.getPath(sCol + "Dest_SmartClient/SmartClient/xml/" + myCol + "/"  + x + ".xml");
            Path destPathxsd = fileSys.getPath(sCol + "Dest_SmartClient/SmartClient/schema/" + myCol + "/" + x + ".xsd");
            Files.copy(srcPathxml, destPathxml, StandardCopyOption.REPLACE_EXISTING);
            Files.copy(srcPathxsd, destPathxsd, StandardCopyOption.REPLACE_EXISTING);
         }
      }
      catch (IOException ex)
      {
         System.err.println("Error" + ex);
         LOGGER.log(Level.FATAL, "Work Collection folders cannot be moved.", ex);
      }
   }
}