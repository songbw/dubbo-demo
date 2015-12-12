package com.flzc.rob.api.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * 私人定制活动用户参与表
 * PropertyCustomizationUserParticipant entity. @author MyEclipse Persistence
 * Tools
 */
@Entity
@Table(name = "property_customization_user_participant")
public class PropertyCustomizationUserParticipant implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer version;
	private Integer activityId;
	private Integer userId;
	private Integer participantWay;
	private Timestamp createTime;
	private Timestamp updateTime;

	// Constructors

	/** default constructor */
	public PropertyCustomizationUserParticipant() {
	}

	/** full constructor */
	public PropertyCustomizationUserParticipant(Integer activityId,
			Integer userId, Integer participantWay, Timestamp createTime,
			Timestamp updateTime) {
		this.activityId = activityId;
		this.userId = userId;
		this.participantWay = participantWay;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Version
	@Column(name = "version")
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Column(name = "activity_id")
	public Integer getActivityId() {
		return this.activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	@Column(name = "user_id")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "participantWay")
	public Integer getParticipantWay() {
		return this.participantWay;
	}

	public void setParticipantWay(Integer participantWay) {
		this.participantWay = participantWay;
	}

	@Column(name = "create_time", length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "update_time", length = 19)
	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

}