/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "t_rb_datatype")
@XmlRootElement
public class TRbDatatype implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "datatype_id")
    private Integer datatypeId;
    @Basic(optional = false)
    @Column(name = "datatype_desc")
    private String datatypeDesc;
    @Basic(optional = false)
    @Column(name = "datatype")
    private String datatype;
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
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "datatypeId")
//    @JsonIgnoreProperties("datatypeId")
//    private Collection<MetadataTable> metadataTableCollection;

    public TRbDatatype() {
    }

    public TRbDatatype(Integer datatypeId) {
        this.datatypeId = datatypeId;
    }

    public TRbDatatype(Integer datatypeId, String datatypeDesc, String datatype, Date lastModified, String modifiedBy) {
        this.datatypeId = datatypeId;
        this.datatypeDesc = datatypeDesc;
        this.datatype = datatype;
        this.lastModified = lastModified;
        this.modifiedBy = modifiedBy;
    }

    public Integer getDatatypeId() {
        return datatypeId;
    }

    public void setDatatypeId(Integer datatypeId) {
        this.datatypeId = datatypeId;
    }

    public String getDatatypeDesc() {
        return datatypeDesc;
    }

    public void setDatatypeDesc(String datatypeDesc) {
        this.datatypeDesc = datatypeDesc;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
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

//    @XmlTransient
//    public Collection<MetadataTable> getMetadataTableCollection() {
//        return metadataTableCollection;
//    }
//
//    public void setMetadataTableCollection(Collection<MetadataTable> metadataTableCollection) {
//        this.metadataTableCollection = metadataTableCollection;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (datatypeId != null ? datatypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TRbDatatype)) {
            return false;
        }
        TRbDatatype other = (TRbDatatype) object;
        if ((this.datatypeId == null && other.datatypeId != null) || (this.datatypeId != null && !this.datatypeId.equals(other.datatypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.datatypeDesc;
    }
    
}
