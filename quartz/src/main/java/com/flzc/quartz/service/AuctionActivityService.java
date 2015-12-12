package com.flzc.quartz.service;

import java.util.List;
import java.util.Map;

/**
 * 竞拍活动服务类
 */
public interface AuctionActivityService {

    /**
     * 查出今天结束的活动
     * @return
     */
    public List<Map<String,Object>> queryEndRecordToday();

    Map<String,Object> queryWinner(Integer activityId, Integer targetPrice);

    /**
     * 设定活动拍得者
     * @param activityId
     */
    public  void updateForWinner(Integer activityId  ,Integer targetPrice) throws Exception;

    void queryMoneyBack(Integer userId, String orderSeq) throws Exception;

    List<Map<String,Object>> queryReturnList(Integer actId);

    void addHeadline(Integer activityId, Integer winnerUserId) throws Exception;

    int queryInvalid(Integer activityId);

    void updateHandleStatus(Integer activityId);
}
