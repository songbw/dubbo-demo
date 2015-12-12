package com.flzc.quartz.service;

/**
 * 所有活动接口服务类
 */
public interface ActivityService {
    /**
     * 所有竞拍活动置为无效
     */
    public void updateAuctionOver();

    void updateQuestionOver();

    void updateCustomizationOver();

    void updateActivityRecapOver();
}
