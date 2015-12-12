package com.flzc.message.comet.server;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flzc.base.util.Memcached;
import com.flzc.message.comet.model.CometMessage;
import com.flzc.message.common.CometMessageType;
import com.flzc.message.common.Constant;
import com.flzc.message.common.RecipientMessageType;
import com.flzc.message.utils.StringTools;
import com.google.gson.Gson;

/**
 * 
 * 处理与web服务器的链接
 * 
 * @author Administrator
 *
 */
public class WebCometHandler extends IoHandlerAdapter {

	//日志
	private static final Logger LOGGER = LoggerFactory.getLogger(WebCometHandler.class);
	
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
		LOGGER.info("服务器收到的消息:");
		CometMessage cometMessage = (CometMessage) message;
		//验证是否是合法的web链接
		boolean isWeb = checkWebConnect(session, cometMessage);
		if(isWeb){
			LOGGER.info("验证web服务链接已通过--------------服务器----------------:" + session);
			//向用户浏览器发送信息
			processor(session, cometMessage);
		}else{
			//关闭此链接
			//如果参数 immediately为 true的话,连接会等到队列中所有的数据发送请求都完成之后才关闭；否则的话就立即关闭
			session.close(false);
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
		super.sessionClosed(session);
	}

	/**
	 * 6.会话连接闲置
	 * 
	 */
	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		//System.out.println("会话连接闲置");
		super.sessionIdle(session, status);
	}

	/**
	 * x.发生错误
	 * 
	 */
	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		LOGGER.error("发生错误");
		super.exceptionCaught(session, cause);
	}
	
	/**
	 * 验证是否是合法的web链接
	 * 
	 * @param session
	 * @param cometMessage
	 * @return
	 */
	private Boolean checkWebConnect(IoSession session, CometMessage cometMessage){
		boolean isWeb = false;
		//是否有发送的信息
		if(cometMessage != null){
			//获取会话中保存的验证信息
			Object obj = session.getAttribute("isWeb");
			if(obj != null){
				String is = String.valueOf(obj);
				//验证通过
				if(Constant.COMET_CHECK_WEB_VALUE.equals(is)){
					isWeb = true;
				}
			}else{
				//获得信息的类型
				String type = cometMessage.getType();
				//获得信息的内容
				String content = cometMessage.getContent();
				//信息类型是否是验证web服务的类型
				if(StringTools.isNotBlank(type) && StringTools.isNotBlank(content) && type.equals(CometMessageType.CHECK_WEB.getCode())){
					String key = String.format(Constant.COMET_CHECK_WEB, content);
					String check = (String) Memcached.get(key);
					if(StringTools.isNotBlank(check) && Constant.COMET_CHECK_WEB_VALUE.equals(check)){
						//验证通过标志此session是合法的链接
						session.setAttribute("isWeb", Constant.COMET_CHECK_WEB_VALUE);
						isWeb = true;
						//验证通过删除此key验证信息
						Memcached.remove(key);
					}
				}
			}
		}
		return isWeb;
	}
	
	/**
	 * 处理收到的消息
	 * 
	 * 
	 * @param session
	 * @param cometMessage
	 */
	private void processor(IoSession session, CometMessage cometMessage){
		String type = cometMessage.getType();
		if(StringTools.isNotBlank(type)){
			CometMessageType current = CometMessageType.getCometMessageType(type);
			List<String> userIds = cometMessage.getUserIds();
			String message = GSON.toJson(cometMessage);
			LOGGER.info("comet收到web短发来的消息:====="+type+"=============" + message);
			System.out.println("comet收到web短发来的消息:====="+type+"=============" + message);
			switch (current) {
			case RECIPIENT_CONSUMER:
				multiple(userIds, RecipientMessageType.RECIPIENT_CONSUMER.getCode(), message);
				break;
			case RECIPIENT_BROKER:
				multiple(userIds, RecipientMessageType.RECIPIENT_BROKER.getCode(), message);
				break;
			case RECIPIENT_DEVELOPER:
				multiple(userIds, RecipientMessageType.RECIPIENT_DEVELOPER.getCode(), message);
				break;
			case RECIPIENT_BROKERAGE_FIRM:
				multiple(userIds, RecipientMessageType.RECIPIENT_BROKERAGE_FIRM.getCode(), message);
				break;
			case RECIPIENT_AC_STAFF:
				multiple(userIds, RecipientMessageType.RECIPIENT_AC_STAFF.getCode(), message);
				break;
			default:
				break;
			}
		}
	}
	
	private void multiple(List<String> userIds,Integer type, String message){
		for (String userId : userIds) {
			sendToClient(String.format("%s_%s", userId, type), message);
		}
	}
	
	/**
	 * 向客户端发送消息
	 * @param userId
	 * @param message
	 */
	private void sendToClient(String userId, String message){
		//重发10次
		sendToClient(userId, message, 10);
	}
 
	private void sendToClient(String userId, String message, int time){
 
		Map<String, List<IoSession>> webSessions = CometContent.getUserSessions(userId);
		
		if(time != 0 &&webSessions.isEmpty() ){
			try{
				Thread.sleep(1000);
				sendToClient(userId,message, --time);
			}
			catch (Exception e) {
				LOGGER.info("the user "+userId+" has been not connected!");
			}
		}
		if(webSessions != null){
			LOGGER.info("comet服务正在向客户端发送消息" + message);
			Set<Map.Entry<String, List<IoSession>>> entrys = webSessions.entrySet();
			Iterator<Map.Entry<String, List<IoSession>>> iterator = entrys.iterator();
			// 获得此用户的所有的session
			while(iterator.hasNext()){
				Entry<String, List<IoSession>> entry = iterator.next();
				List<IoSession> sessions = entry.getValue();
				for (IoSession session : sessions) {
					session.write(message);
				}
			}
			
		
		}
	}

	
}
