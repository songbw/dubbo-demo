package com.flzc.message.api.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MessageSystem entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "message_system")
public class MessageSystem implements java.io.Serializable {

	// Fields

	private Integer id;
	private String code;
	private String title;
	private String content;
	private Integer pushType;
	private Integer pushToPhone;
	private Integer pushToEmail;
	private Integer status;
	private Date createTime;
	private Date updateTime;
	private Integer version;

	// Constructors

	/** default constructor */
	public MessageSystem() {
	}

	/** full constructor */
	public MessageSystem(String title, String content, Integer pushType,
			Integer status, Date createTime, Date updateTime, Integer version) {
		this.title = title;
		this.content = content;
		this.pushType = pushType;
		this.status = status;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.version = version;
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
	
	@Column(name = "code", length = 50)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}