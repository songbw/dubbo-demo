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
 * 私人定制服务保障表
 * @ClassName: PropertyCustomizationAssurance 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年11月18日 下午3:15:38
 */
@Entity
@Table(name="property_customization_assurance")
public class PropertyCustomizationAssurance implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -7951758808902666056L;

	private Integer id;
	
	private Integer activityId;//活动id
	
	private String title;//标题
	
	private String content;//内容
	
	private String picUrl;//图片地址
	
	private Date createTime;//创建时间
	
	private Date updateTime;//更新时间
	
	private Integer version;//版本号

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

	
	public String getTitle() {
	
		return title;
	}

	
	public void setTitle(String title) {
	
		this.title = title;
	}

	
	public String getContent() {
	
		return content;
	}

	
	public void setContent(String content) {
	
		this.content = content;
	}

	@Column(name="pic_url")
	public String getPicUrl() {
	
		return picUrl;
	}

	
	public void setPicUrl(String picUrl) {
	
		this.picUrl = picUrl;
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
