package com.flzc.quartz.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 经纪人意向客户楼盘
 * Created by iverson on 2015/10/28.
 */
@Entity
@Table(name = "agency_client_intention_building")
@DynamicUpdate
public class AgencyClientIntentionBuilding implements Serializable{
    private Integer id;
    private Integer clientId;
    private Integer buildingId;
    private Integer agencyId;
    private String buildingName;
    private Integer status;//报备状态.参考字典表 system_dic
   // private Integer acId;//案场确认人名
  //  private String acName;//案场确认人id
    private Date appointment;//预约时间
    private Date createTime;
    private Date updateTime;
    private Integer version;



    @Id
    @Column(name = "id", nullable = true, insertable = true, updatable = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "client_id", nullable = true, insertable = true, updatable = true)
    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    @Basic
    @Column(name = "building_id", nullable = true, insertable = true, updatable = true)
    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer builidngId) {
        this.buildingId = builidngId;
    }

    @Basic
    @Column(name = "building_name", nullable = true, insertable = true, updatable = true, length = 20)
    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
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
    @Column(name = "appointment", nullable = true, insertable = true, updatable = true)
    public Date getAppointment() {
        return appointment;
    }

    public void setAppointment(Date appointment) {
        this.appointment = appointment;
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
    @Column(name = "version", nullable = true, insertable = true, updatable = true)
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Column(name = "agency_id")
    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }
}
