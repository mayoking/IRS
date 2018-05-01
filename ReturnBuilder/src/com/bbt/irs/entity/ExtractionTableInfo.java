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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "extraction_table_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExtractionTableInfo.findAll", query = "SELECT e FROM ExtractionTableInfo e")
    , @NamedQuery(name = "ExtractionTableInfo.findById", query = "SELECT e FROM ExtractionTableInfo e WHERE e.id = :id")
    , @NamedQuery(name = "ExtractionTableInfo.findByTemplateCode", query = "SELECT e FROM ExtractionTableInfo e WHERE e.templateCode = :templateCode")
    , @NamedQuery(name = "ExtractionTableInfo.findByTemplateName", query = "SELECT e FROM ExtractionTableInfo e WHERE e.templateName = :templateName")})
public class ExtractionTableInfo implements Serializable {

    @Basic(optional = false)
    @Column(name = "template_code")
    private String templateCode;
    @Basic(optional = false)
    @Column(name = "template_name")
    private String templateName;

    @Basic(optional = false)
    @Column(name = "template_type")
    private int templateType;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public ExtractionTableInfo() {
    }

    public ExtractionTableInfo(Long id) {
        this.id = id;
    }

   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExtractionTableInfo)) {
            return false;
        }
        ExtractionTableInfo other = (ExtractionTableInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbt.irs.entity.ExtractionTableInfo[ id=" + id + " ]";
    }

    public int getTemplateType() {
        return templateType;
    }

    public void setTemplateType(int templateType) {
        this.templateType = templateType;
    }

 

  
    
}
