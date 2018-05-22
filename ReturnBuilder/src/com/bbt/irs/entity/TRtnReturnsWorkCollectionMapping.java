/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tkola
 */
@Entity
@Table(name = "t_rtn_returns_work_collection_mapping")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TRtnReturnsWorkCollectionMapping.findAll", query = "SELECT t FROM TRtnReturnsWorkCollectionMapping t")
    , @NamedQuery(name = "TRtnReturnsWorkCollectionMapping.findByWorkCollectionCode", query = "SELECT t FROM TRtnReturnsWorkCollectionMapping t WHERE t.workCollectionCode = :workCollectionCode")
    , @NamedQuery(name = "TRtnReturnsWorkCollectionMapping.findByReturnCode", query = "SELECT t FROM TRtnReturnsWorkCollectionMapping t WHERE t.returnCode = :returnCode")
    , @NamedQuery(name = "TRtnReturnsWorkCollectionMapping.findByRiTypeId", query = "SELECT t FROM TRtnReturnsWorkCollectionMapping t WHERE t.riTypeId = :riTypeId")
    , @NamedQuery(name = "TRtnReturnsWorkCollectionMapping.findByStartValidityDate", query = "SELECT t FROM TRtnReturnsWorkCollectionMapping t WHERE t.startValidityDate = :startValidityDate")
    , @NamedQuery(name = "TRtnReturnsWorkCollectionMapping.findByEndValidityDate", query = "SELECT t FROM TRtnReturnsWorkCollectionMapping t WHERE t.endValidityDate = :endValidityDate")
    , @NamedQuery(name = "TRtnReturnsWorkCollectionMapping.findByCreatedDate", query = "SELECT t FROM TRtnReturnsWorkCollectionMapping t WHERE t.createdDate = :createdDate")
    , @NamedQuery(name = "TRtnReturnsWorkCollectionMapping.findByCreatedBy", query = "SELECT t FROM TRtnReturnsWorkCollectionMapping t WHERE t.createdBy = :createdBy")
    , @NamedQuery(name = "TRtnReturnsWorkCollectionMapping.findByLastModified", query = "SELECT t FROM TRtnReturnsWorkCollectionMapping t WHERE t.lastModified = :lastModified")
    , @NamedQuery(name = "TRtnReturnsWorkCollectionMapping.findByModifiedBy", query = "SELECT t FROM TRtnReturnsWorkCollectionMapping t WHERE t.modifiedBy = :modifiedBy")})
public class TRtnReturnsWorkCollectionMapping implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "work_collection_code")
    private String workCollectionCode;
    @Basic(optional = false)
    @Column(name = "return_code")
    private String returnCode;
    @Basic(optional = false)
    @Column(name = "ri_type_id")
    private int riTypeId;
    @Basic(optional = false)
    @Column(name = "start_validity_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startValidityDate;
    @Basic(optional = false)
    @Column(name = "end_validity_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endValidityDate;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "created_by")
    private String createdBy;
    @Basic(optional = false)
    @Column(name = "last_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
    @Basic(optional = false)
    @Column(name = "modified_by")
    private String modifiedBy;

    public TRtnReturnsWorkCollectionMapping() {
    }

    public TRtnReturnsWorkCollectionMapping(String workCollectionCode) {
        this.workCollectionCode = workCollectionCode;
    }

    public TRtnReturnsWorkCollectionMapping(String workCollectionCode, String returnCode, int riTypeId, Date startValidityDate, Date endValidityDate, Date lastModified, String modifiedBy) {
        this.workCollectionCode = workCollectionCode;
        this.returnCode = returnCode;
        this.riTypeId = riTypeId;
        this.startValidityDate = startValidityDate;
        this.endValidityDate = endValidityDate;
        this.lastModified = lastModified;
        this.modifiedBy = modifiedBy;
    }

    public String getWorkCollectionCode() {
        return workCollectionCode;
    }

    public void setWorkCollectionCode(String workCollectionCode) {
        this.workCollectionCode = workCollectionCode;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public int getRiTypeId() {
        return riTypeId;
    }

    public void setRiTypeId(int riTypeId) {
        this.riTypeId = riTypeId;
    }

    public Date getStartValidityDate() {
        return startValidityDate;
    }

    public void setStartValidityDate(Date startValidityDate) {
        this.startValidityDate = startValidityDate;
    }

    public Date getEndValidityDate() {
        return endValidityDate;
    }

    public void setEndValidityDate(Date endValidityDate) {
        this.endValidityDate = endValidityDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (workCollectionCode != null ? workCollectionCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TRtnReturnsWorkCollectionMapping)) {
            return false;
        }
        TRtnReturnsWorkCollectionMapping other = (TRtnReturnsWorkCollectionMapping) object;
        if ((this.workCollectionCode == null && other.workCollectionCode != null) || (this.workCollectionCode != null && !this.workCollectionCode.equals(other.workCollectionCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbt.irs.entity.TRtnReturnsWorkCollectionMapping[ workCollectionCode=" + workCollectionCode + " ]";
    }
    
}
