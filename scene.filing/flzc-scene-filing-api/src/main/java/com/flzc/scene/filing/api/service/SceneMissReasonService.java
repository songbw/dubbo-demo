package com.flzc.scene.filing.api.service;

import com.flzc.scene.filing.api.entity.SceneMissReason;

/**
 *  爽约原因服务
 * Created by song on 2015/11/18.
 */
public interface SceneMissReasonService {
    /**
     *  保存爽约原因服务
     * @param sceneMissReason
     * @return
     */
    public SceneMissReason saveSceneMissReason(SceneMissReason sceneMissReason);
}
