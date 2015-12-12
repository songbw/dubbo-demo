package com.flzc.rob.api.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 活动汇总表
 * ActivityRecap entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "activity_recap")
public class ActivityRecap implements java.io.Serializable {

	// Fields

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -2045330967056172091L;
	private Integer id;
	private Integer buildingId;//楼盘id
	private Date showStartDate;//展示时间
	private Date actStartDate;//活动开始时间
	/**
	 * 活动类型表 (39001 答题抽奖；39002私人订制；39003,幸运竞拍)
	 */
	private Integer type;
	private Integer actStatus;//状态
	private Integer actId;//活动id
	private Date createTime;//创建时间
	private Date actEndDate;
	private Date actActiveEndDate;

		// Constructors

		/** default constructor */
		public ActivityRecap() {
		}

		/** full constructor */
		public ActivityRecap(Integer buildingId, Integer actId, Date showStartDate,
				Date actStartDate, Integer actStatus, Integer type,
				Date createTime, Date actEndDate, Date actActiveEndDate) {
			this.buildingId = buildingId;
			this.actId = actId;
			this.showStartDate = showStartDate;
			this.actStartDate = actStartDate;
			this.actStatus = actStatus;
			this.type = type;
			this.createTime = createTime;
			this.actEndDate = actEndDate;
			this.actActiveEndDate = actActiveEndDate;
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

		@Column(name = "building_id")
		public Integer getBuildingId() {
			return this.buildingId;
		}

		public void setBuildingId(Integer buildingId) {
			this.buildingId = buildingId;
		}

		@Column(name = "act_id")
		public Integer getActId() {
			return this.actId;
		}

		public void setActId(Integer actId) {
			this.actId = actId;
		}

		@Column(name = "show_start_date", length = 19)
		public Date getShowStartDate() {
			return this.showStartDate;
		}

		public void setShowStartDate(Date showStartDate) {
			this.showStartDate = showStartDate;
		}

		@Column(name = "act_start_date", length = 19)
		public Date getActStartDate() {
			return this.actStartDate;
		}

		public void setActStartDate(Date actStartDate) {
			this.actStartDate = actStartDate;
		}

		@Column(name = "act_status")
		public Integer getActStatus() {
			return this.actStatus;
		}

		public void setActStatus(Integer actStatus) {
			this.actStatus = actStatus;
		}

		@Column(name = "type")
		public Integer getType() {
			return this.type;
		}

		public void setType(Integer type) {
			this.type = type;
		}

		@Column(name = "create_time", length = 19)
		public Date getCreateTime() {
			return this.createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		@Column(name = "act_end_date", length = 19)
		public Date getActEndDate() {
			return this.actEndDate;
		}

		public void setActEndDate(Date actEndDate) {
			this.actEndDate = actEndDate;
		}

		@Column(name = "act_active_end_date", length = 19)
		public Date getActActiveEndDate() {
			return this.actActiveEndDate;
		}

		public void setActActiveEndDate(Date actActiveEndDate) {
			this.actActiveEndDate = actActiveEndDate;
		}
}