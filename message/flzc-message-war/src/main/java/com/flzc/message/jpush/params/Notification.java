package com.flzc.message.jpush.params;

import com.alibaba.fastjson.JSONObject;

public class Notification {
	private String alert;
	private String title;
	private String builder_id;
	private JSONObject extras;
	public String getAlert() {
		return alert;
	}
	public void setAlert(String alert) {
		this.alert = alert;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBuilder_id() {
		return builder_id;
	}
	public void setBuilder_id(String builder_id) {
		this.builder_id = builder_id;
	}
	public JSONObject getExtras() {
		return extras;
	}
	public void setExtras(JSONObject extras) {
		this.extras = extras;
	}
}
