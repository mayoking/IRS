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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "t_db_schema_mapping")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TDbSchemaMapping.findAll", query = "SELECT t FROM TDbSchemaMapping t")
    , @NamedQuery(name = "TDbSchemaMapping.findBySchemaName", query = "SELECT t FROM TDbSchemaMapping t WHERE t.tDbSchemaMappingPK.schemaName = :schemaName")
    , @NamedQuery(name = "TDbSchemaMapping.findByWorkCollectionId", query = "SELECT t FROM TDbSchemaMapping t WHERE t.tDbSchemaMappingPK.workCollectionId = :workCollectionId")
    , @NamedQuery(name = "TDbSchemaMapping.findByCreatedDate", query = "SELECT t FROM TDbSchemaMapping t WHERE t.createdDate = :createdDate")
    , @NamedQuery(name = "TDbSchemaMapping.findByCreatedBy", query = "SELECT t FROM TDbSchemaMapping t WHERE t.createdBy = :createdBy")
    , @NamedQuery(name = "TDbSchemaMapping.findByLastModified", query = "SELECT t FROM TDbSchemaMapping t WHERE t.lastModified = :lastModified")
    , @NamedQuery(name = "TDbSchemaMapping.findByModifiedBy", query = "SELECT t FROM TDbSchemaMapping t WHERE t.modifiedBy = :modifiedBy")})
public class TDbSchemaMapping implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TDbSchemaMappingPK tDbSchemaMappingPK;
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
    @JoinColumn(name = "schema_name", referencedColumnName = "schema_name", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TDbSchemaDefn tDbSchemaDefn;

    public TDbSchemaMapping() {
    }

    public TDbSchemaMapping(TDbSchemaMappingPK tDbSchemaMappingPK) {
        this.tDbSchemaMappingPK = tDbSchemaMappingPK;
    }

    public TDbSchemaMapping(TDbSchemaMappingPK tDbSchemaMappingPK, Date createdDate, String createdBy) {
        this.tDbSchemaMappingPK = tDbSchemaMappingPK;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public TDbSchemaMapping(String schemaName, int workCollectionId) {
        this.tDbSchemaMappingPK = new TDbSchemaMappingPK(schemaName, workCollectionId);
    }

    public TDbSchemaMappingPK getTDbSchemaMappingPK() {
        return tDbSchemaMappingPK;
    }

    public void setTDbSchemaMappingPK(TDbSchemaMappingPK tDbSchemaMappingPK) {
        this.tDbSchemaMappingPK = tDbSchemaMappingPK;
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

    public TDbSchemaDefn getTDbSchemaDefn() {
        return tDbSchemaDefn;
    }

    public void setTDbSchemaDefn(TDbSchemaDefn tDbSchemaDefn) {
        this.tDbSchemaDefn = tDbSchemaDefn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tDbSchemaMappingPK != null ? tDbSchemaMappingPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TDbSchemaMapping)) {
            return false;
        }
        TDbSchemaMapping other = (TDbSchemaMapping) object;
        if ((this.tDbSchemaMappingPK == null && other.tDbSchemaMappingPK != null) || (this.tDbSchemaMappingPK != null && !this.tDbSchemaMappingPK.equals(other.tDbSchemaMappingPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbt.irs.entity.TDbSchemaMapping[ tDbSchemaMappingPK=" + tDbSchemaMappingPK + " ]";
    }
    
}
