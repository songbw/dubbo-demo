package com.flzc.rob.api.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 活动定制类别选项
 * @ClassName: PropertyCustomizationLinkItem 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年11月18日 下午3:39:50
 */
@Entity
@Table(name="property_customization_link_item")
public class PropertyCustomizationLinkItem implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -4779223666777793711L;

	private Integer id;
	
	private Integer itemId;//关联定制项目id
	
	private Integer activityId;//活动id
	
	private Integer parentId;//父id
	
	private Date createTime;//创建时间

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
	
		return id;
	}

	public void setId(Integer id) {
	
		this.id = id;
	}

	@Column(name="item_id")
	public Integer getItemId() {
	
		return itemId;
	}

	
	public void setItemId(Integer itemId) {
	
		this.itemId = itemId;
	}

	@Column(name="activity_id")
	public Integer getActivityId() {
	
		return activityId;
	}

	
	public void setActivityId(Integer activityId) {
	
		this.activityId = activityId;
	}

	@Column(name="parent_id")
	public Integer getParentId() {
	
		return parentId;
	}

	
	public void setParentId(Integer parentId) {
	
		this.parentId = parentId;
	}

	@Column(name="create_time")
	public Date getCreateTime() {
	
		return createTime;
	}

	
	public void setCreateTime(Date createTime) {
	
		this.createTime = createTime;
	}
	
}
