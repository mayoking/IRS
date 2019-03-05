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
@Table(name = "t_entity")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TEntity.findAll", query = "SELECT t FROM TEntity t")
    , @NamedQuery(name = "TEntity.findByEntity", query = "SELECT t FROM TEntity t WHERE t.entity = :entity")
    , @NamedQuery(name = "TEntity.findByName", query = "SELECT t FROM TEntity t WHERE t.name = :name")
    , @NamedQuery(name = "TEntity.findByDescription", query = "SELECT t FROM TEntity t WHERE t.description = :description")
    , @NamedQuery(name = "TEntity.findByAccountCategory", query = "SELECT t FROM TEntity t WHERE t.accountCategory = :accountCategory")
    , @NamedQuery(name = "TEntity.findByPrevCobDate", query = "SELECT t FROM TEntity t WHERE t.prevCobDate = :prevCobDate")
    , @NamedQuery(name = "TEntity.findByCobDate", query = "SELECT t FROM TEntity t WHERE t.cobDate = :cobDate")
    , @NamedQuery(name = "TEntity.findByNextCobDate", query = "SELECT t FROM TEntity t WHERE t.nextCobDate = :nextCobDate")
    , @NamedQuery(name = "TEntity.findByCurrentYear", query = "SELECT t FROM TEntity t WHERE t.currentYear = :currentYear")
    , @NamedQuery(name = "TEntity.findByCurrentPeriod", query = "SELECT t FROM TEntity t WHERE t.currentPeriod = :currentPeriod")
    , @NamedQuery(name = "TEntity.findByReportingYear", query = "SELECT t FROM TEntity t WHERE t.reportingYear = :reportingYear")
    , @NamedQuery(name = "TEntity.findByReportingPeriod", query = "SELECT t FROM TEntity t WHERE t.reportingPeriod = :reportingPeriod")
    , @NamedQuery(name = "TEntity.findByEdNumPeriodsForward", query = "SELECT t FROM TEntity t WHERE t.edNumPeriodsForward = :edNumPeriodsForward")
    , @NamedQuery(name = "TEntity.findByEdNumPeriodsBack", query = "SELECT t FROM TEntity t WHERE t.edNumPeriodsBack = :edNumPeriodsBack")
    , @NamedQuery(name = "TEntity.findByVdNumPeriodsForward", query = "SELECT t FROM TEntity t WHERE t.vdNumPeriodsForward = :vdNumPeriodsForward")
    , @NamedQuery(name = "TEntity.findByVdNumPeriodsBack", query = "SELECT t FROM TEntity t WHERE t.vdNumPeriodsBack = :vdNumPeriodsBack")
    , @NamedQuery(name = "TEntity.findByLclCurrency", query = "SELECT t FROM TEntity t WHERE t.lclCurrency = :lclCurrency")
    , @NamedQuery(name = "TEntity.findByRptCurrency", query = "SELECT t FROM TEntity t WHERE t.rptCurrency = :rptCurrency")
    , @NamedQuery(name = "TEntity.findByCharCustElement1", query = "SELECT t FROM TEntity t WHERE t.charCustElement1 = :charCustElement1")
    , @NamedQuery(name = "TEntity.findByCharCustElement2", query = "SELECT t FROM TEntity t WHERE t.charCustElement2 = :charCustElement2")
    , @NamedQuery(name = "TEntity.findByCharCustElement3", query = "SELECT t FROM TEntity t WHERE t.charCustElement3 = :charCustElement3")
    , @NamedQuery(name = "TEntity.findByCharCustElement4", query = "SELECT t FROM TEntity t WHERE t.charCustElement4 = :charCustElement4")
    , @NamedQuery(name = "TEntity.findByCharCustElement5", query = "SELECT t FROM TEntity t WHERE t.charCustElement5 = :charCustElement5")
    , @NamedQuery(name = "TEntity.findByNumCustElement1", query = "SELECT t FROM TEntity t WHERE t.numCustElement1 = :numCustElement1")
    , @NamedQuery(name = "TEntity.findByNumCustElement2", query = "SELECT t FROM TEntity t WHERE t.numCustElement2 = :numCustElement2")
    , @NamedQuery(name = "TEntity.findByNumCustElement3", query = "SELECT t FROM TEntity t WHERE t.numCustElement3 = :numCustElement3")
    , @NamedQuery(name = "TEntity.findByNumCustElement4", query = "SELECT t FROM TEntity t WHERE t.numCustElement4 = :numCustElement4")
    , @NamedQuery(name = "TEntity.findByNumCustElement5", query = "SELECT t FROM TEntity t WHERE t.numCustElement5 = :numCustElement5")
    , @NamedQuery(name = "TEntity.findByLastModified", query = "SELECT t FROM TEntity t WHERE t.lastModified = :lastModified")
    , @NamedQuery(name = "TEntity.findByModifiedBy", query = "SELECT t FROM TEntity t WHERE t.modifiedBy = :modifiedBy")})
public class TEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "entity")
    private String entity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "account_category")
    private String accountCategory;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prev_cob_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date prevCobDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cob_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cobDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "next_cob_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date nextCobDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "current_year")
    private int currentYear;
    @Basic(optional = false)
    @NotNull
    @Column(name = "current_period")
    private int currentPeriod;
    @Basic(optional = false)
    @NotNull
    @Column(name = "reporting_year")
    private int reportingYear;
    @Basic(optional = false)
    @NotNull
    @Column(name = "reporting_period")
    private int reportingPeriod;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ed_num_periods_forward")
    private int edNumPeriodsForward;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ed_num_periods_back")
    private int edNumPeriodsBack;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vd_num_periods_forward")
    private int vdNumPeriodsForward;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vd_num_periods_back")
    private int vdNumPeriodsBack;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "lcl_currency")
    private String lclCurrency;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "rpt_currency")
    private String rptCurrency;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "char_cust_element1")
    private String charCustElement1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "char_cust_element2")
    private String charCustElement2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "char_cust_element3")
    private String charCustElement3;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "char_cust_element4")
    private String charCustElement4;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "char_cust_element5")
    private String charCustElement5;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_cust_element1")
    private BigDecimal numCustElement1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_cust_element2")
    private BigDecimal numCustElement2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_cust_element3")
    private BigDecimal numCustElement3;
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_cust_element4")
    private BigDecimal numCustElement4;
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_cust_element5")
    private BigDecimal numCustElement5;
    @Basic(optional = false)
    @NotNull
    @Column(name = "last_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "modified_by")
    private String modifiedBy;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entity")
    private Collection<TRtnWorkCollectionDefn> tRtnWorkCollectionDefnCollection;
    @JoinColumn(name = "calendar", referencedColumnName = "calendar")
    @ManyToOne(optional = false)
    private TCalendar calendar;

    public TEntity() {
    }

    public TEntity(String entity) {
        this.entity = entity;
    }

    public TEntity(String entity, String name, String description, String accountCategory, Date prevCobDate, Date cobDate, Date nextCobDate, int currentYear, int currentPeriod, int reportingYear, int reportingPeriod, int edNumPeriodsForward, int edNumPeriodsBack, int vdNumPeriodsForward, int vdNumPeriodsBack, String lclCurrency, String rptCurrency, String charCustElement1, String charCustElement2, String charCustElement3, String charCustElement4, String charCustElement5, BigDecimal numCustElement1, BigDecimal numCustElement2, BigDecimal numCustElement3, BigDecimal numCustElement4, BigDecimal numCustElement5, Date lastModified, String modifiedBy) {
        this.entity = entity;
        this.name = name;
        this.description = description;
        this.accountCategory = accountCategory;
        this.prevCobDate = prevCobDate;
        this.cobDate = cobDate;
        this.nextCobDate = nextCobDate;
        this.currentYear = currentYear;
        this.currentPeriod = currentPeriod;
        this.reportingYear = reportingYear;
        this.reportingPeriod = reportingPeriod;
        this.edNumPeriodsForward = edNumPeriodsForward;
        this.edNumPeriodsBack = edNumPeriodsBack;
        this.vdNumPeriodsForward = vdNumPeriodsForward;
        this.vdNumPeriodsBack = vdNumPeriodsBack;
        this.lclCurrency = lclCurrency;
        this.rptCurrency = rptCurrency;
        this.charCustElement1 = charCustElement1;
        this.charCustElement2 = charCustElement2;
        this.charCustElement3 = charCustElement3;
        this.charCustElement4 = charCustElement4;
        this.charCustElement5 = charCustElement5;
        this.numCustElement1 = numCustElement1;
        this.numCustElement2 = numCustElement2;
        this.numCustElement3 = numCustElement3;
        this.numCustElement4 = numCustElement4;
        this.numCustElement5 = numCustElement5;
        this.lastModified = lastModified;
        this.modifiedBy = modifiedBy;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccountCategory() {
        return accountCategory;
    }

    public void setAccountCategory(String accountCategory) {
        this.accountCategory = accountCategory;
    }

    public Date getPrevCobDate() {
        return prevCobDate;
    }

    public void setPrevCobDate(Date prevCobDate) {
        this.prevCobDate = prevCobDate;
    }

    public Date getCobDate() {
        return cobDate;
    }

    public void setCobDate(Date cobDate) {
        this.cobDate = cobDate;
    }

    public Date getNextCobDate() {
        return nextCobDate;
    }

    public void setNextCobDate(Date nextCobDate) {
        this.nextCobDate = nextCobDate;
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
    }

    public int getCurrentPeriod() {
        return currentPeriod;
    }

    public void setCurrentPeriod(int currentPeriod) {
        this.currentPeriod = currentPeriod;
    }

    public int getReportingYear() {
        return reportingYear;
    }

    public void setReportingYear(int reportingYear) {
        this.reportingYear = reportingYear;
    }

    public int getReportingPeriod() {
        return reportingPeriod;
    }

    public void setReportingPeriod(int reportingPeriod) {
        this.reportingPeriod = reportingPeriod;
    }

    public int getEdNumPeriodsForward() {
        return edNumPeriodsForward;
    }

    public void setEdNumPeriodsForward(int edNumPeriodsForward) {
        this.edNumPeriodsForward = edNumPeriodsForward;
    }

    public int getEdNumPeriodsBack() {
        return edNumPeriodsBack;
    }

    public void setEdNumPeriodsBack(int edNumPeriodsBack) {
        this.edNumPeriodsBack = edNumPeriodsBack;
    }

    public int getVdNumPeriodsForward() {
        return vdNumPeriodsForward;
    }

    public void setVdNumPeriodsForward(int vdNumPeriodsForward) {
        this.vdNumPeriodsForward = vdNumPeriodsForward;
    }

    public int getVdNumPeriodsBack() {
        return vdNumPeriodsBack;
    }

    public void setVdNumPeriodsBack(int vdNumPeriodsBack) {
        this.vdNumPeriodsBack = vdNumPeriodsBack;
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
    public Collection<TRtnWorkCollectionDefn> getTRtnWorkCollectionDefnCollection() {
        return tRtnWorkCollectionDefnCollection;
    }

    public void setTRtnWorkCollectionDefnCollection(Collection<TRtnWorkCollectionDefn> tRtnWorkCollectionDefnCollection) {
        this.tRtnWorkCollectionDefnCollection = tRtnWorkCollectionDefnCollection;
    }

    public TCalendar getCalendar() {
        return calendar;
    }

    public void setCalendar(TCalendar calendar) {
        this.calendar = calendar;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (entity != null ? entity.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TEntity)) {
            return false;
        }
        TEntity other = (TEntity) object;
        if ((this.entity == null && other.entity != null) || (this.entity != null && !this.entity.equals(other.entity))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbt.irs.entity.TEntity[ entity=" + entity + " ]";
    }
    
}
