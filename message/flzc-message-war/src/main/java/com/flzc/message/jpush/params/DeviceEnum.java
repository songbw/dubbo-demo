package com.flzc.message.jpush.params;

public enum DeviceEnum {
	All("all"),
	
	Android("android"),
	
	IOS("ios");
	
	private final String value;
	private DeviceEnum(final String value) {
		this.value = value;
	}
	public String value() {
		return this.value;
	}
}