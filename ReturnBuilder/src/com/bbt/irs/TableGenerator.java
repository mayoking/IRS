/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs;

import com.bbt.irs.dao.CreateTableDAO;
import com.bbt.irs.dao.DAOConstants;
import static com.bbt.irs.dao.DAOConstants.QUOTE;
import com.bbt.irs.util.Utility;
import com.bbt.irs.vo.BasicInfoVO;
import com.bbt.irs.vo.HeaderInfoVO;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import javax.persistence.PersistenceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author opeyemi
 */
public class TableGenerator implements DAOConstants {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(UploadTemplateUI.class);
    LinkedList<String> ls = new LinkedList();

    public static boolean generateTable(LinkedHashMap data) {
        LinkedHashMap<Integer, LinkedList<HeaderInfoVO>> header = (LinkedHashMap<Integer, LinkedList<HeaderInfoVO>>) data.get(2);
        int noofTables = header.size();
        boolean result = false;

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
                IRS.getTableNames().addFirst(info.getTemplateCode() + "_" + i);
                strb.append(info.getTemplateCode()).append("_").append(i).append(QUOTE).append(" (").append(QUOTE).append("ri_code").append(QUOTE).append(" int(11),").append(QUOTE);
            } else {
                IRS.getTableNames().addFirst(info.getTemplateCode());
                strb.append(info.getTemplateCode()).append(QUOTE).append(" (").append(QUOTE).append("ri_code").append(QUOTE).append(" int(11),").append(QUOTE);
            }
            for (int j = 0; j < ls.size() - 1; j++) {
                headerInfoVO = (HeaderInfoVO) ls.get(j);
                strb.append(headerInfoVO.getCellName()).append(QUOTE).append(" ").append(headerInfoVO.getDataType().getDatatype()).append("(").append(headerInfoVO.getDataSize().getDataSize()).append("),").append(QUOTE);
            }
            headerInfoVO = (HeaderInfoVO) ls.get(ls.size() - 1);
            strb.append(headerInfoVO.getCellName()).append(QUOTE).append(" ").append(headerInfoVO.getDataType().getDatatype()).append("(").append(headerInfoVO.getDataSize().getDataSize()).append("),")
                    .append(QUOTE).append("addedby").append(QUOTE).append(" varchar(100),").append(QUOTE).append("addedate").append(QUOTE).append(" datetime )");
            System.out.println(" ddl " + strb.toString());
            CreateTableDAO createTableDAO = new CreateTableDAO();
            result = createTableDAO.createTable(strb.toString());
        } catch (SQLException | PersistenceException ex) {
            LOGGER.log(Level.FATAL, "Unable to connect to to data source", ex);
            Utility.showDialog("Unable to connect to to data source", true);

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
                IRS.getTableNames().addFirst(info.getTemplateCode() + "_" + i);
                strb.append(info.getTemplateCode()).append("_").append(i).append(QUOTE).append(" (").append(QUOTE).append("ri_code").append(QUOTE).append(" int,").append(QUOTE);
            } else {
                IRS.getTableNames().addFirst(info.getTemplateCode());
                strb.append(info.getTemplateCode()).append(QUOTE).append(" (").append(QUOTE).append("ri_code").append(QUOTE).append(" int,").append(QUOTE);
            }
            for (int j = 0; j < ls.size() - 1; j++) {
                headerInfoVO = (HeaderInfoVO) ls.get(j);
                strb.append(headerInfoVO.getCellName()).append(QUOTE).append(" ").append(headerInfoVO.getDataType().getDatatype()).append(buildDataSize(strb, headerInfoVO, false)).append(QUOTE);
            }
            headerInfoVO = (HeaderInfoVO) ls.get(ls.size() - 1);
            strb.append(headerInfoVO.getCellName()).append(QUOTE).append(" ").append(headerInfoVO.getDataType().getDatatype()).append(buildDataSize(strb, headerInfoVO, false))
                    .append(QUOTE).append("addedby").append(QUOTE).append(" varchar(100),").append(QUOTE).append("addedate").append(QUOTE).append(" datetime )");
            System.out.println(" ddl " + strb.toString());
            CreateTableDAO createTableDAO = new CreateTableDAO();
            result = createTableDAO.createTable(strb.toString());
        } catch (SQLException | PersistenceException ex) {
            LOGGER.log(Level.FATAL, "Unable to connect to to data source", ex);
            Utility.showDialog("Unable to connect to to data source", true);

        }
        return result;
    }

    public static StringBuilder buildDataSize(StringBuilder sb, HeaderInfoVO headerInfoVO, boolean dtsize) {
        sb = new StringBuilder();
        if (dtsize) {
            sb.append("(").append(headerInfoVO.getDataSize().getDataSize()).append("),");
        } else {
            sb.append(",");
        }
        return sb;
    }

}
