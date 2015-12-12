package com.flzc.base.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户组
 * @ClassName: AppUserGroup 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年8月4日 下午12:28:13
 */
@Entity
@Table(name="app_user_group")
public class AppUserGroup implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 37596943128742521L;

	//id
	private Integer id;
	
	//用户id
	private Integer userId;
	
	//分组id
	private Integer groupId;
	
	public AppUserGroup(){}
	
	public AppUserGroup(Integer id){
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

	@Column(name="user_id")
	public Integer getUserId() {
	
		return userId;
	}

	
	public void setUserId(Integer userId) {
	
		this.userId = userId;
	}

	@Column(name="group_id")
	public Integer getGroupId() {
	
		return groupId;
	}

	
	public void setGroupId(Integer groupId) {
	
		this.groupId = groupId;
	}
	
}
