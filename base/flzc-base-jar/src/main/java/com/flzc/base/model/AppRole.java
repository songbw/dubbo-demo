package com.flzc.base.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @ClassName: AppRole 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年8月4日 上午11:56:43
 */
@Entity
@Table(name="app_role")
public class AppRole {

	//id
	private Integer id;
	
	//角色名
	private String roleName;
	
	//排序
	private String roleDesc;
	
	//角色状态
	private Integer roleStatus;
	
	//appid
	private Integer appId;
	
	public AppRole(){}
	
	public AppRole(Integer id){
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

	@Column(name="role_name")
	public String getRoleName() {
	
		return roleName;
	}

	
	public void setRoleName(String roleName) {
	
		this.roleName = roleName;
	}

	@Column(name="role_desc")
	public String getRoleDesc() {
	
		return roleDesc;
	}

	
	public void setRoleDesc(String roleDesc) {
	
		this.roleDesc = roleDesc;
	}

	@Column(name="role_status")
	public Integer getRoleStatus() {
	
		return roleStatus;
	}

	
	public void setRoleStatus(Integer roleStatus) {
	
		this.roleStatus = roleStatus;
	}

	@Column(name="app_id")
	public Integer getAppId() {
	
		return appId;
	}

	
	public void setAppId(Integer appId) {
	
		this.appId = appId;
	}
	
}
