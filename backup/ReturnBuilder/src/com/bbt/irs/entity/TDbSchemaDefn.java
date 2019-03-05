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
@Table(name = "t_db_schema_defn")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TDbSchemaDefn.findAll", query = "SELECT t FROM TDbSchemaDefn t")
    , @NamedQuery(name = "TDbSchemaDefn.findBySchemaName", query = "SELECT t FROM TDbSchemaDefn t WHERE t.schemaName = :schemaName")
    , @NamedQuery(name = "TDbSchemaDefn.findBySchemaDesc", query = "SELECT t FROM TDbSchemaDefn t WHERE t.schemaDesc = :schemaDesc")
    , @NamedQuery(name = "TDbSchemaDefn.findBySchemaFilePath", query = "SELECT t FROM TDbSchemaDefn t WHERE t.schemaFilePath = :schemaFilePath")
    , @NamedQuery(name = "TDbSchemaDefn.findBySchemaLogPath", query = "SELECT t FROM TDbSchemaDefn t WHERE t.schemaLogPath = :schemaLogPath")
    , @NamedQuery(name = "TDbSchemaDefn.findByIsDeleted", query = "SELECT t FROM TDbSchemaDefn t WHERE t.isDeleted = :isDeleted")
    , @NamedQuery(name = "TDbSchemaDefn.findByCreatedDate", query = "SELECT t FROM TDbSchemaDefn t WHERE t.createdDate = :createdDate")
    , @NamedQuery(name = "TDbSchemaDefn.findByCreatedBy", query = "SELECT t FROM TDbSchemaDefn t WHERE t.createdBy = :createdBy")
    , @NamedQuery(name = "TDbSchemaDefn.findByLastModified", query = "SELECT t FROM TDbSchemaDefn t WHERE t.lastModified = :lastModified")
    , @NamedQuery(name = "TDbSchemaDefn.findByModifiedBy", query = "SELECT t FROM TDbSchemaDefn t WHERE t.modifiedBy = :modifiedBy")})
public class TDbSchemaDefn implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "schema_name")
    private String schemaName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "schema_desc")
    private String schemaDesc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "schema_file_path")
    private String schemaFilePath;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "schema_log_path")
    private String schemaLogPath;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_deleted")
    private boolean isDeleted;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tDbSchemaDefn")
    private Collection<TDbSchemaMapping> tDbSchemaMappingCollection;

    public TDbSchemaDefn() {
    }

    public TDbSchemaDefn(String schemaName) {
        this.schemaName = schemaName;
    }

    public TDbSchemaDefn(String schemaName, String schemaDesc, String schemaFilePath, String schemaLogPath, boolean isDeleted, Date createdDate, String createdBy) {
        this.schemaName = schemaName;
        this.schemaDesc = schemaDesc;
        this.schemaFilePath = schemaFilePath;
        this.schemaLogPath = schemaLogPath;
        this.isDeleted = isDeleted;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getSchemaDesc() {
        return schemaDesc;
    }

    public void setSchemaDesc(String schemaDesc) {
        this.schemaDesc = schemaDesc;
    }

    public String getSchemaFilePath() {
        return schemaFilePath;
    }

    public void setSchemaFilePath(String schemaFilePath) {
        this.schemaFilePath = schemaFilePath;
    }

    public String getSchemaLogPath() {
        return schemaLogPath;
    }

    public void setSchemaLogPath(String schemaLogPath) {
        this.schemaLogPath = schemaLogPath;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
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
    public Collection<TDbSchemaMapping> getTDbSchemaMappingCollection() {
        return tDbSchemaMappingCollection;
    }

    public void setTDbSchemaMappingCollection(Collection<TDbSchemaMapping> tDbSchemaMappingCollection) {
        this.tDbSchemaMappingCollection = tDbSchemaMappingCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (schemaName != null ? schemaName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TDbSchemaDefn)) {
            return false;
        }
        TDbSchemaDefn other = (TDbSchemaDefn) object;
        if ((this.schemaName == null && other.schemaName != null) || (this.schemaName != null && !this.schemaName.equals(other.schemaName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbt.irs.entity.TDbSchemaDefn[ schemaName=" + schemaName + " ]";
    }
    
}
