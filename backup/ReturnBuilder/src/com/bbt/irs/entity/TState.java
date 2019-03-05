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
@Table(name = "t_state")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TState.findAll", query = "SELECT t FROM TState t")
    , @NamedQuery(name = "TState.findByStateId", query = "SELECT t FROM TState t WHERE t.stateId = :stateId")
    , @NamedQuery(name = "TState.findByStateCode", query = "SELECT t FROM TState t WHERE t.stateCode = :stateCode")
    , @NamedQuery(name = "TState.findByName", query = "SELECT t FROM TState t WHERE t.name = :name")
    , @NamedQuery(name = "TState.findByCountryCode", query = "SELECT t FROM TState t WHERE t.countryCode = :countryCode")
    , @NamedQuery(name = "TState.findByStartValidityDate", query = "SELECT t FROM TState t WHERE t.startValidityDate = :startValidityDate")
    , @NamedQuery(name = "TState.findByEndValidityDate", query = "SELECT t FROM TState t WHERE t.endValidityDate = :endValidityDate")
    , @NamedQuery(name = "TState.findByCreatedDate", query = "SELECT t FROM TState t WHERE t.createdDate = :createdDate")
    , @NamedQuery(name = "TState.findByCreatedBy", query = "SELECT t FROM TState t WHERE t.createdBy = :createdBy")
    , @NamedQuery(name = "TState.findByLastModified", query = "SELECT t FROM TState t WHERE t.lastModified = :lastModified")
    , @NamedQuery(name = "TState.findByModifiedBy", query = "SELECT t FROM TState t WHERE t.modifiedBy = :modifiedBy")})
public class TState implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "state_id")
    private Integer stateId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "state_code")
    private String stateCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "country_code")
    private String countryCode;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stateCode")
    private Collection<TLgaCode> tLgaCodeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "state")
    private Collection<TCoreReportingInstitution> tCoreReportingInstitutionCollection;
    @JoinColumn(name = "zone_code", referencedColumnName = "zone_code")
    @ManyToOne(optional = false)
    private TCountryZone zoneCode;

    public TState() {
    }

    public TState(Integer stateId) {
        this.stateId = stateId;
    }

    public TState(Integer stateId, String stateCode, String name, String countryCode, Date startValidityDate, Date endValidityDate, Date createdDate, String createdBy) {
        this.stateId = stateId;
        this.stateCode = stateCode;
        this.name = name;
        this.countryCode = countryCode;
        this.startValidityDate = startValidityDate;
        this.endValidityDate = endValidityDate;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
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

    @XmlTransient
    public Collection<TLgaCode> getTLgaCodeCollection() {
        return tLgaCodeCollection;
    }

    public void setTLgaCodeCollection(Collection<TLgaCode> tLgaCodeCollection) {
        this.tLgaCodeCollection = tLgaCodeCollection;
    }

    @XmlTransient
    public Collection<TCoreReportingInstitution> getTCoreReportingInstitutionCollection() {
        return tCoreReportingInstitutionCollection;
    }

    public void setTCoreReportingInstitutionCollection(Collection<TCoreReportingInstitution> tCoreReportingInstitutionCollection) {
        this.tCoreReportingInstitutionCollection = tCoreReportingInstitutionCollection;
    }

    public TCountryZone getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(TCountryZone zoneCode) {
        this.zoneCode = zoneCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stateId != null ? stateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TState)) {
            return false;
        }
        TState other = (TState) object;
        if ((this.stateId == null && other.stateId != null) || (this.stateId != null && !this.stateId.equals(other.stateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbt.irs.entity.TState[ stateId=" + stateId + " ]";
    }
    
}
