/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "t_core_reporting_institution")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TCoreReportingInstitution.findAll", query = "SELECT t FROM TCoreReportingInstitution t")
    , @NamedQuery(name = "TCoreReportingInstitution.findByRiId", query = "SELECT t FROM TCoreReportingInstitution t WHERE t.riId = :riId")
    , @NamedQuery(name = "TCoreReportingInstitution.findByRiCode", query = "SELECT t FROM TCoreReportingInstitution t WHERE t.riCode = :riCode")
    , @NamedQuery(name = "TCoreReportingInstitution.findByShortname", query = "SELECT t FROM TCoreReportingInstitution t WHERE t.shortname = :shortname")
    , @NamedQuery(name = "TCoreReportingInstitution.findByFullname", query = "SELECT t FROM TCoreReportingInstitution t WHERE t.fullname = :fullname")
    , @NamedQuery(name = "TCoreReportingInstitution.findByAddress", query = "SELECT t FROM TCoreReportingInstitution t WHERE t.address = :address")
    , @NamedQuery(name = "TCoreReportingInstitution.findByCountry", query = "SELECT t FROM TCoreReportingInstitution t WHERE t.country = :country")
    , @NamedQuery(name = "TCoreReportingInstitution.findByPostcode", query = "SELECT t FROM TCoreReportingInstitution t WHERE t.postcode = :postcode")
    , @NamedQuery(name = "TCoreReportingInstitution.findByBiccode", query = "SELECT t FROM TCoreReportingInstitution t WHERE t.biccode = :biccode")
    , @NamedQuery(name = "TCoreReportingInstitution.findByIsocode", query = "SELECT t FROM TCoreReportingInstitution t WHERE t.isocode = :isocode")
    , @NamedQuery(name = "TCoreReportingInstitution.findByTelephone", query = "SELECT t FROM TCoreReportingInstitution t WHERE t.telephone = :telephone")
    , @NamedQuery(name = "TCoreReportingInstitution.findByMobile", query = "SELECT t FROM TCoreReportingInstitution t WHERE t.mobile = :mobile")
    , @NamedQuery(name = "TCoreReportingInstitution.findByFax", query = "SELECT t FROM TCoreReportingInstitution t WHERE t.fax = :fax")
    , @NamedQuery(name = "TCoreReportingInstitution.findByEmail", query = "SELECT t FROM TCoreReportingInstitution t WHERE t.email = :email")
    , @NamedQuery(name = "TCoreReportingInstitution.findByLclCurrency", query = "SELECT t FROM TCoreReportingInstitution t WHERE t.lclCurrency = :lclCurrency")
    , @NamedQuery(name = "TCoreReportingInstitution.findByRptCurrency", query = "SELECT t FROM TCoreReportingInstitution t WHERE t.rptCurrency = :rptCurrency")
    , @NamedQuery(name = "TCoreReportingInstitution.findByIsActive", query = "SELECT t FROM TCoreReportingInstitution t WHERE t.isActive = :isActive")
    , @NamedQuery(name = "TCoreReportingInstitution.findByAdminUserLimit", query = "SELECT t FROM TCoreReportingInstitution t WHERE t.adminUserLimit = :adminUserLimit")
    , @NamedQuery(name = "TCoreReportingInstitution.findByUsersByAdminsCount", query = "SELECT t FROM TCoreReportingInstitution t WHERE t.usersByAdminsCount = :usersByAdminsCount")
    , @NamedQuery(name = "TCoreReportingInstitution.findByStartValidityDate", query = "SELECT t FROM TCoreReportingInstitution t WHERE t.startValidityDate = :startValidityDate")
    , @NamedQuery(name = "TCoreReportingInstitution.findByEndValidityDate", query = "SELECT t FROM TCoreReportingInstitution t WHERE t.endValidityDate = :endValidityDate")
    , @NamedQuery(name = "TCoreReportingInstitution.findByCharCustElement1", query = "SELECT t FROM TCoreReportingInstitution t WHERE t.charCustElement1 = :charCustElement1")
    , @NamedQuery(name = "TCoreReportingInstitution.findByCharCustElement2", query = "SELECT t FROM TCoreReportingInstitution t WHERE t.charCustElement2 = :charCustElement2")
    , @NamedQuery(name = "TCoreReportingInstitution.findByCharCustElement3", query = "SELECT t FROM TCoreReportingInstitution t WHERE t.charCustElement3 = :charCustElement3")
    , @NamedQuery(name = "TCoreReportingInstitution.findByCharCustElement4", query = "SELECT t FROM TCoreReportingInstitution t WHERE t.charCustElement4 = :charCustElement4")
    , @NamedQuery(name = "TCoreReportingInstitution.findByCharCustElement5", query = "SELECT t FROM TCoreReportingInstitution t WHERE t.charCustElement5 = :charCustElement5")
    , @NamedQuery(name = "TCoreReportingInstitution.findByNumCustElement1", query = "SELECT t FROM TCoreReportingInstitution t WHERE t.numCustElement1 = :numCustElement1")
    , @NamedQuery(name = "TCoreReportingInstitution.findByNumCustElement2", query = "SELECT t FROM TCoreReportingInstitution t WHERE t.numCustElement2 = :numCustElement2")
    , @NamedQuery(name = "TCoreReportingInstitution.findByNumCustElement3", query = "SELECT t FROM TCoreReportingInstitution t WHERE t.numCustElement3 = :numCustElement3")
    , @NamedQuery(name = "TCoreReportingInstitution.findByNumCustElement4", query = "SELECT t FROM TCoreReportingInstitution t WHERE t.numCustElement4 = :numCustElement4")
    , @NamedQuery(name = "TCoreReportingInstitution.findByNumCustElement5", query = "SELECT t FROM TCoreReportingInstitution t WHERE t.numCustElement5 = :numCustElement5")
    , @NamedQuery(name = "TCoreReportingInstitution.findByCreatedDate", query = "SELECT t FROM TCoreReportingInstitution t WHERE t.createdDate = :createdDate")
    , @NamedQuery(name = "TCoreReportingInstitution.findByCreatedBy", query = "SELECT t FROM TCoreReportingInstitution t WHERE t.createdBy = :createdBy")
    , @NamedQuery(name = "TCoreReportingInstitution.findByLastModified", query = "SELECT t FROM TCoreReportingInstitution t WHERE t.lastModified = :lastModified")
    , @NamedQuery(name = "TCoreReportingInstitution.findByModifiedBy", query = "SELECT t FROM TCoreReportingInstitution t WHERE t.modifiedBy = :modifiedBy")})
public class TCoreReportingInstitution implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ri_id")
    private Integer riId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "ri_code")
    private String riCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "shortname")
    private String shortname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "fullname")
    private String fullname;
    @Size(max = 300)
    @Column(name = "address")
    private String address;
    @Size(max = 3)
    @Column(name = "country")
    private String country;
    @Size(max = 40)
    @Column(name = "postcode")
    private String postcode;
    @Size(max = 20)
    @Column(name = "biccode")
    private String biccode;
    @Size(max = 20)
    @Column(name = "isocode")
    private String isocode;
    @Size(max = 30)
    @Column(name = "telephone")
    private String telephone;
    @Size(max = 30)
    @Column(name = "mobile")
    private String mobile;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 30)
    @Column(name = "fax")
    private String fax;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 320)
    @Column(name = "email")
    private String email;
    @Size(max = 3)
    @Column(name = "lcl_currency")
    private String lclCurrency;
    @Size(max = 3)
    @Column(name = "rpt_currency")
    private String rptCurrency;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_active")
    private boolean isActive;
    @Column(name = "admin_user_limit")
    private Integer adminUserLimit;
    @Column(name = "users_by_admins_count")
    private Integer usersByAdminsCount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_validity_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startValidityDate;
    @Column(name = "end_validity_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endValidityDate;
    @Size(max = 40)
    @Column(name = "char_cust_element1")
    private String charCustElement1;
    @Size(max = 40)
    @Column(name = "char_cust_element2")
    private String charCustElement2;
    @Size(max = 40)
    @Column(name = "char_cust_element3")
    private String charCustElement3;
    @Size(max = 40)
    @Column(name = "char_cust_element4")
    private String charCustElement4;
    @Size(max = 40)
    @Column(name = "char_cust_element5")
    private String charCustElement5;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "num_cust_element1")
    private BigDecimal numCustElement1;
    @Column(name = "num_cust_element2")
    private BigDecimal numCustElement2;
    @Column(name = "num_cust_element3")
    private BigDecimal numCustElement3;
    @Column(name = "num_cust_element4")
    private BigDecimal numCustElement4;
    @Column(name = "num_cust_element5")
    private BigDecimal numCustElement5;
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
    @JoinColumn(name = "city", referencedColumnName = "city_code")
    @ManyToOne(optional = false)
    private TCity city;
    @JoinColumn(name = "country_zone", referencedColumnName = "zone_code")
    @ManyToOne(optional = false)
    private TCountryZone countryZone;
    @JoinColumn(name = "lga", referencedColumnName = "lga_code")
    @ManyToOne(optional = false)
    private TLgaCode lga;
    @JoinColumn(name = "state", referencedColumnName = "state_code")
    @ManyToOne(optional = false)
    private TState state;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "riId")
    private Collection<TRtnWorkCollectionSchedule> tRtnWorkCollectionScheduleCollection;

    public TCoreReportingInstitution() {
    }

    public TCoreReportingInstitution(Integer riId) {
        this.riId = riId;
    }

    public TCoreReportingInstitution(Integer riId, String riCode, String shortname, String fullname, boolean isActive, Date startValidityDate, Date createdDate, String createdBy) {
        this.riId = riId;
        this.riCode = riCode;
        this.shortname = shortname;
        this.fullname = fullname;
        this.isActive = isActive;
        this.startValidityDate = startValidityDate;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public Integer getRiId() {
        return riId;
    }

    public void setRiId(Integer riId) {
        this.riId = riId;
    }

    public String getRiCode() {
        return riCode;
    }

    public void setRiCode(String riCode) {
        this.riCode = riCode;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getBiccode() {
        return biccode;
    }

    public void setBiccode(String biccode) {
        this.biccode = biccode;
    }

    public String getIsocode() {
        return isocode;
    }

    public void setIsocode(String isocode) {
        this.isocode = isocode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLclCurrency() {
        return lclCurrency;
    }

    public void setLclCurrency(String lclCurrency) {
        this.lclCurrency = lclCurrency;
    }

    public String getRptCurrency() {
        return rptCurrency;
    }

    public void setRptCurrency(String rptCurrency) {
        this.rptCurrency = rptCurrency;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Integer getAdminUserLimit() {
        return adminUserLimit;
    }

    public void setAdminUserLimit(Integer adminUserLimit) {
        this.adminUserLimit = adminUserLimit;
    }

    public Integer getUsersByAdminsCount() {
        return usersByAdminsCount;
    }

    public void setUsersByAdminsCount(Integer usersByAdminsCount) {
        this.usersByAdminsCount = usersByAdminsCount;
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

    public String getCharCustElement1() {
        return charCustElement1;
    }

    public void setCharCustElement1(String charCustElement1) {
        this.charCustElement1 = charCustElement1;
    }

    public String getCharCustElement2() {
        return charCustElement2;
    }

    public void setCharCustElement2(String charCustElement2) {
        this.charCustElement2 = charCustElement2;
    }

    public String getCharCustElement3() {
        return charCustElement3;
    }

    public void setCharCustElement3(String charCustElement3) {
        this.charCustElement3 = charCustElement3;
    }

    public String getCharCustElement4() {
        return charCustElement4;
    }

    public void setCharCustElement4(String charCustElement4) {
        this.charCustElement4 = charCustElement4;
    }

    public String getCharCustElement5() {
        return charCustElement5;
    }

    public void setCharCustElement5(String charCustElement5) {
        this.charCustElement5 = charCustElement5;
    }

    public BigDecimal getNumCustElement1() {
        return numCustElement1;
    }

    public void setNumCustElement1(BigDecimal numCustElement1) {
        this.numCustElement1 = numCustElement1;
    }

    public BigDecimal getNumCustElement2() {
        return numCustElement2;
    }

    public void setNumCustElement2(BigDecimal numCustElement2) {
        this.numCustElement2 = numCustElement2;
    }

    public BigDecimal getNumCustElement3() {
        return numCustElement3;
    }

    public void setNumCustElement3(BigDecimal numCustElement3) {
        this.numCustElement3 = numCustElement3;
    }

    public BigDecimal getNumCustElement4() {
        return numCustElement4;
    }

    public void setNumCustElement4(BigDecimal numCustElement4) {
        this.numCustElement4 = numCustElement4;
    }

    public BigDecimal getNumCustElement5() {
        return numCustElement5;
    }

    public void setNumCustElement5(BigDecimal numCustElement5) {
        this.numCustElement5 = numCustElement5;
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

    public TCity getCity() {
        return city;
    }

    public void setCity(TCity city) {
        this.city = city;
    }

    public TCountryZone getCountryZone() {
        return countryZone;
    }

    public void setCountryZone(TCountryZone countryZone) {
        this.countryZone = countryZone;
    }

    public TLgaCode getLga() {
        return lga;
    }

    public void setLga(TLgaCode lga) {
        this.lga = lga;
    }

    public TState getState() {
        return state;
    }

    public void setState(TState state) {
        this.state = state;
    }

    @XmlTransient
    public Collection<TRtnWorkCollectionSchedule> getTRtnWorkCollectionScheduleCollection() {
        return tRtnWorkCollectionScheduleCollection;
    }

    public void setTRtnWorkCollectionScheduleCollection(Collection<TRtnWorkCollectionSchedule> tRtnWorkCollectionScheduleCollection) {
        this.tRtnWorkCollectionScheduleCollection = tRtnWorkCollectionScheduleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (riId != null ? riId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TCoreReportingInstitution)) {
            return false;
        }
        TCoreReportingInstitution other = (TCoreReportingInstitution) object;
        if ((this.riId == null && other.riId != null) || (this.riId != null && !this.riId.equals(other.riId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbt.irs.entity.TCoreReportingInstitution[ riId=" + riId + " ]";
    }
    
}
