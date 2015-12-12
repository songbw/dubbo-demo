package com.flzc.service.service;

import com.flzc.scene.filing.api.entity.SceneFeedback;
import com.flzc.service.exception.SceneException;

/**
 *  案场经纪人意见反馈服务
 * Created by song on 2015/11/20.
 */
public interface SceneFeedbackBizService {
    /**
     *  添加意见反馈
     *
     * @param sceneFeedback
     * @return
     */
    public String addSceneFeedback(String token , SceneFeedback sceneFeedback) throws SceneException;
}
