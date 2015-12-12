package com.flzc.base.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @ClassName: AppBasic 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年8月4日 上午10:49:27
 */
@Entity
@Table(name="app_basic")
public class AppBasic {

	//id
	private Integer id;
	
	//名称
	private String appName;
	
	//码
	private String appCode;
	
	//排序
	private Integer appSort;
	
	//url地址
	private String appUrl;
	
	//描述
	private String description;
	
	//创建时间
	private Date createDate;
	
	public AppBasic(){}

	public AppBasic(Integer id){
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

	@Column(name="app_name")
	public String getAppName() {
	
		return appName;
	}

	
	public void setAppName(String appName) {
	
		this.appName = appName;
	}

	@Column(name="app_code")
	public String getAppCode() {
	
		return appCode;
	}

	
	public void setAppCode(String appCode) {
	
		this.appCode = appCode;
	}

	@Column(name="app_sort")
	public Integer getAppSort() {
	
		return appSort;
	}

	
	public void setAppSort(Integer appSort) {
	
		this.appSort = appSort;
	}

	@Column(name="app_url")
	public String getAppUrl() {
	
		return appUrl;
	}

	
	public void setAppUrl(String appUrl) {
	
		this.appUrl = appUrl;
	}

	
	public String getDescription() {
	
		return description;
	}

	
	public void setDescription(String description) {
	
		this.description = description;
	}

	@Column(name="create_date")
	public Date getCreateDate() {
	
		return createDate;
	}

	
	public void setCreateDate(Date createDate) {
	
		this.createDate = createDate;
	}
	
}
