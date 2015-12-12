package com.flzc.rob.api.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户参与定制活动基本信息表
 * @ClassName: PropertyCustomizationBaseInfo 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年11月18日 下午9:45:59
 */
@Entity
@Table(name="property_customization_base_info")
public class PropertyCustomizationBaseInfo implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -2103466409268056180L;

	private Integer id;
	
	private Integer userId;
	
	private Integer activityId;
	
	private String addition;
	
	private Date createTime;
	
	private Integer pid;
	
	private Integer status;
	
	private Integer type;
	
	private Integer approveStatus;
	
	private Date approveTime;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
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

	@Column(name="activity_id")
	public Integer getActivityId() {
	
		return activityId;
	}

	
	public void setActivityId(Integer activityId) {
	
		this.activityId = activityId;
	}

	
	public String getAddition() {
	
		return addition;
	}

	
	public void setAddition(String addition) {
	
		this.addition = addition;
	}

	@Column(name="create_time")
	public Date getCreateTime() {
	
		return createTime;
	}

	
	public void setCreateTime(Date createTime) {
	
		this.createTime = createTime;
	}
	
	public Integer getPid() {
	
		return pid;
	}

	public void setPid(Integer pid) {
	
		this.pid = pid;
	}


	@Column(name="status")
	public Integer getStatus() {
	
		return status;
	}


	
	public void setStatus(Integer status) {
	
		this.status = status;
	}

	public Integer getType() {
	
		return type;
	}

	public void setType(Integer type) {
	
		this.type = type;
	}


	@Column(name="approve_status")
	public Integer getApproveStatus() {
	
		return approveStatus;
	}


	
	public void setApproveStatus(Integer approveStatus) {
	
		this.approveStatus = approveStatus;
	}


	@Column(name = "approve_time")
	public Date getApproveTime() {
	
		return approveTime;
	}


	
	public void setApproveTime(Date approveTime) {
	
		this.approveTime = approveTime;
	}
	
}
