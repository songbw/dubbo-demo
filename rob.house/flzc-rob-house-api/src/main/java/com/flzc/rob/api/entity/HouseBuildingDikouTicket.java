package com.flzc.rob.api.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * HouseBuildingDikouTicket entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "house_building_dikou_ticket")
public class HouseBuildingDikouTicket implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer buildingId;
	private String name;
	private Integer housingQuantity;//可售房源
	private Integer dikouQuantity;//已抵扣房源
	private Integer price;
	private Double ammount;
	private Integer status;
	private Date createTime;
	private Date updateTime;
	private Integer version;
	private Date approveTime;
	private Integer approveStatus;
	private Date actActiveStartDate;
	private Date actActiveEndDate;
	private Date actStartDate;
	private Date actEndDate;
	private String actName;
	private String rule;
	private Date discountStartTime;
	private Date discountEndTime;

	// Constructors

	/** default constructor */
	public HouseBuildingDikouTicket() {
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

	@Column(name = "building_id")
	public Integer getBuildingId() {
		return this.buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "housing_quantity")
	public Integer getHousingQuantity() {
		return this.housingQuantity;
	}

	public void setHousingQuantity(Integer housingQuantity) {
		this.housingQuantity = housingQuantity;
	}

	@Column(name = "price")
	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Column(name = "ammount", precision = 22, scale = 0)
	public Double getAmmount() {
		return this.ammount;
	}

	public void setAmmount(Double ammount) {
		this.ammount = ammount;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "update_time", length = 19)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "version")
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Column(name = "approve_time", length = 19)
	public Date getApproveTime() {
		return this.approveTime;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}

	@Column(name = "approve_status")
	public Integer getApproveStatus() {
		return this.approveStatus;
	}

	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
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

	@Column(name = "act_name", length = 50)
	public String getActName() {
		return this.actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	@Column(name = "rule", length = 500)
	public String getRule() {
		return this.rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	@Column(name = "discount_start_time", length = 19)
	public Date getDiscountStartTime() {
		return this.discountStartTime;
	}

	public void setDiscountStartTime(Date discountStartTime) {
		this.discountStartTime = discountStartTime;
	}

	@Column(name = "discount_end_time", length = 19)
	public Date getDiscountEndTime() {
		return this.discountEndTime;
	}

	public void setDiscountEndTime(Date discountEndTime) {
		this.discountEndTime = discountEndTime;
	}

	@Column(name="dikou_quantity")
	public Integer getDikouQuantity() {
		return dikouQuantity;
	}

	public void setDikouQuantity(Integer dikouQuantity) {
		this.dikouQuantity = dikouQuantity;
	}


}