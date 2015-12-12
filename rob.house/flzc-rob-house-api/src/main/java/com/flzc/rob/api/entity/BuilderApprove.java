package com.flzc.rob.api.entity;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * BuilderApprove entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "builder_approve")
public class BuilderApprove implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer builderId;
	private String companyName;
	private String regAddr;
	private String licenseNo;
	private Date licensePeriodS;
	private Date licensePeriodE;
	private Integer qualificationLevel;
	private String qualificationNo;
	private Date qualificationPeriodS;
	private Date qualificationPeriodE;
	private Date passTime;
	private Integer provinceId;
	private Integer cityId;
	private Integer areaId;
	private String officeAddress;
	private String logoUrl;
	private String licUrl;
	private String quaUrl;
	private Date updateTime;
	private Integer version;
	private Date createTime;
	/**
	 * 状态:14001：待认证，14002认证通过，14003未通过
	 */
	private Integer status;


	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "builder_id")
	public Integer getBuilderId() {
		return this.builderId;
	}

	public void setBuilderId(Integer builderId) {
		this.builderId = builderId;
	}

	@Column(name = "company_name", length = 50)
	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name = "reg_addr")
	public String getRegAddr() {
		return this.regAddr;
	}

	public void setRegAddr(String regAddr) {
		this.regAddr = regAddr;
	}

	@Column(name = "license_no", length = 50)
	public String getLicenseNo() {
		return this.licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "license_period_s", length = 10)
	public Date getLicensePeriodS() {
		return this.licensePeriodS;
	}

	public void setLicensePeriodS(Date licensePeriodS) {
		this.licensePeriodS = licensePeriodS;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "license_period_e", length = 10)
	public Date getLicensePeriodE() {
		return this.licensePeriodE;
	}

	public void setLicensePeriodE(Date licensePeriodE) {
		this.licensePeriodE = licensePeriodE;
	}

	@Column(name = "qualification_level")
	public Integer getQualificationLevel() {
		return this.qualificationLevel;
	}

	public void setQualificationLevel(Integer qualificationLevel) {
		this.qualificationLevel = qualificationLevel;
	}

	@Column(name = "qualification_no", length = 50)
	public String getQualificationNo() {
		return this.qualificationNo;
	}

	public void setQualificationNo(String qualificationNo) {
		this.qualificationNo = qualificationNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "qualification_period_s", length = 10)
	public Date getQualificationPeriodS() {
		return this.qualificationPeriodS;
	}

	public void setQualificationPeriodS(Date qualificationPeriodS) {
		this.qualificationPeriodS = qualificationPeriodS;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "qualification_period_e", length = 10)
	public Date getQualificationPeriodE() {
		return this.qualificationPeriodE;
	}

	public void setQualificationPeriodE(Date qualificationPeriodE) {
		this.qualificationPeriodE = qualificationPeriodE;
	}

	@Column(name = "pass_time", length = 19)
	public Date getPassTime() {
		return this.passTime;
	}

	public void setPassTime(Date passTime) {
		this.passTime = passTime;
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

	@Column(name = "office_address", length = 100)
	public String getOfficeAddress() {
		return this.officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	@Column(name = "logo_url", length = 200)
	public String getLogoUrl() {
		return this.logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	@Column(name = "lic_url", length = 200)
	public String getLicUrl() {
		return this.licUrl;
	}

	public void setLicUrl(String licUrl) {
		this.licUrl = licUrl;
	}

	@Column(name = "qua_url", length = 200)
	public String getQuaUrl() {
		return this.quaUrl;
	}

	public void setQuaUrl(String quaUrl) {
		this.quaUrl = quaUrl;
	}

	@Column(name = "update_time", length = 19)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "version")
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	

}