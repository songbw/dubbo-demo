package com.flzc.rob.api.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AutionActivityInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "aution_activity_info")
public class AutionActivityInfo implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 4203370519196636736L;
	private Integer id;
	private Integer buildingId;//楼盘id
	private String activityName;//活动名称
	private Date actStartDate;//活动开始时间
	private Date actEndDate;//活动结束时间
	private Integer score;//份数
	private Date actActiveStartDate;//活动展示开始时间
	private Date actActiveEndDate;//活动展示结束时间
	private Integer startPrice;//低价
	private Integer targetPrice;//保留价格
	private BigDecimal deposite;//保证金
	private Integer ruleId;//协议id
	private Date createTime;//创建时间
	private Integer status;//状态0未发布，1已发布
	private String rule;//规则
	private Integer perSize; //增减价幅度
	


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

	@Column(name = "building_id")
	public Integer getBuildingId() {
		return this.buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	@Column(name = "activity_name", length = 50)
	public String getActivityName() {
		return this.activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	@Column(name = "act_start_date", length = 19)
	public Date getActStartDate() {
		return this.actStartDate;
	}

	public void setActStartDate(Date actStartDate) {
		this.actStartDate = actStartDate;
	}

	@Column(name = "act_end_date", length = 19)
	public Date getActEndDate() {
		return this.actEndDate;
	}

	public void setActEndDate(Date actEndDate) {
		this.actEndDate = actEndDate;
	}

	@Column(name = "score")
	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Column(name = "act_active_start_date", length = 19)
	public Date getActActiveStartDate() {
		return this.actActiveStartDate;
	}

	public void setActActiveStartDate(Date actActiveStartDate) {
		this.actActiveStartDate = actActiveStartDate;
	}

	@Column(name = "act_active_end_date", length = 19)
	public Date getActActiveEndDate() {
		return this.actActiveEndDate;
	}

	public void setActActiveEndDate(Date actActiveEndDate) {
		this.actActiveEndDate = actActiveEndDate;
	}

	@Column(name = "start_price")
	public Integer getStartPrice() {
		return this.startPrice;
	}

	public void setStartPrice(Integer startPrice) {
		this.startPrice = startPrice;
	}

	@Column(name = "target_price")
	public Integer getTargetPrice() {
		return this.targetPrice;
	}

	public void setTargetPrice(Integer targetPrice) {
		this.targetPrice = targetPrice;
	}

	@Column(name = "deposite")
	public BigDecimal getDeposite() {
		return this.deposite;
	}

	public void setDeposite(BigDecimal deposite) {
		this.deposite = deposite;
	}

	@Column(name = "rule_id")
	public Integer getRuleId() {
		return this.ruleId;
	}

	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}

	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "rule")
	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	@Column(name="per_size")
	public Integer getPerSize() {
		return perSize;
	}

	public void setPerSize(Integer perSize) {
		this.perSize = perSize;
	}

	
}