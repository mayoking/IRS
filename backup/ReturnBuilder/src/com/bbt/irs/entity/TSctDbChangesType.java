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
@Table(name = "t_sct_db_changes_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TSctDbChangesType.findAll", query = "SELECT t FROM TSctDbChangesType t")
    , @NamedQuery(name = "TSctDbChangesType.findByTypeId", query = "SELECT t FROM TSctDbChangesType t WHERE t.typeId = :typeId")
    , @NamedQuery(name = "TSctDbChangesType.findByTypeName", query = "SELECT t FROM TSctDbChangesType t WHERE t.typeName = :typeName")
    , @NamedQuery(name = "TSctDbChangesType.findByTypeDesc", query = "SELECT t FROM TSctDbChangesType t WHERE t.typeDesc = :typeDesc")
    , @NamedQuery(name = "TSctDbChangesType.findByCreatedDate", query = "SELECT t FROM TSctDbChangesType t WHERE t.createdDate = :createdDate")
    , @NamedQuery(name = "TSctDbChangesType.findByCreatedBy", query = "SELECT t FROM TSctDbChangesType t WHERE t.createdBy = :createdBy")})
public class TSctDbChangesType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "type_id")
    private String typeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "type_name")
    private String typeName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "type_desc")
    private String typeDesc;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeChanges")
    private Collection<TSctDbChanges> tSctDbChangesCollection;

    public TSctDbChangesType() {
    }

    public TSctDbChangesType(String typeId) {
        this.typeId = typeId;
    }

    public TSctDbChangesType(String typeId, String typeName, String typeDesc, Date createdDate, String createdBy) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.typeDesc = typeDesc;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
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

    @XmlTransient
    public Collection<TSctDbChanges> getTSctDbChangesCollection() {
        return tSctDbChangesCollection;
    }

    public void setTSctDbChangesCollection(Collection<TSctDbChanges> tSctDbChangesCollection) {
        this.tSctDbChangesCollection = tSctDbChangesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (typeId != null ? typeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TSctDbChangesType)) {
            return false;
        }
        TSctDbChangesType other = (TSctDbChangesType) object;
        if ((this.typeId == null && other.typeId != null) || (this.typeId != null && !this.typeId.equals(other.typeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbt.irs.entity.TSctDbChangesType[ typeId=" + typeId + " ]";
    }
    
}
