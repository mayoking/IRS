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
@Table(name = "metadata_table")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MetadataTable.findAll", query = "SELECT m FROM MetadataTable m")
    , @NamedQuery(name = "MetadataTable.findById", query = "SELECT m FROM MetadataTable m WHERE m.id = :id")
    , @NamedQuery(name = "MetadataTable.findByRiTypeCode", query = "SELECT m FROM MetadataTable m WHERE m.riTypeCode = :riTypeCode")
    , @NamedQuery(name = "MetadataTable.findByReturnCode", query = "SELECT m FROM MetadataTable m WHERE m.returnCode = :returnCode")
    , @NamedQuery(name = "MetadataTable.findByHeaderDesc", query = "SELECT m FROM MetadataTable m WHERE m.headerDesc = :headerDesc")
    , @NamedQuery(name = "MetadataTable.findByXmlName", query = "SELECT m FROM MetadataTable m WHERE m.xmlName = :xmlName")
    , @NamedQuery(name = "MetadataTable.findByHeaderPosition", query = "SELECT m FROM MetadataTable m WHERE m.headerPosition = :headerPosition")
    , @NamedQuery(name = "MetadataTable.findByTableName", query = "SELECT m FROM MetadataTable m WHERE m.tableName = :tableName")})
public class MetadataTable implements Serializable {

    @Basic(optional = false)
    @Column(name = "data_type")
    private int dataType;
    @Basic(optional = false)
    @Column(name = "data_size")
    private int dataSize;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ri_type_code")
    private String riTypeCode;
    @Basic(optional = false)
    @Column(name = "return_code")
    private String returnCode;
    @Basic(optional = false)
    @Column(name = "header_desc")
    private String headerDesc;
    @Basic(optional = false)
    @Column(name = "xml_name")
    private String xmlName;
    @Basic(optional = false)
    @Column(name = "header_position")
    private String headerPosition;
    @Basic(optional = false)
    @Column(name = "table_name")
    private String tableName;

    public MetadataTable() {
    }

    public MetadataTable(Integer id) {
        this.id = id;
    }

    public MetadataTable(Integer id, String riTypeCode, String returnCode, String headerDesc, String xmlName, String headerPosition, String tableName) {
        this.id = id;
        this.riTypeCode = riTypeCode;
        this.returnCode = returnCode;
        this.headerDesc = headerDesc;
        this.xmlName = xmlName;
        this.headerPosition = headerPosition;
        this.tableName = tableName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRiTypeCode() {
        return riTypeCode;
    }

    public void setRiTypeCode(String riTypeCode) {
        this.riTypeCode = riTypeCode;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getHeaderDesc() {
        return headerDesc;
    }

    public void setHeaderDesc(String headerDesc) {
        this.headerDesc = headerDesc;
    }

    public String getXmlName() {
        return xmlName;
    }

    public void setXmlName(String xmlName) {
        this.xmlName = xmlName;
    }

    public String getHeaderPosition() {
        return headerPosition;
    }

    public void setHeaderPosition(String headerPosition) {
        this.headerPosition = headerPosition;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
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
        if (!(object instanceof MetadataTable)) {
            return false;
        }
        MetadataTable other = (MetadataTable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbt.irs.entity.MetadataTable[ id=" + id + " ]";
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public int getDataSize() {
        return dataSize;
    }

    public void setDataSize(int dataSize) {
        this.dataSize = dataSize;
    }

}
