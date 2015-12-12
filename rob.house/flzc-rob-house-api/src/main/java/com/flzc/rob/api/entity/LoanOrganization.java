package com.flzc.rob.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * LoanOrganization entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "loan_organization")
public class LoanOrganization implements java.io.Serializable {

	// Fields

	private Integer id;

	private String userName;

	private String password;

	private String orgName;

	private String email;

	private String linkman;

	private String phone;

	private Integer provinceId;

	private Integer cityId;

	private Integer areaId;

	private String addr;

	private Integer status;

	private Date createTime;

	private Date updateTime;

	// Constructors

	/** default constructor */
	public LoanOrganization() {
	}

	/** full constructor */
	public LoanOrganization(String userName, String password, String orgName, String email, String linkman,
			String phone, Integer provinceId, Integer cityId, Integer areaId, String addr, Integer status) {
		this.userName = userName;
		this.password = password;
		this.orgName = orgName;
		this.email = email;
		this.linkman = linkman;
		this.phone = phone;
		this.provinceId = provinceId;
		this.cityId = cityId;
		this.areaId = areaId;
		this.addr = addr;
		this.status = status;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {

		return this.id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	@Column(name = "user_name", length = 50)
	public String getUserName() {

		return this.userName;
	}

	public void setUserName(String userName) {

		this.userName = userName;
	}

	@Column(name = "password", length = 50)
	public String getPassword() {

		return this.password;
	}

	public void setPassword(String password) {

		this.password = password;
	}

	@Column(name = "org_name", length = 50)
	public String getOrgName() {

		return this.orgName;
	}

	public void setOrgName(String orgName) {

		this.orgName = orgName;
	}

	@Column(name = "email", length = 50)
	public String getEmail() {

		return this.email;
	}

	public void setEmail(String email) {

		this.email = email;
	}

	@Column(name = "linkman", length = 50)
	public String getLinkman() {

		return this.linkman;
	}

	public void setLinkman(String linkman) {

		this.linkman = linkman;
	}

	@Column(name = "phone", length = 50)
	public String getPhone() {

		return this.phone;
	}

	public void setPhone(String phone) {

		this.phone = phone;
	}

	@Column(name = "province_id")
	public Integer getProvinceId() {

		return this.provinceId;
	}

	public void setProvinceId(Integer provinceId) {

		this.provinceId = provinceId;
	}

	@Column(name = "city_id")
	public Integer getCityId() {

		return this.cityId;
	}

	public void setCityId(Integer cityId) {

		this.cityId = cityId;
	}

	@Column(name = "area_id")
	public Integer getAreaId() {

		return this.areaId;
	}

	public void setAreaId(Integer areaId) {

		this.areaId = areaId;
	}

	@Column(name = "addr", length = 50)
	public String getAddr() {

		return this.addr;
	}

	public void setAddr(String addr) {

		this.addr = addr;
	}

	@Column(name = "status")
	public Integer getStatus() {

		return this.status;
	}

	public void setStatus(Integer status) {

		this.status = status;
	}

	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {

		return createTime;
	}

	public void setCreateTime(Date createTime) {

		this.createTime = createTime;
	}

	@Column(name = "update_time", length = 19)
	public Date getUpdateTime() {

		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {

		this.updateTime = updateTime;
	}

}
