package com.flzc.quartz.entity;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * FlzcTicket entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "flzc_ticket")
public class FlzcTicket implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer activityRecapId;
	private String ticketCode;
	private Integer userId;
	private Integer amount;
	private Date startDate;
	private Date endDate;
	private Integer status;
	private Integer actType;
	private Integer ticketType;
	private Date createTime;



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

	@Column(name = "activity_recap_id")
	public Integer getActivityRecapId() {
		return this.activityRecapId;
	}

	public void setActivityRecapId(Integer activityRecapId) {
		this.activityRecapId = activityRecapId;
	}

	@Column(name = "ticket_code", length = 100)
	public String getTicketCode() {
		return this.ticketCode;
	}

	public void setTicketCode(String ticketCode) {
		this.ticketCode = ticketCode;
	}

	@Column(name = "user_id")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "amount")
	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date", length = 10)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "end_date", length = 10)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "act_type")
	public Integer getActType() {
		return this.actType;
	}

	public void setActType(Integer actType) {
		this.actType = actType;
	}

	@Column(name = "ticket_type")
	public Integer getTicketType() {
		return this.ticketType;
	}

	public void setTicketType(Integer ticketType) {
		this.ticketType = ticketType;
	}

	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}