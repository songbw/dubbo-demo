package com.flzc.scene.filing.service.impl;

import com.flzc.base.service.impl.BaseServiceImpl;
import com.flzc.scene.filing.api.entity.SceneVisit;
import com.flzc.scene.filing.api.service.SceneVisitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;

/**
 *  用户看房服务实现类
 * Created by song on 2015/11/18.
 */
@Service("sceneVisitService")
public class SceneVisitServiceImpl extends BaseServiceImpl implements SceneVisitService{
    private static  final Logger LOGGER = LoggerFactory.getLogger(SceneVisitServiceImpl.class);

    /**
     * 保存用户看房信息
     * @param sceneVisit
     * @return
     */
    @Override
    public SceneVisit saveSceneVisit(SceneVisit sceneVisit) {
        try {
            sceneVisit.setCreateAt(new Date());
            Serializable id = this.save(sceneVisit) ;
            sceneVisit.setId(Integer.valueOf(id.toString()));
            return sceneVisit;
        } catch (Exception e) {
            LOGGER.error("保存用户看房信息异常",e);
            throw e ;
        }
    }
}
