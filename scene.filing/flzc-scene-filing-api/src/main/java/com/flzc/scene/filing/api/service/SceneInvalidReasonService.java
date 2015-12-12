package com.flzc.scene.filing.api.service;

import com.flzc.scene.filing.api.entity.SceneInvalidReason;

/**
 *  无效原因服务
 * Created by song on 2015/11/18.
 */
public interface SceneInvalidReasonService {

    /**
     *  保存无效原因
     * @param sceneInvalidReason
     * @return
     */
    public SceneInvalidReason saveSceneInvalidReason(SceneInvalidReason sceneInvalidReason);
}
