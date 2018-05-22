/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.bbt.irs.deploy;
 
import com.bbt.irs.ui.UploadTemplateUI;
import com.bbt.irs.dao.CreateTableDAO;
import com.bbt.irs.dao.DAOConstants;
import static com.bbt.irs.dao.DAOConstants.QUOTE;
import com.bbt.irs.vo.BasicInfoVO;
import com.bbt.irs.vo.HeaderInfoVO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.PersistenceException;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
 
/**
*
* @author opeyemi
*/
public class TableGenerator implements DAOConstants, ErrorNameDesc {
 
    private static List<String> dataTypeNoLength = new ArrayList();
    private static List<String> createdTableNames = new ArrayList();
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(UploadTemplateUI.class);
    LinkedList<String> ls = new LinkedList();
 
    public static boolean generateTable(LinkedHashMap data) {
        LinkedHashMap<Integer, LinkedList<HeaderInfoVO>> header = (LinkedHashMap<Integer, LinkedList<HeaderInfoVO>>) data.get(2);
        int noofTables = header.size();
        boolean result = false;
        dataTypeNoLength.add("numeric");
        dataTypeNoLength.add("int");
        dataTypeNoLength.add("long");
        dataTypeNoLength.add("double");
        dataTypeNoLength.add("datetime");
        dataTypeNoLength.add("date");
        for (int i = 0; i < header.size(); i++) {
            if (noofTables > 1) {
                // result = createTable(data, i, true);
                result = createTableSqlServer(data, i, true);
            } else {
 
//                result = createTable(data, i, false);
                result = createTableSqlServer(data, i, false);
            }
        }
        return result;
    }
 
    public static boolean isDataTtypeWithoutLength(String dataType) {
        boolean result = true;
        for (int i = 1; i < dataTypeNoLength.size(); i++) {
            if (dataTypeNoLength.contains(dataType.toLowerCase())) {
                result = false;
            }
        }
        System.out.println("isDataTtypeWithoutLength " + result);
        return result;
    }
 
    public static boolean createTable(LinkedHashMap data, int i, boolean moreThanOne) {
 
        StringBuilder strb = new StringBuilder();
        strb.append("create table ").append(QUOTE);
        boolean result = false;
        BasicInfoVO info = (BasicInfoVO) data.get(1);
        LinkedHashMap<Integer, LinkedList<HeaderInfoVO>> header = (LinkedHashMap<Integer, LinkedList<HeaderInfoVO>>) data.get(2);
        int noofTables = header.size();
 
        LinkedList ls = header.get(i + 1);
        HeaderInfoVO headerInfoVO;
        try {
            if (moreThanOne) {
                createdTableNames.add(info.getTemplateCode()+"_" +i);
                IRS.getTableNames().addFirst(info.getTemplateCode() + "_" + i);
                strb.append(info.getTemplateCode().toLowerCase()).append("_").append(i).append(QUOTE).append(" (").append(QUOTE).append("ri_code").append(QUOTE).append(" int(11),").append(QUOTE);
            } else {
                createdTableNames.add(info.getTemplateCode());
                IRS.getTableNames().addFirst(info.getTemplateCode());
                strb.append(info.getTemplateCode().toLowerCase()).append(QUOTE).append(" (").append(QUOTE).append("ri_code").append(QUOTE).append(" int(11),").append(QUOTE);
            }
            for (int j = 0; j < ls.size() - 1; j++) {
                headerInfoVO = (HeaderInfoVO) ls.get(j);
                strb.append(StringEscapeUtils.escapeJava(headerInfoVO.getCellName())).append(QUOTE).append(" ").append(headerInfoVO.getDataType().getDatatype()).append("(").append(headerInfoVO.getDataSize().getDatasize()).append("),").append(QUOTE);
            }
            headerInfoVO = (HeaderInfoVO) ls.get(ls.size() - 1);
            strb.append(StringEscapeUtils.escapeJava(headerInfoVO.getCellName())).append(QUOTE).append(" ").append(headerInfoVO.getDataType().getDatatype()).append("(").append(headerInfoVO.getDataSize().getDatasize()).append("),")
                    .append(QUOTE).append("addedby").append(QUOTE).append(" varchar(100),").append(QUOTE).append("addedate").append(QUOTE).append(" datetime )");
            System.out.println(" ddl " + strb.toString());
            CreateTableDAO createTableDAO = new CreateTableDAO();
            result = createTableDAO.createTable(strb.toString());
        } catch (SQLException | PersistenceException ex) {
            LOGGER.log(Level.FATAL, "Unable to connect to to data source", ex);
            IRSDialog.showAlert(ERROR, "Unable to connect to to data source");
 
        } catch (IOException ex) {
            LOGGER.log(Level.FATAL, "", ex);
        }
        return result;
    }
 
    public static boolean createTableSqlServer(LinkedHashMap data, int i, boolean moreThanOne) {
 
        StringBuilder strb = new StringBuilder();
        strb.append("create table ").append(QUOTE);
        boolean result = false;
        BasicInfoVO info = (BasicInfoVO) data.get(1);
        LinkedHashMap<Integer, LinkedList<HeaderInfoVO>> header = (LinkedHashMap<Integer, LinkedList<HeaderInfoVO>>) data.get(2);
        int noofTables = header.size();
 
        LinkedList ls = header.get(i + 1);
        HeaderInfoVO headerInfoVO;
        try {
            if (moreThanOne) {
                 createdTableNames.add(info.getTemplateCode()+"_" +i);
                IRS.getTableNames().addFirst(info.getTemplateCode() + "|" + i);
                strb.append(STARTQUOTE).append("t_rtn_submission_").append(info.getTemplateCode()).append("|").append(i).append(ENDQUOTE).append(" (").append(QUOTE).append("id").append(QUOTE).append(" int IDENTITY(1,1) NOT NULL,").append(QUOTE).append("ri_id").append(QUOTE).append(" int,").append(QUOTE);
            } else {
                 createdTableNames.add(info.getTemplateCode());
                IRS.getTableNames().addFirst(info.getTemplateCode());
                strb.append(STARTQUOTE).append("t_rtn_submission_").append(info.getTemplateCode()).append(ENDQUOTE).append(" (").append(QUOTE).append("id").append(QUOTE).append(" int IDENTITY(1,1) NOT NULL,").append(QUOTE).append("ri_id").append(QUOTE).append(" int,").append(STARTQUOTE);
            }
            for (int j = 0; j < ls.size() - 1; j++) {
                headerInfoVO = (HeaderInfoVO) ls.get(j);
                strb.append(j == 0 ? "item_code" : headerInfoVO.getCellName()).append(ENDQUOTE).append(" ").append(headerInfoVO.getDataType().getDatatype()).append(buildDataSize(strb, headerInfoVO, isDataTtypeWithoutLength(headerInfoVO.getDataType().getDatatype()))).append(STARTQUOTE);
            }
            headerInfoVO = (HeaderInfoVO) ls.get(ls.size() - 1);
            strb.append(headerInfoVO.getCellName()).append(ENDQUOTE).append(" ").append(headerInfoVO.getDataType().getDatatype()).append(buildDataSize(strb, headerInfoVO, isDataTtypeWithoutLength(headerInfoVO.getDataType().getDatatype())));
                    if(info.getTemplateCode().equals("4")){
                        strb.append(STARTQUOTE).append("branch").append(ENDQUOTE).append(" varchar(100),");
                    }
                    strb.append(STARTQUOTE).append("submission_by").append(ENDQUOTE).append(" varchar(100),").append(STARTQUOTE).append("submission_date").append(ENDQUOTE).append(" datetime, ").append("CONSTRAINT pk_rtn_submission_").append(info.getTemplateCode()).append(" PRIMARY KEY CLUSTERED(id), ")
                    .append(" CONSTRAINT uq1_rtn_submission_").append(info.getTemplateCode()).append(" UNIQUE (ri_id, submission_date,item_code))");
//                    .append("WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON PRIMARY) ON PRIMARY");
           
            System.out.println(" ddl " + strb.toString());
            CreateTableDAO createTableDAO = new CreateTableDAO();
            result = createTableDAO.createTable(strb.toString());
            IRS.setCreatedTableNames(createdTableNames);
           
        } catch (SQLException | PersistenceException ex) {
            LOGGER.log(Level.FATAL, "Unable to connect to to data source", ex);
            IRSDialog.showAlert(ERROR, "Unable to connect to to data source");
 
        } catch (IOException ex) {
            LOGGER.log(Level.FATAL, "Unable create table", ex);
        }
        return result;
    }
 
    public static StringBuilder buildDataSize(StringBuilder sb, HeaderInfoVO headerInfoVO, boolean dtsize) {
        sb = new StringBuilder();
        System.out.println("dtsize " + dtsize);
        if (dtsize) {
            sb.append("(").append(headerInfoVO.getDataSize().getDatasize()).append("),");
        } else {
            sb.append(",");
        }
        return sb;
    }
 
 
}