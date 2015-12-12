package com.flzc.rob.api.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 答题活动表
 * AnswerQuestionActivity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "answer_question_activity")
public class AnswerQuestionActivity implements java.io.Serializable {

	// Fields

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -4985894043716607615L;
	private Integer id;
	private Integer buildingId;//楼盘id
	private String actName;//活动名称
	private Date actStartDate;//活动开始时间
	private Date actEndDate;//活动结束时间
	private Integer score;//份数
	private Date actActiveStartDate;//活动开始展示时间
	private Date actActiveEndDate;//活动结束展示时间
	private Integer status;//状态0为正常、1为删除
	private Date createTime;//创建时间
	private Integer answerCount;//答题次数
	private String rule;//规则



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

	@Column(name = "act_name", length = 50)
	public String getActName() {
		return this.actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
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
	
	@Column(name="answer_count")
	public Integer getAnswerCount() {
	
		return answerCount;
	}

	
	public void setAnswerCount(Integer answerCount) {
	
		this.answerCount = answerCount;
	}

	
	public String getRule() {
	
		return rule;
	}

	
	public void setRule(String rule) {
	
		this.rule = rule;
	}
	
}