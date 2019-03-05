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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tkola
 */
@Entity
@Table(name = "t_sct_type_download")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TSctTypeDownload.findAll", query = "SELECT t FROM TSctTypeDownload t")
    , @NamedQuery(name = "TSctTypeDownload.findBySctTypeId", query = "SELECT t FROM TSctTypeDownload t WHERE t.sctTypeId = :sctTypeId")
    , @NamedQuery(name = "TSctTypeDownload.findByRiTypeCode", query = "SELECT t FROM TSctTypeDownload t WHERE t.riTypeCode = :riTypeCode")
    , @NamedQuery(name = "TSctTypeDownload.findByStartValidityDate", query = "SELECT t FROM TSctTypeDownload t WHERE t.startValidityDate = :startValidityDate")
    , @NamedQuery(name = "TSctTypeDownload.findByEndValidityDate", query = "SELECT t FROM TSctTypeDownload t WHERE t.endValidityDate = :endValidityDate")
    , @NamedQuery(name = "TSctTypeDownload.findBySctTypeName", query = "SELECT t FROM TSctTypeDownload t WHERE t.sctTypeName = :sctTypeName")
    , @NamedQuery(name = "TSctTypeDownload.findByDescription", query = "SELECT t FROM TSctTypeDownload t WHERE t.description = :description")
    , @NamedQuery(name = "TSctTypeDownload.findBySctFileName", query = "SELECT t FROM TSctTypeDownload t WHERE t.sctFileName = :sctFileName")
    , @NamedQuery(name = "TSctTypeDownload.findBySctVersion", query = "SELECT t FROM TSctTypeDownload t WHERE t.sctVersion = :sctVersion")
    , @NamedQuery(name = "TSctTypeDownload.findBySctVersionType", query = "SELECT t FROM TSctTypeDownload t WHERE t.sctVersionType = :sctVersionType")
    , @NamedQuery(name = "TSctTypeDownload.findBySctFeatures", query = "SELECT t FROM TSctTypeDownload t WHERE t.sctFeatures = :sctFeatures")
    , @NamedQuery(name = "TSctTypeDownload.findByCreatedDate", query = "SELECT t FROM TSctTypeDownload t WHERE t.createdDate = :createdDate")
    , @NamedQuery(name = "TSctTypeDownload.findByCreatedBy", query = "SELECT t FROM TSctTypeDownload t WHERE t.createdBy = :createdBy")
    , @NamedQuery(name = "TSctTypeDownload.findByLastModified", query = "SELECT t FROM TSctTypeDownload t WHERE t.lastModified = :lastModified")
    , @NamedQuery(name = "TSctTypeDownload.findByModifiedBy", query = "SELECT t FROM TSctTypeDownload t WHERE t.modifiedBy = :modifiedBy")})
public class TSctTypeDownload implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "sct_type_id")
    private Integer sctTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "ri_type_code")
    private String riTypeCode;
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
    @Size(min = 1, max = 128)
    @Column(name = "sct_type_name")
    private String sctTypeName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "description")
    private String description;
    @Size(max = 128)
    @Column(name = "sct_file_name")
    private String sctFileName;
    @Size(max = 128)
    @Column(name = "sct_version")
    private String sctVersion;
    @Size(max = 1)
    @Column(name = "sct_version_type")
    private String sctVersionType;
    @Size(max = 2147483647)
    @Column(name = "sct_features")
    private String sctFeatures;
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

    public TSctTypeDownload() {
    }

    public TSctTypeDownload(Integer sctTypeId) {
        this.sctTypeId = sctTypeId;
    }

    public TSctTypeDownload(Integer sctTypeId, String riTypeCode, Date startValidityDate, String sctTypeName, String description, Date createdDate, String createdBy) {
        this.sctTypeId = sctTypeId;
        this.riTypeCode = riTypeCode;
        this.startValidityDate = startValidityDate;
        this.sctTypeName = sctTypeName;
        this.description = description;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public Integer getSctTypeId() {
        return sctTypeId;
    }

    public void setSctTypeId(Integer sctTypeId) {
        this.sctTypeId = sctTypeId;
    }

    public String getRiTypeCode() {
        return riTypeCode;
    }

    public void setRiTypeCode(String riTypeCode) {
        this.riTypeCode = riTypeCode;
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

    public String getSctTypeName() {
        return sctTypeName;
    }

    public void setSctTypeName(String sctTypeName) {
        this.sctTypeName = sctTypeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSctFileName() {
        return sctFileName;
    }

    public void setSctFileName(String sctFileName) {
        this.sctFileName = sctFileName;
    }

    public String getSctVersion() {
        return sctVersion;
    }

    public void setSctVersion(String sctVersion) {
        this.sctVersion = sctVersion;
    }

    public String getSctVersionType() {
        return sctVersionType;
    }

    public void setSctVersionType(String sctVersionType) {
        this.sctVersionType = sctVersionType;
    }

    public String getSctFeatures() {
        return sctFeatures;
    }

    public void setSctFeatures(String sctFeatures) {
        this.sctFeatures = sctFeatures;
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
        hash += (sctTypeId != null ? sctTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TSctTypeDownload)) {
            return false;
        }
        TSctTypeDownload other = (TSctTypeDownload) object;
        if ((this.sctTypeId == null && other.sctTypeId != null) || (this.sctTypeId != null && !this.sctTypeId.equals(other.sctTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbt.irs.entity.TSctTypeDownload[ sctTypeId=" + sctTypeId + " ]";
    }
    
}
