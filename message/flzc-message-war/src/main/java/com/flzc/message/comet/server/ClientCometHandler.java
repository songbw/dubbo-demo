package com.flzc.message.comet.server;

import java.util.List;
import java.util.Map;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flzc.message.comet.model.CometMessage;
import com.flzc.message.common.CometMessageType;
import com.flzc.message.utils.StringTools;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * 客户端Handler 
 * @ClassName: ClientCometHandler 
 * @Description: TODO
 * @author bing.xiao
 * @date 2015-9-6 下午2:26:47 
 *
 */
public class ClientCometHandler extends IoHandlerAdapter {
	
	//沙箱安全验证信息
	private final static String xml = "<?xml version=\"1.0\"?><cross-domain-policy><site-control permitted-cross-domain-policies=\"all\"/><allow-access-from domain=\"*\" to-ports=\"*\"/></cross-domain-policy>";
	
	//日志
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientCometHandler.class);
	
	private static Gson GSON = new Gson();
	
	/**
	 * 1.新会话创建
	 * 
	 * 从性能方面考虑，不要在 sessionCreated 方法中执行过多的操作
	 * 
	 */
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		//System.out.println("新会话创建");
		super.sessionCreated(session);
	}
	
	/**
	 * 2.会话用户连接
	 * 
	 */
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		//System.out.println("会话用户连接");
		super.sessionOpened(session);
	}
	
	/**
	 * 3.接收到消息
	 * 
	 */
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		LOGGER.info("comet收到用户浏览器发来的消息: " + message);
		if(message != null){
			String json = String.valueOf(message);
			if("<policy-file-request/>".equals(json)){
				LOGGER.info("comet正在通过安全验证");
				//返回安全验证信息
				session.write(xml);
			}else{
				//解析返回的数据
				CometMessage cometMessage = GSON.fromJson(json, CometMessage.class);
				if(cometMessage != null && StringTools.isNotBlank(cometMessage.getContent())){
					//Map<String, String> content = GSON.fromJson(cometMessage.getContent(), new TypeToken<Map<String, String>>(){}.getType());
					
					String type = cometMessage.getType();
					
					List<String> userIds = cometMessage.getUserIds();
					
					boolean isUser = false;
					
					if(StringTools.isNotBlank(type) && CometMessageType.CHECK_USER.getCode().equals(type)){
						//TODO验证用户
						
						isUser = true;
					}
					
					if(isUser){
						//String webSessionId = content.get("webSessionId");
						String webSessionId = cometMessage.getContent();
						//合法用户则保存此链接信息
						//标注此链接的web的session会话ID
						boolean one = CometContent.putUserSession(userIds.get(0), webSessionId, session);
						if(one){
							LOGGER.info("用户" + userIds.get(0) + "上线了");
						}
					}else{
						LOGGER.info("comet验证用户浏览器未通过---------------------客户端---------");
					}
				}
			}
		}
	}
	
	/**
	 * 4.消息已发送
	 * 
	 */
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		//System.out.println("消息已发送");
		super.messageSent(session, message);
	}
	
	/**
	 * 5.会话关闭
	 * 
	 */
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		//System.out.println("会话关闭");
		//移除此链接会话
		Object obj = session.getAttribute("userId");
		CometContent.removeUserSession(session);
		if(obj != null){
			String userId = obj.toString();
			if(!CometContent.isOnline(userId)){
				LOGGER.info("用户" + userId + "下线了");
				//TODO
				//userService.updateOnline(userId, false);
			}
		}
	}

	/**
	 * 6.会话连接闲置
	 * 
	 */
	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		System.out.println("会话连接闲置");
		super.sessionIdle(session, status);
	}

	/**
	 * x.发生错误
	 * 
	 */
	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		System.out.println("发生错误");
		super.exceptionCaught(session, cause);
	}
	
}
