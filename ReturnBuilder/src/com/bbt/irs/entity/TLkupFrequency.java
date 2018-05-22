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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tkola
 */
@Entity
@Table(name = "t_lkup_frequency")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TLkupFrequency.findAll", query = "SELECT t FROM TLkupFrequency t")
    , @NamedQuery(name = "TLkupFrequency.findByFreqUnit", query = "SELECT t FROM TLkupFrequency t WHERE t.freqUnit = :freqUnit")
    , @NamedQuery(name = "TLkupFrequency.findByFreqName", query = "SELECT t FROM TLkupFrequency t WHERE t.freqName = :freqName")
    , @NamedQuery(name = "TLkupFrequency.findByFreqDesc", query = "SELECT t FROM TLkupFrequency t WHERE t.freqDesc = :freqDesc")
    , @NamedQuery(name = "TLkupFrequency.findByFreqInDays", query = "SELECT t FROM TLkupFrequency t WHERE t.freqInDays = :freqInDays")
    , @NamedQuery(name = "TLkupFrequency.findByLastModified", query = "SELECT t FROM TLkupFrequency t WHERE t.lastModified = :lastModified")
    , @NamedQuery(name = "TLkupFrequency.findByModifiedBy", query = "SELECT t FROM TLkupFrequency t WHERE t.modifiedBy = :modifiedBy")})
public class TLkupFrequency implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "frequency")
    private Collection<TRtnReturns> tRtnReturnsCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "frequency")
    private Collection<TRtnWorkCollection> tRtnWorkCollectionCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "freq_unit")
    private String freqUnit;
    @Basic(optional = false)
    @Column(name = "freq_name")
    private String freqName;
    @Basic(optional = false)
    @Column(name = "freq_desc")
    private String freqDesc;
    @Basic(optional = false)
    @Column(name = "freq_in_days")
    private int freqInDays;
    @Basic(optional = false)
    @Column(name = "last_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
    @Basic(optional = false)
    @Column(name = "modified_by")
    private String modifiedBy;

    public TLkupFrequency() {
    }

    public TLkupFrequency(String freqUnit) {
        this.freqUnit = freqUnit;
    }

    public TLkupFrequency(String freqUnit, String freqName, String freqDesc, int freqInDays, Date lastModified, String modifiedBy) {
        this.freqUnit = freqUnit;
        this.freqName = freqName;
        this.freqDesc = freqDesc;
        this.freqInDays = freqInDays;
        this.lastModified = lastModified;
        this.modifiedBy = modifiedBy;
    }

    public String getFreqUnit() {
        return freqUnit;
    }

    public void setFreqUnit(String freqUnit) {
        this.freqUnit = freqUnit;
    }

    public String getFreqName() {
        return freqName;
    }

    public void setFreqName(String freqName) {
        this.freqName = freqName;
    }

    public String getFreqDesc() {
        return freqDesc;
    }

    public void setFreqDesc(String freqDesc) {
        this.freqDesc = freqDesc;
    }

    public int getFreqInDays() {
        return freqInDays;
    }

    public void setFreqInDays(int freqInDays) {
        this.freqInDays = freqInDays;
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
        hash += (freqUnit != null ? freqUnit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TLkupFrequency)) {
            return false;
        }
        TLkupFrequency other = (TLkupFrequency) object;
        if ((this.freqUnit == null && other.freqUnit != null) || (this.freqUnit != null && !this.freqUnit.equals(other.freqUnit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return freqDesc;
    }

    @XmlTransient
    public Collection<TRtnWorkCollection> getTRtnWorkCollectionCollection() {
        return tRtnWorkCollectionCollection;
    }

    public void setTRtnWorkCollectionCollection(Collection<TRtnWorkCollection> tRtnWorkCollectionCollection) {
        this.tRtnWorkCollectionCollection = tRtnWorkCollectionCollection;
    }

    @XmlTransient
    public Collection<TRtnReturns> getTRtnReturnsCollection() {
        return tRtnReturnsCollection;
    }

    public void setTRtnReturnsCollection(Collection<TRtnReturns> tRtnReturnsCollection) {
        this.tRtnReturnsCollection = tRtnReturnsCollection;
    }
    
}
