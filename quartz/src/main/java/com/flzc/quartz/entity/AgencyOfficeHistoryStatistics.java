package com.flzc.quartz.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by iverson on 2015/11/30.
 */
@Entity
@Table(name = "agency_office_week_statistics")
@DynamicInsert
@DynamicUpdate
public class AgencyOfficeHistoryStatistics {
    private Integer id;
    private Integer officeCode; //经纪公司编码
    private Integer reportNum;//报备数
    private Integer visitNum;//带看数
    private Integer dealNum;//成交数
    private Float commission;//佣金数
    private Integer status; //状态,状态:0有效，1无效
    private Date createTime;
    private Date updateTime;
    private Integer version;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "office_code", nullable = true, insertable = true, updatable = true)
    public Integer getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(Integer officeCode) {
        this.officeCode = officeCode;
    }

    @Basic
    @Column(name = "report_num", nullable = true, insertable = true, updatable = true)
    public Integer getReportNum() {
        return reportNum;
    }

    public void setReportNum(Integer reportNum) {
        this.reportNum = reportNum;
    }

    @Basic
    @Column(name = "visit_num", nullable = true, insertable = true, updatable = true)
    public Integer getVisitNum() {
        return visitNum;
    }

    public void setVisitNum(Integer visitNum) {
        this.visitNum = visitNum;
    }

    @Basic
    @Column(name = "deal_num", nullable = true, insertable = true, updatable = true)
    public Integer getDealNum() {
        return dealNum;
    }

    public void setDealNum(Integer dealNum) {
        this.dealNum = dealNum;
    }

    @Basic
    @Column(name = "commission", nullable = true, insertable = true, updatable = true, precision = 0)
    public Float getCommission() {
        return commission;
    }

    public void setCommission(Float commission) {
        this.commission = commission;
    }

    @Basic
    @Column(name = "status", nullable = true, insertable = true, updatable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "create_time", nullable = true, insertable = true, updatable = true)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time", nullable = true, insertable = true, updatable = true)
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Version
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }


}
