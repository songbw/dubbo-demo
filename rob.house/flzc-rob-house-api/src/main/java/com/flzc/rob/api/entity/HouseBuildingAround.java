package com.flzc.rob.api.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 楼盘周边配置表
 * HouseBuildingAround entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "house_building_around")
public class HouseBuildingAround implements java.io.Serializable {

	// Fields

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -1460931851774184249L;
	private Integer id;
	private Integer buildingId;//楼盘id
	private Integer tagCode;//字典中的周边配置
	private Date createTime;//创建时间



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

	@Column(name = "tag_code")
	public Integer getTagCode() {
		return this.tagCode;
	}

	public void setTagCode(Integer dicCode) {
		this.tagCode = dicCode;
	}

	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}