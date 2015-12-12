package com.flzc.rob.api.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 问答活动服务保障表
 * @ClassName: AnswerQuestionGuarantee 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年10月14日 下午9:02:16
 */
@Entity
@Table(name="answer_question_guarantee")
public class AnswerQuestionGuarantee implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -3618064012423421533L;

	private Integer id;
	
	private Integer actId;//活动id
	
	private String title;//标题
	
	private String content;//内容
	
	private String imageUrl;//图片地址
	
	private Date createTime;//创建时间

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
	
		return id;
	}

	
	public void setId(Integer id) {
	
		this.id = id;
	}

	@Column(name="act_id")
	public Integer getActId() {
	
		return actId;
	}

	
	public void setActId(Integer actId) {
	
		this.actId = actId;
	}

	
	public String getTitle() {
	
		return title;
	}

	
	public void setTitle(String title) {
	
		this.title = title;
	}

	
	public String getContent() {
	
		return content;
	}

	
	public void setContent(String content) {
	
		this.content = content;
	}

	@Column(name="image_url")
	public String getImageUrl() {
	
		return imageUrl;
	}

	
	public void setImageUrl(String imageUrl) {
	
		this.imageUrl = imageUrl;
	}

	@Column(name="create_time")
	public Date getCreateTime() {
	
		return createTime;
	}

	
	public void setCreateTime(Date createTime) {
	
		this.createTime = createTime;
	}
	
	
}
