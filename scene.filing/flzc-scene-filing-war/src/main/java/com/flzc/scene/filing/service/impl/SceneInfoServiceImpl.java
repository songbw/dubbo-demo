package com.flzc.scene.filing.service.impl;

import com.flzc.base.service.impl.BaseServiceImpl;
import com.flzc.scene.filing.api.entity.SceneInfo;
import com.flzc.scene.filing.api.service.SceneInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *  案场信息服务实现类
 * Created by song on 2015/11/18.
 */
@Service("sceneInfoService")
public class SceneInfoServiceImpl extends BaseServiceImpl implements SceneInfoService{

    private static  final Logger LOGGER = LoggerFactory.getLogger(SceneInfoServiceImpl.class);
    /**
     * 根据用户名查询案场信息
     *
     * @param username
     * @return
     */
    @Override
    public SceneInfo findByUsername(java.lang.String username) {
        List<SceneInfo> result = this.findByNamedParam("from SceneInfo where user_name = :user_name","user_name", username);
        if (result == null || result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    /**
     * 根据案场ID查询案场信息
     *
     * @param id
     * @return
     */
    @Override
    public SceneInfo findById(Integer id) {
        List<SceneInfo> result = this.findByNamedParam("from SceneInfo where id = :id","id", id) ;
        if (result == null || result.isEmpty()) {
            return null ;
        }
        return result.get(0);
    }

    /**
     * 更新案场信息
     *
     * @param sceneInfo
     */
    @Override
    public void updateSceneInfo(SceneInfo sceneInfo) {
        try {
            sceneInfo.setUpdateTime(new Date());
            this.update(sceneInfo);
        } catch (Exception e) {
            LOGGER.error("更新案场信息异常", e);
            throw e ;
        }
    }

}
