package com.flzc.message.comet.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.mina.core.session.IoSession;

/**
 * comet上下文负责保存所有的用户链接信息
 *
 * @ClassName: CometContent 
 * @Description: TODO
 * @author bing.xiao
 * @date 2015-9-6 下午2:28:01 
 *
 */
public class CometContent {
	
	//所有的合法用户链接
	private static Map<String, Map<String, List<IoSession>>> userSessions = new ConcurrentHashMap<String, Map<String, List<IoSession>>>();
	
	private CometContent(){
		throw new AssertionError("非法的对象创建");
	}
	
	/**
	 * 添加用户链接
	 * (返回是否是首次链接)
	 * @param userId
	 * @param session
	 */
	public static boolean putUserSession(String userId, String webSessionId, IoSession session){
		boolean one = false;
		session.setAttribute("userId", userId);
		session.setAttribute("webSessionId", webSessionId);
		//该用户不同WEB的session会话链接信息
		Map<String, List<IoSession>> webSessions = userSessions.get(userId);
		//是否有已存在的链接
		if(webSessions != null){
			List<IoSession> sessions = webSessions.get(webSessionId);
			if(sessions != null){
				sessions.add(session);
			}else{
				sessions = new ArrayList<IoSession>();
				sessions.add(session);
				webSessions.put(webSessionId, sessions);
			}
		}else{
			webSessions = new HashMap<String, List<IoSession>>();
			List<IoSession> sessions = new ArrayList<IoSession>();
			sessions.add(session);
			webSessions.put(webSessionId, sessions);
			one = true;
			userSessions.put(userId, webSessions);
		}
		return one;
	}
	
	/**
	 * 根据用户ID获得用户的链接信息
	 * 
	 * @param userId
	 * @return
	 */
	public static Map<String, List<IoSession>> getUserSessions(String userId){
		Map<String, List<IoSession>> webSessions = userSessions.get(userId);
		if(webSessions == null){
			webSessions = Collections.emptyMap();
		}
		return webSessions;
	}
	
	/**
	 * 根据用户ID和web session ID获得用户的链接信息
	 * 
	 * @param userId
	 * @param webSessionId
	 * @return
	 */
	public static List<IoSession> getUserSessions(Integer userId, String webSessionId){
		Map<String, List<IoSession>> webSessions = userSessions.get(userId);
		List<IoSession> sessions = new ArrayList<IoSession>();
		if(webSessions != null){
			sessions = webSessions.get(webSessionId);
		}
		return sessions;
	}
	
	/**
	 * 判断用户是否在线
	 * 
	 * @param userId
	 * @return
	 */
	public static Boolean isOnline(String userId){
		Map<String, List<IoSession>> webSessions = getUserSessions(userId); 
		if(webSessions != null){
			Set<Map.Entry<String, List<IoSession>>> entrys = webSessions.entrySet();
			Iterator<Map.Entry<String, List<IoSession>>> iterator = entrys.iterator();
			// 获得此用户的所有的session
			while(iterator.hasNext()){
				Entry<String, List<IoSession>> entry = iterator.next();
				List<IoSession> sessions = entry.getValue();
				for (IoSession session : sessions) {
					if(session != null && session.isConnected()){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * 获得所有链接的用户的ID
	 * 
	 * @return
	 */
	public static Set<String> userSessionsKeySet(){
		Set<String> userIds = userSessions.keySet();
		if(userIds == null){
			userIds = Collections.emptySet();
		}
		return userIds;
	}
	
	/**
	 * 
	 * 删除用户的指定链接
	 * 
	 * @param session
	 */
	public static void removeUserSession(IoSession session){
		Object userIdObj = session.getAttribute("userId");
		Object webSessionIdObj = session.getAttribute("webSessionId");
		if(userIdObj != null && webSessionIdObj != null){
			String userId = userIdObj.toString();
			String webSessionId = String.valueOf(webSessionIdObj);
			Map<String, List<IoSession>> webSessions = userSessions.get(userId);
			if(webSessions != null){
				List<IoSession> sessions = webSessions.get(webSessionId);
				if(sessions != null){
					sessions.remove(session);
				}
			}
			if(!isOnline(userId)){
				userSessions.remove(userId);
			}
		}
	}
	
	/**
	 * 移除此web session下的所有链接会话 
	 * 
	 */
	public static void removeUserSession(Integer userId, String webSessionId){
		Map<String, List<IoSession>> webSessions = userSessions.get(userId);
		if(webSessions != null){
			webSessions.remove(webSessionId);
		}
	}
	
}
