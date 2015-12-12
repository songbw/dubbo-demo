package com.flzc.base.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @ClassName: AppGroup 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年8月4日 上午11:16:24
 */
@Entity
@Table(name="app_group")
public class AppGroup implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -5921215431861281359L;

	//id
	private Integer id;
	
	//名字
	private String groupName;
	
	//排序
	private String groupDesc;
	
	//验证码
	private String groupCode;
	
	//排序
	private Integer groupSeq;
	
	//创建时间
	private Date createDate;
	
	public AppGroup(){}
	
	public AppGroup(Integer id){
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
	
		return id;
	}

	
	public void setId(Integer id) {
	
		this.id = id;
	}

	@Column(name="group_name")
	public String getGroupName() {
	
		return groupName;
	}

	
	public void setGroupName(String groupName) {
	
		this.groupName = groupName;
	}

	@Column(name="group_desc")
	public String getGroupDesc() {
	
		return groupDesc;
	}

	
	public void setGroupDesc(String groupDesc) {
	
		this.groupDesc = groupDesc;
	}

	@Column(name="group_code")
	public String getGroupCode() {
	
		return groupCode;
	}

	
	public void setGroupCode(String groupCode) {
	
		this.groupCode = groupCode;
	}

	@Column(name="group_seq")
	public Integer getGroupSeq() {
	
		return groupSeq;
	}

	
	public void setGroupSeq(Integer groupSeq) {
	
		this.groupSeq = groupSeq;
	}

	@Column(name="create_date")
	public Date getCreateDate() {
	
		return createDate;
	}

	
	public void setCreateDate(Date createDate) {
	
		this.createDate = createDate;
	}
	
}
