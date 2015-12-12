package com.flzc.rob.api.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by iverson on 2015/10/22.
 */
@Entity
@Table(name = "property_customization_activity")
public class PropertyCustomizationActivity implements Serializable{
	
    /**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 2348738907124692245L;
	
	
	private Integer id;
    private Integer buildingId;
    private Date actStartDate; //活动开始时间
    private Date actEndDate;
    private Date actActiveStartDate;//活动展示开始时间
    private Date actActiveEndDate;
   // private Integer actDeposit;//活动保证金
    private BigDecimal privateDeposit;//私人定制定金
    private Integer houseNum;//可定制户数
    private String rules;//活动规则介绍
    private Integer status;//活动状态:0有效，1无效
    private Date createTime;
    private Date updateTime;
    private Integer version;

    private String activityName;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "building_id", nullable = true, insertable = true, updatable = true)
    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    @Basic
    @Column(name = "act_start_date", nullable = true, insertable = true, updatable = true)
    public Date getActStartDate() {
        return actStartDate;
    }

    public void setActStartDate(Date actStartDate) {
        this.actStartDate = actStartDate;
    }

    @Basic
    @Column(name = "act_end_date", nullable = true, insertable = true, updatable = true)
    public Date getActEndDate() {
        return actEndDate;
    }

    public void setActEndDate(Date actEndDate) {
        this.actEndDate = actEndDate;
    }

    @Basic
    @Column(name = "act_active_start_date", nullable = true, insertable = true, updatable = true)
    public Date getActActiveStartDate() {
        return actActiveStartDate;
    }

    public void setActActiveStartDate(Date actActiveStartDate) {
        this.actActiveStartDate = actActiveStartDate;
    }

    @Basic
    @Column(name = "act_active_end_date", nullable = true, insertable = true, updatable = true)
    public Date getActActiveEndDate() {
        return actActiveEndDate;
    }

    public void setActActiveEndDate(Date actActiveEndDate) {
        this.actActiveEndDate = actActiveEndDate;
    }



    @Basic
    @Column(name = "private_deposit")
    public BigDecimal getPrivateDeposit() {
        return privateDeposit;
    }

    public void setPrivateDeposit(BigDecimal privateDeposit) {
        this.privateDeposit = privateDeposit;
    }

    @Basic
    @Column(name = "house_num", nullable = true, insertable = true, updatable = true)
    public Integer getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(Integer houseNum) {
        this.houseNum = houseNum;
    }

    @Basic
    @Column(name = "rules", nullable = true, insertable = true, updatable = true, length = 1000)
    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
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

    @Basic
    @Column(name = "version", nullable = true, insertable = true, updatable = true)
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Column(name = "activity_name")
    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
}
