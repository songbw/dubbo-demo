package com.flzc.rob.api.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * HouseBuildingTagsLink entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "house_building_tags_rec_link")
public class HouseBuildingTagsRecLink implements java.io.Serializable {

	private static final long serialVersionUID = -163057458721889608L;
	// Fields

	private Integer id;
	private Integer buildingId;
	private Integer tagCode;
	private Integer status;
	private Date createTime;
	private Date updateTime;
	private Integer version;

	// Constructors

	/** default constructor */
	public HouseBuildingTagsRecLink() {
	}

	/** minimal constructor */
	public HouseBuildingTagsRecLink(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public HouseBuildingTagsRecLink(Integer id, Integer buildingId, Integer tagCode,
			Integer status, Date createTime, Date updateTime, Integer version) {
		this.id = id;
		this.buildingId = buildingId;
		this.tagCode = tagCode;
		this.status = status;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.version = version;
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

	@Column(name = "tag_code")
	public Integer getTagCode() {
		return this.tagCode;
	}

	public void setTagCode(Integer tagCode) {
		this.tagCode = tagCode;
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

}