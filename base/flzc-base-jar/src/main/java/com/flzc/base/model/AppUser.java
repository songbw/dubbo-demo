package com.flzc.base.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 后台用户表
 * @ClassName: AppUser 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年8月4日 下午12:19:55
 */
@Entity
@Table(name="app_user")
public class AppUser {

	//id
	private Integer id;
	
	//用户名
	private String userName;
	
	private String password;
	
	//岗位id
	private Integer depid;
	
	//名称
	private String fullName;
	
	//状态
	private Integer state;
	
	public AppUser(){}
	
	public AppUser(Integer id){
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

	@Column(name="user_name")
	public String getUserName() {
	
		return userName;
	}

	
	public void setUserName(String userName) {
	
		this.userName = userName;
	}

	
	public Integer getDepid() {
	
		return depid;
	}

	
	public void setDepid(Integer depid) {
	
		this.depid = depid;
	}

	@Column(name="full_name")
	public String getFullName() {
	
		return fullName;
	}

	
	public void setFullName(String fullName) {
	
		this.fullName = fullName;
	}

	
	public Integer getState() {
	
		return state;
	}

	
	public void setState(Integer state) {
	
		this.state = state;
	}


	public String getPassword() {
	
		return password;
	}

	
	public void setPassword(String password) {
	
		this.password = password;
	}
	
}
