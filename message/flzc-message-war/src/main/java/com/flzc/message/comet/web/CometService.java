package com.flzc.message.comet.web;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.flzc.message.comet.model.CometMessage;
import com.flzc.message.common.CometMessageType;
import com.flzc.message.utils.ConfigUtils;

/**
 * Comet操作信息类
 * 
 *
 */
@Service
public class CometService{
	
	public static Map<String, IoSession> webIoSession = new HashMap<String, IoSession>();
	
	//链接信息的处理者
	@Autowired
	private CometMessageHandler cometMessageHandler;
	
	//与comet服务链接的会话信息类
	private IoSession session;
	
	//链接者
	private IoConnector connector;
	
	

	/**
	 * 发送消息
	 * 
	 * @param cometMessage
	 */
	public boolean send(CometMessage cometMessage){
		session = webIoSession.get(CometMessageType.WEB_IOSESSION.getCode());
		if(check()){
			session.resumeWrite();
			session.write(cometMessage);
			return true;
		}
		return false;
	}

	/**
	 * 验证会话链接是否有效
	 * 
	 * @return
	 */
	private boolean check(){
		boolean available = false;
		if(session != null && !session.isClosing() && session.isConnected()){
			available = true;
		}
		return available;
	}
	
	/**
	 * 链接comet服务
	 * 
	 * @param cometMessageHandler
	 */
	public void cometConnect(){
		connector = new NioSocketConnector();
		connector.getFilterChain().addLast("logger", new LoggingFilter(CometService.class));
		connector.getFilterChain().addLast(
			"codec", 
			new ProtocolCodecFilter(
				new ObjectSerializationCodecFactory()
			)
		);
		connector.setHandler(cometMessageHandler);
		connector.getSessionConfig().setReadBufferSize(2048);
		//设置闲置时间,默认是禁用的
//		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		int port = Integer.parseInt(ConfigUtils.getProperty("comet.web.port"));
		connector.connect(new InetSocketAddress(port));
	}
	
	/**
	 * 关闭与comet的链接
	 * 
	 */
	public void cometClose(){
		if(connector != null){
			Map<Long, IoSession> sessions = connector.getManagedSessions();
			if(sessions != null){
				Set<Map.Entry<Long, IoSession>> entrys = sessions.entrySet();
				Iterator<Map.Entry<Long, IoSession>> iterator = entrys.iterator();
				while(iterator.hasNext()){
					Map.Entry<Long, IoSession> entry = iterator.next();
					entry.getValue().close(true);
				}
			}
			connector.dispose();
			connector = null;
		}
	}
	
	/**
	 * 关闭会话链接
	 * 
	 */
	public void closeSession(){
		if(check()){
			session.close(false);
			session = null;
		}
	}
	
	public void setSession(IoSession session) {
		this.session = session;
	}

	public IoSession getSession() {
		return session;
	}
	
}

