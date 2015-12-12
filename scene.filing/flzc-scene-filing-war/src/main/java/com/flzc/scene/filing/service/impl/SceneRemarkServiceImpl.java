package com.flzc.scene.filing.service.impl;

import com.flzc.base.service.impl.BaseServiceImpl;
import com.flzc.scene.filing.api.entity.SceneRemark;
import com.flzc.scene.filing.api.service.SceneRemarkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;

/**
 * 案场备注用户实现类
 * Created by song on 2015/11/18.
 */
@Service("sceneRemarkService")
public class SceneRemarkServiceImpl extends BaseServiceImpl implements SceneRemarkService {
    private static  final Logger LOGGER = LoggerFactory.getLogger(SceneRemarkServiceImpl.class);
    /**
     * 保存案场用户备注信息
     *
     * @param sceneRemark
     * @return
     */
    @Override
    public SceneRemark saveSceneRemark(SceneRemark sceneRemark) {
        try {
            sceneRemark.setCreateAt(new Date());
            Serializable id = this.save(sceneRemark) ;
            sceneRemark.setId(Integer.valueOf(id.toString()));
            return sceneRemark;
        } catch (Exception e) {
            LOGGER.error("保存案场备注用户信息异常",e);
            throw e ;
        }
    }
}
