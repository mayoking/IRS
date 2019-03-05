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
@Table(name = "t_city")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TCity.findAll", query = "SELECT t FROM TCity t")
    , @NamedQuery(name = "TCity.findByCityId", query = "SELECT t FROM TCity t WHERE t.cityId = :cityId")
    , @NamedQuery(name = "TCity.findByCityCode", query = "SELECT t FROM TCity t WHERE t.cityCode = :cityCode")
    , @NamedQuery(name = "TCity.findByCityName", query = "SELECT t FROM TCity t WHERE t.cityName = :cityName")
    , @NamedQuery(name = "TCity.findByStateCode", query = "SELECT t FROM TCity t WHERE t.stateCode = :stateCode")
    , @NamedQuery(name = "TCity.findByStartValidityDate", query = "SELECT t FROM TCity t WHERE t.startValidityDate = :startValidityDate")
    , @NamedQuery(name = "TCity.findByEndValidityDate", query = "SELECT t FROM TCity t WHERE t.endValidityDate = :endValidityDate")
    , @NamedQuery(name = "TCity.findByCreatedDate", query = "SELECT t FROM TCity t WHERE t.createdDate = :createdDate")
    , @NamedQuery(name = "TCity.findByCreatedBy", query = "SELECT t FROM TCity t WHERE t.createdBy = :createdBy")
    , @NamedQuery(name = "TCity.findByLastModified", query = "SELECT t FROM TCity t WHERE t.lastModified = :lastModified")
    , @NamedQuery(name = "TCity.findByModifiedBy", query = "SELECT t FROM TCity t WHERE t.modifiedBy = :modifiedBy")})
public class TCity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "city_id")
    private Integer cityId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "city_code")
    private String cityCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "city_name")
    private String cityName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "state_code")
    private String stateCode;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "city")
    private Collection<TCoreReportingInstitution> tCoreReportingInstitutionCollection;

    public TCity() {
    }

    public TCity(Integer cityId) {
        this.cityId = cityId;
    }

    public TCity(Integer cityId, String cityCode, String cityName, String stateCode, Date startValidityDate, Date createdDate, String createdBy) {
        this.cityId = cityId;
        this.cityCode = cityCode;
        this.cityName = cityName;
        this.stateCode = stateCode;
        this.startValidityDate = startValidityDate;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cityId != null ? cityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TCity)) {
            return false;
        }
        TCity other = (TCity) object;
        if ((this.cityId == null && other.cityId != null) || (this.cityId != null && !this.cityId.equals(other.cityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbt.irs.entity.TCity[ cityId=" + cityId + " ]";
    }
    
}
