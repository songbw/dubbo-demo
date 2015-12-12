/**
 * 
 */
package com.flzc.message.api.service;

import java.util.List;
import java.util.Map;

import com.flzc.message.api.entity.MessageCustom;
import com.flzc.message.api.entity.MessageSystem;


/** 
 * 消息服务接口
 * @ClassName: MessageService 
 * @Description: TODO
 * @author bing.xiao
 * @date 2015-9-6 下午2:13:30 
 *  
 */
public interface MessageService{

	
	/**
	 * 发送系统消息
	 * @param recType
	 * @param recUserId
	 * @param msgId
	 * @return
	 */
	public abstract boolean sendSystemMessage(Integer recType, String recUserId, String code, String content);
	
	/**
	 * 发送短消息
	 * @param msgId
	 * @param recUserIds
	 * @return
	 */
	public abstract boolean sendCustomMessage(Integer msgId, List<String> recUserIds);
	
	/**
	 * 修改用户已读系统消息状态
	 * @param msgId
	 * @param userId
	 * @return
	 */
	public abstract boolean readSystemMessage(Integer msgId, String userId);
	
	/**
	 * 修改用户已读自定义消息状态
	 * @param msgId
	 * @param userId
	 * @return
	 */
	public abstract boolean readCustomMessage(Integer msgId, String userId);
	
	/**
	 * 根据用户Id和用户类型查询用户自定义消息
	 * @param userId
	 * @param userType
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	public abstract List<MessageCustom> getUserCustomMessageList(String userId, Integer userType,Integer curPage,Integer pageSize);
	
	/**
	 * 根据用户Id和用户类型查询用户系统消息
	 * @param userId
	 * @param userType
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	public abstract List<MessageSystem> getUserSystemMessageList(String userId, Integer userType, Integer curPage, Integer pageSize);
	
	/**
	 * 获取用户消息
	 * @param userId
	 * @param userType
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	public abstract List<Map<String, Object>> getUserMessageList(String userId, Integer userType, Integer curPage, Integer pageSize);
	
	/**
	 * 获取用户消息数量
	 * @param userId
	 * @param userType
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	public int getUserMessageListSize(String userId, Integer userType);


	/**
	 * 查询用户未读消息
	 * @param userId
	 * @param userType
	 * @return
	 */
	public int queryUnreadMessageNum(String userId ,Integer userType);

	public void updateAllRead(String userId, Integer userType) throws Exception;
	
	/**
	 * 根据code查询消息模板
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public String queryTemplateByCode(String code) throws Exception;
	
	/**
	 * 删除用户系统消息
	 * @param messageCode
	 * @param recType
	 * @throws Exception
	 */
	public Boolean deleteSystemMessage(String userId,String messageCode, Integer recType) throws Exception;
	
	/**
	 * 根据code查询消息模板
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public MessageSystem queryMessageSystemByCode(String code) throws Exception;
}
