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
 * @ClassName: AppRoleMenu 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年8月4日 下午12:01:35
 */
@Entity
@Table(name="app_role_menu")
public class AppRoleMenu implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -5496462155090887125L;

	private Integer id;
	
	private Integer roleId;
	
	private Integer menuId;
	
	public AppRoleMenu(){}
	
	public AppRoleMenu(Integer id){
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

	@Column(name="role_id")
	public Integer getRoleId() {
	
		return roleId;
	}

	
	public void setRoleId(Integer roleId) {
	
		this.roleId = roleId;
	}

	@Column(name="menu_id")
	public Integer getMenuId() {
	
		return menuId;
	}

	
	public void setMenuId(Integer menuId) {
	
		this.menuId = menuId;
	}
	
}
