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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tkola
 */
@Entity
@Table(name = "t_core_ri_mapping")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TCoreRiMapping.findAll", query = "SELECT t FROM TCoreRiMapping t")
    , @NamedQuery(name = "TCoreRiMapping.findByRiMappingId", query = "SELECT t FROM TCoreRiMapping t WHERE t.riMappingId = :riMappingId")
    , @NamedQuery(name = "TCoreRiMapping.findByStartValidityDate", query = "SELECT t FROM TCoreRiMapping t WHERE t.startValidityDate = :startValidityDate")
    , @NamedQuery(name = "TCoreRiMapping.findByEndValidityDate", query = "SELECT t FROM TCoreRiMapping t WHERE t.endValidityDate = :endValidityDate")
    , @NamedQuery(name = "TCoreRiMapping.findByCreatedDate", query = "SELECT t FROM TCoreRiMapping t WHERE t.createdDate = :createdDate")
    , @NamedQuery(name = "TCoreRiMapping.findByCreatedBy", query = "SELECT t FROM TCoreRiMapping t WHERE t.createdBy = :createdBy")
    , @NamedQuery(name = "TCoreRiMapping.findByLastModified", query = "SELECT t FROM TCoreRiMapping t WHERE t.lastModified = :lastModified")
    , @NamedQuery(name = "TCoreRiMapping.findByModifiedBy", query = "SELECT t FROM TCoreRiMapping t WHERE t.modifiedBy = :modifiedBy")})
public class TCoreRiMapping implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ri_mapping_id")
    private Integer riMappingId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_validity_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startValidityDate;
    @Column(name = "end_validity_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endValidityDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "last_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
    @Size(max = 255)
    @Column(name = "modified_by")
    private String modifiedBy;
    @JoinColumn(name = "ri_id", referencedColumnName = "ri_id")
    @ManyToOne(optional = false)
    private TCoreReportingInstitution riId;
    @JoinColumn(name = "auth_level_id", referencedColumnName = "auth_level_id")
    @ManyToOne(optional = false)
    private TCoreRiAuthorizationLevel authLevelId;
    @JoinColumn(name = "ri_type_id", referencedColumnName = "ri_type_id")
    @ManyToOne(optional = false)
    private TCoreRiType riTypeId;

    public TCoreRiMapping() {
    }

    public TCoreRiMapping(Integer riMappingId) {
        this.riMappingId = riMappingId;
    }

    public TCoreRiMapping(Integer riMappingId, Date startValidityDate, Date createdDate, String createdBy) {
        this.riMappingId = riMappingId;
        this.startValidityDate = startValidityDate;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public Integer getRiMappingId() {
        return riMappingId;
    }

    public void setRiMappingId(Integer riMappingId) {
        this.riMappingId = riMappingId;
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

    public TCoreReportingInstitution getRiId() {
        return riId;
    }

    public void setRiId(TCoreReportingInstitution riId) {
        this.riId = riId;
    }

    public TCoreRiAuthorizationLevel getAuthLevelId() {
        return authLevelId;
    }

    public void setAuthLevelId(TCoreRiAuthorizationLevel authLevelId) {
        this.authLevelId = authLevelId;
    }

    public TCoreRiType getRiTypeId() {
        return riTypeId;
    }

    public void setRiTypeId(TCoreRiType riTypeId) {
        this.riTypeId = riTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (riMappingId != null ? riMappingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TCoreRiMapping)) {
            return false;
        }
        TCoreRiMapping other = (TCoreRiMapping) object;
        if ((this.riMappingId == null && other.riMappingId != null) || (this.riMappingId != null && !this.riMappingId.equals(other.riMappingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbt.irs.entity.TCoreRiMapping[ riMappingId=" + riMappingId + " ]";
    }
    
}
