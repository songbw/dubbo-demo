package com.flzc.message.comet.server;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.flzc.message.utils.ConfigUtils;

/**
 * comet服务端(数据中转站)
 *
 * @ClassName: CometSocketServer 
 * @Description: TODO
 * @author bing.xiao
 * @date 2015-9-6 下午2:27:50 
 *
 */
public class CometSocketServer{
	
	//浏览器链接处理者
	@Autowired 
	private ClientCometHandler userCometHandler;
	//web服务器链接处理者
	@Autowired
	private WebCometHandler webCometHandler;
	
	/*@Autowired
	@Qualifier("config/config")
	private Properties config;*/
	
	private static Logger LOGGER = LoggerFactory.getLogger(CometSocketServer.class);

	//与浏览器的长连接接受者
	private IoAcceptor clientIoAcceptor = null;
	
	//与web服务器的长连接接受者
	private IoAcceptor webIoAcceptor = null;
	
	public static void main(String[] args) throws IOException {
		final ApplicationContext ctx = new ClassPathXmlApplicationContext("/config/spring/server.xml");
		final CometSocketServer server = ctx.getBean(CometSocketServer.class);
		server.startWeb();
		server.startClient();
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				server.closeWeb();
				server.closeClient();
			}
		});
	}
	
	/**
	 * 启动和用户浏览器的监听
	 * 
	 * @throws IOException
	 */
	public void startClient() throws IOException{
		clientIoAcceptor = new NioSocketAcceptor();
		//日志记录
		clientIoAcceptor.getFilterChain().addLast("logger", new LoggingFilter(CometSocketServer.class));
		//编码反编码过滤器注册
		clientIoAcceptor.getFilterChain().addLast(
			"codec", 
			new ProtocolCodecFilter(
				new TextLineCodecFactory(
					Charset.forName("UTF-8"),
					//以\0结束
					LineDelimiter.NUL,
					LineDelimiter.NUL
				)
			)
		);
		clientIoAcceptor.setHandler(userCometHandler);
		clientIoAcceptor.getSessionConfig().setReadBufferSize(2048);
		//设置闲置时间,默认是禁用的
		//acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		int port = Integer.parseInt(ConfigUtils.getProperty("comet.client.port"));
		clientIoAcceptor.bind(new InetSocketAddress(port));
		LOGGER.info("与用户浏览器的监听已启动.....");
	}
	
	/**
	 * 关闭与浏览器用户的链接
	 * 
	 */
	public void closeClient(){
		LOGGER.info("正在关闭与客户端浏览器的监听.....");
		/*Set<Integer> set = CometContent.userSessionsKeySet();
		List<Integer> userIds = new ArrayList<Integer>(set);*/
		//TODO
		//更新所有用户为不在线
		//userService.updateOnline(userIds, false);
		clientIoAcceptor.dispose();
		LOGGER.info("已关闭与客户端浏览器的监听.....");
	}
	
	/**
	 * 启动和web服务器的监听
	 * 
	 * @throws IOException
	 */
	public void startWeb() throws IOException{
		webIoAcceptor = new NioSocketAcceptor();
		webIoAcceptor.getFilterChain().addLast("logger", new LoggingFilter(CometSocketServer.class));
		webIoAcceptor.getFilterChain().addLast(
			"codec", 
			new ProtocolCodecFilter(
				new ObjectSerializationCodecFactory()
			)
		);
		webIoAcceptor.setHandler(webCometHandler);
		webIoAcceptor.getSessionConfig().setReadBufferSize(2048);
		//设置闲置时间,默认是禁用的
		//acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		int port = Integer.parseInt(ConfigUtils.getProperty("comet.web.port"));
		webIoAcceptor.bind(new InetSocketAddress(port));
		LOGGER.info("与web服务器的监听已启动.....");
	}
	
	/**
	 * 关闭与web服务器的链接 
	 * 
	 */
	public void closeWeb(){
		LOGGER.info("正在关闭与web服务器的监听.....");
		webIoAcceptor.dispose();
		LOGGER.info("已关闭与web服务器的监听.....");
	}

}
