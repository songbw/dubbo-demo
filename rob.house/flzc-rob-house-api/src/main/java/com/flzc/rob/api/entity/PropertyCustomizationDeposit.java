package com.flzc.rob.api.entity;

import javax.persistence.Table;
import javax.persistence.Version;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 暂时不用
 * @ClassName: PropertyCustomizationDeposit 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年11月18日 下午3:16:18
 */
@Entity
@Table(name="property_customization_deposit")
public class PropertyCustomizationDeposit implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -4831642302505559739L;

	private Integer id;
	
	private Integer recordId;//定制记录id
	
	private Integer userId;//用户id
	
	private Integer activityId;//活动id
	
	private Integer amount;//金额
	
	private Integer status;//状态:0已扣，1退回
	
	private Date createTime;//创建时间
	
	private Date updateTime;//更新时间
	
	private Integer version;//版本号

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
	
		return id;
	}

	
	public void setId(Integer id) {
	
		this.id = id;
	}

	@Column(name="record_id")
	public Integer getRecordId() {
	
		return recordId;
	}

	
	public void setRecordId(Integer recordId) {
	
		this.recordId = recordId;
	}

	@Column(name="user_id")
	public Integer getUserId() {
	
		return userId;
	}

	
	public void setUserId(Integer userId) {
	
		this.userId = userId;
	}

	@Column(name="activity_id")
	public Integer getActivityId() {
	
		return activityId;
	}

	
	public void setActivityId(Integer activityId) {
	
		this.activityId = activityId;
	}

	
	public Integer getAmount() {
	
		return amount;
	}

	
	public void setAmount(Integer amount) {
	
		this.amount = amount;
	}

	
	public Integer getStatus() {
	
		return status;
	}

	
	public void setStatus(Integer status) {
	
		this.status = status;
	}

	@Column(name="create_time")
	public Date getCreateTime() {
	
		return createTime;
	}

	
	public void setCreateTime(Date createTime) {
	
		this.createTime = createTime;
	}

	@Column(name="update_time")
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
