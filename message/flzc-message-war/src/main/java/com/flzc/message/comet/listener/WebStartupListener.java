package com.flzc.message.comet.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.flzc.message.comet.server.CometSocketServer;
import com.flzc.message.comet.web.CometService;

/**
 * 服务监听器
 *
 * @ClassName: WebStartupListener 
 * @Description: TODO
 * @author bing.xiao
 * @date 2015-9-6 下午2:53:50 
 *
 */
public class WebStartupListener implements ServletContextListener, HttpSessionListener {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WebStartupListener.class);
	
	private static long count = 0;//在线人数
	
	//comet链接service
	private CometService cometService;
	
	/**
	 * 启动初始化
	 */
	public void contextInitialized(ServletContextEvent event) {
		
		System.out.println("contextInit");
		try {
			WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());
			final CometSocketServer server = ctx.getBean(CometSocketServer.class);
			server.startWeb();
			server.startClient();
			Runtime.getRuntime().addShutdownHook(new Thread() {
				public void run() {
					server.closeWeb();
					server.closeClient();
				}
			});
			LOGGER.info("=================启动comet server=================");
			
			cometService = ctx.getBean(CometService.class);
			cometService.cometConnect();
			
			LOGGER.info("=================启动comet web client=================");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("启动comet失败：", e);
		}
		System.out.println("contextInit end");
	}
	
	
	public void contextDestroyed(ServletContextEvent event) {
		if(cometService != null){
			cometService.cometClose();
		}
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		count ++ ;
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		if(count > 0){
			count -- ;
		}
	}
	
	/*
	 * 获得在线人数
	 * 
	 */
	public static long getCount(){
		return count;
	}

}
