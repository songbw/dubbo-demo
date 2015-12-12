package com.flzc.message.common;

/**
 * comet消息类型 
 * 
 *
 */
public enum CometMessageType{
	
		CHECK_USER(-1, "check_user", "验证用户信息"),
		CHECK_WEB(-1, "check_web", "验证服务器"),
		WEB_IOSESSION(-1, "web_iosession","web服务端session"),
		RECIPIENT_CONSUMER(1, "recipient_consumer","普通用户"),
		RECIPIENT_BROKER(2, "recipient_broker","经纪人"),
		RECIPIENT_DEVELOPER(3, "recipient_developer","开发商"),
		RECIPIENT_BROKERAGE_FIRM(4, "recipient_brokerage_firm","经纪公司"),
		RECIPIENT_AC_STAFF(5, "recipient_ac_staff","案场人员");
		
		private final Integer id;
		private final String desc;
		private final String code;
		
		CometMessageType(Integer id, String code, String desc) {
			this.id = id;
			this.code = code;
			this.desc = desc;
		}
		
		public static CometMessageType getCometMessageType(String code){
			for (CometMessageType cometMessageType : CometMessageType.values()) {
				if(cometMessageType.getCode().equals(code)){
					return cometMessageType;
				}
			}
			return null;
		}
		
		public static CometMessageType getCometMessageType(Integer id){
			for (CometMessageType cometMessageType : CometMessageType.values()) {
				if(cometMessageType.getId().equals(id)){
					return cometMessageType;
				}
			}
			return null;
		}
		
		public String getDesc() {
			return desc;
		}

		public String getCode() {
			return code;
		}
		
		public Integer getId() {
			return id;
		}
}
