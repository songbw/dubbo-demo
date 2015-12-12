package com.flzc.base.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @ClassName: AppOrganization 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年8月4日 上午11:29:04
 */
@Entity
@Table(name="app_organization")
public class AppOrganization {

	private Integer id;
	
	private String orgName;
	
	private Integer orgDesc;
	
	private Integer orgSupId;
	
	private String orgPath;
	
	public AppOrganization(){}
	
	public AppOrganization(Integer id){
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

	@Column(name="org_name")
	public String getOrgName() {
	
		return orgName;
	}

	
	public void setOrgName(String orgName) {
	
		this.orgName = orgName;
	}

	@Column(name="org_desc")
	public Integer getOrgDesc() {
	
		return orgDesc;
	}

	
	public void setOrgDesc(Integer orgDesc) {
	
		this.orgDesc = orgDesc;
	}

	@Column(name="org_supId")
	public Integer getOrgSupId() {
	
		return orgSupId;
	}

	
	public void setOrgSupId(Integer orgSupId) {
	
		this.orgSupId = orgSupId;
	}

	@Column(name="org_path")
	public String getOrgPath() {
	
		return orgPath;
	}

	
	public void setOrgPath(String orgPath) {
	
		this.orgPath = orgPath;
	}
	
}
