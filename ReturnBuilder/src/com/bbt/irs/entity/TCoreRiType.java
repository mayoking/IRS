/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author opeyemi
 */
@Entity
@Table(name = "t_core_ri_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TCoreRiType.findAll", query = "SELECT t FROM TCoreRiType t")
    , @NamedQuery(name = "TCoreRiType.findByRiTypeCode", query = "SELECT t FROM TCoreRiType t WHERE t.riTypeCode = :riTypeCode")
    , @NamedQuery(name = "TCoreRiType.findByDescription", query = "SELECT t FROM TCoreRiType t WHERE t.description = :description")
    , @NamedQuery(name = "TCoreRiType.findByStartValidityDate", query = "SELECT t FROM TCoreRiType t WHERE t.startValidityDate = :startValidityDate")
    , @NamedQuery(name = "TCoreRiType.findByEndValidityDate", query = "SELECT t FROM TCoreRiType t WHERE t.endValidityDate = :endValidityDate")
    , @NamedQuery(name = "TCoreRiType.findByLastModified", query = "SELECT t FROM TCoreRiType t WHERE t.lastModified = :lastModified")
    , @NamedQuery(name = "TCoreRiType.findByModifiedBy", query = "SELECT t FROM TCoreRiType t WHERE t.modifiedBy = :modifiedBy")})
public class TCoreRiType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ri_type_code")
    private String riTypeCode;
    @Column(name = "description")
    private String description;
    @Column(name = "start_validity_date")
    private String startValidityDate;
    @Column(name = "end_validity_date")
    private String endValidityDate;
    @Column(name = "last_modified")
    private String lastModified;
    @Column(name = "modified_by")
    private String modifiedBy;

    public TCoreRiType() {
    }

    public TCoreRiType(String riTypeCode) {
        this.riTypeCode = riTypeCode;
    }

    public String getRiTypeCode() {
        return riTypeCode;
    }

    public void setRiTypeCode(String riTypeCode) {
        this.riTypeCode = riTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartValidityDate() {
        return startValidityDate;
    }

    public void setStartValidityDate(String startValidityDate) {
        this.startValidityDate = startValidityDate;
    }

    public String getEndValidityDate() {
        return endValidityDate;
    }

    public void setEndValidityDate(String endValidityDate) {
        this.endValidityDate = endValidityDate;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
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
        hash += (riTypeCode != null ? riTypeCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TCoreRiType)) {
            return false;
        }
        TCoreRiType other = (TCoreRiType) object;
        if ((this.riTypeCode == null && other.riTypeCode != null) || (this.riTypeCode != null && !this.riTypeCode.equals(other.riTypeCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  description;
    }
    
}
