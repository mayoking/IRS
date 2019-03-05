/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.dao;

import com.bbt.irs.deploy.IRS;
import com.bbt.irs.deploy.IRSDialog;
import com.bbt.irs.entity.TRtnWorkCollectionDefn;
import com.bbt.irs.exception.DatabaseException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tkola
 */
public class SchemaDAO {

    public String getSchemaforWorkCol(Integer workcollid) throws DatabaseException {
        List<String> ls;
        try {
            System.out.println("workcollid ---  "+workcollid);
            EntityManager ems = IRS.getEm();
            Query query = ems.createQuery("select a.tDbSchemaDefn.schemaName from TDbSchemaMapping a where a.tDbSchemaMappingPK.workCollectionId=?1 ");
            query.setParameter(1, workcollid);
            ls = query.getResultList();
            if (ls.isEmpty()) {
                throw new DatabaseException("No schema associated with  Work Collection");
            }
        } catch (DatabaseException databaseException) {
            databaseException.printStackTrace();
            IRSDialog.showAlert("Error","No schema assocaited with  Work Collection");
            throw new DatabaseException("No schema assocaited with  Work Collection");
        }
        return ls.get(0);
    }
    
    public String getSchemaforWorkCol(String  returnCode) throws DatabaseException {
        String workcolcode;
        try {
           TRtnWorkCollectionDefn workcoll = new WorkCollectionDAO().getWorCollectionWithReturnCode(returnCode);
           workcolcode = getSchemaforWorkCol( workcoll.getWorkCollectionId());
        } catch (DatabaseException databaseException) {
            databaseException.printStackTrace();
            IRSDialog.showAlert("Error","No schema associated with  Work Collection");
            throw new DatabaseException("No schema associated with  Work Collection");
        }
        return workcolcode;
    }
}
