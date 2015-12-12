package com.flzc.message.jpush.params;

import com.alibaba.fastjson.JSONObject;

public class Message {
	private String msg_content;
	private String title;
	private String content_type;
	private JSONObject extras;
	public String getMsg_content() {
		return msg_content;
	}
	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent_type() {
		return content_type;
	}
	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}
	public JSONObject getExtras() {
		return extras;
	}
	public void setExtras(JSONObject extras) {
		this.extras = extras;
	}
}
