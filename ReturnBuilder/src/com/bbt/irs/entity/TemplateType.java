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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author opeyemi
 */
@Entity
@Table(name = "t_rb_template_type")
@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "TemplateType.findAll", query = "SELECT t FROM TemplateType t")
//    , @NamedQuery(name = "TemplateType.findById", query = "SELECT t FROM TemplateType t WHERE t.id = :id")
//    , @NamedQuery(name = "TemplateType.findByDescs", query = "SELECT t FROM TemplateType t WHERE t.descs = :descs")
//    , @NamedQuery(name = "TemplateType.findByType", query = "SELECT t FROM TemplateType t WHERE t.type = :type")})
public class TemplateType implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "template_id")
    private Integer templateId;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
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

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @Column(name = "type")
    private String type;

    public TemplateType() {
    }

  

    

   

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    
    

   
    public TemplateType(Integer templateId) {
        this.templateId = templateId;
    }

    public TemplateType(Integer templateId, String description, Date lastModified, String modifiedBy) {
        this.templateId = templateId;
        this.description = description;
        this.lastModified = lastModified;
        this.modifiedBy = modifiedBy;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        hash += (templateId != null ? templateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TemplateType)) {
            return false;
        }
        TemplateType other = (TemplateType) object;
        if ((this.templateId == null && other.templateId != null) || (this.templateId != null && !this.templateId.equals(other.templateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return description;
    }
    
}
