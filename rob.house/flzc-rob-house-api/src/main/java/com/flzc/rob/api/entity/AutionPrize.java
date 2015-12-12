package com.flzc.rob.api.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AutionPrice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "aution_prize")
public class AutionPrize implements java.io.Serializable {

	// Fields

	private Integer id;
	
	private Integer actId;//活动id
	
	private Integer winPrizeType;//中奖奖励类型 (56001房链优惠券）
	
	private Integer winPrizeValue;//赢了的奖品值
	
	private Date winPrizeStartTime; //赢了的奖品开始时间
	
	private Date winPrizeEndTime;//赢了的奖品卷结束时间
	
	private Integer unwinPrizeType;//没有赢取奖品的类型(56001房链优惠券）
	
	private Integer unwinPrizeValue;//没有赢取奖品的值
	
	private Date unwinPrizeStartTime;//未赢了的奖品开始时间
	
	private Date unwinPrizeEndTime;//未赢了的奖品卷结束时间
	
	private Date createTime;

	// Constructors

	/** default constructor */
	public AutionPrize() {
	}

	/** minimal constructor */
	public AutionPrize(Integer id) {
		this.id = id;
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

	@Column(name="act_id")
	public Integer getActId() {
		return actId;
	}

	public void setActId(Integer actId) {
		this.actId = actId;
	}

	@Column(name="win_prize_type")
	public Integer getWinPrizeType() {
		return winPrizeType;
	}

	public void setWinPrizeType(Integer winPrizeType) {
		this.winPrizeType = winPrizeType;
	}

	@Column(name="win_prize_value")
	public Integer getWinPrizeValue() {
		return winPrizeValue;
	}

	public void setWinPrizeValue(Integer winPrizeValue) {
		this.winPrizeValue = winPrizeValue;
	}

	@Column(name="win_prize_start_time")
	public Date getWinPrizeStartTime() {
		return winPrizeStartTime;
	}

	public void setWinPrizeStartTime(Date winPrizeStartTime) {
		this.winPrizeStartTime = winPrizeStartTime;
	}

	@Column(name="win_prize_end_time")
	public Date getWinPrizeEndTime() {
		return winPrizeEndTime;
	}

	public void setWinPrizeEndTime(Date winPrizeEndTime) {
		this.winPrizeEndTime = winPrizeEndTime;
	}

	@Column(name="unwin_prize_type")
	public Integer getUnwinPrizeType() {
		return unwinPrizeType;
	}

	public void setUnwinPrizeType(Integer unwinPrizeType) {
		this.unwinPrizeType = unwinPrizeType;
	}

	@Column(name="unwin_prize_value")
	public Integer getUnwinPrizeValue() {
		return unwinPrizeValue;
	}

	public void setUnwinPrizeValue(Integer unwinPrizeValue) {
		this.unwinPrizeValue = unwinPrizeValue;
	}

	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name="unwin_prize_start_time")
	public Date getUnwinPrizeStartTime() {
		return unwinPrizeStartTime;
	}

	public void setUnwinPrizeStartTime(Date unwinPrizeStartTime) {
		this.unwinPrizeStartTime = unwinPrizeStartTime;
	}

	@Column(name="unwin_prize_end_time")
	public Date getUnwinPrizeEndTime() {
		return unwinPrizeEndTime;
	}

	public void setUnwinPrizeEndTime(Date unwinPrizeEndTime) {
		this.unwinPrizeEndTime = unwinPrizeEndTime;
	}
	
}
