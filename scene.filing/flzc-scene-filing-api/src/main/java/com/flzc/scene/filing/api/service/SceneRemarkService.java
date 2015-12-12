package com.flzc.scene.filing.api.service;

import com.flzc.scene.filing.api.entity.SceneRemark;

/**
 *   案场用户备注服务
 * Created by song on 2015/11/18.
 */
public interface SceneRemarkService {
    /**
     *  保存案场用户备注信息
     * @param sceneRemark
     * @return
     */
    public SceneRemark saveSceneRemark(SceneRemark sceneRemark);
}
