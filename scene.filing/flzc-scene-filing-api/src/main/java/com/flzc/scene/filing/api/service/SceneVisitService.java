package com.flzc.scene.filing.api.service;

import com.flzc.scene.filing.api.entity.SceneVisit;

/**
 *  用户看房服务
 * Created by song on 2015/11/18.
 */
public interface SceneVisitService {
    /**
     *  保存用户看房信息
     * @param sceneVisit
     * @return
     */
    public SceneVisit saveSceneVisit(SceneVisit sceneVisit);
}
