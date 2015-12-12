package com.flzc.rob.api.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 答案选项
 * AnswerItems entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "answer_items")
public class AnswerItems implements java.io.Serializable {

	// Fields

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 3046997726199021095L;
	private Integer id;
	private Integer questionId;//问题id
	private String answerContent;//答案内容
	private Date createTime;//创建时间
	private Integer status;//0代表正常、1代表删除

	// Constructors

	/** default constructor */
	public AnswerItems() {
	}

	/** full constructor */
	public AnswerItems(Integer questionId, String answerContent, Date createTime) {
		this.questionId = questionId;
		this.answerContent = answerContent;
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

	@Column(name = "question_id")
	public Integer getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	@Column(name = "answer_content", length = 200)
	public String getAnswerContent() {
		return this.answerContent;
	}

	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}

	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
	public Integer getStatus() {
	
		return status;
	}

	
	public void setStatus(Integer status) {
	
		this.status = status;
	}

}