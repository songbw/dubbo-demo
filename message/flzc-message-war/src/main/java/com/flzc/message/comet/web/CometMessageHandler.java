package com.flzc.message.comet.web;

import java.util.UUID;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flzc.base.util.Memcached;
import com.flzc.message.comet.model.CometMessage;
import com.flzc.message.common.CometMessageType;
import com.flzc.message.common.Constant;

/**
 * web处理comet连接的处理者
 *
 * @ClassName: CometMessageHandler 
 * @Description: TODO
 * @author bing.xiao
 * @date 2015-9-6 下午6:30:32 
 *
 */
@Component
public class CometMessageHandler extends IoHandlerAdapter {
	
	@Autowired
	private CometService cometService;
	
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
		
		//保存新的会话链接
		CometService.webIoSession.put(CometMessageType.WEB_IOSESSION.getCode(), session);
	}
	
	/**
	 * 2.会话用户连接
	 * 
	 */
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		//System.out.println("会话用户连接");
		super.sessionOpened(session);
		//生成uuid验证信息放入memcache中进行验证是否是合法的web链接
		String uuid = UUID.randomUUID().toString();
		String key = String.format(Constant.COMET_CHECK_WEB, uuid);
		Memcached.set(key, Constant.COMET_CHECK_WEB_VALUE);
		session.resumeWrite();
		CometMessage message = new CometMessage();
		message.setType(CometMessageType.CHECK_WEB.getCode());
		message.setContent(uuid);
		//将验证消息发个comet服务进行验证
		session.write(message);
	}
	
	/**
	 * 3.接收到消息
	 * 
	 */
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		//System.out.println("客户端收到的消息");
		//不处理任何收到的信息
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
		//关闭已保存的会话链接
		cometService.closeSession();
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
//		System.out.println("发生错误");
		super.exceptionCaught(session, cause);
	}
	
}
