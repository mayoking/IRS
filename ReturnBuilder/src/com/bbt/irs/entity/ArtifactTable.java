/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author opeyemi
 */
@Entity
@Table(name = "artifact_table")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ArtifactTable.findAll", query = "SELECT a FROM ArtifactTable a")
    , @NamedQuery(name = "ArtifactTable.findById", query = "SELECT a FROM ArtifactTable a WHERE a.id = :id")
    , @NamedQuery(name = "ArtifactTable.findByReturnCode", query = "SELECT a FROM ArtifactTable a WHERE a.returnCode = :returnCode")
    , @NamedQuery(name = "ArtifactTable.findByXlsPath", query = "SELECT a FROM ArtifactTable a WHERE a.xlsPath = :xlsPath")})
public class ArtifactTable implements Serializable {

    @Basic(optional = false)
    @Lob
    @Column(name = "sample_xml")
    private byte[] sampleXml;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "return_code")
    private String returnCode;
    @Basic(optional = false)
    @Column(name = "xls_path")
    private String xlsPath;

    public ArtifactTable() {
    }

    public ArtifactTable(Integer id) {
        this.id = id;
    }

    public ArtifactTable(Integer id, String returnCode, byte[] sampleXml, String xlsPath) {
        this.id = id;
        this.returnCode = returnCode;
        this.sampleXml = sampleXml;
        this.xlsPath = xlsPath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArtifactTable)) {
            return false;
        }
        ArtifactTable other = (ArtifactTable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbt.irs.entity.ArtifactTable[ id=" + id + " ]";
    }

}
