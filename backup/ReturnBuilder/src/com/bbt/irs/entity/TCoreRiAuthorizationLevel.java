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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tkola
 */
@Entity
@Table(name = "t_core_ri_authorization_level")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TCoreRiAuthorizationLevel.findAll", query = "SELECT t FROM TCoreRiAuthorizationLevel t")
    , @NamedQuery(name = "TCoreRiAuthorizationLevel.findByAuthLevelId", query = "SELECT t FROM TCoreRiAuthorizationLevel t WHERE t.authLevelId = :authLevelId")
    , @NamedQuery(name = "TCoreRiAuthorizationLevel.findByAuthLevel", query = "SELECT t FROM TCoreRiAuthorizationLevel t WHERE t.authLevel = :authLevel")
    , @NamedQuery(name = "TCoreRiAuthorizationLevel.findByDescription", query = "SELECT t FROM TCoreRiAuthorizationLevel t WHERE t.description = :description")
    , @NamedQuery(name = "TCoreRiAuthorizationLevel.findByIsActive", query = "SELECT t FROM TCoreRiAuthorizationLevel t WHERE t.isActive = :isActive")
    , @NamedQuery(name = "TCoreRiAuthorizationLevel.findByCreatedDate", query = "SELECT t FROM TCoreRiAuthorizationLevel t WHERE t.createdDate = :createdDate")
    , @NamedQuery(name = "TCoreRiAuthorizationLevel.findByCreatedBy", query = "SELECT t FROM TCoreRiAuthorizationLevel t WHERE t.createdBy = :createdBy")
    , @NamedQuery(name = "TCoreRiAuthorizationLevel.findByLastModified", query = "SELECT t FROM TCoreRiAuthorizationLevel t WHERE t.lastModified = :lastModified")
    , @NamedQuery(name = "TCoreRiAuthorizationLevel.findByModifiedBy", query = "SELECT t FROM TCoreRiAuthorizationLevel t WHERE t.modifiedBy = :modifiedBy")})
public class TCoreRiAuthorizationLevel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "auth_level_id")
    private Integer authLevelId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "auth_level")
    private String authLevel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_active")
    private short isActive;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "authLevelId")
    private Collection<TCoreRiMapping> tCoreRiMappingCollection;

    public TCoreRiAuthorizationLevel() {
    }

    public TCoreRiAuthorizationLevel(Integer authLevelId) {
        this.authLevelId = authLevelId;
    }

    public TCoreRiAuthorizationLevel(Integer authLevelId, String authLevel, String description, short isActive, Date createdDate, String createdBy) {
        this.authLevelId = authLevelId;
        this.authLevel = authLevel;
        this.description = description;
        this.isActive = isActive;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public Integer getAuthLevelId() {
        return authLevelId;
    }

    public void setAuthLevelId(Integer authLevelId) {
        this.authLevelId = authLevelId;
    }

    public String getAuthLevel() {
        return authLevel;
    }

    public void setAuthLevel(String authLevel) {
        this.authLevel = authLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public short getIsActive() {
        return isActive;
    }

    public void setIsActive(short isActive) {
        this.isActive = isActive;
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

    @XmlTransient
    public Collection<TCoreRiMapping> getTCoreRiMappingCollection() {
        return tCoreRiMappingCollection;
    }

    public void setTCoreRiMappingCollection(Collection<TCoreRiMapping> tCoreRiMappingCollection) {
        this.tCoreRiMappingCollection = tCoreRiMappingCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (authLevelId != null ? authLevelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TCoreRiAuthorizationLevel)) {
            return false;
        }
        TCoreRiAuthorizationLevel other = (TCoreRiAuthorizationLevel) object;
        if ((this.authLevelId == null && other.authLevelId != null) || (this.authLevelId != null && !this.authLevelId.equals(other.authLevelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbt.irs.entity.TCoreRiAuthorizationLevel[ authLevelId=" + authLevelId + " ]";
    }
    
}
