/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tkola
 */
@Entity
@Table(name = "t_rtn_work_collection_defn")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TRtnWorkCollectionDefn.findAll", query = "SELECT t FROM TRtnWorkCollectionDefn t")
    , @NamedQuery(name = "TRtnWorkCollectionDefn.findByWorkCollectionId", query = "SELECT t FROM TRtnWorkCollectionDefn t WHERE t.workCollectionId = :workCollectionId")
    , @NamedQuery(name = "TRtnWorkCollectionDefn.findByWorkCollectionCode", query = "SELECT t FROM TRtnWorkCollectionDefn t WHERE t.workCollectionCode = :workCollectionCode")
    , @NamedQuery(name = "TRtnWorkCollectionDefn.findByDescription", query = "SELECT t FROM TRtnWorkCollectionDefn t WHERE t.description = :description")
    , @NamedQuery(name = "TRtnWorkCollectionDefn.findByCharCustElement1", query = "SELECT t FROM TRtnWorkCollectionDefn t WHERE t.charCustElement1 = :charCustElement1")
    , @NamedQuery(name = "TRtnWorkCollectionDefn.findByCharCustElement2", query = "SELECT t FROM TRtnWorkCollectionDefn t WHERE t.charCustElement2 = :charCustElement2")
    , @NamedQuery(name = "TRtnWorkCollectionDefn.findByCharCustElement3", query = "SELECT t FROM TRtnWorkCollectionDefn t WHERE t.charCustElement3 = :charCustElement3")
    , @NamedQuery(name = "TRtnWorkCollectionDefn.findByCharCustElement4", query = "SELECT t FROM TRtnWorkCollectionDefn t WHERE t.charCustElement4 = :charCustElement4")
    , @NamedQuery(name = "TRtnWorkCollectionDefn.findByCharCustElement5", query = "SELECT t FROM TRtnWorkCollectionDefn t WHERE t.charCustElement5 = :charCustElement5")
    , @NamedQuery(name = "TRtnWorkCollectionDefn.findByNumCustElement1", query = "SELECT t FROM TRtnWorkCollectionDefn t WHERE t.numCustElement1 = :numCustElement1")
    , @NamedQuery(name = "TRtnWorkCollectionDefn.findByNumCustElement2", query = "SELECT t FROM TRtnWorkCollectionDefn t WHERE t.numCustElement2 = :numCustElement2")
    , @NamedQuery(name = "TRtnWorkCollectionDefn.findByNumCustElement3", query = "SELECT t FROM TRtnWorkCollectionDefn t WHERE t.numCustElement3 = :numCustElement3")
    , @NamedQuery(name = "TRtnWorkCollectionDefn.findByNumCustElement4", query = "SELECT t FROM TRtnWorkCollectionDefn t WHERE t.numCustElement4 = :numCustElement4")
    , @NamedQuery(name = "TRtnWorkCollectionDefn.findByNumCustElement5", query = "SELECT t FROM TRtnWorkCollectionDefn t WHERE t.numCustElement5 = :numCustElement5")
    , @NamedQuery(name = "TRtnWorkCollectionDefn.findByCreatedDate", query = "SELECT t FROM TRtnWorkCollectionDefn t WHERE t.createdDate = :createdDate")
    , @NamedQuery(name = "TRtnWorkCollectionDefn.findByCreatedBy", query = "SELECT t FROM TRtnWorkCollectionDefn t WHERE t.createdBy = :createdBy")
    , @NamedQuery(name = "TRtnWorkCollectionDefn.findByLastModified", query = "SELECT t FROM TRtnWorkCollectionDefn t WHERE t.lastModified = :lastModified")
    , @NamedQuery(name = "TRtnWorkCollectionDefn.findByModifiedBy", query = "SELECT t FROM TRtnWorkCollectionDefn t WHERE t.modifiedBy = :modifiedBy")})
public class TRtnWorkCollectionDefn implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "work_collection_id")
    private Integer workCollectionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "work_collection_code")
    private String workCollectionCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "description")
    private String description;
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
    @JoinColumn(name = "entity", referencedColumnName = "entity")
    @ManyToOne(optional = false)
    private TEntity entity;
    @JoinColumn(name = "frequency", referencedColumnName = "freq_unit")
    @ManyToOne(optional = false)
    private TLkupFrequency frequency;

    public TRtnWorkCollectionDefn() {
    }

    public TRtnWorkCollectionDefn(Integer workCollectionId) {
        this.workCollectionId = workCollectionId;
    }

    public TRtnWorkCollectionDefn(Integer workCollectionId, String workCollectionCode, String description, Date createdDate, String createdBy) {
        this.workCollectionId = workCollectionId;
        this.workCollectionCode = workCollectionCode;
        this.description = description;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public Integer getWorkCollectionId() {
        return workCollectionId;
    }

    public void setWorkCollectionId(Integer workCollectionId) {
        this.workCollectionId = workCollectionId;
    }

    public String getWorkCollectionCode() {
        return workCollectionCode;
    }

    public void setWorkCollectionCode(String workCollectionCode) {
        this.workCollectionCode = workCollectionCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public TEntity getEntity() {
        return entity;
    }

    public void setEntity(TEntity entity) {
        this.entity = entity;
    }

    public TLkupFrequency getFrequency() {
        return frequency;
    }

    public void setFrequency(TLkupFrequency frequency) {
        this.frequency = frequency;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (workCollectionId != null ? workCollectionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TRtnWorkCollectionDefn)) {
            return false;
        }
        TRtnWorkCollectionDefn other = (TRtnWorkCollectionDefn) object;
        if ((this.workCollectionId == null && other.workCollectionId != null) || (this.workCollectionId != null && !this.workCollectionId.equals(other.workCollectionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getWorkCollectionCode() + "--"+this.getDescription();
    }
    
}
