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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "t_lga_code")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TLgaCode.findAll", query = "SELECT t FROM TLgaCode t")
    , @NamedQuery(name = "TLgaCode.findByLgaId", query = "SELECT t FROM TLgaCode t WHERE t.lgaId = :lgaId")
    , @NamedQuery(name = "TLgaCode.findByLgaCode", query = "SELECT t FROM TLgaCode t WHERE t.lgaCode = :lgaCode")
    , @NamedQuery(name = "TLgaCode.findByName", query = "SELECT t FROM TLgaCode t WHERE t.name = :name")
    , @NamedQuery(name = "TLgaCode.findByStartValidityDate", query = "SELECT t FROM TLgaCode t WHERE t.startValidityDate = :startValidityDate")
    , @NamedQuery(name = "TLgaCode.findByEndValidityDate", query = "SELECT t FROM TLgaCode t WHERE t.endValidityDate = :endValidityDate")
    , @NamedQuery(name = "TLgaCode.findByCreatedDate", query = "SELECT t FROM TLgaCode t WHERE t.createdDate = :createdDate")
    , @NamedQuery(name = "TLgaCode.findByCreatedBy", query = "SELECT t FROM TLgaCode t WHERE t.createdBy = :createdBy")
    , @NamedQuery(name = "TLgaCode.findByLastModified", query = "SELECT t FROM TLgaCode t WHERE t.lastModified = :lastModified")
    , @NamedQuery(name = "TLgaCode.findByModifiedBy", query = "SELECT t FROM TLgaCode t WHERE t.modifiedBy = :modifiedBy")})
public class TLgaCode implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "lga_id")
    private Integer lgaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "lga_code")
    private String lgaCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_validity_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startValidityDate;
    @Basic(optional = false)
    @NotNull
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
    @JoinColumn(name = "state_code", referencedColumnName = "state_code")
    @ManyToOne(optional = false)
    private TState stateCode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lga")
    private Collection<TCoreReportingInstitution> tCoreReportingInstitutionCollection;

    public TLgaCode() {
    }

    public TLgaCode(Integer lgaId) {
        this.lgaId = lgaId;
    }

    public TLgaCode(Integer lgaId, String lgaCode, String name, Date startValidityDate, Date endValidityDate, Date createdDate, String createdBy) {
        this.lgaId = lgaId;
        this.lgaCode = lgaCode;
        this.name = name;
        this.startValidityDate = startValidityDate;
        this.endValidityDate = endValidityDate;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public Integer getLgaId() {
        return lgaId;
    }

    public void setLgaId(Integer lgaId) {
        this.lgaId = lgaId;
    }

    public String getLgaCode() {
        return lgaCode;
    }

    public void setLgaCode(String lgaCode) {
        this.lgaCode = lgaCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public TState getStateCode() {
        return stateCode;
    }

    public void setStateCode(TState stateCode) {
        this.stateCode = stateCode;
    }

    @XmlTransient
    public Collection<TCoreReportingInstitution> getTCoreReportingInstitutionCollection() {
        return tCoreReportingInstitutionCollection;
    }

    public void setTCoreReportingInstitutionCollection(Collection<TCoreReportingInstitution> tCoreReportingInstitutionCollection) {
        this.tCoreReportingInstitutionCollection = tCoreReportingInstitutionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lgaId != null ? lgaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TLgaCode)) {
            return false;
        }
        TLgaCode other = (TLgaCode) object;
        if ((this.lgaId == null && other.lgaId != null) || (this.lgaId != null && !this.lgaId.equals(other.lgaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbt.irs.entity.TLgaCode[ lgaId=" + lgaId + " ]";
    }
    
}
