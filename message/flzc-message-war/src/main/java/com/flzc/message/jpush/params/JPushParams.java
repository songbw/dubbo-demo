package com.flzc.message.jpush.params;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class JPushParams {

	private String platform;
	private Audience audience;
	private Notification notification;
	private Message message;
	private Options options;
	
	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public Audience getAudience() {
		return audience;
	}

	public void setAudience(Audience audience) {
		this.audience = audience;
	}

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Options getOptions() {
		return options;
	}

	public void setOptions(Options options) {
		this.options = options;
	}
	
	
	public static void main(String[] args) {
		JPushParams params = new JPushParams();
		params.setPlatform(DeviceEnum.All.value());
		Audience audience = new Audience();
		List<String> alias = new ArrayList<>();
		alias.add("1_1");
		alias.add("1_2");
		alias.add("1_3");
		alias.add("1_4");
		audience.setAlias(alias);
		params.setAudience(audience);
		Notification notification = new Notification();
		notification.setAlert("hello word!");
		notification.setTitle("title");
		params.setNotification(notification);
		Options options = new Options();
		options.setTime_to_live(0);
		params.setOptions(options);
		
		System.out.println(JSON.toJSONString(params));
	}
}
