package com.flzc.scene.filing.service.impl;

import com.flzc.base.service.impl.BaseServiceImpl;
import com.flzc.scene.filing.api.entity.SceneFeedback;
import com.flzc.scene.filing.api.service.SceneFeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;

/**
 *  案场意见反馈实现类
 * Created by song on 2015/11/18.
 */
@Service("sceneFeedbackService")
public class SceneFeedbackServiceImpl extends BaseServiceImpl implements SceneFeedbackService {
    private static  final Logger LOGGER = LoggerFactory.getLogger(SceneFeedbackServiceImpl.class);
    /**
     * 保存意见反馈信息
     * @param sceneFeedback  反馈信息
     * @return
     */
    @Override
    public SceneFeedback saveSceneFeedback(SceneFeedback sceneFeedback) {
        try {
            sceneFeedback.setCreateAt(new Date());
            Serializable id =  this.save(sceneFeedback) ;
            sceneFeedback.setId(Integer.valueOf(id.toString()));
            return sceneFeedback ;
        } catch (Exception e) {
            LOGGER.error("保存案场信息异常",e);
            throw  e ;
        }
    }
}
