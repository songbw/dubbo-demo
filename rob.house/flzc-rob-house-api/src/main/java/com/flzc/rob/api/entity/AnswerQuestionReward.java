package com.flzc.rob.api.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AnswerQuestionReward entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "answer_question_reward")
public class AnswerQuestionReward implements java.io.Serializable {

	// Fields
	
	private static final long serialVersionUID = -8889639867870467343L;
	private Integer id;
	private Integer actId;//答题活动id
	private String levelMemo;//档位描述
	private Float rewardRate;//档位相对中奖率
	private Integer levelValue;//档位值
	private Date discountStartTime;//优惠卷开始时间
	private Date discountEndTime;//优惠卷结束时间
	private Date createTime;//创建时间
	private Integer rewardTotal; //设置的该档位总份数
	private Integer rewardType;  //奖励类型 56001房链优惠券

	// Constructors

	/** default constructor */
	public AnswerQuestionReward() {
	}

	/** minimal constructor */
	public AnswerQuestionReward(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public AnswerQuestionReward(Integer id, Integer actId, String levelMemo,
			Float rewardRate, Integer levelValue, Date createTime) {
		this.id = id;
		this.actId = actId;
		this.levelMemo = levelMemo;
		this.rewardRate = rewardRate;
		this.levelValue = levelValue;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "act_id")
	public Integer getActId() {
		return this.actId;
	}

	public void setActId(Integer actId) {
		this.actId = actId;
	}

	@Column(name = "level_memo", length = 200)
	public String getLevelMemo() {
		return this.levelMemo;
	}

	public void setLevelMemo(String levelMemo) {
		this.levelMemo = levelMemo;
	}

	@Column(name = "reward_rate", precision = 10, scale = 2)
	public Float getRewardRate() {
		return this.rewardRate;
	}

	public void setRewardRate(Float rewardRate) {
		this.rewardRate = rewardRate;
	}

	@Column(name = "level_value")
	public Integer getLevelValue() {
		return this.levelValue;
	}

	public void setLevelValue(Integer levelValue) {
		this.levelValue = levelValue;
	}
	
	@Column(name = "reward_total")
	public Integer getRewardTotal() {
		return rewardTotal;
	}

	public void setRewardTotal(Integer rewardTotal) {
		this.rewardTotal = rewardTotal;
	}

	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name="reward_type")
	public Integer getRewardType() {
		return rewardType;
	}

	public void setRewardType(Integer rewardType) {
		this.rewardType = rewardType;
	}


	@Column(name="discount_start_time")
	public Date getDiscountStartTime() {
		return discountStartTime;
	}

	public void setDiscountStartTime(Date discountStartTime) {
		this.discountStartTime = discountStartTime;
	}

	@Column(name="discount_end_time")
	public Date getDiscountEndTime() {
		return discountEndTime;
	}

	public void setDiscountEndTime(Date discountEndTime) {
		this.discountEndTime = discountEndTime;
	}
}