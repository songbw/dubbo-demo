package com.flzc.scene.filing.api.service;

import com.flzc.scene.filing.api.entity.SceneInfo;

/**
 *  案场信息服务
 * Created by song on 2015/11/18.
 */
public interface SceneInfoService {
    /**
     *  根据用户名查询案场信息
     * @param username
     * @return
     */
    public SceneInfo findByUsername(String username);

    /**
     *  根据案场ID查询案场信息
     * @param id
     * @return
     */
    public SceneInfo findById(Integer id) ;

    /**
     *  更新案场信息
     * @param sceneInfo
     */
    public void updateSceneInfo(SceneInfo sceneInfo) ;
}
