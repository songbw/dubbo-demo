package com.flzc.service.api;

import com.flzc.base.util.Memcached;
import com.flzc.scene.filing.api.entity.SceneFeedback;
import com.flzc.service.exception.ErrorBean;
import com.flzc.service.exception.SceneException;
import com.flzc.service.exception.SceneResponseException;
import com.flzc.service.service.SceneFeedbackBizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * 意见反馈API
 * Created by song on 2015/11/20.
 */
@Component
@Scope
@Path("/feedback")
public class SceneFeedbackApi {

    private static  final Logger LOGGER = LoggerFactory.getLogger(SceneFeedbackApi.class);
    @Autowired
    public SceneFeedbackBizService sceneFeedbackBizService ;

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String sceneFeedback(@FormParam("tokenId") String tokenId, @FormParam("feedbackInfo") String feedbackInfo)  throws Exception {
        LOGGER.error(tokenId + "    " + feedbackInfo);
        String result = "" ;
        SceneFeedback sceneFeedback = new SceneFeedback() ;
        ErrorBean errorBean = new ErrorBean() ;
        sceneFeedback.setFeedbackInfo(feedbackInfo);
        if (feedbackInfo == null ||  "".equals(feedbackInfo)) {
            errorBean.setErrno("300009");
            errorBean.setErrno("反馈信息不能为空！");
            throw new SceneResponseException(errorBean) ;
        }
        if (Memcached.keyExists(tokenId)) {
            try {
                result = sceneFeedbackBizService.addSceneFeedback(tokenId,sceneFeedback) ;
            } catch (SceneException e) {
                throw new SceneResponseException(e.getErrorBean()) ;
            }
        } else {
            errorBean.setErrno("300101");
            errorBean.setMsg("tokenId验证失败！");
            throw new SceneResponseException(errorBean) ;
        }
        return result;
    }
}
