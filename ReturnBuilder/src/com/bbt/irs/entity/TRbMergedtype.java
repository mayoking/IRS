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
@Table(name = "t_rb_mergedtype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TRbMergedtype.findAll", query = "SELECT t FROM TRbMergedtype t")
    , @NamedQuery(name = "TRbMergedtype.findByMergedId", query = "SELECT t FROM TRbMergedtype t WHERE t.mergedId = :mergedId")
    , @NamedQuery(name = "TRbMergedtype.findByMerged", query = "SELECT t FROM TRbMergedtype t WHERE t.merged = :merged")
    , @NamedQuery(name = "TRbMergedtype.findByMergedDesc", query = "SELECT t FROM TRbMergedtype t WHERE t.mergedDesc = :mergedDesc")
    , @NamedQuery(name = "TRbMergedtype.findByCreatedDate", query = "SELECT t FROM TRbMergedtype t WHERE t.createdDate = :createdDate")
    , @NamedQuery(name = "TRbMergedtype.findByCreatedBy", query = "SELECT t FROM TRbMergedtype t WHERE t.createdBy = :createdBy")
    , @NamedQuery(name = "TRbMergedtype.findByLastModified", query = "SELECT t FROM TRbMergedtype t WHERE t.lastModified = :lastModified")
    , @NamedQuery(name = "TRbMergedtype.findByModifiedBy", query = "SELECT t FROM TRbMergedtype t WHERE t.modifiedBy = :modifiedBy")})
public class TRbMergedtype implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "merged_id")
    private Integer mergedId;
    @Basic(optional = false)
    @Column(name = "merged")
    private String merged;
    @Basic(optional = false)
    @Column(name = "merged_desc")
    private String mergedDesc;
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

    public TRbMergedtype() {
    }

    public TRbMergedtype(Integer mergedId) {
        this.mergedId = mergedId;
    }

    public TRbMergedtype(Integer mergedId, String merged, String mergedDesc, Date lastModified, String modifiedBy) {
        this.mergedId = mergedId;
        this.merged = merged;
        this.mergedDesc = mergedDesc;
        this.lastModified = lastModified;
        this.modifiedBy = modifiedBy;
    }

    public Integer getMergedId() {
        return mergedId;
    }

    public void setMergedId(Integer mergedId) {
        this.mergedId = mergedId;
    }

    public String getMerged() {
        return merged;
    }

    public void setMerged(String merged) {
        this.merged = merged;
    }

    public String getMergedDesc() {
        return mergedDesc;
    }

    public void setMergedDesc(String mergedDesc) {
        this.mergedDesc = mergedDesc;
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
        hash += (mergedId != null ? mergedId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TRbMergedtype)) {
            return false;
        }
        TRbMergedtype other = (TRbMergedtype) object;
        if ((this.mergedId == null && other.mergedId != null) || (this.mergedId != null && !this.mergedId.equals(other.mergedId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbt.irs.entity.TRbMergedtype[ mergedId=" + mergedId + " ]";
    }
    
}
