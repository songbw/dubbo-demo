package com.flzc.scene.filing.api.service;

import com.flzc.scene.filing.api.entity.SceneFeedback;

/**
 *  案场意见反馈服务
 * Created by song on 2015/11/18.
 */
public interface SceneFeedbackService {

    /**
     *  保存意见反馈信息
     * @param sceneFeedback
     * @return
     */
    public SceneFeedback saveSceneFeedback(SceneFeedback sceneFeedback);
}
