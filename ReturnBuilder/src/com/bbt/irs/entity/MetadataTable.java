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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "t_rb_metadata")
@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "MetadataTable.findAll", query = "SELECT m FROM MetadataTable m")
//    , @NamedQuery(name = "MetadataTable.findById", query = "SELECT m FROM MetadataTable m WHERE m.id = :id")
//    , @NamedQuery(name = "MetadataTable.findByRiTypeCode", query = "SELECT m FROM MetadataTable m WHERE m.riTypeCode = :riTypeCode")
//    , @NamedQuery(name = "MetadataTable.findByReturnCode", query = "SELECT m FROM MetadataTable m WHERE m.returnCode = :returnCode")
//    , @NamedQuery(name = "MetadataTable.findByHeaderDesc", query = "SELECT m FROM MetadataTable m WHERE m.headerDesc = :headerDesc")
//    , @NamedQuery(name = "MetadataTable.findByXmlName", query = "SELECT m FROM MetadataTable m WHERE m.xmlName = :xmlName")
//    , @NamedQuery(name = "MetadataTable.findByHeaderPosition", query = "SELECT m FROM MetadataTable m WHERE m.headerPosition = :headerPosition")
//    , @NamedQuery(name = "MetadataTable.findByTableName", query = "SELECT m FROM MetadataTable m WHERE m.tableName = :tableName")})
public class MetadataTable implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "mdata_id")
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mdataId;
    @Column(name = "vertical_merged")
    private String verticalMerged;
    @Column(name = "horizontal_merged")
    private String horizontalMerged;
    @Column(name = "merged_range")
    private String mergedRange;
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
    @JoinColumn(name = "datasize_id")
    @ManyToOne(optional = false)
    private Datasize datasizeId;
    @JoinColumn(name = "datatype_id")
    @ManyToOne(optional = false)
    private Datatype datatypeId;

    @Column(name = "actual_label")
    private String actualTable;


    private static final long serialVersionUID = 1L;
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



   



    public String getActualTable() {
        return actualTable;
    }

    public void setActualTable(String actualTable) {
        this.actualTable = actualTable;
    }

    public MetadataTable(Long mdataId) {
        this.mdataId = mdataId;
    }

    public MetadataTable(Long mdataId, Date lastModified, String modifiedBy) {
        this.mdataId = mdataId;
        this.lastModified = lastModified;
        this.modifiedBy = modifiedBy;
    }

    public Long getMdataId() {
        return mdataId;
    }

    public void setMdataId(Long mdataId) {
        this.mdataId = mdataId;
    }

    public String getVerticalMerged() {
        return verticalMerged;
    }

    public void setVerticalMerged(String verticalMerged) {
        this.verticalMerged = verticalMerged;
    }

    public String getHorizontalMerged() {
        return horizontalMerged;
    }

    public void setHorizontalMerged(String horizontalMerged) {
        this.horizontalMerged = horizontalMerged;
    }

    public String getMergedRange() {
        return mergedRange;
    }

    public void setMergedRange(String mergedRange) {
        this.mergedRange = mergedRange;
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

    public Datasize getDatasizeId() {
        return datasizeId;
    }

    public void setDatasizeId(Datasize datasizeId) {
        this.datasizeId = datasizeId;
    }

    public Datatype getDatatypeId() {
        return datatypeId;
    }

    public void setDatatypeId(Datatype datatypeId) {
        this.datatypeId = datatypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mdataId != null ? mdataId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MetadataTable)) {
            return false;
        }
        MetadataTable other = (MetadataTable) object;
        if ((this.mdataId == null && other.mdataId != null) || (this.mdataId != null && !this.mdataId.equals(other.mdataId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbt.irs.entity.MetadataTable[ mdataId=" + mdataId + " ]";
    }

}
