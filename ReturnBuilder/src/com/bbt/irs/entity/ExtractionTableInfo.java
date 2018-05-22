/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author opeyemi
 */
@Entity
@Table(name = "t_rb_extraction_info")
@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "ExtractionTableInfo.findAll", query = "SELECT e FROM ExtractionTableInfo e")
//    , @NamedQuery(name = "ExtractionTableInfo.findById", query = "SELECT e FROM ExtractionTableInfo e WHERE e.id = :id")
//    , @NamedQuery(name = "ExtractionTableInfo.findByTemplateCode", query = "SELECT e FROM ExtractionTableInfo e WHERE e.templateCode = :templateCode")
//    , @NamedQuery(name = "ExtractionTableInfo.findByTemplateName", query = "SELECT e FROM ExtractionTableInfo e WHERE e.templateName = :templateName")})
public class ExtractionTableInfo implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "extr_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long extrId;
    @Basic(optional = false)
    @Column(name = "template_type")
    private int templateType;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "created_by")
    private String createdBy;
    @Basic(optional = false)
    @Column(name = "last_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
    @Basic(optional = false)
    @Column(name = "modified_by")
    private String modifiedBy;

    @Basic(optional = false)
    @Column(name = "template_code")
    private String templateCode;
    @Basic(optional = false)
    @Column(name = "template_name")
    private String templateName;

    private static final long serialVersionUID = 1L;

    public ExtractionTableInfo() {
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public int getTemplateType() {
        return templateType;
    }

    public void setTemplateType(int templateType) {
        this.templateType = templateType;
    }

    public ExtractionTableInfo(Long extrId) {
        this.extrId = extrId;
    }

    public ExtractionTableInfo(Long extrId, int templateType, Date lastModified, String modifiedBy) {
        this.extrId = extrId;
        this.templateType = templateType;
        this.lastModified = lastModified;
        this.modifiedBy = modifiedBy;
    }

    public Long getExtrId() {
        return extrId;
    }

    public void setExtrId(Long extrId) {
        this.extrId = extrId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (extrId != null ? extrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExtractionTableInfo)) {
            return false;
        }
        ExtractionTableInfo other = (ExtractionTableInfo) object;
        if ((this.extrId == null && other.extrId != null) || (this.extrId != null && !this.extrId.equals(other.extrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbt.irs.entity.ExtractionTableInfo[ extrId=" + extrId + " ]";
    }

}
