/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tkola
 */
@Entity
@Table(name = "t_core_ri_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TCoreRiType.findAll", query = "SELECT t FROM TCoreRiType t")
    , @NamedQuery(name = "TCoreRiType.findByRiTypeId", query = "SELECT t FROM TCoreRiType t WHERE t.riTypeId = :riTypeId")
    , @NamedQuery(name = "TCoreRiType.findByRiTypeCode", query = "SELECT t FROM TCoreRiType t WHERE t.riTypeCode = :riTypeCode")
    , @NamedQuery(name = "TCoreRiType.findByDescription", query = "SELECT t FROM TCoreRiType t WHERE t.description = :description")
    , @NamedQuery(name = "TCoreRiType.findByStartValidityDate", query = "SELECT t FROM TCoreRiType t WHERE t.startValidityDate = :startValidityDate")
    , @NamedQuery(name = "TCoreRiType.findByEndValidityDate", query = "SELECT t FROM TCoreRiType t WHERE t.endValidityDate = :endValidityDate")
    , @NamedQuery(name = "TCoreRiType.findByCreatedDate", query = "SELECT t FROM TCoreRiType t WHERE t.createdDate = :createdDate")
    , @NamedQuery(name = "TCoreRiType.findByCreatedBy", query = "SELECT t FROM TCoreRiType t WHERE t.createdBy = :createdBy")
    , @NamedQuery(name = "TCoreRiType.findByLastModified", query = "SELECT t FROM TCoreRiType t WHERE t.lastModified = :lastModified")
    , @NamedQuery(name = "TCoreRiType.findByModifiedBy", query = "SELECT t FROM TCoreRiType t WHERE t.modifiedBy = :modifiedBy")})
public class TCoreRiType implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "riTypeId")
    private Collection<TRtnWorkCollection> tRtnWorkCollectionCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ri_type_id")
    private Integer riTypeId;
    @Basic(optional = false)
    @Column(name = "ri_type_code")
    private String riTypeCode;
    @Column(name = "description")
    private String description;
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

    public TCoreRiType() {
    }

    public TCoreRiType(Integer riTypeId) {
        this.riTypeId = riTypeId;
    }

    public TCoreRiType(Integer riTypeId, String riTypeCode, Date startValidityDate, Date endValidityDate, Date lastModified, String modifiedBy) {
        this.riTypeId = riTypeId;
        this.riTypeCode = riTypeCode;
        this.startValidityDate = startValidityDate;
        this.endValidityDate = endValidityDate;
        this.lastModified = lastModified;
        this.modifiedBy = modifiedBy;
    }

    public Integer getRiTypeId() {
        return riTypeId;
    }

    public void setRiTypeId(Integer riTypeId) {
        this.riTypeId = riTypeId;
    }

    public String getRiTypeCode() {
        return riTypeCode;
    }

    public void setRiTypeCode(String riTypeCode) {
        this.riTypeCode = riTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        hash += (riTypeId != null ? riTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TCoreRiType)) {
            return false;
        }
        TCoreRiType other = (TCoreRiType) object;
        if ((this.riTypeId == null && other.riTypeId != null) || (this.riTypeId != null && !this.riTypeId.equals(other.riTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return description ;
    }

    @XmlTransient
    public Collection<TRtnWorkCollection> getTRtnWorkCollectionCollection() {
        return tRtnWorkCollectionCollection;
    }

    public void setTRtnWorkCollectionCollection(Collection<TRtnWorkCollection> tRtnWorkCollectionCollection) {
        this.tRtnWorkCollectionCollection = tRtnWorkCollectionCollection;
    }
    
    
    
}
