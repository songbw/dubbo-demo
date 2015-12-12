package com.flzc.service.service.impl;

import com.flzc.base.util.Memcached;
import com.flzc.scene.filing.api.entity.SceneFeedback;
import com.flzc.scene.filing.api.service.SceneFeedbackService;
import com.flzc.service.exception.ErrorBean;
import com.flzc.service.exception.SceneException;
import com.flzc.service.service.SceneFeedbackBizService;
import com.sun.jersey.api.spring.Autowire;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by song on 2015/11/20.
 */
@Service
public class SceneFeedbackBizServiceImpl implements SceneFeedbackBizService {
    private static  final Logger logger = LoggerFactory.getLogger(SceneFeedbackBizServiceImpl.class) ;

    @Autowired
    public SceneFeedbackService sceneFeedbackService ;
    /**
     * 添加意见反馈
     *
     * @param sceneFeedback
     * @return
     */
    @Override
    public String addSceneFeedback(String token ,SceneFeedback sceneFeedback) throws SceneException {
        ErrorBean errorBean = new ErrorBean() ;
        String result = "" ;
        int id  = (int) Memcached.get(token);
        sceneFeedback.setSceneId(id);
        try {
            sceneFeedback = sceneFeedbackService.saveSceneFeedback(sceneFeedback) ;
        } catch (Exception e) {
            logger.error("保存数据库失败",e);
            errorBean.setErrno("100100");
            errorBean.setMsg("意见反馈后台异常"); ;
            throw new SceneException(errorBean) ;
        }
        return "{\"status\":0,\"result\":"+sceneFeedback.getId()+"}";
    }
}
