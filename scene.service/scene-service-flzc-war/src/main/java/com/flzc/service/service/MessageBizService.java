package com.flzc.service.service;

import java.util.Map;

/**
 * 消息业务服务接口
 * @author song
 *
 */
public interface MessageBizService {

    /**
     * 获取用户消息
     * @param tokenId
     * @param userType
     * @param curPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    public Map<String, Object> getUserMessageList(String tokenId,Integer userType,Integer curPage,Integer pageSize) throws Exception;

    /**
     * 获取用户未读消息
     * @param tokenId
     * @param recType
     * @return
     * @throws Exception
     */
    public Map<String, Object> getUserUnReadMessageNumber(String tokenId,Integer recType) throws Exception;

    /**
     * 更新用户消息状态
     * @param tokenId
     * @param recType
     * @return
     * @throws Exception
     */
    public void updateUserMessageStatus(String tokenId,Integer recType) throws Exception;
}

