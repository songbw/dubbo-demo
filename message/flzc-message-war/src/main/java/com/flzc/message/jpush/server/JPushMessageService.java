package com.flzc.message.jpush.server;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.flzc.message.comet.model.JPushMessage;
import com.flzc.message.common.Constant;
import com.flzc.message.common.RecipientMessageType;
import com.flzc.message.jpush.connection.NativeHttpClient;
import com.flzc.message.jpush.connection.ResponseWrapper;
import com.flzc.message.jpush.connection.ServiceHelper;
import com.flzc.message.jpush.params.Audience;
import com.flzc.message.jpush.params.DeviceEnum;
import com.flzc.message.jpush.params.JPushParams;
import com.flzc.message.jpush.params.Notification;
import com.flzc.message.jpush.params.Options;
import com.flzc.message.utils.ConfigUtils;

@Service
public class JPushMessageService {
	private static final Logger LOGGER = LoggerFactory.getLogger(JPushMessageService.class);
	public boolean send(JPushMessage message) {
		RecipientMessageType type = RecipientMessageType.getRecipientMessageType(message.getType());
		String masterSecret = null;
		String appKey = null;
		//获取appkey 及  masterSecret
		switch (type) {
		case RECIPIENT_CONSUMER:
			appKey = ConfigUtils.getProperty("user.appKey");
			masterSecret = ConfigUtils.getProperty("user.masterSecret");
			break;
		case RECIPIENT_BROKER:
			appKey = ConfigUtils.getProperty("agency.appKey");
			masterSecret = ConfigUtils.getProperty("agency.masterSecret");
			break;
		case RECIPIENT_DEVELOPER:
			appKey = ConfigUtils.getProperty("developer.appKey");
			masterSecret = ConfigUtils.getProperty("developer.masterSecret");
			break;
		case RECIPIENT_BROKERAGE_FIRM:
			appKey = ConfigUtils.getProperty("agency.firm.appKey");
			masterSecret = ConfigUtils.getProperty("agency.firm.masterSecret");
			break;
		case RECIPIENT_AC_STAFF:
			appKey = ConfigUtils.getProperty("ac.staff.appKey");
			masterSecret = ConfigUtils.getProperty("ac.staff.masterSecret");
			break;
		default:
			break;
		}
		
		ServiceHelper.checkBasic(appKey, masterSecret);
		//生成验证信息
		String authCode = ServiceHelper.getBasicAuthorization(appKey, masterSecret);
		NativeHttpClient httpClient = new NativeHttpClient(authCode);
		
		JPushParams params = new JPushParams();
		//推送平台设置
		params.setPlatform(DeviceEnum.All.value());
		//推送设备指定
		Audience audience = new Audience();
		List<String> alias = new ArrayList<>();
		for(String id : message.getUserIds()){
			alias.add(String.format("%s_%s", id,message.getType()));
		}
		audience.setAlias(alias);
		params.setAudience(audience);
		//通知内容体设置
		Notification notification = new Notification();
		notification.setAlert(message.getContent());
		notification.setTitle(message.getTitle());
		params.setNotification(notification);
		//消息内容体设置
		Options options = new Options();
		options.setTime_to_live(Constant.JPUSH_TIME_TO_LIVE);
		params.setOptions(options);
		
		try {
			ResponseWrapper resp = httpClient.sendPost(ConfigUtils.getProperty(Constant.JPUSH_URL_KEY), JSON.toJSONString(params));
			LOGGER.info("JPush send resp{respCode:"+resp.responseCode+",respContent:"+resp.responseContent+"}");
			System.out.println("JPush send resp{respCode:"+resp.responseCode+",respContent:"+resp.responseContent+"}");
			if(resp.responseCode==Constant.JPUSH_RESP_SUCCESS){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
