/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.dao;

import static com.bbt.irs.dao.DAOConstants.GETRITYPE;
import com.bbt.irs.entity.TemplateType;
import com.bbt.irs.vo.DataType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author opeyemi
 */
public class TemplateTypeDAO implements DAOConstants {
    EntityManagerFactory emf;

    public List<TemplateType> getTemplateType() {
        List<TemplateType> list;
        this.emf = Persistence.createEntityManagerFactory("ReturnBuilderPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(GETTEMPLATETYPE);
        list = query.getResultList();

        return list;
    }
}
