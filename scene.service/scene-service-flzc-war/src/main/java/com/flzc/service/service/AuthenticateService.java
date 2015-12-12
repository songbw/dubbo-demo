package com.flzc.service.service;

/**
 * 验证码发送
 * @ClassName: AuthenticateService 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年8月22日 下午2:30:07
 */
public interface AuthenticateService {
	
	/**
	 * 生成验证码并发送
	 * @param captchaUrl 验证码发送地址
	 */
	public String creatCaptcha(String captchaUrl);
	
	/**
	 * 当前验证是否成功
	 * @param authenticationText 验证字符串
	 * @return
	 */
	public boolean authentication(String authenticationText);
	
	/**
	 * 发送消息
	 * @param mobileCaptcha
	 * @param conent
	 * @return
	 */
	public void sendMessage(String mobileCaptcha, String conent);
}
