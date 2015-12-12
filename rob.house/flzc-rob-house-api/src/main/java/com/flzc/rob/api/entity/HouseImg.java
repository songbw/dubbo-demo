package com.flzc.rob.api.entity;

import java.util.Date;
import javax.persistence.*;

/**
 * 户型图片表
 */
@Entity
@Table(name = "house_img")
public class HouseImg implements java.io.Serializable {

	// Fields

	private Integer id;//主键
	private Integer houseId;//楼盘id
	private String imgUrl;//图片地址
	private Integer imgOrder;//图片顺序
	private Integer mainPic;//是否是主图 0是，1否
	private Integer type;//图片类型
	private Integer status;//状态，0有效，1无效
	private Date createTime;
	private Date updateTime;
	private Integer version;


	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "house_id")
	public Integer getHouseId() {
		return this.houseId;
	}

	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}

	@Column(name = "img_url", length = 100)
	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	@Column(name = "img_order")
	public Integer getImgOrder() {
		return this.imgOrder;
	}

	public void setImgOrder(Integer imgOrder) {
		this.imgOrder = imgOrder;
	}

	@Column(name = "main_pic")
	public Integer getMainPic() {
		return this.mainPic;
	}

	public void setMainPic(Integer mainPic) {
		this.mainPic = mainPic;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
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
	@Version
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}