package com.flzc.service.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.flzc.scene.filing.api.entity.SceneInfo;
import com.flzc.scene.filing.api.service.SceneInfoService;
import com.flzc.service.service.MessageBizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flzc.base.util.UserUtil;
import com.flzc.message.api.service.MessageService;
import com.flzc.service.common.Constant;

@Service
public class MessageBizServiceImpl implements MessageBizService {

    private static  final Logger logger = LoggerFactory.getLogger(MessageBizServiceImpl.class);

    @Autowired
    private MessageService messageService;

    @Autowired
    private SceneInfoService sceneInfoService;

    @Override
    public Map<String, Object> getUserMessageList(String tokenId,
                                                  Integer userType,Integer curPage,Integer pageSize) throws Exception {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {

            int sceneId = UserUtil.getUserIdByTokenId(tokenId);

            SceneInfo sceneInfo = sceneInfoService.findById(sceneId);
            if(sceneInfo == null )
                return resultMap;

            List<Map<String, Object>> messageList = messageService.getUserMessageList(sceneInfo.getInviteCode(), userType, curPage, pageSize);

            //获取完成列表之后，把消息设置为已读
            if(messageList !=null && !messageList.isEmpty())
                messageService.updateAllRead(sceneInfo.getInviteCode(), userType);

            resultMap.put(Constant.RESULT_FIELD_STATUS, Constant.RESULT_MSG_0);
            resultMap.put(Constant.RESULT_FIELD_MSG, Constant.RESULT_MSG.get(Constant.RESULT_MSG_0));
            resultMap.put(Constant.RESULT_FIELD_DATA, (messageList==null || messageList.isEmpty())?new ArrayList<>():messageList);
        } catch (Exception e) {
            throw e;
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> getUserUnReadMessageNumber(String tokenId,Integer recType) throws Exception {

        Map<String, Object> map = new HashMap<String,Object>();
        try {

            int sceneId = UserUtil.getUserIdByTokenId(tokenId);
            SceneInfo sceneInfo = sceneInfoService.findById(sceneId);
            //用户邀请码
            String inviteCode = sceneInfo==null?" ":sceneInfo.getInviteCode();
            int unReadNum = messageService.queryUnreadMessageNum(inviteCode, recType);

            map.put("unReadNum", unReadNum);
        } catch (Exception e) {
            logger.error("获取用户未读消息异常",e);
            throw e;
        }

        return map;
    }

    @Override
    public void updateUserMessageStatus(String tokenId,Integer recType)
            throws Exception {

        try {

            int sceneId = UserUtil.getUserIdByTokenId(tokenId);
            SceneInfo sceneInfo = sceneInfoService.findById(sceneId);
            //用户邀请码
            String inviteCode = sceneInfo==null?" ":sceneInfo.getInviteCode();
            messageService.updateAllRead(inviteCode, recType);

        } catch (Exception e) {
            logger.error("更新用户消息状态异常",e);
            throw e;
        }
    }

}

