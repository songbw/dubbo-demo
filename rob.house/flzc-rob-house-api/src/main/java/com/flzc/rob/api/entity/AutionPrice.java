package com.flzc.rob.api.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户竞拍表
 * AutionPrice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "aution_price")
public class AutionPrice implements java.io.Serializable {

	// Fields

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 7797188038850688545L;
	private Integer id;
	private Integer activityId;//活动id
	private Integer userId;//用户id
	private Integer price;//价格
	private Date createTime;//创建时间
	
	private String userName;//用户名
	private Integer winner;//拍得者标识:0表示未中标，1中标',
	

	// Constructors

	/** default constructor */
	public AutionPrice() {
	}

	/** minimal constructor */
	public AutionPrice(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public AutionPrice(Integer id, Integer activityId, Integer userId,
			Integer price, Date createTime) {
		this.id = id;
		this.activityId = activityId;
		this.userId = userId;
		this.price = price;
		this.createTime = createTime;
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

	@Column(name = "activity_id")
	public Integer getActivityId() {
		return this.activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	@Column(name = "user_id")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "price")
	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name="user_name")
	public String getUserName() {
	
		return userName;
	}

	
	public void setUserName(String userName) {
	
		this.userName = userName;
	}

	
	public Integer getWinner() {
	
		return winner;
	}

	
	public void setWinner(Integer winner) {
	
		this.winner = winner;
	}

}