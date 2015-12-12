package com.flzc.rob.api.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 楼盘表
 * HouseBuildingImg entity. @author MyEclipse Persistence Tools
 @SuppressWarnings("serial")
*/
@Entity
@Table(name = "house_building_img")
public class HouseBuildingImg implements java.io.Serializable {

	// Fields

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 5237200141001711269L;
	private Integer id;
	private Integer buildingId;//楼盘id
	private String imgUrl;//图片地址
	private Integer imgOrder;//图片排序
	private Integer mainPic;//封面 0为封面，1为普通
	private Integer type;//类型 0为楼盘的特殊类型 1为效果图,2为样板间,3为实景图,4为户型图,5为周边配套,6为外景图,7为交通图
	private Integer status;//状态
	private Date createTime;//创建时间
	private Date updateTime;//修改时间
	private Integer version;//版本号


	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}