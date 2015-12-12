/**
 * 
 */
package com.flzc.message.service.impl;

import com.flzc.base.dao.BaseDao;
import com.flzc.base.dao.CommonDao;
import com.flzc.message.api.entity.MessageCustom;
import com.flzc.message.api.entity.MessageCustomLink;
import com.flzc.message.api.entity.MessageSystem;
import com.flzc.message.api.entity.MessageSystemLink;
import com.flzc.message.api.service.AuthenticateService;
import com.flzc.message.api.service.MessageService;
import com.flzc.message.biz.entity.Agency;
import com.flzc.message.biz.entity.SceneInfo;
import com.flzc.message.biz.entity.User;
import com.flzc.message.biz.service.AgencyBizService;
import com.flzc.message.biz.service.SceneInfoBizService;
import com.flzc.message.biz.service.UserBizService;
import com.flzc.message.comet.model.CometMessage;
import com.flzc.message.comet.model.JPushMessage;
import com.flzc.message.comet.web.CometService;
import com.flzc.message.common.CometMessageType;
import com.flzc.message.common.Constant;
import com.flzc.message.common.RecipientMessageType;
import com.flzc.message.jpush.server.JPushMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/** 
 *
 * @ClassName: MessageServiceImpl 
 * @Description: TODO
 * @author bing.xiao
 * @date 2015-9-6 下午2:16:40 
 *  
 */
 @Service("messageService")
public class MessageServiceImpl implements MessageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);
	
	@Autowired
	private CometService cometService;
	@Autowired
	private JPushMessageService jPushMessageService;
    @Autowired
    private BaseDao baseDao;
	@Autowired
	private CommonDao commonDao;
	/*@Resource(name="authService")
	private AuthenticateService authenticateEmailService;*/
	@Resource(name="authenticateService")
	private AuthenticateService authenticateMobileService;
	@Autowired
	private UserBizService userBizService;
	@Autowired
	private AgencyBizService agencyBizService;
	@Autowired
	private SceneInfoBizService sceneInfoBizService;
	
	@Override
	public boolean sendSystemMessage(Integer recType, String recUserId, String code, String content) {
		LOGGER.info("sendSystemMessage:{recType"+recType+",recUserId:"+recUserId+",code:"+code+",content:"+content+"}");
		if(recType == null || recUserId == null || code == null || content == null){
			return Boolean.FALSE;
		}
		boolean flag = Boolean.TRUE;
		try {
			MessageSystem message = this.queryByCode(code);
			
			MessageSystemLink msgLink = new MessageSystemLink();
			msgLink.setMsgCode(code);
			msgLink.setStatus(Constant.MESSAGE_UNREAD);
			msgLink.setRecType(recType);
			msgLink.setUserId(recUserId);
			msgLink.setContent(content);
			msgLink.setCreateTime(new Date());
			baseDao.save(msgLink);
			
			ArrayList<String> recUserIds = new ArrayList<String>();
			recUserIds.add(recUserId.toString());
			
			//发送web消息 
			if(message.getPushType() == Constant.MESSAGE_PUSH_STATUS_0){
				flag = this.sendWebMessage(recUserIds, content, CometMessageType.getCometMessageType(recType).getCode());
				if(flag){
					this.sendAppMessage(recUserIds, message.getTitle(), content, recType);
				}
				LOGGER.info("PushSystemMessage:{resp result:"+flag+"}");
			}
			//推送消息发送失败，则返回
			if(!flag){
				return flag;
			}
			//发送短信消息 
			if(message.getPushToPhone() == Constant.MESSAGE_PUSH_STATUS_0){
				String mobile = null;
				//普通用户
				if(recType == RecipientMessageType.RECIPIENT_CONSUMER.getCode()){
					User user = userBizService.queryUserByInviteCode(recUserId);
					mobile = user.getPhoneNumber();
				//经纪人
				}else if(recType == RecipientMessageType.RECIPIENT_BROKER.getCode()){
					Agency agency = agencyBizService.queryAgencyByInviteCode(recUserId);
					mobile = agency.getPhone();
				//案场人员
				}else if(recType == RecipientMessageType.RECIPIENT_AC_STAFF.getCode()){
					SceneInfo sceneInfo = sceneInfoBizService.querySceneInfoByInviteCode(recUserId);
					mobile = sceneInfo.getPhone();
				}
				authenticateMobileService.sendMessage(mobile, content);
			}
		} catch (Exception e) {
			LOGGER.error("发系统送消息失败:", e);
			e.printStackTrace();
			flag = Boolean.FALSE;
		}
		return flag;
	}

	@Override
	public boolean sendCustomMessage(Integer msgId, List<String> recUserIds) {
		LOGGER.info("sendCustomMessage:{msgId:"+msgId+"}");
		if(msgId == null || recUserIds == null || recUserIds.size() == 0){
			return false;
		}
		//发送web消息 
		boolean flag = Boolean.FALSE;
		try {
			MessageCustom message = baseDao.findById(MessageCustom.class, msgId);
			
			List<MessageCustomLink> links = new ArrayList<>();
			MessageCustomLink msgLink = null;
			for(String id : recUserIds){
				msgLink = new MessageCustomLink();
				msgLink.setMsgId(msgId);
				msgLink.setStatus(Constant.MESSAGE_UNREAD);
				msgLink.setRecType(message.getUserType());
				msgLink.setUserId(id);
				msgLink.setCreateTime(new Date());
				links.add(msgLink);
			}
			baseDao.saveAll(links);
			
			if(message.getPushType() == Constant.MESSAGE_PUSH_STATUS_0){
				flag = this.sendWebMessage(recUserIds, message.getContent(), CometMessageType.getCometMessageType(message.getUserType()).getCode());
				if(flag){
					flag = this.sendAppMessage(recUserIds, message.getTitle(), message.getContent(), message.getUserType());
				}
			}
			
			//推送消息发送失败，则返回
			if(!flag){
				return flag;
			}
			//发送短信消息 
			if(message.getPushToPhone() == Constant.MESSAGE_PUSH_STATUS_0){
				for(String recUserId : recUserIds){
					String mobile = null;
					//普通用户
					if(message.getUserType() == RecipientMessageType.RECIPIENT_CONSUMER.getCode()){
						User user = userBizService.queryUserByInviteCode(recUserId);
						mobile = user.getPhoneNumber();
					//经纪人
					}else if(message.getUserType() == RecipientMessageType.RECIPIENT_BROKER.getCode()){
						Agency agency = agencyBizService.queryAgencyByInviteCode(recUserId);
						mobile = agency.getPhone();
					//案场人员
					}else if(message.getUserType() == RecipientMessageType.RECIPIENT_AC_STAFF.getCode()){
						SceneInfo sceneInfo = sceneInfoBizService.querySceneInfoByInviteCode(recUserId);
						mobile = sceneInfo.getPhone();
					}
					authenticateMobileService.sendMessage(mobile, message.getContent());
				}
			}
			LOGGER.info("sendCustomMessage:{resp result:"+flag+"}");
		} catch (Exception e) {
			LOGGER.error("发送自定义消息失败:", e);
			flag = Boolean.FALSE;
		}
		return flag;
	}

	@Override
	public boolean readSystemMessage(Integer msgId, String userId) {
		if(msgId == null || userId == null){
			return false;
		}
		String hql = String.format("from MessageSystemLink where msgCode=%s and userId=%s", msgId, userId);
		
		List<MessageSystemLink> messages = baseDao.findByHql(hql);
		if(messages != null && messages.size() > 0){
			MessageSystemLink message = messages.get(0);
			message.setStatus(Constant.MESSAGE_READ);
			
			baseDao.saveOrUpdate(message);
		}
		return true;
	}

	@Override
	public boolean readCustomMessage(Integer msgId, String userId) {
		if(msgId == null || userId == null){
			return false;
		}
		String hql = String.format("from MessageCustom where msgId=%s and userId=%s", msgId, userId);
		
		List<MessageCustomLink> messages = baseDao.findByHql(hql);
		if(messages != null && messages.size() > 0){
			MessageCustomLink message = messages.get(0);
			message.setStatus(Constant.MESSAGE_READ);
			
			baseDao.saveOrUpdate(message);
		}
		return true;
	}
	
	/**
	 * 发送web端消息 
	 * @param msg
	 * @param recUserId
	 * @return
	 */
	private boolean sendWebMessage(List<String> userIds, String content, String type){
		CometMessage message = new CometMessage();
		message.setContent(content);
		message.setDate(new Date());
		message.setType(type);
		message.setUserIds(userIds);
		return cometService.send(message);
	}
	
	/**
	 * 发送app端消息 
	 * @param msg
	 * @param recUserId
	 * @return
	 */
	private boolean sendAppMessage(List<String> userIds, String title, String content, Integer type){
		JPushMessage message = new JPushMessage();
		message.setUserIds(userIds);
		message.setType(type);
		message.setTitle(title);
		message.setContent(content);
		return jPushMessageService.send(message);
	}

	@Override
	public List<MessageCustom> getUserCustomMessageList(String userId, Integer userType,Integer curPage,Integer pageSize) {
		
		if(curPage==0) curPage++;
		if(pageSize==0) pageSize=Constant.DEFAULT_PAGE_SIZE;
		String hql ="select mc from MessageCustom mc ,MessageCustomLink mcl where mc.id=mcl.msgId and mcl.userId=:userId and mcl.recType=:recType";
		
		List<MessageCustom> customList = baseDao.findByNamedParam(hql, new String[]{"userId","recType"}, new Object[]{userId,userType}, curPage, pageSize);
		return customList;
	}
	
	@Override
	public List<MessageSystem> getUserSystemMessageList(String userId, Integer userType,Integer curPage,Integer pageSize) {
		
		if(curPage==0) curPage++;
		if(pageSize==0) pageSize=Constant.DEFAULT_PAGE_SIZE;
		String hql ="select ms from MessageSystem ms ,MessageSystemLink msl where ms.code=msl.msgCode and msl.userId=:userId and msl.recType=:recType";
		
		List<MessageSystem> systemList = baseDao.findByNamedParam(hql, new String[]{"userId","recType"}, new Object[]{userId,userType}, curPage, pageSize);
		return systemList;
	}
	
	@Override
	public List<Map<String, Object>> getUserMessageList(String userId, Integer userType,Integer curPage,Integer pageSize) {
		
		if(curPage == null || curPage==0) curPage++;
		if(pageSize == null || pageSize==0) pageSize=Constant.DEFAULT_PAGE_SIZE;
		
		String sql =" select temp.id,temp.title,temp.content,temp.push_type as pushType,temp.create_time as createTime,temp.user_id as userId,temp.msg_code,temp.rec_type as recType,temp.status" +
					" from (" +
					" select ms.id,ms.title,msl.content,ms.push_type,msl.create_time,msl.user_id,msl.msg_code,msl.rec_type,msl.status "+
					" 	from message_system ms ,message_system_link msl "+
					"	where ms.code=msl.msg_code "+
					" union all " +
					" select mc.id,mc.title,mc.content,mc.push_type,mcl.create_time,mcl.user_id,mcl.msg_id as msg_code,mcl.rec_type,mcl.status "+
					" 	from message_custom mc ,message_custom_link mcl "+
					"	where mc.id=mcl.msg_id "+
					" ) temp where temp.user_id= '" + userId + "' and temp.rec_type= "+userType+" order by temp.create_time desc limit " + (curPage - 1) * pageSize + "," + pageSize;
					
		List<Map<String, Object>> messageList = baseDao.findListMapBySql(sql);
		return messageList;
	}
	
	@Override
	public int getUserMessageListSize(String userId, Integer userType) {
		
		String sql =" select count(temp.id)" +
					" from (" +
					" select ms.id,ms.title,ms.content,ms.push_type,ms.create_time,msl.user_id,msl.rec_type,msl.status "+
					" 	from message_system ms ,message_system_link msl "+
					"	where ms.code=msl.msg_code "+
					" union all " +
					" select mc.id,mc.title,mc.content,mc.push_type,mc.create_time,mcl.user_id,mcl.rec_type,mcl.status "+
					" 	from message_custom mc ,message_custom_link mcl "+
					"	where mc.id=mcl.msg_id "+
					" ) temp where temp.user_id= '" + userId + "' and temp.rec_type= "+userType;
		
		return this.commonDao.findCountBySql(sql);
	}

	/**
	 * 查询用户未读消息
	 *
	 * @param userId
	 * @param userType
	 * @return
	 */
	@Override
	public int queryUnreadMessageNum(String userId, Integer userType) {
		String sql = "select sum(temp.num) from (SELECT " +
				" COUNT(id) as num " +
				"FROM " +
				" message_custom_link m " +
				"WHERE " +
				" m.rec_type =  " + userType +
				" AND m.`status` = 0 " +
				" AND m.user_id = '" + userId+"'" +
			  " union all " +
			  "SELECT " +
				" COUNT(id) as num " +
				"FROM " +
				" message_system_link m " +
				"WHERE " +
				" m.rec_type =  " + userType +
				" AND m.`status` = 0 " +
				" AND m.user_id = '" + userId+"') temp";
		return this.commonDao.findCountBySql(sql);
	}

	/**
	 * 把用户所有信息设置成已读
	 * @param userId
	 * @param userType
	 * @throws Exception
	 */
	@Override
	public void updateAllRead(String invitiCode ,Integer userType) throws Exception {
		String hql = "update MessageSystemLink set status =1 where userId=:userId and recType=:userType";
		this.commonDao.updateByHql(hql,new String[]{"userId","userType"},new Object[]{invitiCode,userType});
		
		hql = "update MessageCustomLink set status =1 where userId=:userId and recType=:userType";
		this.commonDao.updateByHql(hql,new String[]{"userId","userType"},new Object[]{invitiCode,userType});

	}

	@Override
	public String queryTemplateByCode(String code) throws Exception {
		MessageSystem message = this.queryByCode(code);
		if(message != null){
			return message.getContent();
		}
		return null;
	}
	
	private MessageSystem queryByCode(String code) {
		String hql = "from MessageSystem where code=:code and status=0";
		
		List<MessageSystem> messages = baseDao.findByNamedParam(hql, new String[]{"code"}, new Object[]{code});
		if(messages != null && messages.size() > 0){
			return messages.get(0)	;
		}
		return null;
	}

	@Override
	public Boolean deleteSystemMessage(String userId,String msgCode, Integer recType) throws Exception {
		Boolean result = Boolean.FALSE;
		try {
			MessageSystemLink sys = new MessageSystemLink();
			sys.setUserId(userId);
			sys.setRecType(recType);
			sys.setMsgCode(msgCode);
			
			commonDao.delete(sys);
			result = Boolean.TRUE;
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	public MessageSystem queryMessageSystemByCode(String code) throws Exception {

		try {
			
			MessageSystem system = new MessageSystem();
			system.setCode(code);
			
			return commonDao.findUniqueObj(system);
		} catch (Exception e) {
			LOGGER.error("根据code查询消息模板异常",e);
			throw e;
		}
	}
	
}
