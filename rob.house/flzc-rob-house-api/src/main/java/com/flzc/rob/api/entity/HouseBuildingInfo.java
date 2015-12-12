package com.flzc.rob.api.entity;

import java.util.Date;
import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 楼盘信息实体类
 */
@Entity
@Table(name = "house_building_info")
public class HouseBuildingInfo implements java.io.Serializable {

	// Fields

	private Integer id;//主键
	private String name;//楼盘名
	private Integer pdId;//开发商Id
	private Integer referPrice;//参考价
	private Integer totalNum;//总户数
	private Integer saleNum;//在售户数
	private Date finishDate;//交房时间
	private String decoration;//装修情况
	private Float residentRate;//住房率
	private Integer proRightYear;//产权年限
	private String managerBiz;//物业单位
	private String managerBizType;//物业类型
	private Integer residentNum;//住户数
	private Float volRate;//容积率
	private Float greenRate;//绿化率
	private String parkNum;//停车位
	private String saleLicense;//预售许可
	private String managerFee;//物业费
	private Date preSaleDate;//预计开盘时间
	private Integer buildStage;//楼盘阶段
	private String longitude;//经度
	private String latitude;//纬度
	private Integer provinceId;//省id
	private Integer cityId;//市Id
	private Integer areaId;//县id
	private String dikouTicket;//抵扣券
	private Integer buildingState;//楼盘状态（40001已发布，40002活动中，40003已下架,40004未发布
	private String address;//楼盘地址
	private Integer ringPosition;//环线位置
	private Float fieldSize;//占地面积
	private Float buildingSize;//建筑面积
	private String pmcAddress;//物业地址
	private String saleAddress;//售楼地址
	private Integer heatSupply;//供暖
	private Integer powerSupply;//供电
	private Date startDate;//开工时间
	private Date endDate;//竣工时间

	private Date createTime;
	private Date updateTime;
	private Integer version;

	private String memo ;//楼盘详情
	private Integer soldNum;//已售数
	private Integer commision;//佣金


	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "pd_id")
	public Integer getPdId() {
		return this.pdId;
	}

	public void setPdId(Integer pdId) {
		this.pdId = pdId;
	}

	@Column(name = "refer_price")
	public Integer getReferPrice() {
		return this.referPrice;
	}

	public void setReferPrice(Integer referPrice) {
		this.referPrice = referPrice;
	}

	@Column(name = "total_num", precision = 12, scale = 0)
	public Integer getTotalNum() {
		return this.totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	@Column(name = "sale_num", length = 100)
	public Integer getSaleNum() {
		return this.saleNum;
	}

	public void setSaleNum(Integer saleNum) {
		this.saleNum = saleNum;
	}

	@Column(name = "finish_date")
	public Date getFinishDate() {
		return this.finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	@Column(name = "decoration", length = 50)
	public String getDecoration() {
		return this.decoration;
	}

	public void setDecoration(String decoration) {
		this.decoration = decoration;
	}

	@Column(name = "resident_rate", precision = 12, scale = 0)
	public Float getResidentRate() {
		return this.residentRate;
	}

	public void setResidentRate(Float residentRate) {
		this.residentRate = residentRate;
	}

	@Column(name = "pro_right_year")
	public Integer getProRightYear() {
		return this.proRightYear;
	}

	public void setProRightYear(Integer proRightYear) {
		this.proRightYear = proRightYear;
	}

	@Column(name = "manager_biz", length = 50)
	public String getManagerBiz() {
		return this.managerBiz;
	}

	public void setManagerBiz(String managerBiz) {
		this.managerBiz = managerBiz;
	}

	@Column(name = "manager_biz_type", length = 50)
	public String getManagerBizType() {
		return this.managerBizType;
	}

	public void setManagerBizType(String managerBizType) {
		this.managerBizType = managerBizType;
	}

	@Column(name = "resident_num")
	public Integer getResidentNum() {
		return this.residentNum;
	}

	public void setResidentNum(Integer residentNum) {
		this.residentNum = residentNum;
	}

	@Column(name = "vol_rate", precision = 12, scale = 0)
	public Float getVolRate() {
		return this.volRate;
	}

	public void setVolRate(Float volRate) {
		this.volRate = volRate;
	}

	@Column(name = "park_num", length = 100)
	public String getParkNum() {
		return this.parkNum;
	}

	public void setParkNum(String parkNum) {
		this.parkNum = parkNum;
	}

	@Column(name = "sale_license", length = 50)
	public String getSaleLicense() {
		return this.saleLicense;
	}

	public void setSaleLicense(String saleLicense) {
		this.saleLicense = saleLicense;
	}

	@Column(name = "manager_fee", length = 20)
	public String getManagerFee() {
		return this.managerFee;
	}

	public void setManagerFee(String managerFee) {
		this.managerFee = managerFee;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "pre_sale_date", length = 10)
	public Date getPreSaleDate() {
		return this.preSaleDate;
	}

	public void setPreSaleDate(Date preSaleDate) {
		this.preSaleDate = preSaleDate;
	}

	@Column(name = "build_stage")
	public Integer getBuildStage() {
		return this.buildStage;
	}

	public void setBuildStage(Integer buildStage) {
		this.buildStage = buildStage;
	}

	@Column(name = "longitude", length = 20)
	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Column(name = "latitude", length = 20)
	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
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

	@Column(name = "dikou_ticket", length = 50)
	public String getDikouTicket() {
		return this.dikouTicket;
	}

	public void setDikouTicket(String dikouTicket) {
		this.dikouTicket = dikouTicket;
	}

	@Column(name = "area_id")
	public Integer getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "update_time", length = 19)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "version")
	@Version
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Column(name = "building_state")
	public Integer getBuildingState() {
		return buildingState;
	}

	public void setBuildingState(Integer buildingState) {
		this.buildingState = buildingState;
	}

	@Column(name = "green_rate")
	public Float getGreenRate() {
		return greenRate;
	}

	public void setGreenRate(Float greenRate) {
		this.greenRate = greenRate;
	}
	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "ring_position")
	public Integer getRingPosition() {
		return ringPosition;
	}

	public void setRingPosition(Integer ringPosition) {
		this.ringPosition = ringPosition;
	}

	@Column(name = "field_size")
	public Float getFieldSize() {
		return fieldSize;
	}

	public void setFieldSize(Float fieldSize) {
		this.fieldSize = fieldSize;
	}

	@Column(name = "building_size")
	public Float getBuildingSize() {
		return buildingSize;
	}

	public void setBuildingSize(Float buildingSize) {
		this.buildingSize = buildingSize;
	}

	@Column(name = "pmc_address")
	public String getPmcAddress() {
		return pmcAddress;
	}

	public void setPmcAddress(String pmcAddress) {
		this.pmcAddress = pmcAddress;
	}

	@Column(name = "sale_address")
	public String getSaleAddress() {
		return saleAddress;
	}

	public void setSaleAddress(String saleAddress) {
		this.saleAddress = saleAddress;
	}

	@Column(name = "heat_supply")
	public Integer getHeatSupply() {
		return heatSupply;
	}

	public void setHeatSupply(Integer heatSupply) {
		this.heatSupply = heatSupply;
	}

	@Column(name = "power_supply")
	public Integer getPowerSupply() {
		return powerSupply;
	}

	public void setPowerSupply(Integer powerSupply) {
		this.powerSupply = powerSupply;
	}
	
	@Column(name = "start_date", length = 10)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "end_date", length = 10)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
    @Column(name = "memo")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "sold_num")
	public Integer getSoldNum() {
		return soldNum;
	}

	public void setSoldNum(Integer soldNum) {
		this.soldNum = soldNum;
	}
    @Column(name = "commision")
	public Integer getCommision() {
		return commision;
	}

	public void setCommision(Integer commision) {
		this.commision = commision;
	}
}