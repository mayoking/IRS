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
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author opeyemi
 */
@Entity
@Table(name = "t_rb_artifact")
@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "ArtifactTable.findAll", query = "SELECT a FROM ArtifactTable a")
//    , @NamedQuery(name = "ArtifactTable.findById", query = "SELECT a FROM ArtifactTable a WHERE a.id = :id")
//    , @NamedQuery(name = "ArtifactTable.findByReturnCode", query = "SELECT a FROM ArtifactTable a WHERE a.returnCode = :returnCode")
//    , @NamedQuery(name = "ArtifactTable.findByXlsPath", query = "SELECT a FROM ArtifactTable a WHERE a.xlsPath = :xlsPath")})
public class ArtifactTable implements Serializable {

    @Id()
    @Basic(optional = false)
    @Column(name = "artifact_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long artifactId;
    @Basic(optional = false)
    @Lob
    @Column(name = "sample_xml")
    private byte[] sampleXml;
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

    private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @Column(name = "return_code")
    private String returnCode;
    @Basic(optional = false)
    @Column(name = "xls_path")
    private String xlsPath;

    public ArtifactTable() {
    }

   

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public byte[] getSampleXml() {
        return sampleXml;
    }

    public void setSampleXml(byte[] sampleXml) {
        this.sampleXml = sampleXml;
    }

    public String getXlsPath() {
        return xlsPath;
    }

    public void setXlsPath(String xlsPath) {
        this.xlsPath = xlsPath;
    }

    
    public ArtifactTable(Long artifactId) {
        this.artifactId = artifactId;
    }

    public ArtifactTable(Long artifactId, byte[] sampleXml, Date lastModified, String modifiedBy) {
        this.artifactId = artifactId;
        this.sampleXml = sampleXml;
        this.lastModified = lastModified;
        this.modifiedBy = modifiedBy;
    }

    public Long getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(Long artifactId) {
        this.artifactId = artifactId;
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
        hash += (artifactId != null ? artifactId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArtifactTable)) {
            return false;
        }
        ArtifactTable other = (ArtifactTable) object;
        if ((this.artifactId == null && other.artifactId != null) || (this.artifactId != null && !this.artifactId.equals(other.artifactId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbt.irs.entity.ArtifactTable[ artifactId=" + artifactId + " ]";
    }


   

}
