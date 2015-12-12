package com.flzc.rob.api.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * 定制奖品表
 * @ClassName: PropertyCustomizationReward 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年11月18日 下午3:40:11
 */
@Entity
@Table(name="property_customization_reward")
public class PropertyCustomizationReward implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 2203781100938282743L;

	private Integer id;
	
	private Integer activityId;//活动id
	
	private Date dtStartTime;//定金房链卷开始时间
	
	private Date dtEndTime;//定金房链卷结束时间
	
	private Integer depositTicket;//定金房链卷
	
	private String depositMemo;//定金说明
	
	private Integer submitOrderTicket;//提交订单房链卷
	
	private Date stStartTime;//提交订单房链卷开始时间
	
	private Date stEndTime;//提交订单房链卷结束时间
	
	private Integer submitOrderRedbag;//提交订单红包
	
	private String submitOrderMemo;//提交订单说明
	
	private Date createTime;//创建时间
	
	private Date updateTime;//修改时间
	
	private Integer version;//版本

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
	
		return id;
	}

	
	public void setId(Integer id) {
	
		this.id = id;
	}

	@Column(name="activity_id")
	public Integer getActivityId() {
	
		return activityId;
	}

	
	public void setActivityId(Integer activityId) {
	
		this.activityId = activityId;
	}

	@Column(name="dt_start_time")
	public Date getDtStartTime() {
	
		return dtStartTime;
	}

	
	public void setDtStartTime(Date dtStartTime) {
	
		this.dtStartTime = dtStartTime;
	}

	@Column(name="dt_end_time")
	public Date getDtEndTime() {
	
		return dtEndTime;
	}

	
	public void setDtEndTime(Date dtEndTime) {
	
		this.dtEndTime = dtEndTime;
	}

	@Column(name="deposit_ticket")
	public Integer getDepositTicket() {
	
		return depositTicket;
	}

	
	public void setDepositTicket(Integer depositTicket) {
	
		this.depositTicket = depositTicket;
	}

	@Column(name="deposit_memo")
	public String getDepositMemo() {
	
		return depositMemo;
	}

	
	public void setDepositMemo(String depositMemo) {
	
		this.depositMemo = depositMemo;
	}

	@Column(name="submit_order_ticket")
	public Integer getSubmitOrderTicket() {
	
		return submitOrderTicket;
	}

	
	public void setSubmitOrderTicket(Integer submitOrderTicket) {
	
		this.submitOrderTicket = submitOrderTicket;
	}

	@Column(name="st_start_time")
	public Date getStStartTime() {
	
		return stStartTime;
	}

	
	public void setStStartTime(Date stStartTime) {
	
		this.stStartTime = stStartTime;
	}

	@Column(name="st_end_time")
	public Date getStEndTime() {
	
		return stEndTime;
	}

	
	public void setStEndTime(Date stEndTime) {
	
		this.stEndTime = stEndTime;
	}

	@Column(name="submit_order_red_bag")
	public Integer getSubmitOrderRedbag() {
	
		return submitOrderRedbag;
	}

	
	public void setSubmitOrderRedbag(Integer submitOrderRedbag) {
	
		this.submitOrderRedbag = submitOrderRedbag;
	}

	@Column(name="submit_order_memo")
	public String getSubmitOrderMemo() {
	
		return submitOrderMemo;
	}

	
	public void setSubmitOrderMemo(String submitOrderMemo) {
	
		this.submitOrderMemo = submitOrderMemo;
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
