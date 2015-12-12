package com.flzc.message.api.service;

/**
 * 发送消息接口
 * @author bing.xiao
 *
 */
public interface AuthenticateService {
		
	/**
	 * 发送消息
	 * @param mobileCaptcha
	 * @param conent
	 * @return
	 */
	public void sendMessage(String mobile,String content);
}
