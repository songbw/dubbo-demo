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
 * 定制关注
 * @ClassName: PropertyCustomizationFollow 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年11月18日 下午3:39:05
 */
@Entity
@Table(name="property_customization_follow")
public class PropertyCustomizationFollow implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -6999927338776027804L;

	private Integer id;
	
	private Integer userId;//用户id
	
	private Integer recordId;//定制活动记录Id
	
	private Integer status;//状态：0有效，1无效
	
	private Date createTime;//创建时间

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

	@Column(name="record_id")
	public Integer getRecordId() {
	
		return recordId;
	}

	
	public void setRecordId(Integer recordId) {
	
		this.recordId = recordId;
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
	
}
