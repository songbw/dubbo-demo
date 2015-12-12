package com.flzc.rob.api.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 私人定制活动信息
 * Created by iverson on 2015/10/17.
 */
@Entity
@Table(name = "property_customization_record")
public class PropertyCustomizationRecord implements Serializable {
    private Integer id;
    private Integer activityId;//活动Id
    private Integer userId;//用户id;
    private Integer proType;//物业类型
    private Integer greenRate;//绿化率
    private Integer houseType;//户型
    private Integer size;//面积
    private Integer faceTo;//朝向
    private Integer decoration;//装修
    private Integer floorBiz;//裙楼底商
    private Integer proBiz;//物业公司
    private Integer deriveFrom;//表明衍生出该数据的源数据id.默认为0.表示无
    private Date createTime;//
    private Date updateTime;
    private Integer version;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    @Column(name="user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "pro_type", nullable = true, insertable = true, updatable = true)
    public Integer getProType() {
        return proType;
    }

    public void setProType(Integer proType) {
        this.proType = proType;
    }

    @Basic
    @Column(name = "green_rate", nullable = true, insertable = true, updatable = true)
    public Integer getGreenRate() {
        return greenRate;
    }

    public void setGreenRate(Integer greenRate) {
        this.greenRate = greenRate;
    }

    @Basic
    @Column(name = "house_type", nullable = true, insertable = true, updatable = true)
    public Integer getHouseType() {
        return houseType;
    }

    public void setHouseType(Integer houseType) {
        this.houseType = houseType;
    }

    @Basic
    @Column(name = "size", nullable = true, insertable = true, updatable = true)
    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Basic
    @Column(name = "face_to", nullable = true, insertable = true, updatable = true)
    public Integer getFaceTo() {
        return faceTo;
    }

    public void setFaceTo(Integer faceTo) {
        this.faceTo = faceTo;
    }

    @Basic
    @Column(name = "decoration", nullable = true, insertable = true, updatable = true)
    public Integer getDecoration() {
        return decoration;
    }

    public void setDecoration(Integer decoration) {
        this.decoration = decoration;
    }

    @Basic
    @Column(name = "floor_biz", nullable = true, insertable = true, updatable = true)
    public Integer getFloorBiz() {
        return floorBiz;
    }

    public void setFloorBiz(Integer floorBiz) {
        this.floorBiz = floorBiz;
    }

    @Basic
    @Column(name = "pro_biz", nullable = true, insertable = true, updatable = true)
    public Integer getProBiz() {
        return proBiz;
    }

    public void setProBiz(Integer proBiz) {
        this.proBiz = proBiz;
    }

    @Basic
    @Column(name = "create_time", nullable = true, insertable = true, updatable = true)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date crreateTime) {
        this.createTime = crreateTime;
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

    @Column(name = "derive_from")
    public Integer getDeriveFrom() {
        return deriveFrom;
    }

    public void setDeriveFrom(Integer deriveFrom) {
        this.deriveFrom = deriveFrom;
    }

    @Column(name="activity_id")
	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
    
}
