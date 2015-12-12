/**
 * 
 */
package com.flzc.message.common;

import java.io.Serializable;

/** 
 * 接收人类型
 * @ClassName: RecipientMessageType 
 * @Description: TODO
 * @author bing.xiao
 * @date 2015-9-6 下午1:35:39 
 *  
 */
public enum RecipientMessageType implements Serializable {
	
	RECIPIENT_CONSUMER(1,"普通用户"),
	RECIPIENT_BROKER(2,"经纪人"),
	RECIPIENT_DEVELOPER(3,"开发商"),
	RECIPIENT_BROKERAGE_FIRM(4,"经纪公司"),
	RECIPIENT_AC_STAFF(5,"案场人员");

	RecipientMessageType(Integer code, String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public static RecipientMessageType getRecipientMessageType(Integer integer){
		for (RecipientMessageType messageType : RecipientMessageType.values()) {
			if(messageType.getCode().equals(integer)){
				return messageType;
			}
		}
		return null;
	}
	
	private final Integer code;
	private final String desc;
	
	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}
	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}
	
	
}
