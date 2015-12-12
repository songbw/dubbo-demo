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
 * 定制类别
 * @ClassName: PropertyCustomizationItem 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年11月18日 下午3:39:28
 */
@Entity
@Table(name="property_customization_item")
public class PropertyCustomizationItem implements Serializable{

	
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 3065963738960968499L;
	
	private Integer id;
	
	private String itemName;//分类名称
	
	private Integer parentId;//父分类
	
	private Integer orderNo;//排序
	
	private Integer itemType;//项目类型:0固定类，1自己定义项
	
	private Integer defaultChosen;//系统必选状态（0必选,1备选）
	
	private Integer extendLimit;//拓展项上限(null不限定)
	
	private Date createTime;//创建时间
	
	private Date updateTime;//修改时间
	
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

	@Column(name="item_name")
	public String getItemName() {
	
		return itemName;
	}

	
	public void setItemName(String itemName) {
	
		this.itemName = itemName;
	}

	@Column(name="parent_id")
	public Integer getParentId() {
	
		return parentId;
	}

	
	public void setParentId(Integer parentId) {
	
		this.parentId = parentId;
	}

	@Column(name="order_no")
	public Integer getOrderNo() {
	
		return orderNo;
	}

	
	public void setOrderNo(Integer orderNo) {
	
		this.orderNo = orderNo;
	}

	@Column(name="item_type")
	public Integer getItemType() {
	
		return itemType;
	}

	
	public void setItemType(Integer itemType) {
	
		this.itemType = itemType;
	}

	@Column(name="default_chosen")
	public Integer getDefaultChosen() {
	
		return defaultChosen;
	}

	
	public void setDefaultChosen(Integer defaultChosen) {
	
		this.defaultChosen = defaultChosen;
	}

	@Column(name="extend_limit")
	public Integer getExtendLimit() {
	
		return extendLimit;
	}

	
	public void setExtendLimit(Integer extendLimit) {
	
		this.extendLimit = extendLimit;
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
