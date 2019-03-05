/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.dao;

import com.bbt.irs.deploy.IRS;
import com.bbt.irs.deploy.Messages;
import static com.bbt.irs.deploy.Messages.NEWRETURN_CODE;
import static com.bbt.irs.deploy.Messages.NEWWORKCOL_CODE;
import com.bbt.irs.entity.TCoreRiType;
import com.bbt.irs.entity.TRtnReturns;
import com.bbt.irs.entity.TRtnWorkCollectionDefn;
import com.bbt.irs.entity.TSctDbChanges;
import com.bbt.irs.entity.TSctDbChangesStatus;
import com.bbt.irs.entity.TSctDbChangesType;
import com.bbt.irs.exception.DatabaseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;

/**
 *
 * @author tkola
 */
public class SynchronizerDAO implements Messages {

    public EntityManagerFactory getEntityManagerHSQLDB() {
        Map<String, String> properties = new HashMap();
        properties.put("javax.persistence.jdbc.user", "irs");
        properties.put("javax.persistence.jdbc.password", "irs");
        properties.put("javax.persistence.jdbc.driver", "org.hsqldb.jdbcDriver");
        properties.put("javax.persistence.jdbc.url", "jdbc:hsqldb:file:C:\\Users\\tkola\\rb\\db\\hsqlscript;shutdown=true");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(
                "IRSDataExtractorPU", properties);//c:/users/tkola/rb/db

        return emf;
    }

    public EntityManagerFactory getEntityManagerSqlServer() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(
                "IRSDataExtractorPU");

        return emf;
    }

    public List<TRtnReturns> getListofSCTReturns() {
        List<TRtnReturns> list = null;
        EntityManagerFactory factory = null;
        try {
            factory = getEntityManagerHSQLDB();
            Query query = factory.createEntityManager().createQuery("select a from TRtnReturns a");
            list = query.getResultList();
        } catch (Exception ex) {

        } finally {
            if (factory != null) {
                factory.close();
            }
        }

        return list;
    }

    public List<TRtnWorkCollectionDefn> getListofSCTWorkCollections() {
        List<TRtnWorkCollectionDefn> list = null;
        EntityManagerFactory factory = null;
        try {
            factory = getEntityManagerHSQLDB();
            Query query = factory.createEntityManager().createQuery("select a from TRtnWorkCollection a");
            list = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }

        return list;
    }

    public List<TCoreRiType> getListofSCTRiTypes() {
        List<TCoreRiType> list = null;
        EntityManagerFactory factory = null;
        try {
            factory = getEntityManagerHSQLDB();
            Query query = factory.createEntityManager().createQuery("select a from TCoreRiType a");
            list = query.getResultList();
        } catch (Exception ex) {

        } finally {
            if (factory != null) {
                factory.close();
            }
        }

        return list;
    }

    public void updateSmartClientDB() {

    }

    public List<TRtnReturns> getListofMainReturns() {
        List<TRtnReturns> list = null;
        EntityManagerFactory factory = null;
        try {
            factory = getEntityManagerSqlServer();
            Query query = factory.createEntityManager().createQuery("select a from TRtnReturns a");
            list = query.getResultList();
        } catch (Exception ex) {

        } finally {
            if (factory != null) {
                factory.close();
            }
        }

        return list;
    }

    public List<TRtnReturns> getListofMainReturnslDeact(Date date) {
        List<TRtnReturns> list = null;
        EntityManagerFactory factory = null;
        try {
            factory = getEntityManagerSqlServer();
            Query query = factory.createEntityManager().createQuery("select a from TRtnReturns a where a.endValidityDate < ?1");
            query.setParameter(1, date);
            list = query.getResultList();
        } catch (Exception ex) {

        } finally {
            if (factory != null) {
                factory.close();
            }
        }

        return list;
    }

    public List<TRtnWorkCollectionDefn> getListofMainWorkCollections() {
        List<TRtnWorkCollectionDefn> list = null;
        EntityManagerFactory factory = null;
        try {
            factory = getEntityManagerSqlServer();
            Query query = factory.createEntityManager().createQuery("select a from TRtnWorkCollection a");
            list = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }

        return list;
    }

    public List<TRtnWorkCollectionDefn> getListofMainWorkCollDeact(Date date) {
        List<TRtnWorkCollectionDefn> list = null;
        EntityManagerFactory factory = null;
        try {
            factory = getEntityManagerSqlServer();
            Query query = factory.createEntityManager().createQuery("select a from TRtnWorkCollection a where a.endValidityDate < ?1");
            query.setParameter(1, date);
            list = query.getResultList();
        } catch (Exception ex) {

        } finally {
            if (factory != null) {
                factory.close();
            }
        }

        return list;
    }

    public List<TCoreRiType> getListofMainRiTypes() {
        List<TCoreRiType> list = null;
        EntityManagerFactory factory = null;
        try {
            factory = getEntityManagerSqlServer();
            Query query = factory.createEntityManager().createQuery("select a from TCoreRiType a");
            list = query.getResultList();
        } catch (Exception ex) {

        } finally {
            if (factory != null) {
                factory.close();
            }
        }

        return list;
    }

    public List<TCoreRiType> getListofMainRiTypeDeact(Date date) {
        List<TCoreRiType> list = null;
        EntityManagerFactory factory = null;
        try {
            factory = getEntityManagerSqlServer();
            Query query = factory.createEntityManager().createQuery("select a from TCoreRiType a where a.endValidityDate < ?1");
            query.setParameter(1, date);
            list = query.getResultList();
        } catch (Exception ex) {

        } finally {
            if (factory != null) {
                factory.close();
            }
        }

        return list;
    }

    //public boolean truncate
    public static void main(String[] args) {
        SynchronizerDAO synchronizerDAO = new SynchronizerDAO();
        List<TRtnWorkCollectionDefn> ls = synchronizerDAO.getListofSCTWorkCollections();
        List<TRtnWorkCollectionDefn> ls2 = synchronizerDAO.getListofMainWorkCollections();

    }

    public <T> int deleteAllEntities(Class<T> entityType) {
        EntityManagerFactory factory = null;
        factory = this.getEntityManagerHSQLDB();
        EntityManager em = factory.createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaDelete<T> query = builder.createCriteriaDelete(entityType);
        query.from(entityType);
        return em.createQuery(query).executeUpdate();

    }

    public boolean updateSyncTable4NewReturn(TRtnReturns returns) {
        boolean result = false;
        EntityManagerFactory factory = null;
        try {
            factory = getEntityManagerSqlServer();
            EntityManager em = factory.createEntityManager();
            em.getTransaction().begin();
            TSctDbChanges tsctDbChanges = new TSctDbChanges();

            tsctDbChanges.setStatus(new TSctDbChangesStatus(UNSYNC_CODE));
            tsctDbChanges.setChangeBy(returns.getModifiedBy());
            tsctDbChanges.setChangeCode(returns.getReturnCode());
            tsctDbChanges.setChangeDate(returns.getLastModified());
            tsctDbChanges.setCreatedDate(new Date());
            tsctDbChanges.setTypeChanges(new TSctDbChangesType(NEWRETURN_CODE));
            em.getTransaction().commit();

        } catch (Exception e) {

        } finally {
            if (factory != null) {
                factory.close();
            }
        }
        return result;
    }

    public boolean updateSyncTable4NewReturn(TRtnReturns returns, String typeChanges) {
        boolean result = false;
        EntityManagerFactory factory = null;
        try {
            factory = getEntityManagerSqlServer();
            EntityManager em = factory.createEntityManager();
            em.getTransaction().begin();
            TSctDbChanges tsctDbChanges = new TSctDbChanges();

            tsctDbChanges.setStatus(new TSctDbChangesStatus(UNSYNC_CODE));
            tsctDbChanges.setChangeBy(IRS.getInstance().username);
            tsctDbChanges.setChangeCode(returns.getReturnCode());
            tsctDbChanges.setChangeDate(returns.getLastModified());
            tsctDbChanges.setCreatedDate(new Date());
            tsctDbChanges.setTypeChanges(new TSctDbChangesType(typeChanges));
            em.getTransaction().commit();

        } catch (Exception e) {

        } finally {
            if (factory != null) {
                factory.close();
            }
        }
        return result;
    }

    public boolean updateSyncTable4NewRiType(TCoreRiType riType) {
        boolean result = false;
        EntityManagerFactory factory = null;
        try {
            factory = getEntityManagerSqlServer();
            EntityManager em = factory.createEntityManager();
            em.getTransaction().begin();
            TSctDbChanges tsctDbChanges = new TSctDbChanges();

            tsctDbChanges.setStatus(new TSctDbChangesStatus(UNSYNC_CODE));
            tsctDbChanges.setChangeBy(riType.getCreatedBy());
            tsctDbChanges.setChangeCode(riType.getRiTypeCode());
            tsctDbChanges.setChangeDate(riType.getCreatedDate());
            tsctDbChanges.setCreatedDate(new Date());
            tsctDbChanges.setTypeChanges(new TSctDbChangesType(NEWRETURN_CODE));
            em.getTransaction().commit();
        } catch (Exception e) {

        } finally {
            if (factory != null) {
                factory.close();
            }
        }
        return result;
    }

    public boolean updateSyncTable4NewWorkColl(TRtnWorkCollectionDefn workColl) {
        boolean result = false;
        EntityManagerFactory factory = null;
        try {
            factory = getEntityManagerSqlServer();
            EntityManager em = factory.createEntityManager();
            em.getTransaction().begin();
            TSctDbChanges tsctDbChanges = new TSctDbChanges();

            tsctDbChanges.setStatus(new TSctDbChangesStatus(UNSYNC_CODE));
            tsctDbChanges.setChangeBy(workColl.getCreatedBy());
            tsctDbChanges.setChangeCode(workColl.getWorkCollectionCode());
            tsctDbChanges.setChangeDate(workColl.getCreatedDate());
            tsctDbChanges.setCreatedDate(new Date());
            tsctDbChanges.setTypeChanges(new TSctDbChangesType(NEWWORKCOL_CODE));
            em.getTransaction().commit();
        } catch (Exception e) {

        } finally {
            if (factory != null) {
                factory.close();
            }
        }

        return result;
    }

    public List<TSctDbChanges> getTSctDbChanges() {
        List<TSctDbChanges> list = null;
        EntityManagerFactory factory = null;
        try {
            factory = getEntityManagerSqlServer();
            Query query = factory.createEntityManager().createQuery("select a from TSctDbChanges a where a.status=?1");
            query.setParameter(1, new TSctDbChangesStatus(UNSYNC_CODE));
            list = query.getResultList();
        } catch (Exception ex) {

        } finally {
            if (factory != null) {
                factory.close();
            }
        }
        return list;
    }

    public List<TSctDbChanges> getTSctDbChangeType(String changeType) throws DatabaseException {
        List<TSctDbChanges> list = null;
        EntityManagerFactory factory = null;
        try {
            //factory = getEntityManagerSqlServer();
            EntityManager ems = IRS.getEm();
            Query query = ems.createQuery("select a from TSctDbChanges a where a.typeChanges=?1");
            query.setParameter(1, new TSctDbChangesType(changeType));
            list = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DatabaseException("Error while getting new returns for synchronization");
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
        return list;
    }

    public boolean updateSCTReturns(TRtnReturns rtn) {
        boolean result = false;
        List<TRtnReturns> list = null;
        EntityManagerFactory factory = null;
        try {
            factory = getEntityManagerHSQLDB();

        } catch (Exception ex) {

        } finally {
            if (factory != null) {
                factory.close();
            }
        }

        return result;
    }

    public Date getStartValidityDateInVogue() throws DatabaseException {
        List<Date> list = null;
        EntityManagerFactory factory = null;
        try {

            Query query = IRS.getEm().createQuery("select distinct a.validityDate from TSctDbChanges a where a.status=?1 order by  a.validityDate desc ");
            query.setParameter(1, new TSctDbChangesStatus(UNSYNC_CODE));
            list = query.getResultList();
            System.out.println("lisssttt " + list.size());
            if (list.isEmpty()) {
                //return new Date();
                throw new DatabaseException("No startValidity date available, Please choose startValiditydate");
            }
        } catch (DatabaseException ex) {
            throw new DatabaseException(ex.getMessage());
        }catch (Exception ex) {
            throw new DatabaseException("Error , while retrieving start valididty date");
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
        return list.get(0);
    }

}
