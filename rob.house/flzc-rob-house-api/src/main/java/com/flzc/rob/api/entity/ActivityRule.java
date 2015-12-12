package com.flzc.rob.api.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 竞拍活动协议表
 * AutionActivityRule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "activity_rule")
public class ActivityRule implements java.io.Serializable {

	// Fields

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -4963940573848100043L;
	private Integer id;
	private String title;//标题
	private String content;//内容
	private Date createTime;//创建时间
	private Integer type;//协议类型 1为答题、2为竞拍、3为定制

	// Constructors

	/** default constructor */
	public ActivityRule() {
	}

	/** full constructor */
	public ActivityRule(String title, String content,
			Date createTime,Integer type) {
		this.title = title;
		this.content = content;
		this.createTime = createTime;
		this.type = type;
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


	@Column(name = "title", length = 50)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content", length = 2000)
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

	
	public Integer getType() {
	
		return type;
	}

	
	public void setType(Integer type) {
	
		this.type = type;
	}

}