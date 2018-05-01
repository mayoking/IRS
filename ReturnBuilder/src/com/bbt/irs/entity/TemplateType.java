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
@Table(name = "template_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TemplateType.findAll", query = "SELECT t FROM TemplateType t")
    , @NamedQuery(name = "TemplateType.findById", query = "SELECT t FROM TemplateType t WHERE t.id = :id")
    , @NamedQuery(name = "TemplateType.findByDescs", query = "SELECT t FROM TemplateType t WHERE t.descs = :descs")
    , @NamedQuery(name = "TemplateType.findByType", query = "SELECT t FROM TemplateType t WHERE t.type = :type")})
public class TemplateType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "descs")
    private String descs;
    @Basic(optional = false)
    @Column(name = "type")
    private String type;

    public TemplateType() {
    }

    public TemplateType(Integer id) {
        this.id = id;
    }

    public TemplateType(Integer id, String descs, String type) {
        this.id = id;
        this.descs = descs;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        if (!(object instanceof TemplateType)) {
            return false;
        }
        TemplateType other = (TemplateType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descs;
    }
    
}
