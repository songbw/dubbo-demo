package com.flzc.message.comet.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * web长连接传输数据信息类
 * 
 * 
 */
public class CometMessage implements Serializable {

	private static final long serialVersionUID = -7062713258379159235L;
	
	//用户id
	private List<String> userIds;
	
	//信息类型
	private String type;
	
	//信息内容
	private String content;
	
	//时间
	private Date date;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setUserIds(List<String> userIds) {
		this.userIds = userIds;
	}

	public List<String> getUserIds() {
		return userIds;
	}
	
}
