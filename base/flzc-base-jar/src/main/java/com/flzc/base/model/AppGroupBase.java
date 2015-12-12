package com.flzc.base.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @ClassName: AppGroupBase 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年8月4日 上午11:21:17
 */
@Entity
@Table(name="app_group_base")
public class AppGroupBase implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -7766973098319047323L;

	//id
	private Integer id;
	
	//组id
	private Integer groupId;
	
	//baseId
	private Integer baseId;
	
	public AppGroupBase(){}
	
	public AppGroupBase(Integer id){
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
	
		return id;
	}

	
	public void setId(Integer id) {
	
		this.id = id;
	}

	@Column(name="group_id")
	public Integer getGroupId() {
	
		return groupId;
	}

	
	public void setGroupId(Integer groupId) {
	
		this.groupId = groupId;
	}

	@Column(name="base_id")
	public Integer getBaseId() {
	
		return baseId;
	}

	
	public void setBaseId(Integer baseId) {
	
		this.baseId = baseId;
	}
	
}
