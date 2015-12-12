package com.flzc.rob.api.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 竞拍身份证表
 * AutionCerVerfiy entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "aution_cer_verfiy")
public class AutionCerVerfiy implements java.io.Serializable {

	// Fields

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 7208027762852057649L;
	private Integer id;
	private Integer activityId;//活动id
	private Integer userId;//购买用户id
	private Integer cerType;//活动状态
	private String cerNo;//证件号码
	private Date createTime;//创建时间

	// Constructors

	/** default constructor */
	public AutionCerVerfiy() {
	}

	/** full constructor */
	public AutionCerVerfiy(Integer activityId, Integer userId, Integer cerType,
			String cerNo, Date createTime) {
		this.activityId = activityId;
		this.userId = userId;
		this.cerType = cerType;
		this.cerNo = cerNo;
		this.createTime = createTime;
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

	@Column(name = "cer_type")
	public Integer getCerType() {
		return this.cerType;
	}

	public void setCerType(Integer cerType) {
		this.cerType = cerType;
	}

	@Column(name = "cer_no", length = 20)
	public String getCerNo() {
		return this.cerNo;
	}

	public void setCerNo(String cerNo) {
		this.cerNo = cerNo;
	}

	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}