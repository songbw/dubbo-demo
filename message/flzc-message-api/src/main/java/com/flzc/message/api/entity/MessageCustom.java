package com.flzc.message.api.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MessageCustom entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "message_custom")
public class MessageCustom implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userType;
	private Integer pushType;
	private Integer pushToPhone;
	private Integer pushToEmail;
	private Integer sendType;
	private Date sendTime;
	private Integer msgType;
	private String title;
	private String content;
	private Date createTime;

	// Constructors

	/** default constructor */
	public MessageCustom() {
	}

	/** full constructor */
	public MessageCustom(Integer userType,
			Integer pushType, Integer sendType,
			Date sendTime, Integer msgType, String title, String content,
			Date createTime) {
		this.userType = userType;
		this.pushType = pushType;
		this.sendType = sendType;
		this.sendTime = sendTime;
		this.msgType = msgType;
		this.title = title;
		this.content = content;
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

	@Column(name = "user_type")
	public Integer getUserType() {
		return this.userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	@Column(name = "push_type")
	public Integer getPushType() {
		return this.pushType;
	}

	public void setPushType(Integer pushType) {
		this.pushType = pushType;
	}
	
	@Column(name = "push_to_phone")
	public Integer getPushToPhone() {
		return pushToPhone;
	}

	public void setPushToPhone(Integer pushToPhone) {
		this.pushToPhone = pushToPhone;
	}

	@Column(name = "push_to_email")
	public Integer getPushToEmail() {
		return pushToEmail;
	}

	public void setPushToEmail(Integer pushToEmail) {
		this.pushToEmail = pushToEmail;
	}

	@Column(name = "send_type")
	public Integer getSendType() {
		return this.sendType;
	}

	public void setSendType(Integer sendType) {
		this.sendType = sendType;
	}

	@Column(name = "send_time", length = 19)
	public Date getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	@Column(name = "msg_type")
	public Integer getMsgType() {
		return this.msgType;
	}

	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}

	@Column(name = "title", length = 50)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content", length = 500)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}