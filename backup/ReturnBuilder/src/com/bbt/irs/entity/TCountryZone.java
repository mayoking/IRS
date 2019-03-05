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
@Table(name = "t_country_zone")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TCountryZone.findAll", query = "SELECT t FROM TCountryZone t")
    , @NamedQuery(name = "TCountryZone.findByZoneId", query = "SELECT t FROM TCountryZone t WHERE t.zoneId = :zoneId")
    , @NamedQuery(name = "TCountryZone.findByZoneCode", query = "SELECT t FROM TCountryZone t WHERE t.zoneCode = :zoneCode")
    , @NamedQuery(name = "TCountryZone.findByZoneName", query = "SELECT t FROM TCountryZone t WHERE t.zoneName = :zoneName")
    , @NamedQuery(name = "TCountryZone.findByCountryCode", query = "SELECT t FROM TCountryZone t WHERE t.countryCode = :countryCode")
    , @NamedQuery(name = "TCountryZone.findByStartValidityDate", query = "SELECT t FROM TCountryZone t WHERE t.startValidityDate = :startValidityDate")
    , @NamedQuery(name = "TCountryZone.findByEndValidityDate", query = "SELECT t FROM TCountryZone t WHERE t.endValidityDate = :endValidityDate")
    , @NamedQuery(name = "TCountryZone.findByCreatedDate", query = "SELECT t FROM TCountryZone t WHERE t.createdDate = :createdDate")
    , @NamedQuery(name = "TCountryZone.findByCreatedBy", query = "SELECT t FROM TCountryZone t WHERE t.createdBy = :createdBy")
    , @NamedQuery(name = "TCountryZone.findByLastModified", query = "SELECT t FROM TCountryZone t WHERE t.lastModified = :lastModified")
    , @NamedQuery(name = "TCountryZone.findByModifiedBy", query = "SELECT t FROM TCountryZone t WHERE t.modifiedBy = :modifiedBy")})
public class TCountryZone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "zone_id")
    private Integer zoneId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "zone_code")
    private String zoneCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "zone_name")
    private String zoneName;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "countryZone")
    private Collection<TCoreReportingInstitution> tCoreReportingInstitutionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zoneCode")
    private Collection<TState> tStateCollection;

    public TCountryZone() {
    }

    public TCountryZone(Integer zoneId) {
        this.zoneId = zoneId;
    }

    public TCountryZone(Integer zoneId, String zoneCode, String zoneName, String countryCode, Date startValidityDate, Date endValidityDate, Date createdDate, String createdBy) {
        this.zoneId = zoneId;
        this.zoneCode = zoneCode;
        this.zoneName = zoneName;
        this.countryCode = countryCode;
        this.startValidityDate = startValidityDate;
        this.endValidityDate = endValidityDate;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public Integer getZoneId() {
        return zoneId;
    }

    public void setZoneId(Integer zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
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
    public Collection<TCoreReportingInstitution> getTCoreReportingInstitutionCollection() {
        return tCoreReportingInstitutionCollection;
    }

    public void setTCoreReportingInstitutionCollection(Collection<TCoreReportingInstitution> tCoreReportingInstitutionCollection) {
        this.tCoreReportingInstitutionCollection = tCoreReportingInstitutionCollection;
    }

    @XmlTransient
    public Collection<TState> getTStateCollection() {
        return tStateCollection;
    }

    public void setTStateCollection(Collection<TState> tStateCollection) {
        this.tStateCollection = tStateCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (zoneId != null ? zoneId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TCountryZone)) {
            return false;
        }
        TCountryZone other = (TCountryZone) object;
        if ((this.zoneId == null && other.zoneId != null) || (this.zoneId != null && !this.zoneId.equals(other.zoneId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbt.irs.entity.TCountryZone[ zoneId=" + zoneId + " ]";
    }
    
}
