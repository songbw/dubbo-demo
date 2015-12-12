package com.flzc.scene.filing.api.service;

import com.flzc.scene.filing.api.entity.SceneDealImg;

/**
 *  合同图片服务
 * Created by song on 2015/11/18.
 */
public interface SceneDealImgService {
    /**
     * 保存合同图片信息
     * @param sceneDealImg
     * @return
     */
    public SceneDealImg saveSceneDealImg(SceneDealImg sceneDealImg);
}
