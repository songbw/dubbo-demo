package com.flzc.scene.filing.service.impl;

import com.flzc.base.service.impl.BaseServiceImpl;
import com.flzc.scene.filing.api.entity.SceneInvalidReason;
import com.flzc.scene.filing.api.service.SceneInvalidReasonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;

/**
 *  无效原因服务实现类
 * Created by song on 2015/11/18.
 */
@Service("sceneInvalidReasonService")
public class SceneInvalidReasonServiceImpl extends BaseServiceImpl implements SceneInvalidReasonService{
    private static  final Logger LOGGER = LoggerFactory.getLogger(SceneInfoServiceImpl.class);
    /**
     * 保存无效原因
     *
     * @param sceneInvalidReason
     * @return
     */
    @Override
    public SceneInvalidReason saveSceneInvalidReason(SceneInvalidReason sceneInvalidReason) {
        try {
            sceneInvalidReason.setCreateAt(new Date());
            Serializable id = this.save(sceneInvalidReason) ;
            sceneInvalidReason.setId(Integer.valueOf(id.toString()));
            return sceneInvalidReason;
        } catch (Exception e) {
            LOGGER.error("保存无效原因异常",e);
            throw e ;
        }
    }
}
